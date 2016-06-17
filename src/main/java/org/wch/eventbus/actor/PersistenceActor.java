package org.wch.eventbus.actor;

import akka.actor.UntypedActor;

/**
 * Created by weichunhe on 2016/6/17.
 */
public class PersistenceActor extends UntypedActor {
    @Override
    public void onReceive(Object o) throws Exception {
        System.out.println("repository event" + o);
    }
}
