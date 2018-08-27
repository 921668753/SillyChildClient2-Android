package com.sillykid.app.homepage.boutiqueline.fragment;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

public interface BoutiqueLineContract {

    interface Presenter extends BasePresenter {

        /**
         * 精品线路 - 获取精品线路商品列表
         */
        void getRouteList(Context context, int region_id, int is_recommand, int pageno);

    }

    interface View extends BaseView<Presenter, String> {
    }
}
