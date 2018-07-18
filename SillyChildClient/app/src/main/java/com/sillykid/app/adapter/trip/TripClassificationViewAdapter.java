package com.sillykid.app.adapter.trip;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.TripBean.ResultBean.IndexBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 出行分类
 * Created by Admin on 2017/9/22.
 */

public class TripClassificationViewAdapter extends BGAAdapterViewAdapter<IndexBean> {

    public TripClassificationViewAdapter(Context context) {
        super(context, R.layout.item_trip);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, IndexBean model) {
        /**
         * 类别
         */
        if (model.getName().equals(mContext.getString(R.string.visa))) {
            helper.setImageResource(R.id.img_classification, R.mipmap.qianzheng);
        } else if (model.getName().equals(mContext.getString(R.string.homestay))) {
            helper.setImageResource(R.id.img_classification, R.mipmap.minsu);
        } else if (model.getName().equals(mContext.getString(R.string.charter))) {
            helper.setImageResource(R.id.img_classification, R.mipmap.baoche);
        } else if (model.getName().equals(mContext.getString(R.string.strategy))) {
            helper.setImageResource(R.id.img_classification, R.mipmap.gonglue);
        } else if (model.getName().equals(mContext.getString(R.string.gastronomy))) {
            helper.setImageResource(R.id.img_classification, R.mipmap.meishi);
        }
        /**
         * 名称
         */
        helper.setText(R.id.tv_name, model.getName());
    }
}
