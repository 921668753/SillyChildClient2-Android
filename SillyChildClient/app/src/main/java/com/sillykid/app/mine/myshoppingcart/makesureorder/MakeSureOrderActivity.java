package com.sillykid.app.mine.myshoppingcart.makesureorder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.mine.myshoppingcart.makesureorder.MakeSureOrderViewAdaper;
import com.sillykid.app.entity.mine.myshoppingcart.makesureorder.CreateOrderBean;
import com.sillykid.app.entity.mine.myshoppingcart.makesureorder.MakeSureOrderBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.deliveryaddress.AddNewAddressActivity;
import com.sillykid.app.mine.deliveryaddress.DeliveryAddressActivity;
import com.sillykid.app.mine.mywallet.coupons.CouponsActivity;

import static com.sillykid.app.constant.NumericConstants.RESULT_CODE_BASKET_ADD;
import static com.sillykid.app.constant.NumericConstants.RESULT_CODE_BASKET_MINUS;
import static com.sillykid.app.constant.NumericConstants.RESULT_CODE_GET;

/**
 * 我的购物车中的确认订单
 * Created by Administrator on 2017/9/2.
 */

public class MakeSureOrderActivity extends BaseActivity implements MakeSureOrderContract.View {

    /**
     * 地址
     */
    @BindView(id = R.id.ll_name, click = true)
    private LinearLayout ll_name;

    @BindView(id = R.id.tv_name)
    private TextView tv_name;

    @BindView(id = R.id.tv_phone)
    private TextView tv_phone;

    @BindView(id = R.id.ll_address, click = true)
    private LinearLayout ll_address;

    @BindView(id = R.id.tv_tagpersonName)
    private TextView tv_tagpersonName;

    @BindView(id = R.id.tv_tagpersonAddress)
    private TextView tv_tagpersonAddress;

    /**
     * 商品列表
     */
    @BindView(id = R.id.lv_makeSureOrder)
    private ChildListView lv_makeSureOrder;

    @BindView(id = R.id.tv_goodsMoney)
    private TextView tv_goodsMoney;


    /**
     * 运费
     */
    @BindView(id = R.id.tv_freightMoney)
    public TextView tv_freightMoney;


    /**
     * 选择优惠券
     */
    @BindView(id = R.id.tv_couponsMoney)
    public TextView tv_couponsMoney;

    @BindView(id = R.id.tv_usable, click = true)
    public TextView tv_usable;

    @BindView(id = R.id.img_right)
    public ImageView img_right;


    /**
     * 优惠活动
     */
    @BindView(id = R.id.tv_preferentialActivities)
    public TextView tv_preferentialActivities;

    /**
     * 合计价格
     */
    @BindView(id = R.id.tv_money)
    public TextView tv_money;

    @BindView(id = R.id.tv_submitOrder, click = true)
    public TextView tv_submitOrder;


    private String totalPrice = "";

    private MakeSureOrderViewAdaper makeSureOrderViewAdaper = null;

    private String cartIds = null;

    private int bonusid = 0;

    private int addr_id = 0;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_makesureorder);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MakeSureOrderPresenter(this);
        cartIds = getIntent().getStringExtra("goodslistBean");
        totalPrice = getIntent().getStringExtra("totalPrice");
        makeSureOrderViewAdaper = new MakeSureOrderViewAdaper(this);
        showLoadingDialog(getString(R.string.dataLoad));
        if (getIntent().getIntExtra("type", 0) == 1) {
            getSuccess(cartIds, 0);
        } else {
            ((MakeSureOrderContract.Presenter) mPresenter).getCartBalance(cartIds);
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        tv_money.setText(totalPrice);
        tv_goodsMoney.setText(getString(R.string.renminbi) + totalPrice);
        lv_makeSureOrder.setAdapter(makeSureOrderViewAdaper);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.confirmOrder), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_name:
            case R.id.ll_address:
                if (tv_name.getText().toString().contains(getString(R.string.noAddress1))) {
                    Intent intent = new Intent(aty, AddNewAddressActivity.class);
                    startActivityForResult(intent, RESULT_CODE_BASKET_MINUS);
                    return;
                }
                Intent intent = new Intent(aty, DeliveryAddressActivity.class);
                intent.putExtra("type", -1);
                startActivityForResult(intent, RESULT_CODE_BASKET_ADD);
                break;
            case R.id.tv_usable:
                Intent intent1 = new Intent(aty, CouponsActivity.class);
                intent1.putExtra("type", -1);
                intent1.putExtra("money", totalPrice);
                startActivityForResult(intent1, RESULT_CODE_GET);
                break;
            case R.id.tv_submitOrder:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((MakeSureOrderContract.Presenter) mPresenter).postCreateOrder(addr_id, bonusid, cartIds);
                break;
        }

    }

    @Override
    public void setPresenter(MakeSureOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            MakeSureOrderBean makeSureOrderBean = (MakeSureOrderBean) JsonUtil.getInstance().json2Obj(success, MakeSureOrderBean.class);
            if (makeSureOrderBean.getData() == null) {
                ViewInject.toast(getString(R.string.serverReturnsDataNull1));
                return;
            }
            if (makeSureOrderBean.getData().getAddress() != null && makeSureOrderBean.getData().getAddress().getAddr_id() <= 0 && StringUtils.isEmpty(makeSureOrderBean.getData().getAddress().getProvince())) {
                tv_name.setText(getString(R.string.noAddress1));
                tv_phone.setVisibility(View.GONE);
                ll_address.setVisibility(View.GONE);
            } else {
                addr_id = makeSureOrderBean.getData().getAddress().getAddr_id();
                tv_phone.setVisibility(View.VISIBLE);
                ll_address.setVisibility(View.VISIBLE);
                tv_name.setText(makeSureOrderBean.getData().getAddress().getName());
                tv_phone.setText(makeSureOrderBean.getData().getAddress().getMobile());
                tv_tagpersonName.setText(makeSureOrderBean.getData().getAddress().getProvince() + "  " + makeSureOrderBean.getData().getAddress().getCity() + "  " + makeSureOrderBean.getData().getAddress().getRegion());
                tv_tagpersonAddress.setText(makeSureOrderBean.getData().getAddress().getAddr());
            }
            if (makeSureOrderBean.getData().getGoods() != null && makeSureOrderBean.getData().getGoods().size() > 0) {
                makeSureOrderViewAdaper.clear();
                makeSureOrderViewAdaper.addMoreData(makeSureOrderBean.getData().getGoods());
            }
            tv_goodsMoney.setText(getString(R.string.renminbi) + totalPrice);
            tv_freightMoney.setText(getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(makeSureOrderBean.getData().getOrderInfo().getShip_account())));
            if (StringUtils.toInt(makeSureOrderBean.getData().getOrderInfo().getBonus_account(), 0) == 0) {
                tv_usable.setVisibility(View.GONE);
                img_right.setVisibility(View.GONE);
            } else {
                tv_usable.setVisibility(View.VISIBLE);
                img_right.setVisibility(View.VISIBLE);
                tv_usable.setText(makeSureOrderBean.getData().getOrderInfo().getBonus_account() + getString(R.string.usable1));
            }
            if (StringUtils.toDouble(makeSureOrderBean.getData().getOrderInfo().getActivity_account()) > 0) {
                tv_preferentialActivities.setText(getString(R.string.renminbi) + "-" + MathUtil.keepTwo(StringUtils.toDouble(makeSureOrderBean.getData().getOrderInfo().getActivity_account())));
            }
        } else if (flag == 1) {
            CreateOrderBean createOrderBean = (CreateOrderBean) JsonUtil.getInstance().json2Obj(success, CreateOrderBean.class);
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusMyShoppingCartEvent"));
            Intent intent = new Intent(aty, PaymentOrderActivity.class);
            intent.putExtra("order_id", createOrderBean.getData().getOrder_id());
            intent.putExtra("type", 1);
            intent.putExtra("last_time", String.valueOf(StringUtils.toLong(createOrderBean.getData().getLast_time()) - StringUtils.toLong(createOrderBean.getData().getNow_time())));
            intent.putExtra("balance", createOrderBean.getData().getBalance());
            intent.putExtra("money", tv_money.getText().toString());
            skipActivity(aty, intent);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            if (flag == 0) {
                finish();
            }
            return;
        }
        ViewInject.toast(msg);
        if (msg.contains(getString(R.string.soldOut))) {
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CODE_GET && resultCode == RESULT_OK) {
            String money = data.getStringExtra("money");
            bonusid = data.getIntExtra("id", 0);
            tv_couponsMoney.setText(getString(R.string.renminbi) + "-" + MathUtil.keepTwo(StringUtils.toDouble(money)));
            tv_money.setText(calculationTotal());
        } else if (requestCode == RESULT_CODE_BASKET_ADD && resultCode == RESULT_OK || requestCode == RESULT_CODE_BASKET_MINUS && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            addr_id = data.getIntExtra("addr_id", 0);
            String mobile = data.getStringExtra("mobile");
            String provinceRegion = data.getStringExtra("provinceRegion");
            String addr = data.getStringExtra("addr");
            ll_name.setVisibility(View.VISIBLE);
            tv_name.setText(name);
            tv_phone.setVisibility(View.VISIBLE);
            tv_phone.setText(mobile);
            ll_address.setVisibility(View.VISIBLE);
            tv_tagpersonName.setText(provinceRegion);
            tv_tagpersonAddress.setText(addr);
        }
    }

    /**
     * 计算合计
     */
    private String calculationTotal() {
        double total = 0;
        total = StringUtils.toDouble(tv_goodsMoney.getText().toString().trim().substring(1)) + StringUtils.toDouble(tv_freightMoney.getText().toString().trim().substring(1))
                + StringUtils.toDouble(tv_couponsMoney.getText().toString().trim().substring(1)) + StringUtils.toDouble(tv_preferentialActivities.getText().toString().trim().substring(1));
        if (total <= 0) {
            total = 0;
        }
        return MathUtil.keepTwo(total);
    }


}
