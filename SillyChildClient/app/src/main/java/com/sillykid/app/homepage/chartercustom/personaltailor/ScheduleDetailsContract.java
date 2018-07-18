package com.sillykid.app.homepage.chartercustom.personaltailor;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ScheduleDetailsContract {
    interface Presenter extends BasePresenter {
        /**
         * 包车定制
         */
        void getPrivateDetail(String air_id);

        /**
         * 判断是否登录
         */
        void isLogin(int flag);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


