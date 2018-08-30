package com.sillykid.app.homepage.airporttransportation.search;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */

public class ProductSearchListPresenter implements ProductSearchListContract.Presenter {

    private ProductSearchListContract.View mView;

    public ProductSearchListPresenter(ProductSearchListContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getProductByAirportId(String name, int category) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("name", name);
        httpParams.put("category", category);
        RequestClient.postProductByType(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
