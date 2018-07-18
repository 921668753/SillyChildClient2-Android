package com.sillykid.app.homepage.chartercustom.chartercommon;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.mine.sharingceremony.dialog.ShareBouncedDialog;
import com.sillykid.app.entity.CharterDetailsBean;
import com.sillykid.app.homepage.chartercustom.bytheday.ByTheDayActivity;
import com.sillykid.app.homepage.chartercustom.transfer.TransferActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.mycollection.MyCollectionActivity1;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.Log;

import java.util.List;

/**
 * 包车详情
 * Created by Admin on 2017/9/29.
 */

public class CharterDetailsActivity extends BaseActivity implements CharterDetailsContract.View {

    /**
     * 返回
     */
    @BindView(id = R.id.img_back, click = true)
    ImageView img_back;


    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout webViewLayout;

    /**
     * 收藏
     */
    @BindView(id = R.id.img_collect, click = true)
    ImageView img_collect;

    /**
     * 分享
     */
    @BindView(id = R.id.img_share1, click = true)
    ImageView img_share1;

    /**
     * 联系客服
     */
    @BindView(id = R.id.tv_contactCustomer, click = true)
    TextView tv_contactCustomer;

    /**
     * 立即预定
     */
    @BindView(id = R.id.tv_reservationsNow, click = true)
    TextView tv_reservationsNow;

    private int type = 0;
    private int id = 0;
    private ShareBouncedDialog shareBouncedDialog;

    private int isCollect = 0;
    private String carTypeId;
    private String carTypeName;
    private String carSeatTotal;
    private String img;
    private String title;
    private String url;
    private String flyName;
    private Intent intentresult;
    private String costStatement;
    private String costCompensation;
    private String costCompensationLevel;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_charterdetails);
    }

    @Override
    public void initData() {
        super.initData();
        initShareDialog();
        id = getIntent().getIntExtra("id", 0);
        type = getIntent().getIntExtra("type", 1);
        mPresenter = new CharterDetailsPresenter(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        showLoadingDialog(getString(R.string.dataLoad));
        ((CharterDetailsContract.Presenter) mPresenter).getCharterDetails(id);
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
                resultToCollectionRoute();
                finish();
                break;
            case R.id.img_collect:
                ((CharterDetailsContract.Presenter) mPresenter).isLogin(1);
                break;
            case R.id.img_share1:
                if (shareBouncedDialog != null && !shareBouncedDialog.isShowing()) {
                    shareBouncedDialog.show();
                    break;
                }
                initShareDialog();
                shareBouncedDialog.show();
                break;
            case R.id.tv_contactCustomer:
                ((CharterDetailsContract.Presenter) mPresenter).isLogin(2);
                break;
            case R.id.tv_reservationsNow:
                ((CharterDetailsContract.Presenter) mPresenter).isLogin(3);
                break;
        }
    }


    @Override
    public void setPresenter(CharterDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            CharterDetailsBean charterDetailsBean = (CharterDetailsBean) JsonUtil.getInstance().json2Obj(success, CharterDetailsBean.class);
            if (charterDetailsBean == null) {
                errorMsg(getString(R.string.serverReturnsDataNull1), 0);
                return;
            }
            List<String> imgs = charterDetailsBean.getData().getImgs();
            img = imgs.get(0);
            title = charterDetailsBean.getData().getTitle();
            isCollect = charterDetailsBean.getData().getIsCollect();
            if (isCollect == 0) {
                img_collect.setImageResource(R.mipmap.btn_collect_normal);
            } else {
                img_collect.setImageResource(R.mipmap.btn_collect_pressed);
            }
            flyName = charterDetailsBean.getData().getFlyName();
//            carTypeId = charterDetailsBean.getResult().getCarTypeId();
            carTypeName = charterDetailsBean.getData().getCar_level_name();
            if (StringUtils.isEmpty(carTypeName)) {
                carTypeName = "";
            }
            carSeatTotal = charterDetailsBean.getData().getCar_seat_num();
            costStatement = charterDetailsBean.getData().getCostStatement();
            costCompensation = charterDetailsBean.getData().getCostCompensation();
            costCompensationLevel=charterDetailsBean.getData().getCostCompensationLevel();
        } else if (flag == 1) {
            ((CharterDetailsContract.Presenter) mPresenter).postCollectCharter(id + "", isCollect);
            return;
        } else if (flag == 2) {
         //   showActivity(aty, OverleafActivity.class);
        } else if (flag == 3) {
            jumpPage(type);
        } else if (flag == 4) {
            isCollect = 1;
            img_collect.setImageResource(R.mipmap.btn_collect_pressed);
            ViewInject.toast(getString(R.string.collectionSuccess));
        } else if (flag == 5) {
            isCollect = 0;
            img_collect.setImageResource(R.mipmap.btn_collect_normal);
            ViewInject.toast(getString(R.string.uncollectible));
        }
        dismissLoadingDialog();
    }

    private void jumpPage(int type) {
        switch (type) {
            case 1:
                Intent intent = new Intent(aty, TransferActivity.class);
                intent.putExtra("chageIcon", 0);
                intent.putExtra("pcpId", id);
                intent.putExtra("carTypeId", carTypeId);
                intent.putExtra("flyName", flyName);
                intent.putExtra("carTypeName", carTypeName);
                intent.putExtra("carSeatTotal", carSeatTotal);
                intent.putExtra("costStatement", costStatement);
                intent.putExtra("costCompensation", costCompensation);
                intent.putExtra("costCompensationLevel", costCompensationLevel);
                showActivity(aty, intent);
                break;
            case 3:
                Intent intent1 = new Intent(aty, TransferActivity.class);
                intent1.putExtra("chageIcon", 1);
                intent1.putExtra("pcpId", id);
                intent1.putExtra("carTypeId", carTypeId);
                intent1.putExtra("flyName", flyName);
                intent1.putExtra("carTypeName", carTypeName);
                intent1.putExtra("carSeatTotal", carSeatTotal);
                intent1.putExtra("costStatement", costStatement);
                intent1.putExtra("costCompensation", costCompensation);
                intent1.putExtra("costCompensationLevel", costCompensationLevel);
                showActivity(aty, intent1);
                break;
            case 2:
                Intent intent2 = new Intent(aty, ByTheDayActivity.class);
                intent2.putExtra("pcpId", id);
                intent2.putExtra("carTypeId", carTypeId);
                intent2.putExtra("carTypeName", carTypeName);
                intent2.putExtra("carSeatTotal", carSeatTotal);
                intent2.putExtra("costStatement", costStatement);
                intent2.putExtra("costCompensation", costCompensation);
                intent2.putExtra("costCompensationLevel", costCompensationLevel);
                showActivity(aty, intent2);
                break;
        }

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

    public void initView() {
        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
      //  url = URLConstants.PACKCARPRODUCTDETAIL + id + "&token=" + accessToken;
        webViewLayout.setTitleVisibility(false);
        webViewLayout.setWebViewCallBack(new WebViewLayout.WebViewCallBack() {
            @Override
            public void backOnclick() {
                resultToCollectionRoute();
                finish();
            }

            @Override
            public void loadFailedError() {
            }
        });
        if (!StringUtils.isEmpty(url)) {
            webViewLayout.loadUrl(url);
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

    /**
     * 返回是否取消收藏的数据到我的，我的收藏，线路中
     */
    private void resultToCollectionRoute() {
        if (isCollect == 0) {
            intentresult = new Intent(this, MyCollectionActivity1.class);
            setResult(0, intentresult);
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();//此方法不
        resultToCollectionRoute();
        finish();
    }

}
