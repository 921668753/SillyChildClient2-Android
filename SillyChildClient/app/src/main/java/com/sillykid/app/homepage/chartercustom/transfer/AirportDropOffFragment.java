package com.sillykid.app.homepage.chartercustom.transfer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.homepage.airporttransportation.dialog.CompensationChangeBackDialog;
import com.sillykid.app.dialog.chartercustom.CostsThatDialog;
import com.sillykid.app.entity.AirportDropOffBean;
import com.sillykid.app.entity.CarBrandBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myorder.charterorder.orderdetails.CharterOrderDetailsActivity;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.util.Calendar;
import java.util.List;

/**
 * 送机
 * Created by Admin on 2017/8/16.
 */

public class AirportDropOffFragment extends BaseFragment implements AirportDropOffContract.View {

    /**
     * 航班号
     */
    @BindView(id = R.id.et_flightNumber)
    EditText et_flightNumber;

    /**
     * 机场名称
     */
    @BindView(id = R.id.et_deliveredAirport)
    EditText et_deliveredAirport;

    /**
     * 出发地点
     */


    @BindView(id = R.id.et_placeDeparture)
    EditText et_placeDeparture;

    /**
     * 选择车型
     */
    @BindView(id = R.id.ll_selectModels, click = true)
    LinearLayout ll_selectModels;
    @BindView(id = R.id.tv_selectModels)
    TextView tv_selectModels;

    private String selectModels = "";

    private String con_car_seat_num;
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
     * 预计出发时间
     */
    @BindView(id = R.id.tv_departureTime, click = true)
    TextView tv_departureTime;

    private long appoint_at = 0;


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

    @BindView(id = R.id.tv_compensationChangeBack)
    TextView tv_compensationChangeBack;

    private String type = "send_airport";

    private TransferActivity aty;
    private TimePickerView pvTime = null;
    private OptionsPickerView pvOptions = null;
    private CarBrandBean carBrandBean;
    private CompensationChangeBackDialog compensationChangeBackDialog;
    private CostsThatDialog costsThatDialog;
    private String carTypeName;
    private int pcpid = 0;
    private String flyName;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (TransferActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_airportdropoff, null);
    }

    @Override
    protected void initData() {
        super.initData();
//        String selectCity = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectCity", "");
//        if (StringUtils.isEmpty(selectCity) || selectCity.contains(KJActivityStack.create().topActivity().getString(R.string.locateFailure))) {
//            ViewInject.toast(KJActivityStack.create().topActivity().getString(R.string.locateFailure) + KJActivityStack.create().topActivity().getString(R.string.travelCity1), 0);
//            aty.finish();
//            return;
//        }
        String costStatement = aty.getIntent().getStringExtra("costStatement");
        String costCompensation = aty.getIntent().getStringExtra("costCompensation");
        String costCompensationLevel = aty.getIntent().getStringExtra("costCompensationLevel");
        tv_compensationChangeBack.setText(costCompensationLevel);
     //   compensationChangeBackDialog = new CompensationChangeBackDialog(aty, costCompensation);
        compensationChangeBackDialog.setCanceledOnTouchOutside(false);
        costsThatDialog = new CostsThatDialog(aty, costStatement);
        costsThatDialog.setCanceledOnTouchOutside(false);
        mPresenter = new AirportDropOffPresenter(this);
        pcpid = aty.getIntent().getIntExtra("pcpId", 0);
        selectModels = aty.getIntent().getStringExtra("carTypeId");
        carTypeName = aty.getIntent().getStringExtra("carTypeName");
        con_car_seat_num = aty.getIntent().getStringExtra("carSeatTotal");
        flyName = aty.getIntent().getStringExtra("flyName");
        if (!StringUtils.isEmpty(flyName)) {
            et_deliveredAirport.setText(flyName);
            et_deliveredAirport.setEnabled(false);
        }
//        showLoadingDialog(aty.getString(R.string.dataLoad));
//        ((AirportDropOffContract.Presenter) mPresenter).getCarBrand();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        departureTime();
        tv_selectModels.setText(con_car_seat_num + getString(R.string.peopleseat) + carTypeName);
        //  selectModels();
        //   StringNewUtils.setFilters(et_remark);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
//            case R.id.ll_selectModels:
//                SoftKeyboardUtils.packUpKeyboard(aty);
//                pvOptions.show(tv_selectModels);
//                break;
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

            case R.id.tv_departureTime:
                SoftKeyboardUtils.packUpKeyboard(aty);
                pvTime.show(tv_departureTime);
                break;


            case R.id.ll_costsThat:
                costsThatDialog.show();
                break;

            case R.id.ll_compensationChangeBack:
                compensationChangeBackDialog.show();
                break;


            case R.id.tv_submit:
                showLoadingDialog(aty.getString(R.string.submissionLoad));
                ((AirportDropOffContract.Presenter) mPresenter).postAirportDropOff(type, et_flightNumber.getText().toString().trim(), et_deliveredAirport.getText().toString().trim(),
                        et_placeDeparture.getText().toString().trim(), selectModels, et_workNumber.getText().toString().trim(),
                        tv_bags.getText().toString().trim(), tv_bags1.getText().toString().trim(), tv_bags2.getText().toString().trim(), tv_bags3.getText().toString().trim(),
                        et_name.getText().toString().trim(), et_contactWay.getText().toString().trim(),
                        tv_adultNum.getText().toString(), tv_childrenNum.getText().toString(), appoint_at, et_remark.getText().toString().trim(), con_car_seat_num, pcpid);// tv_departureTime.getText().toString()
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
        //TimePicker
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


    @Override
    public void setPresenter(AirportDropOffContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            AirportDropOffBean airportDropOffBean = (AirportDropOffBean) JsonUtil.getInstance().json2Obj(success, AirportDropOffBean.class);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "chatMessage", 1);
//            Intent intent = new Intent(aty, OverleafActivity.class);
//            intent.putExtra("flightNumber", et_flightNumber.getText().toString().trim());
//            intent.putExtra("pickUpAtAirport", et_deliveredAirport.getText().toString().trim());
//            intent.putExtra("deliveredSite", et_placeDeparture.getText().toString().trim());
//            intent.putExtra("selectModels", tv_selectModels.getText().toString().trim());
//            intent.putExtra("workNumber", et_workNumber.getText().toString().trim());
//            intent.putExtra("name", et_name.getText().toString().trim());
//            intent.putExtra("twenty_four", tv_bags3.getText().toString());
//            intent.putExtra("twenty_six", tv_bags2.getText().toString());
//            intent.putExtra("twenty_eight", tv_bags1.getText().toString());
//            intent.putExtra("thirty", tv_bags.getText().toString());
//            intent.putExtra("contactWay", et_contactWay.getText().toString().trim());
//            intent.putExtra("fewAdults", tv_adultNum.getText().toString());
//            intent.putExtra("severalChildren", tv_childrenNum.getText().toString());
//            intent.putExtra("departureTime", tv_departureTime.getText().toString().trim());
//            intent.putExtra("id", airportDropOffBean.getData().getId());
//            intent.putExtra("remark", et_remark.getText().toString().trim());
//            intent.putExtra("chatMessage", "1");

            Intent intent = new Intent(aty, CharterOrderDetailsActivity.class);
            // intent.putExtra("paymoney", tv_price.getText().toString());
            intent.putExtra("airid", airportDropOffBean.getData().getId());
            aty.skipActivity(aty, intent);
        } else if (flag == 1) {
            carBrandBean = (CarBrandBean) JsonUtil.json2Obj(success, CarBrandBean.class);
            if (carBrandBean.getData().getList() != null && carBrandBean.getData().getList().size() > 0) {
                List<CarBrandBean.ResultBean.ListBean> list = carBrandBean.getData().getList();
                for (int i = 0; i < list.size(); i++) {
                    String seatNum = list.get(i).getSeatNum();
                    String name = list.get(i).getName();
                    if (StringUtils.isEmpty(name)) {
                        list.get(i).setName(seatNum + getString(R.string.peopleseat));
                    } else {
                        list.get(i).setName(seatNum + getString(R.string.peopleseat) + "(" + name + ")");
                    }
                }
                pvOptions.setPicker(list);
            }
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            aty.showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }


    /**
     * 判断政策
     */
//    public String changeBack(String compensationChangeBack) {
//        String policy = "";
//        if (compensationChangeBack != null && compensationChangeBack.startsWith("cover_img_k")) {
//            policy = getString(R.string.looseFitting);
//        } else if (compensationChangeBack != null && compensationChangeBack.startsWith("cover_img_y")) {
//            policy = getString(R.string.rigor);
//        } else if (compensationChangeBack != null && compensationChangeBack.startsWith("cover_img_z")) {
//            policy = getString(R.string.middling);
//        } else {
//            policy = getString(R.string.unsubscribe);
//        }
//        return policy;
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        pvTime = null;
        pvOptions = null;
        compensationChangeBackDialog = null;
        costsThatDialog = null;
    }
}
