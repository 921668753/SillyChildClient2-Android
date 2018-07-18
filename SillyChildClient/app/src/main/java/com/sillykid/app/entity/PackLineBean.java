package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/9/17.
 */

public class PackLineBean extends BaseResult<PackLineBean.ResultBean> {


    public class ResultBean {
        private List<IndexBean> index;
        private List<BannerBean> banner;
        private List<LineBean> line;
        private List<DriverBeanX> driver;

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

        public List<DriverBeanX> getDriver() {
            return driver;
        }

        public void setDriver(List<DriverBeanX> driver) {
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
             * ad_name : 包车定制轮播1
             * ad_code : http://img.shahaizi.cn/43b4d201709251841584698.png
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
             * seller_id : 47
             * line_id : 36
             * line_buy_num : 2
             * city : null
             * line_title : 东京+富士山5日游
             * cover_img : http://img.shahaizi.cn/523ef201709260954421719.jpg
             * line_price : 4999.00
             * is_admin : 1
             * create_at : null
             * star : null
             * driver : {"id":47,"avatar":"http://img.shahaizi.cn/e0f1e4e087443c7c82a58d7cfdee8b1b.jpeg","nickname":"苏州吴彦祖","is_driver":1,"plat_start":"4","country_name":"","province_name":"","city_name":"","district_name":""}
             */

            private int seller_id;
            private int line_id;
            private String line_buy_num;
            private String city;
            private String line_title;
            private String cover_img;
            private String line_price;
            private int is_admin;
            private String create_at;
            private String star;
            private DriverBean driver;

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

            public String getLine_buy_num() {
                return line_buy_num;
            }

            public void setLine_buy_num(String line_buy_num) {
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

            public int getIs_admin() {
                return is_admin;
            }

            public void setIs_admin(int is_admin) {
                this.is_admin = is_admin;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public DriverBean getDriver() {
                return driver;
            }

            public void setDriver(DriverBean driver) {
                this.driver = driver;
            }

            public class DriverBean {
                /**
                 * id : 47
                 * avatar : http://img.shahaizi.cn/e0f1e4e087443c7c82a58d7cfdee8b1b.jpeg
                 * nickname : 苏州吴彦祖
                 * is_driver : 1
                 * plat_start : 4
                 * country_name :
                 * province_name :
                 * city_name :
                 * district_name :
                 */

                private int id;
                private String avatar;
                private String nickname;
                private int is_driver;
                private String plat_start;
                private String country_name;
                private String province_name;
                private String city_name;
                private String district_name;
                private String drv_code;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getIs_driver() {
                    return is_driver;
                }

                public void setIs_driver(int is_driver) {
                    this.is_driver = is_driver;
                }

                public String getPlat_start() {
                    return plat_start;
                }

                public void setPlat_start(String plat_start) {
                    this.plat_start = plat_start;
                }

                public String getCountry_name() {
                    return country_name;
                }

                public void setCountry_name(String country_name) {
                    this.country_name = country_name;
                }

                public String getProvince_name() {
                    return province_name;
                }

                public void setProvince_name(String province_name) {
                    this.province_name = province_name;
                }

                public String getCity_name() {
                    return city_name;
                }

                public void setCity_name(String city_name) {
                    this.city_name = city_name;
                }

                public String getDistrict_name() {
                    return district_name;
                }

                public void setDistrict_name(String district_name) {
                    this.district_name = district_name;
                }

                public String getDrv_code() {
                    return drv_code;
                }

                public void setDrv_code(String drv_code) {
                    this.drv_code = drv_code;
                }
            }
        }

        public class DriverBeanX {
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
            // private String seller_name;
            private String country;
            private String drv_code;
            private String province;
            private String city;
            private String plat_start;
            private String star;
            private String line;
            private String nickname;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

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

//            public String getSeller_name() {
//                return seller_name;
//            }
//
//            public void setSeller_name(String seller_name) {
//                this.seller_name = seller_name;
//            }

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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
