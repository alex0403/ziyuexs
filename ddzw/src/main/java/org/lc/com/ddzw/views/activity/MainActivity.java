package org.lc.com.ddzw.views.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;

import com.wakehao.bar.BottomNavigationBar;
import com.wakehao.bar.BottomNavigationItemWithDot;

import org.lc.com.ddzw.Base.BaseActivity;
import org.ddzw.com.ddzw.R;
import org.lc.com.ddzw.component.AppComponent;
import org.lc.com.ddzw.views.fragment.AddFragment;
import org.lc.com.ddzw.views.fragment.BaseFragment;
import org.lc.com.ddzw.views.fragment.DiscoveryFragment;
import org.lc.com.ddzw.views.fragment.MainFragment;
import org.lc.com.ddzw.views.fragment.MineFragment;
import org.lc.com.ddzw.views.fragment.NoteFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;



/**
 * Created by Administrator on 2017-9-18.
 */

public class MainActivity extends BaseActivity{

    @BindView(R.id.navigation)
    com.wakehao.bar.BottomNavigationBar mBottomNavigationView;

    private FragmentManager mFragmentManager;
    private List<BaseFragment> mList = new ArrayList<>();
    BaseFragment mainFragment, mDiscoveryFragment, mNoteFragment, mineFragment, mAddFragment;
    private Fragment mCurrentFragment;
    private Fragment mPreFragment;


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        mList.add(new MainFragment());
        mList.add(new DiscoveryFragment());
        mList.add(new AddFragment());
        mList.add(new NoteFragment());
        mList.add(new MineFragment());
    }
    protected void initStateBar(){
        super.initStateBar();
        mImmersionBar.statusBarColor(R.color.stutas_bar_background).init();
    }

    @Override
    public void initView() {
        mFragmentManager = getFragmentManager();
        initBottomNavtion();
        //initDefaultFragment();
    }

    private void initBottomNavtion(){
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationBar.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull BottomNavigationItemWithDot item, int selectedPosition) {
                if (selectedPosition == 3) {

//                    startActivityForResult(new Intent(MainActivity.this,LoginActivity.class),1);
                    //用户切换item
                    mBottomNavigationView.setItemSelected(3, true);
                    //返回false表示不响应点击事件
                    return false;
                } else return true;
            }

            @Override
            public void onNavigationItemSelectedAgain(@NonNull BottomNavigationItemWithDot item, int reSelectedPosition) {

            }
        });
        /*disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {

            @SuppressLint("NewApi")
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
                mPreFragment = mCurrentFragment;
                hideFragment(mTransaction);
                switch (item.getItemId()) {
                    case R.id.menu_index:
                        showFragment(mTransaction, mainFragment, "mainFragment", 0);
                        return true;
                    case R.id.menu_discover:
                        showFragment(mTransaction, mDiscoveryFragment, "mDiscoveryFragment", 1);
                        return true;
                    case R.id.menu_add:
                        showFragment(mTransaction, mAddFragment, "mAddFragment", 2);
                        return true;
                    case R.id.menu_note:
                        showFragment(mTransaction, mNoteFragment, "mNoteFragment", 3);
                        return true;
                    case R.id.menu_mine:
                        showFragment(mTransaction, mineFragment, "mineFragment", 4);
                        return true;
                }
                return false;
            }

        });*/
    }

    /***
     * 隐藏fragment
     * @param transaction
     */
    /*private void hideFragment( FragmentTransaction transaction){
        if(mainFragment != null ) transaction.hide(mainFragment);
        if(mDiscoveryFragment != null )transaction.hide(mDiscoveryFragment);
        if(mAddFragment != null )transaction.hide(mAddFragment);
        if(mNoteFragment != null )transaction.hide(mNoteFragment);
        if(mineFragment != null ) transaction.hide(mineFragment);
    }*/

    /***
     * 显示Fragment
     * @param **transaction
     * @param **fragment
     * @param **tag
     * @param **index
     */
    /*private void showFragment(FragmentTransaction transaction, BaseFragment fragment, String tag, int index){
        if(fragment ==null){
            transaction.add(R.id.subcontainer, fragment = mList.get(index), tag);
        }else{
            transaction.show(fragment);
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }*/

    /*private void initDefaultFragment(){
        FragmentTransaction transaction =  mFragmentManager.beginTransaction();
        transaction.add(R.id.subcontainer, mainFragment = mList.get(0));
        transaction.commit();
    }*/

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }


    protected void initListener() {

    }

    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                 item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }
}
