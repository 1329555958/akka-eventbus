package org.wch.eventbus.repository;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by weichunhe on 2016/6/17.
 */
public class EventRepository {
    private static ConcurrentHashMap<String, Thread> threadMap = new ConcurrentHashMap<String, Thread>();
    private static ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();

    public static void suspend(String key) {
        threadMap.put(key, Thread.currentThread());
        Thread.currentThread().suspend();
    }

    public static void resume(String key, Object result) {
        Thread thread = threadMap.get(key);
        if (thread != null) {
            resultMap.put(key, result);
            thread.resume();
        }
    }

    public static <R> R getResult(String key) {
        Object o = resultMap.get(key);
        if (o == null) {
            return null;
        }
        R result = (R) o;
        return result;
    }
}
