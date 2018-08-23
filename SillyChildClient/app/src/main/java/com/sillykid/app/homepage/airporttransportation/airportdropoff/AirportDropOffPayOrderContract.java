package com.sillykid.app.homepage.airporttransportation.airportdropoff;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AirportDropOffPayOrderContract {

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


