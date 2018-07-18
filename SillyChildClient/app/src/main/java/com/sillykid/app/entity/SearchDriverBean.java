package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/9/17.
 */

public class SearchDriverBean extends BaseResult<List<SearchDriverBean.ResultBean>> {


    /**
     * status : 1
     * msg : 成功
     * result : [{"seller_id":17,"head_pic":null,"seller_name":"少秋","drv_code":"20170908-1","province":"朝阳门街道","city":"建国门街道","plat_start":null,"star":2.6667,"line":"我的标题很长很长可以用100年"}]
     */


    public static class ResultBean {
        /**
         * seller_id : 17
         * head_pic : null
         * seller_name : 少秋
         * drv_code : 20170908-1
         * province : 朝阳门街道
         * city : 建国门街道
         * plat_start : null
         * star : 2.6667
         * line : 我的标题很长很长可以用100年
         */

        private int seller_id;
        private Object head_pic;
        private String seller_name;
        private String drv_code;
        private String province;
        private String city;
        private Object plat_start;
        private double star;
        private String line;

        public int getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(int seller_id) {
            this.seller_id = seller_id;
        }

        public Object getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(Object head_pic) {
            this.head_pic = head_pic;
        }

        public String getSeller_name() {
            return seller_name;
        }

        public void setSeller_name(String seller_name) {
            this.seller_name = seller_name;
        }

        public String getDrv_code() {
            return drv_code;
        }

        public void setDrv_code(String drv_code) {
            this.drv_code = drv_code;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Object getPlat_start() {
            return plat_start;
        }

        public void setPlat_start(Object plat_start) {
            this.plat_start = plat_start;
        }

        public double getStar() {
            return star;
        }

        public void setStar(double star) {
            this.star = star;
        }

        public String getLine() {
            return line;
        }

        public void setLine(String line) {
            this.line = line;
        }
    }
}
