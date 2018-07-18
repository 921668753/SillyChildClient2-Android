package com.sillykid.app.homepage.chartercustom.chartercommon;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CharterListContract {

    interface Presenter extends BasePresenter {

        /**
         * 获得包车列表
         */
        void getPackCarProduct(String city, int page, int type, String car_level, String car_seat_num, String order_times);

        /**
         * 获得车型类型列表
         */
        void getCarWhere();

//        /**
//         * 判断是否登录
//         */
//        void isLogin();
    }

    interface View extends BaseView<Presenter, String> {
    }

}


