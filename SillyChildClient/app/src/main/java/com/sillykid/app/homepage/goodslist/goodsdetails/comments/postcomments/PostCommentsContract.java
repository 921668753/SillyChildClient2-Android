package com.sillykid.app.homepage.goodslist.goodsdetails.comments.postcomments;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface PostCommentsContract {
    interface Presenter extends BasePresenter {
        /**
         * 上传图片
         */
        void upPictures(String paramname, File voule, int resultsource);
        /**
         * 发布评论
         */
        void postEvaluation(String orderid, int ordertype, int guidestarnum, int starnum, String urls, int anonymity, String textcontent, int resultsource);
        /**
         * 发布评论,只用于对线路订单有效
         */
        void postEvaluationRoute(String air_id, String seller_id, String drv_rank, String drv_content, String drv_img, String drv_is_anonymous, String line_id, String line_rank, String line_content, String line_img, String line_is_anonymous, int resultsource);
        /**
         * 查看评论
         */
        void seeEvaluation(String orderid);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


