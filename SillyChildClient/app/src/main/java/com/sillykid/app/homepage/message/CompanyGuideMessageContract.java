package com.sillykid.app.homepage.message;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CompanyGuideMessageContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取司导消息列表信息
         */
        void getCompanyGuideMessage();

        /**
         * 判断是否登录
         */
        void isLogin(int flag);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


