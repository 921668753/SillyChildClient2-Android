package com.sillykid.app.homepage.message.interactivemessage.imuitl;


import android.app.ActivityManager;
import android.content.Context;

import com.common.cklibrary.common.StringConstants;
import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.CSCustomServiceInfo;

/**
 * 获取Token
 */
public class RongIMUtil {

    // 融云文档提供:
    // 可搜索:关于 IMkit 中报“Rong SDK should not be initialized at subprocess”的解决方法
    public static String getCurProcessName(final Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    public static void connectRongIM(final Context context) {
        // 获取融云的Token
        String imToken = UserUtil.getResTokenInfo(context);
        if (!StringUtils.isEmpty(imToken)) {
            connect(imToken);
        }
    }

    /**
     * 首先需要构造使用客服者的用户信息
     */
    public static CSCustomServiceInfo getCSCustomServiceInfo(final Context context) {
        //首先需要构造使用客服者的用户信息
        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
        String nick_name = PreferenceHelper.readString(context, StringConstants.FILENAME, "nick_name", "");
        String mobile = PreferenceHelper.readString(context, StringConstants.FILENAME, "mobile", "");
        String city = PreferenceHelper.readString(context, StringConstants.FILENAME, "city", "");
        String address = PreferenceHelper.readString(context, StringConstants.FILENAME, "address", "");
        String province = PreferenceHelper.readString(context, StringConstants.FILENAME, "province", "");
        return csBuilder.nickName(nick_name).name(nick_name).mobileNo(mobile).city(city).province(province).address(address).build();
    }


    private static void connect(final String rongYunToken) {

        // 连接融云服务器。
        try {
            RongIM.connect(rongYunToken, new RongIMClient.ConnectCallback() {

                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(final String s) {
                    // 此处处理连接成功。
                    Log.d("RongYun", "Login successfully.");
                    RongCloudEvent.getInstance().setOtherListener();
                }

                @Override
                public void onError(final RongIMClient.ErrorCode errorCode) {
                    // 此处处理连接错误。
                    Log.d("RongYun", "Login failed.");
                }
            });
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
