package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/9/7.
 */

public class LocalTalentDetailsBean extends BaseResult<LocalTalentDetailsBean.ResultBean> {


    public class ResultBean {
        /**
         * talent_id : 4
         * title : 全台旅游来啦
         * cover_img : https://hbcdn.huangbaoche.com//fr-jp/E9Tq8NnMWg0!l
         * summary : 各位朋友好 認識我 帶您全台快樂遊
         * video_url : null
         * seller_id : 17
         * city : 中国·台湾
         * read_num : 200
         * name : 张若
         * drv_code : null
         * store_id : null
         * good_num : 180
         * desc : null
         * status : init
         * create_at : 2017090715
         * update_at : 2017090718
         * type_info : 店主-司导-房东
         */

        private int talent_id;
        private String title;
        private String cover_img;
        private String summary;
        private String content;
        private String video_url;
        private int seller_id;
        private String city;
        private int is_good;
        private int read_num;
        private String name;
        private String drv_code;
        private String store_id;
        private String user_id;
        private int lable;
        private int is_admin;
        private int good_num;
        private String desc;
        private String status;
        private int create_at;
        private int update_at;
        private String type_info;

        public int getIs_admin() {
            return is_admin;
        }

        public void setIs_admin(int is_admin) {
            this.is_admin = is_admin;
        }

        public int getIs_good() {
            return is_good;
        }

        public void setIs_good(int is_good) {
            this.is_good = is_good;
        }

        public int getTalent_id() {
            return talent_id;
        }

        public void setTalent_id(int talent_id) {
            this.talent_id = talent_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getLable() {
            return lable;
        }

        public void setLable(int lable) {
            this.lable = lable;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public int getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(int seller_id) {
            this.seller_id = seller_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getRead_num() {
            return read_num;
        }

        public void setRead_num(int read_num) {
            this.read_num = read_num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDrv_code() {
            return drv_code;
        }

        public void setDrv_code(String drv_code) {
            this.drv_code = drv_code;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public int getGood_num() {
            return good_num;
        }

        public void setGood_num(int good_num) {
            this.good_num = good_num;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getType_info() {
            return type_info;
        }

        public void setType_info(String type_info) {
            this.type_info = type_info;
        }
    }
}
