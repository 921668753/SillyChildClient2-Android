package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/9/27.
 */

public class RouteDetailsBean extends BaseResult<RouteDetailsBean.ResultBean> {


    public class ResultBean {
        /**
         * id : 35
         * title : 日本冲绳4晚5日半自助，1天包车个性游
         * isAdmin : 1
         * isCollect : 0
         * isPraise : 0
         * costCompensationLevel//补偿改退的等级
         */

        private int id;
        private String title;
        private String coverImg;
        private String costStatement;
        private String costCompensation;
        private String costCompensationLevel;//补偿改退的等级
        private String linePrice;
        private int isAdmin;
        private int isCollect;
        private int isPraise;
        private int playDay;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public String getCostStatement() {
            return costStatement;
        }

        public void setCostStatement(String costStatement) {
            this.costStatement = costStatement;
        }

        public String getCostCompensation() {
            return costCompensation;
        }

        public void setCostCompensation(String costCompensation) {
            this.costCompensation = costCompensation;
        }

        public String getCostCompensationLevel() {
            return costCompensationLevel;
        }

        public void setCostCompensationLevel(String costCompensationLevel) {
            this.costCompensationLevel = costCompensationLevel;
        }

        public String getLinePrice() {
            return linePrice;
        }

        public void setLinePrice(String linePrice) {
            this.linePrice = linePrice;
        }

        public int getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(int isAdmin) {
            this.isAdmin = isAdmin;
        }

        public int getIsCollect() {
            return isCollect;
        }

        public void setIsCollect(int isCollect) {
            this.isCollect = isCollect;
        }

        public int getIsPraise() {
            return isPraise;
        }

        public void setIsPraise(int isPraise) {
            this.isPraise = isPraise;
        }

        public int getPlayDay() {
            return playDay;
        }

        public void setPlayDay(int playDay) {
            this.playDay = playDay;
        }
    }
}
