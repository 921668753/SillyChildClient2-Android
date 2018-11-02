package com.sillykid.app.homepage.airporttransportation.airportselect;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */
public class AirportSelectPresenter implements AirportSelectContract.Presenter {
    private AirportSelectContract.View mView;

    public AirportSelectPresenter(AirportSelectContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getCountryAreaList(int type, int parentId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("category", type);
        httpParams.put("parentid", parentId);
        RequestClient.getAreaListParent(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
