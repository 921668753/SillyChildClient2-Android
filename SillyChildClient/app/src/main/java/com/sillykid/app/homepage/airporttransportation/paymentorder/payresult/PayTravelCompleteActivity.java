package com.sillykid.app.homepage.airporttransportation.paymentorder.payresult;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;
import com.sillykid.app.homepage.airporttransportation.AirportTransportationClassificationActivity;
import com.sillykid.app.homepage.airporttransportation.PriceInformationActivity;
import com.sillykid.app.homepage.airporttransportation.SelectProductAirportTransportationActivity;
import com.sillykid.app.homepage.airporttransportation.airportpickup.AirportPickupActivity;
import com.sillykid.app.homepage.airporttransportation.paymentorder.PaymentTravelOrderActivity;
import com.sillykid.app.main.MainActivity;
import com.sillykid.app.mine.myorder.MyOrderActivity;

/**
 * 支付完成/支付失败
 */
public class PayTravelCompleteActivity extends BaseActivity {

    /**
     * 支付状态
     */
    @BindView(id = R.id.tv_payStatus)
    private TextView tv_payStatus;

    @BindView(id = R.id.img_pay)
    private ImageView img_pay;

    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;

    @BindView(id = R.id.ll_consignee)
    private LinearLayout ll_consignee;

    @BindView(id = R.id.ll_deliveryAddress)
    private LinearLayout ll_deliveryAddress;

    /**
     * 支付金额
     */
    @BindView(id = R.id.tv_payMoney)
    private TextView tv_payMoney;

    /**
     * 查看订单
     */
    @BindView(id = R.id.tv_checkOrder, click = true)
    private TextView tv_checkOrder;

    /**
     * 返回首页
     */
    @BindView(id = R.id.tv_returnHomePage, click = true)
    private TextView tv_returnHomePage;

    private int order_id;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_paycomplete);
    }

    @Override
    public void initData() {
        super.initData();
        order_id = getIntent().getIntExtra("order_id", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ll_consignee.setVisibility(View.GONE);
        ll_deliveryAddress.setVisibility(View.GONE);
        if (getIntent().getIntExtra("order_status", 0) == 1) {
            img_pay.setImageResource(R.mipmap.pay_success_icon);
            initTitle(getString(R.string.paySuccess));
            tv_payStatus.setText(getString(R.string.alipay_succeed));
            KJActivityStack.create().finishActivity(PaymentTravelOrderActivity.class);
        } else {
            initTitle(getString(R.string.pay_error));
            img_pay.setImageResource(R.mipmap.pay_failure_icon);
            tv_payStatus.setText(getString(R.string.pay_error));
            tv_returnHomePage.setText(getString(R.string.payAgain));
        }
        tv_orderNumber.setText(getIntent().getStringExtra("order_number"));
        tv_payMoney.setText(getString(R.string.renminbi) + getIntent().getStringExtra("pay_amount"));
    }

    /**
     * 设置标题
     */
    public void initTitle(String title) {
        ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_checkOrder:
                KJActivityStack.create().finishActivity(AirportPickupActivity.class);
                KJActivityStack.create().finishActivity(AirportTransportationClassificationActivity.class);
                KJActivityStack.create().finishActivity(PriceInformationActivity.class);
                KJActivityStack.create().finishActivity(SelectProductAirportTransportationActivity.class);
                Intent intent = new Intent(aty, MyOrderActivity.class);
                if (getIntent().getIntExtra("order_status", 0) == 1) {
                    intent.putExtra("newChageIcon", 1);
                    intent.putExtra("chageIcon", 1);
                    intent.putExtra("chageCharterIcon", 21);
                }
                skipActivity(aty, intent);
                break;
            case R.id.tv_returnHomePage:
                if (getIntent().getIntExtra("order_status", 0) != 1) {
                    finish();
                    return;
                }
                Intent intent1 = new Intent(aty, MainActivity.class);
                intent1.putExtra("newChageIcon", 0);
                showActivity(aty, intent1);
                KJActivityStack.create().finishOthersActivity(MainActivity.class);
                break;
        }
    }

}
