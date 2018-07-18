package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/7/27.
 */

public class SeeEvaluationBean extends BaseResult<SeeEvaluationBean.ResultBean > {

    public static class ResultBean {
        /**
         * userComm : {"id":9,"score":2,"content":"司机很 2","imgs":["http://img.shahaizi.cn/45e4c5d49b2d03b2e9fd2b243cbb9f35.png","http://img.shahaizi.cn/cbb21259e1652fbc34664c9dcb192a25.png"],"commentTime":1507862051,"comm_user_id":12,"commentTimeFmt":"2017.10.13 10:11","owner":{"avatar":"http://q.qlogo.cn/qqapp/1106322329/160E7E39F2D0AFB483DCE5A7CA33EF1A/100","nickname":"河流","sex":1,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0}}
         * sysComm : {"id":9,"score":2,"content":"司机很 2","imgs":["http://img.shahaizi.cn/45e4c5d49b2d03b2e9fd2b243cbb9f35.png","http://img.shahaizi.cn/cbb21259e1652fbc34664c9dcb192a25.png"],"commentTime":1507862051,"commentTimeFmt":"2017.10.13 10:11"}
         * drvComm : {"id":9,"score":2,"content":"司机很 2","imgs":["http://img.shahaizi.cn/45e4c5d49b2d03b2e9fd2b243cbb9f35.png","http://img.shahaizi.cn/cbb21259e1652fbc34664c9dcb192a25.png"],"commentTime":1507862051,"commentTimeFmt":"2017.10.13 10:11"}
         */

        private UserCommBean userComm;
        private SysCommBean sysComm;
        private DrvCommBean drvComm;

        public UserCommBean getUserComm() {
            return userComm;
        }

        public void setUserComm(UserCommBean userComm) {
            this.userComm = userComm;
        }

        public SysCommBean getSysComm() {
            return sysComm;
        }

        public void setSysComm(SysCommBean sysComm) {
            this.sysComm = sysComm;
        }

        public DrvCommBean getDrvComm() {
            return drvComm;
        }

        public void setDrvComm(DrvCommBean drvComm) {
            this.drvComm = drvComm;
        }

        public static class UserCommBean {
            /**
             * id : 9
             * score : 2
             * drv_rank  司导评分
             * line_rank    路线评分
             * content : 司机很 2
             * imgs : ["http://img.shahaizi.cn/45e4c5d49b2d03b2e9fd2b243cbb9f35.png","http://img.shahaizi.cn/cbb21259e1652fbc34664c9dcb192a25.png"]
             * commentTime : 1507862051
             * comm_user_id : 12
             * commentTimeFmt : 2017.10.13 10:11
             * owner : {"avatar":"http://q.qlogo.cn/qqapp/1106322329/160E7E39F2D0AFB483DCE5A7CA33EF1A/100","nickname":"河流","sex":1,"level":1,"fansNum":0,"attentionNum":0,"praiseNum":0,"collectNum":0}
             * isAnonymous ：0：不匿名；1：匿名
             */

            private int id;
            private int score;
            private int drv_rank;
            private String content;
            private int commentTime;
            private int comm_user_id;
            private String commentTimeFmt;
            private OwnerBean owner;
            private List<String> imgs;
            private int isAnonymous;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getDrv_rank() {
                return drv_rank;
            }

            public void setDrv_rank(int drv_rank) {
                this.drv_rank = drv_rank;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getCommentTime() {
                return commentTime;
            }

            public void setCommentTime(int commentTime) {
                this.commentTime = commentTime;
            }

            public int getComm_user_id() {
                return comm_user_id;
            }

            public void setComm_user_id(int comm_user_id) {
                this.comm_user_id = comm_user_id;
            }

            public String getCommentTimeFmt() {
                return commentTimeFmt;
            }

            public void setCommentTimeFmt(String commentTimeFmt) {
                this.commentTimeFmt = commentTimeFmt;
            }

            public OwnerBean getOwner() {
                return owner;
            }

            public void setOwner(OwnerBean owner) {
                this.owner = owner;
            }

            public List<String> getImgs() {
                return imgs;
            }

            public void setImgs(List<String> imgs) {
                this.imgs = imgs;
            }

            public int getIsAnonymous() {
                return isAnonymous;
            }

            public void setIsAnonymous(int isAnonymous) {
                this.isAnonymous = isAnonymous;
            }

            public static class OwnerBean {
                /**
                 * avatar : http://q.qlogo.cn/qqapp/1106322329/160E7E39F2D0AFB483DCE5A7CA33EF1A/100
                 * nickname : 河流
                 * sex : 1
                 * level : 1
                 * fansNum : 0
                 * attentionNum : 0
                 * praiseNum : 0
                 * collectNum : 0
                 */

                private String head_pic;
                private String nickname;
                private int sex;
                private int level;
                private int fansNum;
                private int attentionNum;
                private int praiseNum;
                private int collectNum;
                private int isAnonymous;

                public String getHead_pic() {
                    return head_pic;
                }

                public void setHead_pic(String head_pic) {
                    this.head_pic = head_pic;
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

            }
        }

        public static class SysCommBean {
            /**
             * id : 9
             * score : 2
             * content : 司机很 2
             * imgs : ["http://img.shahaizi.cn/45e4c5d49b2d03b2e9fd2b243cbb9f35.png","http://img.shahaizi.cn/cbb21259e1652fbc34664c9dcb192a25.png"]
             * commentTime : 1507862051
             * commentTimeFmt : 2017.10.13 10:11
             */

            private int id;
            private int score;
            private String content;
            private int commentTime;
            private String commentTimeFmt;
            private List<String> imgs;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getCommentTime() {
                return commentTime;
            }

            public void setCommentTime(int commentTime) {
                this.commentTime = commentTime;
            }

            public String getCommentTimeFmt() {
                return commentTimeFmt;
            }

            public void setCommentTimeFmt(String commentTimeFmt) {
                this.commentTimeFmt = commentTimeFmt;
            }

            public List<String> getImgs() {
                return imgs;
            }

            public void setImgs(List<String> imgs) {
                this.imgs = imgs;
            }
        }

        public static class DrvCommBean {
            /**
             * id : 9
             * score : 2
             * head_pic 司导头像
             * nickname 司导昵称
             * content : 司机很 2
             * imgs : ["http://img.shahaizi.cn/45e4c5d49b2d03b2e9fd2b243cbb9f35.png","http://img.shahaizi.cn/cbb21259e1652fbc34664c9dcb192a25.png"]
             * commentTime : 1507862051
             * commentTimeFmt : 2017.10.13 10:11
             */

            private int id;
            private int score;
            private String head_pic;
            private String nickname;
            private String content;
            private int commentTime;
            private String commentTimeFmt;
            private List<String> imgs;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getCommentTime() {
                return commentTime;
            }

            public void setCommentTime(int commentTime) {
                this.commentTime = commentTime;
            }

            public String getCommentTimeFmt() {
                return commentTimeFmt;
            }

            public void setCommentTimeFmt(String commentTimeFmt) {
                this.commentTimeFmt = commentTimeFmt;
            }

            public List<String> getImgs() {
                return imgs;
            }

            public void setImgs(List<String> imgs) {
                this.imgs = imgs;
            }
        }
    }
}
