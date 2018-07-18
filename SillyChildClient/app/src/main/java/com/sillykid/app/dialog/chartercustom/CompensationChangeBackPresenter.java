package com.sillykid.app.dialog.chartercustom;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class CompensationChangeBackPresenter implements CompensationChangeBackContract.Presenter {
    private CompensationChangeBackContract.View mView;

    public CompensationChangeBackPresenter(CompensationChangeBackContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getCompensationChangeBack(int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getCompensationChangeBack(httpParams, id, new ResponseListener<String>() {
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
