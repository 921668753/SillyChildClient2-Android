package com.sillykid.app.homepage.airporttransportation.airportpickup;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.airporttransportation.airportpickup.AirportPickupPayOrderBean;
import com.sillykid.app.utils.GlideImageLoader;

/**
 * 接机---支付订单
 */
public class AirportPickupPayOrderActivity extends BaseActivity implements AirportPickupPayOrderContract.View {

    @BindView(id = R.id.img_airportPickup)
    private ImageView img_airportPickup;

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

    private int requirement_id = 0;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_airportpickuppayorder);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AirportPickupPayOrderPresenter(this);
        requirement_id = getIntent().getIntExtra("requirement_id", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((AirportPickupPayOrderContract.Presenter) mPresenter).getTravelOrderDetail(requirement_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.airportPickup), true, R.id.titlebar);
        GlideImageLoader.glideOrdinaryLoader(aty, getIntent().getStringExtra("picture"), img_airportPickup, R.mipmap.placeholderfigure2);
        tv_airportName.setText(getIntent().getStringExtra("title"));
        tv_travelConfiguration.setText(getIntent().getStringExtra("baggage_passenger"));
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


    @Override
    public void setPresenter(AirportPickupPayOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        AirportPickupPayOrderBean airportPickupPayOrderBean = (AirportPickupPayOrderBean) JsonUtil.getInstance().json2Obj(success, AirportPickupPayOrderBean.class);
        tv_flightNumber.setText(airportPickupPayOrderBean.getData().getFlight_number());
        tv_deliveredSite.setText(airportPickupPayOrderBean.getData().getDelivery_location());
        tv_flightArrivalTime.setText(airportPickupPayOrderBean.getData().getFlight_arrival_time());
        tv_adult.setText(airportPickupPayOrderBean.getData().getAudit_number() + "");
        tv_children.setText(airportPickupPayOrderBean.getData().getChildren_number() + "");
        tv_luggage.setText(airportPickupPayOrderBean.getData().getBaggage_number() + "");
        tv_contact.setText(airportPickupPayOrderBean.getData().getContact());
        tv_contactWay.setText(airportPickupPayOrderBean.getData().getContact_number());

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            finish();
            return;
        }
        ViewInject.toast(msg);
    }
}
