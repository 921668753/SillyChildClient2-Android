package com.sillykid.app.entity.homepage.airporttransportation.comments;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharterCommentsBean extends BaseResult<CharterCommentsBean.DataBean> {


    public class DataBean {
        /**
         * pageSize : 10
         * totalCount : 5
         * currentPageNo : 1
         * draw : 0
         * result : [{"id":4,"content":"图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张","satisfaction_level":4,"picture":["http://img.shahaizhi.com/FjH7Pcnpe9125201J4p9u5W4JgFI","http://img.shahaizhi.com/FvhSmyH2917ZqcxJd8WM5AX5nSQE","http://img.shahaizhi.com/FtTHoDI8a9wVJVlsFfemKnlunvY6","http://img.shahaizhi.com/FjEWlgr2MGMQ_HKYfQ3SIVzQFRVM"],"create_time":"1535357131","nickname":"彪哥","face":"http://img.shahaizhi.com/FicHB-Gmwfz9q6G_Y6eLZe8kI4ER","like_number":0,"is_like":false}]
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
             * id : 4
             * content : 图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张
             * satisfaction_level : 4
             * picture : ["http://img.shahaizhi.com/FjH7Pcnpe9125201J4p9u5W4JgFI","http://img.shahaizhi.com/FvhSmyH2917ZqcxJd8WM5AX5nSQE","http://img.shahaizhi.com/FtTHoDI8a9wVJVlsFfemKnlunvY6","http://img.shahaizhi.com/FjEWlgr2MGMQ_HKYfQ3SIVzQFRVM"]
             * create_time : 1535357131
             * nickname : 彪哥
             * face : http://img.shahaizhi.com/FicHB-Gmwfz9q6G_Y6eLZe8kI4ER
             * like_number : 0
             * is_like : false
             */

            private int id;
            private String content;
            private int satisfaction_level;
            private String create_time;
            private String nickname;
            private String face;
            private int like_number;
            private boolean is_like;
            private List<String> picture;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getSatisfaction_level() {
                return satisfaction_level;
            }

            public void setSatisfaction_level(int satisfaction_level) {
                this.satisfaction_level = satisfaction_level;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
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

            public int getLike_number() {
                return like_number;
            }

            public void setLike_number(int like_number) {
                this.like_number = like_number;
            }

            public boolean isIs_like() {
                return is_like;
            }

            public void setIs_like(boolean is_like) {
                this.is_like = is_like;
            }

            public List<String> getPicture() {
                return picture;
            }

            public void setPicture(List<String> picture) {
                this.picture = picture;
            }
        }
    }
}
