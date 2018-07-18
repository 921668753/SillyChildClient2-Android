package com.sillykid.app.entity.main;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/9/29.
 */

public class MallHomePageBean extends BaseResult<MallHomePageBean.DataBean> {


    public class DataBean {
        private List<ApiCatTreeBean> apiCatTree;
        private List<AdvcatBean> advcat;
        private List<HomePageBean> homePage;

        public List<ApiCatTreeBean> getApiCatTree() {
            return apiCatTree;
        }

        public void setApiCatTree(List<ApiCatTreeBean> apiCatTree) {
            this.apiCatTree = apiCatTree;
        }

        public List<AdvcatBean> getAdvcat() {
            return advcat;
        }

        public void setAdvcat(List<AdvcatBean> advcat) {
            this.advcat = advcat;
        }

        public List<HomePageBean> getHomePage() {
            return homePage;
        }

        public void setHomePage(List<HomePageBean> homePage) {
            this.homePage = homePage;
        }

        public class ApiCatTreeBean {
            /**
             * cat_id : 1
             * name : 食品饮料
             * parent_id : 0
             * image : http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201504271341534362.jpg
             * level : 1
             * children : [{"cat_id":2,"name":"休闲零食 ","parent_id":1,"image":null,"level":2,"children":[{"cat_id":6,"name":"坚果","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111430347546.jpg","level":3,"children":[]},{"cat_id":7,"name":"蜜饯","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111433537051.jpg","level":3,"children":[]},{"cat_id":8,"name":"肉干","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111434491503.jpg","level":3,"children":[]},{"cat_id":9,"name":"薯片","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111435333735.jpg","level":3,"children":[]},{"cat_id":303,"name":"红枣","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111436524163.jpg","level":3,"children":[]},{"cat_id":304,"name":"膨化","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111438227909.jpg","level":3,"children":[]},{"cat_id":305,"name":"核桃","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111439248172.jpg","level":3,"children":[]},{"cat_id":306,"name":"牛肉干","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111440145496.jpg","level":3,"children":[]},{"cat_id":307,"name":"口香糖","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111441030714.jpg","level":3,"children":[]},{"cat_id":308,"name":"凤爪","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111442326793.jpg","level":3,"children":[]},{"cat_id":309,"name":"鸭脖","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111443130806.jpg","level":3,"children":[]},{"cat_id":310,"name":"海苔","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111444066717.jpg","level":3,"children":[]},{"cat_id":311,"name":"鱼干","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111445092756.jpg","level":3,"children":[]},{"cat_id":491,"name":"测试分类3级","parent_id":2,"image":null,"level":3,"children":[]}]},{"cat_id":3,"name":"糖果/巧克力","parent_id":1,"image":null,"level":2,"children":[{"cat_id":10,"name":"巧克力","parent_id":3,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111446033566.jpg","level":3,"children":[]},{"cat_id":11,"name":"糖果","parent_id":3,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/57748d33Nfc80df5b.jpg!q50.jpg","level":3,"children":[]},{"cat_id":12,"name":"口香糖","parent_id":3,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201612061481034360.jpg","level":3,"children":[]}]},{"cat_id":13,"name":"饮料","parent_id":1,"image":null,"level":2,"children":[{"cat_id":14,"name":"碳酸饮料","parent_id":13,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/brand/201506021638079631.jpg","level":3,"children":[]},{"cat_id":15,"name":"茶饮料","parent_id":13,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201612061481034362.jpg","level":3,"children":[]},{"cat_id":16,"name":"果汁","parent_id":13,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201610201481034042.jpg","level":3,"children":[]},{"cat_id":17,"name":"水","parent_id":13,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/brand/2016/5/28/16//30113688.jpg","level":3,"children":[]},{"cat_id":315,"name":"功能饮料","parent_id":13,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/581051d1Ne659b1d0.jpg","level":3,"children":[]}]},{"cat_id":18,"name":"酒水","parent_id":1,"image":null,"level":2,"children":[{"cat_id":19,"name":"白酒","parent_id":18,"image":"http://static.v4.javamall.com.cn/attachment/brand/201202221742498256.jpg","level":3,"children":[]},{"cat_id":20,"name":"啤酒","parent_id":18,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201610061481034256.jpg","level":3,"children":[]},{"cat_id":21,"name":"洋酒","parent_id":18,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201610081481032684.jpg","level":3,"children":[]},{"cat_id":312,"name":"预调酒","parent_id":18,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/56012e9fN66e83e93.jpg!q50.jpg","level":3,"children":[]},{"cat_id":313,"name":"气泡酒","parent_id":18,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/5800892bN8540b005.jpg!q50.jpg","level":3,"children":[]},{"cat_id":314,"name":"整箱酒","parent_id":18,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/rBEHalDRitoIAAAAAAITuVFKQLQAADVyAARmVEAAhPR457.jpg!q50.jpg","level":3,"children":[]},{"cat_id":168,"name":"葡萄酒","parent_id":18,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/5833b3bcNc332c3b5.jpg!q50.jpg","level":3,"children":[]},{"cat_id":169,"name":"黄酒","parent_id":18,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/556442f9N0d3ebe12.jpg!q50.jpg","level":3,"children":[]}]},{"cat_id":288,"name":"茗茶","parent_id":1,"image":null,"level":2,"children":[{"cat_id":289,"name":"铁观音","parent_id":288,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/571484f5Ncbc958c0.jpg!q50.jpg","level":3,"children":[{"cat_id":297,"name":"白茶","parent_id":289,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/57d218ffNeb552fd5.jpg!q50.jpg","level":4,"children":[]}]},{"cat_id":290,"name":"普洱","parent_id":288,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/58607e70N9b1040d1.jpg!q50.jpg","level":3,"children":[]},{"cat_id":291,"name":"龙井","parent_id":288,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/58043a61N3a2cc803.jpg!q50.jpg","level":3,"children":[]},{"cat_id":292,"name":"绿茶","parent_id":288,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/583653dcN43fd5b37.jpg!q50.jpg","level":3,"children":[]},{"cat_id":293,"name":"红茶","parent_id":288,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/5809c51dNc6285037.jpg!q50.jpg","level":3,"children":[]},{"cat_id":294,"name":"乌龙茶","parent_id":288,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/571b3e6dN81c01926.jpg!q50.jpg","level":3,"children":[]},{"cat_id":295,"name":"花草茶","parent_id":288,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/582c5ad2N15af6b0b.jpg!q50.jpg","level":3,"children":[]},{"cat_id":296,"name":"黑茶","parent_id":288,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/55a36f4aN6b93a3d1.jpg!q50.jpg","level":3,"children":[]},{"cat_id":316,"name":"茉莉花茶","parent_id":288,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/56ebbd14N66fd7c51.jpg!q50.jpg","level":3,"children":[]},{"cat_id":317,"name":"大红袍","parent_id":288,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/571b3e6dN81c01926.jpg!q50.jpg","level":3,"children":[]}]},{"cat_id":298,"name":"食品礼券","parent_id":1,"image":null,"level":2,"children":[{"cat_id":299,"name":"大闸蟹","parent_id":298,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/57e26b08N03e29d16.jpg!q50.jpg","level":3,"children":[]},{"cat_id":300,"name":"粽子","parent_id":298,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/5859ddb7Nf80aa798.jpg!q50.jpg","level":3,"children":[]},{"cat_id":301,"name":"月饼","parent_id":298,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/579f03c0Na122cc52.jpg!q50.jpg","level":3,"children":[]},{"cat_id":302,"name":"卡劵","parent_id":298,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/585b3fd3N12a9b9fa.jpg!q50.jpg","level":3,"children":[]}]},{"cat_id":377,"name":"牛奶乳品","parent_id":1,"image":null,"level":2,"children":[{"cat_id":378,"name":"纯牛奶","parent_id":377,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/57fdea57Ncf05fa2c.jpg!q50.jpg","level":3,"children":[]},{"cat_id":379,"name":"酸奶","parent_id":377,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/58145413N6654a394.jpg!q50.jpg","level":3,"children":[]},{"cat_id":380,"name":"儿童奶","parent_id":377,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/57c66d5eNa3708bde.jpg!q50.jpg","level":3,"children":[]},{"cat_id":381,"name":"豆奶","parent_id":377,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/5631b454Nd6710f36.jpg!q50.jpg","level":3,"children":[]},{"cat_id":382,"name":"风味奶","parent_id":377,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/58229879N73295d8b.jpg!q50.jpg","level":3,"children":[]}]},{"cat_id":410,"name":"粮油/干货","parent_id":1,"image":null,"level":2,"children":[{"cat_id":411,"name":"食用油","parent_id":410,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/57958c27Na5046d8c.jpg!q50.jpg","level":3,"children":[]},{"cat_id":412,"name":"橄榄油","parent_id":410,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/56c3cdf7Na653fc3e.jpg!q50.jpg","level":3,"children":[]},{"cat_id":413,"name":"大米","parent_id":410,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/565fb82eN85fc37b9.jpg!q50.jpg","level":3,"children":[]},{"cat_id":414,"name":"杂粮","parent_id":410,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/57833342N0b33a846.jpg!q50.jpg","level":3,"children":[]},{"cat_id":415,"name":"面粉","parent_id":410,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/570e6b58Nbe5228fb.jpg!q50.jpg","level":3,"children":[]},{"cat_id":416,"name":"菌菇","parent_id":410,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/566556e5N2079de11.jpg!q50.jpg","level":3,"children":[]},{"cat_id":417,"name":"枸杞","parent_id":410,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/583e35e2N9a12b76f.jpg!q50.jpg","level":3,"children":[]}]}]
             */

            private int cat_id;
            private String name;
            private int parent_id;
            private String image;
            private int level;
            private List<ChildrenBeanX> children;

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
                this.cat_id = cat_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public List<ChildrenBeanX> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBeanX> children) {
                this.children = children;
            }

            public class ChildrenBeanX {
                /**
                 * cat_id : 2
                 * name : 休闲零食
                 * parent_id : 1
                 * image : null
                 * level : 2
                 * children : [{"cat_id":6,"name":"坚果","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111430347546.jpg","level":3,"children":[]},{"cat_id":7,"name":"蜜饯","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111433537051.jpg","level":3,"children":[]},{"cat_id":8,"name":"肉干","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111434491503.jpg","level":3,"children":[]},{"cat_id":9,"name":"薯片","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111435333735.jpg","level":3,"children":[]},{"cat_id":303,"name":"红枣","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111436524163.jpg","level":3,"children":[]},{"cat_id":304,"name":"膨化","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111438227909.jpg","level":3,"children":[]},{"cat_id":305,"name":"核桃","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111439248172.jpg","level":3,"children":[]},{"cat_id":306,"name":"牛肉干","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111440145496.jpg","level":3,"children":[]},{"cat_id":307,"name":"口香糖","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111441030714.jpg","level":3,"children":[]},{"cat_id":308,"name":"凤爪","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111442326793.jpg","level":3,"children":[]},{"cat_id":309,"name":"鸭脖","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111443130806.jpg","level":3,"children":[]},{"cat_id":310,"name":"海苔","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111444066717.jpg","level":3,"children":[]},{"cat_id":311,"name":"鱼干","parent_id":2,"image":"http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111445092756.jpg","level":3,"children":[]},{"cat_id":491,"name":"测试分类3级","parent_id":2,"image":null,"level":3,"children":[]}]
                 */

                private int cat_id;
                private String name;
                private int parent_id;
                private String image;
                private int level;
                private List<ChildrenBean> children;

                public int getCat_id() {
                    return cat_id;
                }

                public void setCat_id(int cat_id) {
                    this.cat_id = cat_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getParent_id() {
                    return parent_id;
                }

                public void setParent_id(int parent_id) {
                    this.parent_id = parent_id;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public List<ChildrenBean> getChildren() {
                    return children;
                }

                public void setChildren(List<ChildrenBean> children) {
                    this.children = children;
                }

                public class ChildrenBean {
                    /**
                     * cat_id : 6
                     * name : 坚果
                     * parent_id : 2
                     * image : http://static.b2b2cv2.javamall.com.cn/attachment/goodscat/201509111430347546.jpg
                     * level : 3
                     * children : []
                     */

                    private int cat_id;
                    private String name;
                    private int parent_id;
                    private String image;
                    private int level;
                    private List<?> children;

                    public int getCat_id() {
                        return cat_id;
                    }

                    public void setCat_id(int cat_id) {
                        this.cat_id = cat_id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getParent_id() {
                        return parent_id;
                    }

                    public void setParent_id(int parent_id) {
                        this.parent_id = parent_id;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                    public int getLevel() {
                        return level;
                    }

                    public void setLevel(int level) {
                        this.level = level;
                    }

                    public List<?> getChildren() {
                        return children;
                    }

                    public void setChildren(List<?> children) {
                        this.children = children;
                    }
                }
            }
        }

        public class AdvcatBean {
            /**
             * aid : 1
             * acid : 1
             * atype : 0
             * begintime : 1328025600000
             * endtime : 1614182400000
             * isclose : 0
             * attachment : null
             * atturl : http://static.b2b2cv2.javamall.com.cn/attachment/adv/2017/6/14/18//18424092.png
             * url : /search-cat-35.html
             * aname : 潮流季
             * clickcount : 0
             * disabled : false
             * linkman :
             * company :
             * contact :
             * cname :
             * httpAttUrl : http://static.b2b2cv2.javamall.com.cn/attachment/adv/2017/6/14/18//18424092.png
             */

            private int aid;
            private int acid;
            private int atype;
            private long begintime;
            private long endtime;
            private int isclose;
            private String attachment;
            private String atturl;
            private String url;
            private String aname;
            private int clickcount;
            private String disabled;
            private String linkman;
            private String company;
            private String contact;
            private String cname;
            private String httpAttUrl;

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
            }

            public int getAcid() {
                return acid;
            }

            public void setAcid(int acid) {
                this.acid = acid;
            }

            public int getAtype() {
                return atype;
            }

            public void setAtype(int atype) {
                this.atype = atype;
            }

            public long getBegintime() {
                return begintime;
            }

            public void setBegintime(long begintime) {
                this.begintime = begintime;
            }

            public long getEndtime() {
                return endtime;
            }

            public void setEndtime(long endtime) {
                this.endtime = endtime;
            }

            public int getIsclose() {
                return isclose;
            }

            public void setIsclose(int isclose) {
                this.isclose = isclose;
            }

            public String getAttachment() {
                return attachment;
            }

            public void setAttachment(String attachment) {
                this.attachment = attachment;
            }

            public String getAtturl() {
                return atturl;
            }

            public void setAtturl(String atturl) {
                this.atturl = atturl;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getAname() {
                return aname;
            }

            public void setAname(String aname) {
                this.aname = aname;
            }

            public int getClickcount() {
                return clickcount;
            }

            public void setClickcount(int clickcount) {
                this.clickcount = clickcount;
            }

            public String getDisabled() {
                return disabled;
            }

            public void setDisabled(String disabled) {
                this.disabled = disabled;
            }

            public String getLinkman() {
                return linkman;
            }

            public void setLinkman(String linkman) {
                this.linkman = linkman;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }

            public String getHttpAttUrl() {
                return httpAttUrl;
            }

            public void setHttpAttUrl(String httpAttUrl) {
                this.httpAttUrl = httpAttUrl;
            }
        }

        public class HomePageBean {
            /**
             * goods_id : 133
             * name : ML2017夏季新款时尚优雅荷叶边捆绑系带细高跟女凉鞋
             * thumbnail : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//50085050_thumbnail.jpg
             * price : 1298
             * mktprice : 1698
             * view_count : null
             * buy_count : null
             * store : null
             * sn : null
             * big : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//50085050_big.jpg
             * small : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/13//50085050_small.jpg
             * original : null
             * commentCount : null
             * commentPercent : null
             * brief : null
             * store_name :
             * brand_name :
             * goods_tag : 特价商品
             */

            private int goods_id;
            private String name;
            private String thumbnail;
            private String price;
            private String mktprice;
            private String view_count;
            private String buy_count;
            private String store;
            private String sn;
            private String big;
            private String small;
            private String original;
            private String commentCount;
            private String commentPercent;
            private String brief;
            private String store_name;
            private String brand_name;
            private String goods_tag;
            private int width;
            private int height;


            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getMktprice() {
                return mktprice;
            }

            public void setMktprice(String mktprice) {
                this.mktprice = mktprice;
            }

            public String getView_count() {
                return view_count;
            }

            public void setView_count(String view_count) {
                this.view_count = view_count;
            }

            public String getBuy_count() {
                return buy_count;
            }

            public void setBuy_count(String buy_count) {
                this.buy_count = buy_count;
            }

            public String getStore() {
                return store;
            }

            public void setStore(String store) {
                this.store = store;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getBig() {
                return big;
            }

            public void setBig(String big) {
                this.big = big;
            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getOriginal() {
                return original;
            }

            public void setOriginal(String original) {
                this.original = original;
            }

            public String getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(String commentCount) {
                this.commentCount = commentCount;
            }

            public String getCommentPercent() {
                return commentPercent;
            }

            public void setCommentPercent(String commentPercent) {
                this.commentPercent = commentPercent;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public String getGoods_tag() {
                return goods_tag;
            }

            public void setGoods_tag(String goods_tag) {
                this.goods_tag = goods_tag;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }
}




