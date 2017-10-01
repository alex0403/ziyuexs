package org.lc.com.ddzw.views.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.ddzw.com.ddzw.R;
import org.lc.com.ddzw.widget.AgentWeb.AgentWeb;


/**
 * Created by dell on 2017/5/6.
 */

public  abstract  class BaseFragment extends Fragment {

    Context mContext;

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    Activity mActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.frag_main, null);
        mRelativeLayout = view.findViewById(R.id.container);

        return view;
    }
    AgentWeb mAgentWeb = null;
    private RelativeLayout mRelativeLayout;

    public void onResume(){
        super.onResume();
        mAgentWeb = AgentWeb.with(mActivity)//传入Activity
                .setAgentWebParent(mRelativeLayout, new RelativeLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                // .setReceivedTitleCallback(mCallback) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go(getUrl());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
    }

    public abstract String getUrl();


}
