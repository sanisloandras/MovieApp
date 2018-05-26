package com.sanislo.movieapp.persistence.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "production_country")
public class ProductionCountryEntity {
    @PrimaryKey
    private String iso31661;
    private String name;

    public ProductionCountryEntity(String iso31661, String name) {
        this.iso31661 = iso31661;
        this.name = name;
    }

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductionCountryEntity{" +
                "iso31661='" + iso31661 + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
