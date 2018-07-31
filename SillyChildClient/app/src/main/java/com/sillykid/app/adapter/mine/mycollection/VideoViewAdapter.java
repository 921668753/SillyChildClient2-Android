package com.sillykid.app.adapter.mine.mycollection;

import android.content.Context;
import android.util.Log;

import com.sillykid.app.R;
import com.sillykid.app.entity.mine.mycollection.VideoBean.DataBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class VideoViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public VideoViewAdapter(Context context) {
        super(context, R.layout.item_hotvideolist);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean listBean) {
        Log.d("position", position + "");

        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_title, listBean.getVideo_title());

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getVideo_image(), viewHolderHelper.getImageView(R.id.img_hotVideo), R.mipmap.placeholderfigure);

        /**
         * 关注人数
         */
        viewHolderHelper.setText(R.id.tv_attention, listBean.getConcern_number() + mContext.getString(R.string.people));

        /**
         * 收藏
         */
        viewHolderHelper.setText(R.id.tv_collection, listBean.getCollect_number() + mContext.getString(R.string.people));

        /**
         * 观看
         */
        viewHolderHelper.setText(R.id.tv_watch, listBean.getWatch_number() + mContext.getString(R.string.peopleWatch));

    }

}