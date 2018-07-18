package com.sillykid.app.mine.personaldata;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface PersonalDataContract {
    interface Presenter extends BasePresenter {

        /**
         * 更改用户生日
         */
        void setBirthday(long birthday);

        /**
         * 更改用户地址
         */
        void setRegion(String province, int province_id, String city, int city_id, String region, int region_id);

        /**
         * 上传图片
         */
        void upPictures(String path);

        /**
         * 更改傻孩子账号
         */
        void changeShzCode(String shz_code);

        /**
         * 根据parentid获取所有地区列表
         */
        void getAddress(int parentid);

        /**
         * 根据父id获取地址列表
         */
        void getRegionList(int parentid, int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


