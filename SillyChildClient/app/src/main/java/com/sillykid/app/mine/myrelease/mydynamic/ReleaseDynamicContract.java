package com.sillykid.app.mine.myrelease.mydynamic;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

import java.util.List;

/**
 * Created by ruitu on 2018/9/24.
 */
public interface ReleaseDynamicContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取分类信息列表
         */
        void getClassificationList();

        /**
         * 上传图片
         *
         * @param imgPath
         */
        void upPictures(String imgPath);

        /**
         * 上传视频
         *
         * @param videoPath
         */
        void uploadVideo(String videoPath);

        /**
         * 用户发布帖子
         */
        void postAddPost(String post_title, List<String> imgs, String content, String classification_id, int type);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


