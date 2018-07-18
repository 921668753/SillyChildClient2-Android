package com.sillykid.app.homepage.chartercustom.routes;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.ConfirmOrderBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.mywallet.coupons.CouponsActivity;

import static com.sillykid.app.constant.NumericConstants.STATUS;

/**
 * 确认订单
 * Created by Admin on 2017/9/3.
 */

public class ConfirmOrderActivity extends BaseActivity implements ConfirmOrderContract.View {

    @BindView(id = R.id.tv_lineTitle)
    private TextView tv_lineTitle;

    @BindView(id = R.id.tv_serviceDate)
    private TextView tv_serviceDate;

    @BindView(id = R.id.tv_touristNumber)
    private TextView tv_touristNumber;

    @BindView(id = R.id.tv_bookingPeople)
    private TextView tv_bookingPeople;

    @BindView(id = R.id.tv_contactWay)
    private TextView tv_contactWay;


    @BindView(id = R.id.tv_origin)
    private TextView tv_origin;

    @BindView(id = R.id.tv_destination)
    private TextView tv_destination;

    @BindView(id = R.id.tv_orderAmount)
    private TextView tv_orderAmount;


    @BindView(id = R.id.ll_useCoupons, click = true)
    private LinearLayout ll_useCoupons;

    @BindView(id = R.id.tv_useCoupons)
    private TextView tv_useCoupons;


    @BindView(id = R.id.tv_actualPayment)
    private TextView tv_actualPayment;


    @BindView(id = R.id.tv_submit, click = true)
    private TextView tv_submit;

    private String name;
    private String contactWay;
    private String passportNum;
    private String idNumber;
    private String adult_num;
    private String child_num;
    private String origin;
    private String whereToPlay;
    private String destination;
    private String serviceDate;
    private String remark;
    private String lineTitle;
    private String line_id;
    private String line_price;
    private String bags;
    private String bags1;
    private String bags2;
    private String bags3;
    private int discount_id = 0;
    private int serviceDate1 = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_confirmorder);
    }

    @Override
    public void initData() {
        super.initData();
//        String selectCity = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectCity", "");
//        if (StringUtils.isEmpty(selectCity) || selectCity.contains(KJActivityStack.create().topActivity().getString(R.string.locateFailure))) {
//            ViewInject.toast(KJActivityStack.create().topActivity().getString(R.string.locateFailure) + KJActivityStack.create().topActivity().getString(R.string.travelCity1), 0);
//            finish();
//            return;
//        }
        mPresenter = new ConfirmOrderPresenter(this);
        line_id = getIntent().getStringExtra("line_id");
        serviceDate1 = getIntent().getIntExtra("serviceDate1", 0);
        line_price = getIntent().getStringExtra("line_price");
        lineTitle = getIntent().getStringExtra("line_title");
        name = getIntent().getStringExtra("name");
        contactWay = getIntent().getStringExtra("contactWay");
        passportNum = getIntent().getStringExtra("passportNum");
        idNumber = getIntent().getStringExtra("idNumber");
        adult_num = getIntent().getStringExtra("adult_num");
        child_num = getIntent().getStringExtra("child_num");
        origin = getIntent().getStringExtra("origin");
        whereToPlay = getIntent().getStringExtra("whereToPlay");
        destination = getIntent().getStringExtra("destination");
        serviceDate = getIntent().getStringExtra("serviceDate");
        bags = getIntent().getStringExtra("bags");
        bags1 = getIntent().getStringExtra("bags1");
        bags2 = getIntent().getStringExtra("bags2");
        bags3 = getIntent().getStringExtra("bags3");
        remark = getIntent().getStringExtra("remark");
    }


    @Override
    public void initWidget() {
        super.initWidget();
        /**
         * 设置标题
         */
        ActivityTitleUtils.initToolbar(aty, getString(R.string.confirmOrder), true, R.id.titlebar);
        tv_lineTitle.setText(lineTitle);
        tv_serviceDate.setText(serviceDate);
        if (StringUtils.isEmpty(child_num)) {
            tv_touristNumber.setText(adult_num + getString(R.string.adult));
        } else {
            tv_touristNumber.setText(adult_num + getString(R.string.adult) + "  " + child_num + getString(R.string.children));
        }

        tv_bookingPeople.setText(name);
        tv_contactWay.setText(contactWay);
        tv_origin.setText(origin);
        tv_destination.setText(destination);
        tv_orderAmount.setText(line_price);
        tv_useCoupons.setText("");
        //  tv_actualPayment.setText(line_price);
//        tv_actualPayment.setText(MathUtil.keepTwo(StringUtils.toDouble(line_price)));
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_useCoupons:
                Intent intent = new Intent(aty, CouponsActivity.class);
                intent.putExtra("type", "confirmOrder");
                intent.putExtra("total_price", line_price);
                startActivityForResult(intent, STATUS);
                break;
            case R.id.tv_submit:
                showLoadingDialog(getString(R.string.dataLoad));
                ((ConfirmOrderContract.Presenter) mPresenter).postConfirmOrder(line_id, lineTitle, name, contactWay, passportNum,
                        idNumber, bags3, bags2, bags1, bags, adult_num, child_num, serviceDate, origin + whereToPlay,
                        destination, discount_id, line_price, remark, line_price);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STATUS && resultCode == RESULT_OK) {// 如果等于1
            String money = data.getStringExtra("money");
            discount_id = data.getIntExtra("id", 0);
            tv_useCoupons.setText(getResources().getString(R.string.moneySign1) + money);
        }
    }

    @Override
    public void setPresenter(ConfirmOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        ConfirmOrderBean confirmOrderBean = (ConfirmOrderBean) JsonUtil.json2Obj(success, ConfirmOrderBean.class);
        Intent intent = new Intent(aty, CheckstandActivity.class);
        intent.putExtra("orderid", confirmOrderBean.getData().getAir_id());
        intent.putExtra("paymoney", confirmOrderBean.getData().getReal_price());
        intent.putExtra("paymoneyfmt", confirmOrderBean.getData().getReal_price_fmt());
        showActivity(aty, intent);
        KJActivityStack.create().finishActivity(FillInBasicInformationActivity.class);
        finish();
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
