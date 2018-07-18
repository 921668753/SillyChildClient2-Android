package com.sillykid.app.mine.myorder.goodorder.aftersalesdetails;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.goodorder.aftersalesdetails.AfterSalesDetailsBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.DataUtil;

/**
 * 售后详情
 */
public class AfterSalesDetailsActivity extends BaseActivity implements AfterSalesDetailsContract.View {

    /**
     * 状态
     */
    @BindView(id = R.id.tv_type)
    private TextView tv_type;


    /**
     * 退款金额：
     */
    @BindView(id = R.id.tv_refundSuccess)
    private TextView tv_refundSuccess;

    /**
     * 服务详情
     */
    @BindView(id = R.id.ll_serviceDetails, click = true)
    private LinearLayout ll_serviceDetails;

    /**
     * 店铺名称：
     */
    @BindView(id = R.id.tv_shopName)
    private TextView tv_shopName;

    /**
     * 售后类型：退货退款
     */
    @BindView(id = R.id.tv_afterSalesType)
    private TextView tv_afterSalesType;

    /**
     * 售后数量：123
     */
    @BindView(id = R.id.tv_afterNumber)
    private TextView tv_afterNumber;

    /**
     * 退款金额：￥520.00
     */
    @BindView(id = R.id.tv_refundAmount)
    private TextView tv_refundAmount;

    /**
     * 退款金额：￥520.00
     */
    @BindView(id = R.id.tv_refundReason)
    private TextView tv_refundReason;

    /**
     * 商品名称：xxxxxx
     */
    @BindView(id = R.id.tv_goodName)
    private TextView tv_goodName;

    /**
     * 订单编号：xxxxxx
     */
    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;

    /**
     * 订单时间：2018.06.05  12:00:00
     */
    @BindView(id = R.id.tv_orderTime)
    private TextView tv_orderTime;

    private String item_id = "";

    private String good_id = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_aftersalesdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AfterSalesDetailsPresenter(this);
        item_id = getIntent().getStringExtra("item_id");
        good_id = getIntent().getStringExtra("good_id");
        showLoadingDialog(getString(R.string.dataLoad));
        ((AfterSalesDetailsContract.Presenter) mPresenter).getSellBackDetail(item_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_serviceDetails:
                Intent intent = new Intent(aty, ServiceDetailsActivity.class);
                intent.putExtra("item_id", item_id);
                showActivity(aty, intent);
                break;
        }
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.afterSalesDetails), true, R.id.titlebar);
    }

    @Override
    public void setPresenter(AfterSalesDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        AfterSalesDetailsBean afterSalesDetailsBean = (AfterSalesDetailsBean) JsonUtil.getInstance().json2Obj(success, AfterSalesDetailsBean.class);
        if (afterSalesDetailsBean.getData().getTradestatus() == 0) {
            tv_type.setText(getString(R.string.toAudit));
        } else if (afterSalesDetailsBean.getData().getTradestatus() == 1) {
            tv_type.setText(getString(R.string.pendingDelivery));
        } else if (afterSalesDetailsBean.getData().getTradestatus() == 2) {
            tv_type.setText(getString(R.string.merchantRejectedApplication));
        } else if (afterSalesDetailsBean.getData().getTradestatus() == 3) {
            tv_type.setText(getString(R.string.merchantRefund));
        } else if (afterSalesDetailsBean.getData().getTradestatus() == 6) {
            tv_type.setText(getString(R.string.platformRefundCompleted));
        }

        tv_refundSuccess.setText(getString(R.string.refundAmount1) + getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(afterSalesDetailsBean.getData().getApply_alltotal())));

        tv_shopName.setText(getString(R.string.shopName) + afterSalesDetailsBean.getData().getStore_name());

        tv_afterSalesType.setText(getString(R.string.afterType1) + afterSalesDetailsBean.getData().getRemark());

        tv_afterNumber.setText(getString(R.string.afterNumber1) + afterSalesDetailsBean.getData().getNum());

        tv_refundAmount.setText(getString(R.string.refundAmount1) + getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(afterSalesDetailsBean.getData().getApply_alltotal())));

        tv_refundReason.setText(getString(R.string.refundReason) + afterSalesDetailsBean.getData().getReason());

        tv_goodName.setText(afterSalesDetailsBean.getData().getName());

        tv_orderNumber.setText(getString(R.string.orderNumber) + afterSalesDetailsBean.getData().getOrdersn());

        tv_orderTime.setText(getString(R.string.orderTime1) + DataUtil.formatData(StringUtils.toLong(afterSalesDetailsBean.getData().getOrder_create_time()), "yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            //  ViewInject.toast(getString(R.string.reloginPrompting));
            skipActivity(this, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }
}
