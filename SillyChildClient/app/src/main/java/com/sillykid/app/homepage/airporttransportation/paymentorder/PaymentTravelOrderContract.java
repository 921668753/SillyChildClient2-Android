package com.sillykid.app.homepage.airporttransportation.paymentorder;


import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface PaymentTravelOrderContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取钱包余额
         */
        void getMyWallet();

        /**
         * 订单支付信息接口
         */
        void getOnlinePay(int order_id, String pay_type);
    }

    interface View extends BaseView<Presenter, String> {

    }

}
