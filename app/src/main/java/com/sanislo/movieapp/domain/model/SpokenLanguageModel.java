package com.sanislo.movieapp.domain.model;

import com.google.gson.annotations.SerializedName;

public class SpokenLanguageModel {
    private String name;
    private String iso6391;

    public SpokenLanguageModel(String name, String iso6391) {
        this.name = name;
        this.iso6391 = iso6391;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    @Override
    public String toString() {
        return "SpokenLanguageModel{" +
                "name='" + name + '\'' +
                ", iso6391='" + iso6391 + '\'' +
                '}';
    }
}
