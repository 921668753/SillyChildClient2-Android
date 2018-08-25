package com.sillykid.app.mine.myorder.charterorder.orderdetails;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2018/9/24.
 */

public interface CharterOrderDetailsContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取包车订单详情
         */
        void getCharterOrderDetails(String order_number);

        /**
         * 获取私人定制单的详细信息
         */
        void getCustomizeOrderDetail(String order_number);

        /**
         * 获取会员登录状态
         */
        void getIsLogin(Context context, int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


