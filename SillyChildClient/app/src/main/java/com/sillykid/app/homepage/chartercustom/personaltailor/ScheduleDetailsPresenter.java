package com.sillykid.app.homepage.chartercustom.personaltailor;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class ScheduleDetailsPresenter implements ScheduleDetailsContract.Presenter {

    private ScheduleDetailsContract.View mView;

    public ScheduleDetailsPresenter(ScheduleDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getPrivateDetail(String air_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();

        RequestClient.getPrivateDetail(httpParams, air_id, new ResponseListener<String>() {
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

}
