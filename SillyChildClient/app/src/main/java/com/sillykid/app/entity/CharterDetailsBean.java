package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/9/29.
 */

public class CharterDetailsBean extends BaseResult<CharterDetailsBean.ResultBean> {

    /**
     * status : 1
     * msg : SUCCESS
     * result : {"carLuggageNum":null,"carSeatNum":0,"carSeatTotal":null,"carTypeId":null,"carTypeName":null,"childSeatPrice":null,"childSeatPriceFmt":"¥0.00","hasChildSeat":null,"hasInsurance":null,"hasWheelChair":null,"id":1000,"imgs":["http://gimg1.bitautoimg.com/ResourceFiles/0/3/406/20170712111916756.jpg","http://www.sinaimg.cn/qc/photo_auto/photopng/08/02/1470990802.png"],"isAllowReturn":null,"isCollect":0,"isPraise":0,"overdistancePrice":null,"overdistancePriceFmt":"¥0.00","overtimePrice":null,"overtimePriceFmt":"¥0.00","price":"0.01","priceFmt":"¥0.01","publishTime":1506679144,"publishTimeFmt":"2017.09.29","remind":null,"returnPolicy":null,"serviceCityId":null,"serviceCityName":null,"serviceCountryId":null,"serviceCountryName":null,"serviceMaxDistance":null,"serviceMaxPerson":null,"serviceMaxTime":null,"title":"包车标题（手动添加）","wheelChairPrice":null,"wheelChairPriceFmt":"¥0.00"}
     */

    public class ResultBean {
        /**
         * carLuggageNum : null
         * carSeatNum : 0
         * carSeatTotal : null
         * carTypeId : null
         * carTypeName : null
         * childSeatPrice : null
         * childSeatPriceFmt : ¥0.00
         * hasChildSeat : null
         * hasInsurance : null
         * hasWheelChair : null
         * id : 1000
         * imgs : ["http://gimg1.bitautoimg.com/ResourceFiles/0/3/406/20170712111916756.jpg","http://www.sinaimg.cn/qc/photo_auto/photopng/08/02/1470990802.png"]
         * isAllowReturn : null
         * isCollect : 0
         * isPraise : 0
         * overdistancePrice : null
         * overdistancePriceFmt : ¥0.00
         * overtimePrice : null
         * overtimePriceFmt : ¥0.00
         * price : 0.01
         * priceFmt : ¥0.01
         * publishTime : 1506679144
         * publishTimeFmt : 2017.09.29
         * remind : null
         * returnPolicy : null
         * serviceCityId : null
         * serviceCityName : null
         * serviceCountryId : null
         * serviceCountryName : null
         * serviceMaxDistance : null
         * serviceMaxPerson : null
         * serviceMaxTime : null
         * title : 包车标题（手动添加）
         * wheelChairPrice : null
         * wheelChairPriceFmt : ¥0.00
         * costCompensationLevel//补偿改退的等级
         */


        private String car_level;
        private String car_level_name;
        private String car_seat_num;
        private String carLuggageNum;
        private int carSeatNum;
        private String carSeatTotal;
        private String carTypeId;
        private String carTypeName;
        private String childSeatPrice;
        private String childSeatPriceFmt;
        private String costStatement;
        private String costCompensation;
        private String costCompensationLevel;
        private String hasChildSeat;
        private String hasInsurance;
        private String hasWheelChair;
        private String flyName;
        private int id;
        private String isAllowReturn;
        private int isCollect;
        private int isPraise;
        private String overdistancePrice;
        private String overdistancePriceFmt;
        private String overtimePrice;
        private String overtimePriceFmt;
        private String price;
        private String priceFmt;
        private int publishTime;
        private String publishTimeFmt;
        private String remind;
        private String returnPolicy;
        private String serviceCityId;
        private String serviceCityName;
        private String serviceCountryId;
        private String serviceCountryName;
        private String serviceMaxDistance;
        private String serviceMaxPerson;
        private String serviceMaxTime;
        private String title;
        private String wheelChairPrice;
        private String wheelChairPriceFmt;
        private List<String> imgs;

        public String getCar_level() {
            return car_level;
        }

        public void setCar_level(String car_level) {
            this.car_level = car_level;
        }

        public String getCar_level_name() {
            return car_level_name;
        }

        public void setCar_level_name(String car_level_name) {
            this.car_level_name = car_level_name;
        }

        public String getCar_seat_num() {
            return car_seat_num;
        }

        public void setCar_seat_num(String car_seat_num) {
            this.car_seat_num = car_seat_num;
        }

        public String getCarLuggageNum() {
            return carLuggageNum;
        }

        public void setCarLuggageNum(String carLuggageNum) {
            this.carLuggageNum = carLuggageNum;
        }

        public int getCarSeatNum() {
            return carSeatNum;
        }

        public void setCarSeatNum(int carSeatNum) {
            this.carSeatNum = carSeatNum;
        }

        public String getCarSeatTotal() {
            return carSeatTotal;
        }

        public void setCarSeatTotal(String carSeatTotal) {
            this.carSeatTotal = carSeatTotal;
        }

        public String getCarTypeId() {
            return carTypeId;
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

        public String getFlyName() {
            return flyName;
        }

        public void setFlyName(String flyName) {
            this.flyName = flyName;
        }

        public void setCarTypeId(String carTypeId) {
            this.carTypeId = carTypeId;
        }

        public String getCarTypeName() {
            return carTypeName;
        }

        public void setCarTypeName(String carTypeName) {
            this.carTypeName = carTypeName;
        }

        public String getChildSeatPrice() {
            return childSeatPrice;
        }

        public void setChildSeatPrice(String childSeatPrice) {
            this.childSeatPrice = childSeatPrice;
        }

        public String getChildSeatPriceFmt() {
            return childSeatPriceFmt;
        }

        public void setChildSeatPriceFmt(String childSeatPriceFmt) {
            this.childSeatPriceFmt = childSeatPriceFmt;
        }

        public String getHasChildSeat() {
            return hasChildSeat;
        }

        public void setHasChildSeat(String hasChildSeat) {
            this.hasChildSeat = hasChildSeat;
        }

        public String getHasInsurance() {
            return hasInsurance;
        }

        public void setHasInsurance(String hasInsurance) {
            this.hasInsurance = hasInsurance;
        }

        public String getHasWheelChair() {
            return hasWheelChair;
        }

        public void setHasWheelChair(String hasWheelChair) {
            this.hasWheelChair = hasWheelChair;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIsAllowReturn() {
            return isAllowReturn;
        }

        public void setIsAllowReturn(String isAllowReturn) {
            this.isAllowReturn = isAllowReturn;
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

        public String getOverdistancePrice() {
            return overdistancePrice;
        }

        public void setOverdistancePrice(String overdistancePrice) {
            this.overdistancePrice = overdistancePrice;
        }

        public String getOverdistancePriceFmt() {
            return overdistancePriceFmt;
        }

        public void setOverdistancePriceFmt(String overdistancePriceFmt) {
            this.overdistancePriceFmt = overdistancePriceFmt;
        }

        public String getOvertimePrice() {
            return overtimePrice;
        }

        public void setOvertimePrice(String overtimePrice) {
            this.overtimePrice = overtimePrice;
        }

        public String getOvertimePriceFmt() {
            return overtimePriceFmt;
        }

        public void setOvertimePriceFmt(String overtimePriceFmt) {
            this.overtimePriceFmt = overtimePriceFmt;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPriceFmt() {
            return priceFmt;
        }

        public void setPriceFmt(String priceFmt) {
            this.priceFmt = priceFmt;
        }

        public int getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(int publishTime) {
            this.publishTime = publishTime;
        }

        public String getPublishTimeFmt() {
            return publishTimeFmt;
        }

        public void setPublishTimeFmt(String publishTimeFmt) {
            this.publishTimeFmt = publishTimeFmt;
        }

        public String getRemind() {
            return remind;
        }

        public void setRemind(String remind) {
            this.remind = remind;
        }

        public String getReturnPolicy() {
            return returnPolicy;
        }

        public void setReturnPolicy(String returnPolicy) {
            this.returnPolicy = returnPolicy;
        }

        public String getServiceCityId() {
            return serviceCityId;
        }

        public void setServiceCityId(String serviceCityId) {
            this.serviceCityId = serviceCityId;
        }

        public String getServiceCityName() {
            return serviceCityName;
        }

        public void setServiceCityName(String serviceCityName) {
            this.serviceCityName = serviceCityName;
        }

        public String getServiceCountryId() {
            return serviceCountryId;
        }

        public void setServiceCountryId(String serviceCountryId) {
            this.serviceCountryId = serviceCountryId;
        }

        public String getServiceCountryName() {
            return serviceCountryName;
        }

        public void setServiceCountryName(String serviceCountryName) {
            this.serviceCountryName = serviceCountryName;
        }

        public String getServiceMaxDistance() {
            return serviceMaxDistance;
        }

        public void setServiceMaxDistance(String serviceMaxDistance) {
            this.serviceMaxDistance = serviceMaxDistance;
        }

        public String getServiceMaxPerson() {
            return serviceMaxPerson;
        }

        public void setServiceMaxPerson(String serviceMaxPerson) {
            this.serviceMaxPerson = serviceMaxPerson;
        }

        public String getServiceMaxTime() {
            return serviceMaxTime;
        }

        public void setServiceMaxTime(String serviceMaxTime) {
            this.serviceMaxTime = serviceMaxTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWheelChairPrice() {
            return wheelChairPrice;
        }

        public void setWheelChairPrice(String wheelChairPrice) {
            this.wheelChairPrice = wheelChairPrice;
        }

        public String getWheelChairPriceFmt() {
            return wheelChairPriceFmt;
        }

        public void setWheelChairPriceFmt(String wheelChairPriceFmt) {
            this.wheelChairPriceFmt = wheelChairPriceFmt;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }
    }
}
