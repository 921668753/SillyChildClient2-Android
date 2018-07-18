package com.sillykid.app.adapter.trip;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sillykid.app.R;
import com.sillykid.app.entity.TripBean.ResultBean.GuideListBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 出行---热门攻略 适配器
 * Created by Admin on 2017/8/15.
 */

public class TripHotStrategyViewAdapter extends BGAAdapterViewAdapter<GuideListBean> {

    public TripHotStrategyViewAdapter(Context context) {
        super(context, R.layout.item_popularstrategy);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, GuideListBean listBean) {
        Log.d("position", position + "");
        LinearLayout ll_hotStrategy = (LinearLayout) viewHolderHelper.getView(R.id.ll_hotStrategy);
        ll_hotStrategy.getParent().requestDisallowInterceptTouchEvent(true);
        if (position == 0 && listBean.getStatusL() != null && listBean.getStatusL().equals("last")) {
            ll_hotStrategy.setPadding(30, 0, 30, 0);
        } else {
            if (position == 0) {
                ll_hotStrategy.setPadding(30, 0, 0, 0);
            } else if (listBean.getStatusL() != null && listBean.getStatusL().equals("last")) {
                ll_hotStrategy.setPadding(20, 0, 30, 0);
            } else {
                ll_hotStrategy.setPadding(20, 0, 0, 0);
            }
        }

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getCover_img(), (ImageView) viewHolderHelper.getView(R.id.img_hotStrategy), R.mipmap.placeholderfigure);

        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_title, listBean.getTitle());

        /**
         * 描述
         */
        viewHolderHelper.setText(R.id.tv_describe, listBean.getSummary());


        /**
         * 赞数
         */
        viewHolderHelper.setText(R.id.tv_greatNumber, String.valueOf(listBean.getPraiseNum()));
    }

}