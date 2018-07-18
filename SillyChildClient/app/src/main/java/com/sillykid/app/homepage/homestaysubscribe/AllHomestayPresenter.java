package com.sillykid.app.homepage.homestaysubscribe;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class AllHomestayPresenter implements AllHomestayContract.Presenter {
    private AllHomestayContract.View mView;

    public AllHomestayPresenter(AllHomestayContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }



    @Override
    public void getAllHomestay(int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("page", page);
        httpParams.put("pageSize", 10);
//        RequestClient.getInfo(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 1);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }
}
