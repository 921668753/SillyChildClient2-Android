package com.sillykid.app.homepage.message;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class CompanyGuideMessagePresenter implements CompanyGuideMessageContract.Presenter {
    private CompanyGuideMessageContract.View mView;

    public CompanyGuideMessagePresenter(CompanyGuideMessageContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getCompanyGuideMessage() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type", "driver");
        httpParams.put("page", 1);
        httpParams.put("pageSize", 10000);
        RequestClient.getHxUserList(httpParams, new ResponseListener<String>() {
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
