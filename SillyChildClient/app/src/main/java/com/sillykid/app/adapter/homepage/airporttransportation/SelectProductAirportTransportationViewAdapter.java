package com.sillykid.app.adapter.homepage.airporttransportation;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.mall.moreclassification.ClassificationBean.DataBean;
import com.sillykid.app.utils.GlideImageLoader;


import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;


/**
 * 选择产品----接送机
 */
public class SelectProductAirportTransportationViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public SelectProductAirportTransportationViewAdapter(Context context) {
        super(context, R.layout.item_productairporttransportation);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getImage(), helper.getImageView(R.id.img_selectProductAirport), R.mipmap.placeholderfigure);


        /**
         * 名字
         */
        helper.setText(R.id.tv_name, model.getName());


        /**
         * 车辆名字
         */
        helper.setText(R.id.tv_vehicleName, model.getName());


        /**
         * 人数
         */
        helper.setText(R.id.tv_people, model.getName());


        /**
         * 预定次数
         */
        helper.setText(R.id.tv_bookingNum, model.getName());

    }
}
