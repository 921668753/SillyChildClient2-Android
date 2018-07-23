package com.sillykid.app.main;

import android.app.Activity;

import com.baidu.location.LocationClient;
import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface MallContract {

    interface Presenter extends BasePresenter {

//        /**
//         * 获取分类广告
//         */
//        void getAdvCat();

        /**
         * 获取首页信息
         */
        void getMall();

//        /**
//         * 获取商品分类
//         */
//        void postBaiDuUpdateInfo();

//        /**
//         * 设置定位信息
//         */
//        void initLocation(Activity activity, LocationClient mLocationClient);


    }

    interface View extends BaseView<Presenter, String> {
    }

}


