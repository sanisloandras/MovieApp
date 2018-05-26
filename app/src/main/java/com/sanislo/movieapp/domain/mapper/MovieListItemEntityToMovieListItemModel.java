package com.sanislo.movieapp.domain.mapper;

import com.sanislo.movieapp.domain.model.MovieListItemModel;
import com.sanislo.movieapp.persistence.entity.MovieListItemEntity;

public class MovieListItemEntityToMovieListItemModel
        extends Mapper<MovieListItemEntity, MovieListItemModel> {
    @Override
    public MovieListItemEntity map1(MovieListItemModel data) {
        return null;
    }

    @Override
    public MovieListItemModel map2(MovieListItemEntity data) {
        return new MovieListItemModel(
                data.get_id(),
                data.getOverview(),
                data.getOriginalLanguage(),
                data.getOriginalTitle(),
                data.isVideo(),
                data.getTitle(),
                data.getPosterPath(),
                data.getBackdropPath(),
                data.getReleaseDate(),
                data.getPopularity(),
                data.getVoteAverage(),
                data.getId(),
                data.isAdult(),
                data.getVoteCount()
        );
    }
}
