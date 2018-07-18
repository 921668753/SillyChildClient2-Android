package com.sillykid.app.mine.myshoppingcart.makesureorder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.TimeCount;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.OrderDetailBean;
import com.sillykid.app.entity.mine.mywallet.recharge.AlipayBean;
import com.sillykid.app.entity.mine.mywallet.recharge.WeChatPayBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myshoppingcart.makesureorder.payresult.PayCompleteActivity;
import com.sillykid.app.utils.PayUtils;

/**
 * 支付订单
 */
public class PaymentOrderActivity extends BaseActivity implements PaymentOrderContract.View, TimeCount.TimeCountCallBack {

    @BindView(id = R.id.tv_waitingPayment)
    public TextView tv_waitingPayment;
    @BindView(id = R.id.tv_lateCancelled)
    public TextView tv_lateCancelled;

    /**
     * 余额
     */
    @BindView(id = R.id.tv_currentBalance)
    public TextView tv_currentBalance;

    @BindView(id = R.id.img_currentBalance, click = true)
    public ImageView img_currentBalance;

    /**
     * 微信
     */
    @BindView(id = R.id.img_weChatPay, click = true)
    public ImageView img_weChatPay;

    /**
     * 支付宝
     */
    @BindView(id = R.id.img_alipay, click = true)
    public ImageView img_alipay;

    /**
     * 银联
     */
    @BindView(id = R.id.img_unionpayPay, click = true)
    public ImageView img_unionpayPay;

    /**
     * 合计价格
     */
    @BindView(id = R.id.tv_money)
    public TextView tv_money;

    /**
     * 确认订单
     */
    @BindView(id = R.id.tv_confirmPayment, click = true)
    public TextView tv_confirmPayment;

    private String order_id;

    private TimeCount time;

    private String pay_type = "";

    private PayUtils payUtils = null;

    private int type = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_paymentorder);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new PaymentOrderPresenter(this);
        order_id = getIntent().getStringExtra("order_id");
        type = getIntent().getIntExtra("type", 0);
        payUtils = new PayUtils(this);
        showLoadingDialog(getString(R.string.dataLoad));
        if (type == 1) {
            getSuccess("", 2);
        } else {
            ((PaymentOrderContract.Presenter) mPresenter).getOrderDetails(order_id);
        }
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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.paymentOrder), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_currentBalance:
                if (StringUtils.toDouble(tv_money.getText().toString().trim()) > StringUtils.toDouble(tv_currentBalance.getText().toString().trim())) {
                    return;
                }
                clearImg(img_currentBalance);
                pay_type = "qianbao";
                break;
            case R.id.img_weChatPay:
                clearImg(img_weChatPay);
                pay_type = "weixin";
                break;
            case R.id.img_alipay:
                clearImg(img_alipay);
                pay_type = "zhifubao";
                break;
            case R.id.img_unionpayPay:
                clearImg(img_unionpayPay);
                pay_type = "yinlian";
                break;
            case R.id.tv_confirmPayment:
                showLoadingDialog(getString(R.string.paymentLoad));
                ((PaymentOrderContract.Presenter) mPresenter).getOnlinePay(order_id, pay_type);
                break;
        }
    }

    /**
     * 更改图片
     */
    private void clearImg(ImageView img) {
        img_currentBalance.setImageResource(R.mipmap.top_ups_unselected);
        img_weChatPay.setImageResource(R.mipmap.top_ups_unselected);
        img_alipay.setImageResource(R.mipmap.top_ups_unselected);
        img_unionpayPay.setImageResource(R.mipmap.top_ups_unselected);
        img.setImageResource(R.mipmap.top_ups_selected);
    }


    @Override
    public void setPresenter(PaymentOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            OrderDetailBean orderDetailBean = (OrderDetailBean) JsonUtil.getInstance().json2Obj(success, OrderDetailBean.class);
            long last_time = StringUtils.toLong(orderDetailBean.getData().getLastTime()) - StringUtils.toLong(orderDetailBean.getData().getNowTime());
            time = new TimeCount(last_time * 1000, 1000);
            time.setTimeCountCallBack(this);
            time.start();
            tv_money.setText(MathUtil.keepTwo(StringUtils.toDouble(orderDetailBean.getData().getNeed_pay_money())));
            String balance = PreferenceHelper.readString(aty, StringConstants.FILENAME, "balance");
            tv_currentBalance.setText(MathUtil.keepTwo(StringUtils.toDouble(balance)));
            if (StringUtils.toDouble(tv_money.getText().toString().trim()) > StringUtils.toDouble(tv_currentBalance.getText().toString().trim())) {
                clearImg(img_weChatPay);
                pay_type = "weixin";
            } else {
                clearImg(img_currentBalance);
                pay_type = "qianbao";
            }
        } else if (flag == 1) {
            if (pay_type.contains("qianbao")) {
                jumpPayComplete(1);
            } else if (pay_type.contains("weixin")) {
                WeChatPayBean weChatPayBean = (WeChatPayBean) JsonUtil.getInstance().json2Obj(success, WeChatPayBean.class);
                if (weChatPayBean.getData() == null || StringUtils.isEmpty(weChatPayBean.getData().getAppid())) {
                    dismissLoadingDialog();
                    ViewInject.toast(getString(R.string.payParseError));
                    return;
                }
                if (payUtils == null) {
                    payUtils = new PayUtils(this);
                }
                dismissLoadingDialog();
                payUtils.doPayment(weChatPayBean.getData().getAppid(), weChatPayBean.getData().getPartnerid(), weChatPayBean.getData().getPrepayid(), weChatPayBean.getData().getPackageX(), weChatPayBean.getData().getNoncestr(), weChatPayBean.getData().getTimestamp(), weChatPayBean.getData().getSign());
            } else if (pay_type.contains("zhifubao")) {
                AlipayBean alipayBean = (AlipayBean) JsonUtil.getInstance().json2Obj(success, AlipayBean.class);
                if (alipayBean.getData() == null || StringUtils.isEmpty(alipayBean.getData().getOrderString())) {
                    dismissLoadingDialog();
                    ViewInject.toast(getString(R.string.payParseError));
                    return;
                }
                if (payUtils == null) {
                    payUtils = new PayUtils(this);
                }
                dismissLoadingDialog();
                payUtils.doPay(alipayBean.getData().getOrderString());
            } else if (pay_type.contains("yinlian")) {
                //从网络开始,获取交易流水号即TN（通过网络请求从后台获取到TN）
                if (payUtils == null) {
                    payUtils = new PayUtils(this);
                }
                //   payUtils.doStartUnionPayPlugin(submitBouncedDialog, tn, MODE);
            }
        } else if (flag == 2) {
            long last_time = StringUtils.toLong(getIntent().getStringExtra("last_time"));
            time = new TimeCount(last_time * 1000, 1000);
            time.setTimeCountCallBack(this);
            time.start();
            tv_money.setText(MathUtil.keepTwo(StringUtils.toDouble(getIntent().getStringExtra("money"))));
            tv_currentBalance.setText(MathUtil.keepTwo(StringUtils.toDouble(getIntent().getStringExtra("balance"))));
            if (StringUtils.toDouble(tv_money.getText().toString().trim()) > StringUtils.toDouble(tv_currentBalance.getText().toString().trim())) {
                clearImg(img_weChatPay);
                pay_type = "weixin";
            } else {
                clearImg(img_currentBalance);
                pay_type = "qianbao";
            }
        }
    }

    /**
     * 跳转支付完成页面
     *
     * @param order_status 1成功 0失败
     */
    public void jumpPayComplete(int order_status) {
        Intent intent = new Intent(aty, PayCompleteActivity.class);
        intent.putExtra("order_status", order_status);
        intent.putExtra("order_id", order_id);
        showActivity(aty, intent);
    }

    public String getOrderId() {
        return order_id;
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            if (flag == 0) {
                finish();
            }
            return;
        }
        if (pay_type.contains("qianbao")) {
            jumpPayComplete(0);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    public void onFinishTime() {
        tv_waitingPayment.setText(getString(R.string.tradingClosed));
        tv_lateCancelled.setVisibility(View.GONE);
        tv_confirmPayment.setVisibility(View.GONE);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        String millisUntilFinish = "";
        long minute = millisUntilFinished / 1000 / 60;
        long seconds = millisUntilFinished / 1000 % 60;
        if (minute > 0) {
            millisUntilFinish = minute + getString(R.string.minute) + seconds + getString(R.string.seconds);
        } else {
            millisUntilFinish = seconds + getString(R.string.seconds);
        }
        tv_lateCancelled.setText(getString(R.string.lateCancelled) + millisUntilFinish + getString(R.string.lateCancelled1));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {
            ViewInject.toast(getString(R.string.alipay_succeed));
            jumpPayComplete(1);
        } else if (str.equalsIgnoreCase("fail")) {
            ViewInject.toast(getString(R.string.alipay_order_error));
            jumpPayComplete(0);
        } else if (str.equalsIgnoreCase("cancel")) {
            ViewInject.toast(getString(R.string.alipay_order_cancel));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        payUtils = null;
        if (time != null) {
            time.onFinish();
            time.cancel();
        }
        time = null;
    }
}
