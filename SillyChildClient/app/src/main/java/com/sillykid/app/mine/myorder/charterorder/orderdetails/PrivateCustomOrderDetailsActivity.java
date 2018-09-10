package com.sillykid.app.mine.myorder.charterorder.orderdetails;

import android.Manifest;
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
import com.sillykid.app.entity.mine.myorder.charterorder.orderdetails.PrivateCustomOrderDetailsBean;
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
 * 私人定制订单详情
 */
public class PrivateCustomOrderDetailsActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, CharterOrderDetailsContract.View {

    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;

    @BindView(id = R.id.tv_serviceDate)
    private TextView tv_serviceDate;

    @BindView(id = R.id.tv_destinations)
    private TextView tv_destinations;

    @BindView(id = R.id.tv_passengersNumber)
    private TextView tv_passengersNumber;

    @BindView(id = R.id.tv_bags)
    private TextView tv_bags;

    @BindView(id = R.id.tv_travelPreferences)
    private TextView tv_travelPreferences;

    @BindView(id = R.id.tv_foodPreferences)
    private TextView tv_foodPreferences;

    @BindView(id = R.id.tv_accommodationPreferences)
    private TextView tv_accommodationPreferences;

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

    private PrivateCustomOrderDetailsBean privateCustomOrderDetailsBean;
    private int service_director_state = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_privatecustomorderdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new CharterOrderDetailsPresenter(this);
        order_number = getIntent().getStringExtra("order_number");
        service_director_state = getIntent().getIntExtra("service_director_state", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((CharterOrderDetailsContract.Presenter) mPresenter).getCustomizeOrderDetail(order_number);
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
                intent2.putExtra("order_number", order_number);
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
                servicePhoneDialog.setPhone(privateCustomOrderDetailsBean.getData().getPhone());
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

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            privateCustomOrderDetailsBean = (PrivateCustomOrderDetailsBean) JsonUtil.getInstance().json2Obj(success, PrivateCustomOrderDetailsBean.class);
            tv_orderNumber.setText(privateCustomOrderDetailsBean.getData().getOrder_number());
            tv_serviceDate.setText(DataUtil.formatData(StringUtils.toLong(privateCustomOrderDetailsBean.getData().getTravel_time()), "yyyy-MM-dd"));
            tv_destinations.setText(privateCustomOrderDetailsBean.getData().getDestination());
//            tv_passengersNumber.setText(privateCustomOrderDetailsBean.getData().getAudit_number() + getString(R.string.adult) + "   " + privateCustomOrderDetailsBean.getData().getChildren_number() + getString(R.string.children));
//            tv_bags.setText(privateCustomOrderDetailsBean.getData().getBaggage_number() + getString(R.string.bags2));
            tv_travelPreferences.setText(privateCustomOrderDetailsBean.getData().getTravel_preference());
            tv_foodPreferences.setText(privateCustomOrderDetailsBean.getData().getRepast_preference());
            tv_accommodationPreferences.setText(privateCustomOrderDetailsBean.getData().getStay_preference());
            tv_userNote.setText(privateCustomOrderDetailsBean.getData().getRemark());
            tv_orderPriceNoSign.setText(getString(R.string.renminbi) + privateCustomOrderDetailsBean.getData().getOrder_amount());
            tv_vouchers.setText(getString(R.string.renminbi) + privateCustomOrderDetailsBean.getData().getDis_amount());
            tv_actualPayment.setText(getString(R.string.renminbi) + privateCustomOrderDetailsBean.getData().getPay_amount());
            tv_actualPayment1.setText(getString(R.string.renminbi) + privateCustomOrderDetailsBean.getData().getPay_amount());
            if (privateCustomOrderDetailsBean.getData().getStatus() == 0) {
                ll_actualPayment.setVisibility(View.GONE);
                ll_confirmPayment.setVisibility(View.VISIBLE);
                tv_callUp.setVisibility(View.GONE);
                tv_sendPrivateChat.setVisibility(View.GONE);
                tv_appraiseOrder.setVisibility(View.GONE);
                tv_additionalComments.setVisibility(View.GONE);
            } else if (privateCustomOrderDetailsBean.getData().getStatus() == 1 && service_director_state == 0) {
                ll_actualPayment.setVisibility(View.VISIBLE);
                ll_confirmPayment.setVisibility(View.GONE);
                tv_callUp.setVisibility(View.GONE);
                tv_sendPrivateChat.setVisibility(View.GONE);
                tv_appraiseOrder.setVisibility(View.GONE);
                tv_additionalComments.setVisibility(View.GONE);
            } else if (privateCustomOrderDetailsBean.getData().getStatus() == 1 && service_director_state == 1) {
                ll_actualPayment.setVisibility(View.VISIBLE);
                ll_confirmPayment.setVisibility(View.GONE);
                tv_callUp.setVisibility(View.VISIBLE);
                tv_sendPrivateChat.setVisibility(View.VISIBLE);
                tv_appraiseOrder.setVisibility(View.GONE);
                tv_additionalComments.setVisibility(View.GONE);
            } else if (privateCustomOrderDetailsBean.getData().getStatus() == 2) {
                ll_actualPayment.setVisibility(View.VISIBLE);
                ll_confirmPayment.setVisibility(View.GONE);
                tv_callUp.setVisibility(View.GONE);
                tv_sendPrivateChat.setVisibility(View.GONE);
                tv_appraiseOrder.setVisibility(View.VISIBLE);
                tv_additionalComments.setVisibility(View.GONE);
            } else if (privateCustomOrderDetailsBean.getData().getStatus() == 3) {
                ll_actualPayment.setVisibility(View.VISIBLE);
                ll_confirmPayment.setVisibility(View.GONE);
                tv_callUp.setVisibility(View.GONE);
                tv_sendPrivateChat.setVisibility(View.GONE);
                tv_appraiseOrder.setVisibility(View.GONE);
                tv_additionalComments.setVisibility(View.GONE);
            } else if (privateCustomOrderDetailsBean.getData().getStatus() == 4) {
                ll_actualPayment.setVisibility(View.VISIBLE);
                ll_confirmPayment.setVisibility(View.GONE);
                tv_callUp.setVisibility(View.GONE);
                tv_sendPrivateChat.setVisibility(View.GONE);
                tv_appraiseOrder.setVisibility(View.GONE);
                tv_additionalComments.setVisibility(View.GONE);
            }
        } else if (flag == 1) {//确认结束订单
            Intent intent = new Intent(aty, PaymentTravelOrderActivity.class);
            intent.putExtra("order_id", privateCustomOrderDetailsBean);
            intent.putExtra("order_number", order_number);
            intent.putExtra("pay_amount", privateCustomOrderDetailsBean.getData().getPay_amount());
            intent.putExtra("type", privateCustomOrderDetailsBean.getData().getCategory());
            intent.putExtra("start_time", privateCustomOrderDetailsBean.getData().getStart_time());
            intent.putExtra("end_time", privateCustomOrderDetailsBean.getData().getEnd_time());
            showActivity(aty, intent);
        } else if (flag == 2) {
            RongIMUtil.connectRongIM(aty);
            RongIM.getInstance().startConversation(aty, Conversation.ConversationType.PRIVATE, privateCustomOrderDetailsBean.getData().getRong_id(), privateCustomOrderDetailsBean.getData().getService_director());
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
            ((CharterOrderDetailsContract.Presenter) mPresenter).getCustomizeOrderDetail(order_number);
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
