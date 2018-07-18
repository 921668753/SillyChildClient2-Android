package com.sillykid.app.message;

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
import com.sillykid.app.adapter.message.SystemMessageViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.constant.StringNewConstants;
import com.sillykid.app.entity.message.SystemMessageBean;
import com.sillykid.app.entity.message.SystemMessageBean.DataBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.main.MainActivity;
import com.sillykid.app.message.systemmessage.SystemMessageListActivity;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.rong.imkit.RongIM;

import static android.app.Activity.RESULT_OK;
import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

/**
 * 系统消息
 * Created by Admin on 2017/8/17.
 */

public class SystemMessageFragment extends BaseFragment implements SystemMessageContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    private SystemMessageViewAdapter mAdapter;

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.lv_systemMessage)
    private ListView lv_systemMessage;


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
     * 消息总数
     */
    // private int sum = 0;
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_systemmessage, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new SystemMessagePresenter(this);
        mAdapter = new SystemMessageViewAdapter(getActivity());
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        lv_systemMessage.setAdapter(mAdapter);
        lv_systemMessage.setOnItemClickListener(this);
        mRefreshLayout.beginRefreshing();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        DataBean dataBean = mAdapter.getItem(i);
        Intent intent = new Intent(aty, SystemMessageListActivity.class);
        intent.putExtra("news_title", dataBean.getNews_title());
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((SystemMessageContract.Presenter) mPresenter).getSystem(aty, mMorePageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
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
    public void getSuccess(String success, int flag) {
        mRefreshLayout.setPullDownRefreshEnable(true);
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        SystemMessageBean systemMessageBean = (SystemMessageBean) JsonUtil.getInstance().json2Obj(success, SystemMessageBean.class);
        if (systemMessageBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                systemMessageBean.getData().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            errorMsg(getString(R.string.noSystemMessage), 1);
            return;
        } else if (systemMessageBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                systemMessageBean.getData().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
            ViewInject.toast(getString(R.string.noMoreData));
            dismissLoadingDialog();
            mRefreshLayout.endLoadingMore();
            return;
        }
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
            mAdapter.clear();
            mAdapter.addNewData(systemMessageBean.getData());
        } else {
            mRefreshLayout.endLoadingMore();
            mAdapter.addMoreData(systemMessageBean.getData());
        }
        sendCast(systemMessageBean);
        dismissLoadingDialog();
    }

    /**
     * 发送广播
     *
     * @param systemMessageBean num 未读消息总数
     */
    private void sendCast(SystemMessageBean systemMessageBean) {
        int num = 0;
        if (systemMessageBean.getData() != null && systemMessageBean.getData().size() > 0) {
            for (int i = 0; i < systemMessageBean.getData().size(); i++) {
                num += systemMessageBean.getData().get(i).getNum();
            }
        }
        num += RongIM.getInstance().getTotalUnreadCount();
        Intent intentcast = new Intent(StringNewConstants.MainServiceAction);
        if (num > 0) {
            intentcast.putExtra("havemsg", true);
        } else {
            intentcast.putExtra("havemsg", false);
        }
        aty.sendBroadcast(intentcast);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
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
            //    aty.showActivity(aty, LoginActivity.class);
            return;
        } else if (msg.contains(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.no_network);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        } else if (msg.contains(getString(R.string.noSystemMessage))) {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setVisibility(View.GONE);
        } else {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        }
    }

    @Override
    public void setPresenter(SystemMessageContract.Presenter presenter) {
        mPresenter = presenter;
    }


    /**
     * 当通过changeFragment()显示时会被调用(类似于onResume)
     */
    @Override
    public void onChange() {
        super.onChange();
        mRefreshLayout.beginRefreshing();
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null) {
            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
            ((SystemMessageContract.Presenter) mPresenter).getSystem(aty, mMorePageNumber);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
            ((SystemMessageContract.Presenter) mPresenter).getSystem(aty, mMorePageNumber);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

}
