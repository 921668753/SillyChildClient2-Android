package com.sillykid.app.homepage.chartercustom.personaltailor;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class CustomizationConfirmOrderPresenter implements CustomizationConfirmOrderContract.Presenter {
    private CustomizationConfirmOrderContract.View mView;

    public CustomizationConfirmOrderPresenter(CustomizationConfirmOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postSaveUserPrivate(String air_id, String customer_name, String customer_phone, String user_passport, String user_identity, String bags, String bags1, String bags2, String bags3, String remark) {

        if (StringUtils.isEmpty(customer_name)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseFillYourName), 0);
            return;
        }

        if (StringUtils.isEmpty(customer_phone)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseFillContactWay), 0);
            return;
        }

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("air_id", air_id);
        httpParams.put("customer_name", customer_name);
        httpParams.put("customer_phone", customer_phone);
        httpParams.put("user_passport", user_passport);
        httpParams.put("user_identity", user_identity);
        httpParams.put("remark", remark);
        httpParams.put("twenty_four", bags3);
        httpParams.put("twenty_six", bags2);
        httpParams.put("twenty_eight", bags1);
        httpParams.put("thirty", bags);
        RequestClient.postSaveUserPrivate(httpParams, new ResponseListener<String>() {
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
