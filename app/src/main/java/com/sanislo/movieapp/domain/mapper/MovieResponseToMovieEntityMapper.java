package com.sanislo.movieapp.domain.mapper;

import com.sanislo.movieapp.persistence.entity.MovieEntity;
import com.sanislo.movieapp.persistence.response.movieDetails.MovieResponse;

public class MovieResponseToMovieEntityMapper extends Mapper<MovieResponse, MovieEntity> {
    @Override
    public MovieResponse map1(MovieEntity data) {
        return null;
    }

    @Override
    public MovieEntity map2(MovieResponse data) {
        return new MovieEntity(
                data.getOriginalLanguage(),
                data.getImdbId(),
                data.isVideo(),
                data.getTitle(),
                data.getBackdropPath(),
                data.getRevenue(),
                data.getPopularity(),
                data.getId(),
                data.getVoteCount(),
                data.getBudget(),
                data.getOverview(),
                data.getOriginalTitle(),
                data.getRuntime(),
                data.getPosterPath(),
                data.getReleaseDate(),
                data.getVoteAverage(),
                data.getBelongsToCollection(),
                data.getTagline(),
                data.isAdult(),
                data.getHomepage(),
                data.getStatus()
        );
    }
}
