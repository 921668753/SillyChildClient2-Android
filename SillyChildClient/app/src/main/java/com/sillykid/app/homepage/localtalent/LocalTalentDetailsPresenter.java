package com.sillykid.app.homepage.localtalent;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */
public class LocalTalentDetailsPresenter implements LocalTalentDetailsContract.Presenter {

    private LocalTalentDetailsContract.View mView;

    public LocalTalentDetailsPresenter(LocalTalentDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getLocalTalentDetails(String talent_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getLocalTalentDetails(httpParams, talent_id, new ResponseListener<String>() {
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
    public void postPraise(String talent_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("id", talent_id);
        RequestClient.postLocalTalentPraise(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }
}
