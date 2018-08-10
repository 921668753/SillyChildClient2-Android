package com.sillykid.app.homepage.boutiqueline.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.sillykid.app.R;
import com.sillykid.app.adapter.main.community.CommunityViewAdapter;
import com.sillykid.app.community.dynamic.DynamicDetailsActivity;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.main.community.CommunityBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.main.MainActivity;
import com.sillykid.app.utils.GlideImageLoader;
import com.sillykid.app.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;
import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

/**
 * 热门推荐列表
 */
public class HotRecommendedFragment extends BaseFragment implements CommunityClassificationContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;


    @BindView(id = R.id.rv)
    private RecyclerView recyclerview;

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

    private SpacesItemDecoration spacesItemDecoration = null;

    private int classification_id = 0;

    private String post_title = "";

    private String nickname = "";

    private Thread thread = null;

    private List<CommunityBean.DataBean.ResultBean> list = null;

    private CommunityViewAdapter mAdapter;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_hotrecommended, null);
    }


    @Override
    protected void initData() {
        super.initData();
        list = new ArrayList<CommunityBean.DataBean.ResultBean>();
        mPresenter = new CommunityClassificationPresenter(this);
        spacesItemDecoration = new SpacesItemDecoration(7, 14);
        mAdapter = new CommunityViewAdapter(recyclerview);
    }


    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        initRecyclerView();
        mRefreshLayout.beginRefreshing();
    }

    /**
     * 设置RecyclerView控件部分属性
     */
    private void initRecyclerView() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        //设置item之间的间隔
        recyclerview.addItemDecoration(spacesItemDecoration);
        recyclerview.setAdapter(mAdapter);
    //    layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//不设置的话，图片闪烁错位，有可能有整列错位的情况。
        mAdapter.setOnRVItemClickListener(this);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
    }

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


    public void setClassificationId(int classification_id1) {
        classification_id = classification_id1;
//        if (mRefreshLayout != null) {
//            mRefreshLayout.beginRefreshing();
//        }
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        if (mAdapter.getItem(position).getType() == 1) {//动态
            Intent intent = new Intent(aty, DynamicDetailsActivity.class);
            intent.putExtra("id", mAdapter.getItem(position).getId());
            intent.putExtra("title", mAdapter.getItem(position).getPost_title());
            aty.showActivity(aty, intent);
        } else if (mAdapter.getItem(position).getType() == 2) {//视频
            Intent intent = new Intent(aty, DynamicDetailsActivity.class);
            intent.putExtra("id", mAdapter.getItem(position).getId());
            intent.putExtra("title", mAdapter.getItem(position).getPost_title());
            aty.showActivity(aty, intent);
        } else if (mAdapter.getItem(position).getType() == 3) {//攻略
            Intent intent = new Intent(aty, DynamicDetailsActivity.class);
            intent.putExtra("id", mAdapter.getItem(position).getId());
            intent.putExtra("title", mAdapter.getItem(position).getPost_title());
            aty.showActivity(aty, intent);
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((CommunityClassificationContract.Presenter) mPresenter).getPostList(aty, post_title, nickname, classification_id, mMorePageNumber);
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
        ((CommunityClassificationContract.Presenter) mPresenter).getPostList(aty, post_title, nickname, classification_id, mMorePageNumber);
        return true;
    }

    @Override
    public void setPresenter(CommunityClassificationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        mRefreshLayout.setPullDownRefreshEnable(true);
        CommunityBean communityBean = (CommunityBean) JsonUtil.getInstance().json2Obj(success, CommunityBean.class);
        if (communityBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                communityBean.getData().getResultX() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                communityBean.getData().getResultX().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            errorMsg(getString(R.string.noMovement), 1);
            return;
        } else if (communityBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                communityBean.getData().getResultX() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                communityBean.getData().getResultX().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
            ViewInject.toast(getString(R.string.noMoreData));
            isShowLoadingMore = false;
            dismissLoadingDialog();
            mRefreshLayout.endLoadingMore();
            return;
        }
        if (thread != null && !thread.isAlive()) {
            thread.interrupted();
        }
        thread = null;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                list.clear();
                for (int i = 0; i < communityBean.getData().getResultX().size(); i++) {
                    Bitmap bitmap = GlideImageLoader.load(aty, communityBean.getData().getResultX().get(i).getPicture());
                    if (bitmap != null) {
                        communityBean.getData().getResultX().get(i).setHeight(bitmap.getHeight());
                        communityBean.getData().getResultX().get(i).setWidth(bitmap.getWidth());
                    }
                    list.add(communityBean.getData().getResultX().get(i));
                }
                mMorePageNumber = communityBean.getData().getCurrentPageNo();
                totalPageNumber = communityBean.getData().getTotalPageCount();
                aty.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                            mRefreshLayout.endRefreshing();
                            mAdapter.clear();
                            mAdapter.addNewData(list);
                        } else {
                            mRefreshLayout.endLoadingMore();
                            mAdapter.addMoreData(list);
                        }
                        dismissLoadingDialog();
                    }
                });
            }
        });
        thread.start();

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
            aty.showActivity(aty, LoginActivity.class);
            return;
        } else if (msg.contains(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.no_network);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        } else if (msg.contains(getString(R.string.noMovement))) {
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {// 如果等于1
//            keyword = data.getStringExtra("keyword");
////            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
////            showLoadingDialog(getString(R.string.dataLoad));
////            ((GoodsListContract.Presenter) mPresenter).getGoodsList(mMorePageNumber, cat, sort, keyword, mark);
//            mRefreshLayout.beginRefreshing();
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusCommunityClassificationFragmentEvent") && mPresenter != null) {
            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
            ((CommunityClassificationContract.Presenter) mPresenter).getPostList(aty, post_title, nickname, classification_id, mMorePageNumber);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        list.clear();
        list = null;
        if (thread != null) {
            thread.interrupted();
        }
        thread = null;
        mAdapter.clear();
        mAdapter = null;
    }
}
