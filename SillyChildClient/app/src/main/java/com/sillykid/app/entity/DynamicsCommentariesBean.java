package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 2017/10/20.
 */

public class DynamicsCommentariesBean extends BaseResult<DynamicsCommentariesBean.ResultBean> {


    public class ResultBean {
        /**
         * p : 1
         * totalPages : 1
         * list : [{"id":9,"owner":{"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考**虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"111111111111","createTime":1508412465,"img":null,"isAnonymous":1,"parentId":0,"createTimeFmt":"2017-10-19","isPraise":0,"replies":[{"id":15,"owner":{"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考虑考虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"这是二级评论","createTime":1508465053,"img":null,"isAnonymous":0,"parentId":9,"createTimeFmt":"2017-10-20","isPraise":0,"replies":{"status":4004,"msg":"暂无数据","result":[]}}]},{"id":10,"owner":{"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考**虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"111111","createTime":1508412530,"img":null,"isAnonymous":1,"parentId":0,"createTimeFmt":"2017-10-19","isPraise":0,"replies":[{"id":18,"owner":{"id":4,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"17051335257","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"暑假","createTime":1508470394,"img":null,"isAnonymous":0,"parentId":10,"createTimeFmt":"2017-10-20","isPraise":0,"replies":{"status":4004,"msg":"暂无数据","result":[]}}]},{"id":11,"owner":{"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考虑考虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"22222222","createTime":1508414825,"img":null,"isAnonymous":0,"parentId":0,"createTimeFmt":"2017-10-19","isPraise":0,"replies":[{"id":12,"owner":{"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考虑考虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"评论的回复","createTime":1508414825,"img":null,"isAnonymous":0,"parentId":11,"createTimeFmt":"2017-10-19","isPraise":0,"replies":{"status":4004,"msg":"暂无数据","result":[]}},{"id":13,"owner":{"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考虑考虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"评论的回复22","createTime":1508414825,"img":null,"isAnonymous":0,"parentId":11,"createTimeFmt":"2017-10-19","isPraise":0,"replies":{"status":4004,"msg":"暂无数据","result":[]}}]},{"id":14,"owner":{"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考虑考虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"33333333","createTime":1508464995,"img":null,"isAnonymous":0,"parentId":0,"createTimeFmt":"2017-10-20","isPraise":0,"replies":[]}]
         */

        private int p;
        private int totalPages;
        private List<ListBean> list;

        public int getP() {
            return p;
        }

        public void setP(int p) {
            this.p = p;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean {
            /**
             * id : 9
             * owner : {"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考**虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0}
             * content : 111111111111
             * createTime : 1508412465
             * img : null
             * isAnonymous : 1
             * parentId : 0
             * createTimeFmt : 2017-10-19
             * isPraise : 0
             * replies : [{"id":15,"owner":{"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考虑考虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0},"content":"这是二级评论","createTime":1508465053,"img":null,"isAnonymous":0,"parentId":9,"createTimeFmt":"2017-10-20","isPraise":0,"replies":{"status":4004,"msg":"暂无数据","result":[]}}]
             */

            private String id;
            private OwnerBean owner;
            private String content;
            private int createTime;
            private String img;
            private int isAnonymous;
            private int parentId;
            private String createTimeFmt;
            private int isPraise;
            private String isGone;
            private List<RepliesBeanX> replies;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public OwnerBean getOwner() {
                return owner;
            }

            public void setOwner(OwnerBean owner) {
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

            public String getIsGone() {
                return isGone;
            }

            public void setIsGone(String isGone) {
                this.isGone = isGone;
            }

            public List<RepliesBeanX> getReplies() {
                return replies;
            }

            public void setReplies(List<RepliesBeanX> replies) {
                this.replies = replies;
            }

            public class OwnerBean {
                /**
                 * id : 31
                 * avatar : http://img.shahaizi.cn/user_avatar.png
                 * nickname : 考**虑
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
                 * id : 15
                 * owner : {"id":31,"avatar":"http://img.shahaizi.cn/user_avatar.png","nickname":"考虑考虑","sex":0,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0,"isAttention":0}
                 * content : 这是二级评论
                 * createTime : 1508465053
                 * img : null
                 * isAnonymous : 0
                 * parentId : 9
                 * createTimeFmt : 2017-10-20
                 * isPraise : 0
                 * replies : {"status":4004,"msg":"暂无数据","result":[]}
                 */

                private int id;
                private OwnerBeanX owner;
                private String content;
                private int createTime;
                private String img;
                private int isAnonymous;
                private int parentId;
                private String createTimeFmt;
                private int isPraise;
               // private RepliesBean replies;

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
