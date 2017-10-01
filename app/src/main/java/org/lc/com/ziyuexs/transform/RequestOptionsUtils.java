package org.lc.com.ziyuexs.transform;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Administrator on 2017-9-18.
 */

public class RequestOptionsUtils {
    /***
     * 默认请求对象
     * @return
     */
    public static RequestOptions newDefaultRequestOtions(){
        return new RequestOptions().centerCrop().priority(Priority.NORMAL);
    }

    public static RequestOptions newRequestOtionsBy(int placeholder, int error){
        return new RequestOptions().centerCrop().priority(Priority.NORMAL).placeholder(placeholder).error(error);
    }

    public static RequestOptions newRequestOtions(Priority priority, Drawable placeholder){
        //return new RequestOptions().centerCrop().priority(Priority.NORMAL);
        return newRequestOtions(priority, null);
    }




    public static RequestOptions newRequestOtions(BitmapTransformation transformation){
        return new RequestOptions().centerCrop().priority(Priority.HIGH).transform(transformation);
    }

    public static RequestOptions newRequestOtions(BitmapTransformation transformation, Drawable placeholder){
        return new RequestOptions().centerCrop().priority(Priority.HIGH).transform(transformation).placeholder(placeholder);
    }

    public static RequestOptions newRequestOtions(BitmapTransformation transformation, Drawable placeholder, Drawable error){
        return new RequestOptions().centerCrop().priority(Priority.HIGH).transform(transformation).placeholder(placeholder).error(error);
    }

    public static RequestOptions newRequestOtions(BitmapTransformation transformation, int placeholder, int error){
        return new RequestOptions().centerCrop().priority(Priority.HIGH).transform(transformation).placeholder(placeholder).error(error);
    }

    public static RequestOptions newRequestOtions(BitmapTransformation transformation, int placeholder, int error, int quality){
        return new RequestOptions().centerCrop().priority(Priority.HIGH).transform(transformation).placeholder(placeholder).error(error).encodeQuality(quality);
    }

    public static RequestOptions newRequestOtionsBy(BitmapTransformation transformation,
                                                  Drawable placeholder,
                                                  Drawable error,
                                                  int quality,
                                                  Priority priority,
                                                  boolean isSkipMemoryCache,
                                                  DiskCacheStrategy mDiskCacheStrategy,
                                                  int drawablewith,
                                                  int drawableheight,
                                                  RequestOptions  opt,
                                                  int scaleType){
        RequestOptions options = new RequestOptions();
        setScaleType(options, scaleType);                           //设置图片加载类型
        setPriority(options, priority);                             //设置加载优先级
        options.transform(transformation);                          //设置自定义Transformation
        if(error != null)
            options.error(error);                                   //设置加载错误显示图片
        if(placeholder != null)
            options.placeholder(placeholder);                       //设置默认加载图片
        if(quality > 0 && quality <= 100)
            options.encodeQuality(quality);                         //设置图片质量（0-100， 默认为100）
        options.skipMemoryCache(isSkipMemoryCache);                 //设置是否跳过使用内存加载
        options.diskCacheStrategy(mDiskCacheStrategy);              //设置二级缓存策略
        if(drawablewith > 0 && drawableheight > 0)
            options.override(drawablewith,  drawableheight);        //自定义加载图片的高度和宽度

        options.apply(opt);                                         //子对象加载
        return options;
    }

    /**
     * 设置ScaleType
     * @param options
     * @param scaleType
     */
    private static void setScaleType(RequestOptions options, int scaleType){
        if(options == null) return;
        switch (scaleType){
            case SCALE_TYPE_CENTER_CROP:
                options.centerCrop();
                break;
            case SCALE_TYPE_CENTER_INSIDE:
                options.centerInside();
                break;
            case SCALE_TYPE_CIRCLE_CROP:
                options.circleCrop();
                break;
            case SCALE_TYPE_FIT_CENTER:
                options.fitCenter();
                break;
            default:
                options.centerCrop();
                break;
        }
    }

    /**
     * 设置优先级
     * @param options
     * @param scaleType
     */
    private static void setPriority(RequestOptions options, Priority scaleType){
        if(scaleType.equals(Priority.HIGH)){
            options.priority(Priority.HIGH);
        }else if(scaleType.equals(Priority.IMMEDIATE)){
            options.priority(Priority.IMMEDIATE);
        }else if(scaleType.equals(Priority.LOW)){
            options.priority(Priority.LOW);
        }else if(scaleType.equals(Priority.NORMAL)){
            options.priority(Priority.NORMAL);
        }else{
            options.priority(Priority.HIGH);
        }

    }

    public final static int SCALE_TYPE_CENTER_CROP = 0;
    public final static int SCALE_TYPE_CENTER_INSIDE = 1;
    public final static int SCALE_TYPE_CIRCLE_CROP= 2;
    public final static int SCALE_TYPE_FIT_CENTER= 3;

}
