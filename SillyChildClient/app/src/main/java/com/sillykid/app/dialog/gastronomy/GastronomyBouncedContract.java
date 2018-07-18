package com.sillykid.app.dialog.gastronomy;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface GastronomyBouncedContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取美食信息
         */
        void getGastronomyBounced();
    }

    interface View extends BaseView<Presenter, String> {
    }

}


