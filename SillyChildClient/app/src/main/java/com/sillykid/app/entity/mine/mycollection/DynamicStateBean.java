package com.sillykid.app.entity.mine.mycollection;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class DynamicStateBean extends BaseResult<List<DynamicStateBean.DataBean>> {


    public class DataBean {
        /**
         * id : 1
         * post_title : 京都5日修禅记
         * picture : http://img.shahaizhi.com/S31531216060
         * favorite_id : 49
         * type_id : 3
         * nickname : 开玩笑的啦
         * face : http://thirdqq.qlogo.cn/qqapp/1106946302/7CBFE9E58C5B66CCCC5130E7E6A51BA4/100
         * concern_number : 2
         * post_number : 5
         */

        private int id;
        private String post_title;
        private String picture;
        private int favorite_id;
        private int type_id;
        private String nickname;
        private String face;
        private String concern_number;
        private String post_number;
        private int height;
        private int width;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPost_title() {
            return post_title;
        }

        public void setPost_title(String post_title) {
            this.post_title = post_title;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getFavorite_id() {
            return favorite_id;
        }

        public void setFavorite_id(int favorite_id) {
            this.favorite_id = favorite_id;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getConcern_number() {
            return concern_number;
        }

        public void setConcern_number(String concern_number) {
            this.concern_number = concern_number;
        }

        public String getPost_number() {
            return post_number;
        }

        public void setPost_number(String post_number) {
            this.post_number = post_number;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
