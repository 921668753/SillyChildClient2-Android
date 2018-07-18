package com.sillykid.app.dialog.chartercustom;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CompensationChangeBackContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取补偿改退
         */
        void getCompensationChangeBack(int id);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


