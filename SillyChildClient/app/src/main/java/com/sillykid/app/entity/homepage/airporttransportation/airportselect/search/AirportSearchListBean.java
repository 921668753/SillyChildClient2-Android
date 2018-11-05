package com.sillykid.app.entity.homepage.airporttransportation.airportselect.search;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class AirportSearchListBean extends BaseResult<List<AirportSearchListBean.DataBean>> {

    public class DataBean {
        /**
         * city_name : 美娜多
         * city_id : 104
         * city_picture : http://img.shahaizhi.com/FhUCDiH7CCkb_4YTj14Cp96bD_Pv
         * airport_name : 万鸦老机场
         * airport_id : 108
         * airport_picture : http://img.shahaizhi.com/FhUCDiH7CCkb_4YTj14Cp96bD_Pv
         */

        private String city_name;
        private int city_id;
        private String airport_name;
        private int airport_id;
        private String airport_picture;

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public String getAirport_name() {
            return airport_name;
        }

        public void setAirport_name(String airport_name) {
            this.airport_name = airport_name;
        }

        public int getAirport_id() {
            return airport_id;
        }

        public void setAirport_id(int airport_id) {
            this.airport_id = airport_id;
        }

        public String getAirport_picture() {
            return airport_picture;
        }

        public void setAirport_picture(String airport_picture) {
            this.airport_picture = airport_picture;
        }
    }
}
