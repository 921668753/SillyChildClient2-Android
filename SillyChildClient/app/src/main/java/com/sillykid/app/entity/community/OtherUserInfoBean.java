package com.sillykid.app.entity.community;


import com.common.cklibrary.entity.BaseResult;

public class OtherUserInfoBean extends BaseResult<OtherUserInfoBean.DataBean> {


    public class DataBean {
        /**
         * member_id : 11
         * concern_number : 0
         * face : http://thirdqq.qlogo.cn/qqapp/1106946302/7CBFE9E58C5B66CCCC5130E7E6A51BA4/100
         * fans_number : 2
         * signature : 这家伙什么也没留下 ^V^!
         * like_number : 2
         * is_concern : 0
         * nickname : 开玩笑的啦
         * collected_number : 2
         */

        private int member_id;
        private String concern_number;
        private String face;
        private String fans_number;
        private String signature;
        private String like_number;
        private int is_concern;
        private String nickname;
        private String collected_number;
        private String shz;
        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getConcern_number() {
            return concern_number;
        }

        public void setConcern_number(String concern_number) {
            this.concern_number = concern_number;
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

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getLike_number() {
            return like_number;
        }

        public void setLike_number(String like_number) {
            this.like_number = like_number;
        }

        public int getIs_concern() {
            return is_concern;
        }

        public void setIs_concern(int is_concern) {
            this.is_concern = is_concern;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCollected_number() {
            return collected_number;
        }

        public void setCollected_number(String collected_number) {
            this.collected_number = collected_number;
        }

        public String getShz() {
            return shz;
        }

        public void setShz(String shz) {
            this.shz = shz;
        }
    }
}