package com.sillykid.app.loginregister;

import android.net.Uri;
import android.util.Log;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.qiniu.android.utils.UrlSafeBase64;
import com.sillykid.app.BuildConfig;
import com.sillykid.app.R;
import com.sillykid.app.entity.BaiDuInfo;
import com.sillykid.app.entity.BaiDuInfoList;
import com.sillykid.app.entity.application.RongCloudBean;
import com.sillykid.app.entity.loginregister.LoginBean;
import com.sillykid.app.message.interactivemessage.imuitl.UserInfoEngine;
import com.sillykid.app.message.interactivemessage.imuitl.UserUtil;
import com.sillykid.app.retrofit.RequestClient;

import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

import static com.sillykid.app.constant.StringNewConstants.BAIDUTABID;


/**
 * Created by ruitu on 2016/9/24.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postToLogin(String phone, String pwd) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintAccountText), 0);
            return;
        }
        if (phone.length() != 11) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.inputPhone), 0);
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText), 0);
            return;
        }
        if (pwd.length() < 6 || pwd.length() > 20) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText1), 0);
            return;
        }

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("phone", phone);
        httpParams.put("password", pwd);
        httpParams.put("registration_id", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
        RequestClient.postLogin(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void loginRongYun(LoginBean bean) {
        RongIM.getInstance().logout();
        if (!StringUtils.isEmpty(bean.getData().getRong_cloud())) {
            RongIM.connect(bean.getData().getRong_cloud(), new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), 1);
                        }
                    });
                }

                /**
                 * 连接融云成功
                 *
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.i("XJ", "application--RongIM.connect--onSuccess" + userid);
                    /**
                     * 获取用户信息
                     */
                    UserUtil.saveRcTokenId(KJActivityStack.create().topActivity(), bean.getData().getRong_cloud(), userid);
                    getRongYunUserInfo(userid);
                }

                /**
                 * 连接融云失败
                 *
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.i("XJ", "--errorCode" + errorCode);
                    mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), 1);
                }
            });
        } else {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), 1);
        }
    }

    private void getRongYunUserInfo(String userid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("userId", userid);
        RequestClient.getRongCloud(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                RongCloudBean rongCloudBean = (RongCloudBean) JsonUtil.json2Obj(response, RongCloudBean.class);
                if (RongIM.getInstance() != null && rongCloudBean.getData() != null && !StringUtils.isEmpty(rongCloudBean.getData().getNickname())) {
                    UserInfo userInfo = new UserInfo(userid, rongCloudBean.getData().getNickname(), Uri.parse(rongCloudBean.getData().getFace()));
                    RongIM.getInstance().setCurrentUserInfo(userInfo);
                    RongIM.getInstance().setMessageAttachedUserInfo(true);
                    mView.getSuccess("", 1);
                    return;
//                    String baiDuId = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "baiDuId", "");
//                    if (StringUtils.isEmpty(baiDuId)) {
//                        postBaiDuInfo();
//                        return;
//                    }
//                    postBaiDuUpdateInfo();
                }
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.loginErr1), 1);
            }

            @Override
            public void onFailure(String msg) {
                Log.d("RongYun", "onFailure");
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), 1);
            }
        });
    }


    @Override
    public void postThirdToLogin(String openid, String from, String nickname, String head_pic, int sex) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("open_id", openid);
        httpParams.put("type", from);
        httpParams.put("nickname", nickname);
        httpParams.put("face", head_pic);
        httpParams.put("sex", sex);
        httpParams.put("registration_id", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
        RequestClient.postThirdLogin(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });

    }


    /**
     * 百度定位
     */
    private void postBaiDuInfo() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        String mobile = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "mobile", "");
        httpParams.put("title", mobile);
        httpParams.put("tags", "0");
        httpParams.put("coord_type", 3);
        httpParams.put("page_size", 1);
        httpParams.put("geotable_id", BAIDUTABID);
        httpParams.put("ak", BuildConfig.BAIDU_APPKEY);
        RequestClient.postBaiDuGetdateInfo(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                BaiDuInfoList baiDuInfoList = (BaiDuInfoList) JsonUtil.getInstance().json2Obj(response, BaiDuInfoList.class);
                if (baiDuInfoList.getSize() > 0) {
                    PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "baiDuId", baiDuInfoList.getPois().get(0).getId());
                    postBaiDuUpdateInfo();
                    return;
                }
                mView.getSuccess("", 1);
            }

            @Override
            public void onFailure(String msg) {
                Log.d("BaiDuYun", "onFailure");
                mView.getSuccess("", 1);
                //  mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), 1);
            }
        });
    }

    private void postBaiDuUpdateInfo() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        String baiDuId = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "baiDuId", "");
        httpParams.put("id", baiDuId);
        String mobile = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "mobile", "");
        httpParams.put("title", mobile);
        String latitude = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "latitude", "");
        String longitude = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "longitude", "");
        String locationAddress = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "locationAddress", "");
        httpParams.put("latitude", latitude);
        httpParams.put("longitude", longitude);
        httpParams.put("locationAddress", locationAddress);
        httpParams.put("tags", "0");
        httpParams.put("coord_type", 3);
        httpParams.put("geotable_id", BAIDUTABID);
        httpParams.put("ak", BuildConfig.BAIDU_APPKEY);
        RequestClient.postBaiDuUpdateInfo(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                BaiDuInfo baiDuInfo = (BaiDuInfo) JsonUtil.getInstance().json2Obj(response, BaiDuInfo.class);
                PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "baiDuId", baiDuInfo.getId());
                mView.getSuccess("", 1);
            }

            @Override
            public void onFailure(String msg) {
                Log.d("BaiDuYun", "onFailure");
                mView.getSuccess("", 1);
                //  mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), 1);
            }
        });
    }
}
