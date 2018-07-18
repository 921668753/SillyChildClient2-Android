package com.sillykid.app.homepage.dynamics;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AllDynamicsContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取全部动态信息
         */
        void getAllDynamics(int page, String sort_field, String sort_type);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


