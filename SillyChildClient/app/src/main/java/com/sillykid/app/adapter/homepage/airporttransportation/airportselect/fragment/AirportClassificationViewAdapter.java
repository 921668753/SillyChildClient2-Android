package com.sillykid.app.adapter.homepage.airporttransportation.airportselect.fragment;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.airporttransportation.AirportByCountryIdBean.DataBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;


/**
 * 机场分类
 */
public class AirportClassificationViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public AirportClassificationViewAdapter(Context context) {
        super(context, R.layout.item_airport);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {

        /**
         * 背景色
         */
        if (model.getIsSelected() == 0) {
            helper.setBackgroundRes(R.id.tv_airport, R.color.whiteColors);
            helper.setTextColorRes(R.id.tv_airport, R.color.tabColors);
            //设置不为加粗
            helper.setBold(R.id.tv_airport, false);
        } else {
            helper.setBackgroundRes(R.id.tv_airport, R.color.background);
            helper.setTextColorRes(R.id.tv_airport, R.color.greenColors);
            //设置为加粗
            helper.setBold(R.id.tv_airport, true);
        }

        /**
         * 名字
         */
        helper.setText(R.id.tv_airport, model.getCountry_name() + model.getName());
    }
}
