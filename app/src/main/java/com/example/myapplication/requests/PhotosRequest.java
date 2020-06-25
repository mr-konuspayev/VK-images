package com.example.myapplication.requests;

import com.example.myapplication.pojo.Items;
import com.example.myapplication.pojo.Photo;
import com.example.myapplication.pojo.Response;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class PhotosRequest extends ParsedRequest<Response<Items<Photo>>> {
    public PhotosRequest(Long albumId) {
        super("photos.get");
        addParam("album_id", albumId);
    }

    @Override
    Type getRequestType() {
        return new TypeToken<Response<Items<Photo>>>(){}.getType();
    }
}