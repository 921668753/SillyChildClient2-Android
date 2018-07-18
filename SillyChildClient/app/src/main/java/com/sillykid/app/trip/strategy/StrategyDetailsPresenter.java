package com.sillykid.app.trip.strategy;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class StrategyDetailsPresenter implements StrategyDetailsContract.Presenter {
    private StrategyDetailsContract.View mView;

    public StrategyDetailsPresenter(StrategyDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getReadMessage(String id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getReadMessage(httpParams, id + "", new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 5);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void getStrategyDetails(int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getStrategyDetails(httpParams, id, new ResponseListener<String>() {
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
    public void collectStrategy(int id, int isCollect) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.collectStrategy(httpParams, id, isCollect, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                if (isCollect == 0) {
                    mView.getSuccess(response, 1);
                } else {
                    mView.getSuccess(response, 2);
                }

            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void praiseStrategyDetails(String id, int isPraise) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.praiseStrategyDetails(httpParams, id, isPraise, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                if (isPraise == 0) {
                    mView.getSuccess(response, 3);
                } else {
                    mView.getSuccess(response, 4);
                }
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }
}
