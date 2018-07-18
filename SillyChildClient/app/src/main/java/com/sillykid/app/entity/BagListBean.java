package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/9/14.
 */

public class BagListBean extends BaseResult<List<BagListBean.ResultBean>> {

    /**
     * status : 1
     * msg : 成功
     * result : [{"id":1,"site_num":20}]
     */


    public static class ResultBean {
        /**
         * id : 1
         * site_num : 20
         */

        private String dimension;
        private String luggage_num;

        public String getDimension() {
            return dimension;
        }

        public void setDimension(String dimension) {
            this.dimension = dimension;
        }

        public String getLuggage_num() {
            return luggage_num;
        }

        public void setLuggage_num(String luggage_num) {
            this.luggage_num = luggage_num;
        }
    }
}