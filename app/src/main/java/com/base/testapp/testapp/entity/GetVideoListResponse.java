package com.base.testapp.testapp.entity;

import com.base.testapp.base.app.network.BaseResponseModel;
import com.base.testapp.testapp.entity.Video;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MinhNNA on 10/17/2017.
 */

public class GetVideoListResponse extends BaseResponseModel {

    @SerializedName("data")
    private List<Video> listVideo;

    @SerializedName("total")
    private int maxVideoNumber;

    public List<Video> getListVideo() {
        return listVideo;
    }

    public void setListVideo(List<Video> listVideo) {
        this.listVideo = listVideo;
    }

    public int getMaxVideoNumber() {
        return maxVideoNumber;
    }

    public void setMaxVideoNumber(int maxVideoNumber) {
        this.maxVideoNumber = maxVideoNumber;
    }
}
