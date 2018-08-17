package com.sillykid.app.adapter.homepage.airporttransportation;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.mall.moreclassification.ClassificationBean.DataBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 接送机分类---GridView
 */
public class AirportTransportationClassificationGridViewAdapter extends BGAAdapterViewAdapter<DataBean> {


    public AirportTransportationClassificationGridViewAdapter(Context context) {
        super(context, R.layout.item_countriesclassification);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {


        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getImage(), helper.getImageView(R.id.img_classification), R.mipmap.placeholderfigure);


        /**
         * 分类名字
         */
        helper.setText(R.id.tv_classificationName, model.getName());


    }
}
