package com.sillykid.app.community.dynamic;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.custommediaplayer.JZPLMediaPlayer;
import com.common.cklibrary.utils.myview.ChildListView;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.DensityUtils;
import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.community.dynamic.UserEvaluationViewAdapter;
import com.sillykid.app.community.DisplayPageActivity;
import com.sillykid.app.community.dynamic.dialog.ReportBouncedDialog;
import com.sillykid.app.community.dynamic.dialog.RevertBouncedDialog;
import com.sillykid.app.community.dynamic.dialog.ShareBouncedDialog;
import com.sillykid.app.community.dynamic.dynamiccomments.CommentDetailsActivity;
import com.sillykid.app.community.dynamic.dynamiccomments.DynamicCommentsActivity;
import com.sillykid.app.constant.URLConstants;
import com.sillykid.app.entity.community.dynamic.DynamicDetailsBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;


import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUserAction;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdMgr;
import cn.jzvd.JzvdStd;

import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

/**
 * 动态视频详情
 */
public class DynamicVideoDetailsActivity extends BaseActivity implements DynamicDetailsContract.View, AdapterView.OnItemClickListener, BGAOnItemChildClickListener {

    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;


    @BindView(id = R.id.img_share, click = true)
    private ImageView img_share;

    @BindView(id = R.id.ll_title)
    private LinearLayout ll_title;

    @BindView(id = R.id.ll_title1)
    private LinearLayout ll_title1;

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.tv_fullScreen, click = true)
    private TextView tv_fullScreen;

    @BindView(id = R.id.rl_videoplayer)
    private RelativeLayout rl_videoplayer;

    @BindView(id = R.id.videoplayer)
    private JzvdStd jzVideoPlayerStandard;

    @BindView(id = R.id.ll_author, click = true)
    private LinearLayout ll_author;

    @BindView(id = R.id.img_head)
    private ImageView img_head;

    @BindView(id = R.id.tv_nickName)
    private TextView tv_nickName;

    @BindView(id = R.id.ll_content1, click = true)
    private LinearLayout ll_content1;

    @BindView(id = R.id.tv_content1)
    private TextView tv_content1;

//    @BindView(id = R.id.tv_more1, click = true)
//    private TextView tv_more1;

    @BindView(id = R.id.tv_follow, click = true)
    private TextView tv_follow;

    @BindView(id = R.id.tv_content)
    private TextView tv_content;

    /**
     * 举报
     */
    @BindView(id = R.id.ll_report, click = true)
    private LinearLayout ll_report;

    @BindView(id = R.id.ll_report1)
    private LinearLayout ll_report1;

    @BindView(id = R.id.ll_userEvaluation, click = true)
    private LinearLayout ll_userEvaluation;

    @BindView(id = R.id.tv_userEvaluationNum)
    private TextView tv_userEvaluationNum;

    @BindView(id = R.id.clv_dynamicDetails)
    private ChildListView clv_dynamicDetails;

    @BindView(id = R.id.ll_bottom1)
    private LinearLayout ll_bottom1;

    @BindView(id = R.id.ll_bottom)
    private LinearLayout ll_bottom;

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

    @BindView(id = R.id.ll_zan1, click = true)
    private LinearLayout ll_zan1;

    @BindView(id = R.id.img_zan1)
    private ImageView img_zan1;

    @BindView(id = R.id.tv_zan1)
    private TextView tv_zan1;

    @BindView(id = R.id.tv_zanNum1)
    private TextView tv_zanNum1;

    @BindView(id = R.id.tv_time)
    private TextView tv_time;

    @BindView(id = R.id.ll_collection1, click = true)
    private LinearLayout ll_collection1;

    @BindView(id = R.id.img_collection1)
    private ImageView img_collection1;

    @BindView(id = R.id.tv_collection1)
    private TextView tv_collection1;

    @BindView(id = R.id.tv_collectionNum1)
    private TextView tv_collectionNum1;

    @BindView(id = R.id.ll_comment1, click = true)
    private LinearLayout ll_comment1;

    @BindView(id = R.id.tv_commentNum1)
    private TextView tv_commentNum1;

    private int id = 0;

    private String title = "";

    private int user_id = 0;

    private int is_concern = 0;

    private int is_like = 0;

    private int is_collect = 0;

    private int isRefresh = 0;

    private UserEvaluationViewAdapter mAdapter = null;

    private ReportBouncedDialog reportBouncedDialog = null;

    private RevertBouncedDialog revertBouncedDialog = null;

    private int type = 1;

    private int positionItem = 0;

    private ShareBouncedDialog shareBouncedDialog = null;

    private String smallImg = "";

    private Thread thread = null;

    private boolean isFull = true;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_dynamicvideodetails);
    }

    @Override
    public void initData() {
        super.initData();
        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        mPresenter = new DynamicDetailsPresenter(this);
        mAdapter = new UserEvaluationViewAdapter(this);
        initBouncedDialog();
        initShareBouncedDialog();
        showLoadingDialog(getString(R.string.dataLoad));
        ((DynamicDetailsContract.Presenter) mPresenter).getDynamicDetails(id);
    }

    private void initBouncedDialog() {
        reportBouncedDialog = new ReportBouncedDialog(this, id);
        revertBouncedDialog = new RevertBouncedDialog(this) {
            @Override
            public void toSuccess() {
                RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsEvent"));
            }
        };
    }

    /**
     * 分享
     */
    private void initShareBouncedDialog() {
        shareBouncedDialog = new ShareBouncedDialog(this, id) {
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
        web.setTitle(title);//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(tv_content.getText().toString());
        new ShareAction(aty).setPlatform(platform)
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        clv_dynamicDetails.setAdapter(mAdapter);
        clv_dynamicDetails.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                if (isRefresh == 1) {
                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);
                }
                aty.finish();
                break;
            case R.id.img_share:
                //分享
                if (shareBouncedDialog == null) {
                    initShareBouncedDialog();
                }
                if (shareBouncedDialog != null & !shareBouncedDialog.isShowing()) {
                    shareBouncedDialog.show();
                }
                break;
            case R.id.ll_author:
                Intent intent = new Intent(aty, DisplayPageActivity.class);
                intent.putExtra("user_id", user_id);
                intent.putExtra("isRefresh", 0);
                showActivity(aty, intent);
                break;
            case R.id.ll_content1:
                isFull = false;
                ViewGroup.LayoutParams params = rl_videoplayer.getLayoutParams();
                params.height = DensityUtils.dip2px(216);
                rl_videoplayer.setLayoutParams(params);
                rl_videoplayer.setPadding(0, DensityUtils.dip2px(43), 0, 0);
                animHeightToView(rl_videoplayer, DensityUtils.getScreenH() - DensityUtils.dip2px(20), DensityUtils.dip2px(216), isFull, 1000);
                ViewGroup.LayoutParams lp = jzVideoPlayerStandard.bottomContainer.getLayoutParams();
                lp.height = DensityUtils.dip2px(45);
                jzVideoPlayerStandard.bottomContainer.setLayoutParams(lp);
                jzVideoPlayerStandard.bottomContainer.setPadding(0, 0, 0, 0);
                ll_title1.setBackgroundResource(R.color.searchTextColors);
                ll_content1.setVisibility(View.GONE);
                ll_bottom1.setVisibility(View.GONE);
                tv_content.setVisibility(View.VISIBLE);
                ll_report1.setVisibility(View.VISIBLE);
                ll_userEvaluation.setVisibility(View.VISIBLE);
                clv_dynamicDetails.setVisibility(View.VISIBLE);
                ll_bottom.setVisibility(View.VISIBLE);
                ll_title.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_fullScreen:
                isFull = true;
                ViewGroup.LayoutParams params1 = rl_videoplayer.getLayoutParams();
                params1.height = DensityUtils.getScreenH() - DensityUtils.dip2px(20);
                rl_videoplayer.setLayoutParams(params1);
                rl_videoplayer.setPadding(0, 0, 0, 0);
                animHeightToView(rl_videoplayer, DensityUtils.dip2px(216), DensityUtils.getScreenH() - DensityUtils.dip2px(20), isFull, 1000);
                ViewGroup.LayoutParams lp1 = jzVideoPlayerStandard.bottomContainer.getLayoutParams();
                lp1.height = ll_bottom1.getHeight() + ll_content1.getHeight() + DensityUtils.dip2px(50);
                jzVideoPlayerStandard.bottomContainer.setLayoutParams(lp1);
                jzVideoPlayerStandard.bottomContainer.setPadding(0, 0, 0, ll_bottom1.getHeight() + ll_content1.getHeight());
                ll_title1.setBackgroundResource(R.color.white30);
                break;
            case R.id.tv_follow:
                String title = getString(R.string.attentionLoad);
                if (is_concern == 1) {
                    title = getString(R.string.cancelCttentionLoad);
                } else {
                    title = getString(R.string.attentionLoad);
                }
                showLoadingDialog(title);
                ((DynamicDetailsContract.Presenter) mPresenter).postAddConcern(user_id, type);
                break;

            case R.id.ll_report:
                if (reportBouncedDialog == null) {
                    reportBouncedDialog = new ReportBouncedDialog(this, id);
                }
                if (reportBouncedDialog != null && !reportBouncedDialog.isShowing()) {
                    reportBouncedDialog.show();
                }
                break;
            case R.id.ll_userEvaluation:
                Intent intent1 = new Intent(aty, DynamicCommentsActivity.class);
                intent1.putExtra("id", id);
                intent1.putExtra("type", type);
                showActivity(aty, intent1);
                break;
            case R.id.ll_zan:
            case R.id.ll_zan1:
                showLoadingDialog(getString(R.string.dataLoad));
                ((DynamicDetailsContract.Presenter) mPresenter).postAddLike(id, type);
                break;
            case R.id.ll_collection:
            case R.id.ll_collection1:
                showLoadingDialog(getString(R.string.dataLoad));
                if (is_collect == 1) {
                    ((DynamicDetailsContract.Presenter) mPresenter).postUnfavorite(id);
                } else {
                    ((DynamicDetailsContract.Presenter) mPresenter).postAddFavorite(id);
                }
                break;
            case R.id.ll_comment:
            case R.id.ll_comment1:
                if (revertBouncedDialog == null) {
                    revertBouncedDialog = new RevertBouncedDialog(this) {
                        @Override
                        public void toSuccess() {
                            RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsEvent"));
                        }
                    };
                }
                if (revertBouncedDialog != null && !revertBouncedDialog.isShowing()) {
                    revertBouncedDialog.show();
                    revertBouncedDialog.setHintText(getString(R.string.writeComment), id, 0, 0, type);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }


    private void animHeightToView(final View v, final int start, final int end, final boolean isToShow,
                                  long duration) {
        ValueAnimator va = ValueAnimator.ofInt(start, end);
        final ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int h = (int) animation.getAnimatedValue();
                layoutParams.height = h;
                v.setLayoutParams(layoutParams);
                v.requestLayout();
            }
        });

        va.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (isToShow) {
                    v.setVisibility(View.VISIBLE);
                    ll_bottom.setVisibility(View.GONE);
                }
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (isToShow) {
                    ll_content1.setVisibility(View.VISIBLE);
                    ll_bottom1.setVisibility(View.VISIBLE);
                    tv_content.setVisibility(View.GONE);
                    ll_report1.setVisibility(View.GONE);
                    ll_userEvaluation.setVisibility(View.GONE);
                    clv_dynamicDetails.setVisibility(View.GONE);
                    ll_title.setVisibility(View.GONE);
                } else {
                    ll_content1.setVisibility(View.GONE);
                    ll_bottom1.setVisibility(View.GONE);
                    tv_content.setVisibility(View.VISIBLE);
                    ll_report1.setVisibility(View.VISIBLE);
                    ll_userEvaluation.setVisibility(View.VISIBLE);
                    clv_dynamicDetails.setVisibility(View.VISIBLE);
                    ll_bottom.setVisibility(View.VISIBLE);
                    ll_title.setVisibility(View.VISIBLE);
                }
            }
        });
        va.setDuration(duration);
        va.start();
    }


    @Override
    public void onPause() {
        super.onPause();
        Jzvd.goOnPlayOnPause();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(aty, CommentDetailsActivity.class);
        intent.putExtra("id", mAdapter.getItem(position).getId());
        intent.putExtra("type", type);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        positionItem = position;
        if (childView.getId() == R.id.ll_giveLike) {
            showLoadingDialog(getString(R.string.dataLoad));
            ((DynamicDetailsContract.Presenter) mPresenter).postAddCommentLike(mAdapter.getItem(positionItem).getId(), type);
        } else if (childView.getId() == R.id.tv_revert) {
            Intent intent = new Intent(aty, CommentDetailsActivity.class);
            intent.putExtra("id", mAdapter.getItem(position).getId());
            intent.putExtra("type", type);
            intent.putExtra("type1", 1);
            startActivityForResult(intent, REQUEST_CODE);
        } else if (childView.getId() == R.id.ll_revertNum) {
            Intent intent = new Intent(aty, CommentDetailsActivity.class);
            intent.putExtra("id", mAdapter.getItem(position).getId());
            intent.putExtra("type", type);
            startActivityForResult(intent, REQUEST_CODE);
        } else if (childView.getId() == R.id.tv_nickName) {
            Intent intent = new Intent(aty, DisplayPageActivity.class);
            intent.putExtra("user_id", mAdapter.getItem(position).getMember_id());
            intent.putExtra("isRefresh", 0);
            showActivity(aty, intent);
        } else if (childView.getId() == R.id.tv_nickName1) {
            Intent intent = new Intent(aty, DisplayPageActivity.class);
            intent.putExtra("user_id", mAdapter.getItem(position).getReplyList().get(0).getMember_id());
            intent.putExtra("isRefresh", 0);
            showActivity(aty, intent);
        } else if (childView.getId() == R.id.tv_nickName2) {
            Intent intent = new Intent(aty, DisplayPageActivity.class);
            intent.putExtra("user_id", mAdapter.getItem(position).getReplyList().get(0).getReply_member_id());
            intent.putExtra("isRefresh", 0);
            showActivity(aty, intent);
        }
    }


    @Override
    public void setPresenter(DynamicDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            DynamicDetailsBean dynamicDetailsBean = (DynamicDetailsBean) JsonUtil.getInstance().json2Obj(success, DynamicDetailsBean.class);
            jzVideoPlayerStandard.setUp(dynamicDetailsBean.getData().getList().get(0), "", JzvdStd.SCREEN_WINDOW_NORMAL);
            GlideImageLoader.glideOrdinaryLoader(this, dynamicDetailsBean.getData().getList().get(0) + "?vframe/jpg/offset/0", jzVideoPlayerStandard.thumbImageView, R.mipmap.placeholderfigure);
            Jzvd.setMediaInterface(new JZPLMediaPlayer());
            Jzvd.SAVE_PROGRESS = false;
            jzVideoPlayerStandard.startButton.performClick();
            //这里只有开始播放时才生效
//            jzVideoPlayerStandard.onPrepared();
////            //跳转制定位置播放
////            JZMediaManager.seekTo(0);
            ViewGroup.LayoutParams lp = jzVideoPlayerStandard.bottomContainer.getLayoutParams();
            lp.height = ll_bottom1.getHeight() + ll_content1.getHeight() + DensityUtils.dip2px(45);
            jzVideoPlayerStandard.bottomContainer.setLayoutParams(lp);
            jzVideoPlayerStandard.bottomContainer.setPadding(0, 0, 0, ll_bottom1.getHeight() + ll_content1.getHeight());
            tv_title.setText(title);
            smallImg = dynamicDetailsBean.getData().getList().get(0) + "?vframe/jpg/offset/0";
            user_id = dynamicDetailsBean.getData().getMember_id();
            tv_time.setText(dynamicDetailsBean.getData().getCreate_time());
            GlideImageLoader.glideLoader(this, dynamicDetailsBean.getData().getFace(), img_head, 0, R.mipmap.avatar);
            tv_nickName.setText(dynamicDetailsBean.getData().getNickname());
            is_concern = dynamicDetailsBean.getData().getIs_concern();
            if (is_concern == 1) {
                tv_follow.setText(getString(R.string.followed));
            } else {
                tv_follow.setText(getString(R.string.follow));
            }
            tv_content.setText(dynamicDetailsBean.getData().getContent());
            tv_content1.setText(dynamicDetailsBean.getData().getContent());
            tv_userEvaluationNum.setText(dynamicDetailsBean.getData().getReview_number() + getString(R.string.evaluation1));
            is_like = dynamicDetailsBean.getData().getIs_like();
            tv_zanNum.setText(dynamicDetailsBean.getData().getLike_number());
            tv_zanNum1.setText(dynamicDetailsBean.getData().getLike_number());
            if (is_like == 1) {
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan1);
                tv_zan.setTextColor(getResources().getColor(R.color.greenColors));
                tv_zanNum.setTextColor(getResources().getColor(R.color.greenColors));
                img_zan1.setImageResource(R.mipmap.dynamicdetails_zan1);
                tv_zan1.setTextColor(getResources().getColor(R.color.greenColors));
                tv_zanNum1.setTextColor(getResources().getColor(R.color.greenColors));
            } else {
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan);
                tv_zan.setTextColor(getResources().getColor(R.color.textColor));
                tv_zanNum.setTextColor(getResources().getColor(R.color.textColor));
                img_zan1.setImageResource(R.mipmap.essay_bottom_like_default);
                tv_zan1.setTextColor(getResources().getColor(R.color.whiteColors));
                tv_zanNum1.setTextColor(getResources().getColor(R.color.whiteColors));
            }
            is_collect = dynamicDetailsBean.getData().getIs_collect();
            tv_collectionNum.setText(dynamicDetailsBean.getData().getCollection_number());
            tv_collectionNum1.setText(dynamicDetailsBean.getData().getCollection_number());
            if (is_collect == 1) {
                img_collection.setImageResource(R.mipmap.dynamicdetails_collection1);
                tv_collection.setTextColor(getResources().getColor(R.color.greenColors));
                tv_collectionNum.setTextColor(getResources().getColor(R.color.greenColors));
                img_collection1.setImageResource(R.mipmap.dynamicdetails_collection1);
                tv_collection1.setTextColor(getResources().getColor(R.color.greenColors));
                tv_collectionNum1.setTextColor(getResources().getColor(R.color.greenColors));
            } else {
                img_collection.setImageResource(R.mipmap.dynamicdetails_collection);
                tv_collection.setTextColor(getResources().getColor(R.color.textColor));
                tv_collectionNum.setTextColor(getResources().getColor(R.color.textColor));
                img_collection1.setImageResource(R.mipmap.essay_bottom_collection_default);
                tv_collection1.setTextColor(getResources().getColor(R.color.whiteColors));
                tv_collectionNum1.setTextColor(getResources().getColor(R.color.whiteColors));
            }
            tv_commentNum.setText(dynamicDetailsBean.getData().getReview_number());
            tv_commentNum1.setText(dynamicDetailsBean.getData().getReview_number());
            if (dynamicDetailsBean.getData().getComment() != null && dynamicDetailsBean.getData().getComment().size() > 0) {
                mAdapter.clear();
                mAdapter.addNewData(dynamicDetailsBean.getData().getComment());
            }
            if (isFull) {
                ViewGroup.LayoutParams params1 = rl_videoplayer.getLayoutParams();
                params1.height = DensityUtils.getScreenH() - DensityUtils.dip2px(20);
                rl_videoplayer.setLayoutParams(params1);
                rl_videoplayer.setPadding(0, 0, 0, 0);
//                Animation animation1 = new ViewSizeChangeAnimation(rl_videoplayer, DensityUtils.getScreenH(), DensityUtils.getScreenW());
//                animation1.setDuration(5000);
//                rl_videoplayer.startAnimation(animation1);
                ll_title1.setBackgroundResource(R.color.white30);
                ll_content1.setVisibility(View.VISIBLE);
                ll_bottom1.setVisibility(View.VISIBLE);
                tv_content.setVisibility(View.GONE);
                ll_report1.setVisibility(View.GONE);
                ll_userEvaluation.setVisibility(View.GONE);
                clv_dynamicDetails.setVisibility(View.GONE);
                ll_bottom.setVisibility(View.GONE);
                ll_title.setVisibility(View.GONE);
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            if (is_concern == 1) {
                is_concern = 0;
                tv_follow.setText(getString(R.string.follow));
                ViewInject.toast(getString(R.string.focusSuccess));
            } else {
                is_concern = 1;
                tv_follow.setText(getString(R.string.followed));
                ViewInject.toast(getString(R.string.attentionSuccess));
            }
            isRefresh = 1;
            dismissLoadingDialog();
        } else if (flag == 2) {
            dismissLoadingDialog();
            img_collection.setImageResource(R.mipmap.dynamicdetails_collection);
            tv_collection.setTextColor(getResources().getColor(R.color.textColor));
            tv_collectionNum.setTextColor(getResources().getColor(R.color.textColor));
            is_collect = 0;
            tv_collectionNum.setText(StringUtils.toInt(tv_collectionNum.getText().toString(), 0) - 1 + "");

            img_collection1.setImageResource(R.mipmap.dynamicdetails_collection);
            tv_collection1.setTextColor(getResources().getColor(R.color.textColor));
            tv_collectionNum1.setTextColor(getResources().getColor(R.color.textColor));
            tv_collectionNum1.setText(StringUtils.toInt(tv_collectionNum.getText().toString(), 0) - 1 + "");
            ViewInject.toast(getString(R.string.uncollectible));
            isRefresh = 1;
        } else if (flag == 3) {
            dismissLoadingDialog();
            is_collect = 1;
            img_collection.setImageResource(R.mipmap.dynamicdetails_collection1);
            tv_collection.setTextColor(getResources().getColor(R.color.greenColors));
            tv_collectionNum.setText(StringUtils.toInt(tv_collectionNum.getText().toString(), 0) + 1 + "");
            tv_collectionNum.setTextColor(getResources().getColor(R.color.greenColors));
            img_collection1.setImageResource(R.mipmap.dynamicdetails_collection1);
            tv_collection1.setTextColor(getResources().getColor(R.color.greenColors));
            tv_collectionNum1.setText(StringUtils.toInt(tv_collectionNum.getText().toString(), 0) + 1 + "");
            tv_collectionNum1.setTextColor(getResources().getColor(R.color.greenColors));
            ViewInject.toast(getString(R.string.collectionSuccess));
            isRefresh = 1;
        } else if (flag == 4) {
            if (is_like == 1) {
                is_like = 0;
                tv_zanNum.setText(StringUtils.toInt(tv_zanNum.getText().toString(), 0) - 1 + "");
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan);
                tv_zan.setTextColor(getResources().getColor(R.color.textColor));
                tv_zanNum.setTextColor(getResources().getColor(R.color.textColor));
                tv_zanNum1.setText(StringUtils.toInt(tv_zanNum.getText().toString(), 0) - 1 + "");
                img_zan1.setImageResource(R.mipmap.dynamicdetails_zan);
                tv_zan1.setTextColor(getResources().getColor(R.color.textColor));
                tv_zanNum1.setTextColor(getResources().getColor(R.color.textColor));
                ViewInject.toast(getString(R.string.cancelZanSuccess));
            } else {
                is_like = 1;
                tv_zanNum.setText(StringUtils.toInt(tv_zanNum.getText().toString(), 0) + 1 + "");
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan1);
                tv_zan.setTextColor(getResources().getColor(R.color.greenColors));
                tv_zanNum.setTextColor(getResources().getColor(R.color.greenColors));
                tv_zanNum1.setText(StringUtils.toInt(tv_zanNum.getText().toString(), 0) + 1 + "");
                img_zan1.setImageResource(R.mipmap.dynamicdetails_zan1);
                tv_zan1.setTextColor(getResources().getColor(R.color.greenColors));
                tv_zanNum1.setTextColor(getResources().getColor(R.color.greenColors));
                ViewInject.toast(getString(R.string.zanSuccess));
            }
            dismissLoadingDialog();
        } else if (flag == 5) {
            if (mAdapter.getItem(positionItem).getIs_comment_like() == 1) {
                mAdapter.getItem(positionItem).setComment_like_number(StringUtils.toInt(mAdapter.getItem(positionItem).getComment_like_number(), 0) - 1 + "");
                mAdapter.getItem(positionItem).setIs_comment_like(0);
                mAdapter.notifyDataSetChanged();
                ViewInject.toast(getString(R.string.cancelZanSuccess));
            } else {
                mAdapter.getItem(positionItem).setComment_like_number(StringUtils.toInt(mAdapter.getItem(positionItem).getComment_like_number(), 0) + 1 + "");
                mAdapter.getItem(positionItem).setIs_comment_like(1);
                mAdapter.notifyDataSetChanged();
                ViewInject.toast(getString(R.string.zanSuccess));
            }
            dismissLoadingDialog();
        }
    }


    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusDynamicDetailsEvent") && mPresenter != null) {
            ((DynamicDetailsContract.Presenter) mPresenter).getDynamicDetails(id);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            ((DynamicDetailsContract.Presenter) mPresenter).getDynamicDetails(id);
        } else {
            UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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
        Jzvd.releaseAllVideos();
        UMShareAPI.get(this).release();
        if (thread != null) {
            thread.interrupted();
        }
        thread = null;
        if (shareBouncedDialog != null) {
            shareBouncedDialog.cancel();
        }
        shareBouncedDialog = null;
        if (reportBouncedDialog != null) {
            reportBouncedDialog.cancel();
        }
        reportBouncedDialog = null;

        if (revertBouncedDialog != null) {
            revertBouncedDialog.cancel();
        }
        revertBouncedDialog = null;
        mAdapter.clear();
        mAdapter = null;
    }

}
