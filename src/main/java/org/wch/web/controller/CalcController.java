package org.wch.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wch.eventbus.EventBus;
import org.wch.web.event.AddEvent;
import org.wch.web.subscribe.AddSubscribe;

import javax.annotation.PostConstruct;

/**
 * Created by weichunhe on 2016/6/17.
 */
@RestController
@RequestMapping("/calc")
public class CalcController {
    @RequestMapping("/add")
    public Object add(int left, int right) {
        Integer result = EventBus.syncPublish(new AddEvent(left, right));

        return String.format("%s+%s=%s", left, right, result);
    }

    @PostConstruct
    public void init() {
        EventBus.subscribe(new AddSubscribe(), AddEvent.class);
    }
}
