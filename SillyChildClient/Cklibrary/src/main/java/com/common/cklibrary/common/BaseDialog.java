package com.common.cklibrary.common;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;

import com.common.cklibrary.R;
import com.kymjs.rxvolley.RxVolley;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/9/7.
 */

public abstract class BaseDialog extends Dialog {


    public Context mContext;


    public BaseDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }


    @Override
    public void dismiss() {
        RxVolley.getRequestQueue().cancelAll(mContext.getClass().getName());
        super.dismiss();
    }
}
