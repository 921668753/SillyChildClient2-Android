package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/10/24.
 */

public class StrategyDetailsBean extends BaseResult<StrategyDetailsBean.ResultBean> {

    /**
     * status : 1
     * msg : 成功
     * result : {"info":{"guide_id":6,"title":"北海道","cover_img":"http://img.shahaizi.cn/c14814ec10acd82d763ce6f0d4062e0d.png","summary":"游啊游啊游啊游啊游啊游啊","user_id":5,"user_name":"横着走的螃蟹","content":"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;好多美食 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<\/p>","read_num":37,"status":1,"publish_time":1508498460,"country_id":1,"city_id":47500,"is_admin":1,"is_hot":1,"sort":null,"create_at":"2017-10-20 19:21:57","update_at":"2017-10-24 11:33:18","city":"亚洲·日本·北海道"},"comment":[]}
     */

    public class ResultBean {
        /**
         * info : {"guide_id":6,"title":"北海道","cover_img":"http://img.shahaizi.cn/c14814ec10acd82d763ce6f0d4062e0d.png","summary":"游啊游啊游啊游啊游啊游啊","user_id":5,"user_name":"横着走的螃蟹","content":"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;好多美食 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<\/p>","read_num":37,"status":1,"publish_time":1508498460,"country_id":1,"city_id":47500,"is_admin":1,"is_hot":1,"sort":null,"create_at":"2017-10-20 19:21:57","update_at":"2017-10-24 11:33:18","city":"亚洲·日本·北海道"}
         * comment : []
         */

        private InfoBean info;
        private List<?> comment;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<?> getComment() {
            return comment;
        }

        public void setComment(List<?> comment) {
            this.comment = comment;
        }

        public class InfoBean {
            /**
             * guide_id : 6
             * title : 北海道
             * cover_img : http://img.shahaizi.cn/c14814ec10acd82d763ce6f0d4062e0d.png
             * summary : 游啊游啊游啊游啊游啊游啊
             * user_id : 5
             * user_name : 横着走的螃蟹
             * content : <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;好多美食 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>
             * read_num : 37
             * status : 1
             * publish_time : 1508498460
             * country_id : 1
             * city_id : 47500
             * is_admin : 1
             * is_hot : 1
             * sort : null
             * create_at : 2017-10-20 19:21:57
             * update_at : 2017-10-24 11:33:18
             * city : 亚洲·日本·北海道
             */

            private int guide_id;
            private String title;
            private String cover_img;
            private String summary;
            private int user_id;
            private int isPraise;
            private int isCollect;
            private String user_name;
            private String content;
            private String read_num;
            private int status;
            private int publish_time;
            private int country_id;
            private int city_id;
            private int is_admin;
            private int is_hot;
            private Object sort;
            private String create_at;
            private String update_at;
            private String city;

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

            public int getIsPraise() {
                return isPraise;
            }

            public void setIsPraise(int isPraise) {
                this.isPraise = isPraise;
            }

            public int getIsCollect() {
                return isCollect;
            }

            public void setIsCollect(int isCollect) {
                this.isCollect = isCollect;
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

            public String getRead_num() {
                return read_num;
            }

            public void setRead_num(String read_num) {
                this.read_num = read_num;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public String getUpdate_at() {
                return update_at;
            }

            public void setUpdate_at(String update_at) {
                this.update_at = update_at;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }
        }
    }
}
