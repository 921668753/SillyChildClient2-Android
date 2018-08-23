package com.sillykid.app.homepage.bythedaycharter;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class SelectProductPresenter implements SelectProductContract.Presenter {
    private SelectProductContract.View mView;

    public SelectProductPresenter(SelectProductContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getProductByRegion(int region_id, int category) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("region_id", region_id);
        httpParams.put("category", category);
        RequestClient.getProductByRegion(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
