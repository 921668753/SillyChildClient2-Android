package com.sillykid.app.mine.mywallet.coupons;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CouponRedemptionCentreContract {
    interface Presenter extends BasePresenter {

        /**
         * 优惠券 - 已过期优惠券
         */
        void getMemberCoupon(Context context);


        /**
         * 优惠券 - 已过期优惠券
         */
        void getCoupon(Context context,int coupon_id);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


