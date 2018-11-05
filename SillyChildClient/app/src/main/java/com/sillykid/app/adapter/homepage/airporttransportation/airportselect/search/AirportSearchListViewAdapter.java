package com.sillykid.app.adapter.homepage.airporttransportation.airportselect.search;

import android.content.Context;
import android.view.View;

import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.airporttransportation.airportselect.search.AirportSearchListBean.DataBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;


public class AirportSearchListViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public AirportSearchListViewAdapter(Context context) {
        super(context, R.layout.item_cityclassification);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {


        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getAirport_picture(), helper.getImageView(R.id.img_classification), R.mipmap.placeholderfigure);


        /**
         * 城市名字
         */
        helper.setText(R.id.tv_classificationName, model.getCity_name());

        helper.setVisibility(R.id.tv_airportName, View.VISIBLE);

        helper.setText(R.id.tv_airportName, model.getAirport_name());

    }
}
