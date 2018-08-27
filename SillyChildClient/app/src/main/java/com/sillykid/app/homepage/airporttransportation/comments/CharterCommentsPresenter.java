package com.sillykid.app.homepage.airporttransportation.comments;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class CharterCommentsPresenter implements CharterCommentsContract.Presenter {

    private CharterCommentsContract.View mView;

    public CharterCommentsPresenter(CharterCommentsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getCommentList(Context context, int goodsid, int onlyimage, int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("goodsid", goodsid);
        httpParams.put("onlyimage", onlyimage);
        httpParams.put("page", page);
        RequestClient.getCommentList(context, httpParams, new ResponseListener<String>() {
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
