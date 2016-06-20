package org.wch.eventbus.cluster;

import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by weichunhe on 2016/6/20.
 */
public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("ClusterSystem");
        system.actorOf(Props.create(TransformationBackend.class));
    }
}
