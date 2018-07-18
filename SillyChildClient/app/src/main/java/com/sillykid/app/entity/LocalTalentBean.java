package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 2017/8/15.
 */

public class LocalTalentBean extends BaseResult<LocalTalentBean.ResultBean> {


    public class ResultBean {
        /**
         * totalPages : 1
         * list : [{"talent_id":4,"title":"全台旅游来啦","cover_img":"https://hbcdn.huangbaoche.com//fr-jp/E9Tq8NnMWg0!l","summary":"各位朋友好 認識我 帶您全台快樂遊","video_url":null,"seller_id":17,"city":"中国·台湾","read_num":200,"name":"张若","drv_code":null,"store_id":null,"good_num":180,"desc":null,"status":"init","create_at":2017090715,"update_at":2017090718,"type_info":"店主-司导-房东"},{"talent_id":5,"title":"欢迎来新加坡","cover_img":"https://hbcdn.huangbaoche.com//fr-jp/El320BEmAA0!l","summary":"大家好，我是Cindy。来自于杭州。因为工作的关系，游历了大半个地球，最终还是落脚在了新加坡。原因就是新加坡是海外对华人认同感最强的地方。现在很开心能通过皇包车这一平台，把我所了解，所喜欢，所熟知的新加坡介绍给大家。欢迎大家来一场\u201c从新开始，新有灵犀\u201d的新加坡之旅！","video_url":null,"seller_id":17,"city":"新加坡","read_num":243,"name":"张云","drv_code":null,"store_id":null,"good_num":165,"desc":null,"status":"init","create_at":2017080720,"update_at":2017080813,"type_info":"店主-司导-房东"},{"talent_id":2,"title":"美丽台北","cover_img":"https://z0.muscache.com/im/pictures/fd2664c0-f9da-4f6c-861c-fdb5d2eb6e0b.jpg?aki_policy=large","summary":"哈喽，我是台北人朱碧石，欢迎大家来台北玩哦！","video_url":null,"seller_id":17,"city":"中国·台湾","read_num":123,"name":"朱碧石","drv_code":null,"store_id":null,"good_num":123,"desc":null,"status":"init","create_at":2017061808,"update_at":2017090317,"type_info":"店主-司导-房东"},{"talent_id":3,"title":"带你畅游京都","cover_img":"https://hbcdn.huangbaoche.com//fr-hd/DU7fgscTJA2!l","summary":"我是一名华裔日本人，具有中国人的热情好客与日本人的严谨认真的性格，熟悉两国文化。30年前全家回日本。本籍京都，在京都居住27年。年轻时开始从事观光巴士行业近20年，对关西地区更了解。拥有日本国內旅行管理者资格，有丰富的行程管理经验，对日本的历史风土人情能够较详细的讲解。根据客人的不同需要量身定制行程。尽力提供优质专业服务、得到多数客人的认可与评价。期待您来京都找我，带您体验独特愉快圆满的关西之旅。","video_url":null,"seller_id":17,"city":"日本·京都","read_num":122,"name":"古川雄辉","drv_code":null,"store_id":null,"good_num":123,"desc":null,"status":"init","create_at":2017071511,"update_at":2017071923,"type_info":"店主-司导-房东"},{"talent_id":1,"title":"洛杉矶之旅","cover_img":"https://z0.muscache.com/im/pictures/0816f919-1a63-4057-81ff-d31d9193feaa.jpg?aki_policy=large","summary":"大家好，我是洛杉矶人斯蒂芬，希望大家能来洛杉矶玩，我会带着你们来一场不一样的洛杉矶之旅~","video_url":null,"seller_id":17,"city":"中国·苏州","read_num":111,"name":"斯蒂芬","drv_code":null,"store_id":null,"good_num":111,"desc":null,"status":"init","create_at":2017060606,"update_at":2017080808,"type_info":"店主-司导-房东"}]
         */

        private int totalPages;
        private List<ListBean> list;

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
             * talent_id : 4
             * title : 全台旅游来啦
             * cover_img : https://hbcdn.huangbaoche.com//fr-jp/E9Tq8NnMWg0!l
             * summary : 各位朋友好 認識我 帶您全台快樂遊
             * video_url : null
             * seller_id : 17
             * city : 中国·台湾
             * is_admin=0
             * lable=1
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

            private String talent_id;
            private String title;
            private String cover_img;
            private String summary;
            private String video_url;
            private int seller_id;
            private String city;
            private int read_num;
            private String name;
            private int is_admin;
            private int lable;
            private String drv_code;
            private String store_id;
            private int good_num;
            private String desc;
            @SerializedName("status")
            private String statusX;
            private int create_at;
            private int update_at;
            private String type_info;

            public String getTalent_id() {
                return talent_id;
            }

            public void setTalent_id(String talent_id) {
                this.talent_id = talent_id;
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

            public int getIs_admin() {
                return is_admin;
            }

            public void setIs_admin(int is_admin) {
                this.is_admin = is_admin;
            }

            public int getLable() {
                return lable;
            }

            public void setLable(int lable) {
                this.lable = lable;
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

            public String getStatusX() {
                return statusX;
            }

            public void setStatusX(String statusX) {
                this.statusX = statusX;
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
}
