package com.sillykid.app.homepage.airporttransportation.paymentorder;

import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.entity.mine.mywallet.MyWalletBean;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by Administrator on 2018/5/15.
 */

public class PaymentOrderPresenter implements PaymentOrderContract.Presenter {


    private PaymentOrderContract.View mView;

    public PaymentOrderPresenter(PaymentOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getOrderDetails(String orderId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderid", orderId);
        RequestClient.getOrderDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                getMyWallet(KJActivityStack.create().topActivity(), response);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    /**
     * 获取钱包余额
     */
    public void getMyWallet(Context context, String response1) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getMyWallet(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                MyWalletBean myWalletBean = (MyWalletBean) JsonUtil.getInstance().json2Obj(response, MyWalletBean.class);
                if (!StringUtils.isEmpty(myWalletBean.getData().getBalance())) {
                    PreferenceHelper.write(context, StringConstants.FILENAME, "balance", MathUtil.keepTwo(StringUtils.toDouble(myWalletBean.getData().getBalance())));
                    mView.getSuccess(response1, 0);
                } else {
                    PreferenceHelper.write(context, StringConstants.FILENAME, "balance", "0.00");
                    mView.getSuccess(response1, 0);
                }
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    /**
     * 获取购物车列表
     */
    @Override
    public void getOnlinePay(String order_id, String pay_type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("order_id", order_id);
        httpParams.put("order_type", "TT");
        httpParams.put("pay_type", pay_type);
        RequestClient.getOnlinePay(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
    }

}
