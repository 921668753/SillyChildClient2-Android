package com.sillykid.app.mine.mywallet.paymentpasswordmanagement.setpaymentpassword;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.myview.PayPwdEditText;
import com.sillykid.app.R;

/**
 * 设置支付密码
 * Created by Administrator on 2017/12/12.
 */

public class SetPaymentPasswordActivity extends BaseActivity {

    /**
     * 设置支付密码
     */
    @BindView(id = R.id.tv_setPaymentPassword)
    private TextView tv_setPaymentPassword;

    /**
     * 支付密码输入框
     */
    @BindView(id = R.id.et_paymentPassword)
    private PayPwdEditText et_paymentPassword;

    /**
     * 下一步
     */
    @BindView(id = R.id.tv_nextStep, click = true)
    private TextView tv_nextStep;

    private String paymentPassword = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_paymentpassword);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tv_nextStep.setClickable(false);
        ActivityTitleUtils.initToolbar(aty, getString(R.string.setPaymentPassword), true, R.id.titlebar);
        et_paymentPassword.initStyle(R.drawable.edit_num_bg, 6, 0.33f, R.color.bEBEC0Colors, R.color.f2222Colors, 20);
        et_paymentPassword.setOnTextFinishListener(new PayPwdEditText.OnTextFinishListener() {
            @Override
            public void onFinish(String str) {//密码输入完后的回调
                paymentPassword = str;
                if (str.length() == 6) {
                    tv_nextStep.setClickable(true);
                    tv_nextStep.setBackgroundResource(R.drawable.shape_login);
                } else {
                    tv_nextStep.setClickable(false);
                    tv_nextStep.setBackgroundResource(R.drawable.shape_login1);
                }
            }
        });
        tv_setPaymentPassword.setText(getString(R.string.pleaseEnterPaymentPassword));
        tv_nextStep.setText(getString(R.string.nextStep));
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_nextStep:
                if (paymentPassword.length() < 6) {
                    ViewInject.toast(getString(R.string.pleaseEnterPaymentPassword1));
                    break;
                }
                Intent intent = new Intent(aty, SetPaymentPassword1Activity.class);
                intent.putExtra("paymentPassword", paymentPassword);
                showActivity(aty, intent);
                break;
        }

    }
}
