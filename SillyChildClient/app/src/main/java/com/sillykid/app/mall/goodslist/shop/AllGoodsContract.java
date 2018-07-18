package com.sillykid.app.mall.goodslist.shop;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AllGoodsContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取店铺的全部商品列表
         */
        void getStoreGoodsList(int storeid, int key, String order, int page, String keyword);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


