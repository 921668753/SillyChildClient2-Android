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

public class NearBouncedViewAdapter extends BGAAdapterViewAdapter<ResultBean> {


    public NearBouncedViewAdapter(Context context) {
        super(context, R.layout.item_neardialog);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ResultBean model) {

        /**
         * 类别
         */
        helper.setText(R.id.tv_nearName, model.getName());
        if (model.getStatus() == 0) {
            helper.setBackgroundRes(R.id.tv_nearName, R.color.whiteColors);
        } else {
            helper.setBackgroundRes(R.id.tv_nearName, R.color.background);
        }

    }
}
