package com.sillykid.app.community;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */
public class DisplayPagePresenter implements DisplayPageContract.Presenter {

    private DisplayPageContract.View mView;

    public DisplayPagePresenter(DisplayPageContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getOtherUserInfo(int member_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("member_id", member_id);
        RequestClient.getOtherUserInfo(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void getOtherUserPost(int member_id, int pageno) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("member_id", member_id);
        httpParams.put("pageno", pageno);
        httpParams.put("pagesize", 10);
        RequestClient.getOtherUserPost(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
    }

    @Override
    public void postAddConcern(int user_id, int type_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("member_id", user_id);
        httpParams.put("type_id", type_id);
        RequestClient.postAddConcern(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 2);
            }
        });
    }

}
