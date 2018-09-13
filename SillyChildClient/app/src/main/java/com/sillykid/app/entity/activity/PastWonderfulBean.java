package com.sillykid.app.entity.activity;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PastWonderfulBean extends BaseResult<PastWonderfulBean.DataBean> {


    public class DataBean {
        /**
         * pageSize : 5
         * totalCount : 1
         * currentPageNo : 1
         * draw : 0
         * result : [{"id":1,"main_picture":"http://www.shahaizhi.com","title":"第一个活动","subtitle":"活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介","start_time":1536841800,"end_time":1537360200,"activity_state":1}]
         * totalPageCount : 1
         */

        private int pageSize;
        private int totalCount;
        private int currentPageNo;
        private int draw;
        private int totalPageCount;
        @SerializedName("result")
        private List<ResultBean> resultX;

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

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public List<ResultBean> getResultX() {
            return resultX;
        }

        public void setResultX(List<ResultBean> resultX) {
            this.resultX = resultX;
        }

        public class ResultBean {
            /**
             * id : 1
             * main_picture : http://www.shahaizhi.com
             * title : 第一个活动
             * subtitle : 活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介活动简介
             * start_time : 1536841800
             * end_time : 1537360200
             * activity_state : 1
             */

            private int id;
            private String main_picture;
            private String title;
            private String subtitle;
            private String start_time;
            private String end_time;
            private int activity_state;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public int getActivity_state() {
                return activity_state;
            }

            public void setActivity_state(int activity_state) {
                this.activity_state = activity_state;
            }
        }
    }
}
