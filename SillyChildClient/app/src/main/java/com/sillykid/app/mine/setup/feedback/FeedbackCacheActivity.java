package com.sillykid.app.mine.setup.feedback;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.UserInfoBean;
import com.sillykid.app.loginregister.LoginActivity;

/**
 * Created by Administrator on 2017/11/8.
 */

public class FeedbackCacheActivity extends BaseActivity implements FeedbackContract.View {

    @BindView(id = R.id.ll_zan, click = true)
    private LinearLayout ll_zan;
    @BindView(id = R.id.ll_qiuzhu, click = true)
    private LinearLayout ll_qiuzhu;
    @BindView(id = R.id.ll_guzhang, click = true)
    private LinearLayout ll_guzhang;
    @BindView(id = R.id.ll_jianyi, click = true)
    private LinearLayout ll_jianyi;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_feedbackcache);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.feedback), true, R.id.titlebar);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new FeedbackPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_zan:
                ViewInject.toast("尚未开发，敬请期待！");
                break;
            case R.id.ll_qiuzhu:
                showLoadingDialog(getString(R.string.dataLoad));
             //   ((FeedbackPresenter) mPresenter).getInfo();
                break;
            case R.id.ll_guzhang:
                showActivity(this, FeedbackActivity.class);
                break;
            case R.id.ll_jianyi:
                showActivity(this, FeedbackActivity.class);
                break;
        }
    }

    private void toChart(String hxusername, String nickname, String defaultnickname, String avatar) {
        if (TextUtils.isEmpty(hxusername)) {
            ViewInject.toast(getString(R.string.infoMissing));
            return;
        }
//        Intent jumpintent = new Intent(aty, ChatMessageActivity.class);
//        jumpintent.putExtra("userId", hxusername);
//        if (TextUtils.isEmpty(nickname)) {
//            if (TextUtils.isEmpty(defaultnickname)){
//                ViewInject.toast(getString(R.string.infoMissing));
//                return;
//            }else{
//                jumpintent.putExtra("nickname", defaultnickname);
//            }
//        }else{
//            jumpintent.putExtra("nickname", nickname);
//        }
//        if (!TextUtils.isEmpty(avatar)) {
//            jumpintent.putExtra("avatar", avatar);
//        }
//        showActivity(aty, jumpintent);
    }

    @Override
    public void setPresenter(FeedbackContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        UserInfoBean userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
        if (userInfoBean != null && userInfoBean.getData() != null) {
            // toChart(userInfoBean.getData().getHx_user_name(),userInfoBean.getData().getNickname(),userInfoBean.getData().getMobile(),userInfoBean.getData().getHead_pic());
        } else {
            ViewInject.toast(getString(R.string.infoMissing));
        }

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

}
