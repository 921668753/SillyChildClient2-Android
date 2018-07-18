package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 2017/9/12.
 */

public class IndexCityBean extends BaseResult<List<IndexCityBean.ResultBean>> {


    /**
     * status : 1
     * msg : 成功
     * result : [{"id":1,"parent_id":0,"name":"中国","level":null,"area_code":null},{"id":3414,"parent_id":0,"name":"美洲","level":1,"area_code":"/public/upload/ad/2017/09-11/48df0b9e039749c552a07967c78eed37.jpg"}]
     */


    public static class ResultBean {
        /**
         * id : 1
         * parent_id : 0
         * name : 中国
         * level : null
         * area_code : null
         */

        private int id;
        private int parent_id;
        private String name;
        private String level;
        private int country_id;
        private String area_code;
        //        @SerializedName("status")
//        private int statusX;
        private int status;
        private String country;

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

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getArea_code() {
            return area_code;
        }

        public void setArea_code(String area_code) {
            this.area_code = area_code;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

}
