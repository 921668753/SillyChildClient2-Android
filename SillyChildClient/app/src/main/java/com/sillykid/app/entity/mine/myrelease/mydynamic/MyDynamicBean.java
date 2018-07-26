package com.sillykid.app.entity.mine.myrelease.mydynamic;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class MyDynamicBean extends BaseResult<MyDynamicBean.DataBean> {


    public class DataBean {
        /**
         * pageSize : 10
         * totalCount : 5
         * currentPageNo : 1
         * draw : 0
         * result : [{"id":6,"post_title":"金都","create_time":"2018-01-20 10:46:51","member_id":14,"picture":"http://img.shahaizhi.com/S31531216060","content":"这是个文本内容比较丰富的素材文件，好多有趣的人和事提供给我们","classification_id":1000000,"nickname":"天若有情","face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg"},{"id":7,"post_title":"上海5日游","create_time":"2018-01-20 10:46:52","member_id":14,"picture":"http://img.shahaizhi.com/0a12b201709231156198416.jpg","content":"这是个文本内容比较丰富的素材文件，好多有趣","classification_id":1000001,"nickname":"天若有情","face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg"},{"id":8,"post_title":"北京三日游","create_time":"2018-01-20 10:46:53","member_id":14,"picture":"http://img.shahaizhi.com/%28null%291530252832","content":"这是个文本内容比较丰富的素材文件，好多有趣的人和事提","classification_id":1000001,"nickname":"天若有情","face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg"},{"id":9,"post_title":"日本两日游","create_time":"2018-01-20 10:46:54","member_id":14,"picture":"http://img.shahaizhi.com/0a1dc20f4be3d3269a72277428dc5ebc.png","content":"这是个文本内容比较丰富的素材文件，好","classification_id":1000001,"nickname":"天若有情","face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg"},{"id":10,"post_title":"日本三日游","create_time":"2018-01-20 10:46:55","member_id":14,"picture":"http://img.shahaizhi.com/0bdcf201712051444224495.jpeg","content":"这是个文本内容比较丰富这是个文本内容比较丰富这是个文本内容比较丰富这是个文本内容比较丰富这是个文本内容比较丰富","classification_id":1000001,"nickname":"天若有情","face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg"}]
         * totalPageCount : 1
         */

        private int pageSize;
        private int totalCount;
        private int currentPageNo;
        private int draw;
        private int totalPageCount;
        private List<ResultBean> result;

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

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public class ResultBean {
            /**
             * id : 6
             * post_title : 金都
             * create_time : 2018-01-20 10:46:51
             * member_id : 14
             * picture : http://img.shahaizhi.com/S31531216060
             * content : 这是个文本内容比较丰富的素材文件，好多有趣的人和事提供给我们
             * classification_id : 1000000
             * nickname : 天若有情
             * face : http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg
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
            private int type;
            private int height;
            private int width;

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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
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
        }
    }
}
