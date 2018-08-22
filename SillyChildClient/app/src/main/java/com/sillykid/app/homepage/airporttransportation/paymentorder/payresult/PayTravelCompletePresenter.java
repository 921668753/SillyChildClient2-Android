package com.sillykid.app.homepage.airporttransportation.paymentorder.payresult;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by Administrator on 2018/6/15.
 */

public class PayTravelCompletePresenter implements PayTravelCompleteContract.Presenter {

    private PayTravelCompleteContract.View mView;

    public PayTravelCompletePresenter(PayTravelCompleteContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

//    /**
//     * 获取购物车列表
//     */
//    @Override
//    public void getOrderSimple(int order_id) {
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        httpParams.put("order_id", order_id);
//        RequestClient.getOrderSimple(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
//    }

}
