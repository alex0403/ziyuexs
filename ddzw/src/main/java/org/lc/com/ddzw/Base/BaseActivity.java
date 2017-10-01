package org.lc.com.ddzw.Base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import org.lc.com.ddzw.component.AppComponent;
import org.lc.com.ddzw.dialog.CustomDialog;
import org.lc.com.ziyuelibary.statusbar.ImmersionBar;
import org.lc.com.ziyuelibary.utils.SharedPreferencesUtil;
import org.lc.com.ziyuelibary.utils.systems.AppManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-9-13.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseACInterface {

    protected int mStatusBarColor = 0;
    protected View mStatusBarView;
    //protected Toolbar mCommonToolbar;
    private boolean mNowMode;//当前皮肤模式

    protected Activity mActivity;
    protected Context mContext;
    private CustomDialog dialog;//进度条
    protected ImmersionBar mImmersionBar;
    private Unbinder mViewBind;


    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);


        mContext  = this;
        setContentView(getLayout());
        mViewBind = ButterKnife.bind(mActivity = this);//必须放在布局加载之后，否则报错
        initStateBar();
        setupActivityComponent(DDZWApplication.getsInstance().getAppComponent());
        AppManager.getAppManager().addActivity(this);
        init();
        mNowMode = SharedPreferencesUtil.getInstance().getBoolean(Constant.Theme.ISNIGHT);
    }

    public void init(){
        initData();
        initView();
        addRegisterReceiver();
    }

    protected abstract  void setupActivityComponent(AppComponent appComponent);



    protected void initStateBar(){
       /* if (mStatusBarColor == 0) {
            mStatusBarView = StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.transparent));
        } else if (mStatusBarColor != -1) {
            mStatusBarView = StatusBarCompat.compat(this, mStatusBarColor);
        }
        transparent19and20();*/

        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    protected void transparent19and20() {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }


    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void toolbarSetElevation(float elevation) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mCommonToolbar.setElevation(elevation);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SharedPreferencesUtil.getInstance().getBoolean(Constant.Theme.ISNIGHT, false) != mNowMode) {
            if (SharedPreferencesUtil.getInstance().getBoolean(Constant.Theme.ISNIGHT, false)) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            recreate();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
        removeReceiver();
        AppManager.getAppManager().removeActivity(this);
        if(mImmersionBar != null) mImmersionBar.destroy();
        if(mViewBind != null) mViewBind.unbind();
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public CustomDialog getDialog() {
        if (dialog == null) {
            dialog = CustomDialog.instance(this);
            dialog.setCancelable(true);
        }
        return dialog;
    }

    public void hideDialog() {
        if (dialog != null)
            dialog.hide();
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    public void showDialog() {
        getDialog().show();
    }

    public  void addRegisterReceiver(){};

    public  void removeReceiver(){};

    private InputMethodManager imm;
}
