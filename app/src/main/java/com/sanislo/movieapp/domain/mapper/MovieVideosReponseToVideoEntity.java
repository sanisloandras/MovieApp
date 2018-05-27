package com.sanislo.movieapp.domain.mapper;

import com.sanislo.movieapp.persistence.entity.VideoEntity;
import com.sanislo.movieapp.persistence.response.movieVideos.MovieVideosResponse;
import com.sanislo.movieapp.persistence.response.movieVideos.VideoItem;

import java.util.ArrayList;
import java.util.List;

public class MovieVideosReponseToVideoEntity extends SimpleMapper<MovieVideosResponse, List<VideoEntity>> {
    @Override
    public List<VideoEntity> map(MovieVideosResponse input) {
        List<VideoEntity> videoEntities = new ArrayList<>();
        for (VideoItem videoItem : input.getResults()) {
            videoEntities.add(new VideoEntity(
                    videoItem.getSite(),
                    videoItem.getSize(),
                    videoItem.getIso31661(),
                    videoItem.getName(),
                    videoItem.getId(),
                    input.getId(),
                    videoItem.getType(),
                    videoItem.getIso6391(),
                    videoItem.getKey()
            ));
        }
        return videoEntities;
    }
}
