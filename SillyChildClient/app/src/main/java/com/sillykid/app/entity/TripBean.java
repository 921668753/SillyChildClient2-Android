package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 2017/10/13.
 */

public class TripBean extends BaseResult<TripBean.ResultBean> {

    public class ResultBean {
        private List<IndexBean> index;
        private List<BannerBean> banner;
        private List<GuideListBean> guideList;
        private List<ReliableDrvBean> reliable_drv;

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

        public List<GuideListBean> getGuideList() {
            return guideList;
        }

        public void setGuideList(List<GuideListBean> guideList) {
            this.guideList = guideList;
        }

        public List<ReliableDrvBean> getReliable_drv() {
            return reliable_drv;
        }

        public void setReliable_drv(List<ReliableDrvBean> reliable_drv) {
            this.reliable_drv = reliable_drv;
        }

        public class IndexBean {
            /**
             * id : 7
             * name : 签证
             * type : going_index
             * sort : 1
             */

            private int id;
            private String name;
            private String type;
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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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
             * ad_link :
             * ad_name : 自定义广告名称
             * ad_code : http://img.shahaizi.cn/43594201710131143077397.jpg
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

        public class GuideListBean {
            /**
             * guide_id : 2
             * title : 日本·小樽
             * cover_img : http://img.shahaizi.cn/638c1201710101627158502.jpg
             * summary : 小樽是日本北海道西南部港市，札幌的外港，大约在100 年前作为北海道的海上大门发展起来，异常繁荣，不少银行和企业纷纷来此发展，被人称为“北方的华尔街”。
             * user_id : 0
             * user_name : 平台
             * content : <p></p>
             * read_num : 27
             * status : 1
             * publish_time : 1507624260
             * country_id : 8
             * city_id : 50000
             * is_admin : 1
             * is_hot : 1
             * sort : null
             * create_at : 1507624308
             * update_at : 1507871095
             * city : 日本·东京
             * country : 日本
             * praiseNum : 0
             * owner : null
             */

            private int guide_id;
            private String title;
            private String cover_img;
            private String summary;
            private int user_id;
            private String user_name;
            private String content;
            private int read_num;
            @SerializedName("status")
            private int statusX;
            private int publish_time;
            private int country_id;
            private int city_id;
            private int is_admin;
            private int is_hot;
            private Object sort;
            private int create_at;
            private int update_at;
            private String city;
            private String country;
            private int praiseNum;
            private Object owner;
            private String statusL;


            public int getGuide_id() {
                return guide_id;
            }

            public void setGuide_id(int guide_id) {
                this.guide_id = guide_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCover_img() {
                return cover_img;
            }

            public void setCover_img(String cover_img) {
                this.cover_img = cover_img;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getRead_num() {
                return read_num;
            }

            public void setRead_num(int read_num) {
                this.read_num = read_num;
            }

            public int getStatusX() {
                return statusX;
            }

            public void setStatusX(int statusX) {
                this.statusX = statusX;
            }

            public int getPublish_time() {
                return publish_time;
            }

            public void setPublish_time(int publish_time) {
                this.publish_time = publish_time;
            }

            public int getCountry_id() {
                return country_id;
            }

            public void setCountry_id(int country_id) {
                this.country_id = country_id;
            }

            public int getCity_id() {
                return city_id;
            }

            public void setCity_id(int city_id) {
                this.city_id = city_id;
            }

            public int getIs_admin() {
                return is_admin;
            }

            public void setIs_admin(int is_admin) {
                this.is_admin = is_admin;
            }

            public int getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(int is_hot) {
                this.is_hot = is_hot;
            }

            public Object getSort() {
                return sort;
            }

            public void setSort(Object sort) {
                this.sort = sort;
            }

            public int getCreate_at() {
                return create_at;
            }

            public void setCreate_at(int create_at) {
                this.create_at = create_at;
            }

            public int getUpdate_at() {
                return update_at;
            }

            public void setUpdate_at(int update_at) {
                this.update_at = update_at;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(int praiseNum) {
                this.praiseNum = praiseNum;
            }

            public Object getOwner() {
                return owner;
            }

            public void setOwner(Object owner) {
                this.owner = owner;
            }

            public String getStatusL() {
                return statusL;
            }

            public void setStatusL(String statusL) {
                this.statusL = statusL;
            }
        }

        public class ReliableDrvBean {
            /**
             * seller_id : 1
             * head_pic : http://img.shahaizi.cn/3ce28e0bb34ab33ff27eece52706adf8.jpeg
             * seller_name :
             * drv_code : 20171010-1
             * province : 江苏省
             * city : 苏州市
             * plat_start : 4
             * star : 5
             * line : 互相学习我
             */

            private int seller_id;
            private String head_pic;
            private String seller_name;
            private String drv_code;
            private String province;
            private String city;
            private String plat_start;
            private int star;
            private String line;
            private String statusL;

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

            public String getStatusL() {
                return statusL;
            }

            public void setStatusL(String statusL) {
                this.statusL = statusL;
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
