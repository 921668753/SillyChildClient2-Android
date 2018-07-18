package com.sillykid.app.homepage.addressselection.addresssearch;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;

import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.entity.BaseResult;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.AddressSearchViewAdapter;
import com.sillykid.app.entity.IndexCityBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 地址搜索
 * Created by Admin on 2017/9/5.
 */

public class AddressSearchActivity extends BaseActivity implements TextWatcher, AddressSearchContract.View, AdapterView.OnItemClickListener {

    /**
     * 返回
     */
    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    /**
     * 输入框
     */
    @BindView(id = R.id.et_cityName)
    private EditText et_cityName;

    /**
     * 取消
     */
    @BindView(id = R.id.img_quxiao, click = true)
    private ImageView img_quxiao;

    /**
     * 搜索历史
     */
    @BindView(id = R.id.tv_searchHistory, click = true)
    private TextView tv_searchHistory;

    /**
     * 清除搜索历史
     */
    @BindView(id = R.id.tv_clearSearchRecord, click = true)
    private TextView tv_clearSearchRecord;

    /**
     * 分割线
     */
    @BindView(id = R.id.tv_divider)
    private TextView tv_divider;

    @BindView(id = R.id.tv_divider1)
    private TextView tv_divider1;

    @BindView(id = R.id.lv_addressSearch)
    private ChildListView lv_addressSearch;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;


    private AddressSearchViewAdapter addressSearchViewAdapter;
    private List<IndexCityBean.ResultBean> historyCityList;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_addresssearch);
    }


    @Override
    public void initData() {
        super.initData();
        historyCityList = new ArrayList<>();
        mPresenter = new AddressSearchPresenter(this);
        addressSearchViewAdapter = new AddressSearchViewAdapter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        lv_addressSearch.setAdapter(addressSearchViewAdapter);
        lv_addressSearch.setOnItemClickListener(this);
        et_cityName.addTextChangedListener(this);
        readHistory();
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_quxiao:
                et_cityName.setText("");
                img_quxiao.setVisibility(View.GONE);
                break;
            case R.id.tv_clearSearchRecord:
                PreferenceHelper.write(aty, StringConstants.FILENAME, "searchHistory", null);
                tv_searchHistory.setVisibility(View.GONE);
                lv_addressSearch.setVisibility(View.GONE);
                tv_clearSearchRecord.setVisibility(View.GONE);
                tv_divider.setVisibility(View.GONE);
                tv_divider1.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().trim().length() == 0) {
            readHistory();
            return;
        }
        ((AddressSearchContract.Presenter) mPresenter).getSearchCity(s.toString().trim());
        img_quxiao.setVisibility(View.VISIBLE);
        lv_addressSearch.setVisibility(View.VISIBLE);
        tv_divider.setVisibility(View.GONE);
        tv_divider1.setVisibility(View.GONE);
        tv_searchHistory.setVisibility(View.GONE);
        tv_clearSearchRecord.setVisibility(View.GONE);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void setPresenter(AddressSearchContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        ll_commonError.setVisibility(View.GONE);
        if (flag == 0) {
            IndexCityBean indexCityBean = (IndexCityBean) JsonUtil.json2Obj(success, IndexCityBean.class);
            List<IndexCityBean.ResultBean> indexCityList = indexCityBean.getData();
            if (!(indexCityList != null && indexCityList.size() > 0)) {
                errorMsg(aty.getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            addressSearchViewAdapter.clear();
            addressSearchViewAdapter.addNewData(indexCityList);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setText(msg);
        if (msg.equals(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.nonetworkxxx);
        } else {
            img_err.setImageResource(R.mipmap.nocontentxxx);
        }
        dismissLoadingDialog();
        //  ViewInject.toast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        historyCityList.clear();
        historyCityList = null;
        addressSearchViewAdapter.clear();
        addressSearchViewAdapter = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        IndexCityBean.ResultBean resultBean = addressSearchViewAdapter.getItem(i);
        saveHistory(resultBean);
        //    PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", resultBean.getName());
        Intent intent = new Intent();
        // 获取内容
        intent.putExtra("selectCity", resultBean.getName());
        intent.putExtra("selectCityId", resultBean.getId());
        intent.putExtra("selectCountryId", resultBean.getCountry_id());
        // 设置结果 结果码，一个数据
        setResult(RESULT_OK, intent);
        // 结束该activity 结束之后，前面的activity才可以处理结果
        aty.finish();
    }


    /**
     * 保存历史
     */
    private void saveHistory(IndexCityBean.ResultBean resultBean) {
        if (historyCityList.size() > 0) {
            for (int i = 0; i < historyCityList.size(); i++) {
                if (historyCityList.get(i).getName().equals(resultBean.getName())) {
                    historyCityList.remove(i);
                }
            }
        }
        historyCityList.add(resultBean);
        BaseResult<List<IndexCityBean.ResultBean>> baseResult = new BaseResult<>();
        baseResult.setMessage("");
        baseResult.setResult(1);
        Collections.reverse(historyCityList);
        baseResult.setData(historyCityList);
        PreferenceHelper.write(aty, StringConstants.FILENAME, "searchHistory", JsonUtil.getInstance().obj2JsonString(baseResult));
    }

    /**
     * 读取历史
     */
    private void readHistory() {
        String overseasHistory = PreferenceHelper.readString(aty, StringConstants.FILENAME, "searchHistory", null);
        if (StringUtils.isEmpty(overseasHistory)) {
            tv_searchHistory.setVisibility(View.GONE);
            lv_addressSearch.setVisibility(View.GONE);
            tv_clearSearchRecord.setVisibility(View.GONE);
            tv_divider.setVisibility(View.GONE);
            tv_divider1.setVisibility(View.GONE);
            return;
        }
        tv_searchHistory.setVisibility(View.VISIBLE);
        lv_addressSearch.setVisibility(View.VISIBLE);
        tv_clearSearchRecord.setVisibility(View.VISIBLE);
        tv_divider.setVisibility(View.VISIBLE);
        tv_divider1.setVisibility(View.VISIBLE);
        IndexCityBean indexCityBean = (IndexCityBean) JsonUtil.json2Obj(overseasHistory, IndexCityBean.class);
        historyCityList = indexCityBean.getData();
        addressSearchViewAdapter.clear();
        addressSearchViewAdapter.addNewData(historyCityList);
    }
}
