package com.sillykid.app.mine.myorder.charterorder.orderdetails;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2018/9/24.
 */

public interface CharterOrderDetailsContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取包车订单详情
         */
        void getCharterOrderDetails(String order_number);

        /**
         * 打电话
         */
        void CallPhone(CharterOrderDetailsActivity aty, String phone);

        /**
         * 确认付款
         */
        void toPay(CharterOrderDetailsActivity aty, String orderid, String paymoney, String paymoneyfmt);

        /**
         * 发私聊
         */
        void toChart(CharterOrderDetailsActivity aty, String hxusername, String nickname, String defaultnickname, String avatar);

        /**
         * 去评价
         */
        void toEvaluate(CharterOrderDetailsActivity aty, String air_id, int type, String line_id, String seller_id);
        /**
         * 查看线路详情
         */
        void toRouteDetail(CharterOrderDetailsActivity aty, String line_id);
        /**
         * 查看评价
         */
        void toSeeEvaluate(CharterOrderDetailsActivity aty, String air_id);
        /**
         * 确认结束订单
         */
        void orderConfirmCompleted(CharterOrderDetailsActivity aty,String id,int flag);
        /**
         * 删除未付款的订单
         */
        void delPackOrder(String air_id);
        /**
         * 费用说明和退改政策
         */
        void articleInfo (int type,int flag);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


