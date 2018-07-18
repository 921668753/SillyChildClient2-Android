package com.sillykid.app.mall.goodslist.goodsdetails;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface GoodsDetailsContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取商品详情
         */
        void getGoodDetail(int goodsid);

        /**
         * 收藏商品
         */
        void postFavoriteAdd(int goodsid);

        /**
         * 取消商品
         */
        void postUnfavorite(int goodsid);

        /**
         * 立即购买
         */
        void postOrderBuyNow(int goodId, int num, int product_id);

        /**
         * 加入购物车
         */
        void postAddCartGood(int goodsid, int num, int product_id);

        /**
         * 获取会员登录状态
         */
        void getIsLogin(Context context, int flag);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


