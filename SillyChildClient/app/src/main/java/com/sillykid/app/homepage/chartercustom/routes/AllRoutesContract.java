package com.sillykid.app.homepage.chartercustom.routes;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AllRoutesContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取路线列表信息
         */
        void getAllRoutes(int page, String seat_num, String car_level, String line_buy_num, String city);

        /**
         * 获得车型类型
         */
        void getCarWhere();

    }

    interface View extends BaseView<Presenter, String> {
    }

}


