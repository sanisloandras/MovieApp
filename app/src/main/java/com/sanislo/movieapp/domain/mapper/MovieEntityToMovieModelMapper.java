package com.sanislo.movieapp.domain.mapper;

import com.sanislo.movieapp.domain.model.MovieModel;
import com.sanislo.movieapp.persistence.entity.MovieEntity;

public class MovieEntityToMovieModelMapper extends SimpleMapper<MovieEntity, MovieModel> {


    @Override
    public MovieModel map(MovieEntity input) {
        if (input == null) return null;
        return new MovieModel(
                input.getOriginalLanguage(),
                input.getImdbId(),
                input.isVideo(),
                input.getTitle(),
                input.getBackdropPath(),
                input.getRevenue(),
                null,
                input.getPopularity(),
                null,
                input.getId(),
                input.getVoteCount(),
                input.getBudget(),
                input.getOverview(),
                input.getOriginalTitle(),
                input.getRuntime(),
                input.getPosterPath(),
                null,
                null,
                input.getReleaseDate(),
                input.getVoteAverage(),
                input.getBelongsToCollection(),
                input.getTagline(),
                input.isAdult(),
                input.getHomepage(),
                input.getStatus()
        );
    }
}
