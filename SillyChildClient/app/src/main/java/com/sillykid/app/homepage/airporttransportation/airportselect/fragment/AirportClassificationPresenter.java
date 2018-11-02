package com.sillykid.app.homepage.airporttransportation.airportselect.fragment;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

public class AirportClassificationPresenter implements AirportClassificationContract.Presenter {

    private AirportClassificationContract.View mView;

    public AirportClassificationPresenter(AirportClassificationContract.View view) {
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
    public void getAirportByCountryId(Context context, int country_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("parentid", country_id);
        RequestClient.getAreaTransfer(context, httpParams, new ResponseListener<String>() {
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
