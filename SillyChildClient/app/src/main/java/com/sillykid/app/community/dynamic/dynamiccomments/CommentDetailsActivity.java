package com.sillykid.app.community.dynamic.dynamiccomments;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.sillykid.app.adapter.community.dynamic.dynamiccomments.CommentDetailsViewAdapter;
import com.sillykid.app.community.dynamic.dialog.RevertBouncedDialog;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.community.dynamic.dynamiccomments.CommentDetailsBean;
import com.sillykid.app.entity.community.dynamic.dynamiccomments.CommentDetailsBean.DataBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

/**
 * 单条动态评论详情
 */
public class CommentDetailsActivity extends BaseActivity implements CommentDetailsContract.View {

    @BindView(id = R.id.img_avatar)
    private ImageView img_avatar;

    @BindView(id = R.id.tv_nickName)
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
                ((CommentDetailsContract.Presenter) mPresenter).getCommentDetails(id);
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
    }

    private void initView() {
        if (type == 1) {
            if (revertBouncedDialog == null) {
                revertBouncedDialog = new RevertBouncedDialog(this) {
                    @Override
                    public void toSuccess() {
                        /**
                         * 发送消息
                         */
                        RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsEvent"));
                        ((CommentDetailsContract.Presenter) mPresenter).getCommentDetails(id);
                    }
                };
            }
            if (revertBouncedDialog != null && !revertBouncedDialog.isShowing()) {
                revertBouncedDialog.show();
                revertBouncedDialog.setHintText(getString(R.string.revert) + comment.getNickname(), comment.getPost_id(), comment.getId(), comment.getMember_id(), 1);
            }
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_revert:
                if (revertBouncedDialog == null) {
                    revertBouncedDialog = new RevertBouncedDialog(this) {
                        @Override
                        public void toSuccess() {
                            /**
                             * 发送消息
                             */
                            RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsEvent"));
                            ((CommentDetailsContract.Presenter) mPresenter).getCommentDetails(id);
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
            if (comment.getReplyList() == null || comment.getReplyList().size() <= 0) {
                ll_revert.setVisibility(View.GONE);
            } else {
                ll_revert.setVisibility(View.VISIBLE);
                mAdapter.clear();
                mAdapter.addMoreData(comment.getReplyList());
            }
            initView();
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
