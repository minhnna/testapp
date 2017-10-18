package com.base.testapp.base.app.network;

public abstract class OnResponse<T> {

    public void onStartRequest() {}

    public void onFinishRequest() {}

    public abstract void onResponseSuccess(T rs);

    public void onResponseFailure(String result, String message){}
}
