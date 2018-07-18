package com.sillykid.app.trip.gastronomy;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface StoreNameContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取地区攻略信息
         */
        void getRegionalStrategy(int page);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


