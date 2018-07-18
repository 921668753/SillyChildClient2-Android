package com.sillykid.app.dialog.gastronomy;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class NearBouncedPresenter implements NearBouncedContract.Presenter {

    private NearBouncedContract.View mView;

    public NearBouncedPresenter(NearBouncedContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getNearBounced() {

//        if (StringUtils.isEmpty(pwd)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText), 0);
//            return;
//        }
//        if (pwd.length() < 6 || pwd.length() > 20) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText1), 0);
//            return;
//        }

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        //   Map<String, Object> map = new HashMap<String, Object>();
        //    httpParams.put("username", phone);
        //   httpParams.put("password", CipherUtils.md5("TPSHOP" + pwd));
        httpParams.put("wxOpenid", "");
        // httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
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
    public void getNear1Bounced() {

//        if (StringUtils.isEmpty(pwd)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText), 0);
//            return;
//        }
//        if (pwd.length() < 6 || pwd.length() > 20) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText1), 0);
//            return;
//        }

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        //   Map<String, Object> map = new HashMap<String, Object>();
        //    httpParams.put("username", phone);
        //   httpParams.put("password", CipherUtils.md5("TPSHOP" + pwd));
        httpParams.put("wxOpenid", "");
        // httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
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




}
