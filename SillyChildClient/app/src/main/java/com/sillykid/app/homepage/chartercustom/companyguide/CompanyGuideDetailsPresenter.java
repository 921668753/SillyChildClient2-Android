package com.sillykid.app.homepage.chartercustom.companyguide;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class CompanyGuideDetailsPresenter implements CompanyGuideDetailsContract.Presenter {
    private CompanyGuideDetailsContract.View mView;

    public CompanyGuideDetailsPresenter(CompanyGuideDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getCompanyGuideDetails(String drv_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getCompanyGuideDetails(httpParams, drv_id, new ResponseListener<String>() {
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
