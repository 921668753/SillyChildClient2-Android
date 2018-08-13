package com.sillykid.app.homepage.airporttransportation.airportpickup;


import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;

/**
 * 接机---订单信息
 */
public class AirportPickupActivity extends BaseActivity {

    @BindView(id = R.id.tv_selectProduct, click = true)
    private TextView tv_selectProduct;

    @BindView(id = R.id.tv_travelConfiguration, click = true)
    private TextView tv_travelConfiguration;

    @BindView(id = R.id.et_flightNumber)
    private EditText et_flightNumber;

    @BindView(id = R.id.et_deliveredSite)
    private EditText et_deliveredSite;

    @BindView(id = R.id.et_flightArrivalTime)
    private EditText et_flightArrivalTime;

    @BindView(id = R.id.et_contact)
    private EditText et_contact;

    @BindView(id = R.id.et_contactWay)
    private EditText et_contactWay;

    @BindView(id = R.id.et_adult)
    private EditText et_adult;

    @BindView(id = R.id.et_children)
    private EditText et_children;

    @BindView(id = R.id.et_luggage)
    private EditText et_luggage;

    @BindView(id = R.id.et_remark)
    private EditText et_remark;

    @BindView(id = R.id.tv_submitOrder, click = true)
    private TextView tv_submitOrder;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_airportpickup);
    }


    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.airportPickup), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_selectProduct:
            case R.id.tv_travelConfiguration:
                break;
            case R.id.tv_submitOrder:

                break;
        }
    }




}
