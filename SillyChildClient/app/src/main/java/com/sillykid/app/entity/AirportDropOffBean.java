package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/9/28.
 */

public class AirportDropOffBean extends BaseResult<AirportDropOffBean.ResultBean> {


    /**
     * status : 1
     * msg : 添加成功
     * result : {"id":"8"}
     */

    public class ResultBean {
        /**
         * id : 8
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
