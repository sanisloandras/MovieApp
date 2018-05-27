package com.sanislo.movieapp.domain.mapper;

import com.sanislo.movieapp.persistence.entity.GenreEntity;
import com.sanislo.movieapp.persistence.response.movieDetails.GenresItem;
import com.sanislo.movieapp.persistence.response.movieDetails.MovieResponse;

import java.util.ArrayList;
import java.util.List;

public class MovieResponseToGenreEntityMapper extends SimpleMapper<MovieResponse, List<GenreEntity>>{
    @Override
    public List<GenreEntity> map(MovieResponse input) {
        List<GenreEntity> genreEntities = new ArrayList<>();
        for (GenresItem genresItem : input.getGenres()) {
            genreEntities.add(new GenreEntity(
                genresItem.getName(),
                genresItem.getId()
            ));
        }
        return genreEntities;
    }
}
