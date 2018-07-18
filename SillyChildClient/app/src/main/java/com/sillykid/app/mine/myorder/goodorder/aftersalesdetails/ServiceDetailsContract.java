package com.sillykid.app.mine.myorder.goodorder.aftersalesdetails;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ServiceDetailsContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取服务详情
         */
        void getSellBackService(String order_item_id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


