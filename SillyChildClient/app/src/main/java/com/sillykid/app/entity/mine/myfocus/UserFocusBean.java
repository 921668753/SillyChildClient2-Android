package com.sillykid.app.entity.mine.myfocus;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserFocusBean extends BaseResult<UserFocusBean.DataBean> {


    public class DataBean {
        /**
         * pageSize : 10
         * totalCount : 2
         * currentPageNo : 1
         * draw : 0
         * result : [{"id":57,"member_id":14,"type_id":1,"create_time":"2018-07-24 15:12:43","user_id":12,"nickname":"天若有情","face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg","fans_number":4,"post_number":null},{"id":60,"member_id":14,"type_id":1,"create_time":"2018-07-24 15:16:50","user_id":13,"nickname":"天若有情","face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg","fans_number":2,"post_number":1}]
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
             * id : 57
             * member_id : 14
             * type_id : 1
             * create_time : 2018-07-24 15:12:43
             * user_id : 12
             * nickname : 天若有情
             * face : http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg
             * fans_number : 4
             * post_number : null
             */

            private int id;
            private int member_id;
            private int type_id;
            private String create_time;
            private int user_id;
            private String nickname;
            private String face;
            private String fans_number;
            private String post_number;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
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

            public String getFans_number() {
                return fans_number;
            }

            public void setFans_number(String fans_number) {
                this.fans_number = fans_number;
            }

            public String getPost_number() {
                return post_number;
            }

            public void setPost_number(String post_number) {
                this.post_number = post_number;
            }
        }
    }
}
