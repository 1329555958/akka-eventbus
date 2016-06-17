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
        if (o instanceof FinishEvent) {
            FinishEvent event = (FinishEvent) o;
            System.out.println(event.getEvent().getUUID() + "-事件处理结束:" + event.getEvent() + "->" + event.getResult());
            EventRepository.resume(event.getEvent().getUUID(), event.getResult());
        } else {
            unhandled(o);
        }

    }
}
