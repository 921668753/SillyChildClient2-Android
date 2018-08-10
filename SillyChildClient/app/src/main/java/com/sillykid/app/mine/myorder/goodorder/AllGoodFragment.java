package com.sillykid.app.mine.myorder.goodorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.sillykid.app.R;
import com.sillykid.app.adapter.mine.myorder.GoodsOrderViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.mine.myorder.GoodOrderBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myorder.MyOrderActivity;
import com.sillykid.app.mine.myorder.goodorder.ordertracking.OrderTrackingActivity;
import com.sillykid.app.mine.myorder.goodorder.dialog.OrderBouncedDialog;
import com.sillykid.app.mine.myorder.goodorder.orderdetails.GoodOrderDetailsActivity;
import com.sillykid.app.mine.myorder.goodorder.orderevaluation.PublishedeEvaluationActivity;
import com.sillykid.app.mine.myshoppingcart.makesureorder.PaymentOrderActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 我的订单----商品订单---全部
 * Created by Administrator on 2017/9/2.
 */

public class AllGoodFragment extends BaseFragment implements AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, GoodOrderContract.View, BGAOnItemChildClickListener {

    private MyOrderActivity aty;

    private GoodsOrderViewAdapter mAdapter;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.lv_order)
    private ListView lv_order;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    @BindView(id = R.id.tv_button, click = true)
    private TextView tv_button;

    /**
     * 当前页码
     */
    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;

    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;

    /**
     * 订单状态
     */
    private String status = null;


    private OrderBouncedDialog orderBouncedDialog = null;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyOrderActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_allgood, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new GoodOrderPresenter(this);
        mAdapter = new GoodsOrderViewAdapter(aty);
        initDialog();
    }

    private void initDialog() {
        orderBouncedDialog = new OrderBouncedDialog(aty, "") {
            @Override
            public void toDialogDo(int id, int flag) {
                if (flag == 0) {
                    showLoadingDialog(getString(R.string.cancelLoad));
                    ((GoodOrderContract.Presenter) mPresenter).postOrderCancel(aty, id);
                } else if (flag == 1) {
                    showLoadingDialog(getString(R.string.submissionLoad));
                    ((GoodOrderContract.Presenter) mPresenter).postOrderRemind(aty, id);
                } else if (flag == 2) {
                    showLoadingDialog(getString(R.string.submissionLoad));
                    ((GoodOrderContract.Presenter) mPresenter).postOrderConfirm(aty, id);
                }
            }
        };
    }


    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_order.setAdapter(mAdapter);
        lv_order.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    mRefreshLayout.beginRefreshing();
                    return;
                }
                aty.showActivity(aty, LoginActivity.class);
                break;
        }
    }

    @Override
    public void onChange() {
        super.onChange();
        mRefreshLayout.beginRefreshing();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((GoodOrderContract.Presenter) mPresenter).getOrderList(aty, status, mMorePageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        mMorePageNumber++;
        showLoadingDialog(getString(R.string.dataLoad));
        ((GoodOrderContract.Presenter) mPresenter).getOrderList(aty, status, mMorePageNumber);
        return true;
    }

    @Override
    public void setPresenter(GoodOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(aty, GoodOrderDetailsActivity.class);
        intent.putExtra("order_id", mAdapter.getItem(position).getOrderId());
        aty.showActivity(aty, intent);
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            GoodOrderBean goodOrderBean = (GoodOrderBean) JsonUtil.getInstance().json2Obj(success, GoodOrderBean.class);
            if (goodOrderBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    goodOrderBean.getData().getResultX().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.noOrder), 0);
                return;
            } else if (goodOrderBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    goodOrderBean.getData().getResultX().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
                ViewInject.toast(getString(R.string.noMoreData));
                isShowLoadingMore = false;
                dismissLoadingDialog();
                mRefreshLayout.endLoadingMore();
                return;
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(goodOrderBean.getData().getResultX());
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(goodOrderBean.getData().getResultX());
            }
        } else if (flag == 1 || flag == 2 || flag == 4) {
            mRefreshLayout.beginRefreshing();
        } else if (flag == 3) {
            //    ResultBean resultBean = (ResultBean) JsonUtil.getInstance().json2Obj(success, ResultBean.class);
            // String balance = PreferenceHelper.readString(aty, StringConstants.FILENAME, "balance");
            Intent intent = new Intent(aty, PaymentOrderActivity.class);
            intent.putExtra("order_id", success);
//            intent.putExtra("last_time", String.valueOf(StringUtils.toLong(resultBean.getLast_time()) - StringUtils.toLong(resultBean.getSystem_time())));
            //      intent.putExtra("money", MathUtil.keepTwo(StringUtils.toDouble(resultBean.getPaymoney())));
//            intent.putExtra("balance", MathUtil.keepTwo(StringUtils.toDouble(balance)));
            aty.showActivity(aty, intent);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            isShowLoadingMore = false;
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
            }
            mRefreshLayout.setPullDownRefreshEnable(false);
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setVisibility(View.VISIBLE);
            tv_button.setVisibility(View.VISIBLE);
            if (isLogin(msg)) {
                img_err.setImageResource(R.mipmap.no_login);
                tv_hintText.setVisibility(View.GONE);
                tv_button.setText(getString(R.string.login));
                // ViewInject.toast(getString(R.string.reloginPrompting));
                aty.showActivity(aty, LoginActivity.class);
                return;
            } else if (msg.contains(getString(R.string.checkNetwork))) {
                img_err.setImageResource(R.mipmap.no_network);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            } else if (msg.contains(getString(R.string.noOrder))) {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setVisibility(View.GONE);
            } else {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            }
        } else if (flag == 1 || flag == 2 || flag == 3 || flag == 4) {
            if (isLogin(msg)) {
                aty.showActivity(aty, LoginActivity.class);
                return;
            }
            ViewInject.toast(msg);
        }
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.tv_cancelOrder) {
            if (orderBouncedDialog == null) {
                initDialog();
            }
            if (orderBouncedDialog != null && !orderBouncedDialog.isShowing()) {
                orderBouncedDialog.show();
                orderBouncedDialog.setIdContentFlag(mAdapter.getItem(position).getOrderId(), getString(R.string.confirmCancellationOrder), 0);
            }
        } else if (childView.getId() == R.id.tv_payment) {
            //  ((GoodOrderContract.Presenter) mPresenter).getMyWallet(aty, mAdapter.getItem(position).getOrderId());
            getSuccess(String.valueOf(mAdapter.getItem(position).getOrderId()), 3);
        } else if (childView.getId() == R.id.tv_remindDelivery) {
            if (orderBouncedDialog == null) {
                initDialog();
            }
            if (orderBouncedDialog != null && !orderBouncedDialog.isShowing()) {
                orderBouncedDialog.show();
                orderBouncedDialog.setIdContentFlag(mAdapter.getItem(position).getOrderId(), getString(R.string.confirmReminderDelivery), 1);
            }
        } else if (childView.getId() == R.id.tv_checkLogistics) {
            Intent checkLogisticsIntent = new Intent(aty, OrderTrackingActivity.class);
            checkLogisticsIntent.putExtra("sn", mAdapter.getItem(position).getSn());
            aty.showActivity(aty, checkLogisticsIntent);
        } else if (childView.getId() == R.id.tv_confirmReceipt) {
            if (orderBouncedDialog == null) {
                initDialog();
            }
            if (orderBouncedDialog != null && !orderBouncedDialog.isShowing()) {
                orderBouncedDialog.show();
                orderBouncedDialog.setIdContentFlag(mAdapter.getItem(position).getOrderId(), getString(R.string.confirmReceiptGoods), 2);
            }
        } else if (childView.getId() == R.id.tv_appraiseOrder) {
            Intent publishedeEvaluationIntent = new Intent(aty, PublishedeEvaluationActivity.class);
            publishedeEvaluationIntent.putExtra("order_id", mAdapter.getItem(position).getOrderId());
            aty.showActivity(aty, publishedeEvaluationIntent);
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusPublishedeEvaluationEvent") && mPresenter != null
                || ((String) msgEvent.getData()).equals("RxBusApplyAfterSalesEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusLogOutEvent") && mPresenter != null ||
                ((String) msgEvent.getData()).equals("RxBusWaitGoodsGoodEvent") && mPresenter != null) {
            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
            ((GoodOrderContract.Presenter) mPresenter).getOrderList(aty, status, mMorePageNumber);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (orderBouncedDialog != null) {
            orderBouncedDialog.cancel();
        }
        orderBouncedDialog = null;
    }
}
