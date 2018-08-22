package com.sillykid.app.homepage.airporttransportation.paymentorder;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface PaymentOrderContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取订单详情
         */
        void getOrderDetails(String orderId);

        /**
         * 订单支付信息接口
         */
        void getOnlinePay(String order_id, String pay_type);
    }

    interface View extends BaseView<Presenter, String> {

    }

}
