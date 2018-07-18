package com.sillykid.app.homepage.addressselection.addresssearch;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class AddressSearchPresenter implements AddressSearchContract.Presenter {
    private AddressSearchContract.View mView;

    public AddressSearchPresenter(AddressSearchContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getSearchCity(String name) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getSearchCity(httpParams, name, new ResponseListener<String>() {
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
