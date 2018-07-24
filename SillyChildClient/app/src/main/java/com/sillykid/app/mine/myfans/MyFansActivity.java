package com.sillykid.app.mine.myfans;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.mine.myfans.MyFansViewAdapter;
import com.sillykid.app.community.DisplayPageActivity;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.mine.myfans.MyFansBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mall.goodslist.goodsdetails.GoodsDetailsActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

/**
 * 我的粉丝
 */
public class MyFansActivity extends BaseActivity implements MyFansContract.View, AdapterView.OnItemClickListener, BGAOnItemChildClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private MyFansViewAdapter mAdapter;

    @BindView(id = R.id.lv_myFans)
    private ListView lv_myFans;

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

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myfans);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyFansPresenter(this);
        mAdapter = new MyFansViewAdapter(aty);

    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_myFans.setAdapter(mAdapter);
        lv_myFans.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myFans), true, R.id.titlebar);
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
                showActivity(aty, LoginActivity.class);
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyFansContract.Presenter) mPresenter).getMyFansList(mMorePageNumber);
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
        ((MyFansContract.Presenter) mPresenter).getMyFansList(mMorePageNumber);
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(aty, DisplayPageActivity.class);
        intent.putExtra("user_id", mAdapter.getItem(position).getMember_id());
        intent.putExtra("isRefresh", 1);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        positionItem = position;
        if (childView.getId() == R.id.tv_follow) {
            String title = getString(R.string.attentionLoad);
            if (StringUtils.toInt(mAdapter.getItem(positionItem).getIs_concern(), 0) == 0) {
                title = getString(R.string.attentionLoad);
            } else {
                title = getString(R.string.cancelCttentionLoad);
            }
            showLoadingDialog(title);
            ((MyFansContract.Presenter) mPresenter).postAddConcern(mAdapter.getItem(position).getMember_id(), 1);
        }
    }


    @Override
    public void setPresenter(MyFansContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            MyFansBean myFansBean = (MyFansBean) JsonUtil.getInstance().json2Obj(success, MyFansBean.class);
            if (myFansBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    myFansBean.getData().getTotalCount() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.noFans), 0);
                return;
            } else if (myFansBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    myFansBean.getData().getTotalCount() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
                ViewInject.toast(getString(R.string.noMoreData));
                isShowLoadingMore = false;
                dismissLoadingDialog();
                mRefreshLayout.endLoadingMore();
                return;
            }
            mMorePageNumber = myFansBean.getData().getCurrentPageNo();
            totalPageNumber = myFansBean.getData().getTotalPageCount();
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(myFansBean.getData().getResult());
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(myFansBean.getData().getResult());
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            if (StringUtils.toInt(mAdapter.getItem(positionItem).getIs_concern(), 0) == 0) {
                mAdapter.getItem(positionItem).setIs_concern("1");
                mAdapter.getItem(positionItem).setFans_number(StringUtils.toInt(mAdapter.getItem(positionItem).getFans_number(), 0) + 1 + "");
                mAdapter.notifyDataSetChanged();
                ViewInject.toast(getString(R.string.attentionSuccess));
            } else {
                mAdapter.getItem(positionItem).setIs_concern(null);
                mAdapter.getItem(positionItem).setFans_number(StringUtils.toInt(mAdapter.getItem(positionItem).getFans_number(), 0) - 1 + "");
                mAdapter.notifyDataSetChanged();
                ViewInject.toast(getString(R.string.focusSuccess));
            }
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
                showActivity(aty, LoginActivity.class);
                return;
            } else if (msg.contains(getString(R.string.checkNetwork))) {
                img_err.setImageResource(R.mipmap.no_network);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            } else if (msg.contains(getString(R.string.noFans))) {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setVisibility(View.GONE);
            } else {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            }
        } else if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
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
            ((MyFansContract.Presenter) mPresenter).getMyFansList(mMorePageNumber);
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