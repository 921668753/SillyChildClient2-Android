package com.sillykid.app.main;

import android.annotation.SuppressLint;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.UserInfoBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.message.interactivemessage.imuitl.UserUtil;
import com.sillykid.app.mine.deliveryaddress.DeliveryAddressActivity;
import com.sillykid.app.mine.mycollection.MyCollectionActivity;
import com.sillykid.app.mine.myorder.MyOrderActivity;
import com.sillykid.app.mine.myshoppingcart.MyShoppingCartActivity;
import com.sillykid.app.mine.mywallet.MyWalletActivity;
import com.sillykid.app.mine.mywallet.coupons.CouponsActivity;
import com.sillykid.app.mine.personaldata.PersonalDataActivity;
import com.sillykid.app.mine.setup.SetUpActivity;
import com.sillykid.app.mine.sharingceremony.SharingCeremonyActivity;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;
import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

/**
 * 个人中心
 * Created by Admin on 2017/8/10.
 */
@SuppressLint("NewApi")
public class Mine1Fragment extends BaseFragment implements MineContract.View, View.OnScrollChangeListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.sv_mine)
    private ScrollView sv_mine;

    @BindView(id = R.id.img_messages, click = true)
    private ImageView img_messages;

    @BindView(id = R.id.img_setUp, click = true)
    private ImageView img_setUp;

    @BindView(id = R.id.rl_title)
    private RelativeLayout rl_title;

    @BindView(id = R.id.img_messages1, click = true)
    private ImageView img_messages1;

    @BindView(id = R.id.img_setUp1, click = true)
    private ImageView img_setUp1;

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

//    @BindView(id = R.id.ll_notLogin, click = true)
//    private LinearLayout ll_notLogin;


    @BindView(id = R.id.img_head, click = true)
    private ImageView img_head;

    @BindView(id = R.id.tv_nickName, click = true)
    private TextView tv_nickName;

    /**
     * 收藏数量
     */
    @BindView(id = R.id.tv_dynamicCollection)
    private TextView tv_dynamicCollection;

    /**
     * 关注数量
     */
    @BindView(id = R.id.tv_follow)
    private TextView tv_follow;

    /**
     * 足迹数量
     */
    @BindView(id = R.id.tv_footprint)
    private TextView tv_footprint;

    /**
     * 心得数量
     */
    @BindView(id = R.id.tv_tips)
    private TextView tv_tips;

    /**
     * 钻石会员
     */
    @BindView(id = R.id.img_members)
    private ImageView img_members;
    @BindView(id = R.id.tv_members)
    private TextView tv_members;

    /**
     * 查看特权
     */
    @BindView(id = R.id.tv_clickPrivilege, click = true)
    private TextView tv_clickPrivilege;

    /**
     * 我的订单
     */
    @BindView(id = R.id.ll_myOrder, click = true)
    private LinearLayout ll_myOrder;

    /**
     * 待付款
     */
    @BindView(id = R.id.ll_obligation, click = true)
    private LinearLayout ll_obligation;

    /**
     * 待发货
     */
    @BindView(id = R.id.ll_sendGoods, click = true)
    private LinearLayout ll_sendGoods;

    /**
     * 待收货
     */
    @BindView(id = R.id.ll_waitGoods, click = true)
    private LinearLayout ll_waitGoods;

    /**
     * 评价
     */
    @BindView(id = R.id.ll_evaluation, click = true)
    private LinearLayout ll_evaluation;

    /**
     * 退货退款
     */
    @BindView(id = R.id.ll_refunds, click = true)
    private LinearLayout ll_refunds;

    /**
     * 钱包
     */
    @BindView(id = R.id.ll_purse, click = true)
    private LinearLayout ll_purse;

    /**
     * 邀请好友
     */
    @BindView(id = R.id.ll_inviteFriends, click = true)
    private LinearLayout ll_inviteFriends;

    /**
     * 优惠券
     */
    @BindView(id = R.id.ll_coupons, click = true)
    private LinearLayout ll_coupons;
    @BindView(id = R.id.tv_coupons)
    private TextView tv_coupons;


    /**
     * 帮助与客服
     */
    @BindView(id = R.id.ll_helpCustomerService, click = true)
    private LinearLayout ll_helpCustomerService;

    /**
     * 购物车
     */
    @BindView(id = R.id.ll_shoppingCart, click = true)
    private LinearLayout ll_shoppingCart;

    /**
     * 收货地址
     */
    @BindView(id = R.id.ll_deliveryAddress, click = true)
    private LinearLayout ll_deliveryAddress;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mine1, null);
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
        sv_mine.setOnScrollChangeListener(this);
        mRefreshLayout.beginRefreshing();
    }


    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_setUp:
            case R.id.img_setUp1:
                aty.showActivity(aty, SetUpActivity.class);
                break;
            case R.id.img_messages:
            case R.id.img_messages1:

                break;
            case R.id.img_head:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
            case R.id.tv_nickName:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
            case R.id.ll_dynamicCollection:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 2);
                break;
            case R.id.ll_follow:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 3);
                break;
            case R.id.ll_footprint:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 4);
                break;
            case R.id.ll_tips:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 5);
                break;
            case R.id.tv_clickPrivilege:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 6);
                break;
            case R.id.ll_myOrder:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 7);
                break;
            case R.id.ll_obligation:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 8);
                break;
            case R.id.ll_sendGoods:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 9);
                break;
            case R.id.ll_waitGoods:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 10);
                break;
            case R.id.ll_evaluation:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 11);
                break;
            case R.id.ll_refunds:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 12);
                break;
            case R.id.ll_purse:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 13);
                break;
            case R.id.ll_inviteFriends:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 14);
                break;
            case R.id.ll_coupons:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 15);
                break;
            case R.id.ll_helpCustomerService:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 16);
                break;
            case R.id.ll_shoppingCart:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 17);
                break;
            case R.id.ll_deliveryAddress:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 18);
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
                saveUserInfo(userInfoBean);
                tv_nickName.setText(userInfoBean.getData().getNick_name());
                if (StringUtils.isEmpty(userInfoBean.getData().getFace())) {
                    img_head.setImageResource(R.mipmap.avatar);
                } else {
                    GlideImageLoader.glideLoader(aty, userInfoBean.getData().getFace(), img_head, 0, R.mipmap.avatar);
                }
                //     tv_serialNumber.setText(userInfoBean.getData().getShz());
                if (StringUtils.isEmpty(userInfoBean.getData().getSignature())) {
                    //            tv_synopsis.setVisibility(View.GONE);
                } else {
//                    tv_synopsis.setVisibility(View.VISIBLE);
//                    tv_synopsis.setText(userInfoBean.getData().getSignature());
                }
            }
        } else if (flag == 1) {
            Intent personalDataIntent = new Intent(aty, PersonalDataActivity.class);
            // 获取内容
            // 设置结果 结果码，一个数据
            startActivityForResult(personalDataIntent, REQUEST_CODE);
        } else if (flag == 2) {
            aty.showActivity(aty, MyCollectionActivity.class);
        } else if (flag == 3) {
            //    aty.showActivity(aty, MyWalletActivity.class);
        } else if (flag == 4) {
        } else if (flag == 5) {
        } else if (flag == 6) {
            //    aty.showActivity(aty, SharingCeremonyActivity.class);
        } else if (flag == 7) {
            aty.showActivity(aty, MyOrderActivity.class);
            //   aty.showActivity(aty, DeliveryAddressActivity.class);
        } else if (flag == 8) {
            Intent intent = new Intent(aty, MyOrderActivity.class);
            intent.putExtra("chageIcon", 0);
            aty.showActivity(aty, intent);
        } else if (flag == 9) {
            Intent intent = new Intent(aty, MyOrderActivity.class);
            intent.putExtra("chageIcon", 1);
            aty.showActivity(aty, intent);
        } else if (flag == 10) {
            Intent intent = new Intent(aty, MyOrderActivity.class);
            intent.putExtra("chageIcon", 2);
            aty.showActivity(aty, intent);
        } else if (flag == 11) {
            Intent intent = new Intent(aty, MyOrderActivity.class);
            intent.putExtra("chageIcon", 3);
            aty.showActivity(aty, intent);
        } else if (flag == 12) {
            Intent intent = new Intent(aty, MyOrderActivity.class);
            intent.putExtra("chageIcon", 4);
            aty.showActivity(aty, intent);
        } else if (flag == 13) {
            aty.showActivity(aty, MyWalletActivity.class);
        } else if (flag == 14) {
            aty.showActivity(aty, SharingCeremonyActivity.class);
        } else if (flag == 15) {
            aty.showActivity(aty, CouponsActivity.class);
        } else if (flag == 16) {

//            aty.showActivity(aty, SharingCeremonyActivity.class);
        }else if (flag == 17) {
            aty.showActivity(aty, MyShoppingCartActivity.class);
        }else if (flag == 18) {
            aty.showActivity(aty, DeliveryAddressActivity.class);
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
        img_head.setImageResource(R.mipmap.avatar);
        tv_nickName.setText(getString(R.string.loginOrRegister));
        tv_dynamicCollection.setText("0");
        tv_follow.setText("0");
        tv_footprint.setText("0");
        tv_tips.setText("0");
        tv_coupons.setText("");
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
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY <= 0) {
            rl_title.setBackgroundColor(Color.TRANSPARENT);
            //                          设置文字颜色，黑色，加透明度
            tv_title.setTextColor(Color.TRANSPARENT);
            img_messages1.setImageDrawable(null);
            img_setUp1.setImageDrawable(null);
            Log.e("111", "y <= 0");
        } else if (scrollY > 0 && scrollY <= 200) {
            float scale = (float) scrollY / 200;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)白色透明
            rl_title.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            //                          设置文字颜色，黑色，加透明度
            tv_title.setTextColor(Color.argb((int) alpha, 0, 0, 0));
            img_messages1.setImageResource(R.mipmap.direct_messages);
            img_setUp1.setImageResource(R.mipmap.setting);
            Log.e("111", "y > 0 && y <= imageHeight");
        } else {
//                          白色不透明
            rl_title.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
            //                          设置文字颜色
            //黑色
            tv_title.setTextColor(Color.argb((int) 255, 0, 0, 0));
            img_messages1.setImageResource(R.mipmap.direct_messages1);
            img_setUp1.setImageResource(R.mipmap.setting1);
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
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusLogOutEvent") && mPresenter != null) {
            ((MinePresenter) mPresenter).getInfo(aty);
        }

    }

}
