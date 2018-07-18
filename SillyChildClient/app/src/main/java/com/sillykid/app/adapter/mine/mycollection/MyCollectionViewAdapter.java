package com.sillykid.app.adapter.mine.mycollection;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.mycollection.MyCollectionBean.DataBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 我的收藏中的商品 适配器
 * Created by Admin on 2017/8/15.
 */

public class MyCollectionViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public MyCollectionViewAdapter(Context context) {
        super(context, R.layout.item_good);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        helper.setItemChildClickListener(R.id.img_delete);
        helper.setItemChildClickListener(R.id.img_shoppingCart);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean listBean) {

        /**
         *商品图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getThumbnail(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);

        /**
         *商品名字
         */
        viewHolderHelper.setText(R.id.tv_goodName, listBean.getName());

        /**
         *商品简介
         */
//        if (StringUtils.isEmpty(listBean.getBrief())) {
//            viewHolderHelper.setVisibility(R.id.tv_goodSynopsis, View.GONE);
//        } else {
        viewHolderHelper.setText(R.id.tv_goodSynopsis, listBean.getBrief());
        //    }
        //  viewHolderHelper.setText(R.id.tv_goodSynopsis, listBean.getSpecs());

        /**
         *商品价格
         */
        viewHolderHelper.setText(R.id.tv_money, mContext.getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(listBean.getPrice())));

        /**
         *是否自营
         */
        if (listBean.getStore_name().contains(mContext.getString(R.string.platformProprietary))) {
            viewHolderHelper.setVisibility(R.id.tv_proprietary, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_proprietary, View.GONE);
        }
    }

}