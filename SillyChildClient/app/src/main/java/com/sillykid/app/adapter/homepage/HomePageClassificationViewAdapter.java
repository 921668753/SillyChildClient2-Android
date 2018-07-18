package com.sillykid.app.adapter.homepage;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.sillykid.app.R;
import com.sillykid.app.entity.main.MallHomePageBean.DataBean.ApiCatTreeBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页 分类 适配器
 * Created by Admin on 2017/8/15.
 */

public class HomePageClassificationViewAdapter extends BGAAdapterViewAdapter<ApiCatTreeBean> {

    public HomePageClassificationViewAdapter(Context context) {
        super(context, R.layout.item_homeclassification);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ApiCatTreeBean listBean) {
        Log.d("position", position + "");

        /**0000000000
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getImage(), (ImageView) viewHolderHelper.getView(R.id.img_icon), R.mipmap.placeholderfigure);


        /**
         * 分类名字
         */
        viewHolderHelper.setText(R.id.tv_classificationName, listBean.getName());
    }

}