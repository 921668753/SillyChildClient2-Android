package com.sillykid.app.entity;


import com.common.cklibrary.entity.BaseResult;
import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by Admin on 2017/9/11.
 */

public class CarInfoBean extends BaseResult<List<CarInfoBean.ResultBean>> {


    /**
     * status : 1
     * msg : 成功
     * result : [{"id":1,"site_num":20}]
     */


    public static class ResultBean implements IPickerViewData {
        /**
         * id : 1
         * site_num : 20
         */

        private int id;
        private String site_num;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSite_num() {
            return site_num;
        }

        public void setSite_num(String site_num) {
            this.site_num = site_num;
        }

        // 实现 IPickerViewData 接口，
        // 这个用来显示在PickerView上面的字符串，
        // PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
        @Override
        public String getPickerViewText() {
            return this.site_num;
        }
    }
}
