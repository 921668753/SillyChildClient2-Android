package com.sillykid.app.homepage.homestaysubscribe;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface HomestayDetailsContract {
    interface Presenter extends BasePresenter {
        /**
         * 账号登录
         */
        void postToLogin(String phone, String pwd);

        /**
         * 获取用户信息
         */
        void getInfo();
    }

    interface View extends BaseView<Presenter, String> {
    }

}


