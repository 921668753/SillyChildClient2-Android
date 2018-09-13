package com.sillykid.app.activity;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */
public class PastWonderfulPresenter implements PastWonderfulContract.Presenter {

    private PastWonderfulContract.View mView;

    public PastWonderfulPresenter(PastWonderfulContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getAllActivity(int pageno) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("pageno", pageno);
        httpParams.put("pagesize", 5);
        RequestClient.getAllActivity(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
}
