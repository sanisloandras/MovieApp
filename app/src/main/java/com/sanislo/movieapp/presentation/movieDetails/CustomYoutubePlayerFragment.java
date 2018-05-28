package com.sanislo.movieapp.presentation.movieDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.sanislo.movieapp.BuildConfig;

public class CustomYoutubePlayerFragment extends YouTubePlayerSupportFragment {
    public static final String TAG = CustomYoutubePlayerFragment.class.getSimpleName();
    private static final String EXTRA_ID = "EXTRA_ID";
    private static final int RQS_ErrorDialog = 7;

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
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(BuildConfig.ANDROID_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(getArguments().getString(EXTRA_ID));
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                if (youTubeInitializationResult.isUserRecoverableError()) {
                    youTubeInitializationResult.getErrorDialog(getActivity(), RQS_ErrorDialog).show();
                } else {
                    Snackbar.make(getView(), youTubeInitializationResult.toString(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}
