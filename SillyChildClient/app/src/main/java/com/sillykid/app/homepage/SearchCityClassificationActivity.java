package com.sillykid.app.homepage;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.sillykid.app.R;

/**
 * 城市搜索分类选择
 */
public class SearchCityClassificationActivity extends BaseActivity {

    /**
     * 搜索
     */
    @BindView(id = R.id.tv_search)
    private TextView tv_search;

    @BindView(id = R.id.tv_cancel, click = true)
    private TextView tv_cancel;

    @BindView(id = R.id.tv_airportPickup, click = true)
    private TextView tv_airportPickup;

    @BindView(id = R.id.tv_airportDropOff, click = true)
    private TextView tv_airportDropOff;

    @BindView(id = R.id.tv_route, click = true)
    private TextView tv_route;

    @BindView(id = R.id.tv_charter, click = true)
    private TextView tv_charter;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_searchcityclassification);
    }

    @Override
    public void initData() {
        super.initData();

    }


    @Override
    public void initWidget() {
        super.initWidget();
        tv_search.setText(getIntent().getStringExtra("name"));
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_airportPickup:

                break;
            case R.id.tv_airportDropOff:

                break;
            case R.id.tv_route:

                break;
            case R.id.tv_charter:

                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
