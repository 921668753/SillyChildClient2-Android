package com.sillykid.app.entity.community.dynamic;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class DynamicDetailsBean extends BaseResult<DynamicDetailsBean.DataBean> {


    public class DataBean {
        /**
         * attention_number : 8
         * classification_id : 1000000
         * click_number : 1015
         * collection_number : 8
         * comment : [{"body":"这是一条主评论","create_time":"2018-07-23 09:02:30","face":"http://thirdqq.qlogo.cn/qqapp/1106946302/540B2058F11FE47600B4EC1D65A17835/100","id":2,"member_id":13,"nickname":"15675198215","post_id":1,"replyList":[{"id":3,"member_id":14,"nickname":"天若有情","post_id":"1","reply_body":"这是一条子评论","reply_comment_id":2,"reply_face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg","reply_member_id":12,"reply_nickname":"粉红色叶子","reply_time":"2018-07-23 09:02:50","type":1},{"id":4,"member_id":13,"nickname":"15675198215","post_id":"1","reply_body":"回复的回复","reply_comment_id":2,"reply_face":"http://thirdqq.qlogo.cn/qqapp/1106946302/540B2058F11FE47600B4EC1D65A17835/100","reply_member_id":14,"reply_nickname":"天若有情","reply_time":"2018-07-23-10:55:50","type":1}],"reply_comment_id":0,"reply_member_id":0,"type":1}]
         * content : 这是个文本内容比较丰富的素材文件，好多有趣的人和事提供给我们
         * create_time : 2018-07-18 14:06:20
         * id : 1
         * is_collect : 8
         * is_concern : 0
         * is_like : 0
         * is_recommend : 1
         * like_number : 8
         * list : ["http://img.shahaizhi.com/S31531216060","http://img.shahaizhi.com/S31531216060","http://img.shahaizhi.com/S31531216060","http://img.shahaizhi.com/S31531216060"]
         * member_id : 11
         * post_title : 京都5日修禅记
         * review_number : 8
         * type : 1
         */

        private String attention_number;
        private int classification_id;
        private String click_number;
        private String collection_number;
        private String content;
        private String create_time;
        private int id;
        private int is_collect;
        private int is_concern;
        private int is_like;
        private int is_recommend;
        private String like_number;
        private int member_id;
        private String post_title;
        private String review_number;
        private String face;
        private String nickname;
        private int type;
        private List<CommentBean> comment;
        private List<String> list;

        public String getAttention_number() {
            return attention_number;
        }

        public void setAttention_number(String attention_number) {
            this.attention_number = attention_number;
        }

        public int getClassification_id() {
            return classification_id;
        }

        public void setClassification_id(int classification_id) {
            this.classification_id = classification_id;
        }

        public String getClick_number() {
            return click_number;
        }

        public void setClick_number(String click_number) {
            this.click_number = click_number;
        }

        public String getCollection_number() {
            return collection_number;
        }

        public void setCollection_number(String collection_number) {
            this.collection_number = collection_number;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
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

        public int getIs_concern() {
            return is_concern;
        }

        public void setIs_concern(int is_concern) {
            this.is_concern = is_concern;
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

        public String getPost_title() {
            return post_title;
        }

        public void setPost_title(String post_title) {
            this.post_title = post_title;
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

        public List<CommentBean> getComment() {
            return comment;
        }

        public void setComment(List<CommentBean> comment) {
            this.comment = comment;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
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

        public class CommentBean {
            /**
             * body : 这是一条主评论
             * create_time : 2018-07-23 09:02:30
             * face : http://thirdqq.qlogo.cn/qqapp/1106946302/540B2058F11FE47600B4EC1D65A17835/100
             * id : 2
             * member_id : 13
             * nickname : 15675198215
             * post_id : 1
             * replyList : [{"id":3,"member_id":14,"nickname":"天若有情","post_id":"1","reply_body":"这是一条子评论","reply_comment_id":2,"reply_face":"http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg","reply_member_id":12,"reply_nickname":"粉红色叶子","reply_time":"2018-07-23 09:02:50","type":1},{"id":4,"member_id":13,"nickname":"15675198215","post_id":"1","reply_body":"回复的回复","reply_comment_id":2,"reply_face":"http://thirdqq.qlogo.cn/qqapp/1106946302/540B2058F11FE47600B4EC1D65A17835/100","reply_member_id":14,"reply_nickname":"天若有情","reply_time":"2018-07-23-10:55:50","type":1}]
             * reply_comment_id : 0
             * reply_member_id : 0
             * type : 1
             */

            private String body;
            private String create_time;
            private String face;
            private int id;
            private int member_id;
            private String nickname;
            private int post_id;
            private int reply_comment_id;
            private int reply_member_id;
            private int type;
            private List<ReplyListBean> replyList;

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
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

            public int getPost_id() {
                return post_id;
            }

            public void setPost_id(int post_id) {
                this.post_id = post_id;
            }

            public int getReply_comment_id() {
                return reply_comment_id;
            }

            public void setReply_comment_id(int reply_comment_id) {
                this.reply_comment_id = reply_comment_id;
            }

            public int getReply_member_id() {
                return reply_member_id;
            }

            public void setReply_member_id(int reply_member_id) {
                this.reply_member_id = reply_member_id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<ReplyListBean> getReplyList() {
                return replyList;
            }

            public void setReplyList(List<ReplyListBean> replyList) {
                this.replyList = replyList;
            }

            public class ReplyListBean {
                /**
                 * id : 3
                 * member_id : 14
                 * nickname : 天若有情
                 * post_id : 1
                 * reply_body : 这是一条子评论
                 * reply_comment_id : 2
                 * reply_face : http://img.shahaizhi.com/SHZS_M14_IMG_20180703_142510.jpg
                 * reply_member_id : 12
                 * reply_nickname : 粉红色叶子
                 * reply_time : 2018-07-23 09:02:50
                 * type : 1
                 */

                private int id;
                private int member_id;
                private String nickname;
                private String post_id;
                private String reply_body;
                private int reply_comment_id;
                private String reply_face;
                private int reply_member_id;
                private String reply_nickname;
                private String reply_time;
                private int type;

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

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getPost_id() {
                    return post_id;
                }

                public void setPost_id(String post_id) {
                    this.post_id = post_id;
                }

                public String getReply_body() {
                    return reply_body;
                }

                public void setReply_body(String reply_body) {
                    this.reply_body = reply_body;
                }

                public int getReply_comment_id() {
                    return reply_comment_id;
                }

                public void setReply_comment_id(int reply_comment_id) {
                    this.reply_comment_id = reply_comment_id;
                }

                public String getReply_face() {
                    return reply_face;
                }

                public void setReply_face(String reply_face) {
                    this.reply_face = reply_face;
                }

                public int getReply_member_id() {
                    return reply_member_id;
                }

                public void setReply_member_id(int reply_member_id) {
                    this.reply_member_id = reply_member_id;
                }

                public String getReply_nickname() {
                    return reply_nickname;
                }

                public void setReply_nickname(String reply_nickname) {
                    this.reply_nickname = reply_nickname;
                }

                public String getReply_time() {
                    return reply_time;
                }

                public void setReply_time(String reply_time) {
                    this.reply_time = reply_time;
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
}
