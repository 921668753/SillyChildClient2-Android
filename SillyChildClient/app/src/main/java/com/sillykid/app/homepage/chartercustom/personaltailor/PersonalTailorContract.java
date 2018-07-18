package com.sillykid.app.homepage.chartercustom.personaltailor;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface PersonalTailorContract {
    interface Presenter extends BasePresenter {
        /**
         * 提交私人订制
         */
        void postPersonalTailor(String type, long travelTime, String origin, String destination, String playNumberDays, String fewAdults, String severalChildren,
                                String travelPreferences, String recommendRestaurant, String recommendedAccommodation, String bags,
                                String bags1, String bags2, String bags3, String name, String contactWay, String remark, String workNumber);


        /**
         * 得到私人定制的配置
         */

        void getDriverPackConfig();

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


