package com.sillykid.app.homepage.chartercustom.routes;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.homepage.boutiqueline.dialog.CalendarControlBouncedDialog;
import com.sillykid.app.homepage.airporttransportation.dialog.CompensationChangeBackDialog;
import com.sillykid.app.dialog.chartercustom.CostsThatDialog;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.SoftKeyboardUtils;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 填写基本信息
 * Created by Admin on 2017/9/3.
 */

public class FillInBasicInformationActivity extends BaseActivity implements FillInBasicInformationContract.View {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.et_name)
    EditText et_name;

    @BindView(id = R.id.et_contactWay)
    EditText et_contactWay;

    @BindView(id = R.id.et_passportNum)
    EditText et_passportNum;

    @BindView(id = R.id.et_idNumber)
    EditText et_idNumber;

    /**
     * 选择出行人数
     */
    @BindView(id = R.id.tv_subtractionAdult, click = true)
    TextView tv_subtractionAdult;
    @BindView(id = R.id.tv_adultNum)
    TextView tv_adultNum;
    @BindView(id = R.id.tv_addAdult, click = true)
    TextView tv_addAdult;

    @BindView(id = R.id.tv_subtractionChildren, click = true)
    TextView tv_subtractionChildren;

    @BindView(id = R.id.tv_childrenNum)
    TextView tv_childrenNum;

    @BindView(id = R.id.tv_addChildren, click = true)
    TextView tv_addChildren;

    @BindView(id = R.id.et_origin)
    TextView et_origin;

    @BindView(id = R.id.et_whereToPlay)
    TextView et_whereToPlay;

    @BindView(id = R.id.et_destination)
    TextView et_destination;

    @BindView(id = R.id.ll_serviceDate, click = true)
    LinearLayout ll_serviceDate;
    @BindView(id = R.id.tv_serviceDate)
    TextView tv_serviceDate;

    int serviceDate = 0;
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


    @BindView(id = R.id.ll_costsThat, click = true)
    LinearLayout ll_costsThat;


    @BindView(id = R.id.ll_compensationChangeBack, click = true)
    LinearLayout ll_compensationChangeBack;


    @BindView(id = R.id.et_remark)
    EditText et_remark;

    @BindView(id = R.id.tv_submit, click = true)
    TextView tv_submit;

    @BindView(id = R.id.tv_compensationChangeBack)
    TextView tv_compensationChangeBack;

    private String line_id;
    private String line_title;
    private String line_price;
    private CalendarControlBouncedDialog calendarControlBouncedDialog;
    private CompensationChangeBackDialog compensationChangeBackDialog;
    private CostsThatDialog costsThatDialog;
    private int playDay = 0;
    private String costStatement;
    private String costCompensation;
    private String costCompensationLevel;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_quickreservation);
    }

    @Override
    public void initData() {
        super.initData();
//        String selectCity = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectCity", "");
//        if (StringUtils.isEmpty(selectCity) || selectCity.contains(KJActivityStack.create().topActivity().getString(R.string.locateFailure))) {
//            ViewInject.toast(KJActivityStack.create().topActivity().getString(R.string.locateFailure) + KJActivityStack.create().topActivity().getString(R.string.travelCity1), 0);
//            finish();
//            return;
//        }
        costStatement = getIntent().getStringExtra("costStatement");
        costCompensation = getIntent().getStringExtra("costCompensation");
        costCompensationLevel= getIntent().getStringExtra("costCompensationLevel");
        tv_compensationChangeBack.setText(costCompensationLevel);
      //  compensationChangeBackDialog = new CompensationChangeBackDialog(this, costCompensation);
        compensationChangeBackDialog.setCanceledOnTouchOutside(false);
        costsThatDialog = new CostsThatDialog(this, costStatement);
        costsThatDialog.setCanceledOnTouchOutside(false);
        mPresenter = new FillInBasicInformationPresenter(this);
        line_id = getIntent().getStringExtra("line_id");
        line_title = getIntent().getStringExtra("line_title");
        line_price = getIntent().getStringExtra("line_price");

        playDay = getIntent().getIntExtra("playDay", 0);
        calendarControlBouncedDialog = new CalendarControlBouncedDialog(this) {
            @Override
            public void timeList(String dateStr) {
                tv_serviceDate.setText(dateStr);
                serviceDate = 1;
            }
        };
        calendarControlBouncedDialog.setCanceledOnTouchOutside(false);
//        selectPeople();
//        selectPeople1();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                ((FillInBasicInformationContract.Presenter) mPresenter).isLogin();
            }
        };
        titlebar.setTitleText(getString(R.string.fillInformation));
        titlebar.setRightText("");
        titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.kefu));
        titlebar.setDelegate(simpleDelegate);
        tv_submit.setText(getString(R.string.continueReserve));
      //  StringNewUtils.setFilters(et_remark);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_subtractionAdult:
                subtractionNum(tv_subtractionAdult, tv_adultNum);
                break;
            case R.id.tv_addAdult:
                addNum(tv_subtractionAdult, tv_adultNum);
                break;
            case R.id.tv_subtractionChildren:
                subtractionNum(tv_subtractionChildren, tv_childrenNum);
                break;
            case R.id.tv_addChildren:
                addNum(tv_subtractionChildren, tv_childrenNum);
                break;
            case R.id.ll_serviceDate:
                SoftKeyboardUtils.packUpKeyboard(this);
                calendarControlBouncedDialog.show();
                break;
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
                showLoadingDialog(getString(R.string.submissionLoad));
                ((FillInBasicInformationContract.Presenter) mPresenter).postQuickReservation(
                        et_name.getText().toString().trim(), et_contactWay.getText().toString().trim(),
                        et_passportNum.getText().toString().trim(), et_idNumber.getText().toString().trim(),
                        tv_adultNum.getText().toString(), tv_childrenNum.getText().toString(),
                        et_origin.getText().toString(), et_whereToPlay.getText().toString(), et_destination.getText().toString(),
                        tv_serviceDate.getText().toString(), tv_bags.getText().toString(), tv_bags1.getText().toString(),
                        tv_bags2.getText().toString(), tv_bags3.getText().toString(), et_remark.getText().toString().trim()
                );
                break;
        }
    }

    @Override
    public void setPresenter(FillInBasicInformationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        switch (flag) {
            case 0:
                Intent intent = new Intent(aty, ConfirmOrderActivity.class);
                intent.putExtra("line_id", line_id);
                intent.putExtra("line_title", line_title);
                intent.putExtra("line_price", line_price);
                intent.putExtra("name", et_name.getText().toString().trim());
                intent.putExtra("contactWay", et_contactWay.getText().toString().trim());
                intent.putExtra("passportNum", et_passportNum.getText().toString().trim());
                intent.putExtra("idNumber", et_idNumber.getText().toString().trim());
                intent.putExtra("adult_num", tv_adultNum.getText().toString());
                intent.putExtra("child_num", tv_childrenNum.getText().toString());
                intent.putExtra("origin", et_origin.getText().toString().trim());
                intent.putExtra("whereToPlay", et_whereToPlay.getText().toString().trim());
                intent.putExtra("destination", et_destination.getText().toString().trim());
                intent.putExtra("serviceDate", tv_serviceDate.getText().toString());
                intent.putExtra("serviceDate1", serviceDate);
                intent.putExtra("bags", tv_bags.getText().toString().trim());
                intent.putExtra("bags1", tv_bags1.getText().toString().trim());
                intent.putExtra("bags2", tv_bags2.getText().toString().trim());
                intent.putExtra("bags3", tv_bags3.getText().toString());
                intent.putExtra("remark", et_remark.getText().toString().trim());
                showActivity(aty, intent);
                break;
            case 1:
              //  showActivity(aty, OverleafActivity.class);
                break;


        }
        dismissLoadingDialog();
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
//    public String changeBack(String compensationChangeBack) {
//        String policy = "";
//        if (compensationChangeBack != null && compensationChangeBack.startsWith("cover_img_k")) {
//            policy = getString(R.string.looseFitting);
//        } else if (compensationChangeBack != null && compensationChangeBack.startsWith("cover_img_y")) {
//            policy = getString(R.string.rigor);
//        } else if (compensationChangeBack != null && compensationChangeBack.startsWith("cover_img_z")) {
//            policy = getString(R.string.middling);
//        } else {
//            policy = getString(R.string.unsubscribe);
//        }
//        return policy;
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        calendarControlBouncedDialog = null;
        compensationChangeBackDialog = null;
        costsThatDialog = null;
    }
}
