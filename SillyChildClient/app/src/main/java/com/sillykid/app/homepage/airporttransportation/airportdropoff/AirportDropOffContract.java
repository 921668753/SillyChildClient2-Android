package com.sillykid.app.homepage.airporttransportation.airportdropoff;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AirportDropOffContract {

    interface Presenter extends BasePresenter {

        /**
         * 提交订单信息
         */
        void postAddRequirements(int product_id, String delivery_location, String flight_arrival_time, String contact, String contact_number,
                                 String audit_number, String children_number, String baggage_number, String remarks, int passenger_number1, int baggage_number1);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


