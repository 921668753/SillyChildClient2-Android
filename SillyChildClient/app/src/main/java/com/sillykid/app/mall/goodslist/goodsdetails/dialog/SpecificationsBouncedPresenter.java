package com.sillykid.app.mall.goodslist.goodsdetails.dialog;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class SpecificationsBouncedPresenter implements SpecificationsBouncedContract.Presenter {

    private SpecificationsBouncedContract.View mView;

    public SpecificationsBouncedPresenter(SpecificationsBouncedContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getGoodsSpec(Context context, int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("goodsid", id);
        RequestClient.getGoodsSpec(context, httpParams, new ResponseListener<String>() {
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
    public void getGoodsProductSpec(Context context, int goodsid, String specs) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("goodsid", goodsid);
        httpParams.put("specs", specs);
        RequestClient.getGoodsProductSpec(context, httpParams, new ResponseListener<String>() {
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
    public void getGoodsProductSpecLeft(Context context, int goodsid, String specs) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("goodsid", goodsid);
        httpParams.put("specs", specs);
        RequestClient.getGoodsProductSpecLeft(context, httpParams, new ResponseListener<String>() {
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


}
