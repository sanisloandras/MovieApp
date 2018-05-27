package com.sanislo.movieapp.domain.mapper;

public abstract class SimpleMapper<FROM, TO> {
    public abstract TO map(FROM input);
}
