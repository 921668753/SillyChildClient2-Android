package com.sillykid.app.adapter.gastronomybounced;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.IndexCityBean.ResultBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 美食弹框--附近  适配器
 * Created by Admin on 2017/9/8.
 */

public class Near1BouncedViewAdapter extends BGAAdapterViewAdapter<ResultBean> {


    public Near1BouncedViewAdapter(Context context) {
        super(context, R.layout.item_near1dialog);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ResultBean model) {

        /**
         *名称
         */
        helper.setText(R.id.tv_distance, model.getName());
        if (model.getStatus() == 0) {
            helper.setTextColorRes(R.id.tv_distance, R.color.greenColors);
            helper.setBackgroundRes(R.id.tv_distance1, R.color.greenColors);
        } else {
            helper.setTextColorRes(R.id.tv_distance, R.color.textColor);
            helper.setBackgroundRes(R.id.tv_distance1, R.color.titledivider);
        }
    }
}
