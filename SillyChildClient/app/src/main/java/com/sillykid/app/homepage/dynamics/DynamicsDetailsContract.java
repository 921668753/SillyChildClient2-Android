package com.sillykid.app.homepage.dynamics;

import android.widget.EditText;
import android.widget.TextView;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface DynamicsDetailsContract {
    interface Presenter extends BasePresenter {

        /**
         * 设置系统消息已读
         */
        void getReadMessage(String id);

        /**
         * 获取动态详情信息
         */
        void getDynamicsDetails(String id);

        /**
         * 进行关注
         */
        void getAttention(String id, int isAttention);

        /**
         * 点赞
         */
        void praiseDynamicsDetails(String id, int isPraise);

        /**
         * 收藏
         */
        void collectDynamic(String id, int isCollectDynamic);

        /**
         * 最新动态评论(回复)
         */
        void newActionComment(String article_id, String publish_id, String content, String parent_id);

        /**
         * 评论点赞
         */
        void praiseDynamicsDetailsComment(String id, int isPraiseComment);

        /**
         * 监听EditText输入改变
         */
        void changeInputView(EditText editText, TextView textView, int isComment);

        /**
         * 跳转动态评论
         */
        void toDynamicComments(TextView textView, String article_id, String publish_id, DynamicsDetailsActivity activity);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


