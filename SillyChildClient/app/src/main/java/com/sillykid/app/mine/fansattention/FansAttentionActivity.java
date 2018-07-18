package com.sillykid.app.mine.fansattention;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.sillykid.app.R;
import com.sillykid.app.mine.myrelease.collectiondynamic.CollectionDynamicFragment;
import com.sillykid.app.mine.myrelease.collectionstrate.CollectionStrateFragment;
import com.sillykid.app.mine.myrelease.dynamicstate.DynamicStateFragment;
import com.sillykid.app.mine.myrelease.strate.StrateFragment;


/**
 * Created by Administrator on 2017/11/20.
 */

public class FansAttentionActivity extends BaseActivity {

    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;

    @BindView(id = R.id.ll_fs, click = true)
    private LinearLayout ll_fs;
    @BindView(id = R.id.tv_fs)
    private TextView tv_fs;
    @BindView(id = R.id.v_fs)
    private View v_fs;

    @BindView(id = R.id.ll_gz, click = true)
    private LinearLayout ll_gz;
    @BindView(id = R.id.tv_gz)
    private TextView tv_gz;
    @BindView(id = R.id.v_gz)
    private View v_gz;

    @BindView(id = R.id.fl)
    private FrameLayout fl;
    private FansFragment fansFragment;
    private AttentionsFragment attentionsFragment;
    private int chageIcon=0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_fansattention);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        fansFragment=new FansFragment();
        attentionsFragment=new AttentionsFragment();
        chageIcon = aty.getIntent().getIntExtra("chageIcon", 0);
        cleanColors();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_fs:
                chageIcon=0;
                cleanColors();
                break;
            case R.id.ll_gz:
                chageIcon=1;
                cleanColors();
                break;
        }
    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors() {
        tv_fs.setTextColor(getResources().getColor(R.color.textColor));
        v_fs.setBackgroundResource(android.R.color.transparent);
        tv_gz.setTextColor(getResources().getColor(R.color.textColor));
        v_gz.setBackgroundResource(android.R.color.transparent);
        if (chageIcon == 0) {
            tv_fs.setTextColor(getResources().getColor(R.color.greenColors));
            v_fs.setBackgroundResource(R.color.greenColors);
            changeFragment(fansFragment);
            if (fansFragment.getFragmentJumpBetween()!=null)fansFragment.getFragmentJumpBetween().fragmentPosition();
        } else if (chageIcon == 1) {
            tv_gz.setTextColor(getResources().getColor(R.color.greenColors));
            v_gz.setBackgroundResource(R.color.greenColors);
            changeFragment(attentionsFragment);
            if (attentionsFragment.getFragmentJumpBetween()!=null)attentionsFragment.getFragmentJumpBetween().fragmentPosition();
        }
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.fl, targetFragment);
    }

    public int getChageIcon() {
        return chageIcon;
    }
}
