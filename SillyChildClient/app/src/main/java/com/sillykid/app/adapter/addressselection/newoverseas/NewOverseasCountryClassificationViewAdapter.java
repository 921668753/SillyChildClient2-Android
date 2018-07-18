package com.sillykid.app.adapter.addressselection.newoverseas;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.AllCountryBean.ResultBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 海外 国家分类  适配器
 * Created by Admin on 2017/9/8.
 */

public class NewOverseasCountryClassificationViewAdapter extends BGAAdapterViewAdapter<ResultBean> {


    public NewOverseasCountryClassificationViewAdapter(Context context) {
        super(context, R.layout.item_addressclassification);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ResultBean model) {

        /**
         * 类别
         */
        helper.setText(R.id.tv_classification, model.getName());
        if (model.getStatus() == 1) {
            helper.setTextColorRes(R.id.tv_classification, R.color.greenColors);
            helper.setBackgroundRes(R.id.tv_classification, R.color.whiteColors);
        } else {
            helper.setTextColorRes(R.id.tv_classification, R.color.tabColors);
            helper.setBackgroundRes(R.id.tv_classification, R.color.background);
        }
    }
}
