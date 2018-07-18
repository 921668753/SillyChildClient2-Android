package com.sillykid.app.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.sillykid.app.R;
import com.sillykid.app.entity.PackLineBean.ResultBean.IndexBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 包车定制分类
 * Created by Admin on 2017/9/10.
 */

public class CharterCustomClassificationViewAdaper extends BGAAdapterViewAdapter<IndexBean> {

    public CharterCustomClassificationViewAdaper(Context context) {
        super(context, R.layout.item_chartercustom);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, IndexBean indexBean) {

        /**
         * 图片
         */
        ImageView img_classification = (ImageView) viewHolderHelper.getView(R.id.img_classification);
        if (indexBean.getName().equals(mContext.getString(R.string.quickReservation))) {
            img_classification.setImageResource(R.mipmap.quick);
        } else if (indexBean.getName().equals(mContext.getString(R.string.privateOrder))) {
            img_classification.setImageResource(R.mipmap.dingzhi);
        } else if (indexBean.getName().contains(mContext.getString(R.string.byTheDay))) {
            img_classification.setImageResource(R.mipmap.day);
        } else if (indexBean.getName().contains(mContext.getString(R.string.airportPickup))) {
            img_classification.setImageResource(R.mipmap.jieji);
        } else if (indexBean.getName().contains(mContext.getString(R.string.airportDropOff))) {
            img_classification.setImageResource(R.mipmap.songji);
        } else if (indexBean.getName().contains(mContext.getString(R.string.singleTransport))) {
            img_classification.setImageResource(R.mipmap.dancijiesong);
        }
//       GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getHead_pic(), (ImageView) viewHolderHelper.getView(R.id.img_classification));
        /**
         * 分类名称
         */
        viewHolderHelper.setText(R.id.tv_classificationName, indexBean.getName());
    }

}
