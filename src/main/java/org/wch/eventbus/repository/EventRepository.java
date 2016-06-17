package org.wch.eventbus.repository;

import java.util.HashMap;

/**
 * Created by weichunhe on 2016/6/17.
 */
public class EventRepository {
    private static HashMap<String, Thread> threadMap = new HashMap<String, Thread>();
    private static HashMap<String, Object> resultMap = new HashMap<String, Object>();

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
