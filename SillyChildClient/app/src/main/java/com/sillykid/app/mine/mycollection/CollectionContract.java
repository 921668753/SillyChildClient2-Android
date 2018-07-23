package com.sillykid.app.mine.mycollection;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CollectionContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取收藏商品列表
         */
        void getFavoriteList(int page, int type_id);

        /**
         * 取消收藏
         */
        void postUnFavorite(int goodsid, int type_id);

        /**
         * 加入购物车
         */
        void postAddCartGood(int goodsid, int num, int product_id);


    }

    interface View extends BaseView<Presenter, String> {
    }

}


