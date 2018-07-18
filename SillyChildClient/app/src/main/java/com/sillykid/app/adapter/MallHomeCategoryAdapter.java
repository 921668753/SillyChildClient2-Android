package com.sillykid.app.adapter;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.MallHomeCategoryBean.ResultBean.ListBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 商城主界面商品分类 适配器
 * Created by Admin on 2017/8/15.
 */

public class MallHomeCategoryAdapter extends BGAAdapterViewAdapter<ListBean> {

    public MallHomeCategoryAdapter(Context context) {
        super(context, R.layout.item_classification);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

//        /**
//         * 图片
//         */
//        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getAvatar(), (ImageView) viewHolderHelper.getView(R.id.img_localTalent));
//
//        /**
//         * 姓名
//         */
//        viewHolderHelper.setText(R.id.tv_name, listBean.getName());
//
//        /**
//         * 地址
//         */
//        viewHolderHelper.setText(R.id.tv_address, "泰国·曼谷");
//
//        /**
//         * 类别
//         */
//        viewHolderHelper.setText(R.id.tv_sort, "泰国·曼谷");
//
//        /**
//         * 赞数
//         */
//        viewHolderHelper.setText(R.id.tv_greatNumber, "赞" + mContext.getString(R.string.zan));

    }

}