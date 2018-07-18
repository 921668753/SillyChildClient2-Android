package com.sillykid.app.mine.myorder.goodorder.aftersalesdetails;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class AfterSalesDetailsPresenter implements AfterSalesDetailsContract.Presenter {

    private AfterSalesDetailsContract.View mView;

    public AfterSalesDetailsPresenter(AfterSalesDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getSellBackDetail(String order_item_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("order_item_id", order_item_id);
        RequestClient.getSellBackDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
