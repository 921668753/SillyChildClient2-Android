package com.sillykid.app.mall.goodslist.goodsdetails.comments;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.sillykid.app.R;
import com.sillykid.app.mall.goodslist.goodsdetails.GoodsDetailsContract;
import com.sillykid.app.mall.goodslist.goodsdetails.GoodsDetailsPresenter;
import com.sillykid.app.mall.goodslist.goodsdetails.dialog.SpecificationsBouncedDialog;
import com.sillykid.app.mall.goodslist.shop.ShopActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myshoppingcart.makesureorder.MakeSureOrderActivity;
import com.sillykid.app.utils.GlideImageLoader;
import com.sillykid.app.utils.SoftKeyboardUtils;

import cn.bingoogolapple.titlebar.BGATitleBar;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;

/**
 * 评论
 * Created by Admin on 2017/8/21.
 */

public class CommentsActivity extends BaseActivity implements GoodsDetailsContract.View {

    @BindView(id = R.id.tv_all, click = true)
    private TextView tv_all;

    @BindView(id = R.id.tv_havePictures, click = true)
    private TextView tv_havePictures;

    @BindView(id = R.id.tv_additionalReview, click = true)
    private TextView tv_additionalReview;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;
    private BaseFragment contentFragment2;

    /**
     * 店铺
     */
    @BindView(id = R.id.ll_shop, click = true)
    private LinearLayout ll_shop;

    /**
     * 客服
     */
    @BindView(id = R.id.ll_customerService, click = true)
    private LinearLayout ll_customerService;

    /**
     * 收藏
     */
    @BindView(id = R.id.ll_follow, click = true)
    private LinearLayout ll_follow;

    @BindView(id = R.id.tv_buyNow, click = true)
    private TextView tv_buyNow;

    @BindView(id = R.id.tv_addShoppingCart, click = true)
    private TextView tv_addShoppingCart;

    private SpecificationsBouncedDialog specificationsBouncedDialog = null;

    private boolean favorited = false;

    private int isRefresh = 0;

    private String price = "";

    private int num = 0;

    private String have_spec = "0";

    private int goodsid = 0;

    private int chageIcon = 0;

    private int product_id = 0;

    private String img = null;

    private int store = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_comments);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new GoodsDetailsPresenter(this);
        contentFragment = new AllCommentsFragment();
        contentFragment1 = new HavePicturesCommentsFragment();
        contentFragment2 = new AddCommentsFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
        goodsid = getIntent().getIntExtra("goodsid", 0);
        price = getIntent().getStringExtra("price");
        img = getIntent().getStringExtra("img");
        have_spec = getIntent().getStringExtra("have_spec");
        favorited = getIntent().getBooleanExtra("favorited", false);
        product_id = getIntent().getIntExtra("product_id", 0);
        store = getIntent().getIntExtra("store", 0);
        initDialog();
    }

    /**
     * 商品规格
     */
    private void initDialog() {
        specificationsBouncedDialog = new SpecificationsBouncedDialog(this) {
            @Override
            public void toDo(int goodId, int flag, int num1, int product_id) {
                num = num1;
                if (flag == 0) {
                    ((GoodsDetailsContract.Presenter) mPresenter).postAddCartGood(goodId, num1, product_id);
                } else if (flag == 1) {
                    ((GoodsDetailsContract.Presenter) mPresenter).postOrderBuyNow(goodId, num1, product_id);
                }
            }
        };
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        if (favorited) {
            ll_follow.setBackgroundResource(R.mipmap.mall_collect);
        } else {
            ll_follow.setBackgroundResource(R.mipmap.mall_uncollect);
        }
        if (chageIcon == 0) {
            cleanColors(0);
            changeFragment(contentFragment);
            chageIcon = 0;
        } else if (chageIcon == 1) {
            cleanColors(1);
            changeFragment(contentFragment1);
            chageIcon = 1;
        } else if (chageIcon == 2) {
            cleanColors(2);
            changeFragment(contentFragment2);
            chageIcon = 2;
        }
    }

    private void initTitle() {
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                SoftKeyboardUtils.packUpKeyboard(aty);
                if (isRefresh == 1) {
                    Intent intent = getIntent();
                    intent.putExtra("favorited", favorited);
                    setResult(RESULT_OK, intent);
                }
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.comment), "", true, R.id.titlebar, simpleDelegate);
    }


    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_all:
                cleanColors(0);
                changeFragment(contentFragment);
                chageIcon = 0;
                break;
            case R.id.tv_havePictures:
                cleanColors(1);
                changeFragment(contentFragment1);
                chageIcon = 1;
                break;
            case R.id.tv_additionalReview:
                cleanColors(2);
                changeFragment(contentFragment2);
                chageIcon = 2;
                break;
            case R.id.ll_shop:
                Intent shopIntent = new Intent(aty, ShopActivity.class);
                shopIntent.putExtra("storeid", getIntent().getIntExtra("store_id", 0));
                showActivity(aty, shopIntent);
                break;
            case R.id.ll_customerService:
                ((GoodsDetailsContract.Presenter) mPresenter).getIsLogin(this, 5);
                break;
            case R.id.ll_follow:
                showLoadingDialog(getString(R.string.dataLoad));
                if (!favorited) {
                    ((GoodsDetailsContract.Presenter) mPresenter).postFavoriteAdd(goodsid);
                } else {
                    ((GoodsDetailsContract.Presenter) mPresenter).postUnfavorite(goodsid);
                }
                break;
            case R.id.tv_addShoppingCart:
                if (store <= 0) {
                    ViewInject.toast(getString(R.string.inventory) + getString(R.string.insufficient));
                    return;
                }
                if (specificationsBouncedDialog == null) {
                    initDialog();
                }
                specificationsBouncedDialog.show();
                specificationsBouncedDialog.setFlag(0, goodsid, img, price, StringUtils.toInt(have_spec), product_id, store);
                break;
            case R.id.tv_buyNow:
                if (store <= 0) {
                    ViewInject.toast(getString(R.string.inventory) + getString(R.string.insufficient));
                    return;
                }
                if (specificationsBouncedDialog == null) {
                    initDialog();
                }
                specificationsBouncedDialog.show();
                specificationsBouncedDialog.setFlag(1, goodsid, img, price, StringUtils.toInt(have_spec), product_id, store);
                break;
            default:
                break;
        }
    }


    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_all.setTextColor(getResources().getColor(R.color.titletextcolors));
        tv_all.setBackground(getResources().getDrawable(R.drawable.shape_comments1));
        tv_havePictures.setTextColor(getResources().getColor(R.color.titletextcolors));
        tv_havePictures.setBackground(getResources().getDrawable(R.drawable.shape_comments1));
        tv_additionalReview.setTextColor(getResources().getColor(R.color.titletextcolors));
        tv_additionalReview.setBackground(getResources().getDrawable(R.drawable.shape_comments1));
        if (chageIcon == 0) {
            tv_all.setTextColor(getResources().getColor(R.color.whiteColors));
            tv_all.setBackground(getResources().getDrawable(R.drawable.shape_comments));
        } else if (chageIcon == 1) {
            tv_havePictures.setTextColor(getResources().getColor(R.color.whiteColors));
            tv_havePictures.setBackground(getResources().getDrawable(R.drawable.shape_comments));
        } else if (chageIcon == 2) {
            tv_additionalReview.setTextColor(getResources().getColor(R.color.whiteColors));
            tv_additionalReview.setBackground(getResources().getDrawable(R.drawable.shape_comments));
        } else {
            tv_all.setTextColor(getResources().getColor(R.color.whiteColors));
            tv_all.setBackground(getResources().getDrawable(R.drawable.shape_comments));
        }
    }

    @Override
    public void setPresenter(GoodsDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 1) {
            favorited = true;
            isRefresh = 1;
            ll_follow.setBackgroundResource(R.mipmap.mall_collect);
            ViewInject.toast(getString(R.string.collectionSuccess));
        } else if (flag == 2) {
            favorited = false;
            isRefresh = 1;
            ll_follow.setBackgroundResource(R.mipmap.mall_uncollect);
            ViewInject.toast(getString(R.string.uncollectible));
        } else if (flag == 3) {
            Intent buyNowIntent = new Intent(aty, MakeSureOrderActivity.class);
            buyNowIntent.putExtra("goodslistBean", success);
            buyNowIntent.putExtra("totalPrice", MathUtil.keepTwo(StringUtils.toDouble(price) * num));
            buyNowIntent.putExtra("type", 1);
            showActivity(aty, buyNowIntent);
        } else if (flag == 4) {
            ViewInject.toast(getString(R.string.addCartSuccess));
        } else if (flag == 5) {
            //首先需要构造使用客服者的用户信息
            CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
            CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();
/**
 * 启动客户服聊天界面。
 *
 * @param context           应用上下文。
 * @param customerServiceId 要与之聊天的客服 Id。
 * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
 * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
 */
            RongIM.getInstance().startCustomerServiceChat(this, "KEFU152876757817634", "在线客服", null);
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

    public void setAll(String text) {
        tv_all.setText(getString(R.string.allAeservationNumber) + text);
    }

    public void setHavePictures(String text) {
        tv_havePictures.setText(getString(R.string.figure) + text);
    }

    public void setAdditionalReview(String text) {
        tv_additionalReview.setText(getString(R.string.noImages) + text);
    }

    /**
     * 返回
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (isRefresh == 1) {
                    Intent intent = getIntent();
                    intent.putExtra("favorited", favorited);
                    setResult(RESULT_OK, intent);
                }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (specificationsBouncedDialog != null) {
            specificationsBouncedDialog.cancel();
        }
        specificationsBouncedDialog = null;
    }
}
