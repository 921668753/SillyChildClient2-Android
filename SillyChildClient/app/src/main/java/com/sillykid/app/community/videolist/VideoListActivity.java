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

import cn.jzvd.JZVideoPlayerStandard;


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
        mAdapter = new VideoListViewAdapter(recyclerView, 1);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setAdapter(mAdapter);
        myLayoutManager.setOnViewPagerListener(this);
          playVideo(page + 1);
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
     //   releaseVideo(page);
    }

    @Override
    public void onPageSelected(int position, boolean isBottom) {
        Log.e("VideoListView", "选中位置:" + position + " 是否是滑动到底部:" + isBottom);
        if (isBottom) {
            page++;
        } else {
            page--;
        }
        if (page <= 0) {
            return;
        }
        playVideo(page);
    }


    private void releaseVideo(int index) {
        View itemView = recyclerView.getChildAt(0);
        final JZVideoPlayerStandard jzVideoPlayerStandard = itemView.findViewById(R.id.videoplayer);
        jzVideoPlayerStandard.release();
    }

    private void playVideo(int position) {
        ((VideoListContract.Presenter) mPresenter).getPostList(aty, post_title, nickname, classification_id, position);
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
        mAdapter.clear();
        mAdapter.addNewData(communityBean.getData().getResultX());
//        View itemView = recyclerView.getChildAt(communityBean.getData().getCurrentPageNo() - 1);
//        final JZVideoPlayerStandard jzVideoPlayerStandard = itemView.findViewById(R.id.videoplayer);
//        jzVideoPlayerStandard.setUp(mAdapter.getItem(communityBean.getData().getCurrentPageNo() - 1).getPicture(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
//        JZVideoPlayer.setMediaInterface(new JZPLMediaPlayer());
//        jzVideoPlayerStandard.onEvent(JZUserAction.ON_CLICK_START_AUTO_COMPLETE);
//        jzVideoPlayerStandard.startVideo();
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
}
