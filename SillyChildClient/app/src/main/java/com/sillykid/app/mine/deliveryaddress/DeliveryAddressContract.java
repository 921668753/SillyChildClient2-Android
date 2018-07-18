package com.sillykid.app.mine.deliveryaddress;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface DeliveryAddressContract {
    interface Presenter extends BasePresenter {
        /**
         * 收货地址列表
         */
        void getAddressList();

        /**
         * 设置默认收货地址
         */
        void setDefaultAddress(int addr_id);

        /**
         * 收货地址删除
         */
        void postDeleteAddress(int addr_id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


