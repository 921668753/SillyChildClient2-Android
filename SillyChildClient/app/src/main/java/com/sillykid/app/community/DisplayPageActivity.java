package com.sillykid.app.community;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.sillykid.app.R;

/**
 * 个人发布展示页面
 */
public class DisplayPageActivity extends BaseActivity {


    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;


    private int user_id = 0;

    private int isRefresh = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_displaypage);
    }

    @Override
    public void initData() {
        super.initData();
        user_id = getIntent().getIntExtra("user_id", 0);
        isRefresh = getIntent().getIntExtra("isRefresh", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();


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
        }


    }
}
