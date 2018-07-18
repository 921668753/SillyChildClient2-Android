package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/9/8.
 */

public class AllCompanyGuideBean extends BaseResult<AllCompanyGuideBean.ResultBean> {


    public class ResultBean {
        /**
         * p : 1
         * pageSize : 10
         * totalRows : 1
         * totalPages : 1
         * list : [{"seller_id":1,"head_pic":"http://img.shahaizi.cn/seller_avatar.png","nickname":"18796020192","drv_code":"20171020-1","province":"江苏省","city":"苏州市","plat_start":"4","star":5,"line":""}]
         */

        private int p;
        private String pageSize;
        private int totalRows;
        private int totalPages;
        private List<ListBean> list;

        public int getP() {
            return p;
        }

        public void setP(int p) {
            this.p = p;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(int totalRows) {
            this.totalRows = totalRows;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean {
            /**
             * seller_id : 1
             * head_pic : http://img.shahaizi.cn/seller_avatar.png
             * nickname : 18796020192
             * drv_code : 20171020-1
             * province : 江苏省
             * city : 苏州市
             * plat_start : 4
             * star : 5
             * line :
             */

            private int seller_id;
            private String head_pic;
            private String nickname;
            private String drv_code;
            private String province;
            private String country;
            private String city;
            private String plat_start;
            private String star;
            private String line;

            public int getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getDrv_code() {
                return drv_code;
            }

            public void setDrv_code(String drv_code) {
                this.drv_code = drv_code;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
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
}
