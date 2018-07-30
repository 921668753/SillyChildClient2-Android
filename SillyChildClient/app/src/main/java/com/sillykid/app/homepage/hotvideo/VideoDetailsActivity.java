package com.sillykid.app.homepage.hotvideo;

import com.common.cklibrary.common.BaseActivity;
import com.sillykid.app.R;

/**
 * 视频详情
 */
public class VideoDetailsActivity extends BaseActivity implements VideoDetailsContract.View {

    private int id = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_videodetails);
    }

    @Override
    public void initData() {
        super.initData();
        id = getIntent().getIntExtra("id", 0);
        mPresenter = new VideoDetailsPresenter(this);
        ((VideoDetailsContract.Presenter) mPresenter).getVideoDetails(id);
    }

    @Override
    public void setPresenter(VideoDetailsContract.Presenter presenter) {


    }

    @Override
    public void getSuccess(String success, int flag) {

    }

    @Override
    public void errorMsg(String msg, int flag) {

    }
}
