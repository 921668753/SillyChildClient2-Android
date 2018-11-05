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
        private int city_id;
        private String city_name;
        private String airport_name;
        private String airport_picture;


        public int getAirport_id() {
            return airport_id;
        }

        public void setAirport_id(int airport_id) {
            this.airport_id = airport_id;
        }

        public String getAirport_name() {
            return airport_name;
        }

        public void setAirport_name(String airport_name) {
            this.airport_name = airport_name;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getAirport_picture() {
            return airport_picture;
        }

        public void setAirport_picture(String airport_picture) {
            this.airport_picture = airport_picture;
        }

        @Override
        public String getTarget() {
            return airport_name;
        }
    }

}

