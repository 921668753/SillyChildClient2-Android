package com.sillykid.app.mine.myfocus.user;

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
import com.common.cklibrary.utils.rx.RxBus;
import com.sillykid.app.R;
import com.sillykid.app.adapter.mine.myfocus.UserViewAdapter;
import com.sillykid.app.community.DisplayPageActivity;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.mine.myfocus.UserFocusBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myfocus.FocusContract;
import com.sillykid.app.mine.myfocus.FocusPresenter;
import com.sillykid.app.mine.myfocus.MyFocusActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;
import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

/**
 * 用户
 */
public class UserFragment extends BaseFragment implements FocusContract.View, AdapterView.OnItemClickListener, BGAOnItemChildClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private UserViewAdapter mAdapter;

    @BindView(id = R.id.lv_user)
    private ListView lv_user;

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

    private int positionItem = 0;

    private MyFocusActivity aty;

    private int type_id = 1;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyFocusActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_user, null);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new FocusPresenter(this);
        mAdapter = new UserViewAdapter(aty);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_user.setAdapter(mAdapter);
        lv_user.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
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
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((FocusContract.Presenter) mPresenter).getMyConcernList(mMorePageNumber, type_id);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
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
        ((FocusContract.Presenter) mPresenter).getMyConcernList(mMorePageNumber, type_id);
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(aty, DisplayPageActivity.class);
        // intent.putExtra("user_id", mAdapter.getItem(position).getMember_id());
        intent.putExtra("isRefresh", 1);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        positionItem = position;
        if (childView.getId() == R.id.tv_followed) {
            showLoadingDialog(getString(R.string.cancelCttentionLoad));
            ((FocusContract.Presenter) mPresenter).postAddConcern(mAdapter.getItem(position).getUser_id(), type_id);
        }
    }


    @Override
    public void setPresenter(FocusContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            UserFocusBean userFocusBean = (UserFocusBean) JsonUtil.getInstance().json2Obj(success, UserFocusBean.class);
            if (userFocusBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    userFocusBean.getData().getResultX() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    userFocusBean.getData().getResultX().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.noFollow), 0);
                return;
            } else if (userFocusBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    userFocusBean.getData().getResultX() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    userFocusBean.getData().getResultX().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
                ViewInject.toast(getString(R.string.noMoreData));
                isShowLoadingMore = false;
                dismissLoadingDialog();
                mRefreshLayout.endLoadingMore();
                return;
            }
            mMorePageNumber = userFocusBean.getData().getCurrentPageNo();
            totalPageNumber = userFocusBean.getData().getTotalPageCount();
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(userFocusBean.getData().getResultX());
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(userFocusBean.getData().getResultX());
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            mAdapter.removeItem(positionItem);
            ViewInject.toast(getString(R.string.focusSuccess));
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusMineFragmentEvent"));
            dismissLoadingDialog();
        }
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
            } else if (msg.contains(getString(R.string.noFollow))) {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setVisibility(View.GONE);
            } else {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            }
        } else if (isLogin(msg)) {
            aty.showActivity(aty, LoginActivity.class);
        } else {
            ViewInject.toast(msg);
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null) {
            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
            ((FocusContract.Presenter) mPresenter).getMyConcernList(mMorePageNumber, type_id);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            mRefreshLayout.beginRefreshing();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

}