package com.sillykid.app.homepage.chartercustom.routes;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ConfirmOrderContract {
    interface Presenter extends BasePresenter {

        /**
         * 提交订单
         */
        void postConfirmOrder(String line_id, String title, String customer_name, String customer_phone, String user_passport,
                              String user_user_identity, String twenty_four, String twenty_six,
                              String twenty_eight, String thirty, String use_car_adult,
                              String use_car_children, String work_at, String work_address,
                              String dest_address, int discount_id, String total_price, String remark, String real_price);


    }

    interface View extends BaseView<Presenter, String> {
    }

}


