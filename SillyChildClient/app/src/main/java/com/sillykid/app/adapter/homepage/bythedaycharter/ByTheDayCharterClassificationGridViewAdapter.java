package com.sillykid.app.adapter.homepage.bythedaycharter;

import android.content.Context;
import android.view.View;

import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.bythedaycharter.RegionByCountryIdBean.DataBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 按天包车分类---GridView
 */
public class ByTheDayCharterClassificationGridViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public ByTheDayCharterClassificationGridViewAdapter(Context context) {
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
        helper.setText(R.id.tv_classificationName, model.getRegion_name());

        /**
         * 机场名字
         */
        helper.setVisibility(R.id.tv_airportName, View.GONE);
        // helper.setText(R.id.tv_airportName, "(" + model.getAirport_name() + ")");
    }
}
