package com.sillykid.app.trip.gastronomy;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface DetailsContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取美食信息
         */
        void getDetails(int id);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


