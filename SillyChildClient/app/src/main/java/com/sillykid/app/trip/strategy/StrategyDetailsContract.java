package com.sillykid.app.trip.strategy;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface StrategyDetailsContract {
    interface Presenter extends BasePresenter {
        /**
         * 设置系统消息已读
         */
        void getReadMessage(String id);

        /**
         * 获取攻略详情
         */
        void getStrategyDetails(int id);

        /**
         * 攻略收藏/取消
         */
        void collectStrategy(int id, int isCollect);

        /**
         * 点赞
         */
        void praiseStrategyDetails(String id, int isPraise);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


