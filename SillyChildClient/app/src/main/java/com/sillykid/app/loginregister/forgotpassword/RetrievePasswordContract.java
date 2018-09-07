package com.sillykid.app.loginregister.forgotpassword;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */
interface RetrievePasswordContract {
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
         * 重置密码请求
         */
        void postResetpwd(String phone, String code, String pwd, String pwd1);

        /**
         * 忘记密码通过邮箱
         */
        void getForgetPasswordByMail(String mail, String code, String pwd, String pwd1);
    }

    interface View extends BaseView<Presenter, String> {

    }

}