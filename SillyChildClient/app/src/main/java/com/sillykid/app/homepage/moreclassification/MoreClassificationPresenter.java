package com.sillykid.app.homepage.moreclassification;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.homepage.moreclassification.MoreClassificationContract;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class MoreClassificationPresenter implements MoreClassificationContract.Presenter {
    private MoreClassificationContract.View mView;

    public MoreClassificationPresenter(MoreClassificationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getClassification(int cat_id, int flag) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("cat_id", cat_id);
        RequestClient.getClassification(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, flag);
            }
        });
    }
}
