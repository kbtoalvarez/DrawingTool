package com.hugeinc.drawingtool.model;

import com.hugeinc.drawingtool.common.DrawingToolConstants;

public class Rectangle extends AbstractDrawableShape implements DrawableShape {

    public Rectangle(final int x1, final int y1, final int x2, final int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(final Canvas canvas) {
        for (int y = 0; y < point2.getY() - point1.getY() + 1; y++) {
            for (int x = 0; x < point2.getX() - point1.getX() + 1; x++) {
                if (x == 0 || x == point2.getX() - point1.getX()
                        || y == 0 || y == point2.getY() - point1.getY()) {
                    canvas.setValue(point1.getX() + x, point1.getY() + y, DrawingToolConstants.LINE_SYMBOL);
                }
            }
        }
    }

}
