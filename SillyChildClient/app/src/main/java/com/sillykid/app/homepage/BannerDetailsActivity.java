package com.sillykid.app.homepage;


import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;


/**
 * 轮播图详情
 * Created by Administrator on 2017/6/6.
 */

public class BannerDetailsActivity extends BaseActivity {

    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout webViewLayout;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_bannerdetails);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        initView();
    }

//    /**
//     * 设置标题
//     */
//    public void initTitle() {
//        String title = getIntent().getStringExtra("title");
//        ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);
//    }

    public void initView() {
        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");
        webViewLayout.setTitleText(title);
        webViewLayout.setBackImgResource(R.mipmap.back);
        webViewLayout.setTitleVisibility(true);
        webViewLayout.setWebViewCallBack(new WebViewLayout.WebViewCallBack() {
            @Override
            public void backOnclick() {
                BannerDetailsActivity.this.finish();
            }

            @Override
            public void loadFailedError() {
            }
        });
        if (!StringUtils.isEmpty(url)) {
            webViewLayout.loadUrl(url);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webViewLayout.removeAllViews();
        webViewLayout = null;
    }
}
