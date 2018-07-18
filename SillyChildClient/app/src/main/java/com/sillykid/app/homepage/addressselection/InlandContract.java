package com.sillykid.app.homepage.addressselection;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface InlandContract {
    interface Presenter extends BasePresenter {

        /**
         * 得到国内全部城市
         */
        void getAllCityIn();
        /**
         * 得到国内全部城市（一级全部返回）
         */
        void getAllCity();

        /**
         * 得到国内热门城市（一级全部返回）
         */
        void getChildHotCity();

    }

    interface View extends BaseView<Presenter, String> {
    }

}


