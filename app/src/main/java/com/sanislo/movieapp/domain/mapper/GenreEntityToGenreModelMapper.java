package com.sanislo.movieapp.domain.mapper;

import com.sanislo.movieapp.domain.model.GenreModel;
import com.sanislo.movieapp.persistence.entity.GenreEntity;

public class GenreEntityToGenreModelMapper extends Mapper<GenreEntity, GenreModel> {
    @Override
    public GenreEntity map1(GenreModel data) {
        return null;
    }

    @Override
    public GenreModel map2(GenreEntity data) {
        return new GenreModel(data.getName(), data.getId());
    }
}
