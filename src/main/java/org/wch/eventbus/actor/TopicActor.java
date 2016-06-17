package org.wch.eventbus.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

/**
 * Created by weichunhe on 2016/6/17.
 */
public class TopicActor extends UntypedActor {

    private Class workActorClass;
    private int works = 3;
    private ActorRef[] workActors;

    private int next = 0;

    private ActorRef getNextActor() {
        int cursor = next % works;
        next++;
        if (next > 10000) {
            next = next % works;
        }
        return workActors[cursor];
    }

    public TopicActor(Class workActorClass) {
        this(3, workActorClass);
    }

    public TopicActor(int works, Class workActorClass) {
        this.works = works;
        this.workActorClass = workActorClass;
        workActors = new ActorRef[works];
        for (int i = 0; i < works; i++) {
            workActors[i] = getContext().actorOf(SubscribeActor.props(workActorClass));
        }
    }

    public static Props props(final Class workActorClass) {
        return Props.create(new Creator<TopicActor>() {
            public TopicActor create() throws Exception {
                return new TopicActor(workActorClass);
            }
        });
    }

    @Override
    public void onReceive(Object o) throws Exception {
        getNextActor().tell(o, getSender());
    }
}
