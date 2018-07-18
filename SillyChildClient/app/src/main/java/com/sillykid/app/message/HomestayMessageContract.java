package com.sillykid.app.message;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface HomestayMessageContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取民宿消息列表信息
         */
        void getHomestayMessage();

        /**
         * 判断是否登录
         */
        void isLogin(int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


