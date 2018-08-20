package com.sillykid.app.homepage.bythedaycharter;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface PriceInformationContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取分类
         */
        void getProductDetails(int product_id);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


