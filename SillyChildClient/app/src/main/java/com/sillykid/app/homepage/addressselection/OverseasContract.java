package com.sillykid.app.homepage.addressselection;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface OverseasContract {
    interface Presenter extends BasePresenter {

        /**
         * 得到国外地区的首级列表
         */
        void getIndexCity();

        /**
         * 得到国外地区的子级列表
         */
        void getChildCity(int parent_id);

        /**
         * 得到国内热门城市（一级全部返回）
         */
        void getChildHotCity(int id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


