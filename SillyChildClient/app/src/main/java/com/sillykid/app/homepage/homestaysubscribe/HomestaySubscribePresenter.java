package com.sillykid.app.homepage.homestaysubscribe;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ruitu on 2016/9/24.
 */

public class HomestaySubscribePresenter implements HomestaySubscribeContract.Presenter {
    private HomestaySubscribeContract.View mView;

    public HomestaySubscribePresenter(HomestaySubscribeContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getHomestaySubscribe() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("account", phone);
//        map.put("password", CipherUtils.md5("RUITU" + pwd + "KEJI"));
        map.put("wxOpenid", "");
        map.put("pushToken", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.postLogin(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 0);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }

    @Override
    public void getCarInfo() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getCarInfo(httpParams, new ResponseListener<String>() {
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
}
