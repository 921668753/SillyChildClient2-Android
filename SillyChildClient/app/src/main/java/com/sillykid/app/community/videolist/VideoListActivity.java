package com.sillykid.app.community.videolist;

import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.Log;
import com.sillykid.app.R;
import com.sillykid.app.adapter.community.videolist.VideoListViewAdapter;
import com.sillykid.app.entity.main.community.CommunityBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.layoutmanager.MyLayoutManager;
import com.sillykid.app.utils.layoutmanager.OnViewPagerListener;

import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUserAction;
import cn.jzvd.JZUtils;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdMgr;
import cn.jzvd.JzvdStd;


/**
 * 上下滑动切换视频
 */
public class VideoListActivity extends BaseActivity implements VideoListContract.View, OnViewPagerListener {

    @BindView(id = R.id.recycler)
    private RecyclerView recyclerView;

    private MyLayoutManager myLayoutManager;

    private VideoListViewAdapter mAdapter;
    private int page = 0;

    private int classification_id = 0;

    private String post_title = "";

    private String nickname = "";
    private boolean isFirst = true;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_videolist);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new VideoListPresenter(this);
        page = getIntent().getIntExtra("position", 0);
        int totalPageNumber = getIntent().getIntExtra("totalPageNumber", 0);
        classification_id = getIntent().getIntExtra("classification_id", 0);
        myLayoutManager = new MyLayoutManager(this, OrientationHelper.VERTICAL, false);
        mAdapter = new VideoListViewAdapter(recyclerView, totalPageNumber);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setAdapter(mAdapter);
        myLayoutManager.setOnViewPagerListener(this);
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                Jzvd jzvd = view.findViewById(R.id.videoplayer);
//                if (jzvd != null && JZUtils.dataSourceObjectsContainsUri(jzvd.dataSourceObjects, JZMediaManager.getCurrentDataSource())) {
//                    JZVideoPlayer currentJzvd = JZVideoPlayerManager.getCurrentJzvd();
//                    if (currentJzvd != null && currentJzvd.currentScreen != JZVideoPlayer.SCREEN_WINDOW_FULLSCREEN) {
//                        JZVideoPlayer.releaseAllVideos();
//                    }
//                }
            }
        });
        playVideo(page + 1, 0);
    }


    @Override
    public void onInitComplete() {

    }

    @Override
    public void onPageRelease(boolean isNext, int position) {
        Log.e("VideoListView", "释放位置:" + position + " 下一页:" + isNext);
        if (isNext) {
            page--;
        } else {
            page++;
        }
        if (page <= 0) {
            return;
        }
        releaseVideo(page);
    }

    @Override
    public void onPageSelected(int position, boolean isBottom) {
        Log.e("VideoListView", "选中位置:" + position + " 是否是滑动到底部:" + isBottom);
        if (isBottom) {
            page++;
            playVideo(page, 1);
        } else {
            page--;
            playVideo(page, 2);
        }
        if (page <= 0) {
            return;
        }
    }


    private void releaseVideo(int index) {
        View itemView = recyclerView.getChildAt(index);
        Jzvd jzvd = itemView.findViewById(R.id.videoplayer);
        jzvd.release();

//        if (jzvd != null && JZUtils.dataSourceObjectsContainsUri(jzvd.dataSourceObjects, JzvdMgr.getCurrentDataSource())) {
//            Jzvd currentJzvd = JzvdMgr.getCurrentJzvd();
//            if (currentJzvd != null && currentJzvd.currentScreen != Jzvd.SCREEN_WINDOW_FULLSCREEN) {
//                Jzvd.releaseAllVideos();
//            }
//        }
        // JZVideoPlayer.goOnPlayOnPause();
//        jzVideoPlayerStandard.release();
    }

    private void playVideo(int position, int flag) {
        if (flag != 2) {
            ((VideoListContract.Presenter) mPresenter).getPostList(aty, post_title, nickname, classification_id, position, flag);
            return;
        }
        View itemView = recyclerView.getChildAt(position - 1);
        final JzvdStd jzVideoPlayerStandard = itemView.findViewById(R.id.videoplayer);
        jzVideoPlayerStandard.setUp(mAdapter.getItem(position - 1).getPicture(), "", JzvdStd.SCREEN_WINDOW_NORMAL);
        jzVideoPlayerStandard.onEvent(JZUserAction.ON_CLICK_START_AUTO_COMPLETE);
        jzVideoPlayerStandard.startVideo();
    }


    @Override
    public void setPresenter(VideoListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        CommunityBean communityBean = (CommunityBean) JsonUtil.getInstance().json2Obj(success, CommunityBean.class);
        if (communityBean.getData() == null && communityBean.getData().getResultX().size() <= 0 && page == 1) {
            errorMsg(getString(R.string.noMovement), 1);
            return;
        } else if (communityBean.getData() == null && communityBean.getData().getResultX().size() <= 0 && page > 1) {
            ViewInject.toast(getString(R.string.noMoreData));
            dismissLoadingDialog();
            return;
        }
        if (flag == 0) {
            mAdapter.clear();
            mAdapter.addNewData(communityBean.getData().getResultX());
        } else if (flag == 1) {
            mAdapter.addLastItem(communityBean.getData().getResultX().get(0));
        }
        View itemView = recyclerView.getChildAt(communityBean.getData().getCurrentPageNo() - 1);
        final Jzvd jzVideoPlayerStandard = itemView.findViewById(R.id.videoplayer);
        jzVideoPlayerStandard.setUp(mAdapter.getItem(communityBean.getData().getCurrentPageNo() - 1).getPicture(),"", JzvdStd.SCREEN_WINDOW_NORMAL);
        jzVideoPlayerStandard.onEvent(JZUserAction.ON_CLICK_START_AUTO_COMPLETE);
        jzVideoPlayerStandard.startVideo();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.goOnPlayOnPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Jzvd.releaseAllVideos();
        mAdapter.clear();
        mAdapter = null;
    }
}
