package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;
import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by Admin on 2017/9/30.
 */

public class CarListBean extends BaseResult<CarListBean.ResultBean> {


    /**
     * status : 1
     * msg : 成功
     * result : {"list":[{"car_id":2,"brand_id":1,"brand_name":"大众","car_type_id":32,"car_type_name":"朗逸","seat_num":7},{"car_id":3,"brand_id":62,"brand_name":"奥拓","car_type_id":63,"car_type_name":"尼克斯","seat_num":5}]}
     */


    public class ResultBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean  implements IPickerViewData {
            /**
             * car_id : 2
             * brand_id : 1
             * brand_name : 大众
             * car_type_id : 32
             * car_type_name : 朗逸
             * seat_num : 7
             */

            private int car_id;
            private int brand_id;
            private String brand_name;
            private int car_type_id;
            private String car_type_name;
            private int seat_num;

            public int getCar_id() {
                return car_id;
            }

            public void setCar_id(int car_id) {
                this.car_id = car_id;
            }

            public int getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(int brand_id) {
                this.brand_id = brand_id;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public int getCar_type_id() {
                return car_type_id;
            }

            public void setCar_type_id(int car_type_id) {
                this.car_type_id = car_type_id;
            }

            public String getCar_type_name() {
                return car_type_name;
            }

            public void setCar_type_name(String car_type_name) {
                this.car_type_name = car_type_name;
            }

            public int getSeat_num() {
                return seat_num;
            }

            public void setSeat_num(int seat_num) {
                this.seat_num = seat_num;
            }

            @Override
            public String getPickerViewText() {
                return brand_name;
            }
        }
    }
}
