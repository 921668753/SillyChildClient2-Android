package com.sillykid.app.homepage.message;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class InteractiveMessagePresenter implements InteractiveMessageContract.Presenter {

    private InteractiveMessageContract.View mView;

    public InteractiveMessagePresenter(InteractiveMessageContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getIsLogin(Context context, int flage) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getIsLogin(context, httpParams, new ResponseListener<String>() {
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
