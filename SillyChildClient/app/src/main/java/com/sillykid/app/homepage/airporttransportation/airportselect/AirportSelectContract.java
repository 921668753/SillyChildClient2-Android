package com.sillykid.app.homepage.airporttransportation.airportselect;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AirportSelectContract {

    interface Presenter extends BasePresenter {

        /**
         * 大洲与国家 - 获取大洲信息
         */
        void getCountryAreaList(int type, int parentId);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


