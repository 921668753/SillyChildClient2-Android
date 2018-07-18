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
         * 获取优惠券列表
         */
        void getCoupons(Context context, int type, int page);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


