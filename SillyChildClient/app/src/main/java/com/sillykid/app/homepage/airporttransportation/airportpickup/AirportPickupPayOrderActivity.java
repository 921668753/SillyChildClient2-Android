package com.sillykid.app.homepage.airporttransportation.airportpickup;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;

/**
 * 接机---支付订单
 */
public class AirportPickupPayOrderActivity extends BaseActivity {

    @BindView(id = R.id.tv_airportName)
    private TextView tv_airportName;

    @BindView(id = R.id.tv_travelConfiguration)
    private TextView tv_travelConfiguration;

    @BindView(id = R.id.tv_flightNumber)
    private TextView tv_flightNumber;

    @BindView(id = R.id.tv_deliveredSite)
    private TextView tv_deliveredSite;

    @BindView(id = R.id.tv_flightArrivalTime)
    private TextView tv_flightArrivalTime;

    @BindView(id = R.id.tv_adult)
    private TextView tv_adult;

    @BindView(id = R.id.tv_children)
    private TextView tv_children;

    @BindView(id = R.id.tv_luggage)
    private TextView tv_luggage;

    @BindView(id = R.id.tv_contact)
    private TextView tv_contact;

    @BindView(id = R.id.tv_contactWay)
    private TextView tv_contactWay;

    @BindView(id = R.id.tv_priceDescription)
    private TextView tv_priceDescription;

    @BindView(id = R.id.tv_dueThat)
    private TextView tv_dueThat;

    @BindView(id = R.id.tv_remark)
    private TextView tv_remark;

    @BindView(id = R.id.tv_orderMoney)
    private TextView tv_orderMoney;

    @BindView(id = R.id.tv_vouchers, click = true)
    private TextView tv_vouchers;

    @BindView(id = R.id.tv_actualPayment)
    private TextView tv_actualPayment;

    @BindView(id = R.id.tv_confirmPayment, click = true)
    private TextView tv_confirmPayment;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_airportpickuppayorder);
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
            case R.id.tv_vouchers:

                break;
            case R.id.tv_confirmPayment:
                break;
        }
    }


}
