package com.sillykid.app.homepage.homestaysubscribe;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface HomestaySubscribeContract {
    interface Presenter extends BasePresenter {
        /**
         * 获得房源信息
         */
        void getHomestaySubscribe();

        /**
         * 获得车型
         */
        void getCarInfo();
    }

    interface View extends BaseView<Presenter, String> {
    }

}


