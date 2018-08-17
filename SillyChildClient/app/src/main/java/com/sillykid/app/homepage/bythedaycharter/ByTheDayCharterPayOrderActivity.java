package com.sillykid.app.homepage.bythedaycharter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.sillykid.app.R;

/**
 * 按天包车支付订单
 */
public class ByTheDayCharterPayOrderActivity extends BaseActivity {

    @BindView(id = R.id.img_byTheDayCharter)
    private ImageView img_byTheDayCharter;

    @BindView(id = R.id.tv_tourSpots)
    private TextView tv_tourSpots;

    @BindView(id = R.id.tv_tourSpots1)
    private TextView tv_tourSpots1;

    @BindView(id = R.id.tv_priceDescription)
    private TextView tv_priceDescription;

    @BindView(id = R.id.tv_dueThat)
    private TextView tv_dueThat;

    @BindView(id = R.id.tv_adult)
    private TextView tv_adult;

    @BindView(id = R.id.tv_children)
    private TextView tv_children;

    @BindView(id = R.id.tv_luggage)
    private TextView tv_luggage;

    @BindView(id = R.id.tv_travelTime)
    private TextView tv_travelTime;

    @BindView(id = R.id.tv_contact)
    private TextView tv_contact;

    @BindView(id = R.id.tv_contactWay)
    private TextView tv_contactWay;

    @BindView(id = R.id.tv_remark)
    private TextView tv_remark;

    @BindView(id = R.id.tv_orderPriceNoSign)
    private TextView tv_orderPriceNoSign;

    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;

    @BindView(id = R.id.tv_totalOrder)
    private TextView tv_totalOrder;

    @BindView(id = R.id.tv_vouchers, click = true)
    private TextView tv_vouchers;

    @BindView(id = R.id.tv_actualPayment)
    private TextView tv_actualPayment;

    @BindView(id = R.id.tv_confirmPayment, click = true)
    private TextView tv_confirmPayment;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_bythedaycharterpayorder);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
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
