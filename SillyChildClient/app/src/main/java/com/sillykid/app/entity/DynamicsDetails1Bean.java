package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 2017/9/26.
 */

public class DynamicsDetails1Bean extends BaseResult<DynamicsDetails1Bean.ResultBean> {

    public class ResultBean {
        /**
         * id : 9
         * ownerId : 35
         * img : http://img.shahaizi.cn/3d1beaf72d0b06d078224d30a0a552ed.png
         * title : 我的最新动态
         * content : 我想要发布的
         * subTitle : null
         * readNum : 0
         * timeStamp : 1508998533
         * timeFmt : 2017.10.26
         * isCollect : 0
         * collectNum : 0
         * isPraise : 0
         * praiseNum : 1
         * owner : {"id":35,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"18451847701","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0}
         * comments : [{"id":42,"owner":{"id":35,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"18451847701","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"我对这个文章*了要","createTime":1509005816,"img":null,"isAnonymous":0,"parentId":0,"createTimeFmt":"2017-10-26","isPraise":0,"replies":[{"id":44,"owner":{"id":35,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"18451847701","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"*","createTime":1509007103,"img":null,"isAnonymous":0,"parentId":42,"createTimeFmt":"2017-10-26","isPraise":0,"replies":{"status":4004,"msg":"暂无数据","result":[]}}]},{"id":43,"owner":{"id":35,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"18451847701","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"我对*","createTime":1509006190,"img":null,"isAnonymous":0,"parentId":0,"createTimeFmt":"2017-10-26","isPraise":0,"replies":[{"id":46,"owner":{"id":35,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"18451847701","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"*qqq","createTime":1509010022,"img":null,"isAnonymous":0,"parentId":43,"createTimeFmt":"2017-10-26","isPraise":0,"replies":{"status":4004,"msg":"暂无数据","result":[]}}]},{"id":45,"owner":[],"content":"我摸摸弄","createTime":1509009867,"img":null,"isAnonymous":0,"parentId":0,"createTimeFmt":"2017-10-26","isPraise":0,"replies":[]}]
         */

        private int id;
        private int ownerId;
        private String img;
        private String title;
        private String content;
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
             * id : 35
             * avatar : http://img.shahaizi.cn/user_avatar.png
             * nickname : 18451847701
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

        public class CommentsBean {
            /**
             * id : 42
             * owner : {"id":35,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"18451847701","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0}
             * content : 我对这个文章*了要
             * createTime : 1509005816
             * img : null
             * isAnonymous : 0
             * parentId : 0
             * createTimeFmt : 2017-10-26
             * isPraise : 0
             * replies : [{"id":44,"owner":{"id":35,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"18451847701","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"*","createTime":1509007103,"img":null,"isAnonymous":0,"parentId":42,"createTimeFmt":"2017-10-26","isPraise":0,"replies":{"status":4004,"msg":"暂无数据","result":[]}}]
             */

            private int id;
            private OwnerBeanX owner;
            private String content;
            private int createTime;
            private Object img;
            private int isAnonymous;
            private int parentId;
            private String createTimeFmt;
            private int isPraise;
            private List<RepliesBeanX> replies;

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public Object getImg() {
                return img;
            }

            public void setImg(Object img) {
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
                 * id : 35
                 * avatar : http://img.shahaizi.cn/user_avatar.png
                 * nickname : 18451847701
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
                 * id : 44
                 * owner : {"id":35,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"18451847701","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0}
                 * content : *
                 * createTime : 1509007103
                 * img : null
                 * isAnonymous : 0
                 * parentId : 42
                 * createTimeFmt : 2017-10-26
                 * isPraise : 0
                 * replies : {"status":4004,"msg":"暂无数据","result":[]}
                 */

                private int id;
                private OwnerBeanXX owner;
                private String content;
                private int createTime;
                private int isAnonymous;
                private int parentId;
                private String createTimeFmt;
                private int isPraise;
                private RepliesBean replies;

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

                public RepliesBean getReplies() {
                    return replies;
                }

                public void setReplies(RepliesBean replies) {
                    this.replies = replies;
                }

                public class OwnerBeanXX {
                    /**
                     * id : 35
                     * avatar : http://img.shahaizi.cn/user_avatar.png
                     * nickname : 18451847701
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
