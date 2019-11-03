package com.sanislo.movieapp.presentation.movieDetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sanislo.movieapp.R
import com.sanislo.movieapp.databinding.FragmentMovieDetailsBinding
import com.sanislo.movieapp.domain.model.YoutubeVideoModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    private lateinit var mBinding: FragmentMovieDetailsBinding
    private lateinit var mViewModel: MovieDetailsViewModel
    private lateinit var youtubeVideosAdapter: YoutubeVideosAdapter

    private val diffCallback: DiffUtil.ItemCallback<YoutubeVideoModel> = object : DiffUtil.ItemCallback<YoutubeVideoModel>() {
            override fun areItemsTheSame(oldItem: YoutubeVideoModel, newItem: YoutubeVideoModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: YoutubeVideoModel, newItem: YoutubeVideoModel): Boolean {
                return oldItem == newItem
            }
        }

    private val clickInteractor = object : YoutubeVideosAdapter.ClickInteractor {
        override fun onClick(youtubeVideoModel: YoutubeVideoModel) {
            //TODO move navigation logic to vm
            val customYoutubePlayerFragment = CustomYoutubePlayerFragment.newInstance(youtubeVideoModel.key)
            activity!!.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fl_movie_list_container, customYoutubePlayerFragment)
                    .addToBackStack(null)
                    .commit()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity!!.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        activity!!.window.statusBarColor = Color.TRANSPARENT
        obtainViewModel()
        youtubeVideosAdapter = YoutubeVideosAdapter(diffCallback, clickInteractor)
        mViewModel.loadMovie(arguments!!.getInt(EXTRA_MOVIE_ID))
    }

    private fun obtainViewModel() {
        mViewModel = ViewModelProviders.of(
                this,
                mFactory)
                .get(MovieDetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate<FragmentMovieDetailsBinding>(inflater,
                R.layout.fragment_movie_details,
                container,
                false)
        mBinding.viewModel = mViewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
        setupYoutubeVideosList()
        observeMovieDetails()
        observeYoutubeVideos()
        observeErrors()
    }

    private fun setupYoutubeVideosList() {
        mBinding.rvYoutubeVideos.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL,
                false)
        mBinding.rvYoutubeVideos.adapter = youtubeVideosAdapter
    }

    private fun setupActionBar() {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(mBinding.toolbar)
            supportActionBar?.apply {
                setDisplayShowHomeEnabled(true)
                setDisplayHomeAsUpEnabled(true)
                title = null
            }
        }
    }

    private fun observeErrors() {
        mViewModel.error.observe(this, Observer { throwable ->
            Snackbar.make(mBinding.root, if (throwable != null)
                throwable.localizedMessage
            else
                getString(R.string.error), Snackbar.LENGTH_LONG).show()
        })
    }

    private fun observeYoutubeVideos() {
        mViewModel.getYoutubeVideosForMovie(arguments!!.getInt(EXTRA_MOVIE_ID))
                .observe(this, Observer { youtubeVideoModels ->
                    youtubeVideosAdapter.submitList(youtubeVideoModels)
                })
    }

    private fun observeMovieDetails() {
        mViewModel.getMovieModelLiveData(arguments!!.getInt(EXTRA_MOVIE_ID))
                .observe(this, Observer { movieModel ->
                    if (movieModel == null) return@Observer
                    mBinding.tvMovieTitle.text = movieModel.getTitle()
                    mBinding.tvOverview.text = movieModel.getOverview()
                    mBinding.tvReleaseDate.text = movieModel.getReleaseDate()
                    Glide.with(context!!)
                            .load("https://image.tmdb.org/t/p/original/" + movieModel.getBackdropPath())
                            .into(mBinding.ivBackdrop)
                    Glide.with(context!!)
                            //TODO move this to business logic layer
                            .load("https://image.tmdb.org/t/p/original/" + movieModel.getPosterPath())
                            .into(mBinding.ivPoster)
                })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity!!.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        val TAG = MovieDetailsFragment::class.java.simpleName
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        //TODO use Navigation component
        fun newInstance(movieId: Int): MovieDetailsFragment {
            val args = Bundle().apply {
                putInt(EXTRA_MOVIE_ID, movieId)
            }
            val fragment = MovieDetailsFragment().apply {
                arguments = args
            }
            return fragment
        }
    }
}
