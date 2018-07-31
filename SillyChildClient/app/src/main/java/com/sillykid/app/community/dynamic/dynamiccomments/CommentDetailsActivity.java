package com.sillykid.app.community.dynamic.dynamiccomments;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.StringUtils;
import com.sillykid.app.adapter.community.dynamic.dynamiccomments.CommentDetailsViewAdapter;
import com.sillykid.app.community.DisplayPageActivity;
import com.sillykid.app.community.dynamic.dialog.RevertBouncedDialog;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.community.dynamic.dynamiccomments.CommentDetailsBean;
import com.sillykid.app.entity.community.dynamic.dynamiccomments.CommentDetailsBean.DataBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;

/**
 * 单条动态评论详情
 */
public class CommentDetailsActivity extends BaseActivity implements CommentDetailsContract.View, BGAOnItemChildClickListener {

    @BindView(id = R.id.img_avatar)
    private ImageView img_avatar;

    @BindView(id = R.id.tv_nickName, click = true)
    private TextView tv_nickName;

    @BindView(id = R.id.ll_zan, click = true)
    private LinearLayout ll_zan;

    @BindView(id = R.id.img_zan)
    private ImageView img_zan;

    @BindView(id = R.id.tv_giveLike)
    private TextView tv_giveLike;

    @BindView(id = R.id.tv_content)
    private TextView tv_content;

    @BindView(id = R.id.tv_time)
    private TextView tv_time;

    @BindView(id = R.id.tv_revert, click = true)
    private TextView tv_revert;

    @BindView(id = R.id.ll_revert)
    private LinearLayout ll_revert;

    @BindView(id = R.id.clv_revert)
    private ChildListView clv_revert;

    private CommentDetailsViewAdapter mAdapter;
    private RevertBouncedDialog revertBouncedDialog = null;
    private int type = 0;
    private int id = 0;
    private DataBean comment;

    private int is_like = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_dynamiccommentdetails);
    }


    @Override
    public void initData() {
        super.initData();
        type = getIntent().getIntExtra("type", 0);
        id = getIntent().getIntExtra("id", 0);
        mPresenter = new CommentDetailsPresenter(this);
        mAdapter = new CommentDetailsViewAdapter(this);
        revertBouncedDialog = new RevertBouncedDialog(this) {
            @Override
            public void toSuccess() {
                /**
                 * 发送消息
                 */
                RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsEvent"));
            }
        };
        showLoadingDialog(getString(R.string.dataLoad));
        ((CommentDetailsContract.Presenter) mPresenter).getCommentDetails(id);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.commentDetails), true, R.id.titlebar);
        clv_revert.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(this);
    }

    private void initView() {
        if (getIntent().getIntExtra("type1", 0) == 1) {
            if (revertBouncedDialog == null) {
                revertBouncedDialog = new RevertBouncedDialog(this) {
                    @Override
                    public void toSuccess() {
                        /**
                         * 发送消息
                         */
                        RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsEvent"));
                    }
                };
            }
            if (revertBouncedDialog != null && !revertBouncedDialog.isShowing()) {
                revertBouncedDialog.show();
                revertBouncedDialog.setHintText(getString(R.string.revert) + comment.getNickname(), comment.getPost_id(), comment.getId(), comment.getMember_id(), type);
            }
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_nickName:
                Intent intent = new Intent(aty, DisplayPageActivity.class);
                intent.putExtra("user_id", comment.getReply_member_id());
                intent.putExtra("isRefresh", 0);
                showActivity(aty, intent);
                break;
            case R.id.ll_zan:
                showLoadingDialog(getString(R.string.dataLoad));
                ((CommentDetailsContract.Presenter) mPresenter).postAddLike(id, type);
                break;
            case R.id.tv_revert:
                if (revertBouncedDialog == null) {
                    revertBouncedDialog = new RevertBouncedDialog(this) {
                        @Override
                        public void toSuccess() {
                            /**
                             * 发送消息
                             */
                            RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsEvent"));
                        }
                    };
                }
                if (revertBouncedDialog != null && !revertBouncedDialog.isShowing()) {
                    revertBouncedDialog.show();
                    revertBouncedDialog.setHintText(getString(R.string.revert) + comment.getNickname(), comment.getPost_id(), comment.getId(), comment.getMember_id(), 1);
                }
                break;
        }
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.tv_nickName1) {
            Intent intent = new Intent(aty, DisplayPageActivity.class);
            intent.putExtra("user_id", mAdapter.getItem(position).getMember_id());
            intent.putExtra("isRefresh", 0);
            showActivity(aty, intent);
        } else if (childView.getId() == R.id.tv_nickName2) {
            Intent intent = new Intent(aty, DisplayPageActivity.class);
            intent.putExtra("user_id", mAdapter.getItem(position).getReply_member_id());
            intent.putExtra("isRefresh", 0);
            showActivity(aty, intent);
        }
    }


    @Override
    public void setPresenter(CommentDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            CommentDetailsBean commentDetailsBean = (CommentDetailsBean) JsonUtil.json2Obj(success, CommentDetailsBean.class);
            comment = commentDetailsBean.getData();
            GlideImageLoader.glideLoader(this, comment.getFace(), img_avatar, 0, R.mipmap.avatar);
            tv_nickName.setText(comment.getNickname());
            tv_content.setText(comment.getBody());
            tv_time.setText(comment.getCreate_time());
            is_like = comment.getIs_comment_like();
            if (is_like == 1) {
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan1);
                tv_giveLike.setText(comment.getComment_like_number());
                tv_giveLike.setTextColor(getResources().getColor(R.color.greenColors));
            } else {
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan);
                tv_giveLike.setText(getString(R.string.giveLike));
                tv_giveLike.setTextColor(getResources().getColor(R.color.tabColors));
            }
            if (comment.getReplyList() == null || comment.getReplyList().size() <= 0) {
                ll_revert.setVisibility(View.GONE);
            } else {
                ll_revert.setVisibility(View.VISIBLE);
                mAdapter.clear();
                mAdapter.addMoreData(comment.getReplyList());
            }
            initView();
        } else if (flag == 1) {
            if (is_like == 1) {
                is_like = 0;
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan);
                tv_giveLike.setText(getString(R.string.giveLike));
                tv_giveLike.setTextColor(getResources().getColor(R.color.tabColors));
                ViewInject.toast(getString(R.string.cancelZanSuccess));
            } else {
                is_like = 1;
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan1);
                tv_giveLike.setText(StringUtils.toInt(comment.getComment_like_number(), 0) + 1 + "");
                tv_giveLike.setTextColor(getResources().getColor(R.color.greenColors));
                ViewInject.toast(getString(R.string.zanSuccess));
            }
        }
        dismissLoadingDialog();
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


    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusDynamicDetailsEvent") && mPresenter != null) {
            ((CommentDetailsContract.Presenter) mPresenter).getCommentDetails(id);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (revertBouncedDialog != null) {
            revertBouncedDialog.cancel();
        }
        revertBouncedDialog = null;
        mAdapter.clear();
        mAdapter = null;
    }


}
