package org.lc.com.ziyuexs.views.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.lc.com.ziyuelibary.listview.headerstyle.MaterialHeader;
import org.lc.com.ziyuelibary.listview.zyRefreshLayout.api.RefreshLayout;
import org.lc.com.ziyuelibary.listview.zyRefreshLayout.constant.SpinnerStyle;
import org.lc.com.ziyuelibary.listview.zyRefreshLayout.footer.BallPulseFooter;
import org.lc.com.ziyuelibary.utils.ToastUtils;
import org.lc.com.ziyuexs.Base.BaseActivity;
import org.lc.com.ziyuexs.Bean.RankingList;
import org.lc.com.ziyuexs.R;
import org.lc.com.ziyuexs.component.AppComponent;
import org.lc.com.ziyuexs.network.ZiyueApi;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017-9-18.
 */

public class MainActivity extends BaseActivity{

    @BindView(R.id.navigation)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.toolbar)Toolbar toolbar;

    @BindView(R.id.refreshLayout)RefreshLayout mRefreshLayout;


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }
    protected void initStateBar(){
        super.initStateBar();
        mImmersionBar.titleBar(toolbar).statusBarColor(R.color.gray_white).init();
//        mImmersionBar.hideBar(BarHide.FLAG_HIDE_STATUS_BAR).init();//隐藏状态栏
//        mImmersionBar.hideBar(BarHide.FLAG_SHOW_BAR).init();//显示状态栏
//        mImmersionBar.statusBarColor(R.color.transparent).init();
    }

    @Override
    public void initView() {
        //toolbar.setBackgroundColor(getResources().getColor(R.color.gray_white));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();


//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
        initListener();
        mRefreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        mRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }


    protected void initListener() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                showDialog();
                return false;
            }
        });
        //toolbar返回按钮监听
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.main_add_book)
    protected  void onBookAdd(Button btn){
        ToastUtils.showToast("您点击了添加按钮");
        ZiyueApi.getInstance(new OkHttpClient()).getRanking().enqueue(new Callback<RankingList>() {
            @Override
            public void onResponse(Call<RankingList> call, Response<RankingList> response) {
                RankingList list = response.body();
                ToastUtils.showSingleToast("male.size="+list.male.size()+":female="+list.female.size());
            }

            @Override
            public void onFailure(Call<RankingList> call, Throwable t) {

            }
        });

    }
}
