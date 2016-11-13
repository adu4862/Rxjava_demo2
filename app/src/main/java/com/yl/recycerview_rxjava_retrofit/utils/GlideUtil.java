package com.yl.recycerview_rxjava_retrofit.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 图片加载工具
 * Created by hy on 2016/9/27.
 */
public class GlideUtil {

    /**
     * 这是图片显示的方法
     *
     * @param url
     * @param imageView
     */
    public static void setImageUrl(String url, ImageView imageView) {


        Glide.with(imageView.getContext()).load(url).into(imageView);

    }


    /**
     * 这是圆形图片显示的方法
     *
     * @param url
     * @param imageView
     */
    public static void setCircleImageUrl(String url, ImageView imageView) {


        Glide.with(imageView.getContext()).load(url).transform(new GlideRoundTransform(imageView.getContext(),2)).into(imageView);

    }


    /**
     * 这是圆形图片显示的方法
     *
     * @param url
     * @param imageView
     */
    public static void setCircleImageUrl(String url, ImageView imageView,int r) {


        Glide.with(imageView.getContext()).load(url).transform(new GlideRoundTransform(imageView.getContext(),r)).into(imageView);

    }
}
