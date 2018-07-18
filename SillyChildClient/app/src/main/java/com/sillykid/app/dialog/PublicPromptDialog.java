package com.sillykid.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.icu.util.MeasureUnit;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sillykid.app.R;

/**
 * 通用提示框（可配置一句话的提示语，和按钮提示字样）
 * Created by Administrator on 2017/9/5.
 */

public abstract class PublicPromptDialog extends Dialog implements View.OnClickListener{

    private String content,btncontent;
    private TextView tv_cancel,tv_call,tv_content;
    private TextView tv_contentsmall,tv_tsword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_publicprompt);
        initView();

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width= WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);

    }

    private void initView(){
        tv_tsword=(TextView)findViewById(R.id.tv_tsword);
        tv_cancel=(TextView)findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_call=(TextView)findViewById(R.id.tv_call);
        tv_call.setOnClickListener(this);
        if (TextUtils.isEmpty(btncontent)){
            tv_call.setText("");
        }else{
            tv_call.setText(btncontent);
        }
        tv_content=(TextView)findViewById(R.id.tv_content);
        if (TextUtils.isEmpty(content)){
            tv_content.setText("");
        }else{
            tv_content.setText(content);
        }
        tv_contentsmall=(TextView)findViewById(R.id.tv_contentsmall);
    }

    public PublicPromptDialog(Context context) {
        super(context, R.style.MyDialog);
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(true);
    }

    public PublicPromptDialog(Context context,String content,String btncontent) {
        super(context, R.style.MyDialog);
        this.content=content;
        this.btncontent=btncontent;
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_call:
                dismiss();
                doAction();
                break;
        }
    }

    public abstract void doAction();

    public void setContent(String content){
        if (tv_content!=null){
            if (TextUtils.isEmpty(content)){
                tv_content.setText("");
            }else{
                tv_content.setText(content);
            }
        }
    }

    public void setBtnContent(String btncontent){
        if (tv_call!=null){
            if (TextUtils.isEmpty(btncontent)){
                tv_call.setText("");
            }else{
                tv_call.setText(btncontent);
            }
        }
    }

    public void setContentSmallShow(boolean isshow){
        if (tv_contentsmall!=null&&isshow&&tv_contentsmall.getVisibility()== View.GONE){
            tv_contentsmall.setVisibility(View.VISIBLE);
        }else if(tv_contentsmall!=null&&!isshow){
            tv_contentsmall.setVisibility(View.GONE);
        }
    }

    public void setTitle(int contentcolor,int contentsize){
        if (tv_tsword!=null&&tv_content!=null){
            tv_tsword.setVisibility(View.VISIBLE);
            tv_content.setTextColor(contentcolor);
            tv_content.setTextSize(TypedValue.COMPLEX_UNIT_SP,contentsize);

        }
    }

}
