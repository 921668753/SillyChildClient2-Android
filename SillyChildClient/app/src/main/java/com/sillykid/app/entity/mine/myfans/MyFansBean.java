package com.sillykid.app.entity.mine.myfans;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class MyFansBean extends BaseResult<MyFansBean.DataBean> {

    public class DataBean {
        /**
         * pageSize : 10
         * totalCount : 4
         * currentPageNo : 1
         * draw : 0
         * result : [{"id":25,"member_id":12,"type_id":1,"user_id":14,"face":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83erVaYiaEpP1VibXTX4yFHPicaShMSQYgmzwbVuB3UCbGtMQmFP6K8c4VATujp09iatnf5P2mekR2licv7g/132","nickname":"粉红色叶子","post_number":null,"fans_number":2,"is_concern":null},{"id":26,"member_id":13,"type_id":1,"user_id":14,"face":"http://thirdqq.qlogo.cn/qqapp/1106946302/540B2058F11FE47600B4EC1D65A17835/100","nickname":"15675198215","post_number":null,"fans_number":null,"is_concern":null},{"id":27,"member_id":15,"type_id":1,"user_id":14,"face":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83erVaYiaEpP1VibXTX4yFHPicaShMSQYgmzwbVuB3UCbGtMQmFP6K8c4VATujp09iatnf5P2mekR2licv7g/132","nickname":"17051335258","post_number":null,"fans_number":null,"is_concern":null},{"id":28,"member_id":1,"type_id":1,"user_id":14,"face":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83erVaYiaEpP1VibXTX4yFHPicaShMSQYgmzwbVuB3UCbGtMQmFP6K8c4VATujp09iatnf5P2mekR2licv7g/132","nickname":"javashop","post_number":null,"fans_number":1,"is_concern":null}]
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
             * id : 25
             * member_id : 12
             * type_id : 1
             * user_id : 14
             * face : http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83erVaYiaEpP1VibXTX4yFHPicaShMSQYgmzwbVuB3UCbGtMQmFP6K8c4VATujp09iatnf5P2mekR2licv7g/132
             * nickname : 粉红色叶子
             * post_number : null
             * fans_number : 2
             * is_concern : null
             */

            private int id;
            private int member_id;
            private int type_id;
            private int user_id;
            private String face;
            private String nickname;
            private String post_number;
            private String fans_number;
            private String is_concern;

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

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
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

            public String getIs_concern() {
                return is_concern;
            }

            public void setIs_concern(String is_concern) {
                this.is_concern = is_concern;
            }
        }
    }
}
