package com.sillykid.app.homepage.airporttransportation.airportpickup;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class AirportPickupPresenter implements AirportPickupContract.Presenter {
    private AirportPickupContract.View mView;

    public AirportPickupPresenter(AirportPickupContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void post(int page, String sort_field, String sort_type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("p", page);
        httpParams.put("pageSize", 5);
        httpParams.put("sort_field", sort_field);
        httpParams.put("sort_type", sort_type);
        RequestClient.getAllDynamics(httpParams, new ResponseListener<String>() {
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
