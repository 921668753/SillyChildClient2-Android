package com.sillykid.app.mine.myorder.charterorder;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.sillykid.app.mine.myorder.MyOrderActivity;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CharterOrderContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取用户信息
         */
        void getChartOrder(String type, int page);

        /**
         * 打电话
         */
        void CallPhone(MyOrderActivity aty, String phone);

        /**
         * 确认付款
         */
        void toPay(MyOrderActivity aty, String orderid, String paymoney, String paymoneyfmt);

        /**
         * 发私聊
         */
        void toChart(MyOrderActivity aty, String hxusername, String nickname, String defaultnickname, String avatar);

        /**
         * 去评价
         */
        void toEvaluate(MyOrderActivity aty, String air_id, int type, String line_id, String seller_id);

        /**
         * 查看订单详情
         */
        void toDetails(MyOrderActivity aty, String air_id);
        /**
         * 查看评价
         */
        void toSeeEvaluate(MyOrderActivity aty, String air_id);
        /**
         * 确认结束订单
         */
        void orderConfirmCompleted(MyOrderActivity aty,String id,int flag);
        /**
         * 获取订单列表的数量统计信息
         */
        void getOrderAround();
        /**
         * 删除未付款的订单
         */
        void delPackOrder(String air_id);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


