package com.sillykid.app.mine.mywallet.paymentpasswordmanagement.modifypaymentpassword;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;

/**
 * 修改支付密码
 * Created by Administrator on 2017/12/13.
 */

public class ModifyPaymentPasswordActivity extends BaseActivity {


    /**
     * 账号
     */
    @BindView(id = R.id.tv_accountNumber)
    private TextView tv_accountNumber;

    /**
     * 不记得
     */
    @BindView(id = R.id.tv_doNotRemember, click = true)
    private TextView tv_doNotRemember;

    /**
     * 记得
     */
    @BindView(id = R.id.tv_remember, click = true)
    private TextView tv_remember;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_modifypaymentpassword);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.modifyPaymentPassword), true, R.id.titlebar);
        String phone = PreferenceHelper.readString(aty, StringConstants.FILENAME, "phone", "");
        if (StringUtils.isEmpty(phone)) {
            finish();
            return;
        }
        tv_accountNumber.setText(phone);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_doNotRemember:
                String auth_status = PreferenceHelper.readString(aty, StringConstants.FILENAME, "auth_status", "init");
                if (auth_status != null && auth_status.equals("pass")) {
                    showActivity(aty, ModifyPaymentPassword1Activity.class);
                    break;
                } else if (auth_status != null && auth_status.equals("init") || auth_status != null && auth_status.equals("refuse")) {
                  //  showActivity(aty, IdentityAuthenticationActivity.class);
                    break;
                }
                ViewInject.toast(getString(R.string.doNotRemember1));
                break;
            case R.id.tv_remember:
                showActivity(aty, ModifyPaymentPassword3Activity.class);
                break;
        }
    }
}
