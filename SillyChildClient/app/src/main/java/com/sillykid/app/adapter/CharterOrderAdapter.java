package com.sillykid.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.sillykid.app.R;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.CharterOrderBean.ResultBean.ListBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 我的订单---包车订单   适配器
 * Created by Admin on 2017/8/15.
 */

public class CharterOrderAdapter extends BGAAdapterViewAdapter<ListBean> {

    public CharterOrderAdapter(Context context) {
        super(context, R.layout.item_charterorder);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.tv_confirmPayment);
        helper.setItemChildClickListener(R.id.tv_callUp);
        helper.setItemChildClickListener(R.id.tv_sendPrivateChat);
        helper.setItemChildClickListener(R.id.tv_appraiseOrder);
        helper.setItemChildClickListener(R.id.tv_additionalComments);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        viewHolderHelper.setText(R.id.tv_orderNumber, listBean.getOrder_sn());

        switch (listBean.getStatusX()) {
            case NumericConstants.NoPay://待付款
                viewHolderHelper.setText(R.id.tv_charterStatus, R.string.obligation);
                viewHolderHelper.setVisibility(R.id.tv_confirmPayment, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_callUp, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_sendPrivateChat, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_appraiseOrder, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_additionalComments, View.GONE);
                break;
            case NumericConstants.SendOrder://进行中
                viewHolderHelper.setText(R.id.tv_charterStatus, R.string.ongoing);
                viewHolderHelper.setVisibility(R.id.tv_confirmPayment, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_callUp, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_sendPrivateChat, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_appraiseOrder, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_additionalComments, View.GONE);
                break;
            case NumericConstants.WaiteOrder://待评价
                viewHolderHelper.setText(R.id.tv_charterStatus, R.string.evaluate);
                viewHolderHelper.setVisibility(R.id.tv_confirmPayment, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_callUp, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_sendPrivateChat, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_appraiseOrder, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_additionalComments, View.GONE);
                break;
            case NumericConstants.CompletedInDeatil://已完成
                viewHolderHelper.setText(R.id.tv_charterStatus, R.string.completed);
                viewHolderHelper.setVisibility(R.id.tv_confirmPayment, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_callUp, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_sendPrivateChat, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_appraiseOrder, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_additionalComments, View.VISIBLE);
                break;
            default:
                break;

        }

        GlideImageLoader.glideLoader(mContext, listBean.getAvatar(), (ImageView) viewHolderHelper.getView(R.id.img_charterOrder), 0, R.mipmap.avatar);
        viewHolderHelper.setText(R.id.tv_title, R.string.needpayWithSymbol);
        viewHolderHelper.setText(R.id.tv_serviceTime, mContext.getString(R.string.serviceTime));
        viewHolderHelper.setText(R.id.tv_serviceCompany, mContext.getString(R.string.serviceCompany));
        viewHolderHelper.setText(R.id.tv_orderMoney, mContext.getString(R.string.renminbi));
        viewHolderHelper.setText(R.id.tv_money, mContext.getString(R.string.renminbi));
    }
}