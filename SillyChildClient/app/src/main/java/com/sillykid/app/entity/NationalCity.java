package com.sillykid.app.entity;


import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by Admin on 2017/8/1.
 */

public class NationalCity implements IPickerViewData {


    /**
     * value : 110000
     * text : 北京
     * children : [{"value":110100,"text":"北京市","children":[{"value":110114,"text":"昌平区","children":null},{"value":110105,"text":"朝阳区","children":null},{"value":110115,"text":"大兴区","children":null},{"value":110101,"text":"东城区","children":null},{"value":110111,"text":"房山区","children":null},{"value":110106,"text":"丰台区","children":null},{"value":110108,"text":"海淀区","children":null},{"value":110116,"text":"怀柔区","children":null},{"value":110109,"text":"门头沟区","children":null},{"value":110228,"text":"密云县","children":null},{"value":110117,"text":"平谷区","children":null},{"value":110107,"text":"石景山区","children":null},{"value":110113,"text":"顺义区","children":null},{"value":110112,"text":"通州区","children":null},{"value":110102,"text":"西城区","children":null},{"value":110229,"text":"延庆县","children":null}]}]
     */

    private int value;
    private String text;
    private List<ChildrenBeanX> children;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ChildrenBeanX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBeanX> children) {
        this.children = children;
    }

    // 实现 IPickerViewData 接口，
    // 这个用来显示在PickerView上面的字符串，
    // PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
    @Override
    public String getPickerViewText() {
        return this.text;
    }

    public class ChildrenBeanX {
        /**
         * value : 110100
         * text : 北京市
         * children : [{"value":110114,"text":"昌平区","children":null},{"value":110105,"text":"朝阳区","children":null},{"value":110115,"text":"大兴区","children":null},{"value":110101,"text":"东城区","children":null},{"value":110111,"text":"房山区","children":null},{"value":110106,"text":"丰台区","children":null},{"value":110108,"text":"海淀区","children":null},{"value":110116,"text":"怀柔区","children":null},{"value":110109,"text":"门头沟区","children":null},{"value":110228,"text":"密云县","children":null},{"value":110117,"text":"平谷区","children":null},{"value":110107,"text":"石景山区","children":null},{"value":110113,"text":"顺义区","children":null},{"value":110112,"text":"通州区","children":null},{"value":110102,"text":"西城区","children":null},{"value":110229,"text":"延庆县","children":null}]
         */

        private int value;
        private String text;
        private List<ChildrenBean> children;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public class ChildrenBean {
            /**
             * value : 110114
             * text : 昌平区
             * children : null
             */

            private int value;
            private String text;
            private Object children;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public Object getChildren() {
                return children;
            }

            public void setChildren(Object children) {
                this.children = children;
            }
        }
    }


}
