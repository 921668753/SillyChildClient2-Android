package com.sillykid.app.mine.myshoppingcart.makesureorder;


import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.sillykid.app.entity.mine.myorder.GoodOrderBean.DataBean.ResultBean;

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
