package com.base.testapp.base.app.network;

import com.base.testapp.testapp.entity.GetVideoListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {
    /**
     *
     * @param offset : get video data from id=offset
     */
    @GET("index.php")
    Call<GetVideoListResponse> getVideoList(@Query("offset") String offset);
}
