package com.sillykid.app.homepage.chartercustom;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class CharterCustomPresenter implements CharterCustomContract.Presenter {
    private CharterCustomContract.View mView;

    public CharterCustomPresenter(CharterCustomContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getCharterCustom(String city) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (city.equals(KJActivityStack.create().topActivity().getString(R.string.allAeservationNumber))) {
            city = "";
        }
        RequestClient.getCharterCustom(httpParams, city, new ResponseListener<String>() {
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

    @Override
    public void getSearchDriver(String search) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getSearchDriver(httpParams, search, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 7);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void getFindDriver(String search) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getFindDriver(httpParams, search, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 8);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }


}
