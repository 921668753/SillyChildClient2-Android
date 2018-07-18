package com.sillykid.app.mine.myshoppingcart.makesureorder;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by Administrator on 2018/5/15.
 */

public class MakeSureOrderPresenter implements MakeSureOrderContract.Presenter {


    private MakeSureOrderContract.View mView;

    public MakeSureOrderPresenter(MakeSureOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    /**
     * 获取购物车列表
     */
    @Override
    public void getCartBalance(String cartIds) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("cartIds", cartIds);
        RequestClient.getCartBalance(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postCreateOrder(int addressId, int bonusid, String cartIds) {
        if (addressId <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectAddress), 1);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("paymentId", 0);
        httpParams.put("addressId", addressId);
        if (bonusid != 0) {
            httpParams.put("bonusid", bonusid);
        }
        RequestClient.postCreateOrder(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
