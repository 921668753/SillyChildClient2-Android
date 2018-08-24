package com.sillykid.app.mine.myorder.charterorder;

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
import com.sillykid.app.adapter.mine.myorder.charterorder.CharterOrderAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.mine.myorder.charterorder.CharterOrderBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myorder.MyOrderActivity;
import com.sillykid.app.mine.myorder.charterorder.orderdetails.AirportDropOffOrderDetailsActivity;
import com.sillykid.app.mine.myorder.charterorder.orderdetails.AirportPickupOrderDetailsActivity;
import com.sillykid.app.mine.myorder.charterorder.orderdetails.CharterOrderDetailsActivity;
import com.sillykid.app.mine.myorder.charterorder.orderdetails.PrivateCustomOrderDetailsActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 我的订单----包车订单---全部
 * Created by Administrator on 2017/9/2.
 */
public class AllCharterFragment extends BaseFragment implements AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, CharterOrderContract.View, BGAOnItemChildClickListener {

    private MyOrderActivity aty;

    private CharterOrderAdapter mAdapter;

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
     * 总页码
     */
    private int totalPageNumber = NumericConstants.START_PAGE_NUMBER;
    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;

    private String status = "";

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyOrderActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_obligationcharter, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new CharterOrderPresenter(this);
        mAdapter = new CharterOrderAdapter(aty);
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
    public void onResume() {
        super.onResume();
//        if (charterOrderFragment.getChageIcon() == 4) {
//            mRefreshLayout.beginRefreshing();
//        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();
        if (mAdapter.getItem(i).getProduct_set_cd() == 1) {
            intent.setClass(aty, AirportPickupOrderDetailsActivity.class);
        } else if (mAdapter.getItem(i).getProduct_set_cd() == 2) {
            intent.setClass(aty, AirportDropOffOrderDetailsActivity.class);
        } else if (mAdapter.getItem(i).getProduct_set_cd() == 3) {
            intent.setClass(aty, CharterOrderDetailsActivity.class);
        } else if (mAdapter.getItem(i).getProduct_set_cd() == 4) {
            //  intent.setClass(aty, AirportPickupOrderDetailsActivity.class);
        } else if (mAdapter.getItem(i).getProduct_set_cd() == 5) {
            intent.setClass(aty, PrivateCustomOrderDetailsActivity.class);
        }
        intent.putExtra("order_number", mAdapter.getItem(i).getOrder_number());
        aty.showActivity(aty, intent);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        switch (childView.getId()) {
            case R.id.tv_confirmPayment:

                break;
            case R.id.tv_callUp:

                break;
            case R.id.tv_sendPrivateChat:


                break;
            case R.id.tv_appraiseOrder:


                break;
            case R.id.tv_additionalComments:


                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        showLoadingDialog(getString(R.string.dataLoad));
        ((CharterOrderContract.Presenter) mPresenter).getChartOrder(aty, status, mMorePageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            return false;
        }
        mMorePageNumber++;
        if (mMorePageNumber > totalPageNumber) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        showLoadingDialog(getString(R.string.dataLoad));
        ((CharterOrderContract.Presenter) mPresenter).getChartOrder(aty, status, mMorePageNumber);
        return true;
    }

    @Override
    public void setPresenter(CharterOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            CharterOrderBean charterOrderBean = (CharterOrderBean) JsonUtil.getInstance().json2Obj(success, CharterOrderBean.class);
            if (charterOrderBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    charterOrderBean.getData().getResultX() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    charterOrderBean.getData().getResultX().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.noOrder), 0);
                return;
            } else if (charterOrderBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    charterOrderBean.getData().getResultX() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    charterOrderBean.getData().getResultX().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
                ViewInject.toast(getString(R.string.noMoreData));
                isShowLoadingMore = false;
                dismissLoadingDialog();
                mRefreshLayout.endLoadingMore();
                return;
            }
            mMorePageNumber = charterOrderBean.getData().getCurrentPageNo();
            totalPageNumber = charterOrderBean.getData().getTotalPageCount();
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(charterOrderBean.getData().getResultX());
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(charterOrderBean.getData().getResultX());
            }
            dismissLoadingDialog();


        } else if (flag == 2) {//确认结束订单

        } else if (flag == 3) {
//            charterOrderAngleBean = (CharterOrderAngleBean) JsonUtil.getInstance().json2Obj(success, CharterOrderAngleBean.class);
//            ((CharterOrderContract.Presenter) mPresenter).getChartOrder(StringNewConstants.All, mMorePageNumber);
        } else if (flag == 4) {//删除订单成功


        } else {


        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (flag == 0) {
            dismissLoadingDialog();
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
        } else if (flag == 1) {
            dismissLoadingDialog();
            ViewInject.toast(msg);
            return;
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusLogOutEvent") && mPresenter != null) {
            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
            ((CharterOrderContract.Presenter) mPresenter).getChartOrder(aty, status, mMorePageNumber);
        }
    }

}
