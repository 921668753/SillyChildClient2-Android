package com.sillykid.app.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by Admin on 2017/7/27.
 */

public class CharterOrderAngleBean extends BaseResult<CharterOrderAngleBean.ResultBean > {


    public static class ResultBean {
        /**
         * ALL : 6
         * UN_PAY : 4
         * DOING : 2
         * UN_COMMENT : 0
         * FINISH : 0
         */

        private int ALL;
        private int UN_PAY;
        private int DOING;
        private int UN_COMMENT;
        private int FINISH;

        public int getALL() {
            return ALL;
        }

        public void setALL(int ALL) {
            this.ALL = ALL;
        }

        public int getUN_PAY() {
            return UN_PAY;
        }

        public void setUN_PAY(int UN_PAY) {
            this.UN_PAY = UN_PAY;
        }

        public int getDOING() {
            return DOING;
        }

        public void setDOING(int DOING) {
            this.DOING = DOING;
        }

        public int getUN_COMMENT() {
            return UN_COMMENT;
        }

        public void setUN_COMMENT(int UN_COMMENT) {
            this.UN_COMMENT = UN_COMMENT;
        }

        public int getFINISH() {
            return FINISH;
        }

        public void setFINISH(int FINISH) {
            this.FINISH = FINISH;
        }
    }
}
