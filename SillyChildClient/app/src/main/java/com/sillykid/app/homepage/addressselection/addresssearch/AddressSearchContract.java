package com.sillykid.app.homepage.addressselection.addresssearch;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AddressSearchContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取用户信息
         */
        void getSearchCity(String name);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


