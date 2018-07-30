package com.sillykid.app.homepage.privatecustom;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface PrivateCustomContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取偏好列表
         */
        void getCategoryList();

        /**
         * 用户填写定制要求
         */
        void postAddCustomized(long travel_time, String destination, String play_days, int travel_preference, int repast_preference, int stay_preference, String remark);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


