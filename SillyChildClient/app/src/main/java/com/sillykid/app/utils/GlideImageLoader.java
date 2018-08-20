package com.sillykid.app.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.common.cklibrary.R;
import com.common.cklibrary.common.GlideApp;
import com.common.cklibrary.utils.glide.GlideCatchUtil;
import com.common.cklibrary.utils.glide.GlideCircleTransform;
import com.common.cklibrary.utils.glide.GlideRoundTransform;
import com.common.cklibrary.utils.glide.RoundCornersTransformation;
import com.lzy.imagepicker.loader.ImageLoader;


import java.io.File;

/**
 * 加载图片工具类
 */
@SuppressWarnings("deprecation")
public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.placeholderfigure);
        options.fallback(R.mipmap.placeholderfigure);//当url为空时，显示图片
        options.diskCacheStrategy(DiskCacheStrategy.ALL);//缓存全尺寸
        if (path.startsWith("http")) {
            Glide.with(activity)
                    //      .asBitmap()//配置上下文
                    .load(path)//设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                    //  .placeholder(R.mipmap.load)     //设置占位图片
                    .apply(options)
                    //     .centerInside()
                    //  .transition(withCrossFade().crossFade())//应用在淡入淡出
                    //  .skipMemoryCache(true)//设置跳过内存缓存
                    .into(imageView);
        } else {
            Uri contentUri = null;
            //调试，暂注释   ---qi
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                contentUri = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".fileprovider", new File(path));
//            } else {
            contentUri = Uri.fromFile(new File(path));
//            }
            if (contentUri == null) {
                // ViewInject.toast(KJActivityStack.create().topActivity().getString(R.string.imagePathError));
                return;
            }
            Glide.with(activity)
                    //    .asBitmap()//配置上下文
                    .load(contentUri)//设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                    .apply(options)
                    //        .centerInside()
                    //  .transition(withCrossFade().crossFade())//应用在淡入淡出
                    //  .skipMemoryCache(true)//设置跳过内存缓存
                    .into(imageView);
        }
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.placeholderfigure);
        options.fallback(R.mipmap.placeholderfigure);//当url为空时，显示图片
        options.diskCacheStrategy(DiskCacheStrategy.ALL);//缓存全尺寸
        if (path.startsWith("http")) {
            Glide.with(activity)
                    //      .asBitmap()//配置上下文
                    .load(path)//设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                    .apply(options)
                    //     .centerInside()
                    //  .transition(withCrossFade().crossFade())//应用在淡入淡出
                    //  .skipMemoryCache(true)//设置跳过内存缓存
                    .into(imageView);
        } else {
            Glide.with(activity)                             //配置上下文
                    .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                    .apply(options)
                    .into(imageView);
        }
    }


    @Override
    public void clearMemoryCache() {
        // 必须在UI线程中调用
        GlideCatchUtil.getInstance().clearCacheMemory();
        // 必须在后台线程中调用，建议同时clearMemory()
        GlideCatchUtil.getInstance().clearCacheDiskSelf();
    }


    /**
     * @param context
     * @param url
     * @param imageView
     * @param tag       ==0为圆形  ==1为圆角
     */
    public static void glideLoader(Context context, Object url, ImageView imageView, int tag) {

        if (0 == tag) {
            RequestOptions options = new RequestOptions();
            options.error(R.mipmap.placeholderfigure);
            options.fallback(R.mipmap.placeholderfigure);//当url为空时，显示图片
            options.diskCacheStrategy(DiskCacheStrategy.ALL);//缓存全尺寸
            options.transform(new GlideCircleTransform(context));
            options.dontAnimate();//没有任何淡入淡出效果
            Glide.with(context)
                    .load(url)
                    //  .skipMemoryCache(true)//设置跳过内存缓存
//                    .placeholder(R.mipmap.loading)
                    .apply(options)
                    //    .transition(withCrossFade().crossFade())//应用在淡入淡出
                    .into(imageView);
        } else if (1 == tag) {
            RequestOptions options = new RequestOptions();
            options.error(R.mipmap.placeholderfigure)
                    .fallback(R.mipmap.placeholderfigure)//当url为空时，显示图片
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                    .transform(new GlideRoundTransform(context, 10))
                    .dontAnimate();//没有任何淡入淡出效果
            Glide.with(context)
                    .load(url)
                    //  .placeholder(R.mipmap.loading)
                    .apply(options)
                    //   .transition(withCrossFade().crossFade())//应用在淡入淡出
                    .into(imageView);
        }
    }

    /**
     * @param context
     * @param url
     * @param imageView
     * @param tag       ==0为圆形  ==1为圆角
     */
    public static void glideLoader(Context context, Object url, ImageView imageView, int tag, int defaultimage) {

        if (0 == tag) {
            RequestOptions options = new RequestOptions();
            options.error(defaultimage)
                    .fallback(defaultimage)//当url为空时，显示图片
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                    .transform(new GlideCircleTransform(context))
                    .dontAnimate();//没有任何淡入淡出效果
            Glide.with(context)
                    .load(url)
                    //  .skipMemoryCache(true)//设置跳过内存缓存
//                    .placeholder(R.mipmap.loading)
                    .apply(options)
                    //    .transition(withCrossFade().crossFade())//应用在淡入淡出
                    .into(imageView);
        } else if (1 == tag) {
            RequestOptions options = new RequestOptions();
            options.error(defaultimage)
                    .fallback(defaultimage)//当url为空时，显示图片
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                    .transform(new GlideRoundTransform(context, 10))
                    .dontAnimate();//没有任何淡入淡出效果
            Glide.with(context)
                    .load(url)
                    //  .placeholder(R.mipmap.loading)
                    .apply(options)
                    //   .transition(withCrossFade().crossFade())//应用在淡入淡出
                    .into(imageView);
        }
    }

    /**
     * @param context
     * @param url
     * @param imageView raudio   圆角大小
     */
    public static void glideLoaderRaudio(Context context, Object url, ImageView imageView, int raudio, int defaultimage) {
        RequestOptions options = new RequestOptions();
        options.error(defaultimage)
                .fallback(defaultimage)//当url为空时，显示图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .transform(new GlideRoundTransform(context, raudio))
                .dontAnimate();//没有任何淡入淡出效果
        Glide.with(context)
                .load(url)
                //  .placeholder(R.mipmap.loading)
                .apply(options)
                //   .transition(withCrossFade().crossFade())//应用在淡入淡出
                .into(imageView);

    }


    /**
     * @param context
     * @param url
     * @param imageView raudio   圆角大小
     */
    public static void glideLoaderRaudio(Context context, Object url, ImageView imageView, int raudio, int width, int height, int defaultimage) {
        RequestOptions options = new RequestOptions();
        options.error(defaultimage)
                .fallback(defaultimage)//当url为空时，显示图片
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .transform(new GlideRoundTransform(context, raudio))
                .dontAnimate();//没有任何淡入淡出效果
        Glide.with(context)
                .load(url)
                //  .placeholder(R.mipmap.loading)
                .apply(options)
                //   .transition(withCrossFade().crossFade())//应用在淡入淡出
                .into(imageView);
    }


    /**
     * 需要在子线程执行
     *
     * @param context
     * @param url
     * @return
     */
    public static Bitmap load(Context context, String url) {
        try {
            return GlideApp.with(context)
                    .asBitmap()
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 需要在子线程执行
     *
     * @param context
     * @param url
     * @return
     */
    public static Bitmap load(Context context, int url) {
        try {
            return GlideApp.with(context)
                    .asBitmap()
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
//        int width = drawable.getIntrinsicWidth();
//        int height = drawable.getIntrinsicHeight();
//        Bitmap oldbmp = drawableToBitmap(drawable);
//        Matrix matrix = new Matrix();
//        float scaleWidth = ((float) w / width);
//        float scaleHeight = ((float) h / height);
//        matrix.postScale(scaleWidth, scaleHeight);
//        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
//                matrix, true);
//        return new BitmapDrawable(null, newbmp);
//    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 图片上边圆角，下边直角
     */
    public static void glideLoader(Context context, Object url, ImageView imageView, int radius, int bottom, int defaultimage) {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.default_image)
                .fallback(R.mipmap.default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new RoundCornersTransformation(context, radius, RoundCornersTransformation.CornerType.TOP))
                //   .skipMemoryCache(true)//设置跳过内存缓存
                .dontAnimate();//没有任何淡入淡出效果
        Glide.with(context)
                .load(url)
                //  .placeholder(R.mipmap.loading)
                .apply(options)
                //   .transition(withCrossFade().crossFade())//应用在淡入淡出
                .into(imageView);
    }


    /**
     * 加载网络图片，并可配置默认图片，加载失败图片，占位图片
     */
    public static void glideOrdinaryLoader(Context context, Object url, ImageView imageView, int defaultimage) {

        RequestOptions options = new RequestOptions();
        options.placeholder(defaultimage)
                .error(defaultimage)
                .fallback(defaultimage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

        Glide.with(context)
                .load(url)
                //  .skipMemoryCache(true)//设置跳过内存缓存
                .apply(options)
//                .transition(withCrossFade())//应用在淡入淡出
                .into(imageView);
    }

    /**
     * 加载网络图片，并可配置默认图片，加载失败图片，占位图片
     */
    public static void glideOrdinaryLoader(Context context, Object url, PhotoView photoView, int defaultimage) {
        RequestOptions options = new RequestOptions();
        options.error(defaultimage)
                .fallback(defaultimage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();
        Glide.with(context)
                .load(url)
                //  .skipMemoryCache(true)//设置跳过内存缓存
                //  .placeholder(R.mipmap.loading)
                .apply(options)
//                .transition(withCrossFade())//应用在淡入淡出
                .into(photoView);
    }

    public static void glideOrdinaryLoader(Context context, Object url, ImageView imageView) {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.placeholderfigure)
                .fallback(R.mipmap.placeholderfigure)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();
        Glide.with(context)
                .load(url)
                //  .skipMemoryCache(true)//设置跳过内存缓存
                //  .placeholder(R.mipmap.loading)
                .apply(options)
//                .transition(withCrossFade())//应用在淡入淡出
                .into(imageView);
    }

    //心形图片
    public static void heartIconLoader(Context context, Object url, ImageView imageView) {
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();
        Glide.with(context)
                .load(url)
                //  .skipMemoryCache(true)//设置跳过内存缓存
                //  .placeholder(R.mipmap.loading)
//                .error(null)
//                .fallback(null)
                .apply(options)
//                .transition(withCrossFade())//应用在淡入淡出
                .into(imageView);
    }


    /**
     * 调用Clear()API明确清除先前的加载，以防加载到旧数据
     */
    public static void glideWithClear(Context context, String url, ImageView imageView, int defaultiamge) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(defaultiamge);
        } else {
            RequestOptions options = new RequestOptions();
            options.error(defaultiamge)
                    .fallback(defaultiamge)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();
            Glide.with(context)
                    .load(url)
                    //  .skipMemoryCache(true)//设置跳过内存缓存
                    //  .placeholder(R.mipmap.loading)
                    .apply(options)
//                .transition(withCrossFade())//应用在淡入淡出
                    .into(imageView);
        }
    }

}
