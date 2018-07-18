package com.sillykid.app.mine.fansattention;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * 收藏的动态
 * Created by ruitu on 2016/9/24.
 */

public interface FansAttentionContract {
    interface Presenter extends BasePresenter {
        /**
         * 粉丝
         */
        void getAttentionMeList(int p, int pageSize,String userId);
        /**
         * 关注
         */
        void getAttentionList(int p, int pageSize,String userId);
        /**
         * 获取人物信息
         */
        void baseInfo(String userId);
        /**
         * 获取人物信息动态，攻略以及相应收藏
         */
        void getOtherInfo(String userId, String type,int p);//1动态|2攻略|3收藏的动态|4收藏的攻略
        /**
         * 关注他人
         */
        void attentionOrNo(String userId,int status);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


