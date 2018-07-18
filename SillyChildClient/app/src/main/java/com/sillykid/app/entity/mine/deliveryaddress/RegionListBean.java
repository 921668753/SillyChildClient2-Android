package com.sillykid.app.entity.mine.deliveryaddress;

import com.common.cklibrary.entity.BaseResult;
import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class RegionListBean extends BaseResult<List<RegionListBean.DataBean>> {


    public static class DataBean implements IPickerViewData {
        /**
         * region_id : 1
         * local_name : 北京
         * region_grade : 1
         * p_region_id : 0
         * childnum : 16
         * zipcode :
         * cod : 1
         */

        private int region_id;
        private String local_name;
        private int region_grade;
        private int p_region_id;
        private int childnum;
        private String zipcode;
        private String cod;

        public int getRegion_id() {
            return region_id;
        }

        public void setRegion_id(int region_id) {
            this.region_id = region_id;
        }

        public String getLocal_name() {
            return local_name;
        }

        public void setLocal_name(String local_name) {
            this.local_name = local_name;
        }

        public int getRegion_grade() {
            return region_grade;
        }

        public void setRegion_grade(int region_grade) {
            this.region_grade = region_grade;
        }

        public int getP_region_id() {
            return p_region_id;
        }

        public void setP_region_id(int p_region_id) {
            this.p_region_id = p_region_id;
        }

        public int getChildnum() {
            return childnum;
        }

        public void setChildnum(int childnum) {
            this.childnum = childnum;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getCod() {
            return cod;
        }

        public void setCod(String cod) {
            this.cod = cod;
        }

        //这个用来显示在PickerView上面的字符串,PickerView会通过getPickerViewText方法获取字符串显示出来。
        @Override
        public String getPickerViewText() {
            return local_name;
        }
    }
}
