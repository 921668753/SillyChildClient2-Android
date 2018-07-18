package com.sillykid.app.mine.myrelease.collectionstrate;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * 收藏的动态
 * Created by ruitu on 2016/9/24.
 */

public interface CollectionStrateContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取我收藏的动态列表
         */
        void getCollectionStrateList(int p, int pageSize);
        /**
         * 删除我收藏的动态
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


