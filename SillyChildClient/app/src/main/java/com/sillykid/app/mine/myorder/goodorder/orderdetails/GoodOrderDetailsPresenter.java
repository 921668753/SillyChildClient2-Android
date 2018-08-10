package com.sillykid.app.mine.myorder.goodorder.orderdetails;

import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class GoodOrderDetailsPresenter implements GoodOrderDetailsContract.Presenter {
    private GoodOrderDetailsContract.View mView;

    public GoodOrderDetailsPresenter(GoodOrderDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getOrderDetails(int orderId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderid", orderId);
        RequestClient.getOrderDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postOrderCancel(Context context, int orderid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderid", orderid);
        RequestClient.postOrderCancel(context, httpParams, new ResponseListener<String>() {
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

    @Override
    public void postOrderRemind(Context context, int orderid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("order_id", orderid);
        RequestClient.postOrderRemind(context, httpParams, new ResponseListener<String>() {
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

//    @Override
//    public void getMyWallet(Context context) {
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getMyWallet(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                MyWalletBean myWalletBean = (MyWalletBean) JsonUtil.getInstance().json2Obj(response, MyWalletBean.class);
//                if (!StringUtils.isEmpty(myWalletBean.getData().getBalance())) {
//                    PreferenceHelper.write(context, StringConstants.FILENAME, "balance", MathUtil.keepTwo(StringUtils.toDouble(myWalletBean.getData().getBalance())));
//                    mView.getSuccess("", 3);
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 3);
//            }
//        });
//    }

    @Override
    public void postOrderConfirm(Context context, int orderid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderid", orderid);
        RequestClient.postOrderConfirm(context, httpParams, new ResponseListener<String>() {
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
}
