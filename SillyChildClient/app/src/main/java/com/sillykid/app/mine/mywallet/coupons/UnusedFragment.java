package com.sillykid.app.mine.mywallet.coupons;

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
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.mine.mywallet.coupons.UnusedCouponsViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.mine.mywallet.coupons.UnusedCouponsBean;
import com.sillykid.app.entity.mine.mywallet.coupons.UseAbleCouponBean;
import com.sillykid.app.entity.mine.mywallet.coupons.UnusedCouponsBean.DataBean.ResultBean;
import com.sillykid.app.loginregister.LoginActivity;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;

/**
 * 优惠券中的未使用
 * Created by Administrator on 2017/9/2.
 */

public class UnusedFragment extends BaseFragment implements CouponsContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, AdapterView.OnItemClickListener {

    private CouponsActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.lv_coupons)
    private ListView lv_coupons;

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

    @BindView(id = R.id.tv_couponCentre, click = true)
    private TextView tv_couponCentre;

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

    private UnusedCouponsViewAdapter couponsAdapter;
    private int type = 0;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (CouponsActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_unused, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new CouponsPresenter(this);
        couponsAdapter = new UnusedCouponsViewAdapter(aty, 1);
        type = aty.getIntent().getIntExtra("type", 0);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        if (type == -1) {
            RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        } else {
            RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        }
        lv_coupons.setAdapter(couponsAdapter);
        lv_coupons.setOnItemClickListener(this);
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
            case R.id.tv_couponCentre:
                ((CouponsContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ResultBean resultBean = couponsAdapter.getItem(i);
        String money = aty.getIntent().getStringExtra("money");
        if (StringUtils.toDouble(money) < StringUtils.toDouble(resultBean.getFull_amount())) {
            ViewInject.toast(getString(R.string.notConformUsageRules));
            return;
        }
        Intent intent = new Intent();
        // 获取内容
        intent.putExtra("money", resultBean.getDenomination());
        intent.putExtra("id", resultBean.getCoupon_id());
        // 设置结果 结果码，一个数据
        aty.setResult(RESULT_OK, intent);
        // 结束该activity 结束之后，前面的activity才可以处理结果
        aty.finish();
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        if (type == -1) {
        ((CouponsContract.Presenter) mPresenter).getUseAbleCoupon(aty, aty.getIntent().getIntExtra("business_id", 6));
            return;
        }
        ((CouponsContract.Presenter) mPresenter).getMemberUnusedCoupon(aty, mMorePageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (type == -1) {
            return false;
        }
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        mMorePageNumber++;
        if (mMorePageNumber > totalPageNumber) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        showLoadingDialog(getString(R.string.dataLoad));
        ((CouponsContract.Presenter) mPresenter).getMemberUnusedCoupon(aty, mMorePageNumber);
        return true;
    }


    @Override
    public void setPresenter(CouponsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            UnusedCouponsBean couponsBean = (UnusedCouponsBean) JsonUtil.getInstance().json2Obj(success, UnusedCouponsBean.class);
            if (couponsBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    couponsBean.getData().getResultX().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.unusedCoupons), 0);
                return;
            } else if (couponsBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    couponsBean.getData().getResultX().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
                ViewInject.toast(getString(R.string.noMoreData));
                isShowLoadingMore = false;
                dismissLoadingDialog();
                mRefreshLayout.endLoadingMore();
                return;
            }
            mMorePageNumber = couponsBean.getData().getCurrentPageNo();
            totalPageNumber = couponsBean.getData().getTotalPageCount();
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                couponsAdapter.clear();
                couponsAdapter.addNewData(couponsBean.getData().getResultX());
            } else {
                mRefreshLayout.endLoadingMore();
                couponsAdapter.addMoreData(couponsBean.getData().getResultX());
            }
        } else if (flag == 1) {
            aty.showActivity(aty, CouponRedemptionCentreActivity.class);
        } else if (flag == 2) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            UseAbleCouponBean useAbleCouponBean = (UseAbleCouponBean) JsonUtil.getInstance().json2Obj(success, UseAbleCouponBean.class);
            if (useAbleCouponBean.getData() == null || useAbleCouponBean.getData().size() <= 0) {
                errorMsg(getString(R.string.unusedCoupons), 0);
                return;
            }
            couponsAdapter.clear();
            couponsAdapter.addNewData(useAbleCouponBean.getData());
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
            } else if (msg.contains(getString(R.string.unusedCoupons))) {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setVisibility(View.GONE);
            } else {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            }
        } else if (flag == 1) {
            if (isLogin(msg)) {
                aty.showActivity(aty, LoginActivity.class);
                return;
            }
            ViewInject.toast(msg);
        } else if (flag == 2) {
            mRefreshLayout.setPullDownRefreshEnable(false);
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setVisibility(View.VISIBLE);
            tv_button.setVisibility(View.VISIBLE);
            if (isLogin(msg)) {
                img_err.setImageResource(R.mipmap.no_login);
                tv_hintText.setVisibility(View.GONE);
                tv_button.setText(getString(R.string.login));
                aty.showActivity(aty, LoginActivity.class);
                return;
            } else if (msg.contains(getString(R.string.checkNetwork))) {
                img_err.setImageResource(R.mipmap.no_network);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            } else if (msg.contains(getString(R.string.unusedCoupons))) {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setVisibility(View.GONE);
            } else {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            }
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusLogOutEvent") && mPresenter != null
                || ((String) msgEvent.getData()).equals("RxBusCouponRedemptionCentreEvent") && mPresenter != null) {
            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
            if (type == -1) {
                ((CouponsContract.Presenter) mPresenter).getUseAbleCoupon(aty, aty.getIntent().getIntExtra("business_id", 6));
                return;
            }
            ((CouponsContract.Presenter) mPresenter).getMemberUnusedCoupon(aty, mMorePageNumber);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        couponsAdapter.clear();
        couponsAdapter = null;
    }

}
