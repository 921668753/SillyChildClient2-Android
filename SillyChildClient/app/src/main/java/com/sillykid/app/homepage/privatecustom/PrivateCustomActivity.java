package com.sillykid.app.homepage.privatecustom;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;


/**
 * 私人定制  填写需求
 */
public class PrivateCustomActivity extends BaseActivity {
    @Override
    public void setRootView() {
        setContentView(R.layout.activity_privatecustom);
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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.privateOrdering), true, R.id.titlebar);
    }




}
