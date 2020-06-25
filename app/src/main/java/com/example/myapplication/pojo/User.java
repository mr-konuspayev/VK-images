package com.example.myapplication.pojo;

import com.google.gson.annotations.SerializedName;

public class User {
    private Long id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
