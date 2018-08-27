package com.sillykid.app.entity.homepage.boutiqueline.fragment;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class BoutiqueLineBean extends BaseResult<BoutiqueLineBean.DataBean> {


    public class DataBean {
        /**
         * currentPageNo : 1
         * draw : 0
         * pageSize : 10
         * result : [{"id":4,"main_picture":"http://img.shahaizhi.com/%28null%291530269095","product_name":"大阪3天2夜精选路线","subtitle":"这个是副标题","price":0.01,"recommended":"4.8"}]
         * totalCount : 1
         * totalPageCount : 1
         */

        private int currentPageNo;
        private int draw;
        private int pageSize;
        private int totalCount;
        private int totalPageCount;
        private List<ResultBean> result;

        public int getCurrentPageNo() {
            return currentPageNo;
        }

        public void setCurrentPageNo(int currentPageNo) {
            this.currentPageNo = currentPageNo;
        }

        public int getDraw() {
            return draw;
        }

        public void setDraw(int draw) {
            this.draw = draw;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public class ResultBean {
            /**
             * id : 4
             * main_picture : http://img.shahaizhi.com/%28null%291530269095
             * product_name : 大阪3天2夜精选路线
             * subtitle : 这个是副标题
             * price : 0.01
             * recommended : 4.8
             */

            private int id;
            private String main_picture;
            private String product_name;
            private String subtitle;
            private String price;
            private String recommended;
            private int height;
            private int width;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMain_picture() {
                return main_picture;
            }

            public void setMain_picture(String main_picture) {
                this.main_picture = main_picture;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getRecommended() {
                return recommended;
            }

            public void setRecommended(String recommended) {
                this.recommended = recommended;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }
        }
    }
}
