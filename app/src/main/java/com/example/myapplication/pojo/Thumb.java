package com.example.myapplication.pojo;

import com.google.gson.annotations.SerializedName;

public class Thumb {
    @SerializedName(value = "src", alternate = "url")
    private String src;
    private int width;
    private int height;
    private char type;

    public String getSrc() {
        return src;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
