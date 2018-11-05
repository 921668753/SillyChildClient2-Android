package com.sillykid.app.homepage.airporttransportation.airportselect.fragment;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

public interface AirportClassificationContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取大洲下面的数据
         */
        void getCountryAreaListByParentid(Context context, int id, int type, int flag);

        /**
         * 获取分类
         */
        void getAirportByCountryId(Context context, int country_id);

        /**
         * 获取分类
         */
        void getRecommendAirport(Context context, int type);

    }

    interface View extends BaseView<Presenter, String> {
    }
}
