package com.sillykid.app.homepage.airporttransportation.airportdropoff;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;


/**
 * 送机
 **/
public class AirportDropOffActivity extends BaseActivity {

    @BindView(id = R.id.tv_selectProduct, click = true)
    private TextView tv_selectProduct;

    @BindView(id = R.id.tv_travelConfiguration, click = true)
    private TextView tv_travelConfiguration;

    @BindView(id = R.id.et_deliveredAirport)
    private EditText et_deliveredAirport;

    @BindView(id = R.id.et_placeDeparture)
    private EditText et_placeDeparture;

    @BindView(id = R.id.et_timeDeparture)
    private EditText et_timeDeparture;

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
        setContentView(R.layout.activity_airportdropoff);
    }

    @Override
    public void initData() {
        super.initData();
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.airportDropOff), true, R.id.titlebar);
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
