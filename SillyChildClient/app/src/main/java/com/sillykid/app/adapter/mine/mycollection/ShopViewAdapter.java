package com.sillykid.app.adapter.mine.mycollection;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.mine.mycollection.ShopBean.DataBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 我的收藏中的店铺 适配器
 * Created by Admin on 2018/8/15.
 */
public class ShopViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public ShopViewAdapter(Context context) {
        super(context, R.layout.item_shop);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        helper.setItemChildClickListener(R.id.tv_enterStore);
        helper.setItemChildClickListener(R.id.tv_cancelCollection);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean listBean) {

        /**
         *店铺图片
         */
        GlideImageLoader.glideLoaderRaudio(mContext, listBean.getStore_logo(), viewHolderHelper.getImageView(R.id.img_shop), 5, R.mipmap.placeholderfigure1);

        /**
         *店铺名字
         */
        viewHolderHelper.setText(R.id.tv_shopName, listBean.getStore_name());

        /**
         *店铺收藏人数
         */
        viewHolderHelper.setText(R.id.tv_collectionNum, listBean.getCollect_number() + mContext.getString(R.string.personCollection));

        /**
         *店铺评分
         */
        viewHolderHelper.setText(R.id.tv_score, listBean.getScore_point() + mContext.getString(R.string.minute));

    }

}