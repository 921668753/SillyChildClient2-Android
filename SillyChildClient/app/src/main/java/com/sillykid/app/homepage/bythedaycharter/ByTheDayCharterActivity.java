package com.sillykid.app.homepage.bythedaycharter;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 按天包车
 */
public class ByTheDayCharterActivity extends BaseActivity {

    @BindView(id = R.id.img_byTheDayCharter)
    private ImageView img_byTheDayCharter;

    @BindView(id = R.id.tv_travelTime, click = true)
    private TextView tv_travelTime;

    @BindView(id = R.id.et_journeyBegins)
    private EditText et_journeyBegins;

    @BindView(id = R.id.et_journeyEnds)
    private EditText et_journeyEnds;

    @BindView(id = R.id.et_contact)
    private EditText et_contact;

    @BindView(id = R.id.et_contactWay)
    private EditText et_contactWay;

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

    @BindView(id = R.id.et_remark)
    private EditText et_remark;

    @BindView(id = R.id.tv_submitOrder, click = true)
    private TextView tv_submitOrder;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_bythedaycharter);
    }


    @Override
    public void initData() {
        super.initData();
        initOptions();
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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.fillYourInformation), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_travelTime:

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

        }
    }
}
