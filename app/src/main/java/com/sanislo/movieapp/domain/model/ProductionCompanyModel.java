package com.sanislo.movieapp.domain.model;

import com.google.gson.annotations.SerializedName;

public class ProductionCompanyModel {
    private String logoPath;
    private String name;
    private int id;
    private String originCountry;

    public ProductionCompanyModel(String logoPath, String name, int id, String originCountry) {
        this.logoPath = logoPath;
        this.name = name;
        this.id = id;
        this.originCountry = originCountry;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @Override
    public String toString() {
        return "ProductionCompanyModel{" +
                "logoPath='" + logoPath + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", originCountry='" + originCountry + '\'' +
                '}';
    }
}
