package com.sillykid.app.mine.myorder.goodorder.orderdetails;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.sillykid.app.entity.mine.myorder.GoodOrderBean;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface GoodOrderDetailsContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取订单详情
         */
        void getOrderDetails(int orderId);

        /**
         * 取消订单
         */
        void postOrderCancel(Context context, int orderid);

        /**
         * 提醒发货
         */
        void postOrderRemind(Context context, int orderid);

//        /**
//         * 获取钱包余额
//         */
//        void getMyWallet(Context context);

        /**
         * 确认收货
         */
        void postOrderConfirm(Context context, int orderid);


    }

    interface View extends BaseView<Presenter, String> {
    }

}


