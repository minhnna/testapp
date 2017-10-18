package com.base.testapp.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.testapp.R;
import com.base.testapp.base.app.Constant;
import com.base.testapp.base.app.fragmenthelper.FragmentTransactionHelper;
import com.base.testapp.base.app.network.ApiServices;
import com.base.testapp.base.app.network.ResponseCallBack;
import com.base.testapp.base.app.utilities.CommonUtil;
import com.base.testapp.base.app.utilities.ShareUtils;
import com.base.testapp.base.app.utilities.Utils;
import com.base.testapp.testapp.adapter.VideoAdapter;
import com.base.testapp.testapp.customvideoplayer.VideoPlayer;
import com.base.testapp.testapp.customview.CustomSwipeRefreshLayout;
import com.base.testapp.testapp.customview.CustomSwipeRefreshLayoutDirection;
import com.base.testapp.testapp.customview.SpacesItemDecoration;
import com.base.testapp.testapp.entity.GetVideoListResponse;
import com.base.testapp.testapp.entity.Video;
import com.base.testapp.testapp.fragment.VideoPlayerFragment;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements VideoAdapter.OnClickVideoItemListener, CustomSwipeRefreshLayout.OnRefreshListener, YouTubePlayer.OnInitializedListener, View.OnClickListener {
    private static final String VIDEO_FRAGMENT = "VIDEO_FRAGMENT";
    private static final String YOUTUBE_FRAGMENT = "YOUTUBE_FRAGMENT";

    private List<Video> mListVideo = new ArrayList<>();
    private ImageView mBtnShare;
    private TextView mVideoTitle;
    private RecyclerView rvListVideo;
    private VideoAdapter mVideoAdapter;
    private int mMaxVideoNumber;
    private CustomSwipeRefreshLayout mSRL;
    private String mOffset;
    private YouTubePlayer mYouTubePlayer;
    private YouTubePlayerSupportFragment mYouTubePlayerFragment;
    public FragmentTransactionHelper transactionHelper;
    private String curFragment;
    private String mYoutubeVideoId;
    private Video mCurVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private int getPlaceHolder() {
        return R.id.container;
    }

    private void initView() {
        mBtnShare = (ImageView) findViewById(R.id.btn_share);
        mVideoTitle = (TextView) findViewById(R.id.tv_video_title);
        rvListVideo = (RecyclerView) findViewById(R.id.rvListVideo);
        mSRL = (CustomSwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        setupView();
    }

    private void setupView() {
        //add default VideoPlayerFragment
        transactionHelper = new FragmentTransactionHelper(this, getPlaceHolder());
        transactionHelper.doAddFragment(VideoPlayerFragment.getInstance(), false);
        curFragment = VIDEO_FRAGMENT;

        mBtnShare.setOnClickListener(this);
        //set up recycler listview
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvListVideo.setLayoutManager(mLayoutManager);
        rvListVideo.setItemAnimator(new DefaultItemAnimator());

        SpacesItemDecoration sid = new SpacesItemDecoration(1, (int) (CommonUtil.widthScreen(MainActivity.this) * Constant.SPACE_IN_PERCENT));
        rvListVideo.addItemDecoration(sid);
        mVideoAdapter = new VideoAdapter(getApplicationContext(), mListVideo, this);
        rvListVideo.setAdapter(mVideoAdapter);

        //load more when scroll end of video list
        mSRL.setDirection(CustomSwipeRefreshLayoutDirection.BOTTOM);
        mSRL.setOnRefreshListener(this);
    }

    private void initData() {
        mOffset = Constant.INIT_OFFSET;
        loadVideoList(mOffset);
    }

    private void loadVideoList(String offset) {
        if (offset.equals(Constant.INIT_OFFSET) || Integer.valueOf(offset) < mMaxVideoNumber) {
            ApiServices.getApiClient().getVideoList(offset).enqueue(new ResponseCallBack<GetVideoListResponse>() {

                @Override
                public void onResponseSuccess(GetVideoListResponse rs) {
                    updateVideoList(rs);
                }

            });
        } else {
            cancelGetVideoLoadingProgressbar();
        }
    }

    private void updateVideoList(GetVideoListResponse rs) {
        mListVideo.addAll(rs.getListVideo());
        mMaxVideoNumber = rs.getMaxVideoNumber();
        mOffset = String.valueOf(mListVideo.size());
        mVideoAdapter.notifyDataSetChanged();
        cancelGetVideoLoadingProgressbar();
    }

    private void cancelGetVideoLoadingProgressbar() {
        mSRL.setRefreshing(false);
    }

    private void playVideo(Video video) {
        if (mCurVideo == null || video.getId() != mCurVideo.getId()) {
            mCurVideo = video;
            mVideoTitle.setText(video.getName());
            if (video.getLink().contains(Constant.YOUTUBE_LINK)) {
                mYoutubeVideoId = Utils.getYoutubeVideoId(video.getLink());
                if (curFragment.equals(VIDEO_FRAGMENT)) {
                    //add fragment to view then initialize youtube player, after init successful play video
                    initYouTubePlayerSupportFragment();
                } else {
                    mYouTubePlayer.cueVideo(mYoutubeVideoId);
                }
            } else {
                if (curFragment.equals(YOUTUBE_FRAGMENT)) {
                    transactionHelper.goBack();
                    curFragment = VIDEO_FRAGMENT;
                }
                VideoPlayerFragment.getInstance().playVideo(video);
            }
        }
    }

    private void initYouTubePlayerSupportFragment() {
        mYouTubePlayerFragment = new YouTubePlayerSupportFragment();
        transactionHelper.doAddFragment(mYouTubePlayerFragment);
        curFragment = YOUTUBE_FRAGMENT;
        mYouTubePlayerFragment.initialize(Constant.API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        this.mYouTubePlayer = youTubePlayer;
        playYoutubeVideo();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    private void playYoutubeVideo() {
        mYouTubePlayer.cueVideo(mYoutubeVideoId);
    }

    private void shareCurrentVideoLink() {
        ShareUtils.initShareFacebook(getBaseContext(),mCurVideo.getLink());
//        ShareUtils.initShareZaloApp(getBaseContext(),mCurVideo.getLink());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share: {
                if (mCurVideo != null)
                    shareCurrentVideoLink();
                break;
            }
        }
    }

    @Override
    public void onItemVideoClick(Video video) {
        playVideo(video);
    }

    @Override
    public void onRefresh(CustomSwipeRefreshLayoutDirection direction) {
        loadVideoList(mOffset);
    }


    @Override
    protected void onPause() {
        super.onPause();
        VideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        transactionHelper.terminate();
    }

    @Override
    public void onBackPressed() {
        if (VideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
