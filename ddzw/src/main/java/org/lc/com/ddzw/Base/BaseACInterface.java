package org.lc.com.ddzw.Base;

/**
 * Created by Administrator on 2017-9-18.
 */

public interface BaseACInterface {
    /***
     * 加载布局
     * @return
     */
    public int getLayout();

    /**
     * 初始化数据
     */
    public void initData();

    /**
     * 初始化视图
     */
    public void initView();
}
