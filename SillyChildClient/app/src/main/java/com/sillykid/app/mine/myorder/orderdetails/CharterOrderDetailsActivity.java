package com.sillykid.app.mine.myorder.orderdetails;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.dialog.PublicPromptDialog;
import com.sillykid.app.dialog.chartercustom.CompensationChangeBackDialog;
import com.sillykid.app.dialog.chartercustom.CostsThatDialog;
import com.sillykid.app.entity.CharterOrderDetailBean;
import com.sillykid.app.entity.CharterOrderDetailBean.ResultBean;
import com.sillykid.app.entity.UnsubscribeCostBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.DataUtil;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 我的订单---包车订单详情
 * Created by Administrator on 2017/9/2.
 */

public class CharterOrderDetailsActivity extends BaseActivity implements CharterOrderDetailsContract.View {

    private CharterOrderDetailsContract.Presenter mPresenter;

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    private String airid;

    @BindView(id = R.id.tv_orderid)
    private TextView tv_orderid;

    @BindView(id = R.id.tv_status)
    private TextView tv_status;

    @BindView(id = R.id.ll_flycode)
    private LinearLayout ll_flycode;
    @BindView(id = R.id.tv_flycode)
    private TextView tv_flycode;

    @BindView(id = R.id.tv_date)
    private TextView tv_date;

    @BindView(id = R.id.ll_twoaddress)
    private LinearLayout ll_twoaddress;
    @BindView(id = R.id.tv_startpoint)
    private TextView tv_startpoint;
    @BindView(id = R.id.tv_endpoint)
    private TextView tv_endpoint;
    @BindView(id = R.id.tv_routdetail, click = true)
    private TextView tv_routdetail;

    @BindView(id = R.id.ll_orderonlyaddress)
    private LinearLayout ll_orderonlyaddress;
    @BindView(id = R.id.iv_orderonlyaddress)
    private ImageView iv_orderonlyaddress;
    @BindView(id = R.id.tv_orderonlyaddress)
    private TextView tv_orderonlyaddress;

//    @BindView(id=R.id.ll_distancehideable)
//    private LinearLayout ll_distancehideable;
//    @BindView(id=R.id.tv_distance)
//    private TextView tv_distance;

    @BindView(id = R.id.ll_up1)
    private LinearLayout ll_up1;
    @BindView(id = R.id.tv_upl1)
    private TextView tv_upl1;
    @BindView(id = R.id.tv_upr1)
    private TextView tv_upr1;

    @BindView(id = R.id.ll_up2)
    private LinearLayout ll_up2;
    @BindView(id = R.id.tv_upl2)
    private TextView tv_upl2;
    @BindView(id = R.id.tv_upr2)
    private TextView tv_upr2;

    @BindView(id = R.id.tv_upr3)
    private TextView tv_upr3;

    @BindView(id = R.id.tv_upl4)
    private TextView tv_upl4;

    @BindView(id = R.id.tv_upr4)
    private TextView tv_upr4;

    @BindView(id = R.id.tv_downl1)
    private TextView tv_downl1;
    @BindView(id = R.id.tv_downr1)
    private TextView tv_downr1;

    @BindView(id = R.id.tv_downl2)
    private TextView tv_downl2;
    @BindView(id = R.id.tv_downr2)
    private TextView tv_downr2;

    @BindView(id = R.id.tv_downl3)
    private TextView tv_downl3;
    @BindView(id = R.id.tv_downr3)
    private TextView tv_downr3;

    @BindView(id = R.id.ll_down4)
    private LinearLayout ll_down4;
    @BindView(id = R.id.tv_downr4)
    private TextView tv_downr4;

    @BindView(id = R.id.ll_costsThat, click = true)
    LinearLayout ll_costsThat;
    @BindView(id = R.id.ll_compensationChangeBack, click = true)
    LinearLayout ll_compensationChangeBack;
    @BindView(id = R.id.tv_compensationChangeBack)
    TextView tv_compensationChangeBack;

    @BindView(id = R.id.tv_orderprice)
    private TextView tv_orderprice;

    @BindView(id = R.id.ll_discount)
    private LinearLayout ll_discount;
    @BindView(id = R.id.tv_discount)
    private TextView tv_discount;

    @BindView(id = R.id.tv_paystatus)
    private TextView tv_paystatus;
    @BindView(id = R.id.tv_payamount)
    private TextView tv_payamount;

    @BindView(id = R.id.v_bottom)
    private View v_bottom;
    @BindView(id = R.id.btn_bottoml, click = true)
    private Button btn_bottoml;
    @BindView(id = R.id.btn_bottomr, click = true)
    private Button btn_bottomr;
    @BindView(id = R.id.btn_bottomr2, click = true)
    private Button btn_bottomr2;
    private ResultBean rbean;
    private PublicPromptDialog publicPromptDialog;
    private int dotype = 0;//0:删除订单操作；1：确认订单完成状态
    private CompensationChangeBackDialog compensationChangeBackDialog;
    private CostsThatDialog costsThatDialog;
    private String costStatement;
    private String costCompensation;

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
        btn_bottoml.setOnClickListener(this);
        btn_bottomr.setOnClickListener(this);

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
        if (rbean != null) {
            switch (v.getId()) {
                case R.id.btn_bottoml:
                    switch (rbean.getStatusX()) {
                        case NumericConstants.Close:
                        case NumericConstants.NoPay:
                            //删除订单
                            dotype = 0;
                            initDialog();
                            break;
                        case NumericConstants.OnGoing:
                            if (rbean.getDrv_phone() == null) {
                                ViewInject.toast(getString(R.string.noPhone));
                            } else {
                                mPresenter.CallPhone(this, (String) (rbean.getDrv_phone()));
                            }
                            break;
                    }
                    break;
                case R.id.btn_bottomr:
                    switch (rbean.getStatusX()) {
                        case NumericConstants.NoPay:
                            mPresenter.toPay(this, rbean.getAir_id() + "", rbean.getReal_price(), rbean.getReal_price_fmt());
                            break;
                        case NumericConstants.OnGoing:
                            mPresenter.toChart(this, rbean.getHx_user_name(), rbean.getNickname(), rbean.getDrv_phone(), rbean.getAvatar());
                            break;
                        case NumericConstants.Completed:
                            if (StringUtils.toInt(rbean.getUser_order_status(), 0) == 0) {
                                mPresenter.toEvaluate(this, rbean.getAir_id() + "", rbean.getType(), rbean.getLine_id(), rbean.getSeller_id());
                            } else {
                                mPresenter.toSeeEvaluate(this, rbean.getAir_id());
                            }
                            break;
                        case NumericConstants.CompletedInDeatil:
//                            ViewInject.toast("尚未开发，敬请期待！");
                            mPresenter.toSeeEvaluate(this, rbean.getAir_id());
                            break;
                    }
                    break;
                case R.id.btn_bottomr2:
                    dotype = 1;
                    initDialog();
                    break;
                case R.id.tv_routdetail:
                    mPresenter.toRouteDetail(this, rbean.getLine_id());
                    break;
                case R.id.ll_costsThat:
//                    if (TextUtils.isEmpty(costStatement)&&rbean.getType()==4){
//                        showLoadingDialog(getString(R.string.dataLoad));
//                        ((CharterOrderDetailsPresenter)mPresenter).articleInfo(0,5);
//                    }else if (TextUtils.isEmpty(costStatement)&&rbean.getType()==5){
//                        showLoadingDialog(getString(R.string.dataLoad));
//                        ((CharterOrderDetailsPresenter)mPresenter).articleInfo(1,5);
//                    }else{
                    costsThatDialog = new CostsThatDialog(this, costStatement);
                    costsThatDialog.setCanceledOnTouchOutside(false);
                    costsThatDialog.show();
//                    }
                    break;
                case R.id.ll_compensationChangeBack:
//                    if (TextUtils.isEmpty(costCompensation)&&rbean.getType()==4){
//                        showLoadingDialog(getString(R.string.dataLoad));
//                        ((CharterOrderDetailsPresenter)mPresenter).articleInfo(0,6);
//                    }else if (TextUtils.isEmpty(costCompensation)&&rbean.getType()==5){
//                        showLoadingDialog(getString(R.string.dataLoad));
//                        ((CharterOrderDetailsPresenter)mPresenter).articleInfo(1,6);
//                    }else{
                    compensationChangeBackDialog = new CompensationChangeBackDialog(this, costCompensation);
                    compensationChangeBackDialog.setCanceledOnTouchOutside(true);
                    compensationChangeBackDialog.show();
//                    }

                    break;
            }
        }
    }

    @Override
    public void setPresenter(CharterOrderDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        switch (flag) {
            case 2://确认结束订单
                showLoadingDialog(getString(R.string.dataLoad));
                mPresenter.getCharterOrderDetails(airid);
                break;
            case 4:
                dismissLoadingDialog();
                ViewInject.toast(getString(R.string.deleteFinish));
                finish();
                break;
            case 5:
                UnsubscribeCostBean unsubscribeCostBean1 = (UnsubscribeCostBean) JsonUtil.getInstance().json2Obj(success, CharterOrderDetailBean.class);
                if (unsubscribeCostBean1 != null && unsubscribeCostBean1.getData() != null && unsubscribeCostBean1.getData().getExplain() != null) {
                    costStatement = unsubscribeCostBean1.getData().getExplain().getContent();
                    costCompensation = unsubscribeCostBean1.getData().getPolicy().getContent();
                }
                dismissLoadingDialog();
                costsThatDialog = new CostsThatDialog(this, costStatement);
                costsThatDialog.setCanceledOnTouchOutside(false);
                costsThatDialog.show();
                break;
            case 6:
                UnsubscribeCostBean unsubscribeCostBean2 = (UnsubscribeCostBean) JsonUtil.getInstance().json2Obj(success, CharterOrderDetailBean.class);
                if (unsubscribeCostBean2 != null && unsubscribeCostBean2.getData() != null && unsubscribeCostBean2.getData().getExplain() != null) {
                    costStatement = unsubscribeCostBean2.getData().getExplain().getContent();
                    costCompensation = unsubscribeCostBean2.getData().getPolicy().getContent();
                }
                dismissLoadingDialog();
                costsThatDialog = new CostsThatDialog(this, costCompensation);
                costsThatDialog.setCanceledOnTouchOutside(false);
                costsThatDialog.show();
                break;
            default:
                CharterOrderDetailBean charterOrderDetailBean = (CharterOrderDetailBean) JsonUtil.getInstance().json2Obj(success, CharterOrderDetailBean.class);
                if (charterOrderDetailBean == null) {
                    errorMsg(getString(R.string.otherError), 0);
                    return;
                }
                rbean = charterOrderDetailBean.getData();
                if (rbean == null) {
                    errorMsg(getString(R.string.serverReturnsDataNull), 0);
                    return;
                }
                fitData();
                dismissLoadingDialog();
                break;
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this, LoginActivity.class);
            finish();
            return;
        }
        ViewInject.toast(msg);
    }

    /**
     * 根据网络请求结果，填充数据
     */
    private void fitData() {
        costStatement = rbean.getCostStatement();
        costCompensation = rbean.getCostCompensation();

        tv_orderid.setText(rbean.getOrder_sn());

        if (!TextUtils.isEmpty(rbean.getCoupon_price_fmt())) {
            ll_discount.setVisibility(View.VISIBLE);
            tv_discount.setText(rbean.getCoupon_price_fmt());
        }
        switch (rbean.getStatusX()) {
            case NumericConstants.NoPay:
                tv_status.setText(getString(R.string.obligation));
                tv_paystatus.setText(getString(R.string.needpay));
                btn_bottoml.setVisibility(View.VISIBLE);
                btn_bottoml.setText(getString(R.string.delete));
                btn_bottomr.setVisibility(View.VISIBLE);
                btn_bottomr.setText(getString(R.string.toPayment));
                btn_bottomr2.setVisibility(View.GONE);
                ll_discount.setVisibility(View.GONE);
                break;
            case NumericConstants.SendOrder://未派单
                tv_status.setText(getString(R.string.sendSingle));
                v_bottom.setVisibility(View.GONE);
                btn_bottoml.setVisibility(View.GONE);
                btn_bottomr.setVisibility(View.GONE);
                btn_bottomr2.setVisibility(View.GONE);
                break;
            case NumericConstants.WaiteOrder://已派单待接单
                tv_status.setText(getString(R.string.sendSingleWaitingList));
                v_bottom.setVisibility(View.GONE);
                btn_bottoml.setVisibility(View.GONE);
                btn_bottomr.setVisibility(View.GONE);
                btn_bottomr2.setVisibility(View.GONE);
                break;
            case NumericConstants.OnGoing://即将开始
            case NumericConstants.WaiteEvaluate://进行中
                btn_bottoml.setVisibility(View.VISIBLE);
                btn_bottoml.setText(getString(R.string.callUp));
                btn_bottomr.setVisibility(View.VISIBLE);
                btn_bottomr.setText(getString(R.string.sendPrivateChat));
                if (StringUtils.toInt(rbean.getUser_confirm()) == 0) {
                    tv_status.setText(getString(R.string.ongoing));
                    btn_bottomr2.setVisibility(View.VISIBLE);
                } else {
                    tv_status.setText(getString(R.string.waitConfing));
                    btn_bottomr2.setVisibility(View.GONE);
                }
                break;
            case NumericConstants.Completed://待评价
                btn_bottoml.setVisibility(View.GONE);
                btn_bottomr.setVisibility(View.VISIBLE);
                btn_bottomr2.setVisibility(View.GONE);
                if (StringUtils.toInt(rbean.getUser_order_status(), 0) == 0) {
                    tv_status.setText(getString(R.string.evaluate));
                    btn_bottomr.setText(getString(R.string.toAppraise));
                } else {
                    tv_status.setText(getString(R.string.guideEvaluate));
                    btn_bottomr.setText(getString(R.string.seeEvaluation));
                }
                break;
            case NumericConstants.CompletedInDeatil://已完成
                tv_status.setText(getString(R.string.completed));
                btn_bottoml.setVisibility(View.GONE);
                btn_bottomr.setVisibility(View.VISIBLE);
                btn_bottomr.setText(getString(R.string.seeEvaluation));
                btn_bottomr2.setVisibility(View.GONE);
                break;
            case NumericConstants.Close://已关闭
                tv_status.setText(getString(R.string.closed));
                btn_bottoml.setVisibility(View.VISIBLE);
                btn_bottoml.setText(getString(R.string.delete));
                btn_bottomr.setVisibility(View.GONE);
                btn_bottomr2.setVisibility(View.GONE);
                break;
            default:
                v_bottom.setVisibility(View.GONE);
                btn_bottoml.setVisibility(View.GONE);
                btn_bottomr.setVisibility(View.GONE);
                btn_bottomr2.setVisibility(View.GONE);
                break;
        }

        tv_date.setText(getString(R.string.serviceStartTime) + DataUtil.formatData(StringUtils.toLong(rbean.getStart_time()), "yyyy-MM-dd E HH:mm"));

//        if (!TextUtils.isEmpty(rbean.getReq_car_seat_num())) {
//            tv_upr1.setText(StringUtils.toInt(rbean.getReq_car_seat_num()) + getString(R.string.peopleseat));
//        }

        if (TextUtils.isEmpty(rbean.getCar_level_name())) {
            ll_up2.setVisibility(View.GONE);
        } else {
            if (TextUtils.isEmpty(rbean.getReq_car_seat_num())) {
                tv_upr2.setText(rbean.getCar_level_name());
            }else{
                tv_upr2.setText(StringUtils.toInt(rbean.getReq_car_seat_num()) + getString(R.string.peopleseat)+rbean.getCar_level_name());
            }


        }

        String boxstr = "";
        if (StringUtils.toLong(rbean.getTwenty_four()) != 0) {
            boxstr += "," + rbean.getTwenty_four() + getString(R.string.piece24);
        }
        if (StringUtils.toLong(rbean.getTwenty_six()) != 0) {
            boxstr += "," + rbean.getTwenty_six() + getString(R.string.piece26);
        }
        if (StringUtils.toLong(rbean.getTwenty_eight()) != 0) {
            boxstr += "," + rbean.getTwenty_eight() + getString(R.string.piece28);
        }
        if (StringUtils.toLong(rbean.getThirty()) != 0) {
            boxstr += "," + rbean.getThirty() + getString(R.string.piece30);
        }
        if (TextUtils.isEmpty(boxstr)) {
            tv_upr3.setText(getString(R.string.noHave));
        } else {
            tv_upr3.setText(boxstr.substring(1));
        }

        boxstr = "";
        if (StringUtils.toLong(rbean.getUse_car_adult()) != 0) {
            boxstr += "," + rbean.getUse_car_adult() + getString(R.string.adult);
        }
        if (StringUtils.toLong(rbean.getUse_car_children()) != 0) {
            boxstr += "," + rbean.getUse_car_children() + getString(R.string.children);
        }
        if (TextUtils.isEmpty(boxstr)) {
            tv_upr4.setText(getString(R.string.noHave));
        } else {
            tv_upr4.setText(boxstr.substring(1));
        }

        if (TextUtils.isEmpty(rbean.getCustomer_name())) {
            tv_downr1.setText(getString(R.string.noHave));
        } else {
            tv_downr1.setText(rbean.getCustomer_name());
        }

        if (TextUtils.isEmpty(rbean.getCustomer_phone())) {
            tv_downr2.setText(getString(R.string.noHave));
        } else {
            tv_downr2.setText(rbean.getCustomer_phone());
        }

        if (TextUtils.isEmpty(rbean.getRemark())) {
            tv_downr3.setText(getString(R.string.noHave));
        } else {
            tv_downr3.setText(rbean.getRemark());
        }
        tv_startpoint.setText(rbean.getWork_address());
        tv_endpoint.setText(rbean.getDest_address());
        switch (rbean.getType()) {
            case 1:
                titlebar.setTitleText(R.string.pickOrder);
//                ll_distancehideable.setVisibility(View.VISIBLE);
                ll_flycode.setVisibility(View.VISIBLE);
                tv_flycode.setText(rbean.getFlt_no());
//                tv_distance.setText(StringUtils.toDouble(rbean.getMile_length())+getString(R.string.KM));
                break;
            case 2:
                titlebar.setTitleText(R.string.sendOrder);
//                ll_distancehideable.setVisibility(View.VISIBLE);
                ll_flycode.setVisibility(View.VISIBLE);
                tv_flycode.setText(rbean.getFlt_no());
//                tv_distance.setText(StringUtils.toDouble(rbean.getMile_length())+getString(R.string.KM));
                break;
            case 3:
                titlebar.setTitleText(R.string.LineOrder);
                tv_routdetail.setVisibility(View.VISIBLE);
                tv_date.setText(getString(R.string.serviceStartTime) + rbean.getWork_at());
                ll_up1.setVisibility(View.VISIBLE);
                tv_upl1.setText(getString(R.string.passportNum));
                tv_upr1.setText(rbean.getUser_passport());
                ll_up2.setVisibility(View.VISIBLE);
                tv_upl2.setText(getString(R.string.idNumber));
                tv_upr2.setText(rbean.getUser_identity());
                if (TextUtils.isEmpty(rbean.getUser_message())) {
                    tv_downr3.setText(getString(R.string.noHave));
                } else {
                    tv_downr3.setText(rbean.getUser_message());
                }
                break;
            case 4:
                titlebar.setTitleText(R.string.singleTransport);
                break;
            case 5:
                titlebar.setTitleText(R.string.privateOrdering);
                ll_up1.setVisibility(View.GONE);
                ll_up2.setVisibility(View.GONE);
                tv_upl4.setText(getString(R.string.touristNumber));
//                ll_twoaddress.setVisibility(View.GONE);
//                ll_orderonlyaddress.setVisibility(View.VISIBLE);
//                tv_orderonlyaddress.setText(rbean.getDest_address());
                tv_downl1.setText(getString(R.string.playNumberDays));
                tv_downr1.setText(StringUtils.toInt(rbean.getOrder_day()) + "");
                tv_downl2.setText(getString(R.string.travelPreferences));
                tv_downr2.setText(rbean.getTour_favorite());
                tv_downl3.setText(getString(R.string.recommendRestaurant));
                tv_downr3.setText(rbean.getEating_ave());
                ll_down4.setVisibility(View.VISIBLE);
                tv_downr4.setText(rbean.getStay_ave());
                break;
            case 6:
                titlebar.setTitleText(R.string.byTheDay);
                if (rbean.getPack_start_time() != null && rbean.getPack_start_time().length > 0) {
                    String times = "";
                    for (String s : rbean.getPack_start_time()) {
                        if (!TextUtils.isEmpty(s) && s.length() > 10) {
                            times += "," + s.substring(0, 10);
                        } else if (!TextUtils.isEmpty(s)) {
                            times += "," + s;
                        }
                    }
                    if (TextUtils.isEmpty(times)) {
                        tv_date.setText(getString(R.string.serviceStartTime) + getString(R.string.noHave));
                    } else {
                        tv_date.setText(getString(R.string.serviceStartTime) + times.substring(1));
                    }

                } else {
                    tv_date.setText(getString(R.string.serviceStartTime) + getString(R.string.noHave));
                }
                break;
        }

        if (TextUtils.isEmpty(rbean.getTotal_price_fmt())) {
//            tv_orderprice.setText("$0.00");
        } else {
            tv_orderprice.setText(rbean.getTotal_price_fmt());
        }

        if (TextUtils.isEmpty(rbean.getReal_price_fmt())) {
//            tv_payamount.setText("$0.00");
        } else {
            tv_payamount.setText(rbean.getReal_price_fmt());
        }

        if (TextUtils.isEmpty(rbean.getCostCompensationLevel())){
            tv_compensationChangeBack.setText(getString(R.string.view));
        }else{
            tv_compensationChangeBack.setText(rbean.getCostCompensationLevel());
        }

    }

    private void initDialog() {
        if (publicPromptDialog == null) {
            publicPromptDialog = new PublicPromptDialog(aty) {
                @Override
                public void doAction() {
                    showLoadingDialog(getString(R.string.submissionLoad));
                    if (dotype == 0) {
                        ((CharterOrderDetailsPresenter) mPresenter).delPackOrder(rbean.getAir_id());
                    } else {
                        ((CharterOrderDetailsPresenter) mPresenter).orderConfirmCompleted(CharterOrderDetailsActivity.this, rbean.getAir_id(), 2);
                    }
                }
            };
        }
        publicPromptDialog.show();
        if (dotype == 0) {
            publicPromptDialog.setContent(getString(R.string.orderDeletePrompt));
            publicPromptDialog.setContentSmallShow(false);
            publicPromptDialog.setBtnContent(getString(R.string.delete));
        } else {
            publicPromptDialog.setContent(getString(R.string.orderFinishPrompt));
            publicPromptDialog.setContentSmallShow(true);
            publicPromptDialog.setBtnContent(getString(R.string.confirm));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showLoadingDialog(getString(R.string.dataLoad));
        mPresenter.getCharterOrderDetails(airid);
    }
}
