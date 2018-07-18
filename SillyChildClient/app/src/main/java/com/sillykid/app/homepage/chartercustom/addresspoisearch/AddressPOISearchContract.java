package com.sillykid.app.homepage.chartercustom.addresspoisearch;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AddressPOISearchContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取地址信息
         */
        void getSearchCity(String name);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


