package com.example.myapplication.requests;

import com.example.myapplication.pojo.Response;
import com.example.myapplication.pojo.User;
import com.google.gson.reflect.TypeToken;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.requests.VKRequest;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.List;

public class UserRequest extends ParsedRequest<Response<List<User>>> {

    public UserRequest() {
        super("users.get");
        addParam("user_ids", VK.getUserId());

    }

    @Override
    Type getRequestType() {
        return new TypeToken<Response<List<User>>>(){}.getType();
    }
}
