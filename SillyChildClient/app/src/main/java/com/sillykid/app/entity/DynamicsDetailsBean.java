package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 2017/9/26.
 */

public class DynamicsDetailsBean extends BaseResult<DynamicsDetailsBean.ResultBean> {


    public class ResultBean {
        /**
         * id : 16
         * ownerId : 3
         * img : http://img.shahaizi.cn/7fa1375780eb94d371162ac60423a274.png
         * title : 苏州一天游
         * content : 就是好没有什么好说的啦
         * subTitle : null
         * readNum : 0
         * timeStamp : 1508398662
         * timeFmt : 2017.10.19
         * isCollect : 1
         * collectNum : 2
         * isPraise : 0
         * praiseNum : 0
         * owner : {"id":3,"avatar":"http://img.shahaizi.cn/ff2a5008ce629950956ecb8ce544a07a.jpg","nickname":"吼吼吼","sex":1,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0}
         * comments : [{"id":11,"owner":{"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考虑考虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"22222222","createTime":1508414825,"img":null,"isAnonymous":0,"parentId":0,"createTimeFmt":"2017-10-19","isPraise":0,"replies":[{"id":12,"owner":{"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考虑考虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"评论的回复","createTime":1508414825,"img":null,"isAnonymous":0,"parentId":11,"createTimeFmt":"2017-10-19","isPraise":0,"replies":{"status":4004,"msg":"暂无数据","result":[]}},{"id":13,"owner":{"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考虑考虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"评论的回复22","createTime":1508414825,"img":null,"isAnonymous":0,"parentId":11,"createTimeFmt":"2017-10-19","isPraise":0,"replies":{"status":4004,"msg":"暂无数据","result":[]}}]}]
         */

        private int id;
        private int ownerId;
        private String img;
        private String title;
        private String content;
        private String subTitle;
        private int readNum;
        private int timeStamp;
        private String timeFmt;
        private int isCollect;
        private int collectNum;
        private int isPraise;
        private int praiseNum;
        private OwnerBean owner;
        private List<CommentsBean> comments;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public int getReadNum() {
            return readNum;
        }

        public void setReadNum(int readNum) {
            this.readNum = readNum;
        }

        public int getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(int timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getTimeFmt() {
            return timeFmt;
        }

        public void setTimeFmt(String timeFmt) {
            this.timeFmt = timeFmt;
        }

        public int getIsCollect() {
            return isCollect;
        }

        public void setIsCollect(int isCollect) {
            this.isCollect = isCollect;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }

        public int getIsPraise() {
            return isPraise;
        }

        public void setIsPraise(int isPraise) {
            this.isPraise = isPraise;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public OwnerBean getOwner() {
            return owner;
        }

        public void setOwner(OwnerBean owner) {
            this.owner = owner;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public class OwnerBean {
            /**
             * id : 3
             * avatar : http://img.shahaizi.cn/ff2a5008ce629950956ecb8ce544a07a.jpg
             * nickname : 吼吼吼
             * sex : 1
             * level : 1
             * fansNum : 0
             * attentionNum : 0
             * praiseNum : 0
             * collectNum : 0
             * isAttention : 0
             */

            private int id;
            private String avatar;
            private String nickname;
            private int sex;
            private int level;
            private int fansNum;
            private int attentionNum;
            private int praiseNum;
            private int collectNum;
            private int isAttention;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getFansNum() {
                return fansNum;
            }

            public void setFansNum(int fansNum) {
                this.fansNum = fansNum;
            }

            public int getAttentionNum() {
                return attentionNum;
            }

            public void setAttentionNum(int attentionNum) {
                this.attentionNum = attentionNum;
            }

            public int getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(int praiseNum) {
                this.praiseNum = praiseNum;
            }

            public int getCollectNum() {
                return collectNum;
            }

            public void setCollectNum(int collectNum) {
                this.collectNum = collectNum;
            }

            public int getIsAttention() {
                return isAttention;
            }

            public void setIsAttention(int isAttention) {
                this.isAttention = isAttention;
            }
        }

        public class CommentsBean {
            /**
             * id : 11
             * owner : {"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考虑考虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0}
             * content : 22222222
             * createTime : 1508414825
             * img : null
             * isAnonymous : 0
             * parentId : 0
             * createTimeFmt : 2017-10-19
             * isPraise : 0
             * replies : [{"id":12,"owner":{"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考虑考虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"评论的回复","createTime":1508414825,"img":null,"isAnonymous":0,"parentId":11,"createTimeFmt":"2017-10-19","isPraise":0,"replies":{"status":4004,"msg":"暂无数据","result":[]}},{"id":13,"owner":{"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考虑考虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"评论的回复22","createTime":1508414825,"img":null,"isAnonymous":0,"parentId":11,"createTimeFmt":"2017-10-19","isPraise":0,"replies":{"status":4004,"msg":"暂无数据","result":[]}}]
             */

            private String id;
            private OwnerBeanX owner;
            private String content;
            private int createTime;
            private String img;
            private int isAnonymous;
            private int parentId;
            private String createTimeFmt;
            private int isPraise;
            private List<RepliesBeanX> replies;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public OwnerBeanX getOwner() {
                return owner;
            }

            public void setOwner(OwnerBeanX owner) {
                this.owner = owner;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getCreateTime() {
                return createTime;
            }

            public void setCreateTime(int createTime) {
                this.createTime = createTime;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getIsAnonymous() {
                return isAnonymous;
            }

            public void setIsAnonymous(int isAnonymous) {
                this.isAnonymous = isAnonymous;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getCreateTimeFmt() {
                return createTimeFmt;
            }

            public void setCreateTimeFmt(String createTimeFmt) {
                this.createTimeFmt = createTimeFmt;
            }

            public int getIsPraise() {
                return isPraise;
            }

            public void setIsPraise(int isPraise) {
                this.isPraise = isPraise;
            }

            public List<RepliesBeanX> getReplies() {
                return replies;
            }

            public void setReplies(List<RepliesBeanX> replies) {
                this.replies = replies;
            }

            public class OwnerBeanX {
                /**
                 * id : 31
                 * avatar : http://img.shahaizi.cn/user_avatar.png
                 * nickname : 考虑考虑
                 * sex : 0
                 * level : 1
                 * fansNum : 0
                 * attentionNum : 0
                 * praiseNum : 0
                 * collectNum : 0
                 * isAttention : 0
                 */

                private int id;
                private String avatar;
                private String nickname;
                private int sex;
                private int level;
                private int fansNum;
                private int attentionNum;
                private int praiseNum;
                private int collectNum;
                private int isAttention;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getSex() {
                    return sex;
                }

                public void setSex(int sex) {
                    this.sex = sex;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public int getFansNum() {
                    return fansNum;
                }

                public void setFansNum(int fansNum) {
                    this.fansNum = fansNum;
                }

                public int getAttentionNum() {
                    return attentionNum;
                }

                public void setAttentionNum(int attentionNum) {
                    this.attentionNum = attentionNum;
                }

                public int getPraiseNum() {
                    return praiseNum;
                }

                public void setPraiseNum(int praiseNum) {
                    this.praiseNum = praiseNum;
                }

                public int getCollectNum() {
                    return collectNum;
                }

                public void setCollectNum(int collectNum) {
                    this.collectNum = collectNum;
                }

                public int getIsAttention() {
                    return isAttention;
                }

                public void setIsAttention(int isAttention) {
                    this.isAttention = isAttention;
                }
            }

            public class RepliesBeanX {
                /**
                 * id : 12
                 * owner : {"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考虑考虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0}
                 * content : 评论的回复
                 * createTime : 1508414825
                 * img : null
                 * isAnonymous : 0
                 * parentId : 11
                 * createTimeFmt : 2017-10-19
                 * isPraise : 0
                 * replies : {"status":4004,"msg":"暂无数据","result":[]}
                 */

                private int id;
                private OwnerBeanXX owner;
                private String content;
                private int createTime;
                private String img;
                private int isAnonymous;
                private int parentId;
                private String createTimeFmt;
                private int isPraise;
         //       private RepliesBean replies;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public OwnerBeanXX getOwner() {
                    return owner;
                }

                public void setOwner(OwnerBeanXX owner) {
                    this.owner = owner;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(int createTime) {
                    this.createTime = createTime;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public int getIsAnonymous() {
                    return isAnonymous;
                }

                public void setIsAnonymous(int isAnonymous) {
                    this.isAnonymous = isAnonymous;
                }

                public int getParentId() {
                    return parentId;
                }

                public void setParentId(int parentId) {
                    this.parentId = parentId;
                }

                public String getCreateTimeFmt() {
                    return createTimeFmt;
                }

                public void setCreateTimeFmt(String createTimeFmt) {
                    this.createTimeFmt = createTimeFmt;
                }

                public int getIsPraise() {
                    return isPraise;
                }

                public void setIsPraise(int isPraise) {
                    this.isPraise = isPraise;
                }

//                public RepliesBean getReplies() {
//                    return replies;
//                }
//
//                public void setReplies(RepliesBean replies) {
//                    this.replies = replies;
//                }

                public class OwnerBeanXX {
                    /**
                     * id : 31
                     * avatar : http://img.shahaizi.cn/user_avatar.png
                     * nickname : 考虑考虑
                     * sex : 0
                     * level : 1
                     * fansNum : 0
                     * attentionNum : 0
                     * praiseNum : 0
                     * collectNum : 0
                     * isAttention : 0
                     */

                    private int id;
                    private String avatar;
                    private String nickname;
                    private int sex;
                    private int level;
                    private int fansNum;
                    private int attentionNum;
                    private int praiseNum;
                    private int collectNum;
                    private int isAttention;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }

                    public String getNickname() {
                        return nickname;
                    }

                    public void setNickname(String nickname) {
                        this.nickname = nickname;
                    }

                    public int getSex() {
                        return sex;
                    }

                    public void setSex(int sex) {
                        this.sex = sex;
                    }

                    public int getLevel() {
                        return level;
                    }

                    public void setLevel(int level) {
                        this.level = level;
                    }

                    public int getFansNum() {
                        return fansNum;
                    }

                    public void setFansNum(int fansNum) {
                        this.fansNum = fansNum;
                    }

                    public int getAttentionNum() {
                        return attentionNum;
                    }

                    public void setAttentionNum(int attentionNum) {
                        this.attentionNum = attentionNum;
                    }

                    public int getPraiseNum() {
                        return praiseNum;
                    }

                    public void setPraiseNum(int praiseNum) {
                        this.praiseNum = praiseNum;
                    }

                    public int getCollectNum() {
                        return collectNum;
                    }

                    public void setCollectNum(int collectNum) {
                        this.collectNum = collectNum;
                    }

                    public int getIsAttention() {
                        return isAttention;
                    }

                    public void setIsAttention(int isAttention) {
                        this.isAttention = isAttention;
                    }
                }

                public class RepliesBean {
                    /**
                     * status : 4004
                     * msg : 暂无数据
                     * result : []
                     */

                    @SerializedName("status")
                    private int statusX;
                    @SerializedName("msg")
                    private String msgX;
                    @SerializedName("result")
                    private List<?> resultX;

                    public int getStatusX() {
                        return statusX;
                    }

                    public void setStatusX(int statusX) {
                        this.statusX = statusX;
                    }

                    public String getMsgX() {
                        return msgX;
                    }

                    public void setMsgX(String msgX) {
                        this.msgX = msgX;
                    }

                    public List<?> getResultX() {
                        return resultX;
                    }

                    public void setResultX(List<?> resultX) {
                        this.resultX = resultX;
                    }
                }
            }
        }
    }
}
