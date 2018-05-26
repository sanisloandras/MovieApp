package com.sanislo.movieapp.domain.mapper;

import com.sanislo.movieapp.persistence.entity.MovieListItemEntity;
import com.sanislo.movieapp.persistence.response.movieDetails.MovieResponse;
import com.sanislo.movieapp.persistence.response.movieList.MovieListItem;
import com.sanislo.movieapp.persistence.response.movieList.MovieListResponse;

public class MovieResponseMapper extends Mapper<MovieListItem, MovieListItemEntity>{
    @Override
    public MovieListItem map1(MovieListItemEntity data) {
        return null;
    }

    @Override
    public MovieListItemEntity map2(MovieListItem data) {
        return new MovieListItemEntity(
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
