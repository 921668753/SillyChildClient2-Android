package com.sillykid.app.adapter.mine.mywallet.coupons;

import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.mine.mywallet.coupons.CouponRedemptionCentreBean.DataBean.CouponListBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;


/**
 * 领券中心  适配器
 */
public class CouponRedemptionCentreViewAdapter extends BGAAdapterViewAdapter<CouponListBean> {

    public CouponRedemptionCentreViewAdapter(Context context) {
        super(context, R.layout.item_couponredemption);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.tv_exchange);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, CouponListBean dataBean) {
        /**
         * 图片
         */
//        GlideImageLoader.glideOrdinaryLoader(mContext, dataBean.getLimit_content(), viewHolderHelper.getImageView(R.id.img), R.mipmap.placeholderfigure);
        viewHolderHelper.setText(R.id.tv_name, dataBean.getCoupon_name());
        viewHolderHelper.setText(R.id.tv_money, dataBean.getDenomination());
        viewHolderHelper.setText(R.id.tv_describe, dataBean.getLimit_content());

    }
}