package com.sillykid.app.mine.fansattention;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.retrofit.RequestClient;

/**
 * 收藏的动态
 * Created by ruitu on 2016/9/24.
 */

public class FansAttentionPresenter implements FansAttentionContract.Presenter {
    private FansAttentionContract.View mView;

    public FansAttentionPresenter(FansAttentionContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getAttentionMeList(int p, int pageSize,String userId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("m","Api");
        httpParams.put("c","UserInfo");
        httpParams.put("a","getAttentionMeList");
        httpParams.put("p",p);
        httpParams.put("pageSize",pageSize);
        httpParams.put("user_id",userId);
        RequestClient.getAttentionMeListHttp(httpParams, new ResponseListener<String>() {
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
    public void getAttentionList(int p, int pageSize,String userId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("m","Api");
        httpParams.put("c","UserInfo");
        httpParams.put("a","getAttentionList");
        httpParams.put("p",p);
        httpParams.put("pageSize",pageSize);
        httpParams.put("user_id",userId);
        RequestClient.getAttentionListHttp(httpParams, new ResponseListener<String>() {
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
    public void baseInfo(String userId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("m","Api");
        httpParams.put("c","UserInfo");
        httpParams.put("a","baseInfo");
        httpParams.put("userId",userId);
        RequestClient.baseInfoHttp(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 3);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 3);
            }
        });
    }

    @Override
    public void getOtherInfo(String userId, String type,int p) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("m","Api");
        httpParams.put("c","UserInfo");
        httpParams.put("a","getOtherInfo");
        httpParams.put("userId",userId);
        httpParams.put("type",type);
        httpParams.put("p",p);
        httpParams.put("pageSize", NumericConstants.LOADCOUNT);
        RequestClient.getOtherInfoHttp(httpParams, new ResponseListener<String>() {
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
    public void attentionOrNo(String userId,int status) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (status==0){
            RequestClient.getAttention(httpParams,userId,status, new ResponseListener<String>() {
                @Override
                public void onSuccess(String response) {
                    mView.getSuccess(response, 1);
                }

                @Override
                public void onFailure(String msg) {
                    mView.errorMsg(msg, 1);
                }
            });
        }else{
            RequestClient.getAttention(httpParams,userId,status, new ResponseListener<String>() {
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

}
