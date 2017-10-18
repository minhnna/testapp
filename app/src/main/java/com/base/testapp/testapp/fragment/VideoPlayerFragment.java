package com.base.testapp.testapp.fragment;

import android.view.View;

import com.base.testapp.R;
import com.base.testapp.base.app.BaseFragment;
import com.base.testapp.base.app.utilities.ImageLoaderUtil;
import com.base.testapp.testapp.customvideoplayer.CustomVideoPlayerStandard;
import com.base.testapp.testapp.entity.Video;

/**
 * Created by MinhNNA on 10/18/2017.
 */

public class VideoPlayerFragment extends BaseFragment {
    private CustomVideoPlayerStandard mCustomVideoPlayerStandard;

    private static VideoPlayerFragment instance;


    public synchronized static VideoPlayerFragment getInstance() {
        if (instance == null) {
            instance = new VideoPlayerFragment();
        }
        return instance;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_video_player;
    }

    @Override
    protected void initView(View view) {
        mCustomVideoPlayerStandard = (CustomVideoPlayerStandard) view.findViewById(R.id.video_view);
    }

    public void playVideo(Video video){
        mCustomVideoPlayerStandard.setUp(video.getLink(), mCustomVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, video.getName());
        ImageLoaderUtil.displayImage(video.getImage(),mCustomVideoPlayerStandard.thumbImageView);
    }
}
