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
import com.kymjs.common.StringUtils;
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
import com.sillykid.app.mine.myorder.CharterOrderFragment;
import com.sillykid.app.mine.myorder.MyOrderActivity;

import java.util.List;

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
    @BindView(id = R.id.ll_commonError, click = true)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.img_err)
    private ImageView img_err;
    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    private CharterOrderBean charterOrderBean;
    private List<ListBean> databean;

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
    private CharterOrderFragment charterOrderFragment;
    private int finishPosition = 0;//确认订单结束按钮的位置
    private PublicPromptDialog publicPromptDialog;
    private FragmentJumpBetween fragmentJumpBetween;
    private int dotype = 0;//0:没有操作；1:删除订单操作；2：确认订单完成状态
    private String successwords = "";//删除操作成功之后，刷新列表的提示
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
        ((CharterOrderContract.Presenter) mPresenter).toDetails(aty, databean.get(i).getAir_id());
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(successwords + getString(R.string.dataLoad));
        successwords = "";
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
        ((CharterOrderContract.Presenter) mPresenter).getChartOrder(StringNewConstants.All, mMorePageNumber);
        return true;
    }

    @Override
    public void setPresenter(CharterOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 2) {//确认结束订单
            dotype = 0;
            dismissLoadingDialog();
            ((CharterOrderContract.Presenter) mPresenter).toDetails(aty, databean.get(finishPosition).getAir_id());
        } else if (flag == 3) {
            charterOrderAngleBean = (CharterOrderAngleBean) JsonUtil.getInstance().json2Obj(success, CharterOrderAngleBean.class);
            ((CharterOrderContract.Presenter) mPresenter).getChartOrder(StringNewConstants.All, mMorePageNumber);
        } else if (flag == 4) {//删除订单成功
            ViewInject.toast(getString(R.string.deleteFinish));
            successwords = getString(R.string.deleteFinish) + getString(R.string.refreshDataWords);
            mRefreshLayout.beginRefreshing();
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
            return;
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
        if (charterOrderFragment.getChageIcon() == 4) {
            mRefreshLayout.beginRefreshing();
        }
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        switch (childView.getId()) {
//            case R.id.tv_leftbtn:
//                switch (databean.get(position).getStatusX()) {
//                    case NumericConstants.Close:
//                    case NumericConstants.NoPay:
//                        //删除订单
//                        finishPosition = position;
//                        dotype = 1;
//                        initDialog();
//                        break;
//                    case NumericConstants.OnGoing:
//                        ((CharterOrderContract.Presenter) mPresenter).CallPhone(aty, mAdapter.getItem(position).getDrv_phone());
//                        break;
//                }
//                break;
//            case R.id.tv_rightbtn:
//                switch (databean.get(position).getStatusX()) {
//                    case NumericConstants.NoPay:
//                        ((CharterOrderContract.Presenter) mPresenter).toPay(aty, databean.get(position).getAir_id(), databean.get(position).getReal_price(), databean.get(position).getReal_price_fmt());
//                        break;
//                    case NumericConstants.OnGoing:
//                        ((CharterOrderContract.Presenter) mPresenter).toChart(aty, databean.get(position).getHx_user_name(), databean.get(position).getNickname(), databean.get(position).getDrv_phone(), databean.get(position).getAvatar());
//                        break;
//                    case NumericConstants.Completed:
//                        if (StringUtils.toInt(databean.get(position).getUser_order_status(), 0) == 0) {
//                            ((CharterOrderContract.Presenter) mPresenter).toEvaluate(aty, databean.get(position).getAir_id(), databean.get(position).getType(), databean.get(position).getLine_id(), databean.get(position).getSeller_id());
//                        } else {
//                            ((CharterOrderContract.Presenter) mPresenter).toSeeEvaluate(aty, databean.get(position).getAir_id());
//                        }
//                        break;
//                    case NumericConstants.CompletedInDeatil:
////                        ViewInject.toast("尚未开发，敬请期待！");
//                        ((CharterOrderContract.Presenter) mPresenter).toSeeEvaluate(aty, databean.get(position).getAir_id());
//                        break;
//                }
//                break;
//            case R.id.tv_rightbtn2:
//                //确认完成
//                finishPosition = position;
//                dotype = 2;
//                initDialog();
//                break;
        }
    }

    private void initDialog() {
        if (publicPromptDialog == null) {
            publicPromptDialog = new PublicPromptDialog(aty) {
                @Override
                public void doAction() {
                    showLoadingDialog(getString(R.string.submissionLoad));
                    if (dotype == 1) {
                        ((CharterOrderContract.Presenter) mPresenter).delPackOrder(mAdapter.getItem(finishPosition).getAir_id());
                    } else if (dotype == 2) {
                        ((CharterOrderContract.Presenter) mPresenter).orderConfirmCompleted(aty, databean.get(finishPosition).getAir_id(), 2);
                    }
                }
            };
        }
        publicPromptDialog.show();
        if (dotype == 1) {
            publicPromptDialog.setContent(getString(R.string.orderDeletePrompt));
            publicPromptDialog.setContentSmallShow(false);
            publicPromptDialog.setBtnContent(getString(R.string.delete));
        }
        if (dotype == 2) {
            publicPromptDialog.setContent(getString(R.string.orderFinishPrompt));
            publicPromptDialog.setContentSmallShow(true);
            publicPromptDialog.setBtnContent(getString(R.string.confirm));
        }
    }
}
