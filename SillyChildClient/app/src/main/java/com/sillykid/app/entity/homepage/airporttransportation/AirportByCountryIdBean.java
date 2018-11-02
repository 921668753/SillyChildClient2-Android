package com.sillykid.app.entity.homepage.airporttransportation;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class AirportByCountryIdBean extends BaseResult<List<AirportByCountryIdBean.DataBean>> {


    public class DataBean {
        /**
         * country_id : 8
         * country_name : 中国
         * region_id : 9
         * region_name : 上海
         * airport_id : 10
         * airport_name : 浦东国际机场
         * airport_picture : http://img.shahaizhi.com/%28null%291530269095
         */

        private int isSelected;
        private int id;
        private String country_name;
        private String name;
        private String picture;

        public int getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(int isSelected) {
            this.isSelected = isSelected;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
