package com.sillykid.app.mine.myfans;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2018/9/24.
 */
public interface MyFansContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取收藏商品列表
         */
        void getFavoriteGoodList(int page);

        /**
         * 取消收藏
         */
        void postUnFavoriteGood(int goodsid);

        /**
         * 加入购物车
         */
        void postAddCartGood(int goodsid, int num, int product_id);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


