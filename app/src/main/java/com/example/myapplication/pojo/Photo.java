package com.example.myapplication.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Photo {
    private Long id;
    @SerializedName("album_id")
    private Long albumId;
    @SerializedName("owner_id")
    private Long ownerId;
    private Long date;
    private String text;
    private List<Thumb> sizes;

    public Long getId() {
        return id;
    }

    public Long getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public List<Thumb> getSizes() {
        return sizes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id) &&
                Objects.equals(albumId, photo.albumId) &&
                Objects.equals(ownerId, photo.ownerId) &&
                Objects.equals(date, photo.date) &&
                Objects.equals(text, photo.text) &&
                Objects.equals(sizes, photo.sizes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumId, ownerId, date, text, sizes);
    }
}
