package com.sillykid.app.main;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface TripContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取出行
         */
        void getTrip();

    }

    interface View extends BaseView<Presenter, String> {
    }

}


