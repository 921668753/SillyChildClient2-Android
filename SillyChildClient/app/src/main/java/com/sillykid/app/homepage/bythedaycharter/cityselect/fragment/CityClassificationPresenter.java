package com.sillykid.app.homepage.bythedaycharter.cityselect.fragment;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

public class CityClassificationPresenter implements CityClassificationContract.Presenter {

    private CityClassificationContract.View mView;

    public CityClassificationPresenter(CityClassificationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getCountryAreaListByParentid(Context context, int id, int type, int flag) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("parentid", id);
        httpParams.put("category", type);
        RequestClient.getAreaListParent(context, httpParams, new ResponseListener<String>() {
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


    @Override
    public void getCountryAreaListByParentid(Context context, int id, int flag) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("id", id);
        RequestClient.getCountryAreaListByParentid(context, httpParams, new ResponseListener<String>() {
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


    @Override
    public void getRecommendCity(Context context, int type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("category", type);
        RequestClient.getRecommendCity(context, httpParams, new ResponseListener<String>() {
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
