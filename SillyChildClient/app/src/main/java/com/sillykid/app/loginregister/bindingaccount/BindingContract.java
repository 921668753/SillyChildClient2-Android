package com.sillykid.app.loginregister.bindingaccount;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */
interface BindingContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取验证码
         *
         * @param phone
         */
        void postCode(String phone, String countroy_code, String postCode);

        /**
         * 发送邮件验证码
         *
         * @param mail
         */
        void postMailCaptcha(String mail, String postCode);

        /**
         * 绑定手机号
         */
        void postBindingPhone(String openid, String from, String phone, String countroy_code, String code, String recommendcode);

        /**
         * 绑定邮箱号
         */
        void postBindingMail(String openid, String from, String mail, String code, String recommendcode);


        /**
         * 第三方账号登录
         */
        void postThirdToLogin(String openid, String from, String nickname, String head_pic, int sex);

        /**
         * 登录环信
         */
        void loginHuanXin(String phone, String pwd);
    }

    interface View extends BaseView<Presenter, String> {

    }

}