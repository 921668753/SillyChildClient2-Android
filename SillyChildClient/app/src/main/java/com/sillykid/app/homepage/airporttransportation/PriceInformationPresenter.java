package com.sillykid.app.homepage.airporttransportation;

import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class PriceInformationPresenter implements PriceInformationContract.Presenter {
    private PriceInformationContract.View mView;

    public PriceInformationPresenter(PriceInformationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getProductDetails(int product_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("product_id", product_id);
        RequestClient.getProductDetails(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postAddCommentLike(int id, int type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("comment_id", id);
        httpParams.put("type", type);
        RequestClient.postAddCommentLike(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
