package com.sillykid.app.mine.myshoppingcart.makesureorder;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface MakeSureOrderContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取结算订单详情
         */
        void getCartBalance(String cartIds);

        /**
         * 创建付款订单
         */
        void postCreateOrder(int addressId, int bonusid, String cartIds);


    }

    interface View extends BaseView<Presenter, String> {

    }

}
