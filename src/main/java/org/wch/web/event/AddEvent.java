package org.wch.web.event;

import org.wch.eventbus.event.AbstractEvent;

/**
 * Created by weichunhe on 2016/6/17.
 */
public class AddEvent extends AbstractEvent {

    private int left, right;

    public AddEvent(int right, int left) {
        this.right = right;
        this.left = left;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
