package com.base.testapp.base.app.network;

import com.google.gson.annotations.SerializedName;

public class BaseRequestModel {

    @SerializedName("token")
    private String token;

    public void setToken(String token) {
        this.token = token;
    }
}
