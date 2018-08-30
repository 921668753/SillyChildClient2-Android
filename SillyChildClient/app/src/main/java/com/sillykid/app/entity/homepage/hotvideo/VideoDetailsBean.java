package com.sillykid.app.entity.homepage.hotvideo;

import com.common.cklibrary.entity.BaseResult;
import com.sillykid.app.entity.community.dynamic.DynamicDetailsBean;

import java.util.List;

public class VideoDetailsBean extends BaseResult<VideoDetailsBean.DataBean> {


    public class DataBean {
        /**
         * collection_number : 0
         * comment : [{"body":"你好","create_time":"2018-07-31 10:49:18","face":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83erVaYiaEpP1VibXTX4yFHPicaShMSQYgmzwbVuB3UCbGtMQmFP6K8c4VATujp09iatnf5P2mekR2licv7g/132","id":21,"member_id":12,"nickname":"粉红色叶子","post_id":1,"replyList":[{"id":22,"member_id":14,"nickname":"天若有情","post_id":"1","reply_body":"回复的消息","reply_comment_id":21,"reply_face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg","reply_member_id":12,"reply_nickname":"粉红色叶子","reply_time":"2018-07-31 10:49:18","type":2}],"reply_comment_id":0,"reply_member_id":0,"type":2}]
         * face : http://img.shahaizhi.com/logo.png
         * id : 1
         * is_collect : 0
         * is_like : 0
         * is_recommend : 1
         * like_number : 0
         * member_id : 1
         * nickname : 傻孩志
         * review_number : 1
         * type : 2
         * video_description : 本视频内容由樱蓝公司提供
         * video_image : http://ovwiqces1.bkt.clouddn.com/FmmFKBC8Jrrlt0TUNoAtpQ-ZoORa
         * video_title : 傻孩志在土耳其
         * video_url : http://ovwiqces1.bkt.clouddn.com/lg80xQZKfudCwqqbpVDW8QVFdPqN
         * watch_number : 18
         */
        private String create_time;
        private String collection_number;
        private String face;
        private int id;
        private int is_collect;
        private int is_like;
        private int is_recommend;
        private String like_number;
        private int member_id;
        private String nickname;
        private String review_number;
        private int type;
        private String video_description;
        private String video_image;
        private String video_title;
        private String video_url;
        private int watch_number;
        private List<DynamicDetailsBean.DataBean.CommentBean> comment;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getCollection_number() {
            return collection_number;
        }

        public void setCollection_number(String collection_number) {
            this.collection_number = collection_number;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public int getIs_like() {
            return is_like;
        }

        public void setIs_like(int is_like) {
            this.is_like = is_like;
        }

        public int getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(int is_recommend) {
            this.is_recommend = is_recommend;
        }

        public String getLike_number() {
            return like_number;
        }

        public void setLike_number(String like_number) {
            this.like_number = like_number;
        }

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

        public String getReview_number() {
            return review_number;
        }

        public void setReview_number(String review_number) {
            this.review_number = review_number;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getVideo_description() {
            return video_description;
        }

        public void setVideo_description(String video_description) {
            this.video_description = video_description;
        }

        public String getVideo_image() {
            return video_image;
        }

        public void setVideo_image(String video_image) {
            this.video_image = video_image;
        }

        public String getVideo_title() {
            return video_title;
        }

        public void setVideo_title(String video_title) {
            this.video_title = video_title;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public int getWatch_number() {
            return watch_number;
        }

        public void setWatch_number(int watch_number) {
            this.watch_number = watch_number;
        }

        public List<DynamicDetailsBean.DataBean.CommentBean> getComment() {
            return comment;
        }

        public void setComment(List<DynamicDetailsBean.DataBean.CommentBean> comment) {
            this.comment = comment;
        }

    }
}
