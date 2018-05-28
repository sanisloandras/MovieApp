package com.sanislo.movieapp.domain.video;

import android.arch.lifecycle.LiveData;

import com.sanislo.movieapp.domain.model.VideoModel;
import com.sanislo.movieapp.domain.model.YoutubeVideoModel;
import com.sanislo.movieapp.persistence.response.movieVideos.MovieVideosResponse;

import java.util.List;

import io.reactivex.Single;

public interface VideoRepository {
    Single<MovieVideosResponse> videos(int movieId);
    LiveData<List<VideoModel>> videosLiveData(int movieId);
    LiveData<List<YoutubeVideoModel>> youtubeVideosLiveData(int movieId);
}
