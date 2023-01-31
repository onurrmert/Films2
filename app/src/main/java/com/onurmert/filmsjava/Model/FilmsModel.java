package com.onurmert.filmsjava.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "filmsTable")
public class FilmsModel {

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private final String title;

    @SerializedName("year")
    @ColumnInfo(name = "year")
    private final String year;

    @SerializedName("rating")
    @ColumnInfo(name = "rating")
    private final String rating;

    @SerializedName("summary")
    @ColumnInfo(name = "summary")
    private final String summary;

    @SerializedName("large_cover_image")
    @ColumnInfo(name = "large_cover_image")
    private final String large_cover_image;

    @SerializedName("url")
    @ColumnInfo(name = "url")
    private final String urlFilm;

    public FilmsModel(int id, String title, String year, String rating, String summary, String large_cover_image, String urlFilm){
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.summary = summary;
        this.large_cover_image = large_cover_image;
        this.urlFilm = urlFilm;
    }
    @Ignore
    public FilmsModel(String title, String year, String rating, String summary, String large_cover_image, String urlFilm){
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.summary = summary;
        this.large_cover_image = large_cover_image;
        this.urlFilm = urlFilm;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public String getSummary() {
        return summary;
    }

    public String getLarge_cover_image() {
        return large_cover_image;
    }

    public String getUrlFilm() {
        return urlFilm;
    }
}
