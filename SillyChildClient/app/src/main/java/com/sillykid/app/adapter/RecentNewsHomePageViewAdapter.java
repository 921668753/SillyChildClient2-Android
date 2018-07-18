package com.sillykid.app.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sillykid.app.R;
import com.sillykid.app.entity.HomePageBean.ResultBean.ActionBean.NewBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页 最新动态 适配器
 * Created by Admin on 2017/8/15.
 */

public class RecentNewsHomePageViewAdapter extends BGAAdapterViewAdapter<NewBean> {

    public RecentNewsHomePageViewAdapter(Context context) {
        super(context, R.layout.item_recentnews);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, NewBean listBean) {
        Log.d("position", position + "");
        LinearLayout ll_recentNews = (LinearLayout) viewHolderHelper.getView(R.id.ll_recentNews);
        ll_recentNews.getParent().requestDisallowInterceptTouchEvent(true);
        if (position == 0 && listBean.getStatusL() != null && listBean.getStatusL().equals("last")) {
            ll_recentNews.setPadding(30, 0, 30, 0);
        } else {
            if (position == 0) {
                ll_recentNews.setPadding(30, 0, 0, 0);
            } else if (listBean.getStatusL() != null && listBean.getStatusL().equals("last")) {
                ll_recentNews.setPadding(20, 0, 30, 0);
            } else {
                ll_recentNews.setPadding(20, 0, 0, 0);
            }
        }

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getCover_img(), (ImageView) viewHolderHelper.getView(R.id.img_recentNews), R.mipmap.placeholderfigure);

        /**
         * 姓名
         */
        viewHolderHelper.setText(R.id.tv_name, listBean.getUser_name());

        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_title, listBean.getTitle());

        /**
         * 赞数
         */
        viewHolderHelper.setText(R.id.tv_zan, listBean.getPraiseNum() + mContext.getString(R.string.zan));

    }

}