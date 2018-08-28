package com.sillykid.app.homepage.airporttransportation.airportpickup;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.airporttransportation.airportpickup.AirportPickupBean;
import com.sillykid.app.entity.homepage.airporttransportation.airportpickup.PeopleBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.DataUtil;
import com.sillykid.app.utils.GlideImageLoader;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 接机---订单信息
 */
public class AirportPickupActivity extends BaseActivity implements AirportPickupContract.View {

    @BindView(id = R.id.img_airportPickup)
    private ImageView img_airportPickup;

    @BindView(id = R.id.tv_selectProduct)
    private TextView tv_selectProduct;

    @BindView(id = R.id.tv_travelConfiguration)
    private TextView tv_travelConfiguration;

    @BindView(id = R.id.et_flightNumber)
    private EditText et_flightNumber;

    @BindView(id = R.id.et_deliveredSite)
    private EditText et_deliveredSite;

    @BindView(id = R.id.tv_flightArrivalTime, click = true)
    private TextView tv_flightArrivalTime;
    private TimePickerView pvCustomTime = null;
    private long timeDeparture = 0;

    @BindView(id = R.id.et_contact)
    private EditText et_contact;

    @BindView(id = R.id.et_contactWay)
    private EditText et_contactWay;

    @BindView(id = R.id.ll_adult, click = true)
    private LinearLayout ll_adult;
    @BindView(id = R.id.tv_adult)
    private TextView tv_adult;
    private OptionsPickerView adultOptions = null;

    @BindView(id = R.id.ll_children, click = true)
    private LinearLayout ll_children;
    @BindView(id = R.id.tv_children)
    private TextView tv_children;
    private OptionsPickerView childrenOptions = null;

    @BindView(id = R.id.ll_luggage, click = true)
    private LinearLayout ll_luggage;
    @BindView(id = R.id.tv_luggage)
    private TextView tv_luggage;
    private OptionsPickerView luggageOptions = null;


    @BindView(id = R.id.et_remark)
    private EditText et_remark;

    @BindView(id = R.id.tv_submitOrder, click = true)
    private TextView tv_submitOrder;
    private int baggage_number = 0;
    private int passenger_number = 0;
    private int baggage_number1 = 0;
    private int adult_number = 0;
    private int children_number = 0;
    private int product_id = 0;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_airportpickup);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new AirportPickupPresenter(this);
        baggage_number = getIntent().getIntExtra("baggage_number", 0);
        passenger_number = getIntent().getIntExtra("passenger_number", 0);
        product_id = getIntent().getIntExtra("product_id", 0);
        initCustomTimePicker();
        initOptions(passenger_number, baggage_number);
    }

    /**
     * 选择时间的控件
     */
    private void initCustomTimePicker() {

        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar birthdaycalendar = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(birthdaycalendar.get(Calendar.YEAR), birthdaycalendar.get(Calendar.MONTH), birthdaycalendar.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();
        endDate.set(birthdaycalendar.get(Calendar.YEAR) + 1, birthdaycalendar.get(Calendar.MONTH), birthdaycalendar.get(Calendar.DAY_OF_MONTH));
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                timeDeparture = date.getTime() / 1000;
                ((TextView) v).setText(DataUtil.formatData(date.getTime() / 1000, "yyyy-MM-dd HH:mm"));
            }
        })
                .setRangDate(startDate, endDate)
                .setContentTextSize(18)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel(getString(R.string.year), getString(R.string.month), getString(R.string.day), getString(R.string.hour), getString(R.string.minute), getString(R.string.seconds))
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFFd5d5d5)
                .build();
    }


    private void initOptions(int passenger_number, int baggage_number) {
        List<PeopleBean> list = new ArrayList<PeopleBean>();
        for (int i = 0; i < passenger_number; i++) {
            PeopleBean peopleBean = new PeopleBean();
            peopleBean.setNum(i + 1);
            peopleBean.setName(i + 1 + getString(R.string.people));
            list.add(peopleBean);
        }
        adultOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                adult_number = list.get(options1).getNum();
                ((TextView) v).setText(list.get(options1).getPickerViewText());
            }
        }).build();
        adultOptions.setPicker(list);
        childrenOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                children_number = list.get(options1).getNum();
                ((TextView) v).setText(list.get(options1).getPickerViewText());
            }
        }).build();
        childrenOptions.setPicker(list);
        List<PeopleBean> list1 = new ArrayList<PeopleBean>();
        for (int i = 0; i < baggage_number; i++) {
            PeopleBean peopleBean = new PeopleBean();
            peopleBean.setNum(i + 1);
            peopleBean.setName(i + 1 + getString(R.string.jian));
            list1.add(peopleBean);
        }
        luggageOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                baggage_number1 = list1.get(options1).getNum();
                ((TextView) v).setText(list1.get(options1).getPickerViewText());
            }
        }).build();
        luggageOptions.setPicker(list1);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.airportPickup), true, R.id.titlebar);
        GlideImageLoader.glideOrdinaryLoader(aty, getIntent().getStringExtra("picture"), img_airportPickup, R.mipmap.placeholderfigure2);
        tv_selectProduct.setText(getIntent().getStringExtra("title"));
        tv_travelConfiguration.setText(getString(R.string.pickUpNumber) + "≤" + passenger_number + "  " + getString(R.string.baggageNumber1) + "≤" + baggage_number);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_flightArrivalTime:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvCustomTime.show(tv_flightArrivalTime);
                break;
            case R.id.ll_adult:
                SoftKeyboardUtils.packUpKeyboard(this);
                adultOptions.show(tv_adult);
                break;
            case R.id.ll_children:
                SoftKeyboardUtils.packUpKeyboard(this);
                childrenOptions.show(tv_children);
                break;
            case R.id.ll_luggage:
                SoftKeyboardUtils.packUpKeyboard(this);
                luggageOptions.show(tv_luggage);
                break;
            case R.id.tv_submitOrder:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((AirportPickupContract.Presenter) mPresenter).postAddRequirements(product_id, et_flightNumber.getText().toString().trim(), et_deliveredSite.getText().toString().trim(),
                        String.valueOf(timeDeparture), et_contact.getText().toString().trim(), et_contactWay.getText().toString().trim(), String.valueOf(adult_number), String.valueOf(children_number),
                        String.valueOf(baggage_number1), et_remark.getText().toString().trim(), passenger_number, baggage_number);
                break;
        }
    }


    @Override
    public void setPresenter(AirportPickupContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        AirportPickupBean airportPickupBean = (AirportPickupBean) JsonUtil.getInstance().json2Obj(success, AirportPickupBean.class);
        Intent intent = new Intent(aty, AirportPickupPayOrderActivity.class);
        intent.putExtra("requirement_id", airportPickupBean.getData().getRequirement_id());
        intent.putExtra("baggage_passenger", getString(R.string.pickUpNumber) + "≤" + passenger_number + "  " + getString(R.string.baggageNumber1) + "≤" + baggage_number);
        intent.putExtra("title", getIntent().getStringExtra("title"));
        intent.putExtra("picture", getIntent().getStringExtra("picture"));
        showActivity(aty, intent);
    }

    @Override
    public void errorMsg(String msg, int flag) {
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
        pvCustomTime = null;
        adultOptions = null;
        childrenOptions = null;
        luggageOptions = null;
    }
}
