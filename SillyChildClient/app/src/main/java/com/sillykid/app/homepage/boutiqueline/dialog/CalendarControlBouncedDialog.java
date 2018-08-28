package com.sillykid.app.homepage.boutiqueline.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.common.cklibrary.common.ViewInject;
import com.kymjs.common.StringUtils;
import com.othershe.calendarview.bean.DateBean;
import com.othershe.calendarview.listener.OnPagerChangeListener;
import com.othershe.calendarview.listener.OnSingleChooseListener;
import com.othershe.calendarview.weiget.CalendarView;
import com.sillykid.app.R;
import com.sillykid.app.utils.DataUtil;


/**
 * 日历控件弹框
 * Created by Admin on 2017/9/15.
 */

public abstract class CalendarControlBouncedDialog extends BaseDialog implements View.OnClickListener {

    private TextView title;

    private CalendarView calendarView;

    private String dateStr = "";
    private String dateStr1 = "";

    public CalendarControlBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_calendarcontrol);
        ImageView img_lastMonth = (ImageView) findViewById(R.id.img_lastMonth);
        img_lastMonth.setOnClickListener(this);
        title = (TextView) findViewById(R.id.title);
        ImageView img_nextMonth = (ImageView) findViewById(R.id.img_nextMonth);
        img_nextMonth.setOnClickListener(this);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        TextView tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(this);
        calendarView = (CalendarView) findViewById(R.id.calendar);
        String start = DataUtil.formatData(System.currentTimeMillis() / 1000, "yyyy.MM");
        String end = DataUtil.formatData(System.currentTimeMillis() / 1000 + 3600 * 24 * 365, "yyyy.MM");
        String singleDate = DataUtil.formatData(System.currentTimeMillis() / 1000, "yyyy.MM.dd");
        String endDate = DataUtil.formatData(System.currentTimeMillis() / 1000 + 3600 * 24 * 365, "yyyy.MM.dd");
        calendarView.setStartEndDate(start, end)
                .setDisableStartEndDate(singleDate, endDate)
                .setSingleDate(singleDate)
                .setInitDate(start)
                .init();
        DateBean d = calendarView.getSingleDate();
        dateStr = d.getSolar()[0] + "-" + d.getSolar()[1] + "-" + d.getSolar()[2];
        title.setText(dateStr);
        dateStr1 = d.getSolar()[0] + "-" + d.getSolar()[1];
        //日期点击回调
        calendarView.setOnSingleChooseListener(new OnSingleChooseListener() {
            @Override
            public void onSingleChoose(View view, DateBean dateBean) {
                dateStr = dateBean.getSolar()[0] + "-" + dateBean.getSolar()[1] + "-" + dateBean.getSolar()[2];
            }
        });

        calendarView.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                title.setText(date[0] + "-" + date[1] + "-" + date[2]);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_lastMonth:
                if (title.getText().toString().startsWith(dateStr1)) {
                    return;
                }
                calendarView.lastMonth();
                break;
            case R.id.img_nextMonth:
                calendarView.nextMonth();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_confirm:
                timeListStr();
                break;
        }
    }

    /**
     * 时间集生成字符串
     */
    public void timeListStr() {
        if (StringUtils.isEmpty(dateStr)) {
            ViewInject.toast(getContext().getString(R.string.optionDate1));
            return;
        }
        timeList(dateStr);
        dismiss();
    }


    public abstract void timeList(String dateStr);

}
