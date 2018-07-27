package com.sillykid.app.homepage.hotvideo;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface VideoDetailsContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取视频详情
         */
        void getVideoDetails(int id);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


