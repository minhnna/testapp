package com.base.testapp.base.app.network;

import android.support.annotation.NonNull;

import com.base.testapp.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServices {

    private static Retrofit retrofit;

    public static ApiClient getApiClient() {
        return getRetrofit(ApiClient.class);
    }

    private static <T> T getRetrofit(Class<T> ApiClass) {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLogging interceptor = new HttpLogging();
            interceptor.setLevel(BuildConfig.DEBUG ? HttpLogging.Level.BODY : HttpLogging.Level.NONE);
            httpClient.addNetworkInterceptor(interceptor);
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                }
            });
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_DOMAIN)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiClass);
    }
}
