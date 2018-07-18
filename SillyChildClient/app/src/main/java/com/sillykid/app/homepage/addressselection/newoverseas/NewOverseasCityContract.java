package com.sillykid.app.homepage.addressselection.newoverseas;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface NewOverseasCityContract {
    interface Presenter extends BasePresenter {

        /**
         * 得到全部国家（全部国家返回）
         */
        void getAllCountry();

        /**
         * 得到该国家城市（一级全部返回）
         */
        void getAllChildCity(int countryId);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


