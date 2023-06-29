
package com.data.hope.core.gossip;

import javax.management.timer.Timer;
import java.util.Date;
import java.util.Objects;

public class Gossiper extends Timer {

    protected String host;

    protected int port;

    protected String jsonData;

    protected final GossiperManager manager;

    protected long timeout;

    public Gossiper(GossiperManager manager, String host, int port, long timeout) {
        this.host = host;
        this.port = port;
        this.manager = manager;
        this.timeout = timeout;
    }

    public Gossiper(Gossiper gossiper) {
        this.host = gossiper.host;
        this.port = gossiper.port;
        this.timeout = gossiper.timeout;
        this.manager = gossiper.manager;
        this.jsonData = gossiper.jsonData;
    }

    @Override
    public synchronized void start() {
        startTimer();
        super.start();
    }

    public void startTimer() {
        addNotificationListener(manager, null, this);
    }

    public void resetHeartbeat() {
        removeAllNotifications();
        addNotification("", "", null, new Date(System.currentTimeMillis() + timeout));
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public GossiperManager getManager() {
        return manager;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gossiper that = (Gossiper) o;
        return port == that.port && host.equals(that.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }
}