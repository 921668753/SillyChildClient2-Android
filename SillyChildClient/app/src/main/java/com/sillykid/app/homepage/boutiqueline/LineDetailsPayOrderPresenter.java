package com.sillykid.app.homepage.boutiqueline;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

public class LineDetailsPayOrderPresenter implements LineDetailsPayOrderContract.Presenter {

    private LineDetailsPayOrderContract.View mView;

    public LineDetailsPayOrderPresenter(LineDetailsPayOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getTravelOrderDetail(int requirement_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("requirement_id", requirement_id);
        RequestClient.getTravelOrderDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void createTravelOrder(int product_id, String order_number, int bonus_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("product_id", product_id);
        httpParams.put("order_number", order_number);
        if (bonus_id != 0) {
            httpParams.put("bonus_id", bonus_id);
        }
        RequestClient.postCreateTravelOrder(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
}
