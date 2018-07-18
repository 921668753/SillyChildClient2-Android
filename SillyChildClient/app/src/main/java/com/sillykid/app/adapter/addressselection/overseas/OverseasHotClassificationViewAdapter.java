package com.sillykid.app.adapter.addressselection.overseas;

import android.content.Context;

import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.sillykid.app.R;
import com.sillykid.app.entity.IndexCityBean.ResultBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 海外 地址分类  适配器
 * Created by Admin on 2017/9/8.
 */

public class OverseasHotClassificationViewAdapter extends BGAAdapterViewAdapter<ResultBean> {


    public OverseasHotClassificationViewAdapter(Context context) {
        super(context, R.layout.item_addressclassification2);
    }


    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ResultBean model) {

        /**
         * 国家
         */
        helper.setText(R.id.tv_country, model.getName());
    }
}
