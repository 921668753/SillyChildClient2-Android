package com.sillykid.app.adapter.homepage.moreclassification;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.moreclassification.ClassificationBean.DataBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页 更多分类详情 适配器
 * Created by Admin on 2017/8/15.
 */

public class ClassificationViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public ClassificationViewAdapter(Context context) {
        super(context, R.layout.item_classification);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean listBean) {
        Log.d("position", position + "");

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getImage(), (ImageView) viewHolderHelper.getView(R.id.img_classification), R.mipmap.placeholderfigure);


        /**
         * 分类名字
         */
        viewHolderHelper.setText(R.id.tv_classificationName, listBean.getName());
    }

}