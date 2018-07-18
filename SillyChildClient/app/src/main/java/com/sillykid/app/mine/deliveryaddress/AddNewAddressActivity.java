package com.sillykid.app.mine.deliveryaddress;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.deliveryaddress.AddNewAddressBean;
import com.sillykid.app.entity.mine.deliveryaddress.AddressRegionBean;
import com.sillykid.app.entity.mine.deliveryaddress.RegionListBean;
import com.sillykid.app.entity.mine.deliveryaddress.RegionListBean.DataBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 新添加收货地址/编辑收货地址
 * Created by Administrator on 2017/9/2.
 */

public class AddNewAddressActivity extends BaseActivity implements AddNewAddressContract.View {

    @BindView(id = R.id.et_name)
    private EditText et_name;

    @BindView(id = R.id.et_phone)
    private EditText et_phone;

    @BindView(id = R.id.ll_select, click = true)
    private LinearLayout ll_select;

    @BindView(id = R.id.tv_selectAddress)
    private TextView tv_selectAddress;

    @BindView(id = R.id.et_detailedAddress)
    private EditText et_detailedAddress;

    @BindView(id = R.id.iv_defaultAddress, click = true)
    private ImageView iv_defaultAddress;

    @BindView(id = R.id.tv_addAddress, click = true)
    private TextView tv_addAddress;

    private int def_addr = 0;//0:不作为默认地址。1：作为默认地址
    private int province_id = 0;
    private int city_id = 0;
    private int region_id = 0;
    private String province = "";
    private String city = "";
    private String region = "";
    private int town_id = 0;
    private OptionsPickerView pvNoLinkOptions = null;
    private List<DataBean> provinceList = null;
    private List<DataBean> cityList = null;
    private List<DataBean> areaList = null;
    private int areaOptions3 = 0;
    private int cityOptions2 = 0;
    private int provinceOptions1 = 0;


    private OptionsPickerView pvLinkOptions = null;

    private List<AddressRegionBean.DataBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<AddressRegionBean.DataBean.ChildrenBeanX>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<AddressRegionBean.DataBean.ChildrenBeanX.ChildrenBean>>> options3Items = new ArrayList<>();

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_newaddress);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AddNewAddressPresenter(this);
        provinceList = new ArrayList<DataBean>();
        cityList = new ArrayList<DataBean>();
        areaList = new ArrayList<DataBean>();
        initNoLinkOptionsPicker();
        initLinkOptionsPicker();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        String title = getIntent().getStringExtra("title");
        if (StringUtils.isEmpty(title)) {
            ActivityTitleUtils.initToolbar(aty, getString(R.string.addAddress), true, R.id.titlebar);
            tv_addAddress.setText(getString(R.string.addAddress));
        } else {
            ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);
            tv_addAddress.setText(getString(R.string.save));
            getSuccess("", 0);
        }
        ((AddNewAddressContract.Presenter) mPresenter).getAddress(0);
        //   ((AddNewAddressContract.Presenter) mPresenter).getRegionList(0, 3);
    }

    /**
     * 初始化地区选择
     */
    private void initNoLinkOptionsPicker() {// 不联动的多级选项

        pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                province_id = provinceList.get(options1).getRegion_id();
                city_id = cityList.get(options2).getRegion_id();
                region_id = areaList.get(options3).getRegion_id();
                ((TextView) v).setText(provinceList.get(options1).getLocal_name() + cityList.get(options2).getLocal_name() + areaList.get(options3).getLocal_name());
            }
        })
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        if (provinceOptions1 == options1 && cityOptions2 == options2) {
                            areaOptions3 = options3;
                            return;
                        }
                        if (provinceOptions1 == options1 && cityOptions2 != options2) {
                            cityOptions2 = options2;
                            areaOptions3 = 0;
                            getRegionList(cityList, cityList.get(options2).getLocal_name(), 5);
                            return;
                        }
                        provinceOptions1 = options1;
                        cityOptions2 = 0;
                        areaOptions3 = 0;
                        getRegionList(provinceList, provinceList.get(options1).getLocal_name(), 4);
                    }
                })
                .build();
    }

    /**
     * 联动地区选择
     */
    private void initLinkOptionsPicker() {
        pvLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                province_id = options1Items.get(options1).getRegion_id();
                city_id = options2Items.get(options1).get(options2).getRegion_id();
                region_id = options3Items.get(options1).get(options2).get(options3).getRegion_id();
                ((TextView) v).setText(options1Items.get(options1).getLocal_name() + options2Items.get(options1).get(options2).getLocal_name() + options3Items.get(options1).get(options2).get(options3).getLocal_name());
            }
        }).build();
        //  pvLinkOptions.setPicker()
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_select:
                SoftKeyboardUtils.packUpKeyboard(aty);
                pvLinkOptions.show(tv_selectAddress);
                break;
            case R.id.tv_addAddress:
                SoftKeyboardUtils.packUpKeyboard(aty);
                if (StringUtils.isEmpty(getIntent().getStringExtra("title"))) {
                    showLoadingDialog(getString(R.string.saveLoad));
                    ((AddNewAddressContract.Presenter) mPresenter).postAddAddress(et_name.getText().toString().trim(), et_phone.getText().toString().trim(),
                            province_id, city_id, region_id, town_id, et_detailedAddress.getText().toString().trim(), def_addr);
                } else {
                    showLoadingDialog(getString(R.string.saveLoad));
                    ((AddNewAddressContract.Presenter) mPresenter).postEditAddress(getIntent().getIntExtra("addr_id", 0), et_name.getText().toString().trim(), et_phone.getText().toString().trim(),
                            province_id, city_id, region_id, town_id, et_detailedAddress.getText().toString().trim(), def_addr);
                }
                break;
            case R.id.iv_defaultAddress:
                SoftKeyboardUtils.packUpKeyboard(aty);
                if (def_addr == 0) {
                    def_addr = 1;
                    iv_defaultAddress.setImageResource(R.mipmap.address_set_as_default_selected);
                    //     tv_default.setTextColor(getResources().getColor(R.color.greenColors));
                } else {
                    def_addr = 0;
                    iv_defaultAddress.setImageResource(R.mipmap.address_set_as_default_unselected);
                }
                break;
        }
    }


    @Override
    public void setPresenter(AddNewAddressContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == -1) {
            AddressRegionBean addressRegionBean = (AddressRegionBean) JsonUtil.getInstance().json2Obj(success, AddressRegionBean.class);
            options1Items = addressRegionBean.getData();
            Log.d("tag1", options1Items.size() + "=province");
            for (int i = 0; i < options1Items.size(); i++) {//遍历省份
                ArrayList<AddressRegionBean.DataBean.ChildrenBeanX> CityList = new ArrayList<>();//该省的城市列表（第二级）
                ArrayList<ArrayList<AddressRegionBean.DataBean.ChildrenBeanX.ChildrenBean>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
                if (StringUtils.isEmpty(options1Items.get(i).getLocal_name())) {
                    continue;
                }
                if (province != null && province.equals(options1Items.get(i).getLocal_name())) {
                    provinceOptions1 = i;
                    Log.d("tag1", provinceOptions1 + "=province");
                }
                if (options1Items.get(i).getChildren() == null || options1Items.get(i).getChildren().size() <= 0) {
                    AddressRegionBean.DataBean.ChildrenBeanX childrenBeanX = new AddressRegionBean.DataBean.ChildrenBeanX();
                    childrenBeanX.setRegion_id(0);
                    childrenBeanX.setLocal_name("");
                    CityList.add(childrenBeanX);//添加城市
                    ArrayList<AddressRegionBean.DataBean.ChildrenBeanX.ChildrenBean> childrenBeanList1 = new ArrayList<>();//该城市的所有地区列表
                    AddressRegionBean.DataBean.ChildrenBeanX.ChildrenBean childrenBean = new AddressRegionBean.DataBean.ChildrenBeanX.ChildrenBean();
                    childrenBean.setRegion_id(0);
                    childrenBean.setLocal_name("");
                    childrenBeanList1.add(childrenBean);
                    Province_AreaList.add(childrenBeanList1);
                    options2Items.add(CityList);
                    options3Items.add(Province_AreaList);
                    continue;
                }
                for (int c = 0; c < options1Items.get(i).getChildren().size(); c++) {//遍历该省份的所有城市
                    AddressRegionBean.DataBean.ChildrenBeanX CityName = options1Items.get(i).getChildren().get(c);
                    if (StringUtils.isEmpty(CityName.getLocal_name())) {
                        CityName = new AddressRegionBean.DataBean.ChildrenBeanX();
                        CityName.setRegion_id(0);
                        CityName.setLocal_name("");
                    }
                    CityList.add(CityName);//添加城市
                    ArrayList<AddressRegionBean.DataBean.ChildrenBeanX.ChildrenBean> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                    if (city != null && city.equals(options1Items.get(i).getChildren().get(c).getLocal_name())) {
                        cityOptions2 = c;
                        Log.d("tag1", cityOptions2 + "=city");
                    }
                    //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                    if (options1Items.get(i).getChildren().get(c).getChildren() == null
                            || options1Items.get(i).getChildren().get(c).getChildren().size() == 0) {
                        AddressRegionBean.DataBean.ChildrenBeanX.ChildrenBean AreaName = new AddressRegionBean.DataBean.ChildrenBeanX.ChildrenBean();
                        AreaName.setRegion_id(0);
                        AreaName.setLocal_name("");
                        City_AreaList.add(AreaName);
                    } else {
                        for (int d = 0; d < options1Items.get(i).getChildren().get(c).getChildren().size(); d++) {//该城市对应地区所有数据
                            AddressRegionBean.DataBean.ChildrenBeanX.ChildrenBean AreaName = options1Items.get(i).getChildren().get(c).getChildren().get(d);
                            if (region != null && region.startsWith(AreaName.getLocal_name())) {
                                areaOptions3 = d;
                                Log.d("tag1", areaOptions3 + "=Area");
                            }
                            City_AreaList.add(AreaName);//添加该城市所有地区数据
                        }
                    }
                    Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                }
                /**
                 * 添加城市数据
                 */
                options2Items.add(CityList);
                Log.d("tag1", options2Items.size() + "=CityList");
                /**
                 * 添加地区数据
                 */
                options3Items.add(Province_AreaList);
                Log.d("tag1", options3Items.size() + "=Province_AreaList");
            }

            pvLinkOptions.setPicker(options1Items, options2Items, options3Items);
            pvLinkOptions.setSelectOptions(provinceOptions1, cityOptions2, areaOptions3);
            //   pvLinkOptions.setPicker(addressRegionBean.getData(),);
        } else if (flag == 0) {
//            AddressBean addressBean = (AddressBean) JsonUtil.json2Obj(success, AddressBean.class);
//            if (addressBean.getData() == null || addressBean.getData().getAddr_id() <= 0) {
//                errorMsg(getString(R.string.serverReturnsDataError), 0);
//                return;
//            }
            Intent intent = getIntent();
            et_name.setText(intent.getStringExtra("name"));
            et_phone.setText(intent.getStringExtra("mobile"));
            province = intent.getStringExtra("province");
            city = intent.getStringExtra("city");
            region = intent.getStringExtra("region");
            tv_selectAddress.setText(province + city + region);
            province_id = intent.getIntExtra("province_id", 0);
            city_id = intent.getIntExtra("city_id", 0);
            region_id = intent.getIntExtra("region_id", 0);
            // town_id = addressBean.getData().getTown_id();
            et_detailedAddress.setText(intent.getStringExtra("addr"));
            def_addr = intent.getIntExtra("def_addr", 0);
            if (def_addr == 1) {
                iv_defaultAddress.setImageResource(R.mipmap.address_set_as_default_selected);
            } else {
                iv_defaultAddress.setImageResource(R.mipmap.address_set_as_default_unselected);
            }

        } else if (flag == 1) {
            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
            finish();
        } else if (flag == 2) {
            AddNewAddressBean addNewAddressBean = (AddNewAddressBean) JsonUtil.getInstance().json2Obj(success, AddNewAddressBean.class);
            Intent intent = getIntent();
            if (addNewAddressBean.getData() != null && addNewAddressBean.getData().getAddr_id() > 0) {
                intent.putExtra("addr_id", addNewAddressBean.getData().getAddr_id());
                intent.putExtra("name", addNewAddressBean.getData().getName());
                intent.putExtra("mobile", addNewAddressBean.getData().getMobile());
                intent.putExtra("provinceRegion", addNewAddressBean.getData().getProvince() + "  " + addNewAddressBean.getData().getCity() + "  " + addNewAddressBean.getData().getRegion());
                intent.putExtra("addr", addNewAddressBean.getData().getAddr());
            }
            setResult(RESULT_OK, intent);
            finish();
        } else if (flag == 3) {
            RegionListBean regionListBean = (RegionListBean) JsonUtil.json2Obj(success, RegionListBean.class);
            if (regionListBean.getData() != null && regionListBean.getData().size() > 0) {
                provinceList.addAll(regionListBean.getData());
                getRegionList(provinceList, province, 4);
            }
        } else if (flag == 4) {
            RegionListBean regionListBean = (RegionListBean) JsonUtil.json2Obj(success, RegionListBean.class);
            if (regionListBean.getData() != null && regionListBean.getData().size() > 0) {
                cityList.clear();
                cityList.addAll(regionListBean.getData());
                getRegionList(cityList, city, 5);
            }
        } else if (flag == 5) {
            RegionListBean regionListBean = (RegionListBean) JsonUtil.json2Obj(success, RegionListBean.class);
            if (regionListBean.getData() != null && regionListBean.getData().size() > 0) {
                areaList.clear();
                areaList.addAll(regionListBean.getData());
                getRegionList(areaList, region, 6);
            } else {
                areaList.clear();
                DataBean dataBean = new DataBean();
                dataBean.setLocal_name("");
                areaList.add(dataBean);
            }
            pvNoLinkOptions.setNPicker(provinceList, cityList, areaList);
            pvNoLinkOptions.setSelectOptions(provinceOptions1, cityOptions2, areaOptions3);
        }
    }

    /**
     * 获取地区二级列表
     */
    private void getRegionList(List<DataBean> list, String positionName, int flag) {
        for (int i = 0; i < list.size(); i++) {
            if (positionName.contains(list.get(i).getLocal_name()) && flag == 4 || StringUtils.isEmpty(positionName) && i == 0 && flag == 4) {
                provinceOptions1 = i;
                ((AddNewAddressContract.Presenter) mPresenter).getRegionList(list.get(i).getRegion_id(), flag);
                return;
            } else if (positionName.contains(list.get(i).getLocal_name()) && flag == 5 || StringUtils.isEmpty(positionName) && i == 0 && flag == 5) {
                cityOptions2 = i;
                ((AddNewAddressContract.Presenter) mPresenter).getRegionList(list.get(i).getRegion_id(), flag);
                return;
            } else if (positionName.contains(list.get(i).getLocal_name()) && flag == 6 || StringUtils.isEmpty(positionName) && i == 0 && flag == 6) {
                areaOptions3 = i;
                return;
            }
        }
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(this, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pvNoLinkOptions != null && pvNoLinkOptions.isShowing()) {
            pvNoLinkOptions.dismiss();
        }

        options1Items.clear();
        options1Items = null;
        options2Items.clear();
        options2Items = null;
        options3Items.clear();
        options3Items = null;

        pvNoLinkOptions = null;
        provinceList.clear();
        provinceList = null;
        cityList.clear();
        cityList = null;
        areaList.clear();
        areaList = null;
    }
}
