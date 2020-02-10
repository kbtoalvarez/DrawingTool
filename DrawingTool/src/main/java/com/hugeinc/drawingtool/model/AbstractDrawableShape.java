package com.hugeinc.drawingtool.model;

public abstract class AbstractDrawableShape implements DrawableShape {

    Point point1;
    Point point2;

    public AbstractDrawableShape(final Point point1, final Point point2) {
        this.point1 = point1;
        this.point2 = point2;

        order();
    }

    public AbstractDrawableShape(final int x1, final int y1, final int x2, final int y2) {
        this.point1 = new Point(x1, y1);
        this.point2 = new Point(x2, y2);

        order();
    }

    void order() {
        if (point1.getX() > point2.getX()) {
            final int temp = point1.getX();
            point1 = new Point(point2.getX(), point1.getY());
            point2 = new Point(temp, point2.getY());
        }

        if (point1.getY() > point2.getY()) {
            final int temp = point1.getY();
            point1 = new Point(point1.getX(), point2.getY());
            point2 = new Point(point2.getX(), temp);
        }
    }

    @Override
    public boolean isValid(final Canvas canvas) {
        if (!point1.equals(point2)
                && point1.isValid(canvas)
                && point2.isValid(canvas)) {
            return true;
        }

        return false;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

}
