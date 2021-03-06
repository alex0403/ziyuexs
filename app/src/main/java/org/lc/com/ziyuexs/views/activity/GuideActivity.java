package org.lc.com.ziyuexs.views.activity;

import android.content.Intent;

import org.lc.com.ziyuelibary.banner.BGABanner;
import org.lc.com.ziyuelibary.utils.SharedPreferencesUtil;
import org.lc.com.ziyuexs.Base.BaseActivity;
import org.lc.com.ziyuexs.R;
import org.lc.com.ziyuexs.component.AppComponent;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-9-21.
 */

public class GuideActivity extends BaseActivity {

    @BindView(R.id.banner_guide_background) BGABanner mBackgroundBanner;
    @BindView(R.id.banner_guide_foreground) BGABanner mForegroundBanner;

    @Override
    public int getLayout() {
        return R.layout.activity_guide;
    }

    @Override
    public void initData() {
        mBackgroundBanner.setData(R.drawable.uoko_guide_background_1, R.drawable.uoko_guide_background_2, R.drawable.uoko_guide_background_3);
        mForegroundBanner.setData(R.drawable.uoko_guide_foreground_1, R.drawable.uoko_guide_foreground_2, R.drawable.uoko_guide_foreground_3);
    }

    @Override
    public void initView() {

        setGuideListener();
    }

    protected void initStateBar(){
        super.initStateBar();
//        mImmersionBar.statusBarView(R.id.top_view).init();
    }
    /**
     * 设置进入按钮和跳过按钮控件资源 id 及其点击事件
     * 如果进入按钮和跳过按钮有一个不存在的话就传 0
     * 在 BGABanner 里已经帮开发者处理了防止重复点击事件
     * 在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏
     */
    private void setGuideListener(){
        mForegroundBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                SharedPreferencesUtil.getInstance().putBoolean("is_wellcome", false);
                finish();
            }
        });
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    public void onRresume(){
        super.onResume();
        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
        mBackgroundBanner.setBackgroundResource(android.R.color.white);
    }
}
