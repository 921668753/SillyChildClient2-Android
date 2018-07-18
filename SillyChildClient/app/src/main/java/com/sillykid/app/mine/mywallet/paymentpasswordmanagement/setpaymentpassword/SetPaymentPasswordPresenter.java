package com.sillykid.app.mine.mywallet.paymentpasswordmanagement.setpaymentpassword;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.CipherUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/15.
 */

public class SetPaymentPasswordPresenter implements SetPaymentPasswordContract.Presenter {


    private SetPaymentPasswordContract.View mView;

    public SetPaymentPasswordPresenter(SetPaymentPasswordContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postSetPaymentPassword(String oldPaymentPassword, String paymentPassword) {
        if (paymentPassword.length() != 6) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseEnterPaymentPassword1), 0);
            return;
        }
        if (!oldPaymentPassword.equals(paymentPassword)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.paymentPasswordsNotMatch), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", 0);
        map.put("pay_password", CipherUtils.md5("WUZAI" + paymentPassword + "TIANXIA"));
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.postSetPayPassword(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 0);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }
}