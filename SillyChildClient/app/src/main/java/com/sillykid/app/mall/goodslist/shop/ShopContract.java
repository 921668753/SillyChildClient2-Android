package com.sillykid.app.mall.goodslist.shop;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ShopContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取店铺详情
         */
        void getShopDetail(int storeid);

        /**
         * 收藏商品
         */
        void postFavoriteAdd(int storeid);

        /**
         * 取消商品
         */
        void postUnfavorite(int storeid);

        /**
         * 获取会员登录状态
         */
        void getIsLogin(Context context, int flag);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


