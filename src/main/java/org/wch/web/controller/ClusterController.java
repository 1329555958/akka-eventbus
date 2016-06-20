package org.wch.web.controller;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wch.eventbus.cluster.TransformationFrontend;
import org.wch.eventbus.cluster.TransformationMessages;

import javax.annotation.PostConstruct;

/**
 * Created by weichunhe on 2016/6/20.
 */
@RestController
@RequestMapping("/cluster")
public class ClusterController {
    private ActorSystem system = null;
    private ActorRef actorRef = null;
    private int count = 0;

    @RequestMapping("/transform")
    public Object transform(String text) {
        actorRef.tell(new TransformationMessages.TransformationJob(text + count++), actorRef);
        return null;
    }

    @PostConstruct
    public void init() {
        system = ActorSystem.create("ClusterSystem");
        actorRef = system.actorOf(Props.create(TransformationFrontend.class), "frontend");
        System.out.println(actorRef.path().toStringWithoutAddress());
    }
}
