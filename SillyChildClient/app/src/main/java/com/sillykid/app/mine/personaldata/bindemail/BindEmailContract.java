package com.sillykid.app.mine.personaldata.bindemail;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface BindEmailContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取验证码
         *
         */
        void postCode(String email, String posttype);
        /**
         * 获取用户信息
         */
        void bindEmail(String email, String code);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


