package com.sillykid.app.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.CharterOrderBean.ResultBean.ListBean;
import com.sillykid.app.utils.GlideImageLoader;

import java.text.SimpleDateFormat;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 我的订单---包车订单   适配器
 * Created by Admin on 2017/8/15.
 */

public class CharterOrderAdapter extends BGAAdapterViewAdapter<ListBean> {
    private Context context;
    private SimpleDateFormat dateformat;

    public CharterOrderAdapter(Context context) {
        super(context, R.layout.item_charterorder);
        this.context = context;
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.tv_leftbtn);
        helper.setItemChildClickListener(R.id.tv_rightbtn);
        helper.setItemChildClickListener(R.id.tv_rightbtn2);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        viewHolderHelper.setText(R.id.tv_charter_code, listBean.getOrder_sn());

        switch (listBean.getStatusX()) {
            case NumericConstants.NoPay:
                viewHolderHelper.setVisibility(R.id.ll_btns, View.VISIBLE);
                viewHolderHelper.setText(R.id.tv_charter_status, R.string.obligation);
                viewHolderHelper.setText(R.id.tv_charter_paymoney_hint, R.string.needpayWithSymbol);
                viewHolderHelper.setVisibility(R.id.tv_leftbtn, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_rightbtn, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_rightbtn2, View.GONE);
                viewHolderHelper.setText(R.id.tv_leftbtn, R.string.delete);
                viewHolderHelper.setText(R.id.tv_rightbtn, R.string.toPayment);
                break;
            case NumericConstants.SendOrder://未派单
                viewHolderHelper.setVisibility(R.id.ll_btns, View.GONE);
                viewHolderHelper.setText(R.id.tv_charter_status, R.string.sendSingle);
                break;
            case NumericConstants.WaiteOrder://已派单待接单
                viewHolderHelper.setVisibility(R.id.ll_btns, View.GONE);
                viewHolderHelper.setText(R.id.tv_charter_status, R.string.sendSingleWaitingList);
                break;
            case NumericConstants.OnGoing://即将开始
            case NumericConstants.WaiteEvaluate://进行中
                viewHolderHelper.setVisibility(R.id.ll_btns, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_leftbtn, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_rightbtn, View.VISIBLE);
                if (StringUtils.toInt(listBean.getUser_confirm(), 0) == 0) {
                    viewHolderHelper.setText(R.id.tv_charter_status, R.string.ongoing);
                    viewHolderHelper.setVisibility(R.id.tv_rightbtn2, View.VISIBLE);
                } else {
                    viewHolderHelper.setText(R.id.tv_charter_status, R.string.waitConfing);
                    viewHolderHelper.setVisibility(R.id.tv_rightbtn2, View.GONE);
                }
                viewHolderHelper.setText(R.id.tv_leftbtn, R.string.callUp);
                viewHolderHelper.setText(R.id.tv_rightbtn, R.string.sendPrivateChat);

                break;
            case NumericConstants.Completed://待评价
                viewHolderHelper.setVisibility(R.id.ll_btns, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_leftbtn, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_rightbtn, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_rightbtn2, View.GONE);
                if (StringUtils.toInt(listBean.getUser_order_status(), 0) == 0) {//未评价
                    viewHolderHelper.setText(R.id.tv_charter_status, R.string.evaluate);
                    viewHolderHelper.setText(R.id.tv_rightbtn, R.string.toAppraise);
                } else {
                    viewHolderHelper.setText(R.id.tv_charter_status, R.string.guideEvaluate);
                    viewHolderHelper.setText(R.id.tv_rightbtn, R.string.seeEvaluation);
                }
                break;
            case NumericConstants.CompletedInDeatil://已完成
                viewHolderHelper.setText(R.id.tv_charter_status, R.string.completed);
                viewHolderHelper.setVisibility(R.id.ll_btns, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_leftbtn, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_rightbtn, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_rightbtn2, View.GONE);
                viewHolderHelper.setText(R.id.tv_rightbtn, R.string.seeEvaluation);
                break;
            case NumericConstants.Close://已关闭
                viewHolderHelper.setText(R.id.tv_charter_status, R.string.closed);
                viewHolderHelper.setVisibility(R.id.ll_btns, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_leftbtn, View.VISIBLE);
                viewHolderHelper.setVisibility(R.id.tv_rightbtn, View.GONE);
                viewHolderHelper.setVisibility(R.id.tv_rightbtn2, View.GONE);
                viewHolderHelper.setText(R.id.tv_leftbtn, R.string.delete);
                break;
            default:
                viewHolderHelper.setVisibility(R.id.ll_btns, View.GONE);
                break;

        }

        GlideImageLoader.glideLoader(mContext, listBean.getAvatar(), (ImageView) viewHolderHelper.getView(R.id.iv_goodsicon),0, R.mipmap.avatar);
        viewHolderHelper.setText(R.id.tv_charter_title, listBean.getTitle());
        viewHolderHelper.setText(R.id.tv_charter_datetime, listBean.getCreate_at());
        if (TextUtils.isEmpty(listBean.getTotal_price())) {
            viewHolderHelper.setText(R.id.tv_charter_orderprice, context.getResources().getString(R.string.moneySign) + "0.00");
        } else {
            viewHolderHelper.setText(R.id.tv_charter_orderprice, listBean.getTotal_price_fmt());
        }
        if (TextUtils.isEmpty(listBean.getReal_price())) {
            viewHolderHelper.setText(R.id.tv_charter_paymoney, context.getResources().getString(R.string.moneySign) + "0.00");
        } else {
            viewHolderHelper.setText(R.id.tv_charter_paymoney, listBean.getReal_price_fmt());
        }
    }
}