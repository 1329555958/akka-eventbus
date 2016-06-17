package org.wch.eventbus.subscribe;

/**
 * 订阅事件接口,所有的订阅者都需要实现此接口
 * Created by weichunhe on 2016/6/17.
 */
public interface Subscribe<P, R> {
    R apply(P param);
}
