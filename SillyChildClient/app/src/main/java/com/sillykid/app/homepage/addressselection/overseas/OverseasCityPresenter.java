package com.sillykid.app.homepage.addressselection.overseas;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class OverseasCityPresenter implements OverseasCityContract.Presenter {
    private OverseasCityContract.View mView;

    public OverseasCityPresenter(OverseasCityContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getAllCity(int countryId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getAllCityByCountryId(httpParams, countryId, new ResponseListener<String>() {
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
    public void getChildHotCity(int countryId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getHotCityByCountryId(httpParams, countryId, new ResponseListener<String>() {
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
