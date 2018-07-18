package com.sillykid.app.entity.mine.myorder.goodorder.orderevaluation;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class PublishedeEvaluationBean extends BaseResult<PublishedeEvaluationBean.DataBean> {


    public static class DataBean {
        /**
         * commentVo : {"memberCommentExts":[{"goods_id":1528341338,"content":"","imageList":""}]}
         * store_servicecredit : 1
         * store_deliverycredit : 1
         * store_desccredit : 1
         */

        private List<MemberCommentExtsBean> memberCommentExts;
        private int store_servicecredit;
        private int store_deliverycredit;
        private int store_desccredit;

        public int getStore_servicecredit() {
            return store_servicecredit;
        }

        public void setStore_servicecredit(int store_servicecredit) {
            this.store_servicecredit = store_servicecredit;
        }

        public int getStore_deliverycredit() {
            return store_deliverycredit;
        }

        public void setStore_deliverycredit(int store_deliverycredit) {
            this.store_deliverycredit = store_deliverycredit;
        }

        public int getStore_desccredit() {
            return store_desccredit;
        }

        public void setStore_desccredit(int store_desccredit) {
            this.store_desccredit = store_desccredit;
        }


        public List<MemberCommentExtsBean> getMemberCommentExts() {
            return memberCommentExts;
        }

        public void setMemberCommentExts(List<MemberCommentExtsBean> memberCommentExts) {
            this.memberCommentExts = memberCommentExts;
        }

        public static class MemberCommentExtsBean {
            /**
             * goods_id : 1528341338
             * content :
             * imageList :
             */

            private int goods_id;
            private int position;
            private String content;
            private String name;
            private String specs;
            private String price;
            private String image;
            public List<String> imageList;

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSpecs() {
                return specs;
            }

            public void setSpecs(String specs) {
                this.specs = specs;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public List<String> getImageList() {
                return imageList;
            }

            public void setImageList(List<String> imageList) {
                this.imageList = imageList;
            }
        }
    }
}
