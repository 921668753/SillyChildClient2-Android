package com.sillykid.app.adapter.homepage.privatecustom.cityselect;

import android.content.Context;
import android.view.View;

import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.airporttransportation.airportselect.AirportByCountryIdBean.DataBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 城市选择分类---GridView
 */
public class CitySelectClassificationGridViewAdapter extends BGAAdapterViewAdapter<DataBean> {


    public CitySelectClassificationGridViewAdapter(Context context) {
        super(context, R.layout.item_countriesclassification);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {


        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getPicture(), helper.getImageView(R.id.img_classification), R.mipmap.placeholderfigure);


        /**
         * 城市名字
         */
        helper.setText(R.id.tv_classificationName, model.getCountry_name());

        /**
         * 机场名字
         */
        helper.setVisibility(R.id.tv_airportName, View.VISIBLE);
        helper.setText(R.id.tv_airportName, "(" + model.getName() + ")");
    }
}
