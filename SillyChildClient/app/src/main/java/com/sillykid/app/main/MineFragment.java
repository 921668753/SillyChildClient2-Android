package com.sillykid.app.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.ObservableScrollView;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.UserInfoBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.homepage.message.interactivemessage.imuitl.UserUtil;
import com.sillykid.app.mine.deliveryaddress.DeliveryAddressActivity;
import com.sillykid.app.mine.mycollection.MyCollectionActivity;
import com.sillykid.app.mine.myfans.MyFansActivity;
import com.sillykid.app.mine.myfocus.MyFocusActivity;
import com.sillykid.app.mine.myorder.MyOrderActivity;
import com.sillykid.app.mine.myrelease.MyReleaseActivity;
import com.sillykid.app.mine.myshoppingcart.MyShoppingCartActivity;
import com.sillykid.app.mine.mywallet.MyWalletActivity;
import com.sillykid.app.mine.personaldata.PersonalDataActivity;
import com.sillykid.app.mine.setup.SetUpActivity;
import com.sillykid.app.mine.sharingceremony.SharingCeremonyActivity;
import com.sillykid.app.mine.vipemergencycall.VipEmergencyCallActivity;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;
import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

/**
 * 个人中心
 * Created by Admin on 2017/8/10.
 */
public class MineFragment extends BaseFragment implements MineContract.View, ObservableScrollView.ScrollViewListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.sv_mine)
    private ObservableScrollView sv_mine;

    @BindView(id = R.id.rl_title)
    private RelativeLayout rl_title;

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.ll_notLogin, click = true)
    private LinearLayout ll_notLogin;

    @BindView(id = R.id.tv_editData1, click = true)
    private TextView tv_editData1;

    @BindView(id = R.id.tv_divider)
    private TextView tv_divider;

    @BindView(id = R.id.tv_editData, click = true)
    private TextView tv_editData;

    @BindView(id = R.id.tv_minetouxiang)
    private TextView tv_minetouxiang;

    @BindView(id = R.id.iv_minetouxiang, click = true)
    private ImageView iv_minetouxiang;

    @BindView(id = R.id.tv_nickname, click = true)
    private TextView tv_nickname;

    @BindView(id = R.id.tv_serialNumber)
    private TextView tv_serialNumber;

    @BindView(id = R.id.tv_synopsis)
    private TextView tv_synopsis;

    @BindView(id = R.id.ll_follow, click = true)
    private LinearLayout ll_follow;

    @BindView(id = R.id.tv_follow)
    private TextView tv_follow;

    @BindView(id = R.id.ll_fans, click = true)
    private LinearLayout ll_fans;

    @BindView(id = R.id.tv_fans)
    private TextView tv_fans;

    @BindView(id = R.id.tv_beCollected)
    private TextView tv_beCollected;

    @BindView(id = R.id.tv_giveLike)
    private TextView tv_giveLike;

    @BindView(id = R.id.ll_mineshopping, click = true)
    private LinearLayout ll_mineshopping;

    @BindView(id = R.id.ll_mineorder, click = true)
    private LinearLayout ll_mineorder;

    @BindView(id = R.id.ll_minewallet, click = true)
    private LinearLayout ll_minewallet;

    @BindView(id = R.id.ll_minecollection, click = true)
    private LinearLayout ll_minecollection;

    @BindView(id = R.id.ll_mineshare, click = true)
    private LinearLayout ll_mineshare;

    @BindView(id = R.id.ll_mineaddress, click = true)
    private LinearLayout ll_mineaddress;

    @BindView(id = R.id.ll_minesetup, click = true)
    private LinearLayout ll_minesetup;

    @BindView(id = R.id.ll_vipEmergencyCall, click = true)
    private LinearLayout ll_vipEmergencyCall;

    @BindView(id = R.id.ll_myRelease, click = true)
    private LinearLayout ll_myRelease;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mine, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new MinePresenter(this);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        sv_mine.setScrollViewListener(this);
        mRefreshLayout.beginRefreshing();
    }


    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_editData:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
            case R.id.tv_editData1:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
            case R.id.iv_minetouxiang:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
            case R.id.ll_notLogin:
                aty.showActivity(aty, LoginActivity.class);
                break;
            case R.id.ll_mineshopping:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 2);
                break;
            case R.id.ll_minewallet:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 3);
                break;
            case R.id.ll_mineorder:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 4);
                break;
            case R.id.ll_minecollection:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 5);
                break;
            case R.id.ll_mineshare:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 6);
                break;
            case R.id.ll_mineaddress:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 7);
                break;
            case R.id.ll_follow:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 8);
                break;
            case R.id.ll_fans:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 9);
                break;
            case R.id.ll_myRelease:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 10);
                break;
            case R.id.ll_minesetup:
                aty.showActivity(aty, SetUpActivity.class);
                break;

            case R.id.ll_vipEmergencyCall:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 11);
                break;


        }
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        mRefreshLayout.setPullDownRefreshEnable(true);
        if (flag == 0) {
            Log.e("用户信息", "结果：" + success);
            UserInfoBean userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
            if (userInfoBean != null && userInfoBean.getData() != null) {
                ll_notLogin.setVisibility(View.GONE);
                tv_editData.setVisibility(View.VISIBLE);
                tv_editData1.setVisibility(View.VISIBLE);
                tv_minetouxiang.setVisibility(View.VISIBLE);
                iv_minetouxiang.setVisibility(View.VISIBLE);
                tv_nickname.setVisibility(View.VISIBLE);
                tv_serialNumber.setVisibility(View.VISIBLE);
                saveUserInfo(userInfoBean);
                tv_nickname.setText(userInfoBean.getData().getNick_name());
                if (StringUtils.isEmpty(userInfoBean.getData().getFace())) {
                    iv_minetouxiang.setImageResource(R.mipmap.avatar);
                } else {
                    GlideImageLoader.glideLoader(aty, userInfoBean.getData().getFace(), iv_minetouxiang, 0, R.mipmap.avatar);
                }
                tv_serialNumber.setText(userInfoBean.getData().getShz());
                if (StringUtils.isEmpty(userInfoBean.getData().getSignature())) {
                    tv_synopsis.setVisibility(View.GONE);
                } else {
                    tv_synopsis.setVisibility(View.VISIBLE);
                    tv_synopsis.setText(userInfoBean.getData().getSignature());
                }
                tv_follow.setText(userInfoBean.getData().getConcern_number());
                tv_fans.setText(userInfoBean.getData().getFans_number());
                tv_beCollected.setText(userInfoBean.getData().getCollected_number());
                tv_giveLike.setText(userInfoBean.getData().getLike_number());
            }
        } else if (flag == 1) {
            Intent personalDataIntent = new Intent(aty, PersonalDataActivity.class);
            // 获取内容
            // 设置结果 结果码，一个数据
            startActivityForResult(personalDataIntent, REQUEST_CODE);
        } else if (flag == 2) {
            aty.showActivity(aty, MyShoppingCartActivity.class);
        } else if (flag == 3) {
            aty.showActivity(aty, MyWalletActivity.class);
        } else if (flag == 4) {
            aty.showActivity(aty, MyOrderActivity.class);
        } else if (flag == 5) {
            aty.showActivity(aty, MyCollectionActivity.class);
        } else if (flag == 6) {
            aty.showActivity(aty, SharingCeremonyActivity.class);
        } else if (flag == 7) {
            aty.showActivity(aty, DeliveryAddressActivity.class);
        } else if (flag == 8) {
            aty.showActivity(aty, MyFocusActivity.class);
        } else if (flag == 9) {
            aty.showActivity(aty, MyFansActivity.class);
        } else if (flag == 10) {
            aty.showActivity(aty, MyReleaseActivity.class);
        } else if (flag == 11) {
            aty.showActivity(aty, VipEmergencyCallActivity.class);
        }
        dismissLoadingDialog();
    }

    /**
     * 用户信息本地化
     */
    private void saveUserInfo(UserInfoBean userInfoBean) {
        PreferenceHelper.write(aty, StringConstants.FILENAME, "username", userInfoBean.getData().getUsername());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "nick_name", userInfoBean.getData().getNick_name());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "birthday", userInfoBean.getData().getBirthday());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "shz", userInfoBean.getData().getShz());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "face", userInfoBean.getData().getFace());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "sex", userInfoBean.getData().getSex());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "province", userInfoBean.getData().getProvince());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "province_id", userInfoBean.getData().getProvince_id());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "city", userInfoBean.getData().getCity());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "city_id", userInfoBean.getData().getCity_id());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "region", userInfoBean.getData().getRegion());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "region_id", userInfoBean.getData().getRegion_id());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "address", userInfoBean.getData().getAddress());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "signature", userInfoBean.getData().getSignature());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "mobile", userInfoBean.getData().getMobile());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "invite_code", userInfoBean.getData().getInvite_code());
    }

    @Override
    public void errorMsg(String msg, int flag) {
        mRefreshLayout.setPullDownRefreshEnable(false);
        dismissLoadingDialog();
        if (isLogin(msg) && flag == 0) {
            initDefaultInfo();
            return;
        } else if (isLogin(msg)) {
            aty.showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    /**
     * 将显示的个人信息设置到默认状态
     */
    private void initDefaultInfo() {
        UserUtil.clearUserInfo(aty);
        tv_editData.setVisibility(View.GONE);
        tv_editData1.setVisibility(View.GONE);
        tv_minetouxiang.setVisibility(View.GONE);
        iv_minetouxiang.setVisibility(View.GONE);
        tv_nickname.setVisibility(View.GONE);
        tv_synopsis.setVisibility(View.GONE);
        tv_serialNumber.setVisibility(View.GONE);
        ll_notLogin.setVisibility(View.VISIBLE);
        tv_follow.setText("0");
        tv_fans.setText("0");
        tv_beCollected.setText("0");
        tv_giveLike.setText("0");
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MinePresenter) mPresenter).getInfo(aty);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY <= 0) {
            rl_title.setBackgroundColor(Color.TRANSPARENT);
            //                          设置文字颜色，黑色，加透明度
            tv_title.setTextColor(Color.TRANSPARENT);
            tv_editData1.setTextColor(Color.TRANSPARENT);
            tv_divider.setBackgroundColor(Color.TRANSPARENT);
            Log.e("111", "y <= 0");
        } else if (scrollY > 0 && scrollY <= 200) {
            float scale = (float) scrollY / 200;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)白色透明
            rl_title.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            //    设置文字颜色，黑色，加透明度
            tv_title.setTextColor(Color.argb((int) alpha, 0, 0, 0));
            tv_editData1.setTextColor(Color.argb((int) alpha, 0, 0, 0));
            tv_divider.setBackgroundColor(Color.argb((int) alpha, 0, 0, 0));
            Log.e("111", "y > 0 && y <= imageHeight");
        } else {
//                          白色不透明
            rl_title.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
            //                          设置文字颜色
            //黑色
            tv_title.setTextColor(Color.argb((int) 255, 0, 0, 0));
            tv_editData1.setTextColor(Color.argb((int) 255, 0, 0, 0));
            tv_divider.setBackgroundColor(getResources().getColor(R.color.dividercolors2));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {// 如果等于1
            ((MinePresenter) mPresenter).getInfo(aty);
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusLogOutEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusMineFragmentEvent") && mPresenter != null) {
            ((MinePresenter) mPresenter).getInfo(aty);
        } else if (((String) msgEvent.getData()).equals("RxBusAvatarEvent")) {
            String avatar = PreferenceHelper.readString(aty, StringConstants.FILENAME, "avatar", "");
            if (!StringUtils.isEmpty(avatar)) {
//                GlideImageLoader.glideLoader(this, avatar + "?imageView2/1/w/70/h/70", img_headPortrait, 0);
//                GlideImageLoader.glideLoader(this, avatar + "?imageView2/1/w/70/h/70", img_headPortrait1, 0);
            }
        }
    }


}
