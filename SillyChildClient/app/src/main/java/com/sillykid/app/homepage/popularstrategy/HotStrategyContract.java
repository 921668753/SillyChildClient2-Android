package com.sillykid.app.homepage.popularstrategy;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface HotStrategyContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取热门攻略列表信息
         */
        void getHotStrategy(String city, int page);

        /**
         * 获取地区攻略列表信息
         */
        void getStrategy(String country_name, int page);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


