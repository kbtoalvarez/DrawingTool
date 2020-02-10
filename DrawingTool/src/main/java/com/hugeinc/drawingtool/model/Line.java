package com.hugeinc.drawingtool.model;

import com.hugeinc.drawingtool.common.DrawingToolConstants;

public class Line extends AbstractDrawableShape implements DrawableShape {

    public Line(final Point point1, final Point point2) {
        super(point1, point2);
    }

    public Line(final int x1, final int y1, final int x2, final int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(final Canvas canvas) {
        if (canvas != null && isValid(canvas)) {
            for (int i = 0; i < getLength(); i++) {
                if (isHorizontal()) {
                    canvas.setValue(point1.getX(), point1.getY() + i, DrawingToolConstants.LINE_SYMBOL);
                } else {
                    canvas.setValue(point1.getX() + i, point1.getY(), DrawingToolConstants.LINE_SYMBOL);
                }
            }
        }
    }

    @Override
    public boolean isValid(final Canvas canvas) {
        if (isHorizontal() || isVertical()) {
            return super.isValid(canvas);
        }

        return false;
    }

    public boolean isHorizontal() {
        return point1.getX() == point2.getX() && point1.getY() != point2.getY();
    }

    public boolean isVertical() {
        return point1.getX() != point2.getX() && point1.getY() == point2.getY();
    }

    public int getLength() {
        if (isHorizontal()) {
            return point2.getY() - point1.getY() + 1;
        } else {
            return point2.getX() - point1.getX() + 1;
        }
    }

}
