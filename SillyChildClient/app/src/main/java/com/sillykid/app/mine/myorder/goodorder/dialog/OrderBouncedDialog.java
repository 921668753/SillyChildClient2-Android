package com.sillykid.app.mine.myorder.goodorder.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sillykid.app.R;


/**
 * 订单弹框
 */
public abstract class OrderBouncedDialog extends Dialog implements View.OnClickListener {

    private int id = 0;

    private String content = "";

    private Context context;

    private TextView tv_content;

    private int flag = 0;

    public OrderBouncedDialog(Context context, String content) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_clearcache);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content.setText(content);
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_determine:
                dismiss();
                toDialogDo(id, flag);
                break;
        }
    }

    public void setIdContentFlag(int id, String content, int flag) {
        this.id = id;
        this.flag = flag;
        this.content = content;
        tv_content.setText(content);
    }

    public abstract void toDialogDo(int id, int flag);

}
