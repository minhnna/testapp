package com.base.testapp.testapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.testapp.R;
import com.base.testapp.base.app.utilities.ImageLoaderUtil;
import com.base.testapp.testapp.entity.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 10/13/2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    private final OnClickVideoItemListener mOnClickVideoItemListener;
    private List<Video> mListVideo = new ArrayList<Video>();
    private Context mContext;

    public VideoAdapter(Context context, List<Video> mVideoList, OnClickVideoItemListener onClickVideoItemListener) {
        this.mListVideo = mVideoList;
        this.mContext = mContext;
        this.mOnClickVideoItemListener = onClickVideoItemListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(mListVideo.get(position),mOnClickVideoItemListener);
    }

    @Override
    public int getItemCount() {
        return mListVideo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivThumbnail;
        public TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = (ImageView) itemView.findViewById(R.id.iv_thumbnail);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
        public void bind(final Video video, final OnClickVideoItemListener listener) {
            if (video != null) {
                if (video.getName() != null) {
                    tvName.setText(video.getName());
                }
                ImageLoaderUtil.displayImage(video.getImage(), ivThumbnail);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemVideoClick(video);
                    }
                });
            }
        }

    }

    public interface OnClickVideoItemListener {
        void onItemVideoClick(Video video);
    }
}
