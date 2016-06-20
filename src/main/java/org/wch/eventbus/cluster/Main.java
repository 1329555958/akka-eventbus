package org.wch.eventbus.cluster;

import akka.actor.ActorSystem;

/**
 * Created by weichunhe on 2016/6/20.
 */
public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("ClusterSystem");
    }
}
