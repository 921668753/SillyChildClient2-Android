package com.sillykid.app.homepage.airporttransportation.airportpickup;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.airporttransportation.airportpickup.AirportPickupPayOrderBean;
import com.sillykid.app.entity.homepage.airporttransportation.airportpickup.CreateTravelOrderBean;
import com.sillykid.app.homepage.airporttransportation.AirportTransportationClassificationActivity;
import com.sillykid.app.homepage.airporttransportation.PriceInformationActivity;
import com.sillykid.app.homepage.airporttransportation.SelectProductAirportTransportationActivity;
import com.sillykid.app.homepage.airporttransportation.paymentorder.PaymentTravelOrderActivity;
import com.sillykid.app.homepage.airporttransportation.search.ProductSearchActivity;
import com.sillykid.app.homepage.airporttransportation.search.ProductSearchListActivity;
import com.sillykid.app.mine.mywallet.coupons.CouponsActivity;
import com.sillykid.app.utils.DataUtil;
import com.sillykid.app.utils.GlideImageLoader;

import static com.sillykid.app.constant.NumericConstants.RESULT_CODE_GET;

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

    @BindView(id = R.id.web_priceDescription)
    private WebViewLayout web_priceDescription;

    @BindView(id = R.id.web_dueThat)
    private WebViewLayout web_dueThat;

    @BindView(id = R.id.tv_remark)
    private TextView tv_remark;

    @BindView(id = R.id.tv_orderMoney)
    private TextView tv_orderMoney;

    @BindView(id = R.id.tv_vouchers, click = true)
    private TextView tv_vouchers;

    @BindView(id = R.id.img_right)
    private ImageView img_right;


    @BindView(id = R.id.tv_actualPayment)
    private TextView tv_actualPayment;

    @BindView(id = R.id.tv_confirmPayment, click = true)
    private TextView tv_confirmPayment;

    private int requirement_id = 0;

    private String totalPrice = "0";

    private int bonusid = 0;

    private int product_id = 0;
    private String order_number = "";


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
        web_priceDescription.setTitleVisibility(false);
        web_dueThat.setTitleVisibility(false);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_vouchers:
                Intent intent1 = new Intent(aty, CouponsActivity.class);
                intent1.putExtra("type", -1);
                intent1.putExtra("business_id", 1);
                intent1.putExtra("money", totalPrice);
                startActivityForResult(intent1, RESULT_CODE_GET);
                break;
            case R.id.tv_confirmPayment:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((AirportPickupPayOrderContract.Presenter) mPresenter).createTravelOrder(product_id, order_number, bonusid);
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
        if (flag == 0) {
            AirportPickupPayOrderBean airportPickupPayOrderBean = (AirportPickupPayOrderBean) JsonUtil.getInstance().json2Obj(success, AirportPickupPayOrderBean.class);
            GlideImageLoader.glideOrdinaryLoader(aty, airportPickupPayOrderBean.getData().getMain_picture(), img_airportPickup, R.mipmap.placeholderfigure2);
            tv_airportName.setText(airportPickupPayOrderBean.getData().getTitle());
            tv_travelConfiguration.setText(airportPickupPayOrderBean.getData().getSubtitle_title());
            tv_flightNumber.setText(airportPickupPayOrderBean.getData().getFlight_number());
            tv_deliveredSite.setText(airportPickupPayOrderBean.getData().getDelivery_location());
            tv_flightArrivalTime.setText(DataUtil.formatData(StringUtils.toLong(airportPickupPayOrderBean.getData().getFlight_arrival_time()), "yyyy-MM-dd HH:mm"));
            tv_adult.setText(airportPickupPayOrderBean.getData().getAudit_number() + "");
            tv_children.setText(airportPickupPayOrderBean.getData().getChildren_number() + "");
            tv_luggage.setText(airportPickupPayOrderBean.getData().getBaggage_number() + "");
            tv_contact.setText(airportPickupPayOrderBean.getData().getContact());
            tv_contactWay.setText(airportPickupPayOrderBean.getData().getContact_number());
            String price_description = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" /><title></title></head><body>" + airportPickupPayOrderBean.getData().getPrice_description()
                    + "</body></html>";
            web_priceDescription.loadDataWithBaseURL(null, price_description, "text/html", "utf-8", null);
            web_priceDescription.getWebView().setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            String schedule_description = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" /><title></title></head><body>" + airportPickupPayOrderBean.getData().getSchedule_description()
                    + "</body></html>";
            web_dueThat.loadDataWithBaseURL(null, schedule_description, "text/html", "utf-8", null);
            web_dueThat.getWebView().setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            if (StringUtils.isEmpty(airportPickupPayOrderBean.getData().getRemarks())) {
                tv_remark.setText(getString(R.string.noRemark));
            } else {
                tv_remark.setText(airportPickupPayOrderBean.getData().getRemarks());
            }
            totalPrice = airportPickupPayOrderBean.getData().getPrice();
            tv_orderMoney.setText(getString(R.string.renminbi) + totalPrice);
            if (airportPickupPayOrderBean.getData().getBonus_number() != 0) {
                tv_vouchers.setText(airportPickupPayOrderBean.getData().getBonus_number() + getString(R.string.usable1));
            } else {
                tv_vouchers.setText(getString(R.string.renminbi) + "0.00");
                img_right.setVisibility(View.GONE);
                tv_vouchers.setOnClickListener(null);
            }
            tv_actualPayment.setText(getString(R.string.renminbi) + totalPrice);
            order_number = airportPickupPayOrderBean.getData().getOrder_number();
            product_id = airportPickupPayOrderBean.getData().getProduct_id();
        } else if (flag == 1) {
            KJActivityStack.create().finishActivity(AirportPickupActivity.class);
            KJActivityStack.create().finishActivity(ProductSearchListActivity.class);
            KJActivityStack.create().finishActivity(ProductSearchActivity.class);
            KJActivityStack.create().finishActivity(AirportTransportationClassificationActivity.class);
            KJActivityStack.create().finishActivity(PriceInformationActivity.class);
            KJActivityStack.create().finishActivity(SelectProductAirportTransportationActivity.class);
            CreateTravelOrderBean createTravelOrderBean = (CreateTravelOrderBean) JsonUtil.getInstance().json2Obj(success, CreateTravelOrderBean.class);
            /**
             * 发送消息
             */
            Intent intent = new Intent(aty, PaymentTravelOrderActivity.class);
            intent.putExtra("order_id", createTravelOrderBean.getData().getOrder_id());
            intent.putExtra("type", 1);
            intent.putExtra("end_time", createTravelOrderBean.getData().getEnd_time());
            intent.putExtra("start_time", createTravelOrderBean.getData().getStart_time());
            intent.putExtra("pay_amount", createTravelOrderBean.getData().getPay_amount());
            intent.putExtra("order_number", order_number);
            skipActivity(aty, intent);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CODE_GET && resultCode == RESULT_OK) {
            String money = data.getStringExtra("money");
            bonusid = data.getIntExtra("id", 0);
            tv_vouchers.setText(getString(R.string.renminbi) + "-" + MathUtil.keepTwo(StringUtils.toDouble(money)));
            tv_actualPayment.setText(calculationTotal());
        }
    }

    /**
     * 计算合计
     */
    private String calculationTotal() {
        double total = 0;
        total = StringUtils.toDouble(tv_orderMoney.getText().toString().trim().substring(1)) + StringUtils.toDouble(tv_vouchers.getText().toString().trim().substring(1));
        if (total <= 0) {
            total = 0;
        }
        return MathUtil.keepTwo(total);
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
