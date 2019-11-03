package com.sanislo.movieapp.presentation.upcomingMovies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.res.Configuration
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import com.sanislo.movieapp.R
import com.sanislo.movieapp.databinding.FragmentUpcomingMoviesBinding
import com.sanislo.movieapp.domain.model.MovieListItemModel
import com.sanislo.movieapp.presentation.HasDualPaneSupport
import com.sanislo.movieapp.presentation.movieDetails.MovieDetailsFragment
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UpcomingMoviesFragment : Fragment() {

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    private var hasDualPaneSupport: HasDualPaneSupport? = null

    private val compositeDisposable = CompositeDisposable()
    lateinit var mBinding: FragmentUpcomingMoviesBinding
    lateinit var mViewModel: UpcomingMoviesViewModel
    lateinit var mAdapter: UpcomingMoviesAdapter

    private val diffCallback: DiffUtil.ItemCallback<MovieListItemModel> = object : DiffUtil.ItemCallback<MovieListItemModel>() {
            override fun areItemsTheSame(oldItem: MovieListItemModel, newItem: MovieListItemModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieListItemModel, newItem: MovieListItemModel): Boolean {
                return oldItem == newItem
            }
        }

    private val mClickInteractor = object : UpcomingMoviesAdapter.ClickInteractor {
        override fun onItemClick(movieListItemModel: MovieListItemModel?) {
            //TODO move navigation logic to vm
            movieListItemModel?.let {
                val fragment = MovieDetailsFragment.newInstance(movieListItemModel.id)
                val containerId = if (hasDualPaneSupport!!.isInDualPaneMode())
                    hasDualPaneSupport!!.rightContainer()
                else
                    hasDualPaneSupport!!.leftContainerId()
                activity!!.supportFragmentManager
                        .beginTransaction()
                        .replace(containerId, fragment)
                        .addToBackStack(null)
                        .commit()
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            hasDualPaneSupport = context as HasDualPaneSupport?
        } catch (e: ClassCastException) {
            throw RuntimeException("Parent activity must implement " + HasDualPaneSupport::class.java.simpleName)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        obtainViewModel()
        val movieTitleText = ContextCompat.getColor(requireContext(), R.color.default_movie_title_text_color)
        val movieTitleBackground = ContextCompat.getColor(requireContext(), R.color.default_movie_title_background_color)
        mAdapter = UpcomingMoviesAdapter(diffCallback, movieTitleText, movieTitleBackground, mClickInteractor)
        mViewModel.upcomingMoviesLiveData.observe(this, Observer { movieListItemEntities ->
            mAdapter.submitList(movieListItemEntities)
        })
    }

    private fun obtainViewModel() {
        mViewModel = ViewModelProviders.of(
                this,
                mFactory)
                .get(UpcomingMoviesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_upcoming_movies,
                container,
                false)
        mBinding.viewModel = mViewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupSwipeRefresh()
        observeResfreshing()
    }

    private fun observeResfreshing() {
        mViewModel.isRefreshing.observe(this, Observer { isRefreshing -> mBinding.refresh.isRefreshing = isRefreshing!! })
    }

    private fun setupAdapter() {
        val spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            SPAN_COUNT_LANDSCAPE
        else
            SPAN_COUNT_PORTRAIT
        val gridLayoutManager = GridLayoutManager(context, spanCount)
        mBinding.rvUpcomingMovies.layoutManager = gridLayoutManager
        mBinding.rvUpcomingMovies.adapter = mAdapter
    }

    private fun setupSwipeRefresh() {
        val swipeRefreshDisposable = RxSwipeRefreshLayout.refreshes(mBinding.refresh)
                .subscribe { _ -> mViewModel.refresh() }
        compositeDisposable.add(swipeRefreshDisposable)
    }

    override fun onStart() {
        super.onStart()
        activity!!.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        activity!!.window.statusBarColor = ContextCompat.getColor(context!!, R.color.colorPrimary)
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }

    companion object {
        private const val SPAN_COUNT_PORTRAIT = 2
        private const val SPAN_COUNT_LANDSCAPE = 4

        fun newInstance(): UpcomingMoviesFragment {
            val args = Bundle()
            val fragment = UpcomingMoviesFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
