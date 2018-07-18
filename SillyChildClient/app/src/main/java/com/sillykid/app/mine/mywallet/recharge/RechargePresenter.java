package com.sillykid.app.mine.mywallet.recharge;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class RechargePresenter implements RechargeContract.Presenter {
    private RechargeContract.View mView;

    public RechargePresenter(RechargeContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void doRecharge(int payWay, double amount) {
        if (amount <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseRechargeAmount1), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("pay_type", payWay);
        httpParams.put("order_type", "CZ");
        httpParams.put("amount", String.valueOf(amount));
        RequestClient.postRecharge(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

}
