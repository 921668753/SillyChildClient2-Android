package com.sillykid.app.mine.mywallet.paymentpasswordmanagement.modifypaymentpassword;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.myview.PayPwdEditText;
import com.sillykid.app.R;


/**
 * 确认支付密码
 * Created by Administrator on 2017/12/13.
 */

public class ConfirmPaymentPasswordActivity extends BaseActivity implements ModifyPaymentPasswordContract.View {

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
    private String oldPaymentPassword = "";
    private String paymentPassword = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_paymentpassword);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new ModifyPaymentPasswordPresenter(this);
        oldPaymentPassword = getIntent().getStringExtra("paymentPassword");
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tv_nextStep.setClickable(false);
        ActivityTitleUtils.initToolbar(aty, getString(R.string.confirmPaymentPassword), true, R.id.titlebar);
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
        tv_setPaymentPassword.setText(getString(R.string.pleaseEnterConfirm));
        tv_nextStep.setText(getString(R.string.determine));
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_nextStep:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((ModifyPaymentPasswordContract.Presenter) mPresenter).postModifyPaymentPassword(oldPaymentPassword, paymentPassword);
                break;
        }

    }

    @Override
    public void setPresenter(ModifyPaymentPasswordContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        KJActivityStack.create().finishActivity(ModifyPaymentPasswordActivity.class);
        KJActivityStack.create().finishActivity(ModifyPaymentPassword1Activity.class);
        KJActivityStack.create().finishActivity(ModifyPaymentPassword2Activity.class);
        KJActivityStack.create().finishActivity(ModifyPaymentPassword3Activity.class);
        KJActivityStack.create().finishActivity(ModifyPaymentPassword4Activity.class);
        dismissLoadingDialog();
        ViewInject.toast(getString(R.string.modifyPaymentPasswordSucceed));
        finish();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        isLogin(msg);
    }
}
