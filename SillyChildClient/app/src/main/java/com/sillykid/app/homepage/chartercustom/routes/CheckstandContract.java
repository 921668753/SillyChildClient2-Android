package com.sillykid.app.homepage.chartercustom.routes;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CheckstandContract {
    interface Presenter extends BasePresenter {

        /**
         * 支付订单
         */
        void orderPay(String air_id, String pay_way, String coupon_id);

        /**
         * 获取用户信息
         */
        void getInfo(int flagint);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


