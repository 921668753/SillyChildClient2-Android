package com.sillykid.app.dialog.gastronomy;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface PriceBouncedContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取价格信息
         */
        void getPriceBounced();
    }

    interface View extends BaseView<Presenter, String> {
    }

}


