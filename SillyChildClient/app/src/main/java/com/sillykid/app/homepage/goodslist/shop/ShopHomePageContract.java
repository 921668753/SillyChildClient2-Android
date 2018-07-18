package com.sillykid.app.homepage.goodslist.shop;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ShopHomePageContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取店铺的轮播图图片
         */
        void getStoreImage(int storeid);

        /**
         * 获取店铺首页要展示的商品列表
         */
        void getStoreIndexGoods(int storeid);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


