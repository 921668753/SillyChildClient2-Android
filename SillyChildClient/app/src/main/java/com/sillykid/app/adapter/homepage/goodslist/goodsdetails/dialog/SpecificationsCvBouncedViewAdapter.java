package com.sillykid.app.adapter.homepage.goodslist.goodsdetails.dialog;


import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.goodslist.goodsdetails.dialog.SpecificationsBouncedBean.DataBean.SpecValueIdsBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 商品详情--------规格弹框
 */
public class SpecificationsCvBouncedViewAdapter extends BGAAdapterViewAdapter<SpecValueIdsBean> {

    public SpecificationsCvBouncedViewAdapter(Context context) {
        super(context, R.layout.item_specifications);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, SpecValueIdsBean model) {
        helper.setText(R.id.tv_name, model.getSpec_value());
        if (model.getIsSelected() == 1 && model.getIsNoClick() == 0) {
            helper.setBackgroundRes(R.id.tv_name, R.drawable.shape_specifications);
            helper.setTextColorRes(R.id.tv_name, R.color.whiteColors);
        } else if (model.getIsSelected() == 0 && model.getIsNoClick() == 0) {
            helper.setBackgroundRes(R.id.tv_name, R.drawable.shape_specifications1);
            helper.setTextColorRes(R.id.tv_name, R.color.hintColors);
        } else {
            helper.setBackgroundRes(R.id.tv_name, R.drawable.shape_specifications2);
            helper.setTextColorRes(R.id.tv_name, R.color.hintColors);
        }
    }
}
