package com.sillykid.app.mine.mywallet.paymentpasswordmanagement.setpaymentpassword;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface SetPaymentPasswordContract {

    interface Presenter extends BasePresenter {
        /**
         * 设置支付密码
         */
        void postSetPaymentPassword(String oldPaymentPassword, String paymentPassword);
    }

    interface View extends BaseView<Presenter, String> {

    }

}
