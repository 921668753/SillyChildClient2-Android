package com.sillykid.app.mine.myorder.charterorder.orderdetails;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.charterorder.orderdetails.AirportPickupOrderDetailsBean;
import com.sillykid.app.utils.DataUtil;

/**
 * 接机订单详情
 */
public class AirportPickupOrderDetailsActivity extends BaseActivity implements CharterOrderDetailsContract.View {


    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;


    @BindView(id = R.id.tv_serviceDate)
    private TextView tv_serviceDate;


    @BindView(id = R.id.tv_airportPickupLocation)
    private TextView tv_airportPickupLocation;

    @BindView(id = R.id.tv_flightNumberSign)
    private TextView tv_flightNumberSign;

    @BindView(id = R.id.tv_destination)
    private TextView tv_destination;

    @BindView(id = R.id.tv_passengersNumber)
    private TextView tv_passengersNumber;

    @BindView(id = R.id.tv_bags)
    private TextView tv_bags;

    @BindView(id = R.id.tv_userNote)
    private TextView tv_userNote;

    @BindView(id = R.id.tv_orderPriceNoSign)
    private TextView tv_orderPriceNoSign;


    @BindView(id = R.id.tv_vouchers)
    private TextView tv_vouchers;

    @BindView(id = R.id.tv_actualPayment)
    private TextView tv_actualPayment;

    @BindView(id = R.id.tv_confirmPayment, click = true)
    private TextView tv_confirmPayment;

    private String order_number;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_airportpickuporderdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new CharterOrderDetailsPresenter(this);
        order_number = getIntent().getStringExtra("order_number");
        showLoadingDialog(getString(R.string.dataLoad));
        ((CharterOrderDetailsContract.Presenter) mPresenter).getCharterOrderDetails(order_number);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.airportPickup), true, R.id.titlebar);
    }


    @Override
    public void setPresenter(CharterOrderDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        AirportPickupOrderDetailsBean airportPickupOrderDetailsBean = (AirportPickupOrderDetailsBean) JsonUtil.getInstance().json2Obj(success, AirportPickupOrderDetailsBean.class);
        tv_orderNumber.setText(airportPickupOrderDetailsBean.getData().getOrder_number());
        tv_serviceDate.setText(DataUtil.formatData(StringUtils.toLong(airportPickupOrderDetailsBean.getData().getService_time()), "yyyy-MM-dd HH:mm"));
        tv_airportPickupLocation.setText(airportPickupOrderDetailsBean.getData().getDeparture());
        tv_flightNumberSign.setText(airportPickupOrderDetailsBean.getData().getFlight_number());
        tv_destination.setText(airportPickupOrderDetailsBean.getData().getDelivery_location());
        tv_passengersNumber.setText(airportPickupOrderDetailsBean.getData().getAudit_number() + getString(R.string.adult) + "   " + airportPickupOrderDetailsBean.getData().getChildren_number() + getString(R.string.children));
        tv_bags.setText(airportPickupOrderDetailsBean.getData().getBaggage_number() + getString(R.string.bags2));
        tv_userNote.setText(airportPickupOrderDetailsBean.getData().getRemarks());
        tv_orderPriceNoSign.setText(getString(R.string.renminbi) + airportPickupOrderDetailsBean.getData().getOrder_amount());
        tv_vouchers.setText(getString(R.string.renminbi) + airportPickupOrderDetailsBean.getData().getDis_amount());
        tv_actualPayment.setText(getString(R.string.renminbi) + airportPickupOrderDetailsBean.getData().getPay_amount());
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
    }
}
