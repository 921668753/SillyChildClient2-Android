package com.sillykid.app.homepage.goodslist.goodsdetails;

import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.message.interactivemessage.imuitl.UserUtil;
import com.sillykid.app.retrofit.RequestClient;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Created by ruitu on 2017/9/24.
 */

public class GoodsDetailsPresenter implements GoodsDetailsContract.Presenter {
    private GoodsDetailsContract.View mView;

    public GoodsDetailsPresenter(GoodsDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getGoodDetail(int goodsid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("goodsid", goodsid);
        RequestClient.getGoodDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postFavoriteAdd(int goodsid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("goodsid", goodsid);
        RequestClient.postFavoriteAdd(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void postUnfavorite(int goodsid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("goodsid", goodsid);
        RequestClient.postUnfavorite(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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

    @Override
    public void postOrderBuyNow(int goodId, int num, int product_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("productid", product_id);
        httpParams.put("num", num);
        RequestClient.postOrderBuyNow(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postAddCartGood(int goodsid, int num, int product_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("productid", product_id);
        httpParams.put("num", num);
        RequestClient.postAddCartGood(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 4);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 4);
            }
        });
    }

    @Override
    public void getIsLogin(Context context, int flag) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getIsLogin(context, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                if (flag != 5) {
                    mView.getSuccess(response, flag);
                    return;
                }
                loginRongYun(context, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
    }

    public void loginRongYun(Context context, int flag) {
        // 获取融云的Token
        String imToken = UserUtil.getResTokenInfo(context);
        if (!StringUtils.isEmpty(imToken)) {
            RongIM.connect(imToken, new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), flag);
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
                    mView.getSuccess("", flag);
                }

                /**
                 * 连接融云失败
                 *
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.i("XJ", "--errorCode" + errorCode);
                    mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), flag);
                }
            });
        } else {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), flag);
        }
    }

}
