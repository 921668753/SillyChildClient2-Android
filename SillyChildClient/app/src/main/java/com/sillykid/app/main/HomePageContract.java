package com.sillykid.app.main;

import android.app.Activity;

import com.baidu.location.LocationClient;
import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface HomePageContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取首页信息
         */
        void getHomePage(String city);

        /**
         * 设置定位信息
         */
        void initLocation(Activity activity, LocationClient mLocationClient);

        /**
         * 判断是否登录
         */
        void isLogin(int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


