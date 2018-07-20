package com.sillykid.app.entity.mall.goodslist.goodsdetails.comments;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class CommentsBean extends BaseResult<CommentsBean.DataBean> {


    public class DataBean {
        /**
         * imageCount : 0
         * commentList : [{"uname":"toys","face":"http://ovwiqces1.bkt.clouddn.com/SHZS_M52_IMG_20180608_095231.jpg","comment_id":6,"goods_id":133,"member_id":11,"content":"测试啊测试","dateline":1527854586,"product_id":null,"store_id":18,"gallery":[]}]
         * commentCount : 1
         */

        private String imageCount;
        private String commentCount;
        private List<CommentListBean> commentList;

        public String getImageCount() {
            return imageCount;
        }

        public void setImageCount(String imageCount) {
            this.imageCount = imageCount;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public class CommentListBean {
            /**
             * uname : toys
             * face : http://ovwiqces1.bkt.clouddn.com/SHZS_M52_IMG_20180608_095231.jpg
             * comment_id : 6
             * goods_id : 133
             * member_id : 11
             * content : 测试啊测试
             * dateline : 1527854586
             * product_id : null
             * store_id : 18
             * gallery : []
             */

            private String uname;
            private String face;
            private int comment_id;
            private int goods_id;
            private int member_id;
            private String content;
            private String dateline;
            private String product_id;
            private int store_id;
            private List<GalleryBean> gallery;

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public int getComment_id() {
                return comment_id;
            }

            public void setComment_id(int comment_id) {
                this.comment_id = comment_id;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getDateline() {
                return dateline;
            }

            public void setDateline(String dateline) {
                this.dateline = dateline;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public List<GalleryBean> getGallery() {
                return gallery;
            }

            public void setGallery(List<GalleryBean> gallery) {
                this.gallery = gallery;
            }


            public class GalleryBean {

                private int img_id;
                private int comment_id;
                private String original;
                private int sort;

                public int getImg_id() {
                    return img_id;
                }

                public void setImg_id(int img_id) {
                    this.img_id = img_id;
                }

                public int getComment_id() {
                    return comment_id;
                }

                public void setComment_id(int comment_id) {
                    this.comment_id = comment_id;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }
            }


        }
    }
}
