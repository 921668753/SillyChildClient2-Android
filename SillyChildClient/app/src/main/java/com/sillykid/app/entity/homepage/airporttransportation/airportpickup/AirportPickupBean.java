package com.sillykid.app.entity.homepage.airporttransportation.airportpickup;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

public class AirportPickupBean extends BaseResult<AirportPickupBean.DataBean> {


    public class DataBean {
        /**
         * message : 填写需求成功
         * requirement_id : 4
         */

        @SerializedName("message")
        private String messageX;
        private int requirement_id;

        public String getMessageX() {
            return messageX;
        }

        public void setMessageX(String messageX) {
            this.messageX = messageX;
        }

        public int getRequirement_id() {
            return requirement_id;
        }

        public void setRequirement_id(int requirement_id) {
            this.requirement_id = requirement_id;
        }
    }
}
