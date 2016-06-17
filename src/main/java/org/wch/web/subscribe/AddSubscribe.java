package org.wch.web.subscribe;

import org.wch.eventbus.subscribe.Subscribe;
import org.wch.web.event.AddEvent;

/**
 * Created by weichunhe on 2016/6/17.
 */
public class AddSubscribe implements Subscribe<AddEvent, Integer> {

    @Override
    public Integer apply(AddEvent param) {
        return param.getLeft() + param.getRight();
    }
}
