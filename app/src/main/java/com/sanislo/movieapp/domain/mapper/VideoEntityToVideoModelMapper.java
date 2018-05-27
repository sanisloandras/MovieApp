package com.sanislo.movieapp.domain.mapper;

import com.sanislo.movieapp.domain.model.VideoModel;
import com.sanislo.movieapp.persistence.entity.VideoEntity;

public class VideoEntityToVideoModelMapper extends Mapper<VideoEntity, VideoModel> {

    @Override
    public VideoEntity map1(VideoModel data) {
        return null;
    }

    @Override
    public VideoModel map2(VideoEntity input) {
        if (input == null) return null;
        return new VideoModel(input.getSite(),
                input.getSize(),
                input.getIso31661(),
                input.getName(),
                input.getId(),
                input.getMovieId(),
                input.getType(),
                input.getIso6391(),
                input.getKey());
    }
}
