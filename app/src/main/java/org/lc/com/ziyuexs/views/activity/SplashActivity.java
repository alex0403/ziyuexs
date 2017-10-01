package org.lc.com.ziyuexs.views.activity;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.lc.com.ziyuelibary.utils.AppUtils;
import org.lc.com.ziyuelibary.utils.LogUtils;
import org.lc.com.ziyuelibary.utils.SharedPreferencesUtil;
import org.lc.com.ziyuexs.Base.BaseActivity;
import org.lc.com.ziyuexs.R;
import org.lc.com.ziyuexs.component.AppComponent;
import org.lc.com.ziyuexs.module.glide.GlideApp;
import org.lc.com.ziyuexs.module.glide.GlideRequest;
import org.lc.com.ziyuexs.utils.ActHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017-9-10.
 * 该页面图片需要做成广告页，业务逻辑：1. 首次显示默认图片，每次加载，通过网络获取最新的广告图片并保存到缓存。供下次启动调用。
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_enter)Button enter;
    @BindView(R.id.splash_pic)ImageView mPic;
    @BindArray(R.array.url_ex)String[] picData;

    private ThreadLocal<Integer> obj = new ThreadLocal<>();//防止并发触发事件引起二次进入风险

    public  static final int INIT_PIC_BACKGROUND_DONE = 0x10001;
    public  static final int INIT_PIC_FOREGROUND_DONE = 0x10002;

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what == 0x10001){
                LogUtils.i("后台图片下载成功...");
                List list= getAdsByCache();
                commitAds(list, picData[list.size()+1]);
             }else if(msg.what == 0x10002){
                LogUtils.i("第一张图片下载完成...");
                commitAds(getAdsByCache(), picData[0]);
                SharedPreferencesUtil.getInstance().putBoolean("is_first", false);
            }
        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {
        obj.set(1);
        boolean isFirst = SharedPreferencesUtil.getInstance().getBoolean("is_first");
        //isFirst = true;
        if(isFirst){
            //GlideApp.with(this).load(R.mipmap.splash).skipMemoryCache(true).override(AppUtils.getScreenWidth(), AppUtils.getScreenHeight()).into(mPic);
            loadPicByComm(R.mipmap.splash, true);
            cachePicByDisk(new ArrayList(), 0, INIT_PIC_FOREGROUND_DONE);
        }


        if(!isFirst)
            showPic();
    }

    @Override
    public void initView() {
        mHandler.postDelayed(mDelayCallback = new Runnable() {
            @Override
            public void run() {
                startAct();
            }
        }, 3000);
    }

    @OnClick(R.id.splash_enter)
    public void enterBtnClick(Button view){
        startAct();
    }


    private void showPic(){
        List list = getAdsByCache();
        //clear(list);
        int index = list.size();
        String path = picData[0];
        if(index > 0)  path = (String) list.get(getRondom(index, 0));
        loadPic(list, path, index);
    }

    private void clear(List list){
        ArrayPool array =  Glide.get(this).getArrayPool();
        BitmapPool pool = Glide.get(this).getBitmapPool();
        list.clear();
        commitAds(list, null);
        Glide.get(getApplication()).clearMemory();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(getApplication()).clearDiskCache();
            }
        });
    }

    /***
     * 获取随机数
     * @param max
     * @param min
     * @return
     */
    private static int getRondom(int max, int min){
        Random random = new Random();
        int size = random.nextInt(max)%(max-min+1) + 0;
        LogUtils.i("获取随机数：max:"+max+";min:"+min+";size:"+size);
        return size;
    }

    /***
     * 加载图片
     * @param path
     * @param index
     */
    protected void loadPic(List list, String path, int index){
        /**获取缓存图片加载*/
        //GlideApp.with(this).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).transition(DrawableTransitionOptions.withCrossFade()).override(AppUtils.getScreenWidth(), AppUtils.getScreenHeight()).into(mPic);
        loadPicByComm(path, false);
        //后台下载一张图片
        if(index+1 < picData.length) {
            cachePicByDisk(list, index+1, INIT_PIC_BACKGROUND_DONE);
        }
    }

    /**
     * 缓存远程图片到二级缓存
     * @param index
     * @param what
     */
    protected  void cachePicByDisk(final List list , final int index, final int what){
        GlideApp.with(this).load(picData[index]).diskCacheStrategy(DiskCacheStrategy.ALL).override(AppUtils.getScreenWidth(), AppUtils.getScreenHeight())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if(!list.contains(picData[index])){
                    mHandler.sendMessage(Message.obtain(mHandler, what));
                }
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                LogUtils.e("图片加载失败...");
            }
        });
    }

    protected  void loadPicByComm(Object path, boolean isCache){
        GlideRequest<Drawable> request =  GlideApp.with(this).load(path);
        if(isCache){
            request.diskCacheStrategy(DiskCacheStrategy.ALL);
        }else{
            request.skipMemoryCache(true);
        }
        request.override(AppUtils.getScreenWidth(), AppUtils.getScreenHeight())
                .transition(DrawableTransitionOptions.withCrossFade()).into(mPic);
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
//        DaggerUniversalComponent.builder()
//                .appComponent(appComponent)
//                .build()
//                .inject(this);
    }

    /**
     * 获得广告列表
     * @return
     */
    private synchronized List getAdsByCache(){
        List list = SharedPreferencesUtil.getInstance().getObject("ads_list", List.class);
        if(list ==null) list = new ArrayList();
        return list;
    }

    /**
     * 更新广告列表
     * @param list
     * @param path
     */
    private synchronized void commitAds(List list, String path){
        if(path != null && !path.equals(""))
            list.add(path);
        SharedPreferencesUtil.getInstance().putObject("ads_list", list);
    }

    private void startAct(){
        if(obj.get().equals(null) || (int)obj.get() != 0){
            obj.set(0);
            boolean isWellcome = SharedPreferencesUtil.getInstance().getBoolean("is_wellcome");
            Class clazz  = MainActivity.class;
            if(isWellcome) clazz = GuideActivity.class;
            ActHelper.startActivity(mActivity, clazz, true);
        }
    }

    public void onDestroy(){
        super.onDestroy();
        mHandler.removeCallbacks(mDelayCallback);
    }
    //延时跳转线程
    private Runnable mDelayCallback;

}
