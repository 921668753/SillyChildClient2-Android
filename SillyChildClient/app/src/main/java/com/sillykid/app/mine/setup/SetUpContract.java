package com.sillykid.app.mine.setup;

import android.app.Activity;
import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface SetUpContract {

    interface Presenter extends BasePresenter {
        /**
         * 检测更新app
         */
        void getUpdateApp();

        /**
         * 下载app
         */
        void downloadApp(String updateAppUrl);

        /**
         * 退出登录
         */
        void logOutAPP(Activity activity);

        /**
         * 获取会员登录状态
         */
        void getIsLogin(Context context, int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}
