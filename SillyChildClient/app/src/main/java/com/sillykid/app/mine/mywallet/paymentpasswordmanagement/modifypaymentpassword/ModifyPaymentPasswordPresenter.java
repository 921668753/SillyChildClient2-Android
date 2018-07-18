package com.sillykid.app.mine.mywallet.paymentpasswordmanagement.modifypaymentpassword;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.kymjs.common.CipherUtils;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/15.
 */

public class ModifyPaymentPasswordPresenter implements ModifyPaymentPasswordContract.Presenter {

    private ModifyPaymentPasswordContract.View mView;

    public ModifyPaymentPasswordPresenter(ModifyPaymentPasswordContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postCode(String phone, String type) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintAccountText), 0);
            return;
        }
        if (phone.length() != 11) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.inputPhone), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", phone);
        String codeI = String.valueOf(System.currentTimeMillis());
        String codeId = CipherUtils.md5(codeI.substring(2, codeI.length() - 1));
        map.put("codeId", codeId);
        String validationI = phone.substring(1, phone.length() - 1) + codeId.substring(3, codeId.length() - 1);
        String validationId = CipherUtils.md5(validationI);
        map.put("validationId", validationId);
        map.put("opt", type);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.postCaptcha(httpParams, new ResponseListener<String>() {
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
    public void postVerificationCode(String phone, String code) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintAccountText), 0);
            return;
        }
        if (phone.length() != 11) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.inputPhone), 0);
            return;
        }
        if (StringUtils.isEmpty(code)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.errorCode), 0);
            return;
        }
        if (code.length() < 4 || code.length() > 6) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.enterCorrectVerificationCode), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", phone);
        map.put("captcha", code);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.getCheckPayPassword(httpParams, new ResponseListener<String>() {
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


    @Override
    public void postVerifyIdNumber(String idNumber) {
        if (StringUtils.isEmpty(idNumber)) {
       //     mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseFillOut) + MyApplication.getContext().getString(R.string.IdNumber), 0);
            return;
        }
        if (idNumber.length() != 15 && idNumber.length() != 18) {
        //    mView.errorMsg(MyApplication.getContext().getString(R.string.hintIDerrorText), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("identity", idNumber);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.getCheckPayPassword(httpParams, new ResponseListener<String>() {
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
    public void postOldPayPassword(String oldPaymentPassword) {
        if (oldPaymentPassword.length() != 6) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseEnterPaymentPassword1), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("old_password", CipherUtils.md5("WUZAI" + oldPaymentPassword + "TIANXIA"));
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.getCheckPayPassword(httpParams, new ResponseListener<String>() {
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
    public void postModifyPaymentPassword(String newPaymentPassword, String paymentPassword) {
        if (paymentPassword.length() != 6) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseEnterPaymentPassword1), 0);
            return;
        }
        if (!newPaymentPassword.equals(paymentPassword)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.paymentPasswordsNotMatch), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", 1);
        map.put("pay_password", CipherUtils.md5("WUZAI" + paymentPassword + "TIANXIA"));
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.postSetPayPassword(httpParams, new ResponseListener<String>() {
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