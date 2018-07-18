package com.sillykid.app.homepage.chartercustom.singletransport;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface SingleTransportContract {
    interface Presenter extends BasePresenter {
        /**
         * 单次接送
         */
        void postSingleTransport(String type, long vehicleTime, String fewAdults, String severalChildren,
                                 int selectModels, String whereDoYouStart, String whereAreGoing, String workNumber,
                                 String tv_bags, String tv_bags1, String tv_bags2, String tv_bags3, String name,
                                 String contactWay, String remark, String con_car_seat_num, String car_level);

        /**
         * 获得车型
         */
        void getCarBrand();

        /**
         * 获得车型类型列表
         */
        void getCarWhere();

        /**
         * 获得退订政策|费用说明done
         */
        void getUnsubscribeCost(int type);


        /**
         * 判断是否登录
         */
        void isLogin();
    }

    interface View extends BaseView<Presenter, String> {
    }

}


