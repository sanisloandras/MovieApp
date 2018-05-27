package com.sanislo.movieapp.domain.video;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import com.sanislo.movieapp.domain.mapper.MovieVideosReponseToVideoEntity;
import com.sanislo.movieapp.domain.mapper.VideoEntityToVideoModelMapper;
import com.sanislo.movieapp.domain.model.VideoModel;
import com.sanislo.movieapp.persistence.api.MovieAppApi;
import com.sanislo.movieapp.persistence.dao.VideoDao;
import com.sanislo.movieapp.persistence.entity.VideoEntity;
import com.sanislo.movieapp.persistence.response.movieVideos.MovieVideosResponse;

import java.util.List;

import io.reactivex.Single;

public class VideoRepositoryImpl implements VideoRepository {
    public static final String TAG = VideoRepository.class.getSimpleName();

    private MovieVideosReponseToVideoEntity movieVideosReponseToVideoEntity
            = new MovieVideosReponseToVideoEntity();
    private VideoEntityToVideoModelMapper videoEntityToVideoModelMapper
            = new VideoEntityToVideoModelMapper();

    private MovieAppApi movieAppApi;
    private VideoDao videoDao;

    public VideoRepositoryImpl(MovieAppApi movieAppApi, VideoDao videoDao) {
        this.movieAppApi = movieAppApi;
        this.videoDao = videoDao;
    }

    @Override
    public Single<MovieVideosResponse> videos(int movieId) {
        return movieAppApi.movieVideos(movieId)
                .doOnSuccess(result -> {
                    videoDao.insert(movieVideosReponseToVideoEntity.map(result));
                });
    }

    @Override
    public LiveData<List<VideoModel>> videosLiveData(int movieId) {
        return Transformations.map(videoDao.getVideosForMovie(movieId), input -> {
            return videoEntityToVideoModelMapper.map2(input);
        });
    }
}
