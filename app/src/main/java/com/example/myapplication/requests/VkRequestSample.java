package com.example.myapplication.requests;

import com.vk.api.sdk.requests.VKRequest;

import org.jetbrains.annotations.NotNull;

public class VkRequestSample extends VKRequest {
    public VkRequestSample() {
        super("photos.getAlbums");
        addParam("offset", 0);
        addParam("need_system", 1);
        addParam("need_covers", 1);
        addParam("photo_sizes", 1);
    }
}
