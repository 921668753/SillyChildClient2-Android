package com.sillykid.app.homepage.chartercustom.personaltailor;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.dialog.chartercustom.CompensationChangeBackDialog;
import com.sillykid.app.dialog.chartercustom.CostsThatDialog;
import com.sillykid.app.homepage.chartercustom.routes.CheckstandActivity;
import com.sillykid.app.loginregister.LoginActivity;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 私人定制    确认订单
 * Created by Admin on 2017/11/15.
 */

public class CustomizationConfirmOrderActivity extends BaseActivity implements CustomizationConfirmOrderContract.View {

    /**
     * 标题
     */
    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    /**
     * 姓名
     */
    @BindView(id = R.id.et_yourName)
    private EditText et_yourName;

    /**
     * 联系方式
     */
    @BindView(id = R.id.et_contactWay)
    private EditText et_contactWay;

    /**
     * 护照号
     */
    @BindView(id = R.id.et_passportNum)
    private EditText et_passportNum;

    /**
     * 身份证号
     */
    @BindView(id = R.id.et_idNumber)
    private EditText et_idNumber;

    /**
     * 行程车型
     */
    @BindView(id = R.id.tv_strokeModels)
    private TextView tv_strokeModels;

    /**
     * 行李
     */
    @BindView(id = R.id.tv_subtractionBags, click = true)
    TextView tv_subtractionBags;
    @BindView(id = R.id.tv_bags)
    TextView tv_bags;
    @BindView(id = R.id.tv_addBags, click = true)
    TextView tv_addBags;

    @BindView(id = R.id.tv_subtractionBags1, click = true)
    TextView tv_subtractionBags1;
    @BindView(id = R.id.tv_bags1)
    TextView tv_bags1;
    @BindView(id = R.id.tv_addBags1, click = true)
    TextView tv_addBags1;

    @BindView(id = R.id.tv_subtractionBags2, click = true)
    TextView tv_subtractionBags2;
    @BindView(id = R.id.tv_bags2)
    TextView tv_bags2;
    @BindView(id = R.id.tv_addBags2, click = true)
    TextView tv_addBags2;

    @BindView(id = R.id.tv_subtractionBags3, click = true)
    TextView tv_subtractionBags3;
    @BindView(id = R.id.tv_bags3)
    TextView tv_bags3;
    @BindView(id = R.id.tv_addBags3, click = true)
    TextView tv_addBags3;


    /**
     * 出行人数
     */
    @BindView(id = R.id.tv_contactWay)
    private TextView tv_contactWay;

    /**
     * 出行时间
     */
    @BindView(id = R.id.tv_travelTime)
    private TextView tv_travelTime;


    /**
     * 费用说明
     */
    @BindView(id = R.id.ll_costsThat, click = true)
    private LinearLayout ll_costsThat;

    /**
     * 退订政策
     */
    @BindView(id = R.id.ll_compensationChangeBack, click = true)
    private LinearLayout ll_compensationChangeBack;
    @BindView(id = R.id.tv_compensationChangeBack)
    private TextView tv_compensationChangeBack;

    /**
     * 备注
     */
    @BindView(id = R.id.et_remark)
    private EditText et_remark;

    /**
     * 提交订单
     */
    @BindView(id = R.id.tv_submit, click = true)
    private TextView tv_submit;
    private String air_id = "0";
    private CompensationChangeBackDialog compensationChangeBackDialog;
    private CostsThatDialog costsThatDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_customizationconfirmorder);
    }

    @Override
    public void initData() {
        super.initData();
        air_id = aty.getIntent().getStringExtra("air_id");
        String costStatement = aty.getIntent().getStringExtra("cost_statement");
        String costCompensation = aty.getIntent().getStringExtra("cost_compensation");
        tv_compensationChangeBack.setText(changeBack(costCompensation));
        compensationChangeBackDialog = new CompensationChangeBackDialog(this, costCompensation);
        compensationChangeBackDialog.setCanceledOnTouchOutside(false);
        costsThatDialog = new CostsThatDialog(this, costStatement);
        costsThatDialog.setCanceledOnTouchOutside(false);
        mPresenter = new CustomizationConfirmOrderPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.confirmOrder), true, R.id.titlebar);
        String customer_name = aty.getIntent().getStringExtra("customer_name");
        et_yourName.setText(customer_name);
        String customer_phone = aty.getIntent().getStringExtra("customer_phone");
        et_contactWay.setText(customer_phone);
        String use_car_adult = aty.getIntent().getStringExtra("use_car_adult");
        String use_car_children = aty.getIntent().getStringExtra("use_car_children");
        tv_contactWay.setText(use_car_adult + getString(R.string.adult) + use_car_children + getString(R.string.children));
        String req_car_level = aty.getIntent().getStringExtra("req_car_level");
        String req_car_seat_num = aty.getIntent().getStringExtra("req_car_seat_num");
        if (StringUtils.isEmpty(req_car_seat_num)) {
            tv_strokeModels.setText(req_car_level);
        } else {
            tv_strokeModels.setText(req_car_seat_num + getString(R.string.peopleseat) + req_car_level);
        }
        String tour_time = aty.getIntent().getStringExtra("tour_time");
        tv_travelTime.setText(tour_time);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_subtractionBags:
                subtractionNum(tv_subtractionBags, tv_bags);
                break;
            case R.id.tv_addBags:
                addNum(tv_subtractionBags, tv_bags);
                break;
            case R.id.tv_subtractionBags1:
                subtractionNum(tv_subtractionBags1, tv_bags1);
                break;
            case R.id.tv_addBags1:
                addNum(tv_subtractionBags1, tv_bags1);
                break;
            case R.id.tv_subtractionBags2:
                subtractionNum(tv_subtractionBags2, tv_bags2);
                break;
            case R.id.tv_addBags2:
                addNum(tv_subtractionBags2, tv_bags2);
                break;
            case R.id.tv_subtractionBags3:
                subtractionNum(tv_subtractionBags3, tv_bags3);
                break;
            case R.id.tv_addBags3:
                addNum(tv_subtractionBags3, tv_bags3);
                break;
            case R.id.ll_costsThat:
                costsThatDialog.show();
                break;

            case R.id.ll_compensationChangeBack:
                compensationChangeBackDialog.show();
                break;
            case R.id.tv_submit:
                showLoadingDialog(getString(R.string.dataLoad));
                ((CustomizationConfirmOrderContract.Presenter) mPresenter).postSaveUserPrivate(air_id, et_yourName.getText().toString().trim(),
                        et_contactWay.getText().toString().trim(), et_passportNum.getText().toString().trim(), et_idNumber.getText().toString().trim(),
                        tv_bags.getText().toString().trim(), tv_bags1.getText().toString().trim(), tv_bags2.getText().toString().trim(),
                        tv_bags3.getText().toString().trim(), et_remark.getText().toString().trim());
                break;
        }
    }

    /**
     * @param textView 所要减的控件
     */
    @SuppressWarnings("deprecation")
    private void subtractionNum(TextView textView, TextView textView1) {
        if (StringUtils.toInt(textView1.getText().toString(), 0) - 1 <= 0) {
            textView.setTextColor(aty.getResources().getColor(R.color.hintColors));
            textView.setBackgroundResource(R.drawable.shape_code1);
            textView1.setText("0");
        } else {
            textView.setTextColor(aty.getResources().getColor(R.color.greenColors));
            textView.setBackgroundResource(R.drawable.shape_code);
            textView1.setText(String.valueOf(StringUtils.toInt(textView1.getText().toString(), 0) - 1));
        }
    }

    /**
     * @param textView 所要加的控件
     */
    @SuppressWarnings("deprecation")
    private void addNum(TextView textView, TextView textView1) {
        textView.setTextColor(aty.getResources().getColor(R.color.greenColors));
        textView.setBackgroundResource(R.drawable.shape_code);
        textView1.setText(String.valueOf(StringUtils.toInt(textView1.getText().toString(), 0) + 1));
    }

    /**
     * 判断政策
     */
    public String changeBack(String compensationChangeBack) {
        String policy = "";
        if (compensationChangeBack != null && compensationChangeBack.startsWith("cover_img_k")) {
            policy = getString(R.string.looseFitting);
        } else if (compensationChangeBack != null && compensationChangeBack.startsWith("cover_img_y")) {
            policy = getString(R.string.rigor);
        } else if (compensationChangeBack != null && compensationChangeBack.startsWith("cover_img_z")) {
            policy = getString(R.string.middling);
        } else {
            policy = getString(R.string.unsubscribe);
        }
        return policy;
    }

    @Override
    public void setPresenter(CustomizationConfirmOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        // ConfirmOrderBean confirmOrderBean = (ConfirmOrderBean) JsonUtil.json2Obj(success, ConfirmOrderBean.class);
      //  KJActivityStack.create().finishActivity(CustomerServiceActivity.class);
        KJActivityStack.create().finishActivity(ScheduleDetailsActivity.class);
        Intent intent = new Intent(aty, CheckstandActivity.class);
        intent.putExtra("orderid", air_id);
        intent.putExtra("paymoney", aty.getIntent().getStringExtra("total_price"));
        intent.putExtra("paymoneyfmt", getString(R.string.moneySign) + aty.getIntent().getStringExtra("total_price"));
        skipActivity(aty, intent);
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
}
