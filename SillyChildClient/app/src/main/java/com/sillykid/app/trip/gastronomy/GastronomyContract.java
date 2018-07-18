package com.sillykid.app.trip.gastronomy;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface GastronomyContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取美食列表信息
         */
        void getGastronomy(int page, String n, String n1, String n2);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


