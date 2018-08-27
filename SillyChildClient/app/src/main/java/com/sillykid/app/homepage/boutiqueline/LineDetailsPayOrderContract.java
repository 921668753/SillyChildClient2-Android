package com.sillykid.app.homepage.boutiqueline;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

public class LineDetailsPayOrderContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取订单信息
         */
        void getTravelOrderDetail(int requirement_id);

        /**
         * 创建订单
         */
        void createTravelOrder(int product_id, String order_number, int bonus_id);

    }

    interface View extends BaseView<Presenter, String> {
    }
}
