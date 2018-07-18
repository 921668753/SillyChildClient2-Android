package com.sillykid.app.mine.myrelease.collectionstrate;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * 收藏的动态
 * Created by ruitu on 2016/9/24.
 */

public class CollectionStratePresenter implements CollectionStrateContract.Presenter {
    private CollectionStrateContract.View mView;

    public CollectionStratePresenter(CollectionStrateContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getCollectionStrateList(int p,int pageSize) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("m","Api");
        httpParams.put("c","User");
        httpParams.put("a","collectStrategy");
        httpParams.put("p",p);
        httpParams.put("pageSize",pageSize);
        RequestClient.getStrates(httpParams, new ResponseListener<String>() {
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
    public void doDelete(String id,int resultsource) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.deleteCollectionStrate(httpParams,id, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, resultsource);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, resultsource);
            }
        });
    }

    @Override
    public void getInfo() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getInfo(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 2);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }


}
