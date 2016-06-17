package org.wch.eventbus.actor;

import akka.actor.UntypedActor;
import org.wch.eventbus.event.Event;

/**
 * Created by weichunhe on 2016/6/17.
 */
public class PersistenceActor extends UntypedActor {
    @Override
    public void onReceive(Object o) throws Exception {
        if (o instanceof Event) {
            System.out.println("持久化事件-" + ((Event) o).getUUID());
        }
    }
}
