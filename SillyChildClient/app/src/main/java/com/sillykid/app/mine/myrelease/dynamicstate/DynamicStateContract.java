package com.sillykid.app.mine.myrelease.dynamicstate;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.sillykid.app.mine.myorder.MyOrderActivity;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface DynamicStateContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取我的动态列表
         */
        void getDynamicList(int p, int pageSize);
        /**
         * 发布我的动态
         */
        void pulishDynamic(String img, String title, String content, int flag);
        /**
         * 上传图片
         */
        void upPictures(String paramname, File voule, int resultsource);
        /**
         * 删除动态
         */
        void doDelete(String id, int resultsource);
        /**
         * 获取个人信息
         */
        void getInfo();

    }

    interface View extends BaseView<Presenter, String> {
    }

}


