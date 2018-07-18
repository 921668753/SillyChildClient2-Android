package com.sillykid.app.mine.personaldata.changepassword;

import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.CipherUtils;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;
import com.sillykid.app.utils.AccountValidatorUtil;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ruitu on 2016/9/24.
 */

public class ChangePassWordPresenter implements ChangePassWordContract.Presenter {
    private ChangePassWordContract.View mView;

    public ChangePassWordPresenter(ChangePassWordContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getCode(Context aty, String phone, String opt) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText), 0);
            return;
        }
        if (phone.contains("@")){
            if (!AccountValidatorUtil.isEmail(phone)) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText1), 0);
                return;
            }
            HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
            httpParams.put("mail", phone);
            httpParams.put("opt", opt);
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
        }else{
            if (phone.length() != 11) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText1), 0);
                return;
            }
            HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
            httpParams.put("mobile", PreferenceHelper.readString(aty, StringConstants.FILENAME, "countroy_code")+phone);
            httpParams.put("mobile", PreferenceHelper.readString(aty, StringConstants.FILENAME, "countroy_code")+phone);
            String codeI = String.valueOf(System.currentTimeMillis());
            String codeId = CipherUtils.md5(codeI.substring(2, codeI.length() - 1));
            httpParams.put("codeId", codeId);
            String validationI = phone.substring(1, phone.length() - 1) + codeId.substring(3, codeId.length() - 1);
            String validationId = CipherUtils.md5(validationI);
            httpParams.put("validationId", validationId);
            httpParams.put("opt", opt);
//            RequestClient.postCaptcha(httpParams, new ResponseListener<String>() {
//                @Override
//                public void onSuccess(String response) {
//                    mView.getSuccess(response, 0);
//                }
//
//                @Override
//                public void onFailure(String msg) {
//                    mView.errorMsg(msg, 0);
//                }
//            });
        }
    }

    @Override
    public void changePassWord(String phone,String code,String pwd, String pwd1) {
        if (StringUtils.isEmpty(pwd)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText), 1);
            return;
        }
        if (StringUtils.isEmpty(pwd1)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText), 1);
            return;
        }
        if (pwd.length() < 6 || pwd.length() > 20) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText1), 1);
            return;
        }
        if (!pwd.equals(pwd1)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.passwordMismatch), 1);
            return;
        }

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        //  Map<String, Object> map = new HashMap<String, Object>();
        httpParams.put("mobile", phone);
        httpParams.put("code", code);
        httpParams.put("password", CipherUtils.md5("TPSHOP" + pwd));
        //  httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.postResetpwd(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 1);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 1);
//            }
//        });
    }

//    @Override
//    public void relogin(String phone, String pwd) {
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        //   Map<String, Object> map = new HashMap<String, Object>();
//        httpParams.put("username", phone);
//        httpParams.put("password", CipherUtils.md5("TPSHOP" + pwd));
//        httpParams.put("wxOpenid", "");
//        httpParams.put("pushToken", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
//        // httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.postLogin(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 2);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 2);
//            }
//        });
//    }
}
