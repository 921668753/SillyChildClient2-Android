package com.sillykid.app.activity;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface PastWonderfulContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取活动首页
         */
        void getAllActivity(int pageno);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


