package com.sillykid.app.mine.mywallet.coupons;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class CouponsPresenter implements CouponsContract.Presenter {

    private CouponsContract.View mView;

    public CouponsPresenter(CouponsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getCoupons(Context context, int type, int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type", type);
        httpParams.put("page", page);
        RequestClient.getCouponsList(context, httpParams, new ResponseListener<String>() {
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
