package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;
import com.contrarywind.interfaces.IPickerViewData;
import com.kymjs.common.StringUtils;

import java.util.List;

/**
 * Created by Admin on 2017/11/15.
 */

public class VehicleTypeBean extends BaseResult<VehicleTypeBean.ResultBean> {


    public class ResultBean {
        private List<Integer> seat_list;
        private List<LevelListBean> level_list;

        public List<Integer> getSeat_list() {
            return seat_list;
        }

        public void setSeat_list(List<Integer> seat_list) {
            this.seat_list = seat_list;
        }

        public List<LevelListBean> getLevel_list() {
            return level_list;
        }

        public void setLevel_list(List<LevelListBean> level_list) {
            this.level_list = level_list;
        }

        public class LevelListBean implements IPickerViewData {
            /**
             * id : 1
             * name : 经济型
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                if (StringUtils.isEmpty(name)) {
                    name = "";
                }
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String getPickerViewText() {
                if (StringUtils.isEmpty(name)) {
                    name = "";
                }
                return name;
            }
        }
    }
}
