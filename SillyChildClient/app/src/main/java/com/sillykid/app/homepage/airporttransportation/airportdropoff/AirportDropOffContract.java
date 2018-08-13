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
        void post(int page, String sort_field, String sort_type);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


