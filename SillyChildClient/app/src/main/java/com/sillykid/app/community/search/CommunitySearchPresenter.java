package com.sillykid.app.community.search;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.kymjs.rxvolley.client.HttpParams;

/**
 * Created by ruitu on 2016/9/24.
 */

public class CommunitySearchPresenter implements CommunitySearchContract.Presenter {
    private CommunitySearchContract.View mView;

    public CommunitySearchPresenter(CommunitySearchContract.View view) {
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
