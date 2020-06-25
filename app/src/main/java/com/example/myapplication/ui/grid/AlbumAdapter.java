package com.example.myapplication.ui.grid;

import com.example.myapplication.pojo.Album;
import com.example.myapplication.pojo.Thumb;

import java.util.List;
import java.util.Objects;

public class AlbumAdapter implements GridItem {
    private final Album album;

    public AlbumAdapter(Album album) {
        this.album = album;
    }

    @Override
    public Long getId() {
        return album.getId();
    }

    @Override
    public String getTitle() {
        return album.getTitle();
    }

    @Override
    public List<Thumb> getThumbs() {
        return album.getSizes();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumAdapter that = (AlbumAdapter) o;
        return Objects.equals(album, that.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(album);
    }
}
