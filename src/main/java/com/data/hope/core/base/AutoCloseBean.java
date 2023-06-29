
package com.data.hope.core.base;

import javax.management.timer.Timer;
import java.io.Closeable;
import java.io.IOException;
import java.util.Date;

/**
 * 提供一个可超时自动关闭的计时器，超时时间通过 timeoutMillis() 方法设置。
 * 通过refresh()方法可刷新超时时间
 * 超时后Timer自动调用 close方法
 */
public abstract class AutoCloseBean extends Timer implements Closeable {

    public AutoCloseBean() {
        start();
        initTimer();
    }

    private void initTimer() {
        addNotificationListener((notification, handback) -> {
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, null, null);
        addNewNotification();
    }

    /**
     * @return 超时关闭的时间，单位：毫秒
     */
    public abstract int timeoutMillis();

    /**
     * 刷新关闭时间
     */
    public void refresh() {
        removeAllNotifications();
        addNewNotification();
    }

    private void addNewNotification() {
        addNotification("close", "time to close", this, new Date(System.currentTimeMillis() + timeoutMillis()));
    }


}
