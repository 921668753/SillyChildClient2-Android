package com.sillykid.app.mine.myorder.goodorder.aftersalesdetails;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.goodorder.aftersalesdetails.ApplyAfterSalesBean;
import com.sillykid.app.entity.mine.myorder.goodorder.aftersalesdetails.ApplyAfterSalesBean.DataBean.RefundTypeBean;
import com.sillykid.app.entity.mine.myorder.goodorder.aftersalesdetails.ApplyAfterSalesBean.DataBean.RefundReasonBean;
import com.sillykid.app.entity.mine.myorder.goodorder.aftersalesdetails.RefundMoneyBean;
import com.sillykid.app.loginregister.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 申请售后
 */
public class ApplyAfterSalesActivity extends BaseActivity implements ApplyAfterSalesContract.View {

    @BindView(id = R.id.tv_orderCode)
    private TextView tv_orderCode;

    @BindView(id = R.id.tv_submitTime)
    private TextView tv_submitTime;

    /**
     * 售后类型
     */
    @BindView(id = R.id.ll_afterType, click = true)
    private LinearLayout ll_afterType;

    @BindView(id = R.id.tv_afterType)
    private TextView tv_afterType;


    /**
     * 售后类型
     */
    @BindView(id = R.id.ll_afterNumber, click = true)
    private LinearLayout ll_afterNumber;

    @BindView(id = R.id.tv_afterNumber)
    private TextView tv_afterNumber;


    /**
     * 退款金额
     */
    @BindView(id = R.id.tv_refundAmount)
    private TextView tv_refundAmount;

    /**
     * 售后原因
     */
    @BindView(id = R.id.ll_afterWhy, click = true)
    private LinearLayout ll_afterWhy;

    @BindView(id = R.id.tv_afterWhy)
    private TextView tv_afterWhy;

    @BindView(id = R.id.et_accountAfterSalesService)
    private EditText et_accountAfterSalesService;

    /**
     * 提交
     */
    @BindView(id = R.id.tv_submit, click = true)
    private TextView tv_submit;

    private String item_id;

    private String good_id;

    private OptionsPickerView pvOptions = null;

    private OptionsPickerView pvOptions1 = null;

    private OptionsPickerView pvOptions2 = null;

    private List<RefundTypeBean> refundTypeList = null;

    private List<RefundReasonBean> refundReasonList = null;

    private String refundReasonId = null;

    private String refundTypeId = null;

    private int num = 0;

    private String orderCode;

    private String submitTime;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_applyaftersales);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ApplyAfterSalesPresenter(this);
        item_id = getIntent().getStringExtra("item_id");
        good_id = getIntent().getStringExtra("good_id");
        orderCode = getIntent().getStringExtra("orderCode");
        submitTime = getIntent().getStringExtra("submitTime");
        num = getIntent().getIntExtra("num", 0);
        // apply_alltotal = getIntent().getStringExtra("apply_alltotal");
        selectRefundType();
        selectRefundReason();
        selectReturnsNumber();
        showLoadingDialog(getString(R.string.dataLoad));
        ((ApplyAfterSalesContract.Presenter) mPresenter).getOrderRefundList();
    }

    /**
     * 选择退货类型
     */
    @SuppressWarnings("unchecked")
    private void selectRefundType() {
        pvOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                refundTypeId = refundTypeList.get(options1).getCode();
                ((TextView) v).setText(refundTypeList.get(options1).getCfg_value());
            }
        }).build();
    }

    /**
     * 选择退货原因
     */
    @SuppressWarnings("unchecked")
    private void selectRefundReason() {
        pvOptions1 = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                refundReasonId = refundReasonList.get(options1).getCode();
                ((TextView) v).setText(refundReasonList.get(options1).getCfg_value());
            }
        }).build();
    }

    /**
     * 选择退货数量
     */
    @SuppressWarnings("unchecked")
    private void selectReturnsNumber() {
        List<String> list = new ArrayList<String>();
        pvOptions2 = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                ((ApplyAfterSalesContract.Presenter) mPresenter).getOrderRefundMoney(item_id, list.get(options1));
                //  ((TextView) v).setText(list.get(options1));
            }
        }).build();
        for (int i = 0; i < num; i++) {
            list.add(String.valueOf(i + 1));
        }
        pvOptions2.setPicker(list);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        tv_orderCode.setText(orderCode);
        tv_submitTime.setText(submitTime);
        tv_refundAmount.setText("0.00");
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.applyAfterSales), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_afterType:
                pvOptions.show(tv_afterType);
                break;
            case R.id.ll_afterWhy:
                pvOptions1.show(tv_afterWhy);
                break;
            case R.id.ll_afterNumber:
                pvOptions2.show(tv_afterNumber);
                break;
            case R.id.tv_submit:
                showLoadingDialog(getString(R.string.dataLoad));
                ((ApplyAfterSalesContract.Presenter) mPresenter).postOrderRefund(item_id, refundTypeId, refundReasonId, et_accountAfterSalesService.getText().toString(), num);
                break;
        }
    }

    @Override
    public void setPresenter(ApplyAfterSalesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            ApplyAfterSalesBean applyAfterSalesBean = (ApplyAfterSalesBean) JsonUtil.getInstance().json2Obj(success, ApplyAfterSalesBean.class);
            refundTypeList = applyAfterSalesBean.getData().getRefund_type();
            refundReasonList = applyAfterSalesBean.getData().getRefund_reason();
            if (refundTypeList != null && refundTypeList.size() > 0) {
                pvOptions.setPicker(refundTypeList);
            }
            if (refundReasonList != null && refundReasonList.size() > 0) {
                pvOptions1.setPicker(refundReasonList);
            }
        } else if (flag == 1) {
            RefundMoneyBean refundMoneyBean = (RefundMoneyBean) JsonUtil.getInstance().json2Obj(success, RefundMoneyBean.class);
            if (refundMoneyBean.getData() != null && !StringUtils.isEmpty(refundMoneyBean.getData().getAlltotal())) {
                tv_afterNumber.setText(String.valueOf(refundMoneyBean.getData().getNum()));
                tv_refundAmount.setText(MathUtil.keepTwo(StringUtils.toDouble(refundMoneyBean.getData().getApply_alltotal())));
            }
        } else if (flag == 2) {
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusApplyAfterSalesEvent"));
            ViewInject.toast(getString(R.string.customerServiceStaffReview));
            finish();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            //  ViewInject.toast(getString(R.string.reloginPrompting));
            showActivity(this, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pvOptions = null;
        pvOptions1 = null;
        pvOptions2 = null;
    }

}
