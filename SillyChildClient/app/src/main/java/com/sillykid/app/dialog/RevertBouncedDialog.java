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
 * 商品评价回复
 * Created by Admin on 2017/7/23.
 */

public class RevertBouncedDialog extends Dialog implements View.OnClickListener {
    private String destination;
    private Context context;
    private TextView tv_baidu;
    private TextView tv_gaode;
    private TextView tv_cancel;

    public RevertBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_revertbounced);
        initView();

        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

//            case R.id.tv_baidu:
//
//                break;
//            case R.id.tv_gaode:
//                dismiss();
//                //高德
//                initGaoDeMap("", destination);
//                break;
//            case R.id.tv_cancel:
//                dismiss();
//                break;
        }
    }
}
