package com.sillykid.app.main;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ActivitiesContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取分类广告
         */
        void getAdvCat();

        /**
         * 获取活动信息
         */
        void getActivities();

    }

    interface View extends BaseView<Presenter, String> {
    }

}


