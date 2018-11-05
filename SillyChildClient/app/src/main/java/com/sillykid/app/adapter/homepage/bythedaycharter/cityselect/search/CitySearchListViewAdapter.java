package com.sillykid.app.adapter.homepage.bythedaycharter.cityselect.search;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.privatecustom.cityselect.search.CitySearchRListBean.DataBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;


public class CitySearchListViewAdapter extends BGAAdapterViewAdapter<DataBean> {


    public CitySearchListViewAdapter(Context context) {
        super(context, R.layout.item_citysearchlist);
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
        helper.setText(R.id.tv_classificationName, model.getCity_name());

    }
}
