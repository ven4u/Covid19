package com.android.covid19.database.tables;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "countries_table")
public class Countries implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String slug;

    public Countries(@NonNull String slug) {this.slug = slug;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug(){
        return this.slug;
    }

    public void setSlug(@NonNull String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "Countries{" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                '}';
    }
}