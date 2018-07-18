package com.sillykid.app.homepage.localtalent;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class LocalTalentPresenter implements LocalTalentContract.Presenter {
    private LocalTalentContract.View mView;

    public LocalTalentPresenter(LocalTalentContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getLocalTalent(int page, String city) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (city.equals(KJActivityStack.create().bottomActivity().getString(R.string.allAeservationNumber))) {
            city = "";
        }
        RequestClient.getLocalTalent(httpParams, page, city, new ResponseListener<String>() {
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
