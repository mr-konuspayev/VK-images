package com.example.myapplication.requests;

import com.google.gson.Gson;
import com.vk.api.sdk.requests.VKRequest;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.lang.reflect.Type;

public abstract class ParsedRequest<T> extends VKRequest<T> {
    public ParsedRequest(@NotNull String method) {
        super(method);
    }

    abstract Type getRequestType();

    @Override
    public T parse(@NotNull String response) {
        return new Gson().fromJson(response, getRequestType());
    }

    @Override
    public T parse(@NotNull JSONObject r) {
        return new Gson().fromJson(r.toString(), getRequestType());
    }
}
