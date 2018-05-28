package com.sanislo.movieapp.presentation.movieDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.sanislo.movieapp.BuildConfig;

public class CustomYoutubePlayerFragment extends YouTubePlayerSupportFragment {
    public static final String TAG = CustomYoutubePlayerFragment.class.getSimpleName();
    private static final String EXTRA_ID = "EXTRA_ID";

    public static CustomYoutubePlayerFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString(EXTRA_ID, id);
        CustomYoutubePlayerFragment fragment = new CustomYoutubePlayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initialize(BuildConfig.ANDROID_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(getArguments().getString(EXTRA_ID));
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) view;
    }
}
