package com.sillykid.app.mine.myfans;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;


/**
 * 我的粉丝
 */
public class MyFansActivity extends BaseActivity {


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myfans);
    }


    @Override
    public void initData() {
        super.initData();


    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myFans), true, R.id.titlebar);
    }











}
