package com.sillykid.app.entity.homepage.boutiqueline.selectcity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class SelectCityBean extends BaseResult<List<SelectCityBean.DataBean>> {


    public class DataBean {
        /**
         * country_id : 8
         * country_name : 中国
         * region_id : 9
         * picture : http://img.shahaizhi.com/%28null%291530269095
         * region_name : 上海市内包车
         */

        private int country_id;
        private String country_name;
        private int region_id;
        private String picture;
        private String region_name;

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public int getRegion_id() {
            return region_id;
        }

        public void setRegion_id(int region_id) {
            this.region_id = region_id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }
    }
}
