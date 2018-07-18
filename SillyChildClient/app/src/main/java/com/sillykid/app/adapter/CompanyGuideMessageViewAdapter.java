package com.sillykid.app.adapter;

import android.content.Context;


import com.sillykid.app.R;


import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 司导消息 适配器
 * Created by Admin on 2017/8/15.
 */

public class CompanyGuideMessageViewAdapter extends BGAAdapterViewAdapter<String> {

    public CompanyGuideMessageViewAdapter(Context context) {
        super(context, R.layout.item_companyguidemassage);
    }


    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, String model) {
      //  Message lastMessage = model.getLastMessage();

//        if (lastMessage != null) {
//            if (lastMessage.getType() == Message.Type.TXT) {
//
//                helper.setText(R.id.tv_itemcompanyguidmessagecontent, SmileUtils.getSmiledText(mContext, getTextMessageTitle(lastMessage)));
//
//            } else if (lastMessage.getType() == Message.Type.VOICE) {
//                helper.setText(R.id.tv_itemcompanyguidmessagecontent, R.string.message_type_voice);
//            } else if (lastMessage.getType() == Message.Type.VIDEO) {
//                helper.setText(R.id.tv_itemcompanyguidmessagecontent, R.string.message_type_video);
//
//
//            } else if (lastMessage.getType() == Message.Type.LOCATION) {
//                helper.setText(R.id.tv_itemcompanyguidmessagecontent, R.string.message_type_location);
//            } else if (lastMessage.getType() == Message.Type.FILE) {
//                helper.setText(R.id.tv_itemcompanyguidmessagecontent, R.string.message_type_file);
//            }
//            helper.setText(R.id.tv_itemcompanyguidmessagetime, DateUtils.getTimestampString(new Date(lastMessage.getMsgTime())));
//
//        } else {
//            helper.setText(R.id.tv_itemcompanyguidmessagecontent, "");
//        }
//        OfficialAccount officialAccount = model.getOfficialAccount();
//        if (officialAccount == null) {
//            return;
//        }
//        helper.setText(R.id.tv_itemcompanyguidmessagename, officialAccount.getName());
//        String imgUrl = officialAccount.getImg();
//        if (imgUrl != null && imgUrl.startsWith("//")) {
//            imgUrl = "http:" + imgUrl;
//        }
//        ImageView iv_itemcompanyguidmessage = (ImageView) helper.getView(R.id.iv_itemcompanyguidmessage);
//        GlideApp.with(mContext).load(imgUrl).error(R.drawable.hd_default_avatar).transform(new GlideCircleTransform(mContext)).into(iv_itemcompanyguidmessage);
//    }


   // public String getTextMessageTitle(Message message) {
//        if (com.hyphenate.helpdesk.model.MessageHelper.getEvalRequest(message) != null) {
//            return mContext.getString(R.string.message_type_eval_request);
//        }
//        if (com.hyphenate.helpdesk.model.MessageHelper.getOrderInfo(message) != null) {
//            return mContext.getString(R.string.message_type_order_info);
//        }
//        if (com.hyphenate.helpdesk.model.MessageHelper.getVisitorTrack(message) != null) {
//            return mContext.getString(R.string.message_type_visitor_track);
//        }
//        if (checkFormChatRow(message)) {
//            return mContext.getString(R.string.message_type_form);
//        }
//        if (com.hyphenate.helpdesk.model.MessageHelper.isArticlesMessage(message)) {
//            return mContext.getString(R.string.message_type_articles);
//        }
//        if (com.hyphenate.helpdesk.model.MessageHelper.getRobotMenu(message) != null) {
//            return mContext.getString(R.string.message_type_robot);
//        }
//        if (com.hyphenate.helpdesk.model.MessageHelper.getToCustomServiceInfo(message) != null) {
//            return mContext.getString(R.string.message_type_tocs);
//        }

//        EMTextMessageBody body = (EMTextMessageBody) message.getBody();
//        return body.getMessage();
    }

}