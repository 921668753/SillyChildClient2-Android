package com.sillykid.app.mine.myorder.charterorder.orderdetails;

import android.view.View;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.sillykid.app.R;
import com.sillykid.app.loginregister.LoginActivity;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 我的订单---包车订单详情
 * Created by Administrator on 2018/9/2.
 */

public class CharterOrderDetailsActivity extends BaseActivity implements CharterOrderDetailsContract.View {

    private CharterOrderDetailsContract.Presenter mPresenter;

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    private String airid;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_charterorderdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new CharterOrderDetailsPresenter(this);
        airid = getIntent().getStringExtra("airid");

    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        showLoadingDialog(getString(R.string.dataLoad));
        mPresenter.getCharterOrderDetails(airid);

    }

    /**
     * 设置标题
     */
    public void initTitle() {
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                finish();
            }
        };
        titlebar.setDelegate(simpleDelegate);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.btn_bottoml:
                break;
        }
    }

    @Override
    public void setPresenter(CharterOrderDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {

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
