package com.sillykid.app.homepage.dynamics;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.common.cklibrary.utils.StringNewUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.dynamics.DynamicsCommentarieViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.DynamicsCommentariesBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.SoftKeyboardUtils;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 动态评论
 * Created by Admin on 2017/10/19.
 */

public class DynamicsCommentariesActivity extends BaseActivity implements DynamicsCommentariesContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnItemChildClickListener {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.ll_publishedTime, click = true)
    private LinearLayout ll_publishedTime;

    @BindView(id = R.id.img_publishedTime)
    private ImageView img_publishedTime;

    @BindView(id = R.id.ll_greatNumber, click = true)
    private LinearLayout ll_greatNumber;

    @BindView(id = R.id.img_greatNumber)
    private ImageView img_greatNumber;

//    @BindView(id = R.id.sv_dynamicsCommentaries)
//    private ScrollView sv_dynamicsCommentaries;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private DynamicsCommentarieViewAdapter mAdapter;

    @BindView(id = R.id.lv_dynamicsCommentaries)
    private ListView lv_dynamicsCommentaries;

    @BindView(id = R.id.ll_dynamicsdetailscomment)
    private LinearLayout ll_dynamicsdetailscomment;

    @BindView(id = R.id.et_writeComment)
    private EditText et_writeComment;

    @BindView(id = R.id.tv_published, click = true)
    private TextView tv_published;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;


    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;
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

    String article_id;
    String publish_id;
    private ImageView img_favor;
    private DynamicsCommentariesBean.ResultBean.ListBean commentsBean;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_dynamicscommentaries);
    }


    @Override
    public void initData() {
        super.initData();
        article_id = getIntent().getStringExtra("article_id");
        publish_id = getIntent().getStringExtra("publish_id");
        mPresenter = new DynamicsCommentariesPresenter(this);
        //   mAdapter = new DynamicsCommentarieViewAdapter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.dynamicComments), true, R.id.titlebar);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        ((DynamicsCommentariesContract.Presenter) mPresenter).changeInputView(et_writeComment, tv_published);
        mRefreshLayout.beginRefreshing();
      //  StringNewUtils.setFilters(et_writeComment);
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((DynamicsCommentariesContract.Presenter) mPresenter).getDynamicsCommentaries(mMorePageNumber, article_id);
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
        ((DynamicsCommentariesContract.Presenter) mPresenter).getDynamicsCommentaries(mMorePageNumber, article_id);
        return true;
    }

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_published:
                if (tv_published.getText().toString().equals(getString(R.string.cancel))) {
                    SoftKeyboardUtils.packUpKeyboard(this);
                    ll_dynamicsdetailscomment.setVisibility(View.GONE);
//                    Utility.setListViewHeightBasedOnChildren(lv_dynamicsCommentaries);
                    return;
                }
                showLoadingDialog(getString(R.string.dataLoad));
                ((DynamicsCommentariesContract.Presenter) mPresenter).newActionComment(article_id, publish_id, et_writeComment.getText().toString().trim(), commentsBean.getId());
                break;
            case R.id.tv_hintText:
                mRefreshLayout.beginRefreshing();
                break;
        }
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        commentsBean = mAdapter.getItem(position);
        if (childView.getId() == R.id.tv_revert1) {
            ll_dynamicsdetailscomment.setVisibility(View.VISIBLE);
            et_writeComment.setHint(getString(R.string.revert) + commentsBean.getOwner().getNickname() + "：");
            SoftKeyboardUtils.ejectKeyboard(et_writeComment);
        } else if (childView.getId() == R.id.img_favor) {
            img_favor = (ImageView) childView.findViewById(R.id.img_favor);
            showLoadingDialog(getString(R.string.dataLoad));
            ((DynamicsCommentariesContract.Presenter) mPresenter).praiseDynamicsDetailsComment(commentsBean.getId(), commentsBean.getIsPraise());
        } else if (childView.getId() == R.id.ll_revertNum) {
//            LinearLayout ll_revertNum = (LinearLayout) childView.findViewById(R.id.ll_revertNum);
//            LinearLayout ll_dynamicsdetailsreplies = (LinearLayout) ((View) ll_revertNum.getParent()).findViewById(R.id.ll_dynamicsdetailsreplies);
//            ChildLiistView lv_replies = (ChildLiistView) ((View) ll_revertNum.getParent()).findViewById(R.id.lv_replies);
//            lv_replies.setVisibility(View.VISIBLE);
//            ((LinearLayout) ll_revertNum.getParent()).removeView(ll_dynamicsdetailsreplies);
//            ((LinearLayout) ll_revertNum.getParent()).removeView(ll_revertNum);
        }
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            DynamicsCommentariesBean dynamicsCommentariesBean = (DynamicsCommentariesBean) JsonUtil.getInstance().json2Obj(success, DynamicsCommentariesBean.class);
            mMorePageNumber = dynamicsCommentariesBean.getData().getP();
            totalPageNumber = dynamicsCommentariesBean.getData().getTotalPages();
            if (dynamicsCommentariesBean.getData().getList() == null || dynamicsCommentariesBean.getData().getList().size() == 0) {
                errorMsg(getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter = null;
                mAdapter = new DynamicsCommentarieViewAdapter(this);
                lv_dynamicsCommentaries.setAdapter(mAdapter);
                mAdapter.setOnItemChildClickListener(this);
                mAdapter.clear();
                mAdapter.addNewData(dynamicsCommentariesBean.getData().getList());
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(dynamicsCommentariesBean.getData().getList());
            }
            //   Utility.setListViewHeightBasedOnChildren(lv_dynamicsCommentaries);
        } else if (flag == 1) {
            SoftKeyboardUtils.packUpKeyboard(this);
            ll_dynamicsdetailscomment.setVisibility(View.GONE);
            mRefreshLayout.beginRefreshing();
            ViewInject.toast(getString(R.string.commentSuccess));
            return;
        } else if (flag == 2) {
            img_favor.setImageResource(R.mipmap.btn_favor_pressed);
            ViewInject.toast(getString(R.string.thumbSuccess));
        } else if (flag == 3) {
            //   isPraiseComment = 0;
            img_favor.setImageResource(R.mipmap.btn_favor_normal);
            ViewInject.toast(getString(R.string.unThumbSuccess));
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            Intent intent = new Intent(aty, LoginActivity.class);
            showActivity(aty, intent);
            return;
        }
        if (flag == 1) {
            ViewInject.toast(msg);
            return;
        }
        isShowLoadingMore = false;
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
        if (msg.equals(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.nonetworkxxx);
        } else {
            img_err.setImageResource(R.mipmap.nocontentxxx);
        }
    }

    @Override
    public void setPresenter(DynamicsCommentariesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

}
