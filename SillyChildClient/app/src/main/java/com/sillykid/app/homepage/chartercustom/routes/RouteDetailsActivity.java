package com.sillykid.app.homepage.chartercustom.routes;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.mine.sharingceremony.dialog.ShareBouncedDialog;
import com.sillykid.app.entity.CollectLineBean;
import com.sillykid.app.entity.RouteDetailsBean;
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
 * 路线详情
 * Created by Admin on 2017/8/16.
 */

public class RouteDetailsActivity extends BaseActivity implements RouteDetailsContract.View {


    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    @BindView(id = R.id.img_collect, click = true)
    private ImageView img_collect;

    @BindView(id = R.id.img_share, click = true)
    private ImageView img_share;

    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout webViewLayout;

    @BindView(id = R.id.ll_bottom)
    private LinearLayout ll_bottom;

    @BindView(id = R.id.tv_contactCustomer, click = true)
    private TextView tv_contactCustomer;

    @BindView(id = R.id.tv_bottom, click = true)
    private TextView tv_bottom;

    private ShareBouncedDialog shareBouncedDialog;
    private String line_id;
    private String line_title;
    private String line_price;
    private String action = "collect";
    private int playDay = 0;
    private String url;
    private String coverImg;
    private Intent intentresult;
    private String costStatement;
    private String costCompensation;
    private String costCompensationLevel;


//    private RouteDetailsViewAdapter routeDetailsViewAdapter;
//
//    private ItemRouteDetailsViewAdapter itemRouteDetailsViewAdapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_routedetails);
    }

    @Override
    public void initData() {
        super.initData();
        initShareDialog();
        mPresenter = new RouteDetailsPresenter(this);
        line_id = getIntent().getStringExtra("line_id");
        boolean orderDetailTag = getIntent().getBooleanExtra("orderDetailTag", false);
        if (orderDetailTag) {
            ll_bottom.setVisibility(View.GONE);
        }
        line_title = getIntent().getStringExtra("line_title");
        line_price = getIntent().getStringExtra("line_price");
//        routeDetailsViewAdapter = new RouteDetailsViewAdapter(this);
//        itemRouteDetailsViewAdapter = new ItemRouteDetailsViewAdapter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        showLoadingDialog(getString(R.string.dataLoad));
        ((RouteDetailsContract.Presenter) mPresenter).getRouteDetails(line_id);
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
                showLoadingDialog(getString(R.string.dataLoad));
                ((RouteDetailsContract.Presenter) mPresenter).postCollectLine(action, String.valueOf(line_id));
                break;
            case R.id.img_share:
                if (shareBouncedDialog != null && !shareBouncedDialog.isShowing()) {
                    shareBouncedDialog.show();
                    break;
                }
                initShareDialog();
                shareBouncedDialog.show();
                break;


            case R.id.tv_contactCustomer:
                ((RouteDetailsContract.Presenter) mPresenter).isLogin(4);
                break;

            case R.id.tv_bottom:
                ((RouteDetailsContract.Presenter) mPresenter).isLogin(3);
                break;
        }
    }

    public void initView() {
        String title = getIntent().getStringExtra("title");
     //   url = URLConstants.ROUTEDETAILS + line_id;
        webViewLayout.setTitleText(title);
        webViewLayout.setBackImgResource(R.mipmap.back);
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
//        webViewLayout.setOnScrollChangedCallback(new WebViewLayout.OnScrollChangedCallback() {
//            @Override
//            public void onScroll(int dx, int dy) {
//
//            }
//        });
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
        UMWeb web = new UMWeb(url);
        web.setTitle(line_title);//标题
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

    @Override
    public void setPresenter(RouteDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            action = "cancel";
            CollectLineBean collectLineBean = (CollectLineBean) JsonUtil.getInstance().json2Obj(success, CollectLineBean.class);
            img_collect.setImageResource(R.mipmap.btn_collect_pressed);
            ViewInject.toast(getString(R.string.collectionSuccess));
        } else if (flag == 1) {
            action = "collect";
            CollectLineBean collectLineBean = (CollectLineBean) JsonUtil.getInstance().json2Obj(success, CollectLineBean.class);
            img_collect.setImageResource(R.mipmap.btn_collect_normal);
            ViewInject.toast(getString(R.string.uncollectible));
        } else if (flag == 2) {
            RouteDetailsBean detailsBean = (RouteDetailsBean) JsonUtil.getInstance().json2Obj(success, RouteDetailsBean.class);
            if (detailsBean.getData().getIsCollect() == 0) {
                img_collect.setImageResource(R.mipmap.btn_collect_normal);
                action = "collect";
            } else {
                action = "cancel";
                img_collect.setImageResource(R.mipmap.btn_collect_pressed);
            }
            line_price = detailsBean.getData().getLinePrice();
            coverImg = detailsBean.getData().getCoverImg();
            costStatement = detailsBean.getData().getCostStatement();
            costCompensation = detailsBean.getData().getCostCompensation();
            costCompensationLevel=detailsBean.getData().getCostCompensationLevel();
            playDay = detailsBean.getData().getPlayDay();
        } else if (flag == 3) {
            Intent intent = new Intent(aty, FillInBasicInformationActivity.class);
            intent.putExtra("line_id", line_id);
            intent.putExtra("line_title", line_title);
            intent.putExtra("line_price", line_price);
            intent.putExtra("costStatement", costStatement);
            intent.putExtra("costCompensation", costCompensation);
            intent.putExtra("costCompensationLevel", costCompensationLevel);
            intent.putExtra("playDay", playDay);
            showActivity(aty, intent);
        } else if (flag == 4) {
           // showActivity(aty, OverleafActivity.class);
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
//        if (msg != null && msg.equals(getString(R.string.collectedRoute))) {
//            img_collect.setImageResource(R.mipmap.btn_collect_pressed);
//            action = "collect";
//            return;
//        }
        ViewInject.toast(msg);
    }

    /**
     * 返回是否取消收藏的数据到我的，我的收藏，线路中
     */
    private void resultToCollectionRoute() {
        if (action.equals("collect")) {
            intentresult = new Intent(this, MyCollectionActivity.class);
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
