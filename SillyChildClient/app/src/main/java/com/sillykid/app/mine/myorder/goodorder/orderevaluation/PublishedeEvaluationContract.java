package com.sillykid.app.mine.myorder.goodorder.orderevaluation;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.sillykid.app.entity.mine.myorder.goodorder.orderevaluation.PublishedeEvaluationBean.DataBean.MemberCommentExtsBean;

import java.util.List;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface PublishedeEvaluationContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取订单详情
         */
        void getOrderDetails(int orderId);

        /**
         * 上传图片
         * @param imgPath
         */
        void upPictures(String imgPath);

        /**
         * 发表评论
         */
        void postCommentCreate(List<MemberCommentExtsBean> listBean, int store_desccredit, int store_servicecredit, int store_deliverycredit);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


