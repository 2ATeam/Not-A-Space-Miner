package com.aateam.spaceminer.utils;

import com.sun.istack.internal.NotNull;

public class _2APoint {

    public int x;
    public int y;

    public _2APoint() {
        x = y = 0;
    }

    public _2APoint(@NotNull _2APoint point) {
        this.x = point.x;
        this.y = point.y;
    }

    public _2APoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int distance(int xb, int yb) {
        return (int) Math.sqrt((x - xb) * (x - xb) + (y - yb) * (y - yb));
    }

    @Override
    public String toString() {
        return  "x=" + x + ", y=" + y + '}';
    }
}
