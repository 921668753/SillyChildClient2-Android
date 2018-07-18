package com.sillykid.app.trip.visa;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface VisaContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取签证信息
         */
        void getVisa();
    }

    interface View extends BaseView<Presenter, String> {
    }

}


