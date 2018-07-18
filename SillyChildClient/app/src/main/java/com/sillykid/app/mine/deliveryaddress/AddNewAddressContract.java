package com.sillykid.app.mine.deliveryaddress;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AddNewAddressContract {
    interface Presenter extends BasePresenter {
        /**
         * 根据parentid获取所有地区列表
         */
        void getAddress(int parentid);

        /**
         * 编辑收货地址
         */
        void postEditAddress(int addr_id, String name, String mobile, int province_id, int city_id, int region_id, int town_id, String addr, int def_addr);

        /**
         * 新增收货地址
         */
        void postAddAddress(String name, String mobile, int province_id, int city_id, int region_id, int town_id, String addr, int def_addr);

        /**
         * 根据父id获取地址列表
         */
        void getRegionList(int parentid, int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


