package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/8/17.
 */

public class AllDynamicsBean extends BaseResult<AllDynamicsBean.ResultBean> {


    public class ResultBean {
        /**
         * p : 1
         * totalPages : 1
         * list : [{"id":5,"img":"http://img.shahaizi.cn/b9aff263e05db8960da7c81a8d329829.png","title":"1","subTitle":null,"readNum":0,"owner":{"id":2,"nickname":"15151877660","avatar":"http://img.shahaizi.cn/0e182ff1393a720d97a0f30f3376c034.jpg","email":"","sex":0,"mobile":"15151877660","level":1,"shzCode":"201710101505218002","signature":null,"attentionNum":0,"praiseNum":0,"collectionNum":0},"timeStamp":1508210412,"timeFmt":"2017.10.17","praiseNum":0},{"id":4,"img":"http://img.shahaizi.cn/bcff814f872d90787e5e81c32b2e2c51.png","title":"测试","subTitle":null,"readNum":0,"owner":{"id":3,"nickname":"吼吼吼","avatar":"http://img.shahaizi.cn/ff2a5008ce629950956ecb8ce544a07a.jpg","email":"","sex":0,"mobile":"18013126109","level":1,"shzCode":"20171010","signature":"哈哈哈","attentionNum":0,"praiseNum":0,"collectionNum":0},"timeStamp":1507774146,"timeFmt":"2017.10.12","praiseNum":0},{"id":3,"img":"http://img.shahaizi.cn/2dfe038f6bddc4e63ccde95a70e1ad4b.png","title":"调试","subTitle":null,"readNum":0,"owner":{"id":7,"nickname":"13733190537","avatar":"http://img.shahaizi.cn/user_avatar.png","email":"","sex":0,"mobile":"13733190537","level":1,"shzCode":"201710120854231069","signature":null,"attentionNum":0,"praiseNum":0,"collectionNum":0},"timeStamp":1507771298,"timeFmt":"2017.10.12","praiseNum":0},{"id":2,"img":"http://img.shahaizi.cn/0db7c56fea8c29e3211689fad07e464e.png","title":"调试","subTitle":null,"readNum":0,"owner":{"id":7,"nickname":"13733190537","avatar":"http://img.shahaizi.cn/user_avatar.png","email":"","sex":0,"mobile":"13733190537","level":1,"shzCode":"201710120854231069","signature":null,"attentionNum":0,"praiseNum":0,"collectionNum":0},"timeStamp":1507769905,"timeFmt":"2017.10.12","praiseNum":0},{"id":1,"img":"http://img.shahaizi.cn/0db7c56fea8c29e3211689fad07e464e.png","title":"踢被子事","subTitle":null,"readNum":0,"owner":{"id":7,"nickname":"13733190537","avatar":"http://img.shahaizi.cn/user_avatar.png","email":"","sex":0,"mobile":"13733190537","level":1,"shzCode":"201710120854231069","signature":null,"attentionNum":0,"praiseNum":0,"collectionNum":0},"timeStamp":1507769785,"timeFmt":"2017.10.12","praiseNum":0}]
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
             * id : 5
             * img : http://img.shahaizi.cn/b9aff263e05db8960da7c81a8d329829.png
             * title : 1
             * subTitle : null
             * readNum : 0
             * owner : {"id":2,"nickname":"15151877660","avatar":"http://img.shahaizi.cn/0e182ff1393a720d97a0f30f3376c034.jpg","email":"","sex":0,"mobile":"15151877660","level":1,"shzCode":"201710101505218002","signature":null,"attentionNum":0,"praiseNum":0,"collectionNum":0}
             * timeStamp : 1508210412
             * timeFmt : 2017.10.17
             * praiseNum : 0
             */

            private int id;
            private String img;
            private String title;
            private String subTitle;
            private int readNum;
            private OwnerBean owner;
            private int timeStamp;
            private String timeFmt;
            private String praiseNum;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public OwnerBean getOwner() {
                return owner;
            }

            public void setOwner(OwnerBean owner) {
                this.owner = owner;
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

            public String getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(String praiseNum) {
                this.praiseNum = praiseNum;
            }

            public class OwnerBean {
                /**
                 * id : 2
                 * nickname : 15151877660
                 * avatar : http://img.shahaizi.cn/0e182ff1393a720d97a0f30f3376c034.jpg
                 * email :
                 * sex : 0
                 * mobile : 15151877660
                 * level : 1
                 * shzCode : 201710101505218002
                 * signature : null
                 * attentionNum : 0
                 * praiseNum : 0
                 * collectionNum : 0
                 */

                private int id;
                private String nickname;
                private String avatar;
                private String email;
                private int sex;
                private String mobile;
                private int level;
                private String shzCode;
                private String signature;
                private int attentionNum;
                private int praiseNum;
                private int collectionNum;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public int getSex() {
                    return sex;
                }

                public void setSex(int sex) {
                    this.sex = sex;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getShzCode() {
                    return shzCode;
                }

                public void setShzCode(String shzCode) {
                    this.shzCode = shzCode;
                }

                public String getSignature() {
                    return signature;
                }

                public void setSignature(String signature) {
                    this.signature = signature;
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

                public int getCollectionNum() {
                    return collectionNum;
                }

                public void setCollectionNum(int collectionNum) {
                    this.collectionNum = collectionNum;
                }
            }
        }
    }
}
