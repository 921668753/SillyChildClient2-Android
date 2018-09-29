package com.sillykid.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.myview.WebViewLayout1;
import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.BuildConfig;
import com.sillykid.app.R;
import com.sillykid.app.constant.URLConstants;
import com.sillykid.app.homepage.message.interactivemessage.imuitl.RongIMUtil;
import com.sillykid.app.mine.sharingceremony.dialog.ShareBouncedDialog;
import com.sillykid.app.utils.SoftKeyboardUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import cn.bingoogolapple.titlebar.BGATitleBar;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.Conversation;

/**
 * 活动详情
 */
public class ActivityDetailActivity extends BaseActivity implements WebViewLayout1.WebViewCallBack {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout1 webViewLayout;

    private int id;

    private ShareBouncedDialog shareBouncedDialog = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_activitydetail);
    }

    @Override
    public void initData() {
        super.initData();
        id = getIntent().getIntExtra("id", 0);
        initShareBouncedDialog();
    }

    /**
     * 分享
     */
    private void initShareBouncedDialog() {
        shareBouncedDialog = new ShareBouncedDialog(this) {
            @Override
            public void share(SHARE_MEDIA platform) {
                umShare(platform);
            }
        };
    }

    @Override
    public void initWidget() {
        super.initWidget();
        webViewLayout.setTitleVisibility(false);
        webViewLayout.setWebViewCallBack(this);
        String url = URLConstants.ACTIVITYDETAILS + id;
        if (!StringUtils.isEmpty(url)) {
            webViewLayout.loadUrl(url);
        }
        initTitle();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        if (getIntent().getIntExtra("activity_state", 0) == 3) {
            ActivityTitleUtils.initToolbar(aty, getIntent().getStringExtra("title"), true, R.id.titlebar);
            return;
        }
        titlebar.setTitleText(getIntent().getStringExtra("title"));
        titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.product_details_share));
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                SoftKeyboardUtils.packUpKeyboard(aty);
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                //分享
                if (shareBouncedDialog == null) {
                    initShareBouncedDialog();
                }
                if (shareBouncedDialog != null & !shareBouncedDialog.isShowing()) {
                    shareBouncedDialog.show();
                }
            }
        };
        titlebar.setDelegate(simpleDelegate);

        //   ActivityTitleUtils.initToolbar(aty, webViewLayout.getWebView().getTitle(), true, R.id.titlebar);
    }

    @Override
    public void backOnclick(String id) {
//        Intent intent = new Intent();
//        setResult(RESULT_OK, intent);
        if (StringUtils.isEmpty(getIntent().getStringExtra("service_id"))) {
            finish();
            return;
        }
        showLoadingDialog(getString(R.string.customerServiceLoad));
        RongIMUtil.connectRongIM(aty);
        dismissLoadingDialog();
        //首先需要构造使用客服者的用户信息
        CSCustomServiceInfo csInfo = RongIMUtil.getCSCustomServiceInfo(aty);
        /**
         * 启动客户服聊天界面。
         * @param context           应用上下文。
         * @param customerServiceId 要与之聊天的客服 Id。
         * @param title             聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
         * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
         */
        RongIM.getInstance().startCustomerServiceChat(aty, getIntent().getStringExtra("service_id"), getIntent().getStringExtra("service_name"), csInfo);
//        RongIM.getInstance().setConversationToTop(Conversation.ConversationType.CUSTOMER_SERVICE, BuildConfig.RONGYUN_KEFU, true);
        finish();
    }

    @Override
    public void loadFailedError() {

    }

    /**
     * 直接分享
     */
    public void umShare(SHARE_MEDIA platform) {
        UMImage thumb;
        thumb = new UMImage(this, getIntent().getStringExtra("main_picture"));
        UMWeb web = new UMWeb(URLConstants.ACTIVITYDETAILS + id);
        web.setTitle(getIntent().getStringExtra("title"));//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(getIntent().getStringExtra("subtitle"));//描述
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    /**
     * 返回
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (shareBouncedDialog != null && shareBouncedDialog.isShowing()) {
                    shareBouncedDialog.dismiss();
                    return true;
                }
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
        if (shareBouncedDialog != null) {
            shareBouncedDialog.cancel();
        }
        shareBouncedDialog = null;
        webViewLayout.removeAllViews();
        webViewLayout = null;
    }
}
