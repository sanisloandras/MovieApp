package com.sanislo.movieapp.presentation.upcomingMovies;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanislo.movieapp.R;
import com.sanislo.movieapp.databinding.FragmentUpcomingMoviesBinding;
import com.sanislo.movieapp.domain.model.MovieListItemModel;
import com.sanislo.movieapp.presentation.movieDetails.MovieDetailsFragment;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class UpcomingMoviesFragment extends Fragment {
    private static final String TAG = UpcomingMoviesFragment.class.getSimpleName();

    @Inject
    ViewModelProvider.Factory mFactory;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        obtainViewModel();
        mAdapter = new UpcomingMoviesAdapter(getDiffCallback(), mClickInteractor);
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
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_fragment_container, fragment)
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
        mBinding.rvUpcomingMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvUpcomingMovies.setAdapter(mAdapter);
    }
}
