package com.sillykid.app.mine.mywallet.recharge.rechargeresult;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.sillykid.app.R;
import com.sillykid.app.mine.mywallet.recharge.RechargeActivity;

/**
 * 充值成功/充值失败
 */
public class RechargeCompleteActivity extends BaseActivity {


    @BindView(id = R.id.img_pay)
    private ImageView img_pay;


    @BindView(id = R.id.tv_returnHomePage, click = true)
    private TextView tv_returnHomePage;
    private int recharge_status = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_rechargecomplete);
    }

    @Override
    public void initData() {
        super.initData();
        recharge_status = getIntent().getIntExtra("recharge_status", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        if (recharge_status == 1) {
            initTitle(getString(R.string.rechargeSuccess));
            img_pay.setImageResource(R.mipmap.pay_payment_successful);
        } else {
            initTitle(getString(R.string.rechargeFailure));
            img_pay.setImageResource(R.mipmap.pay_payment_failure);
            tv_returnHomePage.setText(getText(R.string.payAgain));
        }
    }

    /**
     * 设置标题
     */
    public void initTitle(String status) {
        ActivityTitleUtils.initToolbar(aty, status, true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_returnHomePage:
                if (recharge_status == 1) {
                    RxBus.getInstance().post(new MsgEvent<String>("RxBusWithdrawalEvent"));
                    KJActivityStack.create().finishActivity(RechargeActivity.class);
                }
                finish();
                break;
        }
    }

}
