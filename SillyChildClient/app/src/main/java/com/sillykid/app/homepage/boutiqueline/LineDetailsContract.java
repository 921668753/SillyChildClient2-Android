package com.sillykid.app.homepage.boutiqueline;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

public interface LineDetailsContract {

    interface Presenter extends BasePresenter {

        /**
         * 精品线路 - 线路详情
         */
        void getRouteDetail(int product_id);


        /**
         * 给评论点赞
         */
        void postAddCommentLike(int id, int type);

        /**
         * 提交订单信息
         */
        void postAddRequirements(int product_id, String flight_arrival_time, String contact, String contact_number,
                                 String audit_number, String children_number, String baggage_number, String remarks, int passenger_number1, int baggage_number1);
    }

    interface View extends BaseView<Presenter, String> {
    }
}
