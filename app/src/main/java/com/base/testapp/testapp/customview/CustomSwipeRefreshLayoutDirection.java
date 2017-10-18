package com.base.testapp.testapp.customview;

/**
 * Created by oliviergoutay on 1/23/15.
 */
public enum CustomSwipeRefreshLayoutDirection {

    TOP(0),
    BOTTOM(1),
    BOTH(2);

    private int mValue;

    CustomSwipeRefreshLayoutDirection(int value) {
        this.mValue = value;
    }

    public static CustomSwipeRefreshLayoutDirection getFromInt(int value) {
        for (CustomSwipeRefreshLayoutDirection direction : CustomSwipeRefreshLayoutDirection.values()) {
            if (direction.mValue == value) {
                return direction;
            }
        }
        return BOTH;
    }

}
