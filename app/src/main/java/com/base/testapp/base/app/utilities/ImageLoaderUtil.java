package com.base.testapp.base.app.utilities;

import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.ImageView;

import com.base.testapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;


public class ImageLoaderUtil {

    public static void displayImage(String urlImage, ImageView imageView) {
        displayImage(urlImage, imageView, R.drawable.ic_loading_image);
    }

    public static void displayImage(final String urlImage, final ImageView imageView, @DrawableRes int place_holder) {
        if (imageView != null) {
            if (null == urlImage || TextUtils.isEmpty(urlImage)) {
                imageView.setImageResource(place_holder);
            } else {
                Glide.with(imageView.getContext())
                        .load(urlImage)
                        .placeholder(place_holder)
                        .into(imageView);
            }
        }
    }

    public static void displayImage(int imgRes, ImageView imageView) {
        if (imageView == null) return;
        Glide.with(imageView.getContext())
                .load(imgRes)
                .into(imageView);
    }

    public static void displayGif(int gifRes, ImageView imageView) {
        if (imageView == null) return;
        Glide.with(imageView.getContext())
                .load(gifRes)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }
}
