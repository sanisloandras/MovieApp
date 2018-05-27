package com.sanislo.movieapp.domain.mapper;

import com.sanislo.movieapp.persistence.entity.MovieJoin;
import com.sanislo.movieapp.persistence.response.movieDetails.GenresItem;
import com.sanislo.movieapp.persistence.response.movieDetails.MovieResponse;

import java.util.ArrayList;
import java.util.List;

public class MovieResponseToMovieJoinMapper extends SimpleMapper<MovieResponse, List<MovieJoin>> {

    @Override
    public List<MovieJoin> map(MovieResponse input) {
        List<MovieJoin> movieJoinList = new ArrayList<>();
        for (GenresItem genresItem : input.getGenres()) {
            movieJoinList.add(new MovieJoin(
                    input.getId(), genresItem.getId()
            ));
        }
        return movieJoinList;
    }
}
