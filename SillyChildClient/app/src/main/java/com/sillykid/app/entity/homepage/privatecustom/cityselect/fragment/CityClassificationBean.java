package com.sillykid.app.entity.homepage.privatecustom.cityselect.fragment;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class CityClassificationBean extends BaseResult<List<CityClassificationBean.DataBean>> {


    public class DataBean {
        /**
         * id : 1
         * name : 亚洲
         */
        private int isSelected;
        private int id;
        private String name;
        private String picture;

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

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }

}
