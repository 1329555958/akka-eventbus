package org.wch.eventbus.event;

/**
 * 事件处理结束
 * Created by weichunhe on 2016/6/17.
 */
public class FinishEvent<P extends Event, R> {

    private P event;
    private R result;

    public FinishEvent(P param, R result) {
        this.event = param;
        this.result = result;
    }

    public P getEvent() {
        return event;
    }

    public void setEvent(P event) {
        this.event = event;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }
}