package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/9/27.
 */

public class FindDriverBean extends BaseResult<FindDriverBean.ResultBean> {


    /**
     * status : -1
     * msg : 成功
     * result : {"seller_id":19,"head_pic":"http://ovwiqces1.bkt.clouddn.com/0bae9711d0ad40d162526f1c9d420561.jpeg","seller_name":"","drv_code":"20170925-19","province":"","city":"","plat_start":"4","star":4,"line":"娶个厉害的名字"}
     */


    public class ResultBean {
        /**
         * seller_id : 19
         * head_pic : http://ovwiqces1.bkt.clouddn.com/0bae9711d0ad40d162526f1c9d420561.jpeg
         * seller_name :
         * drv_code : 20170925-19
         * province :
         * city :
         * plat_start : 4
         * star : 4
         * line : 娶个厉害的名字
         */

        private String seller_id;
        private String head_pic;
        private String seller_name;
        private String drv_code;
        private String province;
        private String city;
        private String plat_start;
        private String star;
        private String line;

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
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

        public String getPlat_start() {
            return plat_start;
        }

        public void setPlat_start(String plat_start) {
            this.plat_start = plat_start;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
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
