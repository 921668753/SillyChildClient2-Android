package com.sillykid.app.homepage.dynamics;

import android.widget.EditText;
import android.widget.TextView;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface DynamicsCommentariesContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取动态评论
         */
        void getDynamicsCommentaries(int page, String article_id);

        /**
         * 最新动态评论(回复)
         */
        void newActionComment(String article_id, String publish_id, String content, String parent_id);

        /**
         * 监听EditText输入改变
         */
        void changeInputView(EditText editText, TextView textView);

        /**
         * 评论点赞
         */
        void praiseDynamicsDetailsComment(String id, int isPraise);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


