package com.sillykid.app.mine.myorder.charterorder;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */
public class CharterOrderPresenter implements CharterOrderContract.Presenter {
    private CharterOrderContract.View mView;

    public CharterOrderPresenter(CharterOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getChartOrder(Context context, String status, int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (!StringUtils.isEmpty(status)) {
            httpParams.put("status", status);
        }
        httpParams.put("pageno", page);
        httpParams.put("pagesize", 5);
        RequestClient.getChartOrderList(context, httpParams, new ResponseListener<String>() {
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
    public void getIsLogin(Context context, int flag) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getIsLogin(context, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, flag);
            }
        });
    }
}
