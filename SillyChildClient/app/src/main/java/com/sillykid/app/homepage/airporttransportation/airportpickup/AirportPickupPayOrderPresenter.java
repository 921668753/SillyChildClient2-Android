package com.sillykid.app.homepage.airporttransportation.airportpickup;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;
import com.sillykid.app.utils.DataUtil;

/**
 * Created by ruitu on 2016/9/24.
 */

public class AirportPickupPayOrderPresenter implements AirportPickupPayOrderContract.Presenter {

    private AirportPickupPayOrderContract.View mView;

    public AirportPickupPayOrderPresenter(AirportPickupPayOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getTravelOrderDetail(int requirement_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("requirement_id", requirement_id);
        RequestClient.getTravelOrderDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
