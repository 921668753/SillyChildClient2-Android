package com.sillykid.app.entity.homepage.privatecustom.cityselect.search;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class CitySearchRListBean extends BaseResult<List<CitySearchRListBean.DataBean>> {

    public class DataBean {
        /**
         * city_id : 17
         * country_id : 7
         * city_name : 东京
         * city_picture : http://img.shahaizhi.com/%28null%291530269095
         * country_name : 日本
         * country_picture : http://img.shahaizhi.com/%28null%291530269095
         */

        private int city_id;
        private int country_id;
        private String city_name;
        private String country_name;
        private String picture;

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}