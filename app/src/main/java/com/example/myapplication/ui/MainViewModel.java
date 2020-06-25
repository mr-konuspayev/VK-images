package com.example.myapplication.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.pojo.Response;
import com.example.myapplication.pojo.User;
import com.example.myapplication.requests.UserRequest;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiCallback;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<String> userName = new MutableLiveData<>();

    public LiveData<String> userName() {
        return userName;
    }

    public void onLogin() {
        VK.execute(new UserRequest(), new VKApiCallback<Response<List<User>>>() {
            @Override
            public void success(Response<List<User>> listResponse) {
                User user = listResponse.getResponse().get(0);
                userName.setValue(user.getFirstName() + " " + user.getLastName());
            }

            @Override
            public void fail(@NotNull Exception e) {

            }
        });
    }
}
