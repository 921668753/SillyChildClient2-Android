package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;
import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by Admin on 2017/9/15.
 */

public class DriverPackConfigBean extends BaseResult<DriverPackConfigBean.ResultBean> {

    public class ResultBean {
        private List<TripChooseBean> trip_choose;
        private List<RestaurantChooseBean> restaurant_choose;
        private List<SleepChooseBean> sleep_choose;

        public List<TripChooseBean> getTrip_choose() {
            return trip_choose;
        }

        public void setTrip_choose(List<TripChooseBean> trip_choose) {
            this.trip_choose = trip_choose;
        }

        public List<RestaurantChooseBean> getRestaurant_choose() {
            return restaurant_choose;
        }

        public void setRestaurant_choose(List<RestaurantChooseBean> restaurant_choose) {
            this.restaurant_choose = restaurant_choose;
        }

        public List<SleepChooseBean> getSleep_choose() {
            return sleep_choose;
        }

        public void setSleep_choose(List<SleepChooseBean> sleep_choose) {
            this.sleep_choose = sleep_choose;
        }

        public class TripChooseBean implements IPickerViewData {
            /**
             * id : 1
             * name : 悠闲2
             * description : 少量景点 深1
             * sorting : 1
             */

            private int id;
            private String description;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }


            @Override
            public String getPickerViewText() {
                return description;
            }
        }

        public class RestaurantChooseBean implements IPickerViewData {
            /**
             * id : 1
             * name : 0-100
             */

            private int id;
            private String description;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            @Override
            public String getPickerViewText() {
                return description;
            }
        }

        public class SleepChooseBean implements IPickerViewData {
            /**
             * id : 1
             * name : 0-100
             */

            private int id;
            private String description;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            @Override
            public String getPickerViewText() {
                return description;
            }
        }
    }
}
