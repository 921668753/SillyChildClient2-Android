package com.sillykid.app.homepage.boutiqueline.selectcity;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface SelectCityContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取国家列表
         */
        void getAirportCountryList();

        /**
         * 获取分类
         */
        void getRouteRegion(int country_id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


