package com.sillykid.app.mine.personaldata.changepassword;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ChangePassWordContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取验证码
         */
        void getCode(Context aty, String phone, String opt);

        /**
         * 修改密码
         */
        void changePassWord(String phone, String code, String pwd, String pwd1);

//        /**
//         * 登录
//         */
//        void relogin(String phone,String pwd);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


