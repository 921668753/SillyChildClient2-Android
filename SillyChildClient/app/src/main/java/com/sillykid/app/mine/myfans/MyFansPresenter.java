package com.sillykid.app.mine.myfans;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class MyFansPresenter implements MyFansContract.Presenter {

    private MyFansContract.View mView;

    public MyFansPresenter(MyFansContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMyFansList(int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("page", page);
        httpParams.put("pageSize", 10);
        RequestClient.getMyFansList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postAddConcern(int user_id, int type_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("user_id", user_id);
        httpParams.put("type_id", type_id);
        RequestClient.postAddConcern(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
