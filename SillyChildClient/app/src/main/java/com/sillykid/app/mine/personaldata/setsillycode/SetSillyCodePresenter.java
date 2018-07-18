package com.sillykid.app.mine.personaldata.setsillycode;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class SetSillyCodePresenter implements SetSillyCodeContract.Presenter {
    private SetSillyCodeContract.View mView;

    public SetSillyCodePresenter(SetSillyCodeContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void changeShzCode(String shz_code) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("shz_code", shz_code);
        RequestClient.changeShzCode(httpParams, new ResponseListener<String>() {
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
