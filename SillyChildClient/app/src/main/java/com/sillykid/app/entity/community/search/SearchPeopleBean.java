package com.sillykid.app.entity.community.search;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class SearchPeopleBean extends BaseResult<SearchPeopleBean.DataBean> {


    public class DataBean {
        /**
         * totalPageCount : 1
         * list : [{"member_id":9,"nickname":"天若有情","face":"http://img.shahaizhi.com/SHZS_S9_IMG_20180627_134618.jpg","post_number":0,"fans_number":0},{"member_id":14,"nickname":"天若有情","face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg","post_number":1,"fans_number":4},{"member_id":14,"nickname":"天若有情","face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg","post_number":1,"fans_number":4},{"member_id":14,"nickname":"天若有情","face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg","post_number":1,"fans_number":4},{"member_id":14,"nickname":"天若有情","face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg","post_number":1,"fans_number":4},{"member_id":14,"nickname":"天若有情","face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg","post_number":1,"fans_number":4}]
         * totalCount : 2
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
             * member_id : 9
             * nickname : 天若有情
             * face : http://img.shahaizhi.com/SHZS_S9_IMG_20180627_134618.jpg
             * post_number : 0
             * fans_number : 0
             */

            private int member_id;
            private String nickname;
            private String face;
            private String post_number;
            private String fans_number;

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
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

            public String getPost_number() {
                return post_number;
            }

            public void setPost_number(String post_number) {
                this.post_number = post_number;
            }

            public String getFans_number() {
                return fans_number;
            }

            public void setFans_number(String fans_number) {
                this.fans_number = fans_number;
            }
        }
    }
}
