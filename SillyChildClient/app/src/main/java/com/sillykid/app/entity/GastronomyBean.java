package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/9/22.
 */

public class GastronomyBean extends BaseResult<GastronomyBean.ResultBean> {


    public class ResultBean {
        /**
         * p : 1
         * totalPages : 4
         * list : [{"id":13,"img":"http://img002.21cnimg.com/photos/album/20150702/m600/2D79154370E073A2BA3CD4D07868861D.jpeg","title":"小黄人大闹天空","subTitle":null,"readNum":0,"praiseNum":0,"timeStamp":1505729853,"timeFmt":"2017.09.18"},{"id":12,"img":"http://img002.21cnimg.com/photos/album/20150702/m600/2D79154370E073A2BA3CD4D07868861D.jpeg","title":"小黄人大闹天空","subTitle":null,"readNum":0,"praiseNum":0,"timeStamp":1505729850,"timeFmt":"2017.09.18"}]
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
             * id : 13
             * img : http://img002.21cnimg.com/photos/album/20150702/m600/2D79154370E073A2BA3CD4D07868861D.jpeg
             * title : 小黄人大闹天空
             * subTitle : null
             * readNum : 0
             * praiseNum : 0
             * timeStamp : 1505729853
             * timeFmt : 2017.09.18
             */

            private int id;
            private String img;
            private String title;
            private String subTitle;
            private int readNum;
            private int praiseNum;
            private int timeStamp;
            private String timeFmt;

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

            public int getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(int praiseNum) {
                this.praiseNum = praiseNum;
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
        }
    }


}
