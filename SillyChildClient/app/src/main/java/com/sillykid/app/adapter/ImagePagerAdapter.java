package com.sillykid.app.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.sillykid.app.R;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class ImagePagerAdapter extends PagerAdapter {
    private Context context;
    private List<String> urls;

    public ImagePagerAdapter(Context context, List<String> urls){
        this.context=context;
        this.urls=urls;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(context);
        photoView.enable();
        photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        GlideImageLoader.glideOrdinaryLoader(context, urls.get(position), photoView, R.mipmap.placeholderfigure1);
        container.addView(photoView);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
