package com.sillykid.app.mine.myrelease.mydynamic;

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
import com.sillykid.app.R;
import com.sillykid.app.adapter.mine.myrelease.mydynamic.MyDynamicViewAdapter;
import com.sillykid.app.community.dynamic.DynamicDetailsActivity;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.mine.myrelease.mydynamic.MyDynamicBean;
import com.sillykid.app.homepage.hotvideo.VideoDetailsActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myrelease.MyReleaseActivity;
import com.sillykid.app.utils.GlideImageLoader;
import com.sillykid.app.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;
import static com.sillykid.app.constant.NumericConstants.RESULT_CODE_GET;

/**
 * 我的动态
 */
public class MyDynamicFragment extends BaseFragment implements MyDynamicContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener {

    private MyReleaseActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.rv)
    private RecyclerView recyclerview;

    private MyDynamicViewAdapter mAdapter = null;

    /**
     * 发布新动态
     */
    @BindView(id = R.id.tv_newTrends, click = true)
    private TextView tv_newTrends;


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

    private StaggeredGridLayoutManager layoutManager;

    private Thread thread = null;

    private List<MyDynamicBean.DataBean.ResultBean> list = null;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyReleaseActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mydynamic, null);
    }


    @Override
    protected void initData() {
        super.initData();
        mPresenter = new MyDynamicPresenter(this);
        spacesItemDecoration = new SpacesItemDecoration(7, 14);
        mAdapter = new MyDynamicViewAdapter(recyclerview);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//不设置的话，图片闪烁错位，有可能有整列错位的情况。
        list = new ArrayList<MyDynamicBean.DataBean.ResultBean>();
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
        recyclerview.setLayoutManager(layoutManager);
        //设置item之间的间隔
        recyclerview.addItemDecoration(spacesItemDecoration);
        recyclerview.setAdapter(mAdapter);
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
            case R.id.tv_newTrends:
                Intent intent = new Intent(aty, ReleaseDynamicActivity.class);
                startActivityForResult(intent, RESULT_CODE_GET);
                break;
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
    public void setPresenter(MyDynamicContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        mRefreshLayout.setPullDownRefreshEnable(true);
        MyDynamicBean myDynamicBean = (MyDynamicBean) JsonUtil.getInstance().json2Obj(success, MyDynamicBean.class);
        if (myDynamicBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER || myDynamicBean.getData().getResult() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                myDynamicBean.getData().getResult().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            errorMsg(getString(R.string.noDynamicState), 1);
            return;
        } else if (myDynamicBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                myDynamicBean.getData().getResult() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                myDynamicBean.getData().getResult().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
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
                try {
                    list.clear();
                } catch (Exception e) {
                    list = new ArrayList<MyDynamicBean.DataBean.ResultBean>();
                }
                for (int i = 0; i < myDynamicBean.getData().getResult().size(); i++) {
                    Bitmap bitmap = GlideImageLoader.load(aty, myDynamicBean.getData().getResult().get(i).getPicture());
                    if (bitmap != null) {
                        myDynamicBean.getData().getResult().get(i).setHeight(bitmap.getHeight());
                        myDynamicBean.getData().getResult().get(i).setWidth(bitmap.getWidth());
                    }
                    list.add(myDynamicBean.getData().getResult().get(i));
                }
                mMorePageNumber = myDynamicBean.getData().getCurrentPageNo();
                totalPageNumber = myDynamicBean.getData().getTotalPageCount();
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
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        //   if (mAdapter.getItem(position).getType() == 1) {//动态
        Intent intent = new Intent(aty, ReleaseDynamicActivity.class);
        intent.putExtra("id", mAdapter.getItem(position).getId());
        intent.putExtra("type", mAdapter.getItem(position).getType());
        startActivityForResult(intent, RESULT_CODE_GET);
//        } else if (mAdapter.getItem(position).getType() == 2) {//视频
//            Intent intent = new Intent(aty, VideoDetailsActivity.class);
//            intent.putExtra("id", mAdapter.getItem(position).getId());
//            intent.putExtra("title", mAdapter.getItem(position).getPost_title());
//            aty.showActivity(aty, intent);
//        } else if (mAdapter.getItem(position).getType() == 3) {//攻略
//            Intent intent = new Intent(aty, DynamicDetailsActivity.class);
//            intent.putExtra("id", mAdapter.getItem(position).getId());
//            intent.putExtra("title", mAdapter.getItem(position).getPost_title());
//            aty.showActivity(aty, intent);
//        }
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
            aty.showActivity(aty, LoginActivity.class);
            return;
        } else if (msg.contains(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.no_network);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        } else if (msg.contains(getString(R.string.noDynamicState))) {
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


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyDynamicContract.Presenter) mPresenter).getUserPost(mMorePageNumber);
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
        ((MyDynamicContract.Presenter) mPresenter).getUserPost(mMorePageNumber);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == RESULT_CODE_GET && resultCode == RESULT_OK) {
            mRefreshLayout.beginRefreshing();
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