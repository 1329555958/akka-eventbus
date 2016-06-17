package org.wch.eventbus.actor;

import akka.actor.UntypedActor;
import org.wch.eventbus.event.FinishEvent;
import org.wch.eventbus.repository.EventRepository;

/**
 * Created by weichunhe on 2016/6/17.
 */
public class FinishActor extends UntypedActor {
    @Override
    public void onReceive(Object o) throws Exception {
        FinishEvent event = (FinishEvent) o;

        EventRepository.resume(event.getEvent().getUUID(), event.getResult());
    }
}
