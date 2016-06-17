package org.wch.eventbus;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.EventStream;
import org.wch.eventbus.actor.FinishActor;
import org.wch.eventbus.actor.PersistenceActor;
import org.wch.eventbus.actor.SubscribeActor;
import org.wch.eventbus.actor.TopicActor;
import org.wch.eventbus.event.Event;
import org.wch.eventbus.event.FinishEvent;
import org.wch.eventbus.repository.EventRepository;
import org.wch.eventbus.subscribe.Subscribe;

/**
 * Created by weichunhe on 2016/6/17.
 */
public class EventBus {

    private static final ActorSystem SYSTEM = ActorSystem.create("mySystem");
    private static final EventStream EVENT_STREAM = SYSTEM.eventStream();

    static {
        //监听结束事件
        EVENT_STREAM.subscribe(SYSTEM.actorOf(Props.create(FinishActor.class)), FinishEvent.class);
        //持久化所有事件
        EVENT_STREAM.subscribe(SYSTEM.actorOf(Props.create(PersistenceActor.class)), Event.class);
    }

    public static void publish(Object o) {
        EVENT_STREAM.publish(o);
    }

    public static <R> R syncPublish(Event e) {
        EVENT_STREAM.publish(e);
        EventRepository.suspend(e.getUUID());
        return EventRepository.getResult(e.getUUID());
    }

    public static void subscribe(Class<? extends Actor> listener, Class message) {
        ActorRef ref = SYSTEM.actorOf(TopicActor.props(listener));
//        ActorRef ref = SYSTEM.actorOf(Props.create(listener));
        EVENT_STREAM.subscribe(ref, message);
    }

    public static void subscribe(Subscribe actor, Class message) {
        ActorRef ref = SYSTEM.actorOf(SubscribeActor.props(actor));
        EVENT_STREAM.subscribe(ref, message);
    }

}
