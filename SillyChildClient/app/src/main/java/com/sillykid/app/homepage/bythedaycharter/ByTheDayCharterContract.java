package com.sillykid.app.homepage.bythedaycharter;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ByTheDayCharterContract {

    interface Presenter extends BasePresenter {

        /**
         * 提交订单信息
         */
        void postAddCarRequirements(int product_id, long travel_start_time, long travel_end_time, long day,String place_of_departure, String delivery_location, String contact, String contact_number,
                                    String audit_number, String children_number, String baggage_number, String remarks, int passenger_number1, int baggage_number1);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


