package com.sanislo.movieapp.presentation.upcomingMovies

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.snackbar.Snackbar
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
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var hasDualPaneSupport: HasDualPaneSupport

    private val compositeDisposable = CompositeDisposable()
    lateinit var binding: FragmentUpcomingMoviesBinding
    lateinit var viewModel: UpcomingMoviesViewModel
    lateinit var adapter: UpcomingMoviesAdapter

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
                val containerId = if (hasDualPaneSupport.isInDualPaneMode())
                    hasDualPaneSupport.rightContainer()
                else
                    hasDualPaneSupport.leftContainerId()
                activity!!.supportFragmentManager
                        .beginTransaction()
                        .replace(containerId, fragment)
                        .addToBackStack(null)
                        .commit()
            }
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        try {
            hasDualPaneSupport = context as HasDualPaneSupport
        } catch (e: ClassCastException) {
            throw RuntimeException("Parent activity must implement " + HasDualPaneSupport::class.java.simpleName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        obtainViewModel()
    }

    private fun obtainViewModel() {
        viewModel = ViewModelProviders.of(
                this,
                factory)
                .get(UpcomingMoviesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_upcoming_movies,
                container,
                false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupSwipeRefresh()
        observeRefreshing()
        observeErrors()
        observeMoviesList()
    }

    private fun observeRefreshing() {
        viewModel.isRefreshing.observe(viewLifecycleOwner, Observer { isRefreshing -> binding.refresh.isRefreshing = isRefreshing!! })
    }

    private fun setupAdapter() {
        val spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            SPAN_COUNT_LANDSCAPE
        else
            SPAN_COUNT_PORTRAIT
        val gridLayoutManager = androidx.recyclerview.widget.GridLayoutManager(context, spanCount)
        val movieTitleText = ContextCompat.getColor(requireContext(), R.color.default_movie_title_text_color)
        val movieTitleBackground = ContextCompat.getColor(requireContext(), R.color.default_movie_title_background_color)
        adapter = UpcomingMoviesAdapter(diffCallback, movieTitleText, movieTitleBackground, mClickInteractor)
        binding.rvUpcomingMovies.layoutManager = gridLayoutManager
        binding.rvUpcomingMovies.adapter = adapter
    }

    private fun observeMoviesList() {
        viewModel.upcomingMoviesLiveData.observe(viewLifecycleOwner, Observer { movieListItemEntities ->
            adapter.submitList(movieListItemEntities)
        })
    }

    private fun setupSwipeRefresh() {
        val swipeRefreshDisposable = RxSwipeRefreshLayout.refreshes(binding.refresh)
                .subscribe { _ -> viewModel.refresh() }
        compositeDisposable.add(swipeRefreshDisposable)
    }

    private fun observeErrors() {
        viewModel.error.observe(viewLifecycleOwner, Observer { throwable ->
            Snackbar.make(binding.root, if (throwable != null)
                throwable.localizedMessage
            else
                getString(R.string.error), Snackbar.LENGTH_LONG).show()
        })
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
            return UpcomingMoviesFragment().apply {
                arguments = args
            }
        }
    }
}
