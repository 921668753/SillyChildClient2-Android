package com.sillykid.app.main;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;


/**
 * Created by ruitu on 2016/9/24.
 */

public class MinePresenter implements MineContract.Presenter {
    private MineContract.View mView;

    public MinePresenter(MineContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getInfo(Context context) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getInfo(context, httpParams, new ResponseListener<String>() {
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
                mView.errorMsg(msg, 1);
            }
        });
    }


}
