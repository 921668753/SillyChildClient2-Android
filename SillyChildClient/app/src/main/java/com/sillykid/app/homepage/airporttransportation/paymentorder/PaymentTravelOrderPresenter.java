package com.sillykid.app.homepage.airporttransportation.paymentorder;

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

public class PaymentTravelOrderPresenter implements PaymentTravelOrderContract.Presenter {


    private PaymentTravelOrderContract.View mView;

    public PaymentTravelOrderPresenter(PaymentTravelOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    /**
     * 获取钱包余额
     */
    @Override
    public void getMyWallet() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getMyWallet(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                MyWalletBean myWalletBean = (MyWalletBean) JsonUtil.getInstance().json2Obj(response, MyWalletBean.class);
                if (!StringUtils.isEmpty(myWalletBean.getData().getBalance())) {
                    PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "balance", MathUtil.keepTwo(StringUtils.toDouble(myWalletBean.getData().getBalance())));
                    mView.getSuccess(MathUtil.keepTwo(StringUtils.toDouble(myWalletBean.getData().getBalance())), 0);
                } else {
                    PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "balance", "0.00");
                    mView.getSuccess("0.00", 0);
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
    public void getOnlinePay(int order_id, String pay_type) {
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
