package com.sillykid.app.main;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class TripPresenter implements TripContract.Presenter {
    private TripContract.View mView;

    public TripPresenter(TripContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getTrip() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getTrip(httpParams, new ResponseListener<String>() {
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
