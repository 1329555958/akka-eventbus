package org.wch.eventbus.actor;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;
import org.wch.eventbus.EventBus;
import org.wch.eventbus.event.Event;
import org.wch.eventbus.event.FinishEvent;
import org.wch.eventbus.subscribe.Subscribe;

/**
 * Created by weichunhe on 2016/6/16.
 */
public class SubscribeActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private final Subscribe actor;

    public SubscribeActor(Subscribe actor) {
        this.actor = actor;
    }

    @Override
    public void onReceive(Object o) throws Exception {
        finish((Event) o, actor.apply(o));
    }


    /**
     * 实现一种构造模式，这是推荐的最佳实践
     *
     * @param actorClass
     * @return
     */
    public static Props props(Class<Subscribe> actorClass) {
        try {
            Subscribe actor = actorClass.newInstance();
            return Props.create(new Creator<SubscribeActor>() {
                public SubscribeActor create() throws Exception {
                    return new SubscribeActor(actor);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private void finish(Event param, Object result) {
        EventBus.publish(new FinishEvent<Event, Object>(param, result));
    }
}
