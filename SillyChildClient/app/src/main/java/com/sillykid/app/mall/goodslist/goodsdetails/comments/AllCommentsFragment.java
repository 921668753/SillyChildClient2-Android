package com.sillykid.app.mall.goodslist.goodsdetails.comments;

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
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.goodslist.evaluation.CommentsViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.homepage.goodslist.goodsdetails.comments.CommentsBean;
import com.sillykid.app.loginregister.LoginActivity;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 全部评论
 * Created by Admin on 2017/8/10.
 */
public class AllCommentsFragment extends BaseFragment implements CommentsContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private CommentsActivity aty;

    @BindView(id = R.id.lv_comments)
    private ListView lv_comments;

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

    private CommentsViewAdapter mAdapter = null;

    private int goodsid = 0;

    private int onlyimage = 0;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (CommentsActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_comments, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new CommentsPresenter(this);
        mAdapter = new CommentsViewAdapter(getActivity());
        goodsid = aty.getIntent().getIntExtra("goodsid", 0);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_comments.setAdapter(mAdapter);
        lv_comments.setOnItemClickListener(this);
        mRefreshLayout.beginRefreshing();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Intent intent = new Intent(aty, OrderDetailsActivity.class);
//        intent.putExtra("order_id", mAdapter.getItem(i).getOrder_id());
//        aty.showActivity(aty, intent);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((CommentsContract.Presenter) mPresenter).getCommentList(aty, goodsid, onlyimage, mMorePageNumber);
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
        ((CommentsContract.Presenter) mPresenter).getCommentList(aty, goodsid, onlyimage, mMorePageNumber);
        return true;
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
                aty.skipActivity(aty, LoginActivity.class);
                break;
        }
    }

    /**
     * 当通过changeFragment()显示时会被调用(类似于onResume)
     */
    @Override
    public void onChange() {
        super.onChange();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cancelAllComments();
        mAdapter.clear();
        mAdapter = null;
    }

    @Override
    public void setPresenter(CommentsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        CommentsBean commentsBean = (CommentsBean) JsonUtil.getInstance().json2Obj(success, CommentsBean.class);
        if (commentsBean.getData().getCommentList() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER || commentsBean.getData().getCommentList().size() <= 0 &&
                mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            errorMsg(getString(R.string.goodsNotCommented), 1);
            return;
        } else if (commentsBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                commentsBean.getData().getCommentList().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
            ViewInject.toast(getString(R.string.noMoreData));
            isShowLoadingMore = false;
            dismissLoadingDialog();
            mRefreshLayout.endLoadingMore();
            return;
        }
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
            mAdapter.clear();
            mAdapter.addNewData(commentsBean.getData().getCommentList());
        } else {
            mRefreshLayout.endLoadingMore();
            mAdapter.addMoreData(commentsBean.getData().getCommentList());
        }
        if (StringUtils.isEmpty(commentsBean.getData().getCommentCount())) {
            //   aty.setAll("(0)");
        } else {
            aty.setAll("(" + commentsBean.getData().getCommentCount() + ")");
        }
        if (StringUtils.isEmpty(commentsBean.getData().getImageCount())) {
            // aty.setHavePictures("0");
        } else {
            aty.setHavePictures("(" + commentsBean.getData().getImageCount() + ")");
        }
        int num = StringUtils.toInt(commentsBean.getData().getCommentCount(), 0) - StringUtils.toInt(commentsBean.getData().getImageCount(), 0);
        if (num <= 0) {
            // aty.setAdditionalReview("0");
        } else {
            aty.setAdditionalReview("(" + num + ")");
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
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
            //  ViewInject.toast(getString(R.string.reloginPrompting));
            aty.skipActivity(aty, LoginActivity.class);
            return;
        } else if (msg.contains(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.no_network);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        } else if (msg.contains(getString(R.string.goodsNotCommented))) {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setVisibility(View.GONE);
        } else {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        }
        //   ViewInject.toast(msg);
        dismissLoadingDialog();
    }

}
