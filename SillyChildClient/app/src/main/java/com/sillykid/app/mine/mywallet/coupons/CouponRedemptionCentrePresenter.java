package com.sillykid.app.mine.mywallet.coupons;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/9/24.
 */

public class CouponRedemptionCentrePresenter implements CouponRedemptionCentreContract.Presenter {

    private CouponRedemptionCentreContract.View mView;

    public CouponRedemptionCentrePresenter(CouponRedemptionCentreContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMemberCoupon(Context context) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getMemberCoupon(context, httpParams, new ResponseListener<String>() {
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
    public void getCoupon(Context context, int coupon_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("coupon_id", coupon_id);
        RequestClient.getCoupon(context, httpParams, new ResponseListener<String>() {
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
