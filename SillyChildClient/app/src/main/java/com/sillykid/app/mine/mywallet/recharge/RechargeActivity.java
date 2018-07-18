package com.sillykid.app.mine.mywallet.recharge;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.mine.mywallet.recharge.rechargeresult.RechargeCompleteActivity;
import com.unionpay.UPPayAssistEx;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.mywallet.recharge.AlipayBean;
import com.sillykid.app.entity.mine.mywallet.recharge.WeChatPayBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.mywallet.mybankcard.dialog.SubmitBouncedDialog;
import com.sillykid.app.utils.PayUtils;

import static com.sillykid.app.constant.NumericConstants.MODE;

/**
 * 充值
 * Created by Administrator on 2017/9/2.
 */

public class RechargeActivity extends BaseActivity implements RechargeContract.View {

    @BindView(id = R.id.tv_money100, click = true)
    private TextView tv_money100;

    @BindView(id = R.id.tv_money500, click = true)
    private TextView tv_money500;

    @BindView(id = R.id.tv_money3000, click = true)
    private TextView tv_money3000;

    @BindView(id = R.id.tv_money200, click = true)
    private TextView tv_money200;

    @BindView(id = R.id.tv_money1000, click = true)
    private TextView tv_money1000;

    @BindView(id = R.id.tv_money5000, click = true)
    private TextView tv_money5000;

    @BindView(id = R.id.tv_money300, click = true)
    private TextView tv_money300;

    @BindView(id = R.id.tv_money2000, click = true)
    private TextView tv_money2000;

    @BindView(id = R.id.tv_moneyOther, click = true)
    private TextView tv_moneyOther;

    @BindView(id = R.id.et_pleaseRechargeAmount)
    private EditText et_pleaseRechargeAmount;

    @BindView(id = R.id.iv_weChatPay, click = true)
    private ImageView iv_weChatPay;

    @BindView(id = R.id.iv_alipayToPay, click = true)
    private ImageView iv_alipayToPay;

    @BindView(id = R.id.iv_unionpayPay, click = true)
    private ImageView iv_unionpayPay;


    @BindView(id = R.id.tv_determinePay, click = true)
    private TextView tv_determinePay;


    private double rechargeMoney = 100;

    private int payWay = 2;

    private PayUtils payUtils;

    private SubmitBouncedDialog submitBouncedDialog = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_recharge);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new RechargePresenter(this);
        payUtils = new PayUtils(this);
        initDialog();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        cleanColors(tv_money100);
        clearImg(iv_weChatPay);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.accountRecharge), true, R.id.titlebar);
    }

    private void initDialog() {
        submitBouncedDialog = new SubmitBouncedDialog(aty, getString(R.string.installUnionpayControl)) {
            @Override
            public void confirm(int id) {
                this.dismiss();
                UPPayAssistEx.installUPPayPlugin(aty);
            }
        };
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_money100:
                cleanColors(tv_money100);
                rechargeMoney = 100;
                break;
            case R.id.tv_money200:
                cleanColors(tv_money200);
                rechargeMoney = 200;
                break;
            case R.id.tv_money300:
                cleanColors(tv_money300);
                rechargeMoney = 300;
                break;
            case R.id.tv_money500:
                cleanColors(tv_money500);
                rechargeMoney = 500;
                break;
            case R.id.tv_money1000:
                cleanColors(tv_money1000);
                rechargeMoney = 1000;
                break;
            case R.id.tv_money2000:
                cleanColors(tv_money2000);
                rechargeMoney = 2000;
                break;
            case R.id.tv_money3000:
                cleanColors(tv_money3000);
                rechargeMoney = 3000;
                break;
            case R.id.tv_money5000:
                cleanColors(tv_money5000);
                rechargeMoney = 5000;
                break;
            case R.id.tv_moneyOther:
                cleanColors(tv_moneyOther);
                break;
            case R.id.iv_weChatPay:
                clearImg(iv_weChatPay);
                payWay = 2;
                break;
            case R.id.iv_alipayToPay:
                clearImg(iv_alipayToPay);
                payWay = 1;
                break;
            case R.id.iv_unionpayPay:
                clearImg(iv_unionpayPay);
                payWay = 3;
                break;
            case R.id.tv_determinePay:
                showLoadingDialog(getString(R.string.submissionLoad));
                if (!StringUtils.isEmpty(et_pleaseRechargeAmount.getText().toString()) && StringUtils.toDouble(et_pleaseRechargeAmount.getText().toString().trim()) > 0) {
                    rechargeMoney = StringUtils.toDouble(et_pleaseRechargeAmount.getText().toString().trim());
                }

                ((RechargeContract.Presenter) mPresenter).doRecharge(payWay, rechargeMoney);
                break;
        }
    }

    private void cleanColors(TextView textView) {
        tv_money100.setBackgroundResource(R.drawable.shape_rechargeunselected);
        tv_money100.setTextColor(getResources().getColor(R.color.greenColors));
        tv_money500.setBackgroundResource(R.drawable.shape_rechargeunselected);
        tv_money500.setTextColor(getResources().getColor(R.color.greenColors));
        tv_money3000.setBackgroundResource(R.drawable.shape_rechargeunselected);
        tv_money3000.setTextColor(getResources().getColor(R.color.greenColors));
        tv_money200.setBackgroundResource(R.drawable.shape_rechargeunselected);
        tv_money200.setTextColor(getResources().getColor(R.color.greenColors));
        tv_money1000.setBackgroundResource(R.drawable.shape_rechargeunselected);
        tv_money1000.setTextColor(getResources().getColor(R.color.greenColors));
        tv_money5000.setBackgroundResource(R.drawable.shape_rechargeunselected);
        tv_money5000.setTextColor(getResources().getColor(R.color.greenColors));
        tv_money300.setBackgroundResource(R.drawable.shape_rechargeunselected);
        tv_money300.setTextColor(getResources().getColor(R.color.greenColors));
        tv_money2000.setBackgroundResource(R.drawable.shape_rechargeunselected);
        tv_money2000.setTextColor(getResources().getColor(R.color.greenColors));
        if (textView.getId() == R.id.tv_moneyOther) {
            et_pleaseRechargeAmount.setVisibility(View.VISIBLE);
            et_pleaseRechargeAmount.setText("");
        } else {
            et_pleaseRechargeAmount.setVisibility(View.GONE);
            et_pleaseRechargeAmount.setText("");
            textView.setBackgroundResource(R.color.greenColors);
            textView.setTextColor(getResources().getColor(R.color.whiteColors));
        }
    }

    private void clearImg(ImageView img) {
        iv_weChatPay.setImageResource(R.mipmap.top_ups_unselected);
        iv_alipayToPay.setImageResource(R.mipmap.top_ups_unselected);
        iv_unionpayPay.setImageResource(R.mipmap.top_ups_unselected);
        img.setImageResource(R.mipmap.top_ups_selected);
    }

    @Override
    public void setPresenter(RechargeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (payWay == 2) {
            WeChatPayBean weChatPayBean = (WeChatPayBean) JsonUtil.getInstance().json2Obj(success, WeChatPayBean.class);
            if (weChatPayBean.getData() == null || StringUtils.isEmpty(weChatPayBean.getData().getAppid())) {
                dismissLoadingDialog();
                ViewInject.toast(getString(R.string.payParseError));
                return;
            }
            if (payUtils == null) {
                payUtils = new PayUtils(this);
            }
            payUtils.doPayment(weChatPayBean.getData().getAppid(), weChatPayBean.getData().getPartnerid(), weChatPayBean.getData().getPrepayid(), weChatPayBean.getData().getPackageX(), weChatPayBean.getData().getNoncestr(), weChatPayBean.getData().getTimestamp(), weChatPayBean.getData().getSign());
        } else if (payWay == 1) {
            AlipayBean alipayBean = (AlipayBean) JsonUtil.getInstance().json2Obj(success, AlipayBean.class);
            if (alipayBean.getData() == null || StringUtils.isEmpty(alipayBean.getData().getOrderString())) {
                ViewInject.toast(getString(R.string.payParseError));
                return;
            }
            if (payUtils == null) {
                payUtils = new PayUtils(this);
            }

            payUtils.doPay(alipayBean.getData().getOrderString());
        } else {
            //从网络开始,获取交易流水号即TN（通过网络请求从后台获取到TN）
            if (payUtils == null) {
                payUtils = new PayUtils(this);
            }
            //   payUtils.doStartUnionPayPlugin(submitBouncedDialog, tn, MODE);
        }
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
            jumpPayComplete(1);
        } else if (str.equalsIgnoreCase("fail")) {
            jumpPayComplete(0);
        } else if (str.equalsIgnoreCase("cancel")) {
          //  ViewInject.toast(getString(R.string.alipay_order_cancel));
            jumpPayComplete(0);
        }

    }

    /**
     * 跳转支付完成页面
     *
     * @param recharge_status 1成功 0失败
     */
    public void jumpPayComplete(int recharge_status) {
        Intent intent = new Intent(aty, RechargeCompleteActivity.class);
        intent.putExtra("recharge_status", recharge_status);
        intent.putExtra("recharge_money", getRechargeMoney());
        showActivity(aty, intent);
    }

    /**
     * 充值金额
     */
    public String getRechargeMoney() {
        return MathUtil.keepTwo(StringUtils.toDouble(String.valueOf(rechargeMoney)));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (submitBouncedDialog != null) {
            submitBouncedDialog.cancel();
        }
        submitBouncedDialog = null;
        payUtils = null;
    }
}
