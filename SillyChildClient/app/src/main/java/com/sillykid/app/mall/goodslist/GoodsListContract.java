package com.sillykid.app.mall.goodslist;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

public class GoodsListContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取商品列表
         */
        void getGoodsList(int page, int cat, String sort, String keyword, String tag);
    }

    interface View extends BaseView<Presenter, String> {
    }
}