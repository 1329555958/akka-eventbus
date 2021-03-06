package org.wch.eventbus.cluster;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.List;

import static org.wch.eventbus.cluster.TransformationMessages.*;

public class TransformationFrontend extends UntypedActor {

    List<ActorRef> backends = new ArrayList<ActorRef>();
    int jobCounter = 0;

    @Override
    public void onReceive(Object message) {
        System.out.println("receive message:" + message);
        if ((message instanceof TransformationJob) && backends.isEmpty()) {
            TransformationJob job = (TransformationJob) message;
            getSender().tell(
                    new JobFailed("Service unavailable, try again later", job),
                    getSender());
            System.out.println("job faild");

        } else if (message instanceof TransformationJob) {
            TransformationJob job = (TransformationJob) message;
            jobCounter++;
            backends.get(jobCounter % backends.size())
                    .forward(job, getContext());

        } else if (message.equals(BACKEND_REGISTRATION)) {
            System.out.println("backend found");
            getContext().watch(getSender());
            backends.add(getSender());

        } else if (message instanceof Terminated) {
            Terminated terminated = (Terminated) message;
            backends.remove(terminated.getActor());
        } else {

            unhandled(message);
        }
    }

}
