package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/11/8.
 */

public class AllCountryBean extends BaseResult<List<AllCountryBean.ResultBean>> {


    public static class ResultBean {
        /**
         * id : 8
         * parent_id : 1
         * name : 日本
         * level : 2
         * area_code : null
         * is_hot : 1
         */

        private int id;
        private int parent_id;
        private String name;
        private int level;
        private String area_code;
        private int is_hot;
        private int status;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getArea_code() {
            return area_code;
        }

        public void setArea_code(String area_code) {
            this.area_code = area_code;
        }

        public int getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(int is_hot) {
            this.is_hot = is_hot;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
