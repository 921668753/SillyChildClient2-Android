package com.sillykid.app.adapter.mine.myfans;

import android.content.Context;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.mycollection.MyCollectionBean.DataBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 我的粉丝 适配器
 * Created by Admin on 2018/8/15.
 */
public class MyFansViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public MyFansViewAdapter(Context context) {
        super(context, R.layout.item_myfans);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        helper.setItemChildClickListener(R.id.tv_follow);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean listBean) {

        /**
         *头像图片
         */
        GlideImageLoader.glideLoader(mContext, listBean.getThumbnail(), viewHolderHelper.getImageView(R.id.img_head), 0, R.mipmap.placeholderfigure1);

        /**
         *昵称
         */
        viewHolderHelper.setText(R.id.tv_nickName, listBean.getName());

        /**
         *篇日记
         */
        viewHolderHelper.setText(R.id.tv_diary, listBean.getBrief());

        /**
         *个粉丝
         */
        viewHolderHelper.setText(R.id.tv_fan, MathUtil.keepTwo(StringUtils.toDouble(listBean.getPrice())));

    }

}