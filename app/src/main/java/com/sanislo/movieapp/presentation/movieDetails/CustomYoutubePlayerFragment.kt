package com.sanislo.movieapp.presentation.movieDetails

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.sanislo.movieapp.BuildConfig

class CustomYoutubePlayerFragment : YouTubePlayerSupportFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(BuildConfig.ANDROID_API_KEY, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, b: Boolean) {
                youTubePlayer.cueVideo(arguments!!.getString(EXTRA_ID))
            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider, youTubeInitializationResult: YouTubeInitializationResult) {
                if (youTubeInitializationResult.isUserRecoverableError) {
                    youTubeInitializationResult.getErrorDialog(activity!!, RQS_ErrorDialog).show()
                } else {
                    Snackbar.make(getView()!!, youTubeInitializationResult.toString(), Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    companion object {
        val TAG = CustomYoutubePlayerFragment::class.java.simpleName
        private const val EXTRA_ID = "EXTRA_ID"
        private const val RQS_ErrorDialog = 7

        fun newInstance(id: String): CustomYoutubePlayerFragment {
            val args = Bundle().apply {
                putString(EXTRA_ID, id)
            }
            return CustomYoutubePlayerFragment().apply {
                arguments = args
            }
        }
    }
}
