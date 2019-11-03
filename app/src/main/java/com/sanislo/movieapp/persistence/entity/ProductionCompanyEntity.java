package com.sanislo.movieapp.persistence.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "production_company")
public class ProductionCompanyEntity {
    private String logoPath;
    private String name;
    @PrimaryKey
    private int id;
    private String originCountry;

    public ProductionCompanyEntity(String logoPath, String name, int id, String originCountry) {
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
        return "ProductionCompanyEntity{" +
                "logoPath='" + logoPath + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", originCountry='" + originCountry + '\'' +
                '}';
    }
}
