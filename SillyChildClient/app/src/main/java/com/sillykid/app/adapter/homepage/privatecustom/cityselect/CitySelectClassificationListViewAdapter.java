package com.sillykid.app.adapter.homepage.privatecustom.cityselect;

import android.content.Context;
import android.view.View;

import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.privatecustom.cityselect.CitySelectListBean.DataBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 城市选择分类---ListView
 */
public class CitySelectClassificationListViewAdapter extends BGAAdapterViewAdapter<DataBean> {


    public CitySelectClassificationListViewAdapter(Context context) {
        super(context, R.layout.item_countries);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {

        /**
         * 背景色
         */
        if (model.getIsSelected() == 0) {
            helper.setBackgroundRes(R.id.ll_countries, R.color.whiteColors);
            helper.setTextColorRes(R.id.tv_country, R.color.tabColors);
            helper.setVisibility(R.id.img_citySelect, View.INVISIBLE);
            helper.setBold(R.id.tv_country, false);
        } else {
            helper.setBackgroundRes(R.id.ll_countries, R.color.background);
            helper.setVisibility(R.id.img_citySelect, View.VISIBLE);
            helper.setTextColorRes(R.id.tv_country, R.color.greenColors);
            helper.setBold(R.id.tv_country, true);
        }
        if (!StringUtils.isEmpty(model.getName()) && model.getName().startsWith(mContext.getString(R.string.recommended)) && model.getIsSelected() == 0) {
            helper.setTextColorRes(R.id.tv_country, R.color.fF4848Colors);
        }

        /**
         * 名字
         */
        helper.setText(R.id.tv_country, model.getName());
    }
}
