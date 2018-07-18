package com.sillykid.app.adapter.trip;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.RegionalStrategyBean.ResultBean.ListBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 地区攻略 适配器
 * Created by Admin on 2017/8/15.
 */

public class RegionalStrategyViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public RegionalStrategyViewAdapter(Context context) {
        super(context, R.layout.item_regionalstrategy);
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