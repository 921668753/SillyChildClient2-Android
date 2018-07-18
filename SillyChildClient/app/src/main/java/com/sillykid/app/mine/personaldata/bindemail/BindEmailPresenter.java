package com.sillykid.app.mine.personaldata.bindemail;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.CipherUtils;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class BindEmailPresenter implements BindEmailContract.Presenter {
    private BindEmailContract.View mView;

    public BindEmailPresenter(BindEmailContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postCode(String email,String posttype) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("mail", email);
        httpParams.put("opt", posttype);
        RequestClient.postEmailCaptcha(httpParams, new ResponseListener<String>() {
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
    public void bindEmail(String email, String code) {
        if (StringUtils.isEmpty(code)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.errorCode), 1);
            return;
        }

        if (StringUtils.isEmpty(email)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText), 1);
            return;
        }

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("mail", email);
        httpParams.put("code", code);
        RequestClient.postChangeEmail(httpParams, new ResponseListener<String>() {
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
