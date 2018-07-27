package com.sillykid.app.homepage.hotvideo;

import com.common.cklibrary.common.BaseActivity;
import com.sillykid.app.R;

/**
 * 视频详情
 */
public class VideoDetailsActivity extends BaseActivity implements VideoDetailsContract.View{


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_videodetails);
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
