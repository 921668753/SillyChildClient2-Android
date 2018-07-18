package com.sillykid.app.entity.mine.deliveryaddress;

import com.common.cklibrary.entity.BaseResult;
import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class AddressRegionBean extends BaseResult<List<AddressRegionBean.DataBean>> {


    /**
     * result : 1
     * message : null
     * data : [{"region_id":-1,"p_region_id":0,"region_path":",-1,","region_grade":1,"local_name":"未知","zipcode":null,"cod":"1","children":[{"region_id":51984,"p_region_id":-1,"region_path":",-1,51984,","region_grade":2,"local_name":"未知","zipcode":null,"cod":"1","children":[{"region_id":51985,"p_region_id":51984,"region_path":",-1,51984,51985","region_grade":3,"local_name":"未知","zipcode":null,"cod":"1","children":[]}]}]},{"region_id":1,"p_region_id":0,"region_path":",1,","region_grade":1,"local_name":"北京","zipcode":"","cod":"1","children":[{"region_id":2901,"p_region_id":1,"region_path":",1,2901,","region_grade":2,"local_name":"昌平区","zipcode":null,"cod":"1","children":[{"region_id":2906,"p_region_id":2901,"region_path":",1,2901,2906,","region_grade":3,"local_name":"城区以外","zipcode":null,"cod":"1","children":[]}]},{"region_id":2953,"p_region_id":1,"region_path":",1,2953,","region_grade":2,"local_name":"平谷区","zipcode":null,"cod":"1","children":[{"region_id":6666,"p_region_id":2953,"region_path":",1,2953,6666,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":2801,"p_region_id":1,"region_path":",1,2801,","region_grade":2,"local_name":"西城区","zipcode":null,"cod":"1","children":[{"region_id":2827,"p_region_id":2801,"region_path":",1,2801,2827,","region_grade":3,"local_name":"内环到二环里","zipcode":null,"cod":"1","children":[]}]},{"region_id":2802,"p_region_id":1,"region_path":",1,2802,","region_grade":2,"local_name":"东城区","zipcode":null,"cod":"1","children":[{"region_id":2821,"p_region_id":2802,"region_path":",1,2802,2821,","region_grade":3,"local_name":"内环到三环里","zipcode":null,"cod":"1","children":[]}]},{"region_id":2803,"p_region_id":1,"region_path":",1,2803,","region_grade":2,"local_name":"崇文区","zipcode":null,"cod":"1","children":[{"region_id":2829,"p_region_id":2803,"region_path":",1,2803,2829,","region_grade":3,"local_name":"一环到二环","zipcode":null,"cod":"1","children":[]}]},{"region_id":2804,"p_region_id":1,"region_path":",1,2804,","region_grade":2,"local_name":"宣武区","zipcode":null,"cod":"1","children":[{"region_id":2828,"p_region_id":2804,"region_path":",1,2804,2828,","region_grade":3,"local_name":"内环到三环里","zipcode":null,"cod":"1","children":[]}]},{"region_id":2806,"p_region_id":1,"region_path":",1,2806,","region_grade":2,"local_name":"石景山区","zipcode":null,"cod":"1","children":[{"region_id":2831,"p_region_id":2806,"region_path":",1,2806,2831,","region_grade":3,"local_name":"四环到五环内","zipcode":null,"cod":"1","children":[]}]},{"region_id":2807,"p_region_id":1,"region_path":",1,2807,","region_grade":2,"local_name":"门头沟","zipcode":null,"cod":"1","children":[{"region_id":51552,"p_region_id":2807,"region_path":",1,2807,51552,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":2808,"p_region_id":1,"region_path":",1,2808,","region_grade":2,"local_name":"房山区","zipcode":null,"cod":"1","children":[{"region_id":51528,"p_region_id":2808,"region_path":",1,2808,51528,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":3065,"p_region_id":1,"region_path":",1,3065,","region_grade":2,"local_name":"延庆县","zipcode":null,"cod":"1","children":[{"region_id":51505,"p_region_id":3065,"region_path":",1,3065,51505,","region_grade":3,"local_name":"延庆镇","zipcode":null,"cod":"1","children":[]}]},{"region_id":2810,"p_region_id":1,"region_path":",1,2810,","region_grade":2,"local_name":"大兴区","zipcode":null,"cod":"1","children":[{"region_id":4194,"p_region_id":2810,"region_path":",1,2810,4194,","region_grade":3,"local_name":"四环至五环之间","zipcode":null,"cod":"1","children":[]}]},{"region_id":2812,"p_region_id":1,"region_path":",1,2812,","region_grade":2,"local_name":"顺义区","zipcode":null,"cod":"1","children":[{"region_id":51125,"p_region_id":2812,"region_path":",1,2812,51125,","region_grade":3,"local_name":"北石槽镇","zipcode":null,"cod":"1","children":[]}]},{"region_id":2814,"p_region_id":1,"region_path":",1,2814,","region_grade":2,"local_name":"怀柔区","zipcode":null,"cod":"1","children":[{"region_id":6115,"p_region_id":2814,"region_path":",1,2814,6115,","region_grade":3,"local_name":"城区以内","zipcode":null,"cod":"1","children":[]}]},{"region_id":2816,"p_region_id":1,"region_path":",1,2816,","region_grade":2,"local_name":"密云区","zipcode":"","cod":"1","children":[{"region_id":6667,"p_region_id":2816,"region_path":",1,2816,6667,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]},{"region_id":2862,"p_region_id":2816,"region_path":",1,2816,2862,","region_grade":3,"local_name":"城区以外","zipcode":"","cod":"1","children":[]}]},{"region_id":2800,"p_region_id":1,"region_path":",1,2800,","region_grade":2,"local_name":"海淀区","zipcode":"","cod":"1","children":[{"region_id":2848,"p_region_id":2800,"region_path":",1,2800,2848,","region_grade":3,"local_name":"三环以内","zipcode":null,"cod":"1","children":[]}]},{"region_id":2805,"p_region_id":1,"region_path":",1,2805,","region_grade":2,"local_name":"丰台区","zipcode":"","cod":"1","children":[{"region_id":2832,"p_region_id":2805,"region_path":",1,2805,2832,","region_grade":3,"local_name":"四环到五环之间","zipcode":null,"cod":"1","children":[]}]}]},{"region_id":2,"p_region_id":0,"region_path":",2,","region_grade":1,"local_name":"上海","zipcode":"","cod":"1","children":[{"region_id":2817,"p_region_id":2,"region_path":",2,2817,","region_grade":2,"local_name":"静安区","zipcode":null,"cod":"1","children":[{"region_id":51973,"p_region_id":2817,"region_path":",2,2817,51973,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":2820,"p_region_id":2,"region_path":",2,2820,","region_grade":2,"local_name":"闸北区","zipcode":null,"cod":"1","children":[{"region_id":51972,"p_region_id":2820,"region_path":",2,2820,51972,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":2822,"p_region_id":2,"region_path":",2,2822,","region_grade":2,"local_name":"虹口区","zipcode":null,"cod":"1","children":[{"region_id":51979,"p_region_id":2822,"region_path":",2,2822,51979,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":2823,"p_region_id":2,"region_path":",2,2823,","region_grade":2,"local_name":"杨浦区","zipcode":null,"cod":"1","children":[{"region_id":51974,"p_region_id":2823,"region_path":",2,2823,51974,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":2825,"p_region_id":2,"region_path":",2,2825,","region_grade":2,"local_name":"闵行区","zipcode":null,"cod":"1","children":[{"region_id":51931,"p_region_id":2825,"region_path":",2,2825,51931,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":2826,"p_region_id":2,"region_path":",2,2826,","region_grade":2,"local_name":"嘉定区","zipcode":null,"cod":"1","children":[{"region_id":51941,"p_region_id":2826,"region_path":",2,2826,51941,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":2830,"p_region_id":2,"region_path":",2,2830,","region_grade":2,"local_name":"浦东新区","zipcode":null,"cod":"1","children":[{"region_id":51800,"p_region_id":2830,"region_path":",2,2830,51800,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":2833,"p_region_id":2,"region_path":",2,2833,","region_grade":2,"local_name":"青浦区","zipcode":null,"cod":"1","children":[{"region_id":51959,"p_region_id":2833,"region_path":",2,2833,51959,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":2834,"p_region_id":2,"region_path":",2,2834,","region_grade":2,"local_name":"松江区","zipcode":null,"cod":"1","children":[{"region_id":51982,"p_region_id":2834,"region_path":",2,2834,51982,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":2835,"p_region_id":2,"region_path":",2,2835,","region_grade":2,"local_name":"金山区","zipcode":null,"cod":"1","children":[{"region_id":51960,"p_region_id":2835,"region_path":",2,2835,51960,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":2837,"p_region_id":2,"region_path":",2,2837,","region_grade":2,"local_name":"奉贤区","zipcode":null,"cod":"1","children":[{"region_id":51928,"p_region_id":2837,"region_path":",2,2837,51928,","region_grade":3,"local_name":"南桥镇","zipcode":null,"cod":"1","children":[]}]},{"region_id":2841,"p_region_id":2,"region_path":",2,2841,","region_grade":2,"local_name":"普陀区","zipcode":null,"cod":"1","children":[{"region_id":51980,"p_region_id":2841,"region_path":",2,2841,51980,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":78,"p_region_id":2,"region_path":",2,78,","region_grade":2,"local_name":"黄浦区","zipcode":null,"cod":"1","children":[{"region_id":51978,"p_region_id":78,"region_path":",2,78,51978,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":2919,"p_region_id":2,"region_path":",2,2919,","region_grade":2,"local_name":"崇明县","zipcode":null,"cod":"1","children":[{"region_id":50779,"p_region_id":2919,"region_path":",2,2919,50779,","region_grade":3,"local_name":"堡镇","zipcode":null,"cod":"1","children":[]}]},{"region_id":2813,"p_region_id":2,"region_path":",2,2813,","region_grade":2,"local_name":"徐汇区","zipcode":null,"cod":"1","children":[{"region_id":51976,"p_region_id":2813,"region_path":",2,2813,51976,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]},{"region_id":2815,"p_region_id":2,"region_path":",2,2815,","region_grade":2,"local_name":"长宁区","zipcode":null,"cod":"1","children":[{"region_id":51975,"p_region_id":2815,"region_path":",2,2815,51975,","region_grade":3,"local_name":"城区","zipcode":null,"cod":"1","children":[]}]}]}]
     */

    public static class DataBean implements IPickerViewData {
        /**
         * region_id : -1
         * p_region_id : 0
         * region_path : ,-1,
         * region_grade : 1
         * local_name : 未知
         * zipcode : null
         * cod : 1
         * children : [{"region_id":51984,"p_region_id":-1,"region_path":",-1,51984,","region_grade":2,"local_name":"未知","zipcode":null,"cod":"1","children":[{"region_id":51985,"p_region_id":51984,"region_path":",-1,51984,51985","region_grade":3,"local_name":"未知","zipcode":null,"cod":"1","children":[]}]}]
         */

        private int region_id;
        private int p_region_id;
        private String region_path;
        private String region_grade;
        private String local_name;
        private String zipcode;
        private String cod;
        private List<ChildrenBeanX> children;

        public int getRegion_id() {
            return region_id;
        }

        public void setRegion_id(int region_id) {
            this.region_id = region_id;
        }

        public int getP_region_id() {
            return p_region_id;
        }

        public void setP_region_id(int p_region_id) {
            this.p_region_id = p_region_id;
        }

        public String getRegion_path() {
            return region_path;
        }

        public void setRegion_path(String region_path) {
            this.region_path = region_path;
        }

        public String getRegion_grade() {
            return region_grade;
        }

        public void setRegion_grade(String region_grade) {
            this.region_grade = region_grade;
        }

        public String getLocal_name() {
            return local_name;
        }

        public void setLocal_name(String local_name) {
            this.local_name = local_name;
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

        public List<ChildrenBeanX> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBeanX> children) {
            this.children = children;
        }

        @Override
        public String getPickerViewText() {
            return local_name;
        }

        public static class ChildrenBeanX implements IPickerViewData {
            /**
             * region_id : 51984
             * p_region_id : -1
             * region_path : ,-1,51984,
             * region_grade : 2
             * local_name : 未知
             * zipcode : null
             * cod : 1
             * children : [{"region_id":51985,"p_region_id":51984,"region_path":",-1,51984,51985","region_grade":3,"local_name":"未知","zipcode":null,"cod":"1","children":[]}]
             */

            private int region_id;
            private int p_region_id;
            private String region_path;
            private int region_grade;
            private String local_name;
            private String zipcode;
            private String cod;
            private List<ChildrenBean> children;

            public int getRegion_id() {
                return region_id;
            }

            public void setRegion_id(int region_id) {
                this.region_id = region_id;
            }

            public int getP_region_id() {
                return p_region_id;
            }

            public void setP_region_id(int p_region_id) {
                this.p_region_id = p_region_id;
            }

            public String getRegion_path() {
                return region_path;
            }

            public void setRegion_path(String region_path) {
                this.region_path = region_path;
            }

            public int getRegion_grade() {
                return region_grade;
            }

            public void setRegion_grade(int region_grade) {
                this.region_grade = region_grade;
            }

            public String getLocal_name() {
                return local_name;
            }

            public void setLocal_name(String local_name) {
                this.local_name = local_name;
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

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            @Override
            public String getPickerViewText() {
                return local_name;
            }

            public static class ChildrenBean implements IPickerViewData {
                /**
                 * region_id : 51985
                 * p_region_id : 51984
                 * region_path : ,-1,51984,51985
                 * region_grade : 3
                 * local_name : 未知
                 * zipcode : null
                 * cod : 1
                 * children : []
                 */

                private int region_id;
                private int p_region_id;
                private String region_path;
                private int region_grade;
                private String local_name;
                private String zipcode;
                private String cod;
                private List<?> children;

                public int getRegion_id() {
                    return region_id;
                }

                public void setRegion_id(int region_id) {
                    this.region_id = region_id;
                }

                public int getP_region_id() {
                    return p_region_id;
                }

                public void setP_region_id(int p_region_id) {
                    this.p_region_id = p_region_id;
                }

                public String getRegion_path() {
                    return region_path;
                }

                public void setRegion_path(String region_path) {
                    this.region_path = region_path;
                }

                public int getRegion_grade() {
                    return region_grade;
                }

                public void setRegion_grade(int region_grade) {
                    this.region_grade = region_grade;
                }

                public String getLocal_name() {
                    return local_name;
                }

                public void setLocal_name(String local_name) {
                    this.local_name = local_name;
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

                public List<?> getChildren() {
                    return children;
                }

                public void setChildren(List<?> children) {
                    this.children = children;
                }

                @Override
                public String getPickerViewText() {
                    return local_name;
                }
            }
        }
    }
}
