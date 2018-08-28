package com.sillykid.app.homepage.airporttransportation.comments;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 评论
 * Created by Admin on 2018/8/21.
 */
public class CharterCommentsActivity extends BaseActivity {

    @BindView(id = R.id.tv_all, click = true)
    private TextView tv_all;

    @BindView(id = R.id.tv_havePictures, click = true)
    private TextView tv_havePictures;

    @BindView(id = R.id.tv_additionalReview, click = true)
    private TextView tv_additionalReview;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;
    private BaseFragment contentFragment2;

    private int chageIcon = 0;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_chartercomments);
    }

    @Override
    public void initData() {
        super.initData();
        contentFragment = new AllCommentsFragment();
        contentFragment1 = new HavePicturesCommentsFragment();
        contentFragment2 = new AddCommentsFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        if (chageIcon == 0) {
            cleanColors(0);
            changeFragment(contentFragment);
            chageIcon = 0;
        } else if (chageIcon == 1) {
            cleanColors(1);
            changeFragment(contentFragment1);
            chageIcon = 1;
        } else if (chageIcon == 2) {
            cleanColors(2);
            changeFragment(contentFragment2);
            chageIcon = 2;
        }
    }

    private void initTitle() {
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
//                SoftKeyboardUtils.packUpKeyboard(aty);
//                if (isRefresh == 1) {
//                    Intent intent = getIntent();
//                    intent.putExtra("favorited", favorited);
//                    setResult(RESULT_OK, intent);
//                }
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.comment), "", true, R.id.titlebar, simpleDelegate);
    }


    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_all:
                cleanColors(0);
                changeFragment(contentFragment);
                chageIcon = 0;
                break;
            case R.id.tv_havePictures:
                cleanColors(1);
                changeFragment(contentFragment1);
                chageIcon = 1;
                break;
            case R.id.tv_additionalReview:
                cleanColors(2);
                changeFragment(contentFragment2);
                chageIcon = 2;
            default:
                break;
        }
    }


    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_all.setTextColor(getResources().getColor(R.color.titletextcolors));
        tv_all.setBackground(getResources().getDrawable(R.drawable.shape_comments1));
        tv_havePictures.setTextColor(getResources().getColor(R.color.titletextcolors));
        tv_havePictures.setBackground(getResources().getDrawable(R.drawable.shape_comments1));
        tv_additionalReview.setTextColor(getResources().getColor(R.color.titletextcolors));
        tv_additionalReview.setBackground(getResources().getDrawable(R.drawable.shape_comments1));
        if (chageIcon == 0) {
            tv_all.setTextColor(getResources().getColor(R.color.whiteColors));
            tv_all.setBackground(getResources().getDrawable(R.drawable.shape_comments));
        } else if (chageIcon == 1) {
            tv_havePictures.setTextColor(getResources().getColor(R.color.whiteColors));
            tv_havePictures.setBackground(getResources().getDrawable(R.drawable.shape_comments));
        } else if (chageIcon == 2) {
            tv_additionalReview.setTextColor(getResources().getColor(R.color.whiteColors));
            tv_additionalReview.setBackground(getResources().getDrawable(R.drawable.shape_comments));
        } else {
            tv_all.setTextColor(getResources().getColor(R.color.whiteColors));
            tv_all.setBackground(getResources().getDrawable(R.drawable.shape_comments));
        }
    }

    public void setAll(String text) {
        tv_all.setText(getString(R.string.allAeservationNumber) + text);
    }

    public void setHavePictures(String text) {
        tv_havePictures.setText(getString(R.string.figure) + text);
    }

    public void setAdditionalReview(String text) {
        tv_additionalReview.setText(getString(R.string.noImages) + text);
    }

}
