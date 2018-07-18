package com.sillykid.app.mine.vipemergencycall;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class VipEmergencyCallPresenter implements VipEmergencyCallContract.Presenter {
    private VipEmergencyCallContract.View mView;

    public VipEmergencyCallPresenter(VipEmergencyCallContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getVIPServicePhone() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getVIPServicePhoneHttp(httpParams, new ResponseListener<String>() {
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
