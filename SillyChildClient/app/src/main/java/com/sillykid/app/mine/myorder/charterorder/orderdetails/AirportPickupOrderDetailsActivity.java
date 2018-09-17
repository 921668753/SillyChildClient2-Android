package com.sillykid.app.mine.myorder.charterorder.orderdetails;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.mine.myorder.charterorder.orderdetails.AirportPickupOrderDetailsBean;
import com.sillykid.app.homepage.airporttransportation.paymentorder.PaymentTravelOrderActivity;
import com.sillykid.app.homepage.message.interactivemessage.imuitl.RongIMUtil;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myorder.charterorder.dialog.ServicePhoneDialog;
import com.sillykid.app.mine.myorder.charterorder.orderevaluation.AdditionalCommentsActivity;
import com.sillykid.app.mine.myorder.charterorder.orderevaluation.CommentActivity;
import com.sillykid.app.utils.DataUtil;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 接机订单详情
 */
public class AirportPickupOrderDetailsActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, CharterOrderDetailsContract.View {

    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;

    @BindView(id = R.id.tv_serviceDate)
    private TextView tv_serviceDate;

    @BindView(id = R.id.tv_airportPickupLocation)
    private TextView tv_airportPickupLocation;

    @BindView(id = R.id.tv_flightNumberSign)
    private TextView tv_flightNumberSign;

    @BindView(id = R.id.tv_destination)
    private TextView tv_destination;

    @BindView(id = R.id.tv_passengersNumber)
    private TextView tv_passengersNumber;

    @BindView(id = R.id.tv_bags)
    private TextView tv_bags;

    @BindView(id = R.id.tv_userNote)
    private TextView tv_userNote;

    @BindView(id = R.id.tv_orderPriceNoSign)
    private TextView tv_orderPriceNoSign;


    @BindView(id = R.id.tv_vouchers)
    private TextView tv_vouchers;

    @BindView(id = R.id.tv_actualPayment)
    private TextView tv_actualPayment;


    @BindView(id = R.id.ll_actualPayment)
    private LinearLayout ll_actualPayment;
    @BindView(id = R.id.tv_actualPayment1)
    private TextView tv_actualPayment1;


    @BindView(id = R.id.ll_confirmPayment)
    private LinearLayout ll_confirmPayment;

    @BindView(id = R.id.tv_confirmPayment, click = true)
    private TextView tv_confirmPayment;

    @BindView(id = R.id.tv_callUp, click = true)
    private TextView tv_callUp;

    @BindView(id = R.id.tv_sendPrivateChat, click = true)
    private TextView tv_sendPrivateChat;

    @BindView(id = R.id.tv_appraiseOrder, click = true)
    private TextView tv_appraiseOrder;

    @BindView(id = R.id.tv_additionalComments, click = true)
    private TextView tv_additionalComments;

    private ServicePhoneDialog servicePhoneDialog = null;
    private String order_number;
    private int order_id;
    private String phone;
    private AirportPickupOrderDetailsBean airportPickupOrderDetailsBean;
    private int service_director_state = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_airportpickuporderdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new CharterOrderDetailsPresenter(this);
        order_number = getIntent().getStringExtra("order_number");
        service_director_state = getIntent().getIntExtra("service_director_state", 0);
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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.airportPickup), true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_confirmPayment:
                ((CharterOrderDetailsContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
            case R.id.tv_callUp:
                choiceLocationWrapper();
                break;
            case R.id.tv_sendPrivateChat:
                ((CharterOrderDetailsContract.Presenter) mPresenter).getIsLogin(aty, 2);
                break;
            case R.id.tv_appraiseOrder:
                Intent intent1 = new Intent(aty, CommentActivity.class);
                intent1.putExtra("order_number", order_number);
                showActivity(aty, intent1);
                break;
            case R.id.tv_additionalComments:
                Intent intent2 = new Intent(aty, AdditionalCommentsActivity.class);
                intent2.putExtra("order_id", order_id);
//                intent1.putExtra("order_number", bean.getOrder_number());
//                intent1.putExtra("pay_amount", bean.getPay_amount());
//                intent1.putExtra("type", bean.getProduct_set_cd());
//                intent1.putExtra("start_time", bean.getStart_time());
//                intent1.putExtra("end_time", bean.getEnd_time());
                showActivity(aty, intent2);
        }
    }


    @AfterPermissionGranted(NumericConstants.READ_AND_WRITE_CODE)
    private void choiceLocationWrapper() {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            if (servicePhoneDialog == null) {
                servicePhoneDialog = new ServicePhoneDialog(aty);
            }
            if (servicePhoneDialog != null && !servicePhoneDialog.isShowing()) {
                servicePhoneDialog.show();
                servicePhoneDialog.setPhone(phone);
            }
            return;
        }
        EasyPermissions.requestPermissions(this, getString(R.string.callSwitch), NumericConstants.READ_AND_WRITE_CODE, perms);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d("tag", "onPermissionsDenied:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == NumericConstants.READ_AND_WRITE_CODE) {
            ViewInject.toast(getString(R.string.callPermission));
        }
    }


    @Override
    public void setPresenter(CharterOrderDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            airportPickupOrderDetailsBean = (AirportPickupOrderDetailsBean) JsonUtil.getInstance().json2Obj(success, AirportPickupOrderDetailsBean.class);
            tv_orderNumber.setText(airportPickupOrderDetailsBean.getData().getOrder_number());
            tv_serviceDate.setText(DataUtil.formatData(StringUtils.toLong(airportPickupOrderDetailsBean.getData().getService_time()), "yyyy-MM-dd HH:mm"));
            tv_airportPickupLocation.setText(airportPickupOrderDetailsBean.getData().getDeparture());
            tv_flightNumberSign.setText(airportPickupOrderDetailsBean.getData().getFlight_number());
            tv_destination.setText(airportPickupOrderDetailsBean.getData().getDelivery_location());
            tv_passengersNumber.setText(airportPickupOrderDetailsBean.getData().getAudit_number() + getString(R.string.adult) + "   " + airportPickupOrderDetailsBean.getData().getChildren_number() + getString(R.string.children));
            tv_bags.setText(airportPickupOrderDetailsBean.getData().getBaggage_number() + getString(R.string.bags2));
            tv_userNote.setText(airportPickupOrderDetailsBean.getData().getRemarks());
            tv_orderPriceNoSign.setText(getString(R.string.renminbi) + airportPickupOrderDetailsBean.getData().getOrder_amount());
            tv_vouchers.setText(getString(R.string.renminbi) + airportPickupOrderDetailsBean.getData().getDis_amount());
            phone = airportPickupOrderDetailsBean.getData().getPhone();
            order_id = airportPickupOrderDetailsBean.getData().getOrder_id();
            tv_actualPayment.setText(getString(R.string.renminbi) + airportPickupOrderDetailsBean.getData().getPay_amount());
            tv_actualPayment1.setText(getString(R.string.renminbi) + airportPickupOrderDetailsBean.getData().getPay_amount());
            if (airportPickupOrderDetailsBean.getData().getStatus() == 0) {
                ll_actualPayment.setVisibility(View.GONE);
                ll_confirmPayment.setVisibility(View.VISIBLE);
                tv_callUp.setVisibility(View.GONE);
                tv_sendPrivateChat.setVisibility(View.GONE);
                tv_appraiseOrder.setVisibility(View.GONE);
                tv_additionalComments.setVisibility(View.GONE);
            } else if (airportPickupOrderDetailsBean.getData().getStatus() == 1 && service_director_state == 0) {
                ll_actualPayment.setVisibility(View.VISIBLE);
                ll_confirmPayment.setVisibility(View.GONE);
                tv_callUp.setVisibility(View.GONE);
                tv_sendPrivateChat.setVisibility(View.GONE);
                tv_appraiseOrder.setVisibility(View.GONE);
                tv_additionalComments.setVisibility(View.GONE);
            } else if (airportPickupOrderDetailsBean.getData().getStatus() == 1) {
                ll_actualPayment.setVisibility(View.VISIBLE);
                ll_confirmPayment.setVisibility(View.GONE);
                tv_callUp.setVisibility(View.VISIBLE);
                tv_sendPrivateChat.setVisibility(View.VISIBLE);
                tv_appraiseOrder.setVisibility(View.GONE);
                tv_additionalComments.setVisibility(View.GONE);
            } else if (airportPickupOrderDetailsBean.getData().getStatus() == 2) {
                ll_actualPayment.setVisibility(View.VISIBLE);
                ll_confirmPayment.setVisibility(View.GONE);
                tv_callUp.setVisibility(View.GONE);
                tv_sendPrivateChat.setVisibility(View.GONE);
                tv_appraiseOrder.setVisibility(View.VISIBLE);
                tv_additionalComments.setVisibility(View.GONE);
            } else if (airportPickupOrderDetailsBean.getData().getStatus() == 3 || airportPickupOrderDetailsBean.getData().getStatus() == 30) {
                ll_actualPayment.setVisibility(View.VISIBLE);
                ll_confirmPayment.setVisibility(View.GONE);
                tv_callUp.setVisibility(View.GONE);
                tv_sendPrivateChat.setVisibility(View.GONE);
                tv_appraiseOrder.setVisibility(View.GONE);
                tv_additionalComments.setVisibility(View.GONE);
            } else if (airportPickupOrderDetailsBean.getData().getStatus() == 4) {
                ll_actualPayment.setVisibility(View.VISIBLE);
                ll_confirmPayment.setVisibility(View.GONE);
                tv_callUp.setVisibility(View.GONE);
                tv_sendPrivateChat.setVisibility(View.GONE);
                tv_appraiseOrder.setVisibility(View.GONE);
                tv_additionalComments.setVisibility(View.GONE);
            }
        } else if (flag == 1) {//确认结束订单
            Intent intent = new Intent(aty, PaymentTravelOrderActivity.class);
            intent.putExtra("order_id", order_id);
            intent.putExtra("order_number", order_number);
            intent.putExtra("pay_amount", airportPickupOrderDetailsBean.getData().getPay_amount());
            intent.putExtra("type", airportPickupOrderDetailsBean.getData().getCategory());
            intent.putExtra("start_time", airportPickupOrderDetailsBean.getData().getStart_time());
            intent.putExtra("end_time", airportPickupOrderDetailsBean.getData().getEnd_time());
            showActivity(aty, intent);
        } else if (flag == 2) {
            RongIMUtil.connectRongIM(aty);
            RongIM.getInstance().startConversation(aty, Conversation.ConversationType.PRIVATE, airportPickupOrderDetailsBean.getData().getRong_id(), airportPickupOrderDetailsBean.getData().getService_director());
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            skipActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusPayTravelCompleteEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusCharterCommentEvent") && mPresenter != null) {
            ((CharterOrderDetailsContract.Presenter) mPresenter).getCharterOrderDetails(order_number);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (servicePhoneDialog != null && servicePhoneDialog.isShowing()) {
            servicePhoneDialog.cancel();
        }
        servicePhoneDialog = null;
    }
}
