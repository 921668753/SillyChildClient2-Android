package com.sillykid.app.homepage.airporttransportation.airportpickup;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AirportPickupPayOrderContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取订单信息
         */
        void getTravelOrderDetail(int requirement_id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


