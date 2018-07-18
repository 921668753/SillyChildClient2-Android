package com.sillykid.app.mine.myrelease.strate;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface StrateContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取我的攻略列表
         */
        void getStrateList(int p, int pageSize);
        /**
         * 发布我的攻略
         */
        void pulishStrate(String title, String countryid, String regionId, String city, String summary, String content, int flag);
        /**
         * 上传图片
         */
        void upPictures(String paramname, File voule, int resultsource);
        /**
         * 删除攻略
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


