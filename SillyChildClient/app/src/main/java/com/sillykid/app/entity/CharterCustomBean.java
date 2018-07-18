package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/9/10.
 */

public class CharterCustomBean extends BaseResult<CharterCustomBean.ResultBean> {


    public class ResultBean {
        private List<IndexBean> index;
        private List<BannerBean> banner;
        private List<LineBean> line;
        private List<DriverBean> driver;

        public List<IndexBean> getIndex() {
            return index;
        }

        public void setIndex(List<IndexBean> index) {
            this.index = index;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<LineBean> getLine() {
            return line;
        }

        public void setLine(List<LineBean> line) {
            this.line = line;
        }

        public List<DriverBean> getDriver() {
            return driver;
        }

        public void setDriver(List<DriverBean> driver) {
            this.driver = driver;
        }

        public class IndexBean {
            /**
             * id : 1
             * name : 接机
             * sort : 1
             */

            private int id;
            private String name;
            private int sort;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }

        public class BannerBean {
            /**
             * ad_link : http://dev.tpshop.cn/index.php/Home/Topic/detail/topic_id/1
             * ad_name : 自定义广告名称
             * ad_code : /public/upload/ad/2016/09-19/57dfb0fbf3660.jpg
             */

            private String ad_link;
            private String ad_name;
            private String ad_code;

            public String getAd_link() {
                return ad_link;
            }

            public void setAd_link(String ad_link) {
                this.ad_link = ad_link;
            }

            public String getAd_name() {
                return ad_name;
            }

            public void setAd_name(String ad_name) {
                this.ad_name = ad_name;
            }

            public String getAd_code() {
                return ad_code;
            }

            public void setAd_code(String ad_code) {
                this.ad_code = ad_code;
            }
        }

        public class LineBean {
            /**
             * seller_id : 17
             * line_id : 1
             * line_buy_num : 20
             * city : 大阪
             * line_title : 我的标题很长很长可以用100年
             * cover_img : null
             * line_price : 99.00
             * create_at : 2017-09-14 00:00:00
             * star : 4
             */

            private int seller_id;
            private int line_id;
            private int line_buy_num;
            private String city;
            private String line_title;
            private String cover_img;
            private String line_price;
            private String create_at;
            private int star;

            public int getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }

            public int getLine_id() {
                return line_id;
            }

            public void setLine_id(int line_id) {
                this.line_id = line_id;
            }

            public int getLine_buy_num() {
                return line_buy_num;
            }

            public void setLine_buy_num(int line_buy_num) {
                this.line_buy_num = line_buy_num;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getLine_title() {
                return line_title;
            }

            public void setLine_title(String line_title) {
                this.line_title = line_title;
            }

            public String getCover_img() {
                return cover_img;
            }

            public void setCover_img(String cover_img) {
                this.cover_img = cover_img;
            }

            public String getLine_price() {
                return line_price;
            }

            public void setLine_price(String line_price) {
                this.line_price = line_price;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }
        }

        public class DriverBean {
            /**
             * seller_id : 17
             * head_pic : null
             * seller_name : 少秋
             * drv_code : 20170908-1
             * province : 朝阳门街道
             * city : 建国门街道
             * plat_start : null
             * star : 4
             * line : 我的标题很长很长可以用100年
             */

            private int seller_id;
            private String head_pic;
            private String seller_name;
            private String drv_code;
            private String province;
            private String city;
            private int plat_start;
            private int star;
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

            public int getPlat_start() {
                return plat_start;
            }

            public void setPlat_start(int plat_start) {
                this.plat_start = plat_start;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
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
