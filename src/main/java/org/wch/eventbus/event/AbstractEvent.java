package org.wch.eventbus.event;

import java.util.UUID;

/**
 * Created by weichunhe on 2016/6/17.
 */
public abstract class AbstractEvent implements Event {
    private String _uuid;

    public AbstractEvent() {
        _uuid = UUID.randomUUID().toString();
    }

    public String getUUID() {
        return _uuid;
    }
}
