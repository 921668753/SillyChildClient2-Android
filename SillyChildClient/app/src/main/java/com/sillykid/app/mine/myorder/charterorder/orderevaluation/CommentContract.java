package com.sillykid.app.mine.myorder.charterorder.orderevaluation;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CommentContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取订单信息
         */
        void getReviewProduct(String order_number);

        /**
         * 商品评价 - 添加商品评价
         */
        void postAddProductReview(String order_number, int time_degree, int play_experience, int service_attitude, String content, List<LocalMedia> selectList);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


