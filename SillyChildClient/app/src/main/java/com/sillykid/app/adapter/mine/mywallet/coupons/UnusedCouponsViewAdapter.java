package com.sillykid.app.adapter.mine.mywallet.coupons;

import android.content.Context;
import android.view.View;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.mywallet.coupons.UnusedCouponsBean.DataBean.ResultBean;
import com.sillykid.app.utils.DataUtil;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class UnusedCouponsViewAdapter extends BGAAdapterViewAdapter<ResultBean> {

    private int type = 1;

    public UnusedCouponsViewAdapter(Context context, int type) {
        super(context, R.layout.item_coupons);
        this.type = type;
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ResultBean dataBean) {
        viewHolderHelper.setText(R.id.tv_redpacketnum, MathUtil.keepTwo(StringUtils.toDouble(dataBean.getDenomination())));
        viewHolderHelper.setText(R.id.tv_useconditions, dataBean.getLimit_content());
        viewHolderHelper.setText(R.id.tv_couponstype, dataBean.getCoupon_name());
        viewHolderHelper.setText(R.id.tv_expirydate, DataUtil.formatData(StringUtils.toLong(dataBean.getValidity_period()), "yyyy-MM-dd"));
        viewHolderHelper.setText(R.id.tv_couponsexplain, dataBean.getRemark());
        if (type == 1) {
            viewHolderHelper.setBackgroundRes(R.id.ll_coupons, R.mipmap.coupon_unused);
            viewHolderHelper.setVisibility(R.id.iv_image, View.GONE);
        } else if (type == 2) {
            viewHolderHelper.setBackgroundRes(R.id.ll_coupons, R.mipmap.coupon_used);
            viewHolderHelper.setVisibility(R.id.iv_image, View.GONE);
        } else if (type == 3) {
            viewHolderHelper.setBackgroundRes(R.id.ll_coupons, R.mipmap.coupon_used);
            viewHolderHelper.setVisibility(R.id.iv_image, View.VISIBLE);
        }

//        if (type == 1 && StringUtils.toDouble(money) >= StringUtils.toDouble(dataBean.getType_money()) && StringUtils.toDouble(money) > 0) {
//
//            viewHolderHelper.setText(R.id.tv_expirydate, );
//
//        } else if (type == 1 && StringUtils.toDouble(money) <= StringUtils.toDouble(dataBean.getType_money()) && StringUtils.toDouble(money) > 0) {
//            viewHolderHelper.setBackgroundRes(R.id.ll_coupons, R.mipmap.coupon_used);
//            viewHolderHelper.setText(R.id.tv_expirydate, DataUtil.formatData(StringUtils.toLong(dataBean.getCreate_time()) + dataBean.getLimit_days() * 3600 * 24, "yyyy-MM-dd"));
//            viewHolderHelper.setVisibility(R.id.iv_image, View.GONE);
//        } else if (type == 1) {
//            viewHolderHelper.setBackgroundRes(R.id.ll_coupons, R.mipmap.coupon_unused);
//            viewHolderHelper.setText(R.id.tv_expirydate, DataUtil.formatData(StringUtils.toLong(dataBean.getCreate_time()) + dataBean.getLimit_days() * 3600 * 24, "yyyy-MM-dd"));
//            viewHolderHelper.setVisibility(R.id.iv_image, View.GONE);
//        } else if (type == 2) {
//            viewHolderHelper.setBackgroundRes(R.id.ll_coupons, R.mipmap.coupon_unused);
//            viewHolderHelper.setText(R.id.tv_expirydate, mContext.getString(R.string.used));
//            viewHolderHelper.setVisibility(R.id.iv_image, View.GONE);
//        } else {
//            viewHolderHelper.setBackgroundRes(R.id.ll_coupons, R.mipmap.coupon_used);
//            viewHolderHelper.setText(R.id.tv_expirydate, DataUtil.formatData(StringUtils.toLong(dataBean.getCreate_time()) + dataBean.getLimit_days() * 3600 * 24, "yyyy-MM-dd"));
//            viewHolderHelper.setVisibility(R.id.iv_image, View.VISIBLE);
//        }
    }
}