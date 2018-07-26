package com.sillykid.app.adapter.community.search;

import android.content.Context;
import android.view.View;

import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.community.search.SearchPeopleBean.DataBean.ListBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class SearchPeopleViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public SearchPeopleViewAdapter(Context context) {
        super(context, R.layout.item_myfans);
    }

//    @Override
//    protected void setItemChildListener(BGAViewHolderHelper helper) {
//        helper.setItemChildClickListener(R.id.tv_follow);
//    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         *头像图片
         */
        GlideImageLoader.glideLoader(mContext, listBean.getFace(), viewHolderHelper.getImageView(R.id.img_head), 0, R.mipmap.avatar);

        /**
         *昵称
         */
        viewHolderHelper.setText(R.id.tv_nickName, listBean.getNickname());

        /**
         *篇日记
         */
        if (StringUtils.isEmpty(listBean.getPost_number())) {
            viewHolderHelper.setText(R.id.tv_diary, "0");
        } else {
            viewHolderHelper.setText(R.id.tv_diary, listBean.getPost_number());
        }


        /**
         *个粉丝
         */
        if (StringUtils.isEmpty(listBean.getFans_number())) {
            viewHolderHelper.setText(R.id.tv_fan, "0");
        } else {
            viewHolderHelper.setText(R.id.tv_fan, listBean.getFans_number());
        }

        viewHolderHelper.setVisibility(R.id.tv_follow, View.GONE);

//        if (StringUtils.toInt(listBean.getIs_concern(), 0) == 1) {
//            viewHolderHelper.setText(R.id.tv_follow, mContext.getString(R.string.followed));
//            viewHolderHelper.setBackgroundRes(R.id.tv_follow, R.drawable.shape_followed);
//            viewHolderHelper.setTextColorRes(R.id.tv_follow, R.color.tabColors);
//        } else {
//            viewHolderHelper.setText(R.id.tv_follow, mContext.getString(R.string.follow));
//            viewHolderHelper.setBackgroundRes(R.id.tv_follow, R.drawable.shape_follow);
//            viewHolderHelper.setTextColorRes(R.id.tv_follow, R.color.greenColors);
//        }

    }
}