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
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.adapter.CharterOrderAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.constant.StringNewConstants;
import com.sillykid.app.custominterfaces.FragmentJumpBetween;
import com.sillykid.app.dialog.PublicPromptDialog;
import com.sillykid.app.entity.CharterOrderAngleBean;
import com.sillykid.app.entity.CharterOrderBean;
import com.sillykid.app.entity.CharterOrderBean.ResultBean.ListBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myorder.MyOrderActivity;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 我的订单----包车订单---进行中
 * Created by Administrator on 2017/9/2.
 */

public class OngoingCharterFragment extends BaseFragment implements AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, CharterOrderContract.View, BGAOnItemChildClickListener {
    private MyOrderActivity aty;
    private CharterOrderAdapter mAdapter;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.lv_order)
    private ListView lv_order;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError, click = true)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.img_err)
    private ImageView img_err;
    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    private CharterOrderBean charterOrderBean;
    private List<ListBean> databean;
    private CharterOrderFragment charterOrderFragment;


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
    private int finishPosition = 0;//确认订单结束按钮的位置
    private PublicPromptDialog publicPromptDialog;
    private FragmentJumpBetween fragmentJumpBetween;
    private CharterOrderAngleBean charterOrderAngleBean;


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
        mAdapter.setOnItemChildClickListener(this);
        charterOrderFragment = (CharterOrderFragment) getParentFragment();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_order.setAdapter(mAdapter);
        lv_order.setOnItemClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_commonError:
                if (tv_hintText.getText().toString().equals(getString(R.string.login1))) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "id", 0);
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", "0");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", "0");
                    //   PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "getCompanyGuideMessageFragment");
                    Intent intent = new Intent(aty, LoginActivity.class);
                    aty.showActivity(aty, intent);
                    break;
                }
                //  ViewInject.toast("onBGARefreshLayoutBeginRefreshing");
                mRefreshLayout.beginRefreshing();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ((CharterOrderPresenter) mPresenter).toDetails(aty, databean.get(i).getAir_id());
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        ((CharterOrderPresenter) mPresenter).getOrderAround();
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
        ((CharterOrderPresenter) mPresenter).getChartOrder(StringNewConstants.DOING, mMorePageNumber);
        return true;
    }

    @Override
    public void setPresenter(CharterOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 2) {//确认结束订单
            dismissLoadingDialog();
            ((CharterOrderContract.Presenter) mPresenter).toDetails(aty, databean.get(finishPosition).getAir_id());
            return;
        } else if (flag == 3) {
            charterOrderAngleBean = (CharterOrderAngleBean) JsonUtil.getInstance().json2Obj(success, CharterOrderAngleBean.class);
            ((CharterOrderPresenter) mPresenter).getChartOrder(StringNewConstants.DOING, mMorePageNumber);
        } else {
            charterOrderBean = (CharterOrderBean) JsonUtil.getInstance().json2Obj(success, CharterOrderBean.class);
            if (charterOrderBean == null) {
                ll_commonError.setVisibility(View.VISIBLE);
                tv_hintText.setText(getString(R.string.otherError) + getString(R.string.clickRefresh));
                dismissLoadingDialog();
                return;
            }
            if (charterOrderBean.getData().getList() == null || charterOrderBean.getData().getList().size() == 0) {
                ll_commonError.setVisibility(View.VISIBLE);
                tv_hintText.setText(getString(R.string.youNo));
                dismissLoadingDialog();
                return;
            }
            isShowLoadingMore = true;
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            databean = charterOrderBean.getData().getList();
            totalPageNumber = charterOrderBean.getData().getTotalPages();
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(databean);
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(databean);
            }
            databean = mAdapter.getData();
            dismissLoadingDialog();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (flag == 1) {
            dismissLoadingDialog();
            ViewInject.toast(msg);
        } else if (flag == 4) {
            isShowLoadingMore = false;
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
            }
            dismissLoadingDialog();
            if (isLogin(msg)) {
                ViewInject.toast(getString(R.string.reloginPrompting));
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
                aty.showActivity(aty, LoginActivity.class);
                return;
            }
            ViewInject.toast(msg);
        } else {
            isShowLoadingMore = false;
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
            }
            if (isLogin(msg)) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
                dismissLoadingDialog();
                tv_hintText.setText(getString(R.string.login1));
                //       aty.showActivity(aty, LoginActivity.class);
                return;
            }
            tv_hintText.setText(msg);
            dismissLoadingDialog();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (charterOrderFragment.getChageIcon() == 1) {
            mRefreshLayout.beginRefreshing();
        }
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        switch (childView.getId()) {
            case R.id.tv_leftbtn:
                ((CharterOrderPresenter) mPresenter).CallPhone(aty, databean.get(position).getDrv_phone());
                break;
            case R.id.tv_rightbtn:
                ((CharterOrderPresenter) mPresenter).toChart(aty, databean.get(position).getHx_user_name(), databean.get(position).getNickname(), databean.get(position).getDrv_phone(), databean.get(position).getAvatar());
                break;
            case R.id.tv_rightbtn2:
                //确认完成
                finishPosition = position;
                initDialog();
                break;
        }
    }

    private void initDialog() {
        if (publicPromptDialog == null) {
            publicPromptDialog = new PublicPromptDialog(aty) {
                @Override
                public void doAction() {
                    showLoadingDialog(getString(R.string.submissionLoad));
                    ((CharterOrderContract.Presenter) mPresenter).orderConfirmCompleted(aty, databean.get(finishPosition).getAir_id(), 2);
                }
            };
        }
        publicPromptDialog.show();
        publicPromptDialog.setContent(getString(R.string.orderFinishPrompt));
        publicPromptDialog.setContentSmallShow(true);
        publicPromptDialog.setBtnContent(getString(R.string.confirm));
    }

    public FragmentJumpBetween getFragmentJumpBetween() {
        return fragmentJumpBetween;
    }

}

