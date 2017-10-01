package org.lc.com.ziyuelibary.listview.zyRefreshLayout.utils;

public class DelayedRunable implements Runnable {
    public long delayMillis;
    public Runnable runnable = null;
    public DelayedRunable(Runnable runnable) {
        this.runnable = runnable;
    }
    public DelayedRunable(Runnable runnable, long delayMillis) {
        this.runnable = runnable;
        this.delayMillis = delayMillis;
    }
    @Override
    public void run() {
        if (runnable != null) {
            runnable.run();
            runnable = null;
        }
    }
}