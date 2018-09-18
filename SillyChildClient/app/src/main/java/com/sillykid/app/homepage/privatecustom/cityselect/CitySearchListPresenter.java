package com.sillykid.app.homepage.privatecustom.cityselect;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */

public class CitySearchListPresenter implements CitySearchListContract.Presenter {

    private CitySearchListContract.View mView;

    public CitySearchListPresenter(CitySearchListContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getAreaByName(String name) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("name", name);
        RequestClient.getAreaByName(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
