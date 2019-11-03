package com.sanislo.movieapp.domain.video

import androidx.lifecycle.LiveData

import com.sanislo.movieapp.domain.model.VideoModel
import com.sanislo.movieapp.domain.model.YoutubeVideoModel
import com.sanislo.movieapp.persistence.response.movieVideos.MovieVideosResponse

import io.reactivex.Single

interface VideoRepository {
    fun videos(movieId: Int): Single<MovieVideosResponse>
    fun videosLiveData(movieId: Int): LiveData<List<VideoModel>>
    fun youtubeVideosLiveData(movieId: Int): LiveData<List<YoutubeVideoModel>>
}
