package com.sillykid.app.trip.strategy;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.mine.sharingceremony.dialog.ShareBouncedDialog;
import com.sillykid.app.entity.StrategyDetailsBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.mycollection.MyCollectionActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.Log;

/**
 * 攻略详情
 * Created by Admin on 2017/9/7.
 */

public class StrategyDetailsActivity extends BaseActivity implements StrategyDetailsContract.View {

    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout webViewLayout;

    @BindView(id = R.id.img_zan)
    private ImageView img_zan;

    @BindView(id = R.id.ll_zan, click = true)
    private LinearLayout ll_zan;

    @BindView(id = R.id.ll_share, click = true)
    private LinearLayout ll_share;

    @BindView(id = R.id.tv_read)
    private TextView tv_read;

    @BindView(id = R.id.ll_dynamicCollection, click = true)
    private LinearLayout ll_dynamicCollection;

    @BindView(id = R.id.img_collect)
    private ImageView img_collect;

    private ShareBouncedDialog shareBouncedDialog;
    private String title;
    private int guide_id;
    private int isCollect = 0;
    private int isPraise = 0;
    private Intent intentresult;
    private String img;
    private String url;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_strategydetails);
    }

    @Override
    public void initData() {
        super.initData();
        title = getIntent().getStringExtra("title");
        guide_id = getIntent().getIntExtra("guide_id", 0);
        mPresenter = new StrategyDetailsPresenter(this);
        int is_read = getIntent().getIntExtra("is_read", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        if (is_read == 0) {
            ((StrategyDetailsContract.Presenter) mPresenter).getStrategyDetails(guide_id);
        } else {
            int message_id = getIntent().getIntExtra("message_id", 0);
            ((StrategyDetailsContract.Presenter) mPresenter).getReadMessage(message_id + "");
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initView();
    }

    public void initView() {
        //  String url = getIntent().getStringExtra("url");
        if (StringUtils.isEmpty(title)) {
            // initTitle(getString(R.string.app_name));
            webViewLayout.setTitleText(R.string.app_name);
        } else {
            //  initTitle(title);
            webViewLayout.setTitleText(title);
        }
        webViewLayout.setBackImgResource(R.mipmap.back);
        webViewLayout.setTitleVisibility(true);
        webViewLayout.setWebViewCallBack(new WebViewLayout.WebViewCallBack() {
            @Override
            public void backOnclick() {
                StrategyDetailsActivity.this.finish();
            }

            @Override
            public void loadFailedError() {
//              //  initTitle(title);
//                webViewLayout.setTitleText(title);
            }
        });
        //     if (!StringUtils.isEmpty(url)) {
        //  initTitle(title);
        //    webViewLayout.setTitleText(title);
        //   } else {
        //     initTitle(title);
        webViewLayout.setTitleText(title);
   //     url = URLConstants.GUIDEDETAIL + guide_id;
        webViewLayout.loadUrl(url);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_zan:
                showLoadingDialog(getString(R.string.dataLoad));
                ((StrategyDetailsContract.Presenter) mPresenter).praiseStrategyDetails(String.valueOf(guide_id), isPraise);
                break;
            case R.id.ll_share:
                if (shareBouncedDialog != null && !shareBouncedDialog.isShowing()) {
                    shareBouncedDialog.show();
                    break;
                }
                initShareDialog();
                shareBouncedDialog.show();
                break;

            case R.id.ll_dynamicCollection:
                showLoadingDialog(getString(R.string.dataLoad));
                ((StrategyDetailsContract.Presenter) mPresenter).collectStrategy(guide_id, isCollect);
                break;
        }
    }

    @Override
    public void setPresenter(StrategyDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            StrategyDetailsBean strategyDetailsBean = (StrategyDetailsBean) JsonUtil.getInstance().json2Obj(success, StrategyDetailsBean.class);
            if (strategyDetailsBean.getData().getInfo() != null && !StringUtils.isEmpty(strategyDetailsBean.getData().getInfo().getRead_num())) {
                tv_read.setText(strategyDetailsBean.getData().getInfo().getRead_num() + getString(R.string.views));
            } else {
                tv_read.setText("");
            }
            isCollect = strategyDetailsBean.getData().getInfo().getIsCollect();
            if (isCollect == 1) {
                img_collect.setImageResource(R.mipmap.btn_collect_pressed);
            } else {
                img_collect.setImageResource(R.mipmap.btn_collect_normal1);
            }
            isPraise = strategyDetailsBean.getData().getInfo().getIsPraise();
            if (isPraise == 1) {
                img_zan.setImageResource(R.mipmap.btn_favor_pressed);
            } else {
                img_zan.setImageResource(R.mipmap.btn_favor_normal1);
            }
            title = strategyDetailsBean.getData().getInfo().getTitle();
            img = strategyDetailsBean.getData().getInfo().getCover_img();
        } else if (flag == 1) {
            isCollect = 1;
            img_collect.setImageResource(R.mipmap.btn_collect_pressed);
            ViewInject.toast(getString(R.string.collectionSuccess));
        } else if (flag == 2) {
            isCollect = 0;
            img_collect.setImageResource(R.mipmap.btn_collect_normal1);
            ViewInject.toast(getString(R.string.uncollectible));
        } else if (flag == 3) {
            isPraise = 1;
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshingHomePageFragment", true);
            img_zan.setImageResource(R.mipmap.btn_favor_pressed);
            ViewInject.toast(getString(R.string.thumbSuccess));
        } else if (flag == 4) {
            isPraise = 0;
            img_zan.setImageResource(R.mipmap.btn_favor_normal1);
            ViewInject.toast(getString(R.string.unThumbSuccess));
        } else if (flag == 5) {
            ((StrategyDetailsContract.Presenter) mPresenter).getStrategyDetails(guide_id);
            return;
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();//此方法不
        resultToCollectionRoute();
        finish();
    }

    /**
     * 返回是否取消收藏的攻略到我的，我的发布，收藏的攻略
     */
    private void resultToCollectionRoute() {
        if (isCollect == 0) {
            if (intentresult == null) intentresult = new Intent(this, MyCollectionActivity.class);
            setResult(0, intentresult);
        }
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
        if (StringUtils.isEmpty(img)) {
            thumb = new UMImage(this, R.mipmap.android_template);
        } else {
            thumb = new UMImage(this, img);
        }
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(getString(R.string.share_driver_description));//描述
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
            Log.d("plat", "platform" + platform);
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
                Log.d("throw", "throw:" + t.getMessage());
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
}

