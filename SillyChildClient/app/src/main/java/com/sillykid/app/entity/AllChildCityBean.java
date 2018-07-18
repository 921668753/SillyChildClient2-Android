package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/11/8.
 */

public class AllChildCityBean extends BaseResult<List<AllChildCityBean.ResultBean>> {

    public static class ResultBean {
        /**
         * id : 47498
         * name : 东京
         * level : 2
         * parent_id : 0
         * is_hot : 1
         * country_id : 8
         * area_code : null
         */

        private int id;
        private String name;
        private int level;
        private int parent_id;
        private int is_hot;
        private int country_id;
        private String area_code;

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

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public int getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(int is_hot) {
            this.is_hot = is_hot;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public String getArea_code() {
            return area_code;
        }

        public void setArea_code(String area_code) {
            this.area_code = area_code;
        }
    }
}
