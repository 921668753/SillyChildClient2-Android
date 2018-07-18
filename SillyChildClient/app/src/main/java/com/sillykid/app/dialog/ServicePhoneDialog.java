package com.sillykid.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sillykid.app.R;

/**
 * 拨打电话无权限提示
 * Created by Administrator on 2017/9/5.
 */

public abstract class ServicePhoneDialog extends Dialog implements View.OnClickListener{

    private TextView tv_cancel,tv_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_servicephone);
        initView();

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width= WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);

    }

    private void initView(){
        tv_cancel=(TextView)findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_call=(TextView)findViewById(R.id.tv_call);
        tv_call.setOnClickListener(this);
    }

    public ServicePhoneDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_call:
                dismiss();
                callDo();
                break;
        }
    }

    public abstract void callDo();

}
