package com.sillykid.app.homepage.bythedaycharter;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
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
import com.sillykid.app.utils.custompicker.bean.DayTimeEntity;

import java.util.ArrayList;
import java.util.List;

import static com.sillykid.app.constant.NumericConstants.RESULT_CODE_GET;

/**
 * 按天包车
 */
public class ByTheDayCharterActivity extends BaseActivity implements ByTheDayCharterContract.View {

    @BindView(id = R.id.img_byTheDayCharter)
    private ImageView img_byTheDayCharter;

    @BindView(id = R.id.tv_travelTime, click = true)
    private TextView tv_travelTime;
    private long travel_start_time = 0;
    private long travel_end_time = 0;

    @BindView(id = R.id.et_journeyBegins)
    private EditText et_journeyBegins;

    @BindView(id = R.id.et_journeyEnds)
    private EditText et_journeyEnds;

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
    private long day = 0;
    private DayTimeEntity startDayBean;
    private DayTimeEntity stopDayBean;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_bythedaycharter);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new ByTheDayCharterPresenter(this);
        baggage_number = getIntent().getIntExtra("baggage_number", 0);
        passenger_number = getIntent().getIntExtra("passenger_number", 0);
        product_id = getIntent().getIntExtra("product_id", 0);
        initOptions(passenger_number, baggage_number);
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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.fillYourInformation), true, R.id.titlebar);
        GlideImageLoader.glideOrdinaryLoader(aty, getIntent().getStringExtra("picture"), img_byTheDayCharter, R.mipmap.placeholderfigure2);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_travelTime:
                Intent intent = new Intent(aty, SelectDateActivity.class);
                intent.putExtra("startDayBean", startDayBean);
                intent.putExtra("stopDayBean", stopDayBean);
                showActivityForResult(aty, intent, RESULT_CODE_GET);
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
                ((ByTheDayCharterContract.Presenter) mPresenter).postAddCarRequirements(product_id, travel_start_time, travel_end_time, day, et_journeyBegins.getText().toString().trim(),
                        et_journeyEnds.getText().toString().trim(), et_contact.getText().toString().trim(),
                        et_contactWay.getText().toString().trim(), String.valueOf(adult_number), String.valueOf(children_number),
                        String.valueOf(baggage_number1), et_remark.getText().toString().trim(), passenger_number, baggage_number);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CODE_GET && resultCode == RESULT_OK) {
            String startDay = data.getStringExtra("startDay");
            String stopDay = data.getStringExtra("stopDay");
            startDayBean = (DayTimeEntity) data.getSerializableExtra("startDayBean");
            stopDayBean = (DayTimeEntity) data.getSerializableExtra("stopDayBean");
            travel_start_time = DataUtil.getStringToDate(startDay + "00" + getString(R.string.hour) + "00" + getString(R.string.minute) + "00" + getString(R.string.seconds), "yyyy" + getString(R.string.year) + "MM" + getString(R.string.month) + "dd" + getString(R.string.day)
                    + "HH" + getString(R.string.hour) + "mm" + getString(R.string.minute) + "ss" + getString(R.string.seconds)) / 1000;
            travel_end_time = DataUtil.getStringToDate(stopDay + "00" + getString(R.string.hour) + "00" + getString(R.string.minute) + "00" + getString(R.string.seconds), "yyyy" + getString(R.string.year) + "MM" + getString(R.string.month) + "dd" + getString(R.string.day)
                    + "HH" + getString(R.string.hour) + "mm" + getString(R.string.minute) + "ss" + getString(R.string.seconds)) / 1000;
            day = (travel_end_time - travel_start_time) / 24 / 3600 + 1;
            tv_travelTime.setText(startDay + "-" + stopDay + "(" + getString(R.string.general) + day + getString(R.string.day) + ")");
        }
    }


    @Override
    public void setPresenter(ByTheDayCharterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        AirportPickupBean airportPickupBean = (AirportPickupBean) JsonUtil.getInstance().json2Obj(success, AirportPickupBean.class);
        Intent intent = new Intent(aty, ByTheDayCharterPayOrderActivity.class);
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

}
