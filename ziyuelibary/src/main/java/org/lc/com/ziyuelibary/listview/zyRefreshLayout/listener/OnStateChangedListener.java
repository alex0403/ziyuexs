package org.lc.com.ziyuelibary.listview.zyRefreshLayout.listener;


//import com.scwang.smartrefresh.layout.api.RefreshLayout;
//import com.scwang.smartrefresh.layout.constant.RefreshState;

import org.lc.com.ziyuelibary.listview.zyRefreshLayout.api.RefreshLayout;
import org.lc.com.ziyuelibary.listview.zyRefreshLayout.constant.RefreshState;

/**
 * 刷新状态改变监听器
 * Created by SCWANG on 2017/5/26.
 */

public interface OnStateChangedListener {
    /**
     * 状态改变事件 {@link RefreshState}
     * @param refreshLayout RefreshLayout
     * @param oldState 改变之前的状态
     * @param newState 改变之后的状态
     */
    void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState);
}
