package com.sillykid.app.homepage.chartercustom.routes;

import android.text.TextUtils;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class CheckstandPresenter implements CheckstandContract.Presenter {
    private CheckstandContract.View mView;

    public CheckstandPresenter(CheckstandContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getInfo(int flagint) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getInfo(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, flagint);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }

    @Override
    public void orderPay(String air_id,String pay_way,String coupon_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();

        if (!TextUtils.isEmpty(coupon_id)&& StringUtils.toDouble(coupon_id)!=0d){
            httpParams.put("coupon_id",coupon_id);
        }

        httpParams.put("air_id", air_id);
        httpParams.put("pay_way", pay_way);
        RequestClient.postScorePay(httpParams, new ResponseListener<String>() {
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
