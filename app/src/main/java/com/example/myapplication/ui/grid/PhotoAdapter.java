package com.example.myapplication.ui.grid;

import android.annotation.SuppressLint;

import com.example.myapplication.pojo.Photo;
import com.example.myapplication.pojo.Thumb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class PhotoAdapter implements GridItem {

    @SuppressLint("SimpleDateFormat")
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");

    private final Photo photo;

    public PhotoAdapter(Photo photo) {
        this.photo = photo;
    }

    @Override
    public Long getId() {
        return photo.getId();
    }

    @Override
    public String getTitle() {
        return photo.getText() + " " + dateFormat.format(new Date(photo.getDate()));
    }

    @Override
    public List<Thumb> getThumbs() {
        return photo.getSizes();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoAdapter that = (PhotoAdapter) o;
        return Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photo);
    }
}
