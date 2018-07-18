/*
 * Copyright (C) 2015 tony<wuhaiyang1213@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sillykid.app.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.ViewInject;
import com.sillykid.app.BuildConfig;
import com.sillykid.app.R;
import com.sillykid.app.mine.myshoppingcart.makesureorder.PaymentOrderActivity;
import com.sillykid.app.mine.myshoppingcart.makesureorder.payresult.PayCompleteActivity;
import com.sillykid.app.mine.mywallet.recharge.RechargeActivity;
import com.sillykid.app.mine.mywallet.recharge.rechargeresult.RechargeCompleteActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void setRootView() {
        api = WXAPIFactory.createWXAPI(this, BuildConfig.WEIXIN_APPKEY);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("pay", "errStr=" + resp.errStr);
        Log.d("pay", "errCode=" + resp.errCode);
        Log.d("pay", "getType=" + resp.getType());
        Log.d("pay", "toString=" + resp.toString());
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
//                if (KJActivityStack.create().findActivity(RechargeActivity.class) != null) {
//                    ViewInject.toast(getString(R.string.alipay_succeed));
//                } else if (KJActivityStack.create().findActivity(PaymentOrderActivity.class) != null) {
                jumpPayComplete(1);
                //   }
//            } else if (resp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
//                ViewInject.toast(getString(R.string.alipay_order_cancel));
            } else {
//                if (KJActivityStack.create().findActivity(RechargeActivity.class) != null) {
//                    ViewInject.toast(getString(R.string.alipay_order_error));
//                } else if (KJActivityStack.create().findActivity(PaymentOrderActivity.class) != null) {
                jumpPayComplete(0);
                //   }
            }
            finish();
        }
    }

    /**
     * 跳转支付完成页面
     *
     * @param order_status 1成功 0失败
     */
    private void jumpPayComplete(int order_status) {
        if (KJActivityStack.create().findActivity(RechargeActivity.class) != null) {
            Intent jumpintent = new Intent(this, RechargeCompleteActivity.class);
            Activity context = KJActivityStack.create().findActivity(RechargeActivity.class);
            //    Intent intent = new Intent(aty, PayCompleteActivity.class);
            jumpintent.putExtra("recharge_status", order_status);
            jumpintent.putExtra("recharge_money", ((RechargeActivity) context).getRechargeMoney());
            showActivity(this, jumpintent);
        } else if (KJActivityStack.create().findActivity(PaymentOrderActivity.class) != null) {
            Intent jumpintent = new Intent(this, PayCompleteActivity.class);
            Activity context = KJActivityStack.create().findActivity(PaymentOrderActivity.class);
            //   Intent intent = new Intent(aty, PayCompleteActivity.class);
            jumpintent.putExtra("order_status", order_status);
            jumpintent.putExtra("order_id", ((PaymentOrderActivity) context).getOrderId());
            showActivity(this, jumpintent);
        }
    }

}