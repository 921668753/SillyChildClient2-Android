package com.sillykid.app.mine.myrelease.dynamicstate;

import android.text.TextUtils;

import com.common.cklibrary.common.KJActivity;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public class DynamicStatePresenter implements DynamicStateContract.Presenter {
    private DynamicStateContract.View mView;

    public DynamicStatePresenter(DynamicStateContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getDynamicList(int p,int pageSize) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("m","Api");
        httpParams.put("c","User");
        httpParams.put("a","dynamic");
        httpParams.put("p",p);
        httpParams.put("pageSize",pageSize);
        RequestClient.getDynamics(httpParams, new ResponseListener<String>() {
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
    public void pulishDynamic(String img,String title,String content,int flag) {
        if (TextUtils.isEmpty(img)||TextUtils.isEmpty(title)||TextUtils.isEmpty(content)||TextUtils.isEmpty(img.substring(1))){
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.fillPrompt),0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("img",img.substring(1));
        httpParams.put("title",title);
        httpParams.put("content",content);
        RequestClient.postDynamic(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, flag);
            }
        });
    }

    @Override
    public void upPictures(String paramname, File voule, int resultsource) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put(paramname,voule);
//        RequestClient.upLoadImg(KJActivityStack.create().topActivity(),httpParams,0, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, resultsource);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, resultsource);
//            }
//        });
    }

    @Override
    public void doDelete(String id,int resultsource) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.deleteDynamicState(httpParams,id, new ResponseListener<String>() {
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
