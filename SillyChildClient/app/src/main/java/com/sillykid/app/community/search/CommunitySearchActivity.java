package com.sillykid.app.community.search;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.sillykid.app.adapter.community.search.RecentSearchViewAdapter;
import com.sillykid.app.community.search.dialog.ClearSearchDialog;
import com.sillykid.app.entity.community.search.RecentSearchBean;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 社区搜索
 */
public class CommunitySearchActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    /**
     * 搜索
     */
    @BindView(id = R.id.et_search)
    private EditText et_search;

    @BindView(id = R.id.tv_cancel, click = true)
    private TextView tv_cancel;

    /**
     * 找人
     */
    @BindView(id = R.id.tv_lookForSomeone, click = true)
    private TextView tv_lookForSomeone;

    /**
     * 找文章
     */
    @BindView(id = R.id.tv_findArticl, click = true)
    private TextView tv_findArticl;

    /**
     * 最近搜索
     */
    @BindView(id = R.id.ll_recentSearch)
    private LinearLayout ll_recentSearch;

    @BindView(id = R.id.img_delete, click = true)
    private ImageView img_delete;

    @BindView(id = R.id.lv_recentSearch)
    private ListView lv_recentSearch;

    private List<RecentSearchBean.DataBean> recentSearchList = null;

    private RecentSearchViewAdapter recentSearchAdapter = null;

    private ClearSearchDialog clearSearchDialog = null;

    private int type = -1;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_communitysearch);
    }

    @Override
    public void initData() {
        super.initData();
        recentSearchList = new ArrayList<RecentSearchBean.DataBean>();
        recentSearchAdapter = new RecentSearchViewAdapter(this);
        type = getIntent().getIntExtra("type", -1);
        initClearSearchDialog();
    }

    /**
     * 弹框
     */
    public void initClearSearchDialog() {
        clearSearchDialog = new ClearSearchDialog(this, getString(R.string.clearSearch)) {
            @Override
            public void deleteCollectionDo(int addressId) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "recentSearchCommunityHistory", null);
                ll_recentSearch.setVisibility(View.GONE);
                lv_recentSearch.setVisibility(View.GONE);
            }
        };
    }

    @Override
    public void initWidget() {
        super.initWidget();
        if (type == 0) {
            tv_lookForSomeone.setVisibility(View.VISIBLE);
            tv_findArticl.setVisibility(View.GONE);
        } else if (type == 1) {
            tv_lookForSomeone.setVisibility(View.GONE);
            tv_findArticl.setVisibility(View.VISIBLE);
        } else {
            tv_lookForSomeone.setVisibility(View.VISIBLE);
            tv_findArticl.setVisibility(View.VISIBLE);
        }
        lv_recentSearch.setAdapter(recentSearchAdapter);
        lv_recentSearch.setOnItemClickListener(this);
        readRecentSearchHistory();
    }


    /**
     * 保存历史
     */
    private void saveRecentSearchHistory(String name, int type) {
        if (recentSearchList.size() > 0) {
            for (int i = 0; i < recentSearchList.size(); i++) {
                if (recentSearchList.get(i).getName().equals(name)) {
                    recentSearchList.remove(i);
                }
            }
        }
        RecentSearchBean.DataBean dataBean = new RecentSearchBean.DataBean();
        dataBean.setName(name);
        dataBean.setType(type);
        recentSearchList.add(dataBean);
        BaseResult<List<RecentSearchBean.DataBean>> baseResult = new BaseResult<>();
        baseResult.setMessage("");
        baseResult.setResult(1);
        Collections.reverse(recentSearchList);
        baseResult.setData(recentSearchList);
        PreferenceHelper.write(aty, StringConstants.FILENAME, "recentSearchCommunityHistory", JsonUtil.getInstance().obj2JsonString(baseResult));
    }

    /**
     * 读取历史
     */
    private void readRecentSearchHistory() {
        String recentSearch = PreferenceHelper.readString(aty, StringConstants.FILENAME, "recentSearchCommunityHistory", "");
        if (StringUtils.isEmpty(recentSearch)) {
            ll_recentSearch.setVisibility(View.GONE);
            lv_recentSearch.setVisibility(View.GONE);
            return;
        }
        ll_recentSearch.setVisibility(View.VISIBLE);
        lv_recentSearch.setVisibility(View.VISIBLE);
        RecentSearchBean recentSearchBean = (RecentSearchBean) JsonUtil.json2Obj(recentSearch, RecentSearchBean.class);
        if (recentSearchBean == null || recentSearchBean.getData() == null || recentSearchBean.getData().size() <= 0) {
            ll_recentSearch.setVisibility(View.GONE);
            lv_recentSearch.setVisibility(View.GONE);
            return;
        }
        recentSearchList.clear();
        recentSearchList.addAll(recentSearchBean.getData());
        recentSearchAdapter.clear();
        recentSearchAdapter.setData(recentSearchBean.getData());
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_cancel:
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
            case R.id.tv_lookForSomeone:
                if (StringUtils.isEmpty(et_search.getText().toString().trim())) {
                    ViewInject.toast(getString(R.string.enterSearchContent));
                    return;
                }
                SoftKeyboardUtils.packUpKeyboard(aty);
                saveRecentSearchHistory(et_search.getText().toString().trim(), 0);
                Intent beautyCareIntent = new Intent();
                if (type == 0) {
                    beautyCareIntent.putExtra("name", et_search.getText().toString().trim());
                    setResult(RESULT_OK, beautyCareIntent);
                } else {
                    beautyCareIntent.setClass(aty, SearchPeopleActivity.class);
                    beautyCareIntent.putExtra("name", et_search.getText().toString().trim());
                    showActivity(aty, beautyCareIntent);
                }
                finish();
                break;
            case R.id.tv_findArticl:
                if (StringUtils.isEmpty(et_search.getText().toString().trim())) {
                    ViewInject.toast(getString(R.string.enterSearchContent));
                    return;
                }
                SoftKeyboardUtils.packUpKeyboard(aty);
                saveRecentSearchHistory(et_search.getText().toString().trim(), 1);
                Intent beautyCareIntent1 = new Intent();
                if (type == 1) {
                    beautyCareIntent1.putExtra("name", et_search.getText().toString().trim());
                    setResult(RESULT_OK, beautyCareIntent1);
                } else {
                    beautyCareIntent1.setClass(aty, SearchArticleActivity.class);
                    beautyCareIntent1.putExtra("name", et_search.getText().toString().trim());
                    showActivity(aty, beautyCareIntent1);
                }
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent beautyCareIntent = new Intent();
        if (type == 0 || type == 1) {
            beautyCareIntent.putExtra("name", recentSearchAdapter.getItem(position).getName());
            setResult(RESULT_OK, beautyCareIntent);
        } else {
            if (recentSearchAdapter.getItem(position).getType() == 0) {
                beautyCareIntent.setClass(aty, SearchPeopleActivity.class);
            } else if (recentSearchAdapter.getItem(position).getType() == 1) {
                beautyCareIntent.setClass(aty, SearchArticleActivity.class);
            }
            beautyCareIntent.putExtra("name", recentSearchAdapter.getItem(position).getName());
            showActivity(aty, beautyCareIntent);
        }
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (clearSearchDialog != null) {
            clearSearchDialog.cancel();
        }
        clearSearchDialog = null;
        recentSearchAdapter.clear();
        recentSearchAdapter = null;
    }

}
