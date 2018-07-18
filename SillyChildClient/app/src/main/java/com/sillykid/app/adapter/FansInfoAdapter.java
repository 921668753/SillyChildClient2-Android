package com.sillykid.app.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.common.cklibrary.common.StringConstants;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.entity.FansAndAttentionBean.ResultBean.ListBean;
import com.sillykid.app.utils.GlideImageLoader;

import java.text.SimpleDateFormat;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 我的 粉丝 关注列表
 * Created by Admin on 2017/8/15.
 */

public class FansInfoAdapter extends BGAAdapterViewAdapter<ListBean> {
    private int userid;
    private Context context;
    private SimpleDateFormat dateformat;
    private boolean formguanzhu;

    public FansInfoAdapter(Context context,boolean formguanzhu) {
        super(context, R.layout.item_fans_list);
        this.context = context;
        this.formguanzhu = formguanzhu;
        userid=PreferenceHelper.readInt(context, StringConstants.FILENAME, "userId", 0);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.tv_status);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        GlideImageLoader.glideLoader(mContext, listBean.getHead_pic(), (ImageView) viewHolderHelper.getView(R.id.iv_headimg),0, R.mipmap.avatar);
        viewHolderHelper.setText(R.id.iv_nickname, listBean.getNickname());

        if (listBean.getUser_id()==userid){
            viewHolderHelper.setText(R.id.tv_status, mContext.getString(R.string.self));
            viewHolderHelper.setBackgroundRes(R.id.tv_status,R.drawable.shape_soldgray_rad);
        }else{
            if (listBean.getIs_attention()==0){
                viewHolderHelper.setText(R.id.tv_status, mContext.getString(R.string.follow));
                viewHolderHelper.setBackgroundRes(R.id.tv_status,R.drawable.shape_login1);
            }else{
                viewHolderHelper.setText(R.id.tv_status, mContext.getString(R.string.followed));
                viewHolderHelper.setBackgroundRes(R.id.tv_status,R.drawable.shape_soldgray_rad);
            }
        }
//
//        switch (listBean.getStatusX()) {
//            case NumericConstants.NoPay:
//                viewHolderHelper.setVisibility(R.id.ll_btns, View.VISIBLE);
//                viewHolderHelper.setText(R.id.tv_charter_status, R.string.obligation);
//                viewHolderHelper.setText(R.id.tv_charter_paymoney_hint, R.string.needpayWithSymbol);
//                viewHolderHelper.setVisibility(R.id.tv_leftbtn, View.VISIBLE);
//                viewHolderHelper.setVisibility(R.id.tv_rightbtn, View.VISIBLE);
//                viewHolderHelper.setVisibility(R.id.tv_rightbtn2, View.GONE);
//                viewHolderHelper.setText(R.id.tv_leftbtn, R.string.delete);
//                viewHolderHelper.setText(R.id.tv_rightbtn, R.string.toPayment);
//                break;
//            case NumericConstants.SendOrder://未派单
//                viewHolderHelper.setVisibility(R.id.ll_btns, View.GONE);
//                viewHolderHelper.setText(R.id.tv_charter_status, R.string.sendSingle);
//                break;
//            case NumericConstants.WaiteOrder://已派单待接单
//                viewHolderHelper.setVisibility(R.id.ll_btns, View.GONE);
//                viewHolderHelper.setText(R.id.tv_charter_status, R.string.sendSingleWaitingList);
//                break;
//            case NumericConstants.OnGoing://即将开始
//            case NumericConstants.WaiteEvaluate://进行中
//                viewHolderHelper.setVisibility(R.id.ll_btns, View.VISIBLE);
//                viewHolderHelper.setVisibility(R.id.tv_leftbtn, View.VISIBLE);
//                viewHolderHelper.setVisibility(R.id.tv_rightbtn, View.VISIBLE);
//                if (StringUtils.toInt(listBean.getUser_confirm(), 0) == 0) {
//                    viewHolderHelper.setText(R.id.tv_charter_status, R.string.ongoing);
//                    viewHolderHelper.setVisibility(R.id.tv_rightbtn2, View.VISIBLE);
//                } else {
//                    viewHolderHelper.setText(R.id.tv_charter_status, R.string.waitConfing);
//                    viewHolderHelper.setVisibility(R.id.tv_rightbtn2, View.GONE);
//                }
//                viewHolderHelper.setText(R.id.tv_leftbtn, R.string.callUp);
//                viewHolderHelper.setText(R.id.tv_rightbtn, R.string.sendPrivateChat);
//
//                break;
//            case NumericConstants.Completed://待评价
//                viewHolderHelper.setVisibility(R.id.ll_btns, View.VISIBLE);
//                viewHolderHelper.setVisibility(R.id.tv_leftbtn, View.GONE);
//                viewHolderHelper.setVisibility(R.id.tv_rightbtn, View.VISIBLE);
//                viewHolderHelper.setVisibility(R.id.tv_rightbtn2, View.GONE);
//                if (StringUtils.toInt(listBean.getUser_order_status(), 0) == 0) {//未评价
//                    viewHolderHelper.setText(R.id.tv_charter_status, R.string.evaluate);
//                    viewHolderHelper.setText(R.id.tv_rightbtn, R.string.toAppraise);
//                } else {
//                    viewHolderHelper.setText(R.id.tv_charter_status, R.string.guideEvaluate);
//                    viewHolderHelper.setText(R.id.tv_rightbtn, R.string.seeEvaluation);
//                }
//                break;
//            case NumericConstants.CompletedInDeatil://已完成
//                viewHolderHelper.setText(R.id.tv_charter_status, R.string.completed);
//                viewHolderHelper.setVisibility(R.id.ll_btns, View.VISIBLE);
//                viewHolderHelper.setVisibility(R.id.tv_leftbtn, View.GONE);
//                viewHolderHelper.setVisibility(R.id.tv_rightbtn, View.VISIBLE);
//                viewHolderHelper.setVisibility(R.id.tv_rightbtn2, View.GONE);
//                viewHolderHelper.setText(R.id.tv_rightbtn, R.string.seeEvaluation);
//                break;
//            case NumericConstants.Close://已关闭
//                viewHolderHelper.setText(R.id.tv_charter_status, R.string.closed);
//                viewHolderHelper.setVisibility(R.id.ll_btns, View.VISIBLE);
//                viewHolderHelper.setVisibility(R.id.tv_leftbtn, View.VISIBLE);
//                viewHolderHelper.setVisibility(R.id.tv_rightbtn, View.GONE);
//                viewHolderHelper.setVisibility(R.id.tv_rightbtn2, View.GONE);
//                viewHolderHelper.setText(R.id.tv_leftbtn, R.string.delete);
//                break;
//            default:
//                viewHolderHelper.setVisibility(R.id.ll_btns, View.GONE);
//                break;
//
//        }
//
//        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getAvatar(), (ImageView) viewHolderHelper.getView(R.id.iv_goodsicon), R.mipmap.placeholderfigure1);
//        viewHolderHelper.setText(R.id.tv_charter_title, listBean.getTitle());
//        viewHolderHelper.setText(R.id.tv_charter_datetime, listBean.getCreate_at());
//        if (TextUtils.isEmpty(listBean.getTotal_price())) {
//            viewHolderHelper.setText(R.id.tv_charter_orderprice, context.getResources().getString(R.string.moneySign) + "0.00");
//        } else {
//            viewHolderHelper.setText(R.id.tv_charter_orderprice, listBean.getTotal_price_fmt());
//        }
//        if (TextUtils.isEmpty(listBean.getReal_price())) {
//            viewHolderHelper.setText(R.id.tv_charter_paymoney, context.getResources().getString(R.string.moneySign) + "0.00");
//        } else {
//            viewHolderHelper.setText(R.id.tv_charter_paymoney, listBean.getReal_price_fmt());
//        }
    }
}