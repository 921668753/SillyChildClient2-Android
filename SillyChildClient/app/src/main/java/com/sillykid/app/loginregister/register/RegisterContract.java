package com.sillykid.app.loginregister.register;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.sillykid.app.entity.loginregister.LoginBean;

/**
 * Created by ruitu on 2016/9/24.
 */
interface RegisterContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取验证码
         *
         * @param phone
         */
        void postCode(String phone, String countryCode, String postCode);

        /**
         * 发送邮件验证码
         *
         * @param mail
         */
        void postMailCaptcha(String mail, String postCode);

        /**
         * 注册
         */
        void postRegister(String phone, String code, String pwd);

        /**
         * 登录融云
         */
        void loginRongYun(LoginBean bean);
    }

    interface View extends BaseView<Presenter, String> {

    }

}