package com.sillykid.app.homepage.homestaysubscribe;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AllHomestayContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取全部民宿信息
         */
        void getAllHomestay(int page);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


