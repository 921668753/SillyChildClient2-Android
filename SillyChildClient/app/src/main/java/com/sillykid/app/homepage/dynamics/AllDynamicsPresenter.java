package com.sillykid.app.homepage.dynamics;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class AllDynamicsPresenter implements AllDynamicsContract.Presenter {
    private AllDynamicsContract.View mView;

    public AllDynamicsPresenter(AllDynamicsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getAllDynamics(int page, String sort_field, String sort_type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("p", page);
        httpParams.put("pageSize", 5);
        httpParams.put("sort_field", sort_field);
//        httpParams.put("sort_type", time_type);
//        httpParams.put("sort_field", "praise");
        httpParams.put("sort_type", sort_type);
        RequestClient.getAllDynamics(httpParams, new ResponseListener<String>() {
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
