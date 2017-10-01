package org.lc.com.ddzw.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017-9-10.
 */


 public  class ActHelper {
        /**
         * 普通启动Activity
         * @param context
         * @param intent
         */
        public static void startActivity(Context context, Intent intent){
            context.startActivity(intent);
        }

        /***
         * 启动并关闭当前Activity
         * @param context
         * @param intent
         * @param isFinish
         */
        public static void startActivity(Activity context, Intent intent, boolean isFinish){
            context.startActivity(intent);
            if(isFinish) context.finish();
        }

        public static void startActivity(Activity act, Class _target, boolean isFinish){
            Intent intent = new Intent(act, _target);
            act.startActivity(intent);
            if(isFinish) act.finish();
        }

        /**
         * 启动并带返回结果
         * @param context
         * @param intent
         * @param flag
         */
        public static void startActivityByResult(Activity context, Intent intent, int flag){
            context.startActivityForResult(intent, flag);
        }
    }

