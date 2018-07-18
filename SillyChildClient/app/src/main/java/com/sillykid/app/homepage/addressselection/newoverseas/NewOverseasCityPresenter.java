package com.sillykid.app.homepage.addressselection.newoverseas;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class NewOverseasCityPresenter implements NewOverseasCityContract.Presenter {
    private NewOverseasCityContract.View mView;

    public NewOverseasCityPresenter(NewOverseasCityContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getAllCountry() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getAllCountry(httpParams, new ResponseListener<String>() {
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
    public void getAllChildCity(int countryId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getAllCityByCountryId(httpParams, countryId, new ResponseListener<String>() {
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
