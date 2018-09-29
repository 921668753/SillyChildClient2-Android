package com.sillykid.app.homepage.bythedaycharter;

import android.content.Context;

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

        /**
         * 给评论点赞
         */
        void postAddCommentLike(int id, int type);

        /**
         * 获取会员登录状态
         */
        void getIsLogin(Context context, int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


