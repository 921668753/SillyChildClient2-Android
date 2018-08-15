package com.sillykid.app.mine.myrelease.mydynamic;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by ruitu on 2018/9/24.
 */
public interface ReleaseDynamicContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取帖子详情
         */
        void getPostDetail(int id);

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
        void postAddPost(String post_title, List<LocalMedia> selectList, int mediaType, String content, int classification_id);

        /**
         * 编辑帖子
         */
        void postEditPost(String post_title, List<LocalMedia> selectList, int mediaType, String content, int classification_id, int post_id);

        /**
         * 用户删除帖子
         */
        void postDeletePost(int id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


