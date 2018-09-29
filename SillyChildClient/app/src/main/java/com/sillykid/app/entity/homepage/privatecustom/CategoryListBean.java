package com.sillykid.app.entity.homepage.privatecustom;

import com.common.cklibrary.entity.BaseResult;
import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class CategoryListBean extends BaseResult<CategoryListBean.DataBean> {


    public class DataBean {
        /**
         * result : 1
         * repast_list : [{"id":4,"preference_name":"餐饮1"},{"id":5,"preference_name":"餐饮2"},{"id":6,"preference_name":"餐饮3"}]
         * message : 获取偏好数据成功
         * stay_list : [{"id":7,"preference_name":"住宿1"},{"id":8,"preference_name":"住宿2"},{"id":9,"preference_name":"住宿3"}]
         * picture : http://ovwiqces1.bkt.clouddn.com/FmDy-A7IqzfD72FmvS1ez2EK0NGM
         * content : 私人订制说明私人定制说明私人订制说明私人定制说明私人订制
         * 说明私人定制说明私人订制说明私人定制说明私人订制说明私人
         * 定制说明私人订制说明私人定制说明私人订制说明私人定制说明
         * <p>
         * travel_list : [{"id":1,"preference_name":"出行1"},{"id":2,"preference_name":"出行2"},{"id":3,"preference_name":"出行3"}]
         */

        private int result;
        private String message;
        private String picture;
        private String content;
        private String service_id;
        private String service_name;
        private List<RepastListBean> repast_list;
        private List<StayListBean> stay_list;
        private List<TravelListBean> travel_list;

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<RepastListBean> getRepast_list() {
            return repast_list;
        }

        public void setRepast_list(List<RepastListBean> repast_list) {
            this.repast_list = repast_list;
        }

        public List<StayListBean> getStay_list() {
            return stay_list;
        }

        public void setStay_list(List<StayListBean> stay_list) {
            this.stay_list = stay_list;
        }

        public List<TravelListBean> getTravel_list() {
            return travel_list;
        }

        public void setTravel_list(List<TravelListBean> travel_list) {
            this.travel_list = travel_list;
        }

        public class RepastListBean implements IPickerViewData {
            /**
             * id : 4
             * preference_name : 餐饮1
             */

            private int id;
            private String preference_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPreference_name() {
                return preference_name;
            }

            public void setPreference_name(String preference_name) {
                this.preference_name = preference_name;
            }

            @Override
            public String getPickerViewText() {
                return preference_name;
            }
        }

        public class StayListBean implements IPickerViewData {
            /**
             * id : 7
             * preference_name : 住宿1
             */

            private int id;
            private String preference_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPreference_name() {
                return preference_name;
            }

            public void setPreference_name(String preference_name) {
                this.preference_name = preference_name;
            }

            @Override
            public String getPickerViewText() {
                return preference_name;
            }
        }

        public class TravelListBean implements IPickerViewData {
            /**
             * id : 1
             * preference_name : 出行1
             */

            private int id;
            private String preference_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPreference_name() {
                return preference_name;
            }

            public void setPreference_name(String preference_name) {
                this.preference_name = preference_name;
            }

            @Override
            public String getPickerViewText() {
                return preference_name;
            }
        }
    }
}
