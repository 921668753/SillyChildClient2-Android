package com.sillykid.app.adapter.main.activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.DensityUtils;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.ActivitiesBean.DataBean.MonthHotBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页——活动当季热销商品列表  适配器
 * Created by Administrator on 2017/9/6.
 */

public class ProductlViewAdapter extends BGARecyclerViewAdapter<MonthHotBean> {

    public ProductlViewAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_mallhomepage);
    }


//    public MallHomePageViewAdapter(Context mcontext, List<ListBean> list, GoodsListItemOnClickListener listener) {
//        this.list = list;
//        this.mcontext = mcontext;
//        this.listener = listener;
//    }

//    @Override
//    public GoodsListView onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mallhomepage, viewGroup, false);
//        GoodsListView goodsListView = new GoodsListView(view, listener);
//        return goodsListView;
//    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, MonthHotBean model) {
        ImageView imageView = (ImageView) helper.getView(R.id.img_good);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        float width1 = (DensityUtils.getScreenW() - 6 * 3 - 10 * 2) / 2;
        lp.width = (int) width1;
        float scale = 0;
        float tempHeight = 0;
        if (model.getWidth() <= 0 || model.getHeight() <= 0) {
            tempHeight = width1;
        } else {
            scale = (width1 + 0f) / model.getWidth();
            tempHeight = model.getHeight() * scale;
        }
        lp.height = (int) tempHeight;
        imageView.setLayoutParams(lp);
        GlideImageLoader.glideLoaderRaudio(mContext, model.getThumbnail() + "?imageView2/0/w/" + lp.width + "/h/" + lp.height, imageView, 5, (int) lp.width, (int) lp.height, R.mipmap.placeholderfigure);
        helper.setText(R.id.tv_goodName, model.getName());
        if (StringUtils.isEmpty(model.getBrief())) {
            helper.setVisibility(R.id.tv_goodSynopsis, View.GONE);
        } else {
            helper.setText(R.id.tv_goodSynopsis, model.getBrief());
        }
        helper.setText(R.id.tv_goodMoney, mContext.getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(model.getPrice())));
        helper.setText(R.id.tv_brand, model.getBrand_name());
        if (StringUtils.isEmpty(model.getStore_name()) && StringUtils.isEmpty(model.getGoods_tag())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.GONE);
        } else if (!StringUtils.isEmpty(model.getStore_name()) && StringUtils.isEmpty(model.getGoods_tag())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.VISIBLE);
            helper.setVisibility(R.id.img_proprietary, View.VISIBLE);
            helper.setVisibility(R.id.tv_timedSpecials, View.GONE);
        } else if (StringUtils.isEmpty(model.getStore_name()) && !StringUtils.isEmpty(model.getGoods_tag())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.VISIBLE);
            helper.setVisibility(R.id.img_proprietary, View.GONE);
            helper.setVisibility(R.id.tv_timedSpecials, View.VISIBLE);
            helper.setText(R.id.tv_timedSpecials, model.getGoods_tag());
        } else if (!StringUtils.isEmpty(model.getStore_name()) && !StringUtils.isEmpty(model.getGoods_tag())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.VISIBLE);
            helper.setVisibility(R.id.img_proprietary, View.VISIBLE);
            helper.setVisibility(R.id.tv_timedSpecials, View.VISIBLE);
            helper.setText(R.id.tv_timedSpecials, model.getGoods_tag());
        } else {
            helper.setVisibility(R.id.ll_bottomLabel, View.GONE);
        }
    }

//    @Override
//    public void onBindViewHolder(GoodsListView goodsListView, int position) {
//        GlideImageLoader.glideOrdinaryLoader(mContext, model.getImg(), (ImageView) helper.getView(R.id.img_good), R.mipmap.placeholderfigure);
//        helper.setText(R.id.tv_goodName, model.getTitle());
//        helper.setText(R.id.tv_goodSynopsis, model.getTitle());
//        helper.setText(R.id.tv_goodMoney, model.getTitle());
//        helper.setText(R.id.tv_brand, model.getTitle());
//        if (TextUtils.isEmpty(model.getSubTitle())) {
//            helper.setVisibility(R.id.ll_bottomLabel, View.GONE);
//            helper.setVisibility(R.id.img_proprietary, View.GONE);
//            helper.setVisibility(R.id.img_timedSpecials, View.VISIBLE);
//        } else {
//            helper.setVisibility(R.id.ll_bottomLabel, View.GONE);
//            helper.setVisibility(R.id.img_proprietary, View.GONE);
//            helper.setVisibility(R.id.img_timedSpecials, View.VISIBLE);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class GoodsListView extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        ImageView img_good;
//        TextView tv_goodName;
//        TextView tv_goodSynopsis;
//        TextView tv_goodMoney;
//        TextView tv_brand;
//        LinearLayout ll_bottomLabel;
//        TextView tv_proprietary;
//        TextView tv_timedSpecials;
//
//        private GoodsListItemOnClickListener itemlistener;
//
//        public GoodsListView(View itemView, GoodsListItemOnClickListener listener) {
//            super(itemView);
//            img_good = (ImageView) itemView.findViewById(R.id.img_good);
//            tv_goodName = (TextView) itemView.findViewById(R.id.tv_goodName);
//            tv_goodMoney = (TextView) itemView.findViewById(R.id.tv_goodMoney);
//            tv_goodSynopsis = (TextView) itemView.findViewById(R.id.tv_goodSynopsis);
//            tv_brand = (TextView) itemView.findViewById(R.id.tv_brand);
//            ll_bottomLabel = (LinearLayout) itemView.findViewById(R.id.ll_bottomLabel);
//            tv_proprietary = (TextView) itemView.findViewById(R.id.tv_proprietary);
//            tv_timedSpecials = (TextView) itemView.findViewById(R.id.tv_timedSpecials);
//            this.itemlistener = listener;
//            itemView.setOnClickListener(this);
//        }
//
//
//        @Override
//        public void onClick(View view) {
//            if (itemlistener != null) itemlistener.goodsListOnItemClick(view, getPosition());
//        }
//    }
//
//    public interface GoodsListItemOnClickListener {
//        void goodsListOnItemClick(View view, int postion);
//
//    }

}