package com.sillykid.app.message;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class ShopMessagePresenter implements ShopMessageContract.Presenter {

    private ShopMessageContract.View mView;

    public ShopMessagePresenter(ShopMessageContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getShopMessage() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type", "shop");
        httpParams.put("page", 1);
        httpParams.put("pageSize", 10000);
        RequestClient.getHxUserList(httpParams, new ResponseListener<String>() {
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
    public void isLogin() {
//        RequestClient.isLogin(new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 1);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 1);
//            }
//        });
    }
}
