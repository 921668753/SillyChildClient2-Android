package com.sillykid.app.homepage.chartercustom.chartercommon;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class CharterDetailsPresenter implements CharterDetailsContract.Presenter {
    private CharterDetailsContract.View mView;

    public CharterDetailsPresenter(CharterDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    /**
     * 获得包车产品详情
     */
    @Override
    public void getCharterDetails(int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("id", id);
        RequestClient.getCharterDetails(httpParams, new ResponseListener<String>() {
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
    public void postCollectCharter(String line_id, int type) {

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.postCollectCharter(httpParams, line_id, type, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                if (type == 0) {
                    mView.getSuccess(response, 4);
                } else {
                    mView.getSuccess(response, 5);
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
