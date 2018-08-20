package com.sillykid.app.homepage.airporttransportation;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class SelectProductAirportTransportationPresenter implements SelectProductAirportTransportationContract.Presenter {
    private SelectProductAirportTransportationContract.View mView;

    public SelectProductAirportTransportationPresenter(SelectProductAirportTransportationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getProductByAirportId(int airport_id, int category) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("airport_id", airport_id);
        httpParams.put("category", category);
        RequestClient.getProductByAirportId(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
