package com.sillykid.app.homepage.chartercustom.routes;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.AlipayBean;
import com.sillykid.app.entity.main.UserInfoBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.mywallet.coupons.CouponsActivity;
import com.sillykid.app.mine.mywallet.recharge.RechargeActivity;
import com.sillykid.app.utils.PayUtils;
import com.sillykid.app.utils.StringSubUtil;

/**
 * 收银台
 * Created by Admin on 2017/9/3.
 */

public class CheckstandActivity extends BaseActivity implements CheckstandContract.View{

    private CheckstandContract.Presenter mPresenter;

    @BindView(id=R.id.tv_ordermoney)
    private TextView tv_ordermoney;

    @BindView(id=R.id.ll_coupons,click = true)
    private LinearLayout ll_coupons;
    @BindView(id=R.id.ll_couponmoney,click = true)
    private LinearLayout ll_couponmoney;
    @BindView(id=R.id.tv_couponmoney)
    private TextView tv_couponmoney;

    @BindView(id=R.id.ll_needpay)
    private LinearLayout ll_needpay;
    @BindView(id=R.id.tv_needpay)
    private TextView tv_needpay;

    @BindView(id=R.id.ll_payweixin,click = true)
    private LinearLayout ll_payweixin;
    @BindView(id=R.id.iv_weixin)
    private ImageView iv_weixin;

    @BindView(id=R.id.ll_payzhifubao,click = true)
    private LinearLayout ll_payzhifubao;
    @BindView(id=R.id.iv_zhifubao)
    private ImageView iv_zhifubao;

    @BindView(id=R.id.ll_payyue,click = true)
    private LinearLayout ll_payyue;
    @BindView(id=R.id.ll_yue,click = true)
    private LinearLayout ll_yue;
    @BindView(id=R.id.tv_yue)
    private TextView tv_yue;
    @BindView(id=R.id.iv_yue,click = true)
    private ImageView iv_yue;

    @BindView(id=R.id.tv_okpay,click = true)
    private TextView tv_okpay;

    private int paytype=-1;//支付类型:0,微信支付；1，支付宝支付；2，余额
    private String orderid;
    private double yued;
    private double moneyd;
    private String paymoney_fmt;
    private int couponid=0;//优惠券id
    private String couponmoney;//优惠价格
    private PayUtils payUtils;
    private String single;
    private int decimalnum=0;//保留小数的位数
    private String moneyallnum;//订单价格获取到的数字
    private int singleposition=0;//金额符号在字符串中所在的位置
    private UserInfoBean userInfoBean;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_checkstand);
    }

    @Override
    public void initData() {
        super.initData();
        Intent tagintent=getIntent();
        orderid=tagintent.getStringExtra("orderid");
        moneyd=StringUtils.toDouble(tagintent.getStringExtra("paymoney"));
        paymoney_fmt=tagintent.getStringExtra("paymoneyfmt");
        mPresenter=new CheckstandPresenter(this);

    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        setPayType(iv_weixin,NumericConstants.orderWX);
        tv_ordermoney.setText(paymoney_fmt);
        initMathNeedMoney();
        showLoadingDialog(getString(R.string.dataLoad));
        mPresenter.getInfo(2);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.checkstand), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.ll_payweixin:
                setPayType(iv_weixin,NumericConstants.orderWX);
                break;
            case R.id.ll_payzhifubao:
                setPayType(iv_zhifubao,NumericConstants.orderAplipay);
                break;
            case R.id.ll_payyue:
            case R.id.ll_yue:
            case R.id.iv_yue:
                setPayType(iv_yue,NumericConstants.orderBalance);
                break;
            case R.id.tv_okpay:
                doPay();
                break;
            case R.id.ll_coupons:
            case R.id.ll_couponmoney:
                //选择优惠券
                Intent jumpintent=new Intent(this, CouponsActivity.class);
                jumpintent.putExtra("type","confirmOrder");
                jumpintent.putExtra("total_price",moneyd+"");
                showActivityForResult(this,jumpintent,0);
                break;
        }
    }

    private void setPayType(ImageView iv,int type){
        if (type!=paytype){
            iv_weixin.setImageResource(R.mipmap.mineaddress_unselectxxx);
            iv_zhifubao.setImageResource(R.mipmap.mineaddress_unselectxxx);
            iv_yue.setImageResource(R.mipmap.mine_payoffxxx);
            if (type==NumericConstants.orderBalance){
                if (yued<moneyd){
                    ViewInject.toast(aty.getResources().getString(R.string.lackOfBalance));
                    paytype=-1;
                    return;
                }else{
                    iv_yue.setImageResource(R.mipmap.mine_payonxxx);
                    paytype=type;
                }
            }else{
                iv.setImageResource(R.mipmap.mineaddress_selectxxx);
                paytype=type;
            }
        }
    }

    private void doPay(){
        if (paytype==-1){//没有选择支付方式
            ViewInject.toast(aty.getResources().getString(R.string.lackOfBalance));
        }else{
            showLoadingDialog(getString(R.string.submissionLoad));
            mPresenter.getInfo(1);
        }
    }


    @Override
    public void setPresenter(CheckstandContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag==1){
//            userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
//            if (StringUtils.toDouble(userInfoBean.getData().getUser_money())!=StringUtils.toDouble(tv_yue.getText().toString())){
//                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
//            }
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "user_money", userInfoBean.getData().getUser_money());
//            if (TextUtils.isEmpty(userInfoBean.getData().getUser_money())){
//                tv_yue.setText(getString(R.string.currentBalance)+"￥0.00");
//                yued=0.00;
//            }else{
//                tv_yue.setText(getString(R.string.currentBalance)+userInfoBean.getData().getUser_money());
//                yued= StringUtils.toDouble(userInfoBean.getData().getUser_money());
//            }
//            if (paytype==2){
//                if (yued<moneyd){
//                    ViewInject.toast(aty.getResources().getString(R.string.lackOfBalance));
//                }else{
//                    mPresenter.orderPay(orderid,paytype+"",couponid+"");
//                }
//            }else{
//                mPresenter.orderPay(orderid,paytype+"",couponid+"");
//            }
        }else if (flag==2){
            userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
//            if (userInfoBean!=null&&userInfoBean.getData()!=null&&!TextUtils.isEmpty(userInfoBean.getData().getUser_money_fmt())){
//                tv_yue.setText(getString(R.string.currentBalance)+userInfoBean.getData().getUser_money_fmt());
//                yued=StringUtils.toDouble(userInfoBean.getData().getUser_money());
//            }else{
//                tv_yue.setText(getString(R.string.currentBalance)+"￥0.00");
//                yued=0.00;
//            }
            dismissLoadingDialog();
        }else{
            AlipayBean payFinshBean = (AlipayBean) JsonUtil.getInstance().json2Obj(success, AlipayBean.class);
            if (payFinshBean==null||payFinshBean.getData()==null){
                dismissLoadingDialog();
                ViewInject.toast(getString(R.string.payParseError));
            }else{
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
                dismissLoadingDialog();
                paymoney_fmt=payFinshBean.getData().getRealPrice();
                switch (paytype){
                    case NumericConstants.orderWX:
                        Log.d("调试","订单微信支付结果："+success);
                        AlipayBean.ResultBean.WxPayParamsBean wxPayParamsBean = payFinshBean.getData().getWxPayParams();
                        if (wxPayParamsBean==null){
                            dismissLoadingDialog();
                            ViewInject.toast(getString(R.string.payParseError));
                        }else{
                            if (payUtils == null) payUtils = new PayUtils(this);
                            dismissLoadingDialog();
                            payUtils.doPayment(wxPayParamsBean.getAppid(), wxPayParamsBean.getPartnerid(), wxPayParamsBean.getPrepayid(), wxPayParamsBean.getPackageX(), wxPayParamsBean.getNoncestr(), wxPayParamsBean.getTimestamp(), wxPayParamsBean.getSign());
                        }
                        break;
                    case NumericConstants.orderAplipay:
                        Log.d("调试","订单支付宝支付结果："+success);
                        if (payUtils == null) payUtils = new PayUtils(this);
                        payUtils.doPay(payFinshBean.getData().getAliPayParams());
                        break;
                    case NumericConstants.orderBalance:
                        Log.d("调试","订单余额支付结果："+success);
                        Intent jumpintent=new Intent(this,PaySuccessActivity.class);
                        jumpintent.putExtra("orderid",orderid);
                        jumpintent.putExtra("paytype",paytype);
                        jumpintent.putExtra("paymoney",tv_needpay.getText().toString());
                        showActivity(this,jumpintent);
                        finish();
                        break;
                }
            }
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)){
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(this, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this,LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            couponmoney=data.getStringExtra("money");
            if (!TextUtils.isEmpty(couponmoney)){
                tv_couponmoney.setText(getString(R.string.subtract)+couponmoney);
                if (ll_needpay.getVisibility()!=View.GONE){
                    moneyallnum=MathUtil.subtractWithDecimal(moneyallnum,couponmoney,decimalnum);
                    if (singleposition>0){
                        tv_needpay.setText(moneyallnum+single);
                    }else{
                        tv_needpay.setText(single+moneyallnum);
                    }
                }
            }
            couponid=data.getIntExtra("id",0);
        }
    }

    /**
     * 计算出需要支付的金额并展示
     */
    private void initMathNeedMoney(){
        single=StringSubUtil.getStringNoNumber(paymoney_fmt);
        if (!TextUtils.isEmpty(paymoney_fmt.replace(single,""))){
            moneyallnum=paymoney_fmt.replace(single,"").replace(",","");
            if (!TextUtils.isEmpty(moneyallnum)&&moneyallnum.matches("-?[0-9]+.?[0-9]*")){
                int pointposition=moneyallnum.indexOf(".");
                if (pointposition>0){
                    decimalnum=moneyallnum.length()-1-pointposition;
                }else{
                    decimalnum=0;
                }
                singleposition=paymoney_fmt.indexOf(single);
                if (TextUtils.isEmpty(paymoney_fmt)){
                    ll_needpay.setVisibility(View.GONE);
                }else{
                    tv_needpay.setText(paymoney_fmt);
                }
            }else{
                ll_needpay.setVisibility(View.GONE);
            }
        }else{
            ll_needpay.setVisibility(View.GONE);
        }
    }

    public int getPaytype() {
        return paytype;
    }

    public String getOrderid() {
        return orderid;
    }

    public String getPaymoney_fmt() {
        return paymoney_fmt;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showLoadingDialog(getString(R.string.dataLoad));
        mPresenter.getInfo(2);
    }
}
