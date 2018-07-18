package com.sillykid.app.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.sillykid.app.R;
import com.sillykid.app.entity.AllDynamicsBean.ResultBean.ListBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 全部动态 适配器
 * Created by Admin on 2017/8/15.
 */

public class AllDynamicsViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public AllDynamicsViewAdapter(Context context) {
        super(context, R.layout.item_alldynamics);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getImg(), (ImageView) viewHolderHelper.getView(R.id.img_dynamics));

        /**
         * 姓名
         */
        viewHolderHelper.setText(R.id.tv_title, listBean.getTitle());

        /**
         * 姓名
         */
        viewHolderHelper.setText(R.id.tv_name, listBean.getOwner().getNickname());

        /**
         * 头像
         */
        GlideImageLoader.glideLoader(mContext, listBean.getOwner().getAvatar(), (ImageView) viewHolderHelper.getView(R.id.img_avatar), 0);

        /**
         * 赞数
         */
        viewHolderHelper.setText(R.id.tv_zanNumber, listBean.getPraiseNum());

    }

}