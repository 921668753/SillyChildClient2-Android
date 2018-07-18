package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/8/17.
 */

public class MineAddressBean extends BaseResult<MineAddressBean.ResultBean> {

    public class ResultBean {

        private int page;
        private int pageSize;
        private int pageTotal;
        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean {
            /**
             * avatar : 2222
             * name : 2222
             * bonus : 2222
             */

            private String avatar;
            private String name;
            private String bonus;
            private String phone;
            private boolean isdefault;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBonus() {
                return bonus;
            }

            public void setBonus(String bonus) {
                this.bonus = bonus;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public boolean isdefault() {
                return isdefault;
            }

            public void setIsdefault(boolean isdefault) {
                this.isdefault = isdefault;
            }
        }
    }


}
