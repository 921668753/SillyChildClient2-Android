package com.sillykid.app.community;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.sillykid.app.R;
import com.sillykid.app.adapter.community.DisplayPageViewAdapter;
import com.sillykid.app.community.dynamic.DynamicDetailsActivity;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.community.OtherUserInfoBean;
import com.sillykid.app.entity.community.OtherUserPostBean;
import com.sillykid.app.homepage.hotvideo.VideoDetailsActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;
import com.sillykid.app.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 个人发布展示页面
 */
@SuppressLint("NewApi")
public class DisplayPageActivity extends BaseActivity implements DisplayPageContract.View, View.OnScrollChangeListener, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener {

    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    @BindView(id = R.id.img_back1, click = true)
    private ImageView img_back1;

    @BindView(id = R.id.iv_minetouxiang)
    private ImageView iv_minetouxiang;

    @BindView(id = R.id.tv_nickname)
    private TextView tv_nickname;

    @BindView(id = R.id.tv_serialNumber)
    private TextView tv_serialNumber;

    @BindView(id = R.id.tv_synopsis)
    private TextView tv_synopsis;

    @BindView(id = R.id.tv_giveLike)
    private TextView tv_giveLike;

    @BindView(id = R.id.tv_follow)
    private TextView tv_follow;

    @BindView(id = R.id.tv_fans)
    private TextView tv_fans;

    @BindView(id = R.id.tv_beCollected)
    private TextView tv_beCollected;

    @BindView(id = R.id.tv_followN, click = true)
    private TextView tv_followN;

    private int user_id = 0;

    private int isRefresh = 0;

    private int is_concern = 0;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.sv_displayPage)
    private ScrollView sv_displayPage;


    @BindView(id = R.id.rl_title)
    private RelativeLayout rl_title;

    @BindView(id = R.id.tv_title)
    private TextView tv_title;


    @BindView(id = R.id.rv)
    private RecyclerView recyclerview;

    /**
     * 错误提示页
     */
//    @BindView(id = R.id.ll_commonError)
//    private LinearLayout ll_commonError;
//
//    @BindView(id = R.id.img_err)
//    private ImageView img_err;
//
    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;
//
//    @BindView(id = R.id.tv_button, click = true)
//    private TextView tv_button;

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

    private Thread thread = null;

    private List<OtherUserPostBean.DataBean.ListBean> list = null;

    private DisplayPageViewAdapter mAdapter;
    private ArrayList<OtherUserPostBean.DataBean.ListBean> list1;

    @Override

    public void setRootView() {
        setContentView(R.layout.activity_displaypage);
    }

    @Override
    public void initData() {
        super.initData();
        user_id = getIntent().getIntExtra("user_id", 0);
        isRefresh = getIntent().getIntExtra("isRefresh", 0);
        list = new ArrayList<OtherUserPostBean.DataBean.ListBean>();
        list1 = new ArrayList<OtherUserPostBean.DataBean.ListBean>();
        mPresenter = new DisplayPagePresenter(this);
        mAdapter = new DisplayPageViewAdapter(recyclerview);
        spacesItemDecoration = new SpacesItemDecoration(7, 14);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        initRecyclerView();
        sv_displayPage.setOnScrollChangeListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((DisplayPageContract.Presenter) mPresenter).getOtherUserInfo(user_id);
    }

    /**
     * 设置RecyclerView控件部分属性
     */
    private void initRecyclerView() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //   layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//不设置的话，图片闪烁错位，有可能有整列错位的情况。
        layoutManager.setAutoMeasureEnabled(true);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
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
            case R.id.img_back:
                if (isRefresh == 1) {
                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);
                }
                finish();
                break;
            case R.id.img_back1:
                if (isRefresh == 1) {
                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);
                }
                finish();
                break;
            case R.id.tv_followN:
                String title = getString(R.string.attentionLoad);
                if (is_concern == 1) {
                    title = getString(R.string.cancelCttentionLoad);
                } else {
                    title = getString(R.string.attentionLoad);
                }
                showLoadingDialog(title);
                ((DisplayPageContract.Presenter) mPresenter).postAddConcern(user_id, 1);
                break;
//            case R.id.tv_button:
//                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
//                    mRefreshLayout.beginRefreshing();
//                    return;
//                }
//                showActivity(aty, LoginActivity.class);
//                break;
        }

    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        if (mAdapter.getItem(position).getType() == 1) {//动态
            Intent intent = new Intent(aty, DynamicDetailsActivity.class);
            intent.putExtra("id", mAdapter.getItem(position).getId());
            intent.putExtra("title", mAdapter.getItem(position).getPost_title());
            showActivity(aty, intent);
        } else if (mAdapter.getItem(position).getType() == 2) {//视频
            Intent intent = new Intent(aty, VideoDetailsActivity.class);
            intent.putExtra("id", mAdapter.getItem(position).getId());
            intent.putExtra("title", mAdapter.getItem(position).getPost_title());
            showActivity(aty, intent);
        } else if (mAdapter.getItem(position).getType() == 3) {//攻略
            Intent intent = new Intent(aty, DynamicDetailsActivity.class);
            intent.putExtra("id", mAdapter.getItem(position).getId());
            intent.putExtra("title", mAdapter.getItem(position).getPost_title());
            showActivity(aty, intent);
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((DisplayPageContract.Presenter) mPresenter).getOtherUserInfo(user_id);
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
        ((DisplayPageContract.Presenter) mPresenter).getOtherUserPost(user_id, mMorePageNumber);
        return true;
    }

    @Override
    public void setPresenter(DisplayPageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            OtherUserInfoBean otherUserInfoBean = (OtherUserInfoBean) JsonUtil.getInstance().json2Obj(success, OtherUserInfoBean.class);
            GlideImageLoader.glideLoader(this, otherUserInfoBean.getData().getFace(), iv_minetouxiang, 0, R.mipmap.avatar);
            tv_nickname.setText(otherUserInfoBean.getData().getNickname());
            tv_title.setText(otherUserInfoBean.getData().getNickname());
            tv_serialNumber.setText(otherUserInfoBean.getData().getShz());
            tv_synopsis.setText(otherUserInfoBean.getData().getSignature());
            tv_follow.setText(otherUserInfoBean.getData().getConcern_number());
            tv_beCollected.setText(otherUserInfoBean.getData().getCollected_number());
            tv_fans.setText(otherUserInfoBean.getData().getFans_number());
            tv_giveLike.setText(otherUserInfoBean.getData().getLike_number());
            is_concern = otherUserInfoBean.getData().getIs_concern();
            if (is_concern == 1) {
                tv_followN.setText(getString(R.string.followed));
//                tv_followN.setBackgroundResource(R.drawable.shape_code1);
//                tv_followN.setTextColor(getResources().getColor(R.color.tabColors));
            } else {
                tv_followN.setText(getString(R.string.follow));
//                tv_followN.setBackgroundResource(R.drawable.shape_displaypage);
//                tv_followN.setTextColor(getResources().getColor(R.color.whiteColors));
            }
            ((DisplayPageContract.Presenter) mPresenter).getOtherUserPost(user_id, mMorePageNumber);
        } else if (flag == 1) {
            isShowLoadingMore = true;
            tv_hintText.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            mRefreshLayout.setPullDownRefreshEnable(true);
            OtherUserPostBean otherUserPostBean = (OtherUserPostBean) JsonUtil.getInstance().json2Obj(success, OtherUserPostBean.class);
            if (otherUserPostBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    otherUserPostBean.getData().getList() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    otherUserPostBean.getData().getList().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.noMovement), 1);
                return;
            } else if (otherUserPostBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    otherUserPostBean.getData().getList() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    otherUserPostBean.getData().getList().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
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
                    for (int i = 0; i < otherUserPostBean.getData().getList().size(); i++) {
                        Bitmap bitmap = GlideImageLoader.load(aty, otherUserPostBean.getData().getList().get(i).getPicture());
                        if (bitmap != null) {
                            otherUserPostBean.getData().getList().get(i).setHeight(bitmap.getHeight());
                            otherUserPostBean.getData().getList().get(i).setWidth(bitmap.getWidth());
                        }
                        list.add(otherUserPostBean.getData().getList().get(i));
                    }
                    aty.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mMorePageNumber = otherUserPostBean.getData().getCurrentPageNo();
                            totalPageNumber = otherUserPostBean.getData().getTotalPageCount();
                            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                                mRefreshLayout.endRefreshing();
                                mAdapter.clear();
                                mAdapter.addNewData(list);
                            } else {
                                list1.clear();
                                list1.addAll(mAdapter.getData());
                                list1.addAll(list);
                                mAdapter.clear();
                                mRefreshLayout.endLoadingMore();
                                mAdapter.addNewData(list1);
                            }
                            dismissLoadingDialog();
                        }
                    });
                }
            });
            thread.start();
        } else if (flag == 2) {
            if (is_concern == 1) {
                is_concern = 0;
                tv_followN.setText(getString(R.string.follow));
//                tv_follow.setBackgroundResource(R.drawable.shape_follow);
//                tv_follow.setTextColor(getResources().getColor(R.color.greenColors));
                ViewInject.toast(getString(R.string.focusSuccess));
            } else {
                is_concern = 1;
                tv_followN.setText(getString(R.string.followed));
//                tv_follow.setBackgroundResource(R.drawable.shape_followed);
//                tv_follow.setTextColor(getResources().getColor(R.color.tabColors));
                ViewInject.toast(getString(R.string.attentionSuccess));
            }
            dismissLoadingDialog();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 1) {
            isShowLoadingMore = false;
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
            }
//            mRefreshLayout.setPullDownRefreshEnable(false);
//            mRefreshLayout.setVisibility(View.GONE);
//            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setVisibility(View.VISIBLE);
//            tv_button.setVisibility(View.VISIBLE);
//            if (isLogin(msg)) {
//                img_err.setImageResource(R.mipmap.no_login);
//                tv_hintText.setVisibility(View.GONE);
//                tv_button.setText(getString(R.string.login));
//                showActivity(aty, LoginActivity.class);
//                return;
//            } else if (msg.contains(getString(R.string.checkNetwork))) {
//                img_err.setImageResource(R.mipmap.no_network);
//                tv_hintText.setText(msg);
//                tv_button.setText(getString(R.string.retry));
//            } else
            if (msg.contains(getString(R.string.noMovement))) {
                tv_hintText.setText(msg);
            } else {
                tv_hintText.setText(msg);
            }
            return;
        }
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list.clear();
        list = null;
        list1.clear();
        list1 = null;
        if (thread != null) {
            thread.interrupted();
        }
        thread = null;
        mAdapter.clear();
        mAdapter = null;
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY <= 0) {
            img_back.setImageDrawable(null);
            rl_title.setBackgroundColor(Color.TRANSPARENT);
            //                          设置文字颜色，黑色，加透明度
            tv_title.setTextColor(Color.TRANSPARENT);
            Log.e("111", "y <= 0");
        } else if (scrollY > 0 && scrollY <= 200) {
            float scale = (float) scrollY / 200;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)白色透明
            rl_title.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            //    设置文字颜色，黑色，加透明度
            tv_title.setTextColor(Color.argb((int) alpha, 0, 0, 0));
            img_back.setImageResource(R.mipmap.back3);
            Log.e("111", "y > 0 && y <= imageHeight");
        } else {
//                          白色不透明
            rl_title.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
            //                          设置文字颜色
            //黑色
            tv_title.setTextColor(Color.argb((int) 255, 0, 0, 0));
            img_back.setImageResource(R.mipmap.back);
        }

    }
}
