package com.sillykid.app.adapter.main.mall;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.DensityUtils;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.MallBean.DataBean.HomePageBean;
import com.sillykid.app.utils.GlideImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页——商品列表  适配器
 * Created by Administrator on 2017/9/6.
 */

public class MallViewAdapter extends BGARecyclerViewAdapter<HomePageBean> {

    public MallViewAdapter(RecyclerView recyclerView) {
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
    protected void fillData(BGAViewHolderHelper helper, int position, HomePageBean model) {
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
        GlideImageLoader.glideLoaderRaudio(mContext, model.getThumbnail() + "?imageView2/0/w/" + lp.width + "/h/" + lp.width, imageView, 5, (int) lp.width, (int) lp.height, R.mipmap.placeholderfigure);
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

    public static void getImageWidthHeight(String path) {
        try {
            URL url = new URL(path);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 10;
            Bitmap originalImage = BitmapFactory.decodeStream(is, null, options);
            // imageView.setImageBitmap(originalImage);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 动态设置imageView的大小(可以设置到父级如:ViewGroup)  根据下载的图片大小 计算高度(宽度是全屏宽)
     *
     * @param context
     * @param url
     * @param imageView
     * @param srceenWidth
     * @param topMargin
     * @param layoutParamsType 0 LinearLayout.LayoutParams   1 FrameLayout$LayoutParams
     */
    public static void getPicByGlideAndScale(@NonNull final Context context, @NonNull final String url, @NonNull final ImageView imageView,
                                             final float srceenWidth, final int topMargin, final View parentView, final int layoutParamsType) {
//        getPicBitMapByGlide(context, url, imageView, new IUICallBackInterface() {
//            @Override
//            public void uiCallBack(Object supportResponse, int caseKey) {
//                Bitmap source = (Bitmap) supportResponse;
//                // Do something with bitmap here.
//                int height = source.getHeight();
//                int width = source.getWidth();
//                float height_temp_1 = srceenWidth / width;
//                if (layoutParamsType == 0) {
//                    final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) srceenWidth, (int) (height_temp_1 * height));
//                    layoutParams.topMargin = topMargin;
//                    imageView.setLayoutParams(layoutParams);
////                    if (YunbaoConfig.isDebug())
////                        Log.e(YunbaoConfig.Debug_flage, "==========view.width= " + layoutParams.width + " height= " + layoutParams.height + " height_temp_1= " + height_temp_1 + " url= " + url);
//                } else if (layoutParamsType == 1) {
//                    final FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) srceenWidth, (int) (height_temp_1 * height));
//                    layoutParams.topMargin = topMargin;
//                    imageView.setLayoutParams(layoutParams);
//                }
//                Glide.with(context)
//                        .load(url)
////                                .override(layoutParams.width, layoutParams.height)
//                        .into(imageView);
//                imageView.invalidate();
//                if (parentView != null) {
//                    ViewGroup.LayoutParams groupParams = parentView.getLayoutParams();
//                    groupParams.height = (int) (height_temp_1 * height);
//                    groupParams.width = (int) srceenWidth;
//                    parentView.invalidate();
//                }
//            }
//        });
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