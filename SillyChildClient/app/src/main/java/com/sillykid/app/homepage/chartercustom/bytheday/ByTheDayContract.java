package com.sillykid.app.homepage.chartercustom.bytheday;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ByTheDayContract {

    interface Presenter extends BasePresenter {
        /**
         * 按天包车游信息
         */
        void postByTheDay(String type, String origin, String destination, String selectStartEndDateCar, String selectModels, String workNumber,
                          String adultNum, String childrenNum, String tv_bags, String tv_bags1, String tv_bags2, String tv_bags3,
                          String name, String contactWay, String remark, String con_car_seat_num, int pcpid);

        /**
         * 获得车型
         */
        void getCarBrand();

        /**
         * 判断是否登录
         */
        void isLogin();
    }

    interface View extends BaseView<Presenter, String> {
    }

}


