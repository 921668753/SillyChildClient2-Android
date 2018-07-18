package com.sillykid.app.dialog.gastronomy;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface NearBouncedContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取附近分类信息
         */
        void getNearBounced();

        /**
         * 获取附近二级分类信息
         */
        void getNear1Bounced();
    }

    interface View extends BaseView<Presenter, String> {
    }

}


