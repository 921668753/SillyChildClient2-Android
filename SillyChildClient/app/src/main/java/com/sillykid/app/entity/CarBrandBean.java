package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;
import com.contrarywind.interfaces.IPickerViewData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 2017/9/22.
 */

public class CarBrandBean extends BaseResult<CarBrandBean.ResultBean> {


    public class ResultBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean implements IPickerViewData {
            /**
             * id : 1
             * pid : 0
             * name : 大众
             * initialLetter : D
             * status : 1
             * seatNum : 0
             * logo : null
             */

            private int id;
            private int pid;
            private String name;
            private String initialLetter;
            @SerializedName("status")
            private int statusX;
            private String seatNum;
            private String logo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getInitialLetter() {
                return initialLetter;
            }

            public void setInitialLetter(String initialLetter) {
                this.initialLetter = initialLetter;
            }

            public int getStatusX() {
                return statusX;
            }

            public void setStatusX(int statusX) {
                this.statusX = statusX;
            }

            public String getSeatNum() {
                return seatNum;
            }

            public void setSeatNum(String seatNum) {
                this.seatNum = seatNum;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            @Override
            public String getPickerViewText() {
                return name;
            }
        }
    }
}

