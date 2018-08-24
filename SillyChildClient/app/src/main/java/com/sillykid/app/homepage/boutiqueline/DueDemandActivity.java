package com.sillykid.app.homepage.boutiqueline;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.myview.ChildListView;
import com.sillykid.app.R;
import com.sillykid.app.utils.DataUtil;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 预定需求
 */
public class DueDemandActivity extends BaseActivity {

    @BindView(id = R.id.ll_adult, click = true)
    private LinearLayout ll_adult;
    @BindView(id = R.id.tv_adult)
    private TextView tv_adult;
    private OptionsPickerView adultOptions = null;

    @BindView(id = R.id.ll_children, click = true)
    private LinearLayout ll_children;
    @BindView(id = R.id.tv_children)
    private TextView tv_children;
    private OptionsPickerView childrenOptions = null;

    @BindView(id = R.id.ll_luggage, click = true)
    private LinearLayout ll_luggage;
    @BindView(id = R.id.tv_luggage)
    private TextView tv_luggage;
    private OptionsPickerView luggageOptions = null;

    @BindView(id = R.id.tv_travelTime, click = true)
    private TextView tv_travelTime;
    private TimePickerView pvCustomTime = null;
    private long timeDeparture = 0;


    @BindView(id = R.id.clv_dueDemand)
    private ChildListView clv_dueDemand;

    @BindView(id = R.id.et_contact)
    private TextView et_contact;

    @BindView(id = R.id.et_contactWay)
    private TextView et_contactWay;


    @BindView(id = R.id.et_remark)
    private TextView et_remark;

    @BindView(id = R.id.tv_submitOrder, click = true)
    private TextView tv_submitOrder;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_duedemand);
    }

    @Override
    public void initData() {
        super.initData();

        initCustomTimePicker();
        initOptions();
    }

    /**
     * 选择时间的控件
     */
    private void initCustomTimePicker() {

        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar birthdaycalendar = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(birthdaycalendar.get(Calendar.YEAR), birthdaycalendar.get(Calendar.MONTH), birthdaycalendar.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();
        endDate.set(birthdaycalendar.get(Calendar.YEAR) + 99, birthdaycalendar.get(Calendar.MONTH), birthdaycalendar.get(Calendar.DAY_OF_MONTH));
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                timeDeparture = date.getTime() / 1000;
                ((TextView) v).setText(DataUtil.formatData(date.getTime() / 1000, "yyyy-MM-dd HH:mm"));
            }
        })
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final Button btnSubmit = (Button) v.findViewById(R.id.btnSubmit);
                        Button btnCancel = (Button) v.findViewById(R.id.btnCancel);
                        btnSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFFd5d5d5)
                .build();
    }


    private void initOptions() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1 + "");
        }
        adultOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                ((TextView) v).setText(list.get(options1));
            }
        }).build();
        adultOptions.setPicker(list);
        childrenOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                ((TextView) v).setText(list.get(options1));
            }
        }).build();
        childrenOptions.setPicker(list);
        luggageOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                ((TextView) v).setText(list.get(options1));
            }
        }).build();
        luggageOptions.setPicker(list);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.route2), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_flightArrivalTime:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvCustomTime.show(tv_travelTime);
                break;
            case R.id.ll_adult:
                SoftKeyboardUtils.packUpKeyboard(this);
                adultOptions.show(tv_adult);
                break;
            case R.id.ll_children:
                SoftKeyboardUtils.packUpKeyboard(this);
                childrenOptions.show(tv_children);
                break;
            case R.id.ll_luggage:
                SoftKeyboardUtils.packUpKeyboard(this);
                luggageOptions.show(tv_luggage);
                break;
            case R.id.tv_submitOrder:


                break;
        }
    }


}
