package com.sillykid.app.homepage.chartercustom.singletransport;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.dialog.chartercustom.CompensationChangeBackDialog;
import com.sillykid.app.dialog.chartercustom.CostsThatDialog;
import com.sillykid.app.entity.AirportDropOffBean;
import com.sillykid.app.entity.CarInfoBean;
import com.sillykid.app.entity.CarListBean;
import com.sillykid.app.entity.UnsubscribeCostBean;
import com.sillykid.app.entity.VehicleTypeBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 单次接送
 * Created by Admin on 2017/8/16.
 */

public class SingleTransportActivity extends BaseActivity implements SingleTransportContract.View {


    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;
    /**
     * 用车时间
     */
    @BindView(id = R.id.ll_vehicleTime, click = true)
    LinearLayout ll_vehicleTime;
    @BindView(id = R.id.tv_vehicleTime)
    TextView tv_vehicleTime;

    private long appoint_at = 0;

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
     * 车型
     */
    @BindView(id = R.id.ll_selectModels, click = true)
    LinearLayout ll_selectModels;
    @BindView(id = R.id.tv_selectModels)
    TextView tv_selectModels;
    int car_type_id = 0;

    @BindView(id = R.id.img_arrow)
    ImageView img_arrow;

    /**
     * 出发城市
     */

    @BindView(id = R.id.et_whereDoYouStart)
    EditText et_whereDoYouStart;

    /**
     * 到达城市
     */

    @BindView(id = R.id.et_whereAreGoing)
    EditText et_whereAreGoing;

    /**
     * 司导工号
     */
    @BindView(id = R.id.et_workNumber)
    EditText et_workNumber;

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


    private String type = "once_pickup";
    private TimePickerView pvTime = null;
    private OptionsPickerView pvOptions = null;

    private String con_car_seat_num;
    private CompensationChangeBackDialog compensationChangeBackDialog;
    private CostsThatDialog costsThatDialog;
    private CarListBean carListBean;


    private OptionsPickerView modelsOptions;
    private ArrayList<CarInfoBean.ResultBean> options1Items;
    private List<VehicleTypeBean.ResultBean.LevelListBean> options2Items;


    private String car_level = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_singletransport);
    }

    @Override
    public void initData() {
        super.initData();
        String selectCity = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectCity", "");
        if (StringUtils.isEmpty(selectCity) || selectCity.contains(KJActivityStack.create().topActivity().getString(R.string.allAeservationNumber))) {
            ViewInject.toast(KJActivityStack.create().topActivity().getString(R.string.travelCity2));
            finish();
            return;
        }
        mPresenter = new SingleTransportPresenter(this);
        selectNewModels();
        showLoadingDialog(aty.getString(R.string.dataLoad));
        ((SingleTransportContract.Presenter) mPresenter).getCarWhere();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void initWidget() {
        super.initWidget();
        img_arrow.setVisibility(View.VISIBLE);
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                ((SingleTransportContract.Presenter) mPresenter).isLogin();
            }
        };
        titlebar.setTitleText(getString(R.string.singleTransport));
        titlebar.setRightText("");
        titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.kefu));
        titlebar.setDelegate(simpleDelegate);
        //     selectModels();
        departureTime();
      //  StringNewUtils.setFilters1(et_remark);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_vehicleTime:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvTime.show(tv_vehicleTime);
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
            case R.id.ll_selectModels:
                SoftKeyboardUtils.packUpKeyboard(this);
                //  pvOptions.show(tv_selectModels);
                modelsOptions.show(tv_selectModels);
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
                ((SingleTransportContract.Presenter) mPresenter).postSingleTransport(type, appoint_at, tv_adultNum.getText().toString().trim(),
                        tv_childrenNum.getText().toString().trim(), car_type_id, et_whereDoYouStart.getText().toString().trim(), et_whereAreGoing.getText().toString().trim(),
                        et_workNumber.getText().toString().trim(), tv_bags.getText().toString(), tv_bags1.getText().toString(), tv_bags2.getText().toString(), tv_bags3.getText().toString(),
                        et_name.getText().toString().trim(), et_contactWay.getText().toString().trim(), et_remark.getText().toString().trim(), con_car_seat_num, car_level);// tv_vehicleTime.getText().toString()
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
//                appoint_at = date.getTime() / 1000;
//                ((TextView) v).setText(format.format(date));
//            }
//        }).setType(type).setRangDate(startDate, endDate).setLabel("", "", "", "", "", "").build();
        pvTime.setDate(calendar);
    }


    /**
     * 选择车型
     */
    @SuppressWarnings("unchecked")
    private void selectModels() {
//        pvOptions = new OptionsPickerView.Builder(aty, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                car_type_id = carListBean.getData().getList().get(options1).getCar_type_id();
//                con_car_seat_num = carListBean.getData().getList().get(options1).getSeat_num() + "";
//                ((TextView) v).setText(carListBean.getData().getList().get(options1).getBrand_name());
//            }
//        }).build();
    }


    /**
     * 选择车型
     */

    public void selectNewModels() {
        options1Items = new ArrayList<>();
//        modelsOptions = new OptionsPickerView.Builder(aty, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                ((TextView) v).setText(options1Items.get(options1).getSite_num() + options2Items.get(option2).getName());
//                con_car_seat_num = String.valueOf(options1Items.get(options1).getId());
//                car_level = String.valueOf(options2Items.get(option2).getId());
//            }
//        }).build();
    }

    @Override
    public void setPresenter(SingleTransportContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            tv_submit.setClickable(true);
            AirportDropOffBean airportDropOffBean = (AirportDropOffBean) JsonUtil.getInstance().json2Obj(success, AirportDropOffBean.class);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "chatMessage", 2);
//            Intent intent = new Intent(aty, OverleafActivity.class);
//            intent.putExtra("vehicleTime", tv_vehicleTime.getText().toString().trim());
//            intent.putExtra("fewAdults", tv_adultNum.getText().toString().trim());
//            intent.putExtra("severalChildren", tv_childrenNum.getText().toString().trim());
//            intent.putExtra("selectModels", tv_selectModels.getText().toString().trim());
//            intent.putExtra("whereDoYouStart", et_whereDoYouStart.getText().toString().trim());
//            intent.putExtra("whereAreGoing", et_whereAreGoing.getText().toString().trim());
//            intent.putExtra("workNumber", et_workNumber.getText().toString().trim());
//            intent.putExtra("id", airportDropOffBean.getData().getId());
//            intent.putExtra("twenty_four", tv_bags3.getText().toString());
//            intent.putExtra("twenty_six", tv_bags2.getText().toString());
//            intent.putExtra("twenty_eight", tv_bags1.getText().toString());
//            intent.putExtra("thirty", tv_bags.getText().toString());
//            intent.putExtra("name", et_name.getText().toString());
//            intent.putExtra("contactWay", et_contactWay.getText().toString().trim());
//            intent.putExtra("remark", et_remark.getText().toString().trim());
//            intent.putExtra("chatMessage", "2");
//            showActivity(aty, intent);
        } else if (flag == 1) {
            tv_submit.setClickable(true);
            carListBean = (CarListBean) JsonUtil.json2Obj(success, CarListBean.class);
            if (carListBean.getData().getList() != null && carListBean.getData().getList().size() > 0) {
                List<CarListBean.ResultBean.ListBean> list = carListBean.getData().getList();
                for (int i = 0; i < list.size(); i++) {
                    int seatNum = list.get(i).getSeat_num();
                    String name = list.get(i).getBrand_name() + list.get(i).getCar_type_name();
                    if (StringUtils.isEmpty(name)) {
                        list.get(i).setBrand_name(seatNum + getString(R.string.peopleseat));
                    } else {
                        list.get(i).setBrand_name(seatNum + getString(R.string.peopleseat) + "(" + name + ")");
                    }
                }
                pvOptions.setPicker(list);
                //   ((SingleTransportContract.Presenter) mPresenter).getUnsubscribeCost(0);
            }
        } else if (flag == 2) {
           // showActivity(aty, OverleafActivity.class);
        } else if (flag == 3) {
            UnsubscribeCostBean unsubscribeCostBean = (UnsubscribeCostBean) JsonUtil.getInstance().json2Obj(success, UnsubscribeCostBean.class);
            compensationChangeBackDialog = new CompensationChangeBackDialog(this, unsubscribeCostBean.getData().getPolicy().getContent());
            compensationChangeBackDialog.setCanceledOnTouchOutside(false);
            costsThatDialog = new CostsThatDialog(this, unsubscribeCostBean.getData().getExplain().getContent());
            costsThatDialog.setCanceledOnTouchOutside(false);
        } else if (flag == 4) {
            VehicleTypeBean vehicleTypeBean = (VehicleTypeBean) JsonUtil.json2Obj(success, VehicleTypeBean.class);
            if (vehicleTypeBean.getData().getSeat_list() != null && vehicleTypeBean.getData().getSeat_list().size() > 0) {
                List<Integer> list = vehicleTypeBean.getData().getSeat_list();
                for (int i = 0; i < list.size(); i++) {
                    int seatNum = list.get(i);
                    CarInfoBean.ResultBean resultBean = new CarInfoBean.ResultBean();
                    resultBean.setId(seatNum);
                    resultBean.setSite_num(seatNum + getString(R.string.peopleseat));
                    options1Items.add(resultBean);
                }
            } else {
                options1Items = null;
            }
            if (vehicleTypeBean.getData().getLevel_list() != null && vehicleTypeBean.getData().getLevel_list().size() > 0) {
                options2Items = vehicleTypeBean.getData().getLevel_list();
            } else {
                options2Items = null;
            }
            modelsOptions.setNPicker(options1Items, options2Items, null);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        pvTime = null;
        pvOptions = null;
        compensationChangeBackDialog = null;
        costsThatDialog = null;
    }
}
