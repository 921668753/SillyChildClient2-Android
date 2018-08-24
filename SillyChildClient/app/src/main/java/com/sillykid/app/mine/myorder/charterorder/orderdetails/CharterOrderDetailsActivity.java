package com.sillykid.app.mine.myorder.charterorder.orderdetails;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.charterorder.orderdetails.CharterOrderDetailsBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.DataUtil;

/**
 * 我的订单---包车订单详情
 * Created by Administrator on 2018/9/2.
 */

public class CharterOrderDetailsActivity extends BaseActivity implements CharterOrderDetailsContract.View {

    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;

    @BindView(id = R.id.tv_serviceTime)
    private TextView tv_serviceTime;

    @BindView(id = R.id.tv_charterCity)
    private TextView tv_charterCity;

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
        setContentView(R.layout.activity_charterorderdetails);
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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.byTheDay), true, R.id.titlebar);
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
        CharterOrderDetailsBean charterOrderDetailsBean = (CharterOrderDetailsBean) JsonUtil.getInstance().json2Obj(success, CharterOrderDetailsBean.class);
        tv_orderNumber.setText(charterOrderDetailsBean.getData().getOrder_number());
        tv_serviceTime.setText(DataUtil.formatData(StringUtils.toLong(charterOrderDetailsBean.getData().getTravel_start_time()),
                "yyyy" + getString(R.string.year) + "MM" + getString(R.string.month) + "dd" + getString(R.string.day)) +
                "-" + DataUtil.formatData(StringUtils.toLong(charterOrderDetailsBean.getData().getTravel_end_time()),
                "yyyy" + getString(R.string.year) + "MM" + getString(R.string.month) + "dd" + getString(R.string.day)));
        tv_charterCity.setText(charterOrderDetailsBean.getData().getDeparture());
        tv_passengersNumber.setText(charterOrderDetailsBean.getData().getAudit_number() + getString(R.string.adult) + "   " + charterOrderDetailsBean.getData().getChildren_number() + getString(R.string.children));
        tv_bags.setText(charterOrderDetailsBean.getData().getBaggage_number() + getString(R.string.bags2));
        tv_userNote.setText(charterOrderDetailsBean.getData().getRemarks());
        tv_orderPriceNoSign.setText(getString(R.string.renminbi) + charterOrderDetailsBean.getData().getOrder_amount());
        tv_vouchers.setText(getString(R.string.renminbi) + charterOrderDetailsBean.getData().getDis_amount());
        tv_actualPayment.setText(getString(R.string.renminbi) + charterOrderDetailsBean.getData().getPay_amount());
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(this, LoginActivity.class);
            finish();
            return;
        }
        ViewInject.toast(msg);
    }

}
