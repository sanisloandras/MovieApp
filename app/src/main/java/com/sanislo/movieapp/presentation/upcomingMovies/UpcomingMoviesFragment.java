package com.sanislo.movieapp.presentation.upcomingMovies;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout;
import com.sanislo.movieapp.R;
import com.sanislo.movieapp.databinding.FragmentUpcomingMoviesBinding;
import com.sanislo.movieapp.domain.model.MovieListItemModel;
import com.sanislo.movieapp.presentation.HasDualPaneSupport;
import com.sanislo.movieapp.presentation.movieDetails.MovieDetailsFragment;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class UpcomingMoviesFragment extends Fragment {
    private static final String TAG = UpcomingMoviesFragment.class.getSimpleName();

    private static final int SPAN_COUNT_PORTRAIT = 2;
    private static final int SPAN_COUNT_LANDSCAPE = 4;

    @Inject
    ViewModelProvider.Factory mFactory;

    private HasDualPaneSupport hasDualPaneSupport;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private FragmentUpcomingMoviesBinding mBinding;
    private UpcomingMoviesViewModel mViewModel;
    private UpcomingMoviesAdapter mAdapter;

    public static UpcomingMoviesFragment newInstance() {
        Bundle args = new Bundle();
        UpcomingMoviesFragment fragment = new UpcomingMoviesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            hasDualPaneSupport = (HasDualPaneSupport) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("Parent activity must implement " + HasDualPaneSupport.class.getSimpleName());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        obtainViewModel();
        int movieTitleText = ContextCompat.getColor(requireContext(), R.color.default_movie_title_text_color);
        int movieTitleBackground = ContextCompat.getColor(requireContext(), R.color.default_movie_title_background_color);
        mAdapter = new UpcomingMoviesAdapter(getDiffCallback(), movieTitleText, movieTitleBackground, mClickInteractor);
        mViewModel.getMovieListItemEntityLiveData().observe(this, movieListItemEntities -> {
            Log.d(TAG, "onCreate: " + movieListItemEntities);
            Log.d(TAG, "onCreate: " + movieListItemEntities.size());
            mAdapter.submitList(movieListItemEntities);
        });
    }

    @NonNull
    private DiffUtil.ItemCallback<MovieListItemModel> getDiffCallback() {
        return new DiffUtil.ItemCallback<MovieListItemModel>() {
            @Override
            public boolean areItemsTheSame(MovieListItemModel oldItem, MovieListItemModel newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(MovieListItemModel oldItem, MovieListItemModel newItem) {
                return oldItem.equals(newItem);
            }
        };
    }

    private UpcomingMoviesAdapter.ClickInteractor mClickInteractor = new UpcomingMoviesAdapter.ClickInteractor() {
        @Override
        public void onItemClick(MovieListItemModel movieListItemModel) {
            MovieDetailsFragment fragment = MovieDetailsFragment.newInstance(movieListItemModel.getId());
            int containerId = hasDualPaneSupport.isInDualPaneMode() ?
                    hasDualPaneSupport.rightContainer()
                    : hasDualPaneSupport.leftContainerId();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(containerId, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    };

    private void obtainViewModel() {
        Log.d(TAG, "obtainViewModel: " + mFactory);
        mViewModel = ViewModelProviders.of(
                this,
                mFactory)
                .get(UpcomingMoviesViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_upcoming_movies,
                container,
                false);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();
        setupSwipeRefresh();
        mViewModel.getIsRefreshing().observe(this, isRefreshing -> mBinding.refresh.setRefreshing(isRefreshing));
    }

    private void setupAdapter() {
        int spanCount = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE ?
                SPAN_COUNT_LANDSCAPE : SPAN_COUNT_PORTRAIT;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        mBinding.rvUpcomingMovies.setLayoutManager(gridLayoutManager);
        mBinding.rvUpcomingMovies.setAdapter(mAdapter);
    }

    private void setupSwipeRefresh() {
        Disposable swipeRefreshDisposable =
                RxSwipeRefreshLayout.refreshes(mBinding.refresh)
                        .subscribe(o -> mViewModel.refresh());
        compositeDisposable.add(swipeRefreshDisposable);
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
    }

    @Override
    public void onDestroyView() {
        compositeDisposable.clear();
        super.onDestroyView();
    }
}
