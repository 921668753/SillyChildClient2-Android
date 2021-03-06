package com.sillykid.app.homepage.hotvideo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.community.dynamic.UserEvaluationViewAdapter;
import com.sillykid.app.community.DisplayPageActivity;
import com.sillykid.app.community.dynamic.dialog.ReportBouncedDialog;
import com.sillykid.app.community.dynamic.dialog.RevertBouncedDialog;
import com.sillykid.app.community.dynamic.dynamiccomments.CommentDetailsActivity;
import com.sillykid.app.community.dynamic.dynamiccomments.DynamicCommentsActivity;
import com.sillykid.app.constant.URLConstants;
import com.sillykid.app.entity.homepage.hotvideo.VideoDetailsBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.sharingceremony.dialog.ShareBouncedDialog;
import com.sillykid.app.utils.GlideImageLoader;
import com.common.cklibrary.utils.custommediaplayer.JZPLMediaPlayer;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.titlebar.BGATitleBar;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

/**
 * 视频详情
 */
public class VideoDetailsActivity extends BaseActivity implements VideoDetailsContract.View, AdapterView.OnItemClickListener, BGAOnItemChildClickListener {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.videoplayer)
    private JzvdStd jzVideoPlayerStandard;

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

    @BindView(id = R.id.tv_time)
    private TextView tv_time;

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

    private String title = "";

    private int is_concern = 0;

    private int is_like = 0;

    private int is_collect = 0;

    private int isRefresh = 0;

    private UserEvaluationViewAdapter mAdapter = null;

    private ReportBouncedDialog reportBouncedDialog = null;

    private RevertBouncedDialog revertBouncedDialog = null;

    private ShareBouncedDialog shareBouncedDialog = null;

    private String smallImg = "";
    private int id = 0;
    private int user_id = 0;
    private int type = 2;
    private int positionItem = 0;
    private boolean isOpenPlayer = true;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_videodetails);
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void initData() {
        super.initData();
        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        mAdapter = new UserEvaluationViewAdapter(this);
        mPresenter = new VideoDetailsPresenter(this);
        initBouncedDialog();
        initShareBouncedDialog();
        showLoadingDialog(getString(R.string.dataLoad));
        ((VideoDetailsContract.Presenter) mPresenter).getVideoDetails(id);
    }

    private void initBouncedDialog() {
        reportBouncedDialog = new ReportBouncedDialog(this, id);
        revertBouncedDialog = new RevertBouncedDialog(this) {
            @Override
            public void toSuccess() {
                RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsEvent"));
//                tv_userEvaluationNum.setText(StringUtils.toInt(tv_commentNum.getText().toString(), 0) + 1 + getString(R.string.evaluation1));
//                tv_commentNum.setText(StringUtils.toInt(tv_commentNum.getText().toString(), 0) + 1 + "");
            }
        };
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
        web.setTitle(title);//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(tv_content.getText().toString());
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

        titlebar.setTitleText(title);
        titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.product_details_share));
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                if (isRefresh == 1) {
                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);
                }
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
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_author:
//                Intent intent = new Intent(aty, DisplayPageActivity.class);
//                intent.putExtra("user_id", user_id);
//                intent.putExtra("isRefresh", 0);
//                showActivity(aty, intent);
                break;
            case R.id.tv_follow:
                String title = getString(R.string.attentionLoad);
                if (is_concern == 1) {
                    title = getString(R.string.cancelCttentionLoad);
                } else {
                    title = getString(R.string.attentionLoad);
                }
                showLoadingDialog(title);
                ((VideoDetailsContract.Presenter) mPresenter).postAddConcern(user_id, type);
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
                showLoadingDialog(getString(R.string.dataLoad));
                ((VideoDetailsContract.Presenter) mPresenter).postAddLike(id, type);
                break;
            case R.id.ll_collection:
                showLoadingDialog(getString(R.string.dataLoad));
                if (is_collect == 1) {
                    ((VideoDetailsContract.Presenter) mPresenter).postUnfavorite(id);
                } else {
                    ((VideoDetailsContract.Presenter) mPresenter).postAddFavorite(id);
                }
                break;
            case R.id.ll_comment:
                if (revertBouncedDialog == null) {
                    revertBouncedDialog = new RevertBouncedDialog(this) {
                        @Override
                        public void toSuccess() {
                            RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsEvent"));
//                            tv_userEvaluationNum.setText(StringUtils.toInt(tv_commentNum.getText().toString(), 0) + 1 + getString(R.string.evaluation1));
//                            tv_commentNum.setText(StringUtils.toInt(tv_commentNum.getText().toString(), 0) + 1 + "");
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
            ((VideoDetailsContract.Presenter) mPresenter).postAddCommentLike(mAdapter.getItem(positionItem).getId(), type);
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
    public void setPresenter(VideoDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            VideoDetailsBean dynamicDetailsBean = (VideoDetailsBean) JsonUtil.getInstance().json2Obj(success, VideoDetailsBean.class);
            is_like = dynamicDetailsBean.getData().getIs_like();
            is_collect = dynamicDetailsBean.getData().getIs_collect();
            user_id = dynamicDetailsBean.getData().getMember_id();
            smallImg = dynamicDetailsBean.getData().getVideo_image();
            jzVideoPlayerStandard.setUp(dynamicDetailsBean.getData().getVideo_url(), "", JzvdStd.SCREEN_WINDOW_NORMAL);
            GlideImageLoader.glideOrdinaryLoader(this, smallImg, jzVideoPlayerStandard.thumbImageView, R.mipmap.placeholderfigure);
            Jzvd.setMediaInterface(new JZPLMediaPlayer());
            Jzvd.SAVE_PROGRESS = false;
            GlideImageLoader.glideLoader(this, dynamicDetailsBean.getData().getFace(), img_head, 0, R.mipmap.avatar);
            tv_nickName.setText(dynamicDetailsBean.getData().getNickname());
            tv_content.setText(dynamicDetailsBean.getData().getVideo_description());
            tv_userEvaluationNum.setText(dynamicDetailsBean.getData().getReview_number() + getString(R.string.evaluation1));
            tv_zanNum.setText(dynamicDetailsBean.getData().getLike_number());
            tv_time.setText(dynamicDetailsBean.getData().getCreate_time());
            if (is_like == 1) {
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan1);
                tv_zan.setTextColor(getResources().getColor(R.color.greenColors));
                tv_zanNum.setTextColor(getResources().getColor(R.color.greenColors));
            } else {
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan);
                tv_zan.setTextColor(getResources().getColor(R.color.textColor));
                tv_zanNum.setTextColor(getResources().getColor(R.color.textColor));
            }
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
                tv_follow.setBackgroundResource(R.drawable.shape_followdd);
                tv_follow.setTextColor(getResources().getColor(R.color.greenColors));
                ViewInject.toast(getString(R.string.focusSuccess));
            } else {
                is_concern = 1;
                tv_follow.setText(getString(R.string.followed));
                tv_follow.setBackgroundResource(R.drawable.shape_followed1);
                tv_follow.setTextColor(getResources().getColor(R.color.whiteColors));
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
            ViewInject.toast(getString(R.string.uncollectible));
            isRefresh = 1;
        } else if (flag == 3) {
            dismissLoadingDialog();
            is_collect = 1;
            img_collection.setImageResource(R.mipmap.dynamicdetails_collection1);
            tv_collection.setTextColor(getResources().getColor(R.color.greenColors));
            tv_collectionNum.setText(StringUtils.toInt(tv_collectionNum.getText().toString(), 0) + 1 + "");
            tv_collectionNum.setTextColor(getResources().getColor(R.color.greenColors));
            ViewInject.toast(getString(R.string.collectionSuccess));
            isRefresh = 1;
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
            isOpenPlayer = false;
            ((VideoDetailsContract.Presenter) mPresenter).getVideoDetails(id);
        } else if (((String) msgEvent.getData()).equals("RxBusDynamicDetailsEvent") && mPresenter != null) {
            ((VideoDetailsContract.Presenter) mPresenter).getVideoDetails(id);
        } else if (((String) msgEvent.getData()).equals("RxBusDynamicDetailsZanEvent") && mPresenter != null) {
            is_like = 1;
            tv_zanNum.setText(StringUtils.toInt(tv_zanNum.getText().toString(), 0) + 1 + "");
            img_zan.setImageResource(R.mipmap.dynamicdetails_zan1);
            tv_zan.setTextColor(getResources().getColor(R.color.greenColors));
            tv_zanNum.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (((String) msgEvent.getData()).equals("RxBusDynamicDetailsCancelZanEvent") && mPresenter != null) {
            is_like = 0;
            tv_zanNum.setText(StringUtils.toInt(tv_zanNum.getText().toString(), 0) - 1 + "");
            img_zan.setImageResource(R.mipmap.dynamicdetails_zan);
            tv_zan.setTextColor(getResources().getColor(R.color.textColor));
            tv_zanNum.setTextColor(getResources().getColor(R.color.textColor));
        } else if (((String) msgEvent.getData()).equals("RxBusDynamicDetailsCollectionEvent") && mPresenter != null) {
            is_collect = 1;
            img_collection.setImageResource(R.mipmap.dynamicdetails_collection1);
            tv_collection.setTextColor(getResources().getColor(R.color.greenColors));
            tv_collectionNum.setText(StringUtils.toInt(tv_collectionNum.getText().toString(), 0) + 1 + "");
            tv_collectionNum.setTextColor(getResources().getColor(R.color.greenColors));
            isRefresh = 1;
        } else if (((String) msgEvent.getData()).equals("RxBusDynamicDetailsUnCollectionEvent") && mPresenter != null) {
            img_collection.setImageResource(R.mipmap.dynamicdetails_collection);
            tv_collection.setTextColor(getResources().getColor(R.color.textColor));
            tv_collectionNum.setTextColor(getResources().getColor(R.color.textColor));
            is_collect = 0;
            isRefresh = 1;
            tv_collectionNum.setText(StringUtils.toInt(tv_collectionNum.getText().toString(), 0) - 1 + "");
        } else if (((String) msgEvent.getData()).equals("RxBusDynamicDetailsFocusEvent") && mPresenter != null) {
            is_concern = 1;
            tv_follow.setText(getString(R.string.followed));
            tv_follow.setBackgroundResource(R.drawable.shape_followed1);
            tv_follow.setTextColor(getResources().getColor(R.color.whiteColors));
            isRefresh = 1;
        } else if (((String) msgEvent.getData()).equals("RxBusDynamicDetailsUnFocusEvent") && mPresenter != null) {
            is_concern = 0;
            tv_follow.setText(getString(R.string.follow));
            tv_follow.setBackgroundResource(R.drawable.shape_followdd);
            tv_follow.setTextColor(getResources().getColor(R.color.greenColors));
            isRefresh = 1;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            ((VideoDetailsContract.Presenter) mPresenter).getVideoDetails(id);
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
        if (reportBouncedDialog != null) {
            reportBouncedDialog.cancel();
        }
        reportBouncedDialog = null;

        if (revertBouncedDialog != null) {
            revertBouncedDialog.cancel();
        }
        revertBouncedDialog = null;
        if (shareBouncedDialog != null) {
            shareBouncedDialog.cancel();
        }
        shareBouncedDialog = null;
        mAdapter.clear();
        mAdapter = null;
    }
}
