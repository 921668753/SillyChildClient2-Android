package com.sillykid.app.community.dynamic.dynamiccomments;

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
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.community.dynamic.dynamiccomments.DynamicCommentsViewAdapter;
import com.sillykid.app.community.DisplayPageActivity;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.community.dynamic.dynamiccomments.DynamicCommentsBean;
import com.sillykid.app.loginregister.LoginActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

/**
 * 动态评论
 */
public class DynamicCommentsActivity extends BaseActivity implements DynamicCommentsContract.View, AdapterView.OnItemClickListener, BGAOnItemChildClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private DynamicCommentsViewAdapter mAdapter;

    @BindView(id = R.id.lv_dynamicDetails)
    private ListView lv_dynamicDetails;

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

    private int id = 0;
    private int type = 0;
    private int positionItem = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_dynamiccomments);
    }

    @Override
    public void initData() {
        super.initData();
        id = getIntent().getIntExtra("id", 0);
        type = getIntent().getIntExtra("type", 0);
        mPresenter = new DynamicCommentsPresenter(this);
        mAdapter = new DynamicCommentsViewAdapter(aty);

    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.commentList), true, R.id.titlebar);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_dynamicDetails.setAdapter(mAdapter);
        lv_dynamicDetails.setOnItemClickListener(this);
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
                showActivity(aty, LoginActivity.class);
                break;
        }
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((DynamicCommentsContract.Presenter) mPresenter).getPostComment(id, mMorePageNumber, type);
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
        ((DynamicCommentsContract.Presenter) mPresenter).getPostComment(id, mMorePageNumber, type);
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(aty, CommentDetailsActivity.class);
        intent.putExtra("id", mAdapter.getItem(position).getId());
        intent.putExtra("type", type);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        positionItem = position;
        if (childView.getId() == R.id.ll_giveLike) {
            showLoadingDialog(getString(R.string.dataLoad));
            ((DynamicCommentsContract.Presenter) mPresenter).postAddLike(mAdapter.getItem(positionItem).getId(), type);
        } else if (childView.getId() == R.id.tv_revert) {
            Intent intent = new Intent(aty, CommentDetailsActivity.class);
            intent.putExtra("id", mAdapter.getItem(position).getId());
            intent.putExtra("type", type);
            intent.putExtra("type1", 1);
            startActivityForResult(intent, REQUEST_CODE);
        } else if (childView.getId() == R.id.ll_revertNum) {
            Intent intent = new Intent(aty, CommentDetailsActivity.class);
            intent.putExtra("id", mAdapter.getItem(position).getId());
            intent.putExtra("type", type);
            startActivityForResult(intent, REQUEST_CODE);
        } else if (childView.getId() == R.id.tv_nickName) {
            Intent intent = new Intent(aty, DisplayPageActivity.class);
            intent.putExtra("user_id", mAdapter.getItem(position).getMember_id());
            intent.putExtra("isRefresh", 0);
            showActivity(aty, intent);
        } else if (childView.getId() == R.id.tv_nickName1) {
            Intent intent = new Intent(aty, DisplayPageActivity.class);
            intent.putExtra("user_id", mAdapter.getItem(position).getReplyList().get(0).getMember_id());
            intent.putExtra("isRefresh", 0);
            showActivity(aty, intent);
        } else if (childView.getId() == R.id.tv_nickName2) {
            Intent intent = new Intent(aty, DisplayPageActivity.class);
            intent.putExtra("user_id", mAdapter.getItem(position).getReplyList().get(0).getReply_member_id());
            intent.putExtra("isRefresh", 0);
            showActivity(aty, intent);
        }
    }

    @Override
    public void setPresenter(DynamicCommentsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            DynamicCommentsBean dynamicCommentsBean = (DynamicCommentsBean) JsonUtil.getInstance().json2Obj(success, DynamicCommentsBean.class);
            if (dynamicCommentsBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    dynamicCommentsBean.getData().getList() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    dynamicCommentsBean.getData().getList().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.dynamicNotCommented), 0);
                return;
            } else if (dynamicCommentsBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    dynamicCommentsBean.getData().getList() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    dynamicCommentsBean.getData().getList().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
                ViewInject.toast(getString(R.string.noMoreData));
                isShowLoadingMore = false;
                dismissLoadingDialog();
                mRefreshLayout.endLoadingMore();
                return;
            }
            mMorePageNumber = dynamicCommentsBean.getData().getCurrentPageNo();
            totalPageNumber = dynamicCommentsBean.getData().getTotalPageCount();
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(dynamicCommentsBean.getData().getList());
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(dynamicCommentsBean.getData().getList());
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            if (mAdapter.getItem(positionItem).getIs_comment_like() == 1) {
                mAdapter.getItem(positionItem).setComment_like_number(StringUtils.toInt(mAdapter.getItem(positionItem).getComment_like_number(), 0) - 1 + "");
                mAdapter.getItem(positionItem).setIs_comment_like(0);
                mAdapter.notifyDataSetChanged();
                ViewInject.toast(getString(R.string.cancelZanSuccess));
            } else {
                mAdapter.getItem(positionItem).setComment_like_number(StringUtils.toInt(mAdapter.getItem(positionItem).getComment_like_number(), 0) + 1 + "");
                mAdapter.getItem(positionItem).setIs_comment_like(1);
                mAdapter.notifyDataSetChanged();
                ViewInject.toast(getString(R.string.zanSuccess));
            }
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
                finish();
                return;
            } else if (msg.contains(getString(R.string.checkNetwork))) {
                img_err.setImageResource(R.mipmap.no_network);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            } else if (msg.contains(getString(R.string.dynamicNotCommented))) {
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
            ((DynamicCommentsContract.Presenter) mPresenter).getPostComment(id, mMorePageNumber, type);
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
