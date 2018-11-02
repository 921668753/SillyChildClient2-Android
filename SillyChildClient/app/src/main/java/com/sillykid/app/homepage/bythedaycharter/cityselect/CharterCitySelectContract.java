package com.sillykid.app.homepage.bythedaycharter.cityselect;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CharterCitySelectContract {

    interface Presenter extends BasePresenter {

        /**
         * 大洲与国家 - 获取大洲信息
         */
        void getCountryAreaList(int type, int parentId);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


