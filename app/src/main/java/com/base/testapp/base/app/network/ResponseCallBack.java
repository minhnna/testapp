package com.base.testapp.base.app.network;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

import com.base.testapp.base.app.utilities.LogUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ResponseCallBack<T> extends OnResponse<T> implements Callback<T> {

    public ResponseCallBack() {
        onStartRequest();
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        onFinishRequest();
        if (response.isSuccessful()) {
            T responseClass = response.body();
            if (responseClass instanceof BaseResponseModel) {
                String message = ((BaseResponseModel) responseClass).getMessage();
//                if (result.equals(ResponseCode.SUCCESS) || result.equals(ResponseCode.NG)) {
                    onResponseSuccess(responseClass);
//                } else {
//                    onResponseFailure(result, message);
//                }
                LogUtils.e("OkHttp Response Message: " + message);
            }
        } else {
            ResponseBody error = response.errorBody();
            String errorStr = "unknown";
            if (error != null) {
                try {
                    errorStr = error.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            onResponseFailure(ResponseCode.UNKNOW_ERROR, errorStr);
            LogUtils.e("OkHttp Response Error 1: " + errorStr);
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        onFinishRequest();
        onResponseFailure(ResponseCode.UNKNOW_ERROR, t.getMessage());
        LogUtils.e("OkHttp Response Error 2: " + t.getMessage());
    }
}
