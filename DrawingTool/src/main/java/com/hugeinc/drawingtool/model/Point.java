package com.hugeinc.drawingtool.model;

import com.hugeinc.drawingtool.common.DrawingToolConstants;

public class Point implements DrawableShape {

    private int x;
    private int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Point point = (Point) o;

        return x == point.x &&
                y == point.y;
    }

    @Override
    public void draw(final Canvas canvas) {
        if (canvas != null && isValid(canvas)) {
            canvas.setValue(x, y, DrawingToolConstants.LINE_SYMBOL);
        }
    }

    @Override
    public boolean isValid(final Canvas canvas) {
        if (x > 0 && x <= canvas.getWidth()
                && y > 0 && y <= canvas.getHeight()) {
            return true;
        }

        return false;
    }

}
