package com.sillykid.app.adapter.addressselection.newoverseas;

import android.content.Context;
import android.widget.ImageView;

import com.sillykid.app.R;
import com.sillykid.app.entity.AllChildCityBean.ResultBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 海外 国家分类---城市  适配器
 * Created by Admin on 2017/9/8.
 */

public class OverseasAllCityViewAdapter extends BGAAdapterViewAdapter<ResultBean> {


    public OverseasAllCityViewAdapter(Context context) {
        super(context, R.layout.item_city);
    }


    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ResultBean model) {

        /**
         * 城市图片
         */
        GlideImageLoader.glideLoaderRaudio(mContext, model.getArea_code(), (ImageView) helper.getView(R.id.img_city), 10, R.mipmap.placeholderfigure);

        /**
         * 城市名称
         */
        helper.setText(R.id.tv_city, model.getName());
    }
}
