package com.sillykid.app.homepage.addressselection;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class InlandPresenter implements InlandContract.Presenter {
    private InlandContract.View mView;

    public InlandPresenter(InlandContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getAllCityIn() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getAllCityInHttp(httpParams,  new ResponseListener<String>() {
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
    public void getAllCity() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getAllCityByCountryId(httpParams, 7, new ResponseListener<String>() {
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
    public void getChildHotCity() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getHotCityByCountryId(httpParams, 0, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }
}
