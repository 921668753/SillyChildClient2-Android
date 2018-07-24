package com.sillykid.app.entity.main.community;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommunityBean extends BaseResult<CommunityBean.DataBean> {


    public class DataBean {
        /**
         * pageSize : 10
         * totalCount : 2
         * currentPageNo : 1
         * draw : 0
         * result : [{"id":1,"post_title":"京都5日修禅记","create_time":"2018-07-18 14:06:20","member_id":11,"picture":"http://img.shahaizhi.com/S31531216060","content":"这是个文本内容比较丰富的素材文件，好多有趣的人和事提供给我们","classification_id":1000000,"nickname":"开玩笑的啦","face":"http://thirdqq.qlogo.cn/qqapp/1106946302/7CBFE9E58C5B66CCCC5130E7E6A51BA4/100","concern_number":2},{"id":2,"post_title":"上海一日游","create_time":"2018-01-20 10:46:50","member_id":11,"picture":"http://img.shahaizhi.com/S31531216060","content":"这是个文本内容比较丰富的素材文件，好多有趣的人和事提供给我们","classification_id":1000000,"nickname":"开玩笑的啦","face":"http://thirdqq.qlogo.cn/qqapp/1106946302/7CBFE9E58C5B66CCCC5130E7E6A51BA4/100","concern_number":2}]
         * totalPageCount : 1
         */

        private int pageSize;
        private int totalCount;
        private int currentPageNo;
        private int draw;
        private int totalPageCount;
        @SerializedName("result")
        private List<ResultBean> resultX;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
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

        public int getDraw() {
            return draw;
        }

        public void setDraw(int draw) {
            this.draw = draw;
        }

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public List<ResultBean> getResultX() {
            return resultX;
        }

        public void setResultX(List<ResultBean> resultX) {
            this.resultX = resultX;
        }

        public class ResultBean {
            /**
             * id : 1
             * post_title : 京都5日修禅记
             * create_time : 2018-07-18 14:06:20
             * member_id : 11
             * picture : http://img.shahaizhi.com/S31531216060
             * content : 这是个文本内容比较丰富的素材文件，好多有趣的人和事提供给我们
             * classification_id : 1000000
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
            private int classification_id;
            private String nickname;
            private String face;
            private int height;
            private int width;
            private String concern_number;
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

            public int getClassification_id() {
                return classification_id;
            }

            public void setClassification_id(int classification_id) {
                this.classification_id = classification_id;
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

            public String getConcern_number() {
                return concern_number;
            }

            public void setConcern_number(String concern_number) {
                this.concern_number = concern_number;
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
