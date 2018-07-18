package com.sillykid.app.adapter.mine.mywallet.coupons;

import android.content.Context;
import android.view.View;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.mywallet.coupons.CouponsBean.DataBean;
import com.sillykid.app.utils.DataUtil;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 优惠券  适配器
 * Created by Admin on 2017/8/15.
 */

public class CouponsViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    private int type = 0;

    private String money = "";

    public CouponsViewAdapter(Context context, int type, String money) {
        super(context, R.layout.item_coupons);
        this.type = type;
        this.money = money;
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean dataBean) {
        viewHolderHelper.setText(R.id.tv_redpacketnum, MathUtil.keepTwo(StringUtils.toDouble(dataBean.getType_money())));
        viewHolderHelper.setText(R.id.tv_useconditions, mContext.getString(R.string.full) + MathUtil.keepTwo(StringUtils.toDouble(dataBean.getMin_goods_amount())) + mContext.getString(R.string.usable));
        viewHolderHelper.setText(R.id.tv_couponstype, dataBean.getType_name());
        viewHolderHelper.setText(R.id.tv_couponsexplain, mContext.getString(R.string.only) + dataBean.getUname() + mContext.getString(R.string.customerUse));
        if (type == 1 && StringUtils.toDouble(money) >= StringUtils.toDouble(dataBean.getType_money()) && StringUtils.toDouble(money) > 0) {
            viewHolderHelper.setBackgroundRes(R.id.ll_coupons, R.mipmap.coupon_unused);
            viewHolderHelper.setText(R.id.tv_expirydate, DataUtil.formatData(StringUtils.toLong(dataBean.getCreate_time()) + dataBean.getLimit_days() * 3600 * 24, "yyyy-MM-dd"));
            viewHolderHelper.setVisibility(R.id.iv_image, View.GONE);
        } else if (type == 1 && StringUtils.toDouble(money) <= StringUtils.toDouble(dataBean.getType_money()) && StringUtils.toDouble(money) > 0) {
            viewHolderHelper.setBackgroundRes(R.id.ll_coupons, R.mipmap.coupon_used);
            viewHolderHelper.setText(R.id.tv_expirydate, DataUtil.formatData(StringUtils.toLong(dataBean.getCreate_time()) + dataBean.getLimit_days() * 3600 * 24, "yyyy-MM-dd"));
            viewHolderHelper.setVisibility(R.id.iv_image, View.GONE);
        } else if (type == 1) {
            viewHolderHelper.setBackgroundRes(R.id.ll_coupons, R.mipmap.coupon_unused);
            viewHolderHelper.setText(R.id.tv_expirydate, DataUtil.formatData(StringUtils.toLong(dataBean.getCreate_time()) + dataBean.getLimit_days() * 3600 * 24, "yyyy-MM-dd"));
            viewHolderHelper.setVisibility(R.id.iv_image, View.GONE);
        } else if (type == 2) {
            viewHolderHelper.setBackgroundRes(R.id.ll_coupons, R.mipmap.coupon_unused);
            viewHolderHelper.setText(R.id.tv_expirydate, mContext.getString(R.string.used));
            viewHolderHelper.setVisibility(R.id.iv_image, View.GONE);
        } else {
            viewHolderHelper.setBackgroundRes(R.id.ll_coupons, R.mipmap.coupon_used);
            viewHolderHelper.setText(R.id.tv_expirydate, DataUtil.formatData(StringUtils.toLong(dataBean.getCreate_time()) + dataBean.getLimit_days() * 3600 * 24, "yyyy-MM-dd"));
            viewHolderHelper.setVisibility(R.id.iv_image, View.VISIBLE);
        }
    }
}