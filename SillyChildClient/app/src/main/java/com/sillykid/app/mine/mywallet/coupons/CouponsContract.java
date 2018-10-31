package com.sillykid.app.mine.mywallet.coupons;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CouponsContract {
    interface Presenter extends BasePresenter {
        /**
         * 优惠券 - 未使用优惠券
         */
        void getMemberUnusedCoupon(Context context, int page);

        /**
         * 优惠券 - 未使用优惠券
         */
        void getUseAbleCoupon(Context context, int business_id);

        /**
         * 优惠券 - 已使用优惠券
         */
        void getMemberUsedCoupon(Context context, int page);

        /**
         * 优惠券 - 已过期优惠券
         */
        void getMemberExpiredCoupon(Context context, int page);

        /**
         * 获取会员登录状态
         */
        void getIsLogin(Context context, int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


