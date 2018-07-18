package com.sillykid.app.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.NationalCity;

import org.json.JSONArray;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/2/21.
 */

public abstract class PickerViewUtil {

    private int type = 0;
    private ArrayList<NationalCity> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private Thread thread1 = null;
    private Thread thread2 = null;

    public Context context;
    private OptionsPickerView pvOptions;
    private int currentLocationProvinceItemPosition = 0;
    private int currentLocationCityItemPosition = 0;
    private int currentLocationAreaItemPosition = 0;

//    private static PickerViewUtil pickerViewUtil;


    public PickerViewUtil(Context context, int type) {
        this.context = context;
        this.type = type;
        //添加三级联动数据
        if (type == 0) {
            showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.dataLoad));
            thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    initJsonData(0);
                    thread1.interrupt();
                    dismissLoadingDialog();
                }
            });
            thread1.start();
        } else {
            showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.dataLoad));
            thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    initJsonData(1);
                    thread2.interrupt();
                    dismissLoadingDialog();
                }
            });
            thread2.start();
        }
    }

    public void onoptionsSelectListener() {
        if (pvOptions != null) {
            pvOptions.show();
            return;
        }
        pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = null;
                String options1Items1 = options1Items.get(options1).getPickerViewText();
                String options1Items2 = options2Items.get(options1).get(option2);
                String options1Items3 = options3Items.get(options1).get(option2).get(options3);
                if (type == 0) {
                    if (options1Items1.equals(options1Items2.substring(0, options1Items2.length() - 1))) {
                        tx = options1Items2 + options1Items3;
                    } else if (options1Items2.equals("直辖县级")) {
                        tx = options1Items1 + options1Items3;
                    } else {
                        tx = options1Items1 + options1Items2 + options1Items3;
                    }
                } else {
                    if (options1Items2.equals("中国")) {
                        tx = "";
                    } else if (options1Items2.equals(options1Items3)) {
                        tx = options1Items1;
                    } else if (options1Items1.equals(options1Items2.substring(0, options1Items2.length() - 1)) && options1Items2.equals(options1Items3.substring(1))) {
                        tx = options1Items2;
                    } else if (options1Items1.equals(options1Items2.substring(0, options1Items2.length() - 1))) {
                        tx = options1Items2 + options1Items3;
                    } else if (options1Items2.equals(options1Items3.substring(1))) {
                        tx = options1Items1 + options1Items2;
                    } else if (options1Items2.equals("直辖县级")) {
                        tx = options1Items1 + options1Items3;
                    } else {
                        tx = options1Items1 + options1Items2 + options1Items3;
                    }
                }
                getAddress(tx, options1, option2, options3);
            }
        })
                .setContentTextSize(18)//设置滚轮文字大小
                .build();
        pvOptions.setSelectOptions(currentLocationProvinceItemPosition, currentLocationCityItemPosition, currentLocationAreaItemPosition);
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    public void onoptionsSelect(int currentLocationProvinceItemPosition, int currentLocationCityItemPosition, int currentLocationAreaItemPosition) {
        if (pvOptions != null) {
            pvOptions.setSelectOptions(currentLocationProvinceItemPosition, currentLocationCityItemPosition, currentLocationAreaItemPosition);
        }
    }


    public void onDestroy() {
        if (pvOptions != null) {
            pvOptions.dismiss();
        }
        pvOptions = null;
        thread1 = null;
        thread2 = null;
        options1Items = null;
        options2Items = null;
        options3Items = null;
    }

    public void onDismiss() {
        pvOptions.dismiss();
    }

    public void onShow() {
        pvOptions.show();
    }

    public boolean isShowing() {
        if (pvOptions != null) {
            return pvOptions.isShowing();
        }
        return false;
    }


    public abstract void getAddress(String address, int province, int city, int area);

    public ArrayList<NationalCity> parseData(String result) {//Gson 解析
        ArrayList<NationalCity> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            for (int i = 0; i < data.length(); i++) {
                NationalCity entity = (NationalCity) JsonUtil.getInstance().json2Obj(data.optJSONObject(i).toString(), NationalCity.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }


    /**
     * 添加联动数据
     */

    private void initJsonData(int type) {//解析数据
        try {
            /**
             * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
             * 关键逻辑在于循环体
             *
             * */
            String JsonData = null;
            String currentLocationProvince = PreferenceHelper.readString(context, StringConstants.FILENAME, "currentLocationProvince");
            String currentLocationCity = PreferenceHelper.readString(context, StringConstants.FILENAME, "currentLocationCity");
            String currentLocationArea = PreferenceHelper.readString(context, StringConstants.FILENAME, "currentLocationArea");
            if (type == 0) {
                JsonData = new GetJsonDataUtil().getJson(context, "province.json");//获取assets目录下的json文件数据
            } else {
                JsonData = new GetJsonDataUtil().getJson(context, "province1.json");//获取assets目录下的json文件数据
            }
            Log.d("tag", JsonData);
            ArrayList<NationalCity> jsonBean = parseData(JsonData);//用Gson 转成实体
            /**
             * 添加省份数据
             *
             * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
             * PickerView会通过getPickerViewText方法获取字符串显示出来。
             */
            options1Items = jsonBean;
            Log.d("tag1", options3Items.size() + "=province");
            for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
                ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
                ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
                if (StringUtils.isEmpty(jsonBean.get(i).getText())) {
                    continue;
                }
                if (currentLocationProvince != null && currentLocationProvince.equals(jsonBean.get(i).getText())) {
                    currentLocationProvinceItemPosition = i;
                    Log.d("tag1", currentLocationProvinceItemPosition + "=province");
                }
                for (int c = 0; c < jsonBean.get(i).getChildren().size(); c++) {//遍历该省份的所有城市
                    String CityName = jsonBean.get(i).getChildren().get(c).getText();
                    if (StringUtils.isEmpty(CityName)) {
                        CityName = "";
                    }
                    CityList.add(CityName);//添加城市
                    ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                    if (currentLocationCity != null && currentLocationCity.equals(jsonBean.get(i).getChildren().get(c).getText())) {
                        currentLocationCityItemPosition = c;
                        Log.d("tag1", currentLocationCityItemPosition + "=city");
                    }
                    //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                    if (jsonBean.get(i).getChildren().get(c).getChildren() == null
                            || jsonBean.get(i).getChildren().get(c).getChildren().size() == 0) {
                        City_AreaList.add("");
                    } else {
                        for (int d = 0; d < jsonBean.get(i).getChildren().get(c).getChildren().size(); d++) {//该城市对应地区所有数据
                            String AreaName = jsonBean.get(i).getChildren().get(c).getChildren().get(d).getText();

                            if (currentLocationArea != null && currentLocationArea.startsWith(AreaName)) {
                                currentLocationAreaItemPosition = d;
                                Log.d("tag1", currentLocationAreaItemPosition + "=Area");
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
                Log.d("tag1", options3Items.size() + "=CityList");
                /**
                 * 添加地区数据
                 */
                options3Items.add(Province_AreaList);
                Log.d("tag1", options3Items.size() + "=Province_AreaList");

            }
        } catch (Exception e) {
            options1Items.clear();
            options2Items.clear();
            options3Items.clear();
            initJsonData(type);
        }
//        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    private SweetAlertDialog mLoadingDialog1 = null;

    @SuppressWarnings("deprecation")
    public void showLoadingDialog(String title) {
        if (mLoadingDialog1 == null) {
            mLoadingDialog1 = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog1.getProgressHelper().setBarColor(context.getResources().getColor(R.color.dialogLoadingColor));
            mLoadingDialog1.setCancelable(false);
            mLoadingDialog1.setTitleText(title);
        }
        mLoadingDialog1.show();
        ((View) mLoadingDialog1.getButton(SweetAlertDialog.BUTTON_CONFIRM).getParent()).setVisibility(View.GONE);
    }


    public void dismissLoadingDialog() {
        if (mLoadingDialog1 != null && mLoadingDialog1.isShowing()) {
            try {
                mLoadingDialog1.dismiss();
            } catch (Exception e) {
                mLoadingDialog1 = null;
            }
        }
        mLoadingDialog1 = null;
    }
}
