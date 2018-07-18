package com.sillykid.app.homepage.chartercustom.companyguide;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.WebViewLayout1;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.mine.sharingceremony.dialog.ShareBouncedDialog;
import com.sillykid.app.entity.CompanyGuideDetailsBean;
import com.sillykid.app.homepage.chartercustom.routes.RouteDetailsActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * 司导详情
 * Created by Admin on 2017/8/16.
 */

public class CompanyGuideDetailsActivity extends BaseActivity implements CompanyGuideDetailsContract.View {

    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout1 webViewLayout;

    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    @BindView(id = R.id.img_collectNormal, click = true)
    private ImageView img_collectNormal;

    @BindView(id = R.id.img_share, click = true)
    private ImageView img_share;

    private ShareBouncedDialog shareBouncedDialog;

    private String drv_id;
    private String url;
    private String coverImg;
    private String title;
    private String share_driver_description;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_companyguidedetails);
    }

    @Override
    public void initData() {
        super.initData();
        drv_id = getIntent().getStringExtra("drv_id");
        mPresenter = new CompanyGuideDetailsPresenter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((CompanyGuideDetailsContract.Presenter) mPresenter).getCompanyGuideDetails(drv_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initView();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_collectNormal:

                break;
            case R.id.img_share:
                if (shareBouncedDialog != null && !shareBouncedDialog.isShowing()) {
                    shareBouncedDialog.show();
                    break;
                }
                initShareDialog();
                shareBouncedDialog.show();
                break;
        }
    }

    private void initView() {
     //   url = URLConstants.COMPANYGUIDEDETAILS1;
        webViewLayout.setTitleVisibility(false);

        //  webViewLayout.setBackImgResource(R.mipmap.back);
        webViewLayout.setWebViewCallBack(new WebViewLayout1.WebViewCallBack() {
            @Override
            public void backOnclick(String id) {
                if (StringUtils.isEmpty(id)) {
                    finish();
                } else {
                    Intent intent = new Intent(aty, RouteDetailsActivity.class);
                    intent.putExtra("line_id", id);
                    showActivity(aty, intent);
                }
            }

            @Override
            public void loadFailedError() {

            }
        });
        if (!StringUtils.isEmpty(url)) {
            webViewLayout.loadUrl(url + drv_id);
        }
        webViewLayout.setOnScrollChangedCallback(new WebViewLayout1.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                Log.d("tag11", "left" + dx);
                Log.d("tag11", "top" + dy);
            }
        });
    }

    /**
     * 初始化分享
     */
    private void initShareDialog() {
        shareBouncedDialog = null;
        shareBouncedDialog = new ShareBouncedDialog(this) {
            @Override
            public void share(SHARE_MEDIA platform) {
                umShare(platform);
            }
        };
    }

    /**
     * 直接分享
     */
    public void umShare(SHARE_MEDIA platform) {
        UMImage thumb;
        if (StringUtils.isEmpty(coverImg)) {
            thumb = new UMImage(this, R.mipmap.android_template);
        } else {
            thumb = new UMImage(this, coverImg);
        }
        UMWeb web = new UMWeb(url + drv_id);
        web.setTitle(title);//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(share_driver_description);//描述
        new ShareAction(aty).setPlatform(platform)
//                .withText("hello")
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
            showLoadingDialog(getString(R.string.shareJumpLoad));
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            com.umeng.socialize.utils.Log.d("plat", "platform" + platform);
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.shareSuccess));
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            dismissLoadingDialog();
            if (t.getMessage().contains(getString(R.string.notInstalled))) {
                ViewInject.toast(getString(R.string.notInstalled1));
                return;
            }
            ViewInject.toast(getString(R.string.shareError));
            if (t != null) {
                com.umeng.socialize.utils.Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            dismissLoadingDialog();
            //  ViewInject.toast(getString(R.string.shareError));
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        shareBouncedDialog = null;
    }

    @Override
    public void setPresenter(CompanyGuideDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        CompanyGuideDetailsBean companyGuideDetailsBean = (CompanyGuideDetailsBean) JsonUtil.getInstance().json2Obj(success, CompanyGuideDetailsBean.class);
        share_driver_description = companyGuideDetailsBean.getData().getPreson_info().getContent();
        coverImg = companyGuideDetailsBean.getData().getPreson_info().getCover_img();
        title = companyGuideDetailsBean.getData().getPreson_info().getSeller_name();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }
}
