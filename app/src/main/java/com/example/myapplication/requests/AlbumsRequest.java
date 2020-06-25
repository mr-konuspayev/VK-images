package com.example.myapplication.requests;

import com.example.myapplication.pojo.Album;
import com.example.myapplication.pojo.Items;
import com.example.myapplication.pojo.Response;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class AlbumsRequest extends ParsedRequest<Response<Items<Album>>> {
    public AlbumsRequest() {
        super("photos.getAlbums");
        addParam("offset", 0);
        addParam("need_system", 1);
        addParam("need_covers", 1);
        addParam("photo_sizes", 1);
    }

    @Override
    Type getRequestType() {
        return new TypeToken<Response<Items<Album>>>(){}.getType();
    }
}