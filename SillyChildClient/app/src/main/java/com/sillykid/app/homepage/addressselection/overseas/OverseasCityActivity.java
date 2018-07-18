package com.sillykid.app.homepage.addressselection.overseas;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.IndexNewBar;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.sillykid.app.R;
import com.sillykid.app.adapter.addressselection.CommonAdapter;
import com.sillykid.app.adapter.addressselection.HeaderRecyclerAndFooterWrapperAdapter;
import com.sillykid.app.adapter.addressselection.InlandViewAdapter;
import com.sillykid.app.adapter.addressselection.OnItemClickListener;
import com.sillykid.app.adapter.addressselection.ViewHolder;
import com.sillykid.app.entity.IndexCityBean;
import com.sillykid.app.entity.InlandBean;
import com.sillykid.app.entity.InlandHotCityBean;
import com.sillykid.app.utils.decoration.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import static com.sillykid.app.constant.NumericConstants.STATUS;

/**
 * 国外城市列表
 * Created by Admin on 2017/9/25.
 */

public class OverseasCityActivity extends BaseActivity implements OverseasCityContract.View, OnItemClickListener {

    /**
     * 返回
     */
    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    /**
     * 地址搜索
     */
//    @BindView(id = R.id.ll_addressSearch, click = true)
//    private LinearLayout ll_addressSearch;

    @BindView(id = R.id.rv)
    private RecyclerView mRv;

    @BindView(id = R.id.indexBar)
    private IndexNewBar mIndexBar;
    private InlandViewAdapter mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private LinearLayoutManager mManager;

    private SuspensionDecoration mDecoration;

    //设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    //头部数据源
    private List<InlandHotCityBean> mHeaderDatas;
    //主体部分数据源（城市数据）
    private List<InlandBean.ResultBean> mBodyDatas;

    private DividerItemDecoration dividerItemDecoration;

    private List<IndexCityBean.ResultBean> historyCityList;

    TextView textView1;
    private String selectCountry;
    private int selectCountryId;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_overseascity);
    }


    @Override
    public void initData() {
        super.initData();
        showLoadingDialog(getString(R.string.dataLoad));
        selectCountry = getIntent().getStringExtra("selectCountry");
        selectCountryId = getIntent().getIntExtra("selectCountryId", 0);
        historyCityList = new ArrayList<>();
        mSourceDatas = new ArrayList<>();
        mBodyDatas = new ArrayList<InlandBean.ResultBean>();
        mHeaderDatas = new ArrayList<InlandHotCityBean>();
        mPresenter = new OverseasCityPresenter(this);
        mManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(mManager);
        List<InlandHotCityBean.ResultBean> hotCitys = new ArrayList<>();
        InlandHotCityBean hotCity = new InlandHotCityBean(hotCitys, "热门城市", "热门");
        mHeaderDatas.add(hotCity);
        mSourceDatas.addAll(mHeaderDatas);
        mAdapter = new InlandViewAdapter(aty, R.layout.item_selectcountry, mBodyDatas);
        mAdapter.setOnItemClickListener(this);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.inland_item_header:
                        final InlandHotCityBean meituanHeaderBean = (InlandHotCityBean) o;
                        //网格
                        RecyclerView recyclerView = holder.getView(R.id.rvCity);
                        recyclerView.setAdapter(
                                new CommonAdapter<InlandHotCityBean.ResultBean>(aty, R.layout.inland_item_header_item, meituanHeaderBean.getCityList()) {
                                    @Override
                                    public void convert(ViewHolder holder, final InlandHotCityBean.ResultBean cityName) {
                                        holder.setText(R.id.tvName, cityName.getName());
                                        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                        //        PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", cityName.getName());
//                                                saveHistory(cityName);
                                                Intent intent = new Intent();
                                                // 获取内容
                                                intent.putExtra("selectCity", cityName.getName());
                                                intent.putExtra("selectCityId", cityName.getId());
                                                intent.putExtra("selectCountry", selectCountry);
                                                intent.putExtra("selectCountryId", cityName.getCountry_id());
                                                // 设置结果 结果码，一个数据
                                                aty.setResult(RESULT_OK, intent);
                                                // 结束该activity 结束之后，前面的activity才可以处理结果
                                                aty.finish();
                                            }
                                        });
                                    }
                                });
                        recyclerView.setLayoutManager(new GridLayoutManager(aty, 3));
                        break;
                    case R.layout.inland_item_header_top:
                        TextView textView = holder.getView(R.id.tvCurrent);
                        textView.setText(getString(R.string.currentState));
                        textView1 = holder.getView(R.id.tv_country);
                        textView1.setText(selectCountry);
                        break;
                    default:
                        break;
                }
            }
        };
//        readHistory();
        mDecoration = new SuspensionDecoration(aty, mSourceDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()))
                .setColorTitleFont(aty.getResources().getColor(android.R.color.black))
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size());
        dividerItemDecoration = new DividerItemDecoration(aty, DividerItemDecoration.VERTICAL_LIST);
    }

    @Override
    public void initDataFromThread() {
        super.initDataFromThread();
        ((OverseasCityContract.Presenter) mPresenter).getAllCity(getIntent().getIntExtra("selectCountryId", 0));
    }

    @Override
    public void initWidget() {
        super.initWidget();
        dividerItemDecoration.setOrientation(0);
        mRv.setAdapter(mHeaderAdapter);
        mRv.addItemDecoration(mDecoration);
        mRv.addItemDecoration(dividerItemDecoration);
        mIndexBar.setmPressedShowTextView(null)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size());
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
        }
    }


//    /**
//     * 读取历史
//     */
//    private void readHistory() {
//        mHeaderAdapter.setHeaderView(0, R.layout.inland_item_header_top, new InlandTopHeaderBean(""));
//        mHeaderAdapter.setHeaderView(1, R.layout.inland_item_header, mHeaderDatas.get(0));
//        String overseasHistory = PreferenceHelper.readString(aty, StringConstants.FILENAME, "overseasHistory", null);
//        if (StringUtils.isEmpty(overseasHistory)) {
//            return;
//        }
//        IndexCityBean indexCityBean = (IndexCityBean) JsonUtil.json2Obj(overseasHistory, IndexCityBean.class);
//        historyCityList = indexCityBean.getResult();
//    }

//    /**
//     * 保存历史
//     */
//    private void saveHistory(InlandHotCityBean.ResultBean resultBean) {
//        resultBean.setCountry(selectCountry);
//        IndexCityBean.ResultBean resultBean1 = new IndexCityBean.ResultBean();
//        resultBean1.setName(resultBean.getName());
//        resultBean1.setId(resultBean.getId());
//        resultBean1.setCountry_id(resultBean.getCountry_id());
//        resultBean1.setCountry(resultBean.getCountry());
//        if (historyCityList.size() > 0) {
//            for (int i = 0; i < historyCityList.size(); i++) {
//                if (historyCityList.get(i).getName().equals(resultBean1.getName())) {
//                    historyCityList.remove(i);
//                }
//            }
//        }
//        historyCityList.add(resultBean1);
//        if (historyCityList.size() > 4) {
//            historyCityList.remove(0);
//        }
//        BaseResult<List<IndexCityBean.ResultBean>> baseResult = new BaseResult<>();
//        baseResult.setMsg("");
//        baseResult.setStatus(1);
//        Collections.reverse(historyCityList);
//        baseResult.setResult(historyCityList);
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "overseasHistory", JsonUtil.getInstance().obj2JsonString(baseResult));
//    }


    @Override
    public void setPresenter(OverseasCityContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            InlandBean inlandBean = (InlandBean) JsonUtil.json2Obj(success, InlandBean.class);
            //   inlandBean.getResult()
            if (!(inlandBean.getData() != null && inlandBean.getData().size() > 0)) {
                errorMsg(aty.getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            //模拟线上加载数据
            mBodyDatas.clear();
            mBodyDatas.addAll(inlandBean.getData());
            ((OverseasCityContract.Presenter) mPresenter).getChildHotCity(getIntent().getIntExtra("selectCountryId", 0));
        } else if (flag == 1) {
            InlandHotCityBean inlandHotCityBean = (InlandHotCityBean) JsonUtil.json2Obj(success, InlandHotCityBean.class);
            initDatas(mBodyDatas, inlandHotCityBean.getCityList());
            dismissLoadingDialog();
        }
    }

    /**
     * 组织数据源
     *
     * @return
     */
    private void initDatas(List<InlandBean.ResultBean> list, List<InlandHotCityBean.ResultBean> hotCitys) {
        //先排序
        mIndexBar.getDataHelper().sortSourceDatas(list);
        mAdapter.setDatas(list);
        mHeaderAdapter.notifyDataSetChanged();
        mSourceDatas.addAll(list);
        mIndexBar.setmSourceDatas(mSourceDatas)//设置数据
                .invalidate();
        mDecoration.setmDatas(mSourceDatas);
        InlandHotCityBean header3 = mHeaderDatas.get(0);
        header3.setCityList(hotCitys);
        mHeaderAdapter.notifyItemRangeChanged(0, 1);
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter = null;
        mHeaderAdapter.clearFooterView();
        mHeaderAdapter.clearHeaderView();
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        InlandBean.ResultBean resultBean = mBodyDatas.get(position);
        InlandHotCityBean.ResultBean resultBean1 = new InlandHotCityBean.ResultBean();
        resultBean1.setId(resultBean.getId());
        resultBean1.setName(resultBean.getName());
        resultBean1.setLevel(resultBean.getLevel());
        resultBean1.setParent_id(resultBean.getParent_id());
//        saveHistory(resultBean1);
     //   PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", resultBean.getName());
        Intent intent = new Intent();
        // 获取内容
        intent.putExtra("selectCity", resultBean.getName());
        intent.putExtra("selectCityId", resultBean.getId());
        intent.putExtra("selectCountry", selectCountry);
        intent.putExtra("selectCountryId", resultBean.getCountry_id());
        // 设置结果 结果码，一个数据
        aty.setResult(RESULT_OK, intent);
        // 结束该activity 结束之后，前面的activity才可以处理结果
        aty.finish();
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STATUS && resultCode == RESULT_OK) {// 如果等于1
            String selectCity = data.getStringExtra("selectCity");
            int selectCityId = data.getIntExtra("selectCityId", 0);
            android.util.Log.d("tag888", selectCity);
            Intent intent = new Intent();
            // 获取内容
            intent.putExtra("selectCity", selectCity);
            intent.putExtra("selectCityId", selectCityId);
            //   intent.putExtra("selectCountry", selectCountry);
            intent.putExtra("selectCountryId", selectCountryId);
            // 设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
        }
    }

}
