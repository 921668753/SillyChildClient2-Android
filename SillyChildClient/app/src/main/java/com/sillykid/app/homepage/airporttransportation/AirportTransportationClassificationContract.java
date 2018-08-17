package com.sillykid.app.homepage.airporttransportation;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AirportTransportationClassificationContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取分类
         */
        void getClassification(int cat_id, int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


