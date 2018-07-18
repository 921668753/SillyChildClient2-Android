package com.sillykid.app.mall.goodslist.goodsdetails.dialog;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface SpecificationsBouncedContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取商品规格
         */
        void getGoodsSpec(Context context, int id);

        /**
         * 由规格数组获取货品的参数
         */
        void getGoodsProductSpec(Context context, int goodsid, String specs);


        /**
         * 获取商品剩余规格
         */
        void getGoodsProductSpecLeft(Context context, int goodsid, String specs);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


