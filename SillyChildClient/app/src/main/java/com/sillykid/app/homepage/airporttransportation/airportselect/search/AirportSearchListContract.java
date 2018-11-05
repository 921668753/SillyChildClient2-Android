package com.sillykid.app.homepage.airporttransportation.airportselect.search;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AirportSearchListContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取分类
         */
        void getAirportByName(String name);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


