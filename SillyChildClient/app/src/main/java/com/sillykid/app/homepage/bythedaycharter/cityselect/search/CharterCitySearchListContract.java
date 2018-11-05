package com.sillykid.app.homepage.bythedaycharter.cityselect.search;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CharterCitySearchListContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取分类
         */
        void getCityByName(String name);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


