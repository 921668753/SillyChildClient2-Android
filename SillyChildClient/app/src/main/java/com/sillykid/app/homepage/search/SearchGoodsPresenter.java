package com.sillykid.app.homepage.search;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class SearchGoodsPresenter implements SearchGoodsContract.Presenter {
    private SearchGoodsContract.View mView;

    public SearchGoodsPresenter(SearchGoodsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getStrategy() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getHotStrategy(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 1);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }
}
