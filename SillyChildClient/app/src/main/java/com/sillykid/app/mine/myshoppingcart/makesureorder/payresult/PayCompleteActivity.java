package com.sillykid.app.mine.myshoppingcart.makesureorder.payresult;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myshoppingcart.makesureorder.payresult.PayCompleteBean;
import com.sillykid.app.main.MainActivity;
import com.sillykid.app.mine.myorder.MyOrderActivity;
import com.sillykid.app.mine.myshoppingcart.makesureorder.MakeSureOrderActivity;
import com.sillykid.app.mine.myshoppingcart.makesureorder.PaymentOrderActivity;

/**
 * 支付完成/支付失败
 */
public class PayCompleteActivity extends BaseActivity implements PayCompleteContract.View {

    /**
     * 支付状态
     */
    @BindView(id = R.id.tv_payStatus)
    private TextView tv_payStatus;

    @BindView(id = R.id.img_pay)
    private ImageView img_pay;

    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;

    @BindView(id = R.id.tv_consignee)
    private TextView tv_consignee;

    @BindView(id = R.id.tv_phone)
    private TextView tv_phone;

    @BindView(id = R.id.tv_deliveryAddress)
    private TextView tv_deliveryAddress;

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

    private String order_id;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_paycomplete);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new PayCompletePresenter(this);
        order_id = getIntent().getStringExtra("order_id");
        ((PayCompleteContract.Presenter) mPresenter).getOrderSimple(order_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        if (getIntent().getIntExtra("order_status", 0) == 1) {
            img_pay.setImageResource(R.mipmap.pay_success_icon);
            initTitle(getString(R.string.paySuccess));
            tv_payStatus.setText(getString(R.string.alipay_succeed));
            KJActivityStack.create().finishActivity(PaymentOrderActivity.class);
        } else {
            initTitle(getString(R.string.pay_error));
            img_pay.setImageResource(R.mipmap.pay_failure_icon);
            tv_payStatus.setText(getString(R.string.pay_error));
            tv_returnHomePage.setText(getString(R.string.payAgain));
        }
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
                KJActivityStack.create().finishActivity(MakeSureOrderActivity.class);
                Intent intent = new Intent(aty, MyOrderActivity.class);
                if (getIntent().getIntExtra("order_status", 0) == 1) {
                    intent.putExtra("newChageIcon", 1);
                    intent.putExtra("chageIcon", 1);
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

    @Override
    public void setPresenter(PayCompleteContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        PayCompleteBean payCompleteBean = (PayCompleteBean) JsonUtil.getInstance().json2Obj(success, PayCompleteBean.class);
        tv_orderNumber.setText(payCompleteBean.getData().getSn());
        tv_consignee.setText(payCompleteBean.getData().getShip_name());
        tv_phone.setText(payCompleteBean.getData().getShip_mobile());
        tv_deliveryAddress.setText(payCompleteBean.getData().getShip_area() + payCompleteBean.getData().getShip_addr());
        tv_payMoney.setText(getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(payCompleteBean.getData().getNeed_pay_money())));
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
