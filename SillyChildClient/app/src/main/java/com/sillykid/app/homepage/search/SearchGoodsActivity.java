package com.sillykid.app.homepage.search;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.entity.BaseResult;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.search.RecentSearchTagAdapter;
import com.sillykid.app.entity.homepage.search.RecentSearchBean;
import com.sillykid.app.homepage.goodslist.GoodsListActivity;
import com.sillykid.app.homepage.search.dialog.ClearSearchDialog;
import com.sillykid.app.utils.SoftKeyboardUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 搜索商品
 */
public class SearchGoodsActivity extends BaseActivity implements TagFlowLayout.OnTagClickListener {

    /**
     * 最近搜索
     */
    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    @BindView(id = R.id.et_search)
    private EditText et_search;

    @BindView(id = R.id.tv_search, click = true)
    private TextView tv_search;


    /**
     * 最近搜索
     */
    @BindView(id = R.id.ll_recentSearch)
    private LinearLayout ll_recentSearch;

    @BindView(id = R.id.tv_recentSearch)
    private TextView tv_recentSearch;

    @BindView(id = R.id.tfl_recentSearch)
    private TagFlowLayout tfl_recentSearch;


    @BindView(id = R.id.img_delete, click = true)
    private ImageView img_delete;

    private List<RecentSearchBean.DataBean> recentSearchList = null;

    private RecentSearchTagAdapter recentSearchTagAdapter = null;

    /**
     * 搜索发现
     */
    @BindView(id = R.id.tfl_searchFound)
    private TagFlowLayout tfl_searchFound;

    private ClearSearchDialog clearSearchDialog = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_searchgoods);
    }

    @Override
    public void initData() {
        super.initData();
        recentSearchList = new ArrayList<RecentSearchBean.DataBean>();
        recentSearchTagAdapter = new RecentSearchTagAdapter(this, recentSearchList);
        initClearSearchDialog();
    }

    /**
     * 弹框
     */
    public void initClearSearchDialog() {
        clearSearchDialog = new ClearSearchDialog(this, getString(R.string.clearSearch)) {
            @Override
            public void deleteCollectionDo(int addressId) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "recentSearchHistory", null);
                ll_recentSearch.setVisibility(View.GONE);
                tfl_recentSearch.setVisibility(View.GONE);
            }
        };
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tfl_recentSearch.setAdapter(recentSearchTagAdapter);
        tfl_recentSearch.setOnTagClickListener(this);
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (StringUtils.isEmpty(textView.getText().toString().trim())) {
                        ViewInject.toast(getString(R.string.enterSearching));
                        handled = true;
                        return handled;
                    }
                    SoftKeyboardUtils.packUpKeyboard(aty);
                    saveRecentSearchHistory(textView.getText().toString().trim());
                    Intent beautyCareIntent = new Intent();
                    if (getIntent().getIntExtra("tag", 0) == 1) {
                        beautyCareIntent.putExtra("keyword", textView.getText().toString().trim());
                        setResult(RESULT_OK, beautyCareIntent);
                    } else {
                        beautyCareIntent.setClass(aty, GoodsListActivity.class);
                        beautyCareIntent.putExtra("keyword", textView.getText().toString().trim());
                        showActivity(aty, beautyCareIntent);
                    }
                    finish();
                    handled = true;
                }
                return handled;
            }
        });
        readRecentSearchHistory();
    }


    /**
     * 保存历史
     */
    private void saveRecentSearchHistory(String name) {
        if (recentSearchList.size() > 0) {
            for (int i = 0; i < recentSearchList.size(); i++) {
                if (recentSearchList.get(i).getName().equals(name)) {
                    recentSearchList.remove(i);
                }
            }
        }
        RecentSearchBean.DataBean dataBean = new RecentSearchBean.DataBean();
        dataBean.setName(name);
        recentSearchList.add(dataBean);
        BaseResult<List<RecentSearchBean.DataBean>> baseResult = new BaseResult<>();
        baseResult.setMessage("");
        baseResult.setResult(1);
        Collections.reverse(recentSearchList);
        baseResult.setData(recentSearchList);
        PreferenceHelper.write(aty, StringConstants.FILENAME, "recentSearchHistory", JsonUtil.getInstance().obj2JsonString(baseResult));
    }

    /**
     * 读取历史
     */
    private void readRecentSearchHistory() {
        String recentSearch = PreferenceHelper.readString(aty, StringConstants.FILENAME, "recentSearchHistory", "");
        if (StringUtils.isEmpty(recentSearch)) {
            ll_recentSearch.setVisibility(View.GONE);
            tfl_recentSearch.setVisibility(View.GONE);
            return;
        }
        ll_recentSearch.setVisibility(View.VISIBLE);
        tfl_recentSearch.setVisibility(View.VISIBLE);
        RecentSearchBean recentSearchBean = (RecentSearchBean) JsonUtil.json2Obj(recentSearch, RecentSearchBean.class);
        if (recentSearchBean == null || recentSearchBean.getData() == null || recentSearchBean.getData().size() <= 0) {
            ll_recentSearch.setVisibility(View.GONE);
            tfl_recentSearch.setVisibility(View.GONE);
            return;
        }
        recentSearchTagAdapter.clear();
        recentSearchTagAdapter.setData(recentSearchBean.getData());
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_delete:
                if (clearSearchDialog == null) {
                    initClearSearchDialog();
                }
                if (clearSearchDialog != null && !clearSearchDialog.isShowing()) {
                    clearSearchDialog.show();
                }
                break;
            case R.id.tv_search:
                if (StringUtils.isEmpty(et_search.getText().toString().trim())) {
                    ViewInject.toast(getString(R.string.enterSearching));
                    return;
                }
                saveRecentSearchHistory(et_search.getText().toString().trim());
                Intent beautyCareIntent = new Intent();
                if (getIntent().getIntExtra("tag", 0) == 1) {
                    beautyCareIntent.putExtra("keyword", et_search.getText().toString().trim());
                    setResult(RESULT_OK, beautyCareIntent);
                } else {
                    beautyCareIntent.setClass(aty, GoodsListActivity.class);
                    beautyCareIntent.putExtra("keyword", et_search.getText().toString().trim());
                    showActivity(aty, beautyCareIntent);
                }
                finish();
                break;
        }
    }

    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        if (parent.getId() == R.id.tfl_recentSearch) {
            Intent beautyCareIntent = new Intent();
            if (getIntent().getIntExtra("tag", 0) == 1) {
                beautyCareIntent.putExtra("keyword", recentSearchTagAdapter.getItem(position).getName());
                setResult(RESULT_OK, beautyCareIntent);
            } else {
                beautyCareIntent.setClass(aty, GoodsListActivity.class);
                beautyCareIntent.putExtra("keyword", recentSearchTagAdapter.getItem(position).getName());
                showActivity(aty, beautyCareIntent);
            }
            finish();
        } else if (parent.getId() == R.id.tfl_searchFound) {

        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (clearSearchDialog != null) {
            clearSearchDialog.cancel();
        }
        clearSearchDialog = null;
        recentSearchTagAdapter.clear();
        recentSearchTagAdapter = null;
    }
}
