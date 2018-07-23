package com.sillykid.app.community.dynamic.dynamiccomments;

import com.common.cklibrary.common.BaseActivity;
import com.sillykid.app.R;

/**
 * 动态评论
 */
public class DynamicCommentsActivity extends BaseActivity implements DynamicCommentsContract.View {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_dynamiccomments);
    }

    @Override
    public void setPresenter(DynamicCommentsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {

    }

    @Override
    public void errorMsg(String msg, int flag) {

    }
}
