package com.example.myapplication.pojo;

import java.util.List;
import java.util.Objects;

public class Album {
    private Long id;
    private String title;
    private int size;
    private List<Thumb> sizes;


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public List<Thumb> getSizes() {
        return sizes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Album album = (Album) o;
        return size == album.size &&
                Objects.equals(id, album.id) &&
                Objects.equals(title, album.title) &&
                Objects.equals(sizes, album.sizes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, size, sizes);
    }
}
