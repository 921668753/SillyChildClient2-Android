package com.sillykid.app.adapter.homepage.hotvideo;

import android.content.Context;
import android.util.Log;

import com.sillykid.app.R;
import com.sillykid.app.entity.main.HomePageBean.ResultBean.ActionBean.LocalBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页 热门视频 适配器
 * Created by Admin on 2017/8/15.
 */

public class HotVideoViewAdapter extends BGAAdapterViewAdapter<LocalBean> {


    public HotVideoViewAdapter(Context context) {
        super(context, R.layout.item_hotvideolist);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, LocalBean listBean) {
        Log.d("position", position + "");

        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_title, listBean.getName());

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getCover_img(), viewHolderHelper.getImageView(R.id.img_hotVideo), R.mipmap.placeholderfigure);

        /**
         * 关注人数
         */
        viewHolderHelper.setText(R.id.tv_attention, listBean.getName());

        /**
         * 收藏
         */
        viewHolderHelper.setText(R.id.tv_collection, listBean.getName());

        /**
         * 观看
         */
        viewHolderHelper.setText(R.id.tv_watch, listBean.getName());

    }

}