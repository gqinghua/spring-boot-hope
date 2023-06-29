
package com.data.hope.core.gossip;


import javax.management.Notification;
import javax.management.NotificationListener;
import java.util.Random;

public class SyncGossiperStatus implements NotificationListener {

    private final Random random = new Random();

    @Override
    public void handleNotification(Notification notification, Object handback) {

        GossiperManager manager = (GossiperManager) handback;

        synchronized (manager.getRemoteGossipers()) {

            if (manager.getShutdown().get()) {
                return;
            }

            if (manager.getRemoteGossipers() == null || manager.getRemoteGossipers().size() == 0) {
                manager.resetTimer();
                return;
            }
            Gossiper gossiper = randomGossiper(manager);
            
        }

    }


    private Gossiper randomGossiper(GossiperManager manager) {
        if (manager.getRemoteGossipers() == null || manager.getRemoteGossipers().size() == 0) {
            return null;
        }
        int randomIndex = random.nextInt(manager.getRemoteGossipers().size());
        return manager.getRemoteGossipers().get(randomIndex);
    }

}
