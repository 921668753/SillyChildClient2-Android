package com.sillykid.app.community.dynamic;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.community.dynamic.UserEvaluationViewAdapter;
import com.sillykid.app.community.DisplayPageActivity;
import com.sillykid.app.community.dynamic.dynamiccomments.CommentDetailsActivity;
import com.sillykid.app.community.dynamic.dynamiccomments.DynamicCommentsActivity;
import com.sillykid.app.entity.community.dynamic.DynamicDetailsBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 动态详情
 */
public class DynamicDetailsActivity extends BaseActivity implements DynamicDetailsContract.View, AdapterView.OnItemClickListener, BGAOnItemChildClickListener, BGABanner.Delegate<ImageView, String>, BGABanner.Adapter<ImageView, String> {

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    @BindView(id = R.id.ll_author, click = true)
    private LinearLayout ll_author;

    @BindView(id = R.id.img_head)
    private ImageView img_head;

    @BindView(id = R.id.tv_nickName)
    private TextView tv_nickName;

    @BindView(id = R.id.tv_follow, click = true)
    private TextView tv_follow;

    @BindView(id = R.id.tv_content)
    private TextView tv_content;

    /**
     * 举报
     */
    @BindView(id = R.id.ll_report, click = true)
    private LinearLayout ll_report;

    @BindView(id = R.id.ll_userEvaluation, click = true)
    private LinearLayout ll_userEvaluation;

    @BindView(id = R.id.tv_userEvaluationNum)
    private TextView tv_userEvaluationNum;


    @BindView(id = R.id.clv_dynamicDetails)
    private ChildListView clv_dynamicDetails;


    @BindView(id = R.id.ll_zan, click = true)
    private LinearLayout ll_zan;

    @BindView(id = R.id.img_zan)
    private ImageView img_zan;

    @BindView(id = R.id.tv_zan)
    private TextView tv_zan;

    @BindView(id = R.id.tv_zanNum)
    private TextView tv_zanNum;


    @BindView(id = R.id.ll_collection, click = true)
    private LinearLayout ll_collection;

    @BindView(id = R.id.img_collection)
    private ImageView img_collection;

    @BindView(id = R.id.tv_collection)
    private TextView tv_collection;

    @BindView(id = R.id.tv_collectionNum)
    private TextView tv_collectionNum;


    @BindView(id = R.id.ll_comment, click = true)
    private LinearLayout ll_comment;

    @BindView(id = R.id.tv_commentNum)
    private TextView tv_commentNum;

    private int id = 0;

    private String title = "";

    private int user_id = 0;

    private int is_concern = 0;

    private int is_like = 0;

    private int is_collect = 0;

    private UserEvaluationViewAdapter mAdapter = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_dynamicdetails);
    }

    @Override
    public void initData() {
        super.initData();
        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        mPresenter = new DynamicDetailsPresenter(this);
        mAdapter = new UserEvaluationViewAdapter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((DynamicDetailsContract.Presenter) mPresenter).getDynamicDetails(id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        clv_dynamicDetails.setAdapter(mAdapter);
        clv_dynamicDetails.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);
        initBanner();
    }

    /**
     * 初始化轮播图
     */
    public void initBanner() {
        mForegroundBanner.setAutoPlayAble(true);
        mForegroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mForegroundBanner.setAllowUserScrollable(true);
        mForegroundBanner.setAutoPlayInterval(3000);
        // 初始化方式1：配置数据源的方式1：通过传入数据模型并结合 Adapter 的方式配置数据源。这种方式主要用于加载网络图片，以及实现少于3页时的无限轮播
        mForegroundBanner.setAdapter(this);
        mForegroundBanner.setDelegate(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_author:
                Intent intent = new Intent(aty, DisplayPageActivity.class);
                intent.putExtra("user_id", user_id);
                intent.putExtra("isRefresh", 0);
                showActivity(aty, intent);
                break;
            case R.id.tv_follow:
                String title = getString(R.string.attentionLoad);
                if (is_concern == 1) {
                    title = getString(R.string.cancelCttentionLoad);
                } else {
                    title = getString(R.string.attentionLoad);
                }
                showLoadingDialog(title);
                ((DynamicDetailsContract.Presenter) mPresenter).postAddConcern(user_id, 1);
                break;

            case R.id.ll_report:
                showLoadingDialog(getString(R.string.dataLoad));
                //((DynamicDetailsContract.Presenter)mPresenter).postAddConcern();
                break;
            case R.id.ll_userEvaluation:
                Intent intent1 = new Intent(aty, DynamicCommentsActivity.class);
                intent1.putExtra("id", id);
                showActivity(aty, intent1);
                break;
            case R.id.ll_zan:
                showLoadingDialog(getString(R.string.dataLoad));
                ((DynamicDetailsContract.Presenter) mPresenter).postAddLike(id);
                break;
            case R.id.ll_collection:
                showLoadingDialog(getString(R.string.dataLoad));
                if (is_collect == 1) {
                    ((DynamicDetailsContract.Presenter) mPresenter).postUnfavorite(id);
                } else {
                    ((DynamicDetailsContract.Presenter) mPresenter).postAddFavorite(id);
                }
                break;
            case R.id.ll_comment:
                showLoadingDialog(getString(R.string.dataLoad));
                //((DynamicDetailsContract.Presenter)mPresenter).postAddConcern();


                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mForegroundBanner.startAutoPlay();
    }


    @Override
    public void onPause() {
        super.onPause();
        mForegroundBanner.stopAutoPlay();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(aty, CommentDetailsActivity.class);
        // intent.putExtra("");
        showActivity(aty, intent);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.ll_giveLike) {


        } else if (childView.getId() == R.id.tv_revert) {


        }
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        GlideImageLoader.glideOrdinaryLoader(aty, model, itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
//        if (StringUtils.isEmpty(model.getAd_link())) {
//            return;
//        }
//        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
//        bannerDetails.putExtra("url", model.getAd_link());
//        bannerDetails.putExtra("title", model.getAd_name());
//        showActivity(aty, bannerDetails);
    }


    @Override
    public void setPresenter(DynamicDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            DynamicDetailsBean dynamicDetailsBean = (DynamicDetailsBean) JsonUtil.getInstance().json2Obj(success, DynamicDetailsBean.class);
            processLogic(dynamicDetailsBean.getData().getList());
            user_id = dynamicDetailsBean.getData().getMember_id();
            GlideImageLoader.glideLoader(this, dynamicDetailsBean.getData().getFace(), img_head, 0, R.mipmap.avatar);
            tv_nickName.setText(dynamicDetailsBean.getData().getNickname());
            is_concern = dynamicDetailsBean.getData().getIs_concern();
            if (is_concern == 1) {
                tv_follow.setText(getString(R.string.followed));
                tv_follow.setBackgroundResource(R.drawable.shape_followed);
                tv_follow.setTextColor(getResources().getColor(R.color.tabColors));
            } else {
                tv_follow.setText(getString(R.string.follow));
                tv_follow.setBackgroundResource(R.drawable.shape_follow);
                tv_follow.setTextColor(getResources().getColor(R.color.greenColors));
            }
            tv_content.setText(dynamicDetailsBean.getData().getContent());
            tv_userEvaluationNum.setText(dynamicDetailsBean.getData().getReview_number() + getString(R.string.evaluation1));
            is_like = dynamicDetailsBean.getData().getIs_like();
            tv_zanNum.setText(dynamicDetailsBean.getData().getLike_number());
            if (is_like == 1) {
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan1);
                tv_zan.setTextColor(getResources().getColor(R.color.greenColors));
                tv_zanNum.setTextColor(getResources().getColor(R.color.greenColors));
            } else {
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan);
                tv_zan.setTextColor(getResources().getColor(R.color.textColor));
                tv_zanNum.setTextColor(getResources().getColor(R.color.textColor));
            }
            is_collect = dynamicDetailsBean.getData().getIs_collect();
            tv_collectionNum.setText(dynamicDetailsBean.getData().getCollection_number());
            if (is_collect == 1) {
                img_collection.setImageResource(R.mipmap.dynamicdetails_collection1);
                tv_collection.setTextColor(getResources().getColor(R.color.greenColors));
                tv_collectionNum.setTextColor(getResources().getColor(R.color.greenColors));
            } else {
                img_collection.setImageResource(R.mipmap.dynamicdetails_collection);
                tv_collection.setTextColor(getResources().getColor(R.color.textColor));
                tv_collectionNum.setTextColor(getResources().getColor(R.color.textColor));
            }
            tv_commentNum.setText(dynamicDetailsBean.getData().getReview_number());
            if (dynamicDetailsBean.getData().getComment() != null && dynamicDetailsBean.getData().getComment().size() > 0) {
                mAdapter.clear();
                mAdapter.addNewData(dynamicDetailsBean.getData().getComment());
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            if (is_concern == 1) {
                is_concern = 0;
                tv_follow.setText(getString(R.string.follow));
                tv_follow.setBackgroundResource(R.drawable.shape_follow);
                tv_follow.setTextColor(getResources().getColor(R.color.greenColors));
                ViewInject.toast(getString(R.string.focusSuccess));
            } else {
                is_concern = 1;
                tv_follow.setText(getString(R.string.followed));
                tv_follow.setBackgroundResource(R.drawable.shape_followed);
                tv_follow.setTextColor(getResources().getColor(R.color.tabColors));
                ViewInject.toast(getString(R.string.attentionSuccess));
            }
            dismissLoadingDialog();
        } else if (flag == 2) {
            img_collection.setImageResource(R.mipmap.dynamicdetails_collection);
            tv_collection.setTextColor(getResources().getColor(R.color.textColor));
            tv_collectionNum.setTextColor(getResources().getColor(R.color.textColor));
            is_collect = 0;
            tv_collectionNum.setText(StringUtils.toInt(tv_collectionNum.getText().toString(), 0) - 1 + "");
            ViewInject.toast(getString(R.string.uncollectible));
        } else if (flag == 3) {
            is_collect = 1;
            img_collection.setImageResource(R.mipmap.dynamicdetails_collection1);
            tv_collection.setTextColor(getResources().getColor(R.color.greenColors));
            tv_collectionNum.setText(StringUtils.toInt(tv_collectionNum.getText().toString(), 0) + 1 + "");
            tv_collectionNum.setTextColor(getResources().getColor(R.color.greenColors));
            ViewInject.toast(getString(R.string.collectionSuccess));
        } else if (flag == 4) {
            if (is_like == 1) {
                is_like = 0;
                tv_zanNum.setText(StringUtils.toInt(tv_zanNum.getText().toString(), 0) - 1 + "");
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan);
                tv_zan.setTextColor(getResources().getColor(R.color.textColor));
                tv_zanNum.setTextColor(getResources().getColor(R.color.textColor));
                ViewInject.toast(getString(R.string.cancelZanSuccess));
            } else {
                is_like = 1;
                tv_zanNum.setText(StringUtils.toInt(tv_zanNum.getText().toString(), 0) + 1 + "");
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan1);
                tv_zan.setTextColor(getResources().getColor(R.color.greenColors));
                tv_zanNum.setTextColor(getResources().getColor(R.color.greenColors));
                ViewInject.toast(getString(R.string.zanSuccess));
            }
            dismissLoadingDialog();
        } else if (flag == 5) {


        }
    }

    /**
     * 广告轮播图
     */
    @SuppressWarnings("unchecked")
    private void processLogic(List<String> list) {
        if (list != null && list.size() > 0) {
            if (list.size() == 1) {
                mForegroundBanner.setAutoPlayAble(false);
                mForegroundBanner.setAllowUserScrollable(false);
            } else {
                mForegroundBanner.setAutoPlayAble(true);
                mForegroundBanner.setAllowUserScrollable(true);
            }
            mForegroundBanner.setBackground(null);
            mForegroundBanner.setData(list, null);
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null) {
            ((DynamicDetailsContract.Presenter) mPresenter).getDynamicDetails(id);
        }
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
        } else {
            ViewInject.toast(msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }


}
