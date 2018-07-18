package com.sillykid.app.homepage.addressselection.overseas;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface OverseasCityContract {
    interface Presenter extends BasePresenter {

        /**
         * 得到全部城市（一级全部返回）
         */
        void getAllCity(int countryId);

        /**
         * 得到热门城市（一级全部返回）
         */
        void getChildHotCity(int countryId);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


