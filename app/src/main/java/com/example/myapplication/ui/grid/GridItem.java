package com.example.myapplication.ui.grid;

import com.example.myapplication.pojo.Thumb;

import java.util.List;

public interface GridItem {
    Long getId();
    String getTitle();

    List<Thumb> getThumbs();

    boolean equals(Object object);
}
