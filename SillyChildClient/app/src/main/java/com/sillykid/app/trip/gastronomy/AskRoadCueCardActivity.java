package com.sillykid.app.trip.gastronomy;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.sillykid.app.R;

/**
 * 问路提示卡   横屏
 * Created by Admin on 2017/9/27.
 */

public class AskRoadCueCardActivity extends BaseActivity {


    @BindView(id = R.id.tv_askRoadCueCard)
    private TextView tv_askRoadCueCard;

    @BindView(id = R.id.img_back, click = true)
    private TextView img_back;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_askroadcuecard);
    }

    @Override
    public void initData() {
        super.initData();
        tv_askRoadCueCard.setText(getIntent().getStringExtra("askRoadCueCard"));
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }
}
