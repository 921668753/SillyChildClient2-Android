package com.sillykid.app.entity.homepage.privatecustom.cityselect;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class CitySelectListBean extends BaseResult<List<CitySelectListBean.DataBean>> {


    public class DataBean {
        /**
         * id : 1
         * name : 亚洲
         */
        private int isSelected;
        private int id;
        private String name;

        public int getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(int isSelected) {
            this.isSelected = isSelected;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
