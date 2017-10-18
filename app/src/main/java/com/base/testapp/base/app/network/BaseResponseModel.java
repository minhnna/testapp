package com.base.testapp.base.app.network;

import com.google.gson.annotations.SerializedName;

public class BaseResponseModel {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
