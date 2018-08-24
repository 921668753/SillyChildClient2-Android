package com.sillykid.app.mine.myorder.goodorder;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class GoodOrderPresenter implements GoodOrderContract.Presenter {
    private GoodOrderContract.View mView;

    public GoodOrderPresenter(GoodOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getOrderList(Context context, String status, int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (!StringUtils.isEmpty(status)) {
            httpParams.put("status", status);
        }
        httpParams.put("page", page);
        RequestClient.getOrderList(context, httpParams, new ResponseListener<String>() {
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

    @Override
    public void postOrderCancel(Context context, int orderid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderid", orderid);
        RequestClient.postOrderCancel(context, httpParams, new ResponseListener<String>() {
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

    @Override
    public void postOrderRemind(Context context, int orderid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("order_id", orderid);
        RequestClient.postOrderRemind(context, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 2);
            }
        });

    }

    @Override
    public void postOrderConfirm(Context context, int orderid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderid", orderid);
        RequestClient.postOrderConfirm(context, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 4);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 4);
            }
        });
    }
}
