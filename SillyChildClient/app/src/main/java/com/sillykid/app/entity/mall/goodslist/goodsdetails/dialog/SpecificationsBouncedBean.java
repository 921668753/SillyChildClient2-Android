package com.sillykid.app.entity.mall.goodslist.goodsdetails.dialog;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class SpecificationsBouncedBean extends BaseResult<List<SpecificationsBouncedBean.DataBean>> {


    public class DataBean {
        /**
         * spec_id : 1
         * specValueIds : [{"spec_value_id":80,"spec_value":"黄色"},{"spec_value_id":42,"spec_value":"绿色"},{"spec_value_id":43,"spec_value":"黑色"}]
         */

        private int spec_id;
        private String spec_name;
        private int isSelected;
        private List<SpecValueIdsBean> specValueIds;

        public int getSpec_id() {
            return spec_id;
        }

        public void setSpec_id(int spec_id) {
            this.spec_id = spec_id;
        }

        public String getSpec_name() {
            return spec_name;
        }

        public void setSpec_name(String spec_name) {
            this.spec_name = spec_name;
        }

        public int getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(int isSelected) {
            this.isSelected = isSelected;
        }

        public List<SpecValueIdsBean> getSpecValueIds() {
            return specValueIds;
        }

        public void setSpecValueIds(List<SpecValueIdsBean> specValueIds) {
            this.specValueIds = specValueIds;
        }


        public class SpecValueIdsBean {
            /**
             * spec_value_id : 80
             * spec_value : 黄色
             */
            private int isSelected;
            private int spec_value_id;
            private String spec_value;
            private int isNoClick;

            public int getIsSelected() {
                return isSelected;
            }

            public void setIsSelected(int isSelected) {
                this.isSelected = isSelected;
            }

            public int getSpec_value_id() {
                return spec_value_id;
            }

            public void setSpec_value_id(int spec_value_id) {
                this.spec_value_id = spec_value_id;
            }

            public String getSpec_value() {
                return spec_value;
            }

            public void setSpec_value(String spec_value) {
                this.spec_value = spec_value;
            }

            public int getIsNoClick() {
                return isNoClick;
            }

            public void setIsNoClick(int isNoClick) {
                this.isNoClick = isNoClick;
            }
        }
    }
}
