package com.sillykid.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.ViewInject;
import com.kymjs.common.StringUtils;
import com.othershe.calendarview.bean.DateBean;
import com.othershe.calendarview.listener.OnPagerChangeListener;
import com.othershe.calendarview.listener.OnSingleChooseListener;
import com.othershe.calendarview.weiget.CalendarView;
import com.sillykid.app.R;
import com.sillykid.app.entity.CalendarControlDateBean;


/**
 * 日历控件弹框
 * Created by Admin on 2017/9/15.
 */

public abstract class CalendarControlBouncedDialog extends Dialog implements View.OnClickListener {


    private Context context;
    // private int numberDays;
    private ImageView img_lastMonth;

    private TextView title;

    private ImageView img_nextMonth;

    private TextView tv_cancel;

    private TextView tv_confirm;

    private CalendarView calendarView;

    //   private List<CalendarControlDateBean> timeList;

    private String dateStr = "";
    private String dateStr1 = "";

    public CalendarControlBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
        //   this.numberDays = numberDays;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_calendarcontrol);
        img_lastMonth = (ImageView) findViewById(R.id.img_lastMonth);
        img_lastMonth.setOnClickListener(this);
        title = (TextView) findViewById(R.id.title);
        img_nextMonth = (ImageView) findViewById(R.id.img_nextMonth);
        img_nextMonth.setOnClickListener(this);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(this);
        calendarView = (CalendarView) findViewById(R.id.calendar);
        calendarView.init();
        DateBean d = calendarView.getSingleDate();
        dateStr = d.getSolar()[0] + "-" + d.getSolar()[1] + "-" + d.getSolar()[2];
        title.setText(dateStr);
        dateStr1 = d.getSolar()[0] + "-" + d.getSolar()[1];
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        title.setText(format.format(new Date()));
//        //  sb.append("选中：" + d + "\n");
//        // chooseDate.setText(sb.toString());
        //       timeList = new ArrayList();
//        CalendarControlDateBean calendarControlDateBean = new CalendarControlDateBean();
//        calendarControlDateBean.setDateStr(format.format(new Date()));
//        calendarControlDateBean.setFlag(true);
//        calendarControlDateBean.setDateTime(new Date().getTime() / 1000);
//        timeList(calendarControlDateBean);
//        calendarView.setOnMonthItemChooseListener(new OnMonthItemChooseListener() {
//            @Override
//            public void onMonthItemChoose(View view, DateBean date, boolean flag) {
//                String d = null;
//                if (date.getSolar()[1] < 10) {
//                    d = date.getSolar()[0] + "-0" + date.getSolar()[1] + "-" + date.getSolar()[2];
//                } else {
//                    d = date.getSolar()[0] + "-" + date.getSolar()[1] + "-" + date.getSolar()[2];
//                }
//                //   calendarView.setLastChooseDate(date.getSolar()[2],false);
//                CalendarControlDateBean calendarControlDateBean = new CalendarControlDateBean();
//                calendarControlDateBean.setDateStr(d);
//                calendarControlDateBean.setFlag(flag);
//                calendarControlDateBean.setDateTime(DataUtil.getStringToDate(d, "yyyy-MM-dd") / 1000);
//                timeList(calendarControlDateBean);
//            }
//        });

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
     * 整理添加时间集
     */
    public void timeList(CalendarControlDateBean date) {
//        if (date.isFlag()) {
//            timeList.add(date);
//            return;
//        }
//        for (int i = 0; i < timeList.size(); i++) {
//            if (timeList.get(i).getDateStr() != null && date.getDateStr().equals(timeList.get(i).getDateStr()) && !date.isFlag()) {
//                timeList.remove(i);
//            }
//        }

        //   timeList = timeList1;
        // timeList1 = null;
    }

    /**
     * 时间集生成字符串
     */
    public void timeListStr() {

        if (StringUtils.isEmpty(dateStr)) {
            ViewInject.toast(getContext().getString(R.string.optionDate1));
            return;
        }

//        if (timeList != null && timeList.size() > numberDays) {
//            ViewInject.toast(context.getString(R.string.thanLineDays));
//            return;
//        }

//        Collections.sort(timeList, new SequencingUtils());
//        for (int i = 0; i < timeList.size(); i++) {
//            dateStr = dateStr + "/" + timeList.get(i).getDateStr();
//        }
//        dateStr = dateStr.substring(1);
        timeList(dateStr);
        dismiss();
    }


    public abstract void timeList(String dateStr);

}
