package com.sillykid.app.homepage.popularstrategy;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class HotStrategyPresenter implements HotStrategyContract.Presenter {
    private HotStrategyContract.View mView;

    public HotStrategyPresenter(HotStrategyContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getHotStrategy(String city, int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getHotStrategy(httpParams, city, page, new ResponseListener<String>() {
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

    @Override
    public void getStrategy(String country_name, int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getStrategy(httpParams, page, country_name, new ResponseListener<String>() {
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
