package com.sillykid.app.entity.community;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class OtherUserPostBean extends BaseResult<OtherUserPostBean.DataBean> {


    public class DataBean {
        /**
         * totalPageCount : 1
         * list : [{"id":1,"post_title":"京都5日修禅记","create_time":"2018-07-18 14:06:20","member_id":11,"picture":"http://img.shahaizhi.com/S31531216060","content":"这是个文本内容比较丰富的素材文件，好多有趣的人和事提供给我们","nickname":"开玩笑的啦","face":"http://thirdqq.qlogo.cn/qqapp/1106946302/7CBFE9E58C5B66CCCC5130E7E6A51BA4/100","concern_number":2}]
         * totalCount : 1
         * currentPageNo : 1
         */

        private int totalPageCount;
        private int totalCount;
        private int currentPageNo;
        private List<ListBean> list;

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getCurrentPageNo() {
            return currentPageNo;
        }

        public void setCurrentPageNo(int currentPageNo) {
            this.currentPageNo = currentPageNo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean {
            /**
             * id : 1
             * post_title : 京都5日修禅记
             * create_time : 2018-07-18 14:06:20
             * member_id : 11
             * picture : http://img.shahaizhi.com/S31531216060
             * content : 这是个文本内容比较丰富的素材文件，好多有趣的人和事提供给我们
             * nickname : 开玩笑的啦
             * face : http://thirdqq.qlogo.cn/qqapp/1106946302/7CBFE9E58C5B66CCCC5130E7E6A51BA4/100
             * concern_number : 2
             */

            private int id;
            private String post_title;
            private String create_time;
            private int member_id;
            private String picture;
            private String content;
            private String nickname;
            private String face;
            private String concern_number;
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

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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
}
