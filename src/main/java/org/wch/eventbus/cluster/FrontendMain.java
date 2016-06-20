package org.wch.eventbus.cluster;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by weichunhe on 2016/6/20.
 */
public class FrontendMain {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("ClusterSystem");
        ActorRef actorRef = system.actorOf(Props.create(TransformationFrontend.class));
        actorRef.tell(new TransformationMessages.TransformationJob("wch"), actorRef);
    }
}
