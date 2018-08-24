package com.sillykid.app.mine.myorder.charterorder.orderdetails;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;

/**
 * 私人定制订单详情
 */
public class PrivateCustomOrderDetailsActivity extends BaseActivity implements CharterOrderDetailsContract.View {

    private String order_number;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_privatecustomorderdetails);
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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.privateOrdering), true, R.id.titlebar);
    }

    @Override
    public void setPresenter(CharterOrderDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
    }
}
