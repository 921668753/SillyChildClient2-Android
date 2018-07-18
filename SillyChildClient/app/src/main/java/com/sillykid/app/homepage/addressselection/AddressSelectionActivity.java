package com.sillykid.app.homepage.addressselection;

import android.content.Intent;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.kymjs.common.Log;
import com.sillykid.app.R;

import static com.sillykid.app.constant.NumericConstants.STATUS;

/**
 * 地址选择
 * Created by Admin on 2017/9/5.
 */

public class AddressSelectionActivity extends BaseActivity {

    /**
     * 返回
     */
    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;
    @BindView(id = R.id.title)
    private TextView title;

    /**
     * 国内
     */
    @BindView(id = R.id.ll_inland, click = true)
    private LinearLayout ll_inland;

    @BindView(id = R.id.tv_inland)
    private TextView tv_inland;

    @BindView(id = R.id.v_inland1)
    private View v_inland1;

    /**
     * 海外
     */
    @BindView(id = R.id.ll_overseas, click = true)
    private LinearLayout ll_overseas;

    @BindView(id = R.id.tv_overseas)
    private TextView tv_overseas;

    @BindView(id = R.id.v_overseas1)
    private View v_overseas1;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;

    private int chageIcon = 0;
    private boolean showinland;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_addressselection);
    }


    @Override
    public void initData() {
        super.initData();
        showinland =getIntent().getBooleanExtra("showinland",true);
        if (showinland){
            ll_inland.setVisibility(View.VISIBLE);
            chageIcon=0;
            contentFragment = new InlandFragment();
        }else{
            chageIcon=1;
        }
        contentFragment1 = new OverseasFragment();
//        chageIcon = getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        cleanColors(chageIcon);
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }


    /**
     * Activity的启动模式变为singleTask
     * 调用onNewIntent(Intent intent)方法。
     * Fragment调用的时候，一定要在onResume方法中。
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int newChageIcon = intent.getIntExtra("newChageIcon", 2);
        Log.d("newChageIcon", newChageIcon + "");
        if (newChageIcon == 0) {
            setSimulateClick(ll_inland, 160, 100);
        } else if (newChageIcon == 1) {
            setSimulateClick(ll_overseas, 160, 100);
        }
    }

    /**
     * 模拟点击
     *
     * @param view
     * @param x
     * @param y
     */
    private void setSimulateClick(View view, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 1000;
        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        upEvent.recycle();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;

//            case R.id.ll_addressSearch:
//                Intent intent = new Intent();
//                intent.setClass(aty, AddressSearchActivity.class);
//                startActivityForResult(intent, STATUS);
//                overridePendingTransition(0, 0);
//                break;
            case R.id.ll_inland:
                chageIcon = 0;
                cleanColors(0);
                break;
            case R.id.ll_overseas:
                chageIcon = 1;
                cleanColors(1);
                break;
            default:
                break;
        }
    }


    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_inland.setTextColor(getResources().getColor(R.color.messagetopbarColors));
        v_inland1.setBackgroundResource(android.R.color.transparent);
        tv_overseas.setTextColor(getResources().getColor(R.color.messagetopbarColors));
        v_overseas1.setBackgroundResource(android.R.color.transparent);
        if (chageIcon == 0) {
            tv_inland.setTextColor(getResources().getColor(R.color.greenColors));
            v_inland1.setBackgroundResource(R.color.greenColors);
            changeFragment(contentFragment);
        } else if (chageIcon == 1) {
            tv_overseas.setTextColor(getResources().getColor(R.color.greenColors));
            v_overseas1.setBackgroundResource(R.color.greenColors);
            changeFragment(contentFragment1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STATUS && resultCode == RESULT_OK) {// 如果等于1
            String selectCity = data.getStringExtra("selectCity");
            int selectCityId = data.getIntExtra("selectCityId", 0);
            String selectCountry = data.getStringExtra("selectCountry");
            int selectCountryId = data.getIntExtra("selectCountryId", 0);
            android.util.Log.d("tag888", selectCity);
            Intent intent = new Intent();
            // 获取内容
            intent.putExtra("selectCity", selectCity);
            intent.putExtra("selectCityId", selectCityId);
            intent.putExtra("selectCountry", selectCountry);
            intent.putExtra("selectCountryId", selectCountryId);
            // 设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
        }
    }
}
