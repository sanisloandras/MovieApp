package com.sanislo.movieapp.domain.video;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import android.util.Log;

import com.sanislo.movieapp.domain.mapper.MovieVideosReponseToVideoEntity;
import com.sanislo.movieapp.domain.mapper.VideoEntityToVideoModelMapper;
import com.sanislo.movieapp.domain.model.VideoModel;
import com.sanislo.movieapp.domain.model.YoutubeVideoModel;
import com.sanislo.movieapp.persistence.api.MovieAppApi;
import com.sanislo.movieapp.persistence.dao.VideoDao;
import com.sanislo.movieapp.persistence.entity.VideoEntity;
import com.sanislo.movieapp.persistence.response.movieVideos.MovieVideosResponse;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class VideoRepositoryImpl implements VideoRepository {
    public static final String TAG = VideoRepository.class.getSimpleName();

    private static final String SITE_YOUTUBE = "YouTube";
    private static final String youtubeThumbnailFormat = "https://img.youtube.com/vi/%s/0.jpg";

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

    @Override
    public LiveData<List<YoutubeVideoModel>> youtubeVideosLiveData(int movieId) {
        //return LiveDataReactiveStreams.fromPublisher(getYoutubeVideoFlowable(movieId));
        return Transformations.map(videoDao.getVideosForMovieBySiteLiveData(movieId, SITE_YOUTUBE), new android.arch.core.util.Function<List<VideoEntity>, List<YoutubeVideoModel>>() {
            @Override
            public List<YoutubeVideoModel> apply(List<VideoEntity> input) {
                List<YoutubeVideoModel> youtubeVideoModels = new ArrayList<>();
                for (VideoEntity videoEntity : input) {
                    youtubeVideoModels.add(mapVideoEntityToYoutubeVideoModel(videoEntity));
                }
                return youtubeVideoModels;
            }
        });
    }

    private Flowable<List<YoutubeVideoModel>> getYoutubeVideoFlowable(int movieId) {
        Flowable<List<YoutubeVideoModel>> youtubeVideoModelFlowable = videoDao.getVideosForMovieFlowable(movieId)
                .flatMap((Function<List<VideoEntity>, Publisher<VideoEntity>>) videoEntities -> Flowable.fromIterable(videoEntities))
                //.doOnNext(videoEntity -> Log.d(TAG, "getYoutubeVideoFlowable: " + videoEntity))
                .filter(videoEntity -> videoEntity.getSite().equals(SITE_YOUTUBE))
                .map(videoEntity -> {
                    return mapVideoEntityToYoutubeVideoModel(videoEntity);
                })
                //.doOnNext(youtubeVideoModel -> Log.d(TAG, "getYoutubeVideoFlowable: " + youtubeVideoModel))
                .toList()
                .doOnSuccess(youtubeVideoModels -> Log.d(TAG, "getYoutubeVideoFlowable: " + youtubeVideoModels))
                .toFlowable();
        return youtubeVideoModelFlowable;
    }

    @NonNull
    private YoutubeVideoModel mapVideoEntityToYoutubeVideoModel(VideoEntity videoEntity) {
        return new YoutubeVideoModel(
                videoEntity.getSite(),
                videoEntity.getSize(),
                videoEntity.getIso31661(),
                videoEntity.getName(),
                videoEntity.getId(),
                videoEntity.getMovieId(),
                videoEntity.getType(),
                videoEntity.getIso6391(),
                videoEntity.getKey(),
                String.format(youtubeThumbnailFormat, videoEntity.getKey())
        );
    }
}
