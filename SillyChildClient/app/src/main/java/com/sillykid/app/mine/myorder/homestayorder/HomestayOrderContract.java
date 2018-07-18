package com.sillykid.app.mine.myorder.homestayorder;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface HomestayOrderContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取用户信息
         */
        void getChartOrder(String type);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


