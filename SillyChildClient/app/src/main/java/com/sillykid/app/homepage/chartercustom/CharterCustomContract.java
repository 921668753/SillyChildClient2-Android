package com.sillykid.app.homepage.chartercustom;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CharterCustomContract {
    interface Presenter extends BasePresenter {
        /**
         * 包车定制
         */
        void getCharterCustom(String city);

        /**
         * 判断是否登录
         */
        void isLogin(int flag);

        /**
         * 搜索司导
         */
        void getSearchDriver(String search);

        /**
         * 精确查找司导
         */
        void getFindDriver(String search);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


