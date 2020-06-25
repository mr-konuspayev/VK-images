package com.example.myapplication.pojo;

import java.util.List;

public class Items<T> {
    private int count;
    private List<T> items;

    public int getCount() {
        return count;
    }

    public List<T> getItems() {
        return items;
    }
}
