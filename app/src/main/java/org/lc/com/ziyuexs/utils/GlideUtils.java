package org.lc.com.ziyuexs.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import org.lc.com.ziyuexs.R;
import org.lc.com.ziyuexs.module.glide.GlideApp;
import org.lc.com.ziyuexs.transform.BlurTransformation;
import org.lc.com.ziyuexs.transform.GlideCircleTransform;
import org.lc.com.ziyuexs.transform.GlideRoundTransform;
import org.lc.com.ziyuexs.transform.RequestOptionsUtils;
import org.lc.com.ziyuexs.transform.RotateTransformation;

import butterknife.BindDrawable;

import static com.bumptech.glide.Glide.with;
/*import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;*/

/**
 * @Description:主要功能:Glide图片加载工具类
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月19日 14:29
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

/**
 *Glide 工具类
 *Google推荐的图片加载库，专注于流畅的滚动
 *<p>
 *Glide 比Picasso  加载快 但需要更大的内存来缓存
 *<p>
 *Glide 不光接受Context，还接受Activity 和 Fragment ,图片加载会和Activity/Fragment的生命周期保持一致 在onPause（）暂停加载，onResume（）恢复加载
 *<p>
 *支持GIF格式图片加载
 */
public class GlideUtils {

    private static GlideUtils instance;

    @BindDrawable(R.drawable.ic_home_black_24dp)
    Drawable mDefaultpic;
    @BindDrawable(R.drawable.ic_dashboard_black_24dp)
    Drawable mErorpic;


    public static GlideUtils getInstance(){
        if(instance==null){
            synchronized (GlideUtils.class) {
                if(instance==null){
                    instance=new GlideUtils();
                }
            }
        }
        return instance;
    }
    //  with(Context context). 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制。
    //  with(Activity activity).使用Activity作为上下文，Glide的请求会受到Activity生命周期控制。
    //  with(FragmentActivity activity).Glide的请求会受到FragmentActivity生命周期控制。
    //  with(android.app.Fragment fragment).Glide的请求会受到Fragment 生命周期控制。
    //  with(android.support.v4.app.Fragment fragment).Glide的请求会受到Fragment生命周期控制。
    //-----------------------------
    //  Glide基本可以load任何可以拿到的媒体资源，如：
    //  load SD卡资源：load("file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg")
    //  load assets资源：load("file:///android_asset/f003.gif")
    //  load raw资源：load("android.resource://com.frank.glide/raw/raw_1")或load("android.resource://com.frank.glide/raw/"+R.raw.raw_1)
    //  load drawable资源：load("android.resource://com.frank.glide/drawable/news")或load("android.resource://com.frank.glide/drawable/"+R.drawable.news)
    //  load ContentProvider资源：load("content://media/external/images/media/139469")
    //  load http资源：load("http://img.my.csdn.net/uploads/201508/05/1438760757_3588.jpg")
    //  load https资源：load("https://img.alicdn.com/tps/TB1uyhoMpXXXXcLXVXXXXXXXXXX-476-538.jpg_240x5000q50.jpg_.webp")
    //  当然，load不限于String类型，还可以：
    //  load(Uri uri)，load(File file)，load(Integer resourceId)，load(URL url)，load(byte[] model, final String id)，load(byte[] model)，load(T model)。
    //  而且可以使用自己的ModelLoader进行资源加载：
    //  using(ModelLoader<A, T> modelLoader, Class<T> dataClass)，using(final StreamModelLoader<T> modelLoader)，using(StreamByteArrayLoader modelLoader)，using(final FileDescriptorModelLoader<T> modelLoader)。
    //  返回RequestBuilder实例
    //--------------------------------------
    //  * thumbnail(float sizeMultiplier). 请求给定系数的缩略图。如果缩略图比全尺寸图先加载完，
    //        就显示缩略图，否则就不显示。系数sizeMultiplier必须在(0,1)之间，可以递归调用该方法。

    //  * sizeMultiplier(float sizeMultiplier). 在加载资源之前给Target大小设置系数。

    //  * skipMemoryCache(boolean skip). 设置是否跳过内存缓存，但不保证一定不被缓存
    //     （比如请求已经在加载资源且没设置跳过内存缓存，这个资源就会被缓存在内存中）。
    //  *  diskCacheStrategy(DiskCacheStrategy strategy).设置缓存策略。
    //     DiskCacheStrategy.SOURCE：缓存原始数据，
    //     DiskCacheStrategy.RESULT：缓存变换修改后的资源数据，
    //     DiskCacheStrategy.NONE：什么都不缓存，
    //     DiskCacheStrategy.ALL：缓存所有图片  默认
    //          默认采用DiskCacheStrategy.RESULT策略，对于download only操作要使用DiskCacheStrategy.SOURCE。

    //  * priority(Priority priority). 指定加载的优先级，优先级越高越优先加载，但不保证所有图片都按序加载。
    //       枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW。默认为Priority.NORMAL。
    //  * crossFade(5000) //设置淡入淡出效果，默认300ms，可以传参
    //  * dontAnimate(). 移除所有的动画。
    //  * animate(int animationId). 在异步加载资源完成时会执行该动画。
    //  * animate(ViewPropertyAnimation.Animator animator). 在异步加载资源完成时会执行该动画。
    //  * placeholder(int resourceId). 设置资源加载过程中的占位Drawable。
    //  * placeholder(Drawable drawable). 设置资源加载过程中的占位Drawable。

    //  * fallback(int resourceId). 设置model为空时要显示的Drawable。如果没设置fallback，
    //    model为空时将显示error的Drawable，如果error的Drawable也没设置，就显示placeholder的Drawable。
    //  * fallback(Drawable drawable).设置model为空时显示的Drawable。
    //  * error(int resourceId).设置load失败时显示的Drawable。
    //  * error(Drawable drawable).设置load失败时显示的Drawable。

    //  * Glide支持两种图片缩放形式，CenterCrop 和 FitCenter
    //    CenterCrop：等比例缩放图片， 直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。
    //    FitCenter：等比例缩放图片，宽或者是高等于ImageView的宽或者是高。

    //  * 当列表在滑动的时候，调用pauseRequests()取消请求，滑动停止时，调用resumeRequests()恢复请求

    //  * listener(RequestListener<? super ModelType, TranscodeType> requestListener).
    //        监听资源加载的请求状态，可以使用两个回调：
    //     onResourceReady(R resource, T model, Target<R> target, boolean isFromMemoryCache, boolean isFirstResource)
    //       和onException(Exception e, T model, Target&lt;R&gt; target, boolean isFirstResource)，
    //       但不要每次请求都使用新的监听器，要避免不必要的内存申请，可以使用单例进行统一的异常监听和处理。
    //  * clear() 清除掉所有的图片加载请求
    //  * override(int width, int height). 重新设置Target的宽高值（单位为pixel）。
    //  * into(Y target).设置资源将被加载到的Target。
    //  * into(ImageView view). 设置资源将被加载到的ImageView。取消该ImageView之前所有的加载并释放资源。
    //  * into(int width, int height). 后台线程加载时要加载资源的宽高值（单位为pixel）。
    //  * preload(int width, int height). 预加载resource到缓存中（单位为pixel）。
    //  * asBitmap(). 无论资源是不是gif动画，都作为Bitmap对待。如果是gif动画会停在第一帧。
    //  * asGif().把资源作为GifDrawable对待。如果资源不是gif动画将会失败，会回调.error()。
    //-------------------------------------------------------------------------------
    /**
     * 加载bitmap，如果是GIF则显示第一帧
     */
    public static String LOAD_BITMAP="GLIDEUTILS_GLIDE_LOAD_BITMAP";
    /**
     * 加载gif动画
     */
    public static String LOAD_GIF="GLIDEUTILS_GLIDE_LOAD_GIF";
    /**
     * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
     * 使用activity 会受到Activity生命周期控制
     * 使用FragmentActivity 会受到FragmentActivity生命周期控制
     * @param context
     * @param path
     * @param imageView
     * @param placeid 占位
     * @param errorid  错误
     * @param bitmapOrgif  加载普通图片 或者GIF图片 ，GIF图片设置bitmap显示第一帧
     */
    public void LoadContextBitmap(Context context,String path,ImageView imageView,int placeid,int errorid,String bitmapOrgif){
        if(bitmapOrgif==null||bitmapOrgif.equals(LOAD_BITMAP)){
            GlideApp
                    .with(context)
                    .load(path)
                    .placeholder(placeid)
                    .error(errorid)
                    //.apply(RequestOptionsUtils.newRequestOtionsBy(placeid, errorid))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);

        }else if(bitmapOrgif.equals(LOAD_GIF)){

            GlideApp
                    .with(context)
                    .asGif()
                    .load(path)
                    .apply(RequestOptionsUtils.newRequestOtionsBy(placeid, errorid))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }
    }

    /**
     * Glide请求图片，会受到Fragment 生命周期控制。
     * @param fragment
     * @param path
     * @param imageView
     * @param placeid
     * @param errorid
     * @param bitmapOrgif  加载普通图片 或者GIF图片 ，GIF图片设置bitmap显示第一帧
     */
    public void LoadFragmentBitmap(android.app.Fragment fragment,String path,ImageView imageView,int placeid,int errorid,String bitmapOrgif){
//        if(bitmapOrgif==null||bitmapOrgif.equals(LOAD_BITMAP)){
//            with(fragment).load(path).placeholder(placeid).error(errorid).crossFade().into(imageView);
//        }else if(bitmapOrgif.equals(LOAD_GIF)){
//            with(fragment).load(path).asGif().crossFade().into(imageView);
//        }imageView
        if(bitmapOrgif==null||bitmapOrgif.equals(LOAD_BITMAP)){
            GlideApp
                    .with(fragment)
                    .load(path)
                    .placeholder(placeid)
                    .error(errorid)
                    //.apply(RequestOptionsUtils.newRequestOtionsBy(placeid, errorid))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }else if(bitmapOrgif.equals(LOAD_GIF)){
            GlideApp.
                    with(fragment)
                    .asGif()
                    .load(path)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }
    }
    /**
     * Glide请求图片，会受到support.v4.app.Fragment生命周期控制。
     * @param fragment
     * @param path
     * @param imageView
     * @param placeid
     * @param errorid
     * @param bitmapOrgif  加载普通图片 或者GIF图片 ，GIF图片设置bitmap显示第一帧
     */
    public void LoadSupportv4FragmentBitmap(android.support.v4.app.Fragment fragment,String path,ImageView imageView,int placeid,int errorid,String bitmapOrgif){
        if(bitmapOrgif==null||bitmapOrgif.equals(LOAD_BITMAP)){
            GlideApp
                    .with(fragment)
                    .load(path)
                    .placeholder(placeid)
                    .error(errorid)
                    //.apply(RequestOptionsUtils.newRequestOtionsBy(placeid, errorid))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }else if(bitmapOrgif.equals(LOAD_GIF)){
            GlideApp.
                    with(fragment)
                    .asGif()
                    .load(path)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }
    }
    //---------------------圆形图片-------------------
    /**
     * 加载设置圆形图片
     * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
     * <BR/>使用activity 会受到Activity生命周期控制
     * <BR/>使用FragmentActivity 会受到FragmentActivity生命周期控制
     * @param context
     * @param path
     * @param imageView
     */
    @SuppressWarnings("unchecked")
    public void LoadContextCircleBitmap(Context context,String path,ImageView imageView){
        with(context)
                .load(path)
                .apply(RequestOptionsUtils.newRequestOtions(
                        newInstanceTransformation(TRANSFORMATION_FLAG_CIRCLE, context, -1.0f)))
                .into(imageView);
    }
    /**
     * Glide请求图片设置圆形，会受到android.app.Fragment生命周期控制
     * @param fragment
     * @param path
     * @param imageView
     */
    @SuppressWarnings("unchecked")
    public void LoadfragmentCircleBitmap(android.app.Fragment fragment,String path,ImageView imageView){
        with(fragment)
                .load(path)
                .apply(RequestOptionsUtils.newRequestOtions(
                        newInstanceTransformation(TRANSFORMATION_FLAG_CIRCLE, fragment.getActivity(), -1.0f)))
                .into(imageView);
    }
    /**
     * Glide请求图片设置圆形，会受到android.support.v4.app.Fragment生命周期控制
     * @param fragment
     * @param path
     * @param imageView
     */
    @SuppressWarnings("unchecked")
    public void LoadSupportv4FragmentCircleBitmap(android.support.v4.app.Fragment fragment,String path,ImageView imageView){
        with(fragment)
                .load(path)
                .apply(RequestOptionsUtils.newRequestOtions(
                        newInstanceTransformation(TRANSFORMATION_FLAG_CIRCLE, fragment.getActivity(), -1.0f)))
                .into(imageView);
    }
    //-----------------------圆角图片----------------------
    /**
     * 加载设置圆角图片
     * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
     * <BR/>使用activity 会受到Activity生命周期控制
     * <BR/>使用FragmentActivity 会受到FragmentActivity生命周期控制
     * @param context
     * @param path
     * @param imageView
     * @param roundradius 圆角大小（>0）
     */
    @SuppressWarnings("unchecked")
    public void LoadContextRoundBitmap(Context context,String path,ImageView imageView,int roundradius){
        if(roundradius<0){
            with(context)
                    .load(path)
                    .apply(RequestOptionsUtils.newRequestOtions(
                            newInstanceTransformation(TRANSFORMATION_FLAG_ROUND, context, -1.0f)))
                    .into(imageView);
        }else{
            with(context)
                    .load(path)
                    .apply(RequestOptionsUtils.newRequestOtions(
                            newInstanceTransformation(TRANSFORMATION_FLAG_ROUND, context, Float.valueOf(String.valueOf(roundradius)))))
                    .into(imageView);
        }
    }
    /**
     * Glide请求图片设置圆角，会受到android.app.Fragment生命周期控制
     * @param fragment
     * @param path
     * @param imageView
     * @param roundradius
     */
    @SuppressWarnings("unchecked")
    public void LoadfragmentRoundBitmap(android.app.Fragment fragment,String path,ImageView imageView,int roundradius){
       if(roundradius<0){
            with(fragment)
                    .load(path)
                    .apply(RequestOptionsUtils.newRequestOtions(
                            newInstanceTransformation(TRANSFORMATION_FLAG_ROUND, fragment.getActivity(), -1.0f)))
                    .into(imageView);
        }else{
            with(fragment)
                    .load(path)
                    .apply(RequestOptionsUtils.newRequestOtions(
                            newInstanceTransformation(TRANSFORMATION_FLAG_ROUND, fragment.getActivity(), Float.valueOf(String.valueOf(roundradius)))))
                    .into(imageView);
        }
    }
    /**
     * Glide请求图片设置圆角，会受到android.support.v4.app.Fragment生命周期控制
     * @param fragment
     * @param path
     * @param imageView
     * @param roundradius
     */
    @SuppressWarnings("unchecked")
    public void LoadSupportv4FragmentRoundBitmap(android.support.v4.app.Fragment fragment,String path,ImageView imageView,int roundradius){
        if(roundradius<0){
            with(fragment)
                    .load(path)
                    .apply(RequestOptionsUtils.newRequestOtions(
                            newInstanceTransformation(TRANSFORMATION_FLAG_ROUND, fragment.getActivity(), -1.0f)))
                    .into(imageView);
        }else{
            with(fragment)
                    .load(path)
                    .apply(RequestOptionsUtils.newRequestOtions(
                            newInstanceTransformation(TRANSFORMATION_FLAG_ROUND, fragment.getActivity(), Float.valueOf(String.valueOf(roundradius)))))
                    .into(imageView);
        }
    }
    //-------------------------------------------------
    /**
     * Glide 加载模糊图片
     * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
     * <BR/>使用activity 会受到Activity生命周期控制
     * <BR/>使用FragmentActivity 会受到FragmentActivity生命周期控制
     * @param context
     * @param path
     * @param imageView
     */
    @SuppressWarnings("unchecked")
    public void LoadContextBlurBitmap(Context context,String path,ImageView imageView){
        with(context)
                .load(path)
                .apply(RequestOptionsUtils.newRequestOtions(
                        newInstanceTransformation(TRANSFORMATION_FLAG_BLUR, context, -1.0f)))
                .into(imageView);
    }
    /**
     * Glide 加载模糊图片 会受到Fragment生命周期控制
     * @param fragment
     * @param path
     * @param imageView
     */
    @SuppressWarnings("unchecked")
    public void LoadFragmentBlurBitmap(android.app.Fragment fragment,String path,ImageView imageView){
        with(fragment)
                .load(path)
                .apply(RequestOptionsUtils.newRequestOtions(
                        newInstanceTransformation(TRANSFORMATION_FLAG_BLUR, fragment.getActivity(), -1.0f)))
                .into(imageView);
    }
    /**
     * Glide 加载模糊图片 会受到support.v4.app.Fragment生命周期控制
     * @param fragment
     * @param path
     * @param imageView
     */
    @SuppressWarnings("unchecked")
    public void LoadSupportv4FragmentBlurBitmap(android.support.v4.app.Fragment fragment,String path,ImageView imageView){
        with(fragment)
                .load(path)
                .apply(RequestOptionsUtils.newRequestOtions(
                        newInstanceTransformation(TRANSFORMATION_FLAG_BLUR, fragment.getActivity(), -1.0f)))
                .into(imageView);
    }
    //---------------------------------------------------------
    /**
     * 旋转图片
     *使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
     * <BR/>使用activity 会受到Activity生命周期控制
     * <BR/>使用FragmentActivity 会受到FragmentActivity生命周期控制
     * @param context
     * @param path
     * @param imageView
     * @param rotateRotationAngle 旋转角度
     */
    @SuppressWarnings("unchecked")
    public void LoadContextRotateBitmap(Context context,String path,ImageView imageView,Float rotateRotationAngle){
        with(context)
                .load(path)
                .apply(RequestOptionsUtils.newRequestOtions(
                        newInstanceTransformation(TRANSFORMATION_FLAG_ROTATE, context, rotateRotationAngle)))
                .into(imageView);
    }
    /**
     * Glide 加载旋转图片 会受到Fragment生命周期控制
     * @param fragment
     * @param path
     * @param imageView
     * @param rotateRotationAngle
     */
    @SuppressWarnings("unchecked")
    public void LoadFragmentRotateBitmap(android.app.Fragment fragment,String path,ImageView imageView,Float rotateRotationAngle){
        with(fragment)
                .load(path)
                .apply(RequestOptionsUtils.newRequestOtions(
                        newInstanceTransformation(TRANSFORMATION_FLAG_ROTATE, fragment.getActivity(), rotateRotationAngle)))
                .into(imageView);
    }
    /**
     * Glide 加载旋转图片 会受到support.v4.app.Fragment生命周期控制
     * @param fragment
     * @param path
     * @param imageView
     * @param rotateRotationAngle
     */
    @SuppressWarnings("unchecked")
    public void LoadSupportv4FragmentRotateBitmap(android.support.v4.app.Fragment fragment, String path,
                                                  ImageView imageView, Float rotateRotationAngle){
                with(fragment)
                .load(path)
                .apply(RequestOptionsUtils.newRequestOtions(
                        newInstanceTransformation(TRANSFORMATION_FLAG_ROTATE, fragment.getActivity(), rotateRotationAngle)))
                .into(imageView);
    }




    /***
     * 获取BitmapTransformation对象
     * @param object_flag
     * @param context
     * @param data
     * @return
     */
    private  BitmapTransformation newInstanceTransformation(String  object_flag, Context context, Float data){
        switch (object_flag){
            case TRANSFORMATION_FLAG_ROTATE:
                return new RotateTransformation(context, data);
            case TRANSFORMATION_FLAG_BLUR:
                return new BlurTransformation(context);
            case TRANSFORMATION_FLAG_ROUND:
                return new GlideRoundTransform(context, Integer.parseInt(String.valueOf(data)));
            case TRANSFORMATION_FLAG_CIRCLE:
                return new GlideCircleTransform(context);
            default:
                return null;
        }
     }

    public final static String TRANSFORMATION_FLAG_ROTATE = "RotateTransformation";
    public final static String TRANSFORMATION_FLAG_BLUR = "BlurTransformation";
    public final static String TRANSFORMATION_FLAG_ROUND = "GlideRoundTransform";
    public final static String TRANSFORMATION_FLAG_CIRCLE = "GlideCircleTransform";
}
