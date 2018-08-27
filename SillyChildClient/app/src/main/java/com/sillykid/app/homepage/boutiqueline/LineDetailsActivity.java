package com.sillykid.app.homepage.boutiqueline;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.constant.URLConstants;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.sharingceremony.dialog.ShareBouncedDialog;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;


/**
 * 线路详情
 */
public class LineDetailsActivity extends BaseActivity implements LineDetailsContract.View {

    @BindView(id = R.id.img_share, click = true)
    private ImageView img_share;


    @BindView(id = R.id.tv_reservationsNow, click = true)
    private TextView tv_reservationsNow;

    private ShareBouncedDialog shareBouncedDialog = null;

    private int product_id = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_linedetails);
    }

    @Override
    public void initData() {
        super.initData();
        product_id = getIntent().getIntExtra("id", 0);
        mPresenter = new LineDetailsPresenter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((LineDetailsContract.Presenter) mPresenter).getProductDetails(product_id);
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


    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_share:
                //分享
                if (shareBouncedDialog == null) {
                    initShareBouncedDialog();
                }
                if (shareBouncedDialog != null & !shareBouncedDialog.isShowing()) {
                    shareBouncedDialog.show();
                }
                break;
            case R.id.tv_reservationsNow:
                //分享
                Intent intent = new Intent(aty, DueDemandActivity.class);
                intent.putExtra("product_id", product_id);
                showActivity(aty, intent);
                break;
        }
    }

    @Override
    public void setPresenter(LineDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
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

    /**
     * 直接分享
     * SHARE_MEDIA.QQ
     */
    public void umShare(SHARE_MEDIA platform) {
//        UMImage thumb = new UMImage(this, smallImg);
//        String invite_code = PreferenceHelper.readString(aty, StringConstants.FILENAME, "invite_code", "");
//        String url = URLConstants.REGISTERHTML + invite_code;
//        UMWeb web = new UMWeb(url);
////        web.setTitle(title);//标题
////        web.setThumb(thumb);  //缩略图
////        web.setDescription(brief);
//        new ShareAction(aty).setPlatform(platform)
////                .withText("hello")
////                .withMedia(thumb)
//                .withMedia(web)
//                .setCallback(umShareListener)
//                .share();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
            showLoadingDialog(getString(R.string.shareJumpLoad));
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            dismissLoadingDialog();
            Log.d("throw", "platform" + platform);
            ViewInject.toast(getString(R.string.shareSuccess));
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            dismissLoadingDialog();
            if (t.getMessage().contains(getString(R.string.authoriseErr3))) {
                ViewInject.toast(getString(R.string.authoriseErr2));
                return;
            }
            ViewInject.toast(getString(R.string.shareError));
            //   ViewInject.toast(t.getMessage());
            Log.d("throw", "throw:" + t.getMessage());
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Log.d("throw", "throw:" + "onCancel");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
        if (shareBouncedDialog != null) {
            shareBouncedDialog.cancel();
        }
        shareBouncedDialog = null;
//        webViewLayout.removeAllViews();
//        webViewLayout = null;
    }
}
