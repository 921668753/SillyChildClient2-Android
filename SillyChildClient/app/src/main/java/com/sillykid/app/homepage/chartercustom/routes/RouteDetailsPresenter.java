package com.sillykid.app.homepage.chartercustom.routes;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class RouteDetailsPresenter implements RouteDetailsContract.Presenter {
    private RouteDetailsContract.View mView;

    public RouteDetailsPresenter(RouteDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getRouteDetails(String id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getRouteDetails(httpParams, id, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void postCollectLine(String action, String line_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("action", action);
        httpParams.put("line_id", line_id);
        RequestClient.postCollectLine(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                if (action.equals("collect")) {
                    mView.getSuccess(response, 0);
                } else {
                    mView.getSuccess(response, 1);
                }
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void isLogin(int flag) {
//        RequestClient.isLogin(new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, flag);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 1);
//            }
//        });
    }
}
