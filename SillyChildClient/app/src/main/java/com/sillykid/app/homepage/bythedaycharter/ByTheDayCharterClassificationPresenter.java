package com.sillykid.app.homepage.bythedaycharter;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class ByTheDayCharterClassificationPresenter implements ByTheDayCharterClassificationContract.Presenter {
    private ByTheDayCharterClassificationContract.View mView;

    public ByTheDayCharterClassificationPresenter(ByTheDayCharterClassificationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getAirportCountryList(int type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("category", type);
        RequestClient.getAirportCountryList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void getRegionByCountryId(int country_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("country_id", country_id);
        RequestClient.getRegionByCountryId(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
