package com.sillykid.app.mine.myorder.charterorder.orderdetails;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.charterorder.orderdetails.AirportDropOffOrderDetailsBean;
import com.sillykid.app.utils.DataUtil;

/**
 * 送机机订单详情
 */
public class AirportDropOffOrderDetailsActivity extends BaseActivity implements CharterOrderDetailsContract.View {


    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;

    @BindView(id = R.id.tv_serviceDate)
    private TextView tv_serviceDate;

    @BindView(id = R.id.tv_placeDeparture)
    private TextView tv_placeDeparture;

    @BindView(id = R.id.tv_deliveredAirport)
    private TextView tv_deliveredAirport;

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
        setContentView(R.layout.activity_airportdropofforderdetails);
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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.airportDropOff), true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_confirmPayment:


                break;
        }
    }


    @Override
    public void setPresenter(CharterOrderDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        AirportDropOffOrderDetailsBean airportDropOffOrderDetailsBean = (AirportDropOffOrderDetailsBean) JsonUtil.getInstance().json2Obj(success, AirportDropOffOrderDetailsBean.class);
        tv_orderNumber.setText(airportDropOffOrderDetailsBean.getData().getOrder_number());
        tv_serviceDate.setText(DataUtil.formatData(StringUtils.toLong(airportDropOffOrderDetailsBean.getData().getService_time()), "yyyy-MM-dd HH:mm"));
        tv_placeDeparture.setText(airportDropOffOrderDetailsBean.getData().getDelivery_location());
        tv_deliveredAirport.setText(airportDropOffOrderDetailsBean.getData().getDeparture());
        tv_passengersNumber.setText(airportDropOffOrderDetailsBean.getData().getAudit_number() + getString(R.string.adult) + "   " + airportDropOffOrderDetailsBean.getData().getChildren_number() + getString(R.string.children));
        tv_bags.setText(airportDropOffOrderDetailsBean.getData().getBaggage_number() + getString(R.string.bags2));
        tv_userNote.setText(airportDropOffOrderDetailsBean.getData().getRemarks());
        tv_orderPriceNoSign.setText(getString(R.string.renminbi) + airportDropOffOrderDetailsBean.getData().getOrder_amount());
        tv_vouchers.setText(getString(R.string.renminbi) + airportDropOffOrderDetailsBean.getData().getDis_amount());
        tv_actualPayment.setText(getString(R.string.renminbi) + airportDropOffOrderDetailsBean.getData().getPay_amount());
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
    }
}
