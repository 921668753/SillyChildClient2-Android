package com.sillykid.app.entity.community;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class ClassificationListBean extends BaseResult<List<ClassificationListBean.DataBean>> {

    public class DataBean {
        /**
         * id : 1000000
         * name : 旅行
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}