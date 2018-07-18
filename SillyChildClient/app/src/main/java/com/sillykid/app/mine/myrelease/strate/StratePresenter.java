package com.sillykid.app.mine.myrelease.strate;

import android.text.TextUtils;

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

public class StratePresenter implements StrateContract.Presenter {
    private StrateContract.View mView;

    public StratePresenter(StrateContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getStrateList(int p,int pageSize) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("m","Api");
        httpParams.put("c","User");
        httpParams.put("a","strategy");
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
    public void pulishStrate(String title,String countryid,String regionId,String city,String summary,String content,int flag) {
        if (TextUtils.isEmpty(title)||TextUtils.isEmpty(countryid)
                ||TextUtils.isEmpty(regionId)||TextUtils.isEmpty(summary)||TextUtils.isEmpty(content)){
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.fillPrompt),0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("title",title);
        httpParams.put("content",content);//内容
        httpParams.put("countryId",countryid);//国家，数字
        httpParams.put("cityId",regionId);//地区，数字
        httpParams.put("city",city);//地区，数字
        httpParams.put("summary",summary);//简介
        RequestClient.postStrate(httpParams, new ResponseListener<String>() {
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
//        RequestClient.upLoadImg(httpParams,0, new ResponseListener<String>() {
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
        RequestClient.deleteStrate(httpParams,id, new ResponseListener<String>() {
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
