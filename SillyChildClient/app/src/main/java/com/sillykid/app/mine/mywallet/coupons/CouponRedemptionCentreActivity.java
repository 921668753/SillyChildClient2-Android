package com.sillykid.app.mine.mywallet.coupons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.adapter.mine.mywallet.coupons.CouponRedemptionCentreViewAdapter;
import com.sillykid.app.entity.mine.mywallet.coupons.CouponRedemptionCentreBean;
import com.sillykid.app.entity.mine.mywallet.coupons.UnusedCouponsBean;
import com.sillykid.app.main.MinePresenter;
import com.sillykid.app.mine.sharingceremony.dialog.ShareBouncedDialog;
import com.sillykid.app.utils.GlideImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.sillykid.app.R;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.constant.URLConstants;
import com.sillykid.app.loginregister.LoginActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 领券中心
 */
public class CouponRedemptionCentreActivity extends BaseActivity implements CouponRedemptionCentreContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnItemChildClickListener {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.img_couponRedemption)
    private ImageView img_couponRedemption;

    @BindView(id = R.id.clv_couponRedemption)
    private ChildListView clv_couponRedemption;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    @BindView(id = R.id.tv_button, click = true)
    private TextView tv_button;

    private ShareBouncedDialog shareBouncedDialog = null;

    private String smallImg = "";

    private CouponRedemptionCentreViewAdapter mAdapter;
    private int clickPosition = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_couponredemptioncentre);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new CouponRedemptionCentrePresenter(this);
        mAdapter = new CouponRedemptionCentreViewAdapter(this);
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

    /**
     * 直接分享
     * SHARE_MEDIA.QQ
     */
    public void umShare(SHARE_MEDIA platform) {
        UMImage thumb = new UMImage(this, smallImg);
        String invite_code = PreferenceHelper.readString(aty, StringConstants.FILENAME, "invite_code", "");
        String url = URLConstants.REGISTERHTML + invite_code;
        UMWeb web = new UMWeb(url);
        web.setTitle(getString(R.string.couponRedemptionCentre));//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription("");
        new ShareAction(aty).setPlatform(platform)
//                .withText("hello")
//                .withMedia(thumb)
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
    public void initWidget() {
        super.initWidget();
        initTitle();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        clv_couponRedemption.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        titlebar.setTitleText(R.string.couponRedemptionCentre);
        titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.product_details_share));
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                finish();
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
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    mRefreshLayout.beginRefreshing();
                    return;
                }
                showActivity(aty, LoginActivity.class);
                break;
        }
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.tv_exchange) {
            clickPosition = position;
            showLoadingDialog(getString(R.string.dataLoad));
            ((CouponRedemptionCentreContract.Presenter) mPresenter).getCoupon(aty, mAdapter.getItem(position).getCoupon_id());
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((CouponRedemptionCentreContract.Presenter) mPresenter).getMemberCoupon(aty);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    @Override
    public void setPresenter(CouponRedemptionCentreContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            CouponRedemptionCentreBean couponRedemptionCentreBean = (CouponRedemptionCentreBean) JsonUtil.getInstance().json2Obj(success, CouponRedemptionCentreBean.class);
            if (couponRedemptionCentreBean.getData() == null || StringUtils.isEmpty(couponRedemptionCentreBean.getData().getPicture())) {
                img_couponRedemption.setVisibility(View.GONE);
            } else {
                img_couponRedemption.setVisibility(View.VISIBLE);
                GlideImageLoader.glideOrdinaryLoader(this, couponRedemptionCentreBean.getData().getPicture(), img_couponRedemption, R.mipmap.placeholderfigure);
            }
            if (couponRedemptionCentreBean.getData() == null || couponRedemptionCentreBean.getData().getCoupon_list() == null ||
                    couponRedemptionCentreBean.getData().getCoupon_list().size() <= 0) {
                errorMsg(getString(R.string.notGotCoupons), 0);
                return;
            }
            mAdapter.clear();
            mAdapter.addNewData(couponRedemptionCentreBean.getData().getCoupon_list());
        } else if (flag == 1) {
            ViewInject.toast(getString(R.string.getSuccess));
            mAdapter.removeItem(clickPosition);
            if (mAdapter.getCount() <= 0) {
                errorMsg(getString(R.string.notGotCoupons), 0);
            }
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusCouponRedemptionCentreEvent"));
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            mRefreshLayout.setPullDownRefreshEnable(false);
            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setVisibility(View.VISIBLE);
            tv_button.setVisibility(View.VISIBLE);
            if (isLogin(msg)) {
                img_err.setImageResource(R.mipmap.no_login);
                tv_hintText.setVisibility(View.GONE);
                tv_button.setText(getString(R.string.login));
                showActivity(aty, LoginActivity.class);
                return;
            } else if (msg.contains(getString(R.string.checkNetwork))) {
                img_err.setImageResource(R.mipmap.no_network);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            } else if (msg.contains(getString(R.string.notGotCoupons))) {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setVisibility(View.GONE);
            } else {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            }
        } else {
            if (isLogin(msg)) {
                showActivity(aty, LoginActivity.class);
                return;
            }
            ViewInject.toast(msg);
        }
    }


    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusLogOutEvent") && mPresenter != null) {
            ((CouponRedemptionCentreContract.Presenter) mPresenter).getMemberCoupon(aty);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }


}
