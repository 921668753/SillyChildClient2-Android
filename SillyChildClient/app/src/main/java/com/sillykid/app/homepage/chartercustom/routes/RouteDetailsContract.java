package com.sillykid.app.homepage.chartercustom.routes;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface RouteDetailsContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取路线详情信息
         */
        void getRouteDetails(String id);

        /**
         * 判断是否登录
         */
        void isLogin(int flag);

        /**
         * 收藏路线
         */
        void postCollectLine(String action, String line_id);


    }

    interface View extends BaseView<Presenter, String> {
    }

}


