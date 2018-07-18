package com.sillykid.app.homepage.chartercustom.personaltailor;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.StringNewUtils;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.dialog.chartercustom.CompensationChangeBackDialog;
import com.sillykid.app.dialog.chartercustom.CostsThatDialog;
import com.sillykid.app.entity.AirportDropOffBean;
import com.sillykid.app.entity.DriverPackConfigBean;
import com.sillykid.app.entity.UnsubscribeCostBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 私人定制
 * Created by Admin on 2017/9/3.
 */

public class PersonalTailorActivity extends BaseActivity implements PersonalTailorContract.View {


    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    /**
     * 出行时间
     */
    @BindView(id = R.id.ll_travelTime, click = true)
    LinearLayout ll_travelTime;

    @BindView(id = R.id.tv_travelTime)
    TextView tv_travelTime;
    long travelTime = 0;


    /**
     * 出发地
     */
    @BindView(id = R.id.et_origin)
    EditText et_origin;

    /**
     * 目的地
     */
    @BindView(id = R.id.et_destination)
    EditText et_destination;

    /**
     * 游玩天数
     */
    @BindView(id = R.id.et_playNumberDays)
    EditText et_playNumberDays;

    /**
     * 选择出行人数
     */
    @BindView(id = R.id.tv_subtractionAdult, click = true)
    TextView tv_subtractionAdult;

    @BindView(id = R.id.tv_adultNum)
    TextView tv_adultNum;

    @BindView(id = R.id.tv_addAdult, click = true)
    TextView tv_addAdult;

    @BindView(id = R.id.tv_subtractionChildren, click = true)
    TextView tv_subtractionChildren;

    @BindView(id = R.id.tv_childrenNum)
    TextView tv_childrenNum;

    @BindView(id = R.id.tv_addChildren, click = true)
    TextView tv_addChildren;

    /**
     * 出行偏好
     */
    @BindView(id = R.id.tv_travelPreferences, click = true)
    TextView tv_travelPreferences;

    /**
     * 推荐餐馆
     */
    @BindView(id = R.id.tv_recommendRestaurant, click = true)
    TextView tv_recommendRestaurant;

    /**
     * 推荐餐馆
     */
    @BindView(id = R.id.tv_recommendedAccommodation, click = true)
    TextView tv_recommendedAccommodation;
    /**
     * 行李
     */

    @BindView(id = R.id.tv_subtractionBags, click = true)
    TextView tv_subtractionBags;
    @BindView(id = R.id.tv_bags)
    TextView tv_bags;
    @BindView(id = R.id.tv_addBags, click = true)
    TextView tv_addBags;

    @BindView(id = R.id.tv_subtractionBags1, click = true)
    TextView tv_subtractionBags1;
    @BindView(id = R.id.tv_bags1)
    TextView tv_bags1;
    @BindView(id = R.id.tv_addBags1, click = true)
    TextView tv_addBags1;

    @BindView(id = R.id.tv_subtractionBags2, click = true)
    TextView tv_subtractionBags2;
    @BindView(id = R.id.tv_bags2)
    TextView tv_bags2;
    @BindView(id = R.id.tv_addBags2, click = true)
    TextView tv_addBags2;

    @BindView(id = R.id.tv_subtractionBags3, click = true)
    TextView tv_subtractionBags3;
    @BindView(id = R.id.tv_bags3)
    TextView tv_bags3;
    @BindView(id = R.id.tv_addBags3, click = true)
    TextView tv_addBags3;


    /**
     * 选择司导
     */
    @BindView(id = R.id.et_workNumber)
    EditText et_workNumber;


    /**
     * 姓名
     */
    @BindView(id = R.id.et_name)
    EditText et_name;
    /**
     * 联系方式
     */
    @BindView(id = R.id.et_contactWay)
    EditText et_contactWay;


    @BindView(id = R.id.ll_costsThat, click = true)
    LinearLayout ll_costsThat;


    @BindView(id = R.id.ll_compensationChangeBack, click = true)
    LinearLayout ll_compensationChangeBack;


    /**
     * 备注
     */
    @BindView(id = R.id.et_remark)
    EditText et_remark;

    /**
     * 提交
     */
    @BindView(id = R.id.tv_submit, click = true)
    TextView tv_submit;


    private String type = "private_person";
    private TimePickerView pvTime;


    private OptionsPickerView pvOptions;

    private List<DriverPackConfigBean.ResultBean.RestaurantChooseBean> restaurant_chooseList;
    private List<DriverPackConfigBean.ResultBean.SleepChooseBean> sleep_chooseList;
    private List<DriverPackConfigBean.ResultBean.TripChooseBean> trip_chooseList;
    private OptionsPickerView pvOptions1;
    private OptionsPickerView pvOptions2;
    private CompensationChangeBackDialog compensationChangeBackDialog;
    private CostsThatDialog costsThatDialog;

    @Override

    public void setRootView() {
        setContentView(R.layout.activity_personaltailor);
    }

    @Override
    public void initData() {
        super.initData();
        ll_travelTime.setFocusable(true);
        ll_travelTime.setFocusableInTouchMode(true);
        ll_travelTime.requestFocus();
        ll_travelTime.requestFocusFromTouch();
        String selectCity = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectCity", "");
        if (StringUtils.isEmpty(selectCity) || selectCity.contains(KJActivityStack.create().topActivity().getString(R.string.allAeservationNumber))) {
            ViewInject.toast(KJActivityStack.create().topActivity().getString(R.string.travelCity2));
            finish();
            return;
        }
        mPresenter = new PersonalTailorPresenter(this);
        departureTime();
        travelPreferences();
        recommendRestaurant();
        recommendedAccommodation();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void initWidget() {
        super.initWidget();
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                ((PersonalTailorContract.Presenter) mPresenter).isLogin();
            }
        };
        titlebar.setTitleText(getString(R.string.fillInformation1));
        titlebar.setRightText("");
        titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.kefu));
        titlebar.setDelegate(simpleDelegate);
        showLoadingDialog(getString(R.string.dataLoad));
        ((PersonalTailorContract.Presenter) mPresenter).getDriverPackConfig();
        StringNewUtils.setFilters(et_remark);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_travelTime:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvTime.show(tv_travelTime);
                break;

            case R.id.tv_subtractionAdult:
                subtractionNum(tv_subtractionAdult, tv_adultNum);
                break;
            case R.id.tv_addAdult:
                addNum(tv_subtractionAdult, tv_adultNum);
                break;
            case R.id.tv_subtractionChildren:
                subtractionNum(tv_subtractionChildren, tv_childrenNum);
                break;
            case R.id.tv_addChildren:
                addNum(tv_subtractionChildren, tv_childrenNum);
                break;

            case R.id.tv_travelPreferences:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions.show(tv_travelPreferences);
                break;
            case R.id.tv_recommendRestaurant:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions1.show(tv_recommendRestaurant);
                break;
            case R.id.tv_recommendedAccommodation:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions2.show(tv_recommendedAccommodation);
                break;
            case R.id.tv_subtractionBags:
                subtractionNum(tv_subtractionBags, tv_bags);
                break;
            case R.id.tv_addBags:
                addNum(tv_subtractionBags, tv_bags);
                break;
            case R.id.tv_subtractionBags1:
                subtractionNum(tv_subtractionBags1, tv_bags1);
                break;
            case R.id.tv_addBags1:
                addNum(tv_subtractionBags1, tv_bags1);
                break;
            case R.id.tv_subtractionBags2:
                subtractionNum(tv_subtractionBags2, tv_bags2);
                break;
            case R.id.tv_addBags2:
                addNum(tv_subtractionBags2, tv_bags2);
                break;
            case R.id.tv_subtractionBags3:
                subtractionNum(tv_subtractionBags3, tv_bags3);
                break;
            case R.id.tv_addBags3:
                addNum(tv_subtractionBags3, tv_bags3);
                break;


            case R.id.ll_costsThat:
                costsThatDialog.show();

                break;

            case R.id.ll_compensationChangeBack:
                compensationChangeBackDialog.show();
                break;

            case R.id.tv_submit:
                showLoadingDialog(getString(R.string.submissionLoad));
                tv_submit.setClickable(false);
                ((PersonalTailorContract.Presenter) mPresenter).postPersonalTailor(type, travelTime, et_origin.getText().toString().trim(),
                        et_destination.getText().toString().trim(), et_playNumberDays.getText().toString().trim(), tv_adultNum.getText().toString(), tv_childrenNum.getText().toString(), tv_travelPreferences.getText().toString().trim(), tv_recommendRestaurant.getText().toString().trim(), tv_recommendedAccommodation.getText().toString().trim(),
                        tv_bags.getText().toString(), tv_bags1.getText().toString(), tv_bags2.getText().toString(), tv_bags3.getText().toString(), et_name.getText().toString().trim(), et_contactWay.getText().toString().trim(), et_remark.getText().toString().trim(), et_workNumber.getText().toString().trim());
                break;
        }
    }

    /**
     * @param textView 所要减的控件
     */
    @SuppressWarnings("deprecation")
    private void subtractionNum(TextView textView, TextView textView1) {
        if (StringUtils.toInt(textView1.getText().toString(), 0) - 1 <= 0) {
            textView.setTextColor(aty.getResources().getColor(R.color.hintColors));
            textView.setBackgroundResource(R.drawable.shape_code1);
            textView1.setText("0");
        } else {
            textView.setTextColor(aty.getResources().getColor(R.color.greenColors));
            textView.setBackgroundResource(R.drawable.shape_code);
            textView1.setText(String.valueOf(StringUtils.toInt(textView1.getText().toString(), 0) - 1));
        }
    }

    /**
     * @param textView 所要加的控件
     */
    @SuppressWarnings("deprecation")
    private void addNum(TextView textView, TextView textView1) {
        textView.setTextColor(aty.getResources().getColor(R.color.greenColors));
        textView.setBackgroundResource(R.drawable.shape_code);
        textView1.setText(String.valueOf(StringUtils.toInt(textView1.getText().toString(), 0) + 1));
    }


    @Override
    public void setPresenter(PersonalTailorContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {

        if (flag == 0) {
            tv_submit.setClickable(true);
            AirportDropOffBean airportDropOffBean = (AirportDropOffBean) JsonUtil.getInstance().json2Obj(success, AirportDropOffBean.class);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "chatMessage", 3);
//            Intent intent = new Intent(aty, OverleafActivity.class);
//            intent.putExtra("travelTime", tv_travelTime.getText().toString().trim());
//            intent.putExtra("origin", et_origin.getText().toString().trim());
//            intent.putExtra("destination", et_destination.getText().toString().trim());
//            intent.putExtra("playNumberDays", et_playNumberDays.getText().toString().trim());
//            intent.putExtra("fewAdults", tv_adultNum.getText().toString().trim());
//            intent.putExtra("severalChildren", tv_childrenNum.getText().toString().trim());
//            intent.putExtra("travelPreferences", tv_travelPreferences.getText().toString().trim());
//            intent.putExtra("recommendRestaurant", tv_recommendRestaurant.getText().toString().trim());
//            intent.putExtra("recommendedAccommodation", tv_recommendedAccommodation.getText().toString().trim());
////            intent.putExtra("workNumber", et_workNumber.getText().toString().trim());
////            intent.putExtra("twenty_four", tv_bags3.getText().toString());
////            intent.putExtra("twenty_six", tv_bags2.getText().toString());
//            intent.putExtra("id", airportDropOffBean.getData().getId());
////            intent.putExtra("twenty_eight", tv_bags1.getText().toString());
////            intent.putExtra("thirty", tv_bags.getText().toString());
////            intent.putExtra("name", et_name.getText().toString());
////            intent.putExtra("contactWay", et_contactWay.getText().toString().trim());
////            intent.putExtra("remark", et_remark.getText().toString().trim());
//            intent.putExtra("chatMessage", "3");
//            showActivity(aty, intent);
        } else if (flag == 1) {
            tv_submit.setClickable(true);
            DriverPackConfigBean driverPackConfigBean = (DriverPackConfigBean) JsonUtil.json2Obj(success, DriverPackConfigBean.class);

            trip_chooseList = driverPackConfigBean.getData().getTrip_choose();
            if (trip_chooseList != null && trip_chooseList.size() > 0) {
//                for (int i = 0; i < trip_chooseList.size(); i++) {
//                    trip_chooseList.get(i).setDescription(trip_chooseList.get(i).getName() + "(" + trip_chooseList.get(i).getDescription() + ")");
//                }
                pvOptions.setPicker(trip_chooseList);
            }
            restaurant_chooseList = driverPackConfigBean.getData().getRestaurant_choose();
            if (restaurant_chooseList != null && restaurant_chooseList.size() > 0) {
                pvOptions1.setPicker(restaurant_chooseList);
            }
            sleep_chooseList = driverPackConfigBean.getData().getSleep_choose();
            if (sleep_chooseList != null && sleep_chooseList.size() > 0) {
                pvOptions2.setPicker(sleep_chooseList);
            }

            // ((PersonalTailorContract.Presenter) mPresenter).getUnsubscribeCost(1);
        } else if (flag == 2) {
           // showActivity(aty, OverleafActivity.class);
        } else if (flag == 3) {
            UnsubscribeCostBean unsubscribeCostBean = (UnsubscribeCostBean) JsonUtil.getInstance().json2Obj(success, UnsubscribeCostBean.class);
            compensationChangeBackDialog = new CompensationChangeBackDialog(this, unsubscribeCostBean.getData().getPolicy().getContent());
            compensationChangeBackDialog.setCanceledOnTouchOutside(false);
            costsThatDialog = new CostsThatDialog(this, unsubscribeCostBean.getData().getExplain().getContent());
            costsThatDialog.setCanceledOnTouchOutside(false);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        tv_submit.setClickable(true);
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }


    /**
     * 选择时间的控件
     */
    private void departureTime() {
        //控制时间范围
        boolean[] type = new boolean[]{true, true, true, true, true, false};
        Calendar calendar = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(calendar.get(Calendar.YEAR) - 1, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();
        endDate.set(calendar.get(Calendar.YEAR) + 1, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        pvTime = null;
//        pvTime = new TimePickerView.Builder(aty, new TimePickerView.OnTimeSelectListener() {
//
//            @Override
//            public void onTimeSelect(Date date, View v) {//选中事件回调
//                if (date.getTime() <= System.currentTimeMillis()) {
//                    ViewInject.toast(aty.getString(R.string.greateThanCurrentTime));
//                    return;
//                }
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                travelTime = date.getTime() / 1000;
//                ((TextView) v).setText(format.format(date));
//            }
//        }).setType(type).setRangDate(startDate, endDate).setLabel("", "", "", "", "", "").build();
        pvTime.setDate(calendar);
    }

    /**
     * 出行偏好
     */
    @SuppressWarnings("unchecked")
    private void travelPreferences() {
//        pvOptions = new OptionsPickerView.Builder(aty, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                //  car_type_id = carInfoBean.getData().get(options1).getId();
//                ((TextView) v).setText(trip_chooseList.get(options1).getDescription());
//            }
//        }).build();
    }

    /**
     * 推荐餐馆
     */
    @SuppressWarnings("unchecked")
    private void recommendRestaurant() {
//        pvOptions1 = new OptionsPickerView.Builder(aty, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                //  car_type_id = carInfoBean.getData().get(options1).getId();
//                ((TextView) v).setText(restaurant_chooseList.get(options1).getDescription());
//            }
//        }).build();
    }

    /**
     * 推荐住宿
     */
    @SuppressWarnings("unchecked")
    private void recommendedAccommodation() {
//        pvOptions2 = new OptionsPickerView.Builder(aty, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                //  car_type_id = carInfoBean.getData().get(options1).getId();
//                ((TextView) v).setText(sleep_chooseList.get(options1).getDescription());
//            }
//        }).build();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        pvOptions = null;
        pvOptions1 = null;
        pvOptions2 = null;
        compensationChangeBackDialog = null;
        costsThatDialog = null;
    }
}
