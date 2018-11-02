package com.sillykid.app.entity.homepage.airporttransportation.airportselect.fragment;

import com.common.cklibrary.entity.BaseResult;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

public class RecommendedBean extends BaseResult<List<RecommendedBean.DataBean>> {


    public class DataBean extends BaseIndexPinyinBean {
        /**
         * city_id : 17
         * country_id : 7
         * city_name : 东京
         * city_picture : http://img.shahaizhi.com/%28null%291530269095
         * country_name : 日本
         * country_picture : http://img.shahaizhi.com/%28null%291530269095
         */

        private int airport_id;
        private int country_id;
        private String country_name;
        private String airport_name;
        private String picture;


        public int getAirport_id() {
            return airport_id;
        }

        public void setAirport_id(int airport_id) {
            this.airport_id = airport_id;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public String getAirport_name() {
            return airport_name;
        }

        public void setAirport_name(String airport_name) {
            this.airport_name = airport_name;
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

        @Override
        public String getTarget() {
            return country_name;
        }
    }

}

