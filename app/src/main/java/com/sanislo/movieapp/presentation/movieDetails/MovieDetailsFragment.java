package com.sanislo.movieapp.presentation.movieDetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sanislo.movieapp.R;
import com.sanislo.movieapp.databinding.FragmentMovieDetailsBinding;
import com.sanislo.movieapp.domain.model.MovieModel;
import com.sanislo.movieapp.domain.model.VideoModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MovieDetailsFragment extends Fragment {
    public static final String TAG = MovieDetailsFragment.class.getSimpleName();
    private static final String EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID";

    @Inject
    ViewModelProvider.Factory mFactory;

    private FragmentMovieDetailsBinding mBinding;
    private MovieDetailsViewModel mViewModel;
    private int movieId;

    public static MovieDetailsFragment newInstance(int movieId) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_MOVIE_ID, movieId);
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        obtainViewModel();
        mViewModel.loadMovie(getArguments().getInt(EXTRA_MOVIE_ID));
    }

    private void obtainViewModel() {
        mViewModel = ViewModelProviders.of(
                this,
                mFactory)
                .get(MovieDetailsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_movie_details,
                container,
                false);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getMovieModelLiveData(getArguments().getInt(EXTRA_MOVIE_ID))
                .observe(this, movieModel -> {
                    if (movieModel == null) return;
                    Log.d(TAG, "onChanged: " + movieModel);
                    mBinding.tvMovieTitle.setText(movieModel.getTitle());
                    mBinding.tvOverview.setText(movieModel.getOverview());
                    mBinding.tvReleaseDate.setText(movieModel.getReleaseDate());
                    Glide.with(getContext())
                            .load("https://image.tmdb.org/t/p/original/"
                                    + movieModel.getBackdropPath())
                            .into(mBinding.ivBackdrop);
                    Glide.with(getContext())
                            .load("https://image.tmdb.org/t/p/original/"
                                    + movieModel.getPosterPath())
                            .into(mBinding.ivPoster);
                });
        mViewModel.getVideosForMovieLiveData(getArguments().getInt(EXTRA_MOVIE_ID))
                .observe(this, videoModels -> {
                    Log.d(TAG, "onChanged: videoModels: " + videoModels);
                });
    }
}
