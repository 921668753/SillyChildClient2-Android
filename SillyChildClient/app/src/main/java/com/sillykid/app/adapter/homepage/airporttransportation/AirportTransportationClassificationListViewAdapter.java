package com.sillykid.app.adapter.homepage.airporttransportation;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.mall.moreclassification.MoreClassificationBean.DataBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 接送机分类---ListView
 */
public class AirportTransportationClassificationListViewAdapter extends BGAAdapterViewAdapter<DataBean> {


    public AirportTransportationClassificationListViewAdapter(Context context) {
        super(context, R.layout.item_countries);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {

        /**
         * 背景色
         */
        if (model.getIsSelected() == 0) {
            helper.setBackgroundRes(R.id.tv_country, R.color.whiteColors);
            helper.setTextColorRes(R.id.tv_country, R.color.tabColors);
        } else {
            helper.setBackgroundRes(R.id.tv_country, R.drawable.shape_followed1);
            helper.setTextColorRes(R.id.tv_country, R.color.whiteColors);
        }

        /**
         * 名字
         */
        helper.setText(R.id.tv_country, model.getName());
    }
}
