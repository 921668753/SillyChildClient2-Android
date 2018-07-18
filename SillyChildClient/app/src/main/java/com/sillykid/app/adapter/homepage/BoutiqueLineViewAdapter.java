package com.sillykid.app.adapter.homepage;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.sillykid.app.R;
import com.sillykid.app.entity.HomePageBean.ResultBean.ActionBean.HotBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页 精品线路 适配器
 * Created by Admin on 2017/8/15.
 */

public class BoutiqueLineViewAdapter extends BGAAdapterViewAdapter<HotBean> {

    public BoutiqueLineViewAdapter(Context context) {
        super(context, R.layout.item_boutiqueline);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, HotBean listBean) {
        Log.d("position", position + "");

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getCover_img(), (ImageView) viewHolderHelper.getView(R.id.img_boutiqueLine), R.mipmap.placeholderfigure);


        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_boutiqueLine, listBean.getCity());

        /**
         * 收藏数
         */
        viewHolderHelper.setText(R.id.tv_collectionNum, listBean.getPraiseNum());

        /**
         * 赞数
         */
        viewHolderHelper.setText(R.id.tv_zanNum, listBean.getPraiseNum());

    }

}