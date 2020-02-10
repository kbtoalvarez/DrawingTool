package com.hugeinc.drawingtool.model;

import com.hugeinc.drawingtool.common.DrawingToolConstants;

import java.util.ArrayList;
import java.util.List;

public class Canvas {

    private char[][] canvas;
    private int width;
    private int height;

    public Canvas(final int width, final int height) {
        if (width > 0 && height > 0) {
            this.width = width;
            this.height = height;
            this.canvas = new char[width][height];
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setValue(final int x, final int y, final char value) {
        if (x > 0 && x <= this.width
                && y > 0 && y <= this.height) {
            this.canvas[x - 1][y - 1] = value;
        }
    }

    public char getValue(final int x, final int y) {
        if (x > 0 && x <= this.width
                && y > 0 && y <= this.height) {
            return this.canvas[x - 1][y - 1];
        }

        return 0;
    }

    public void fillCanvas(final Canvas canvas, final Point point, final char color) {
        if (canvas != null && point != null && color != DrawingToolConstants.LINE_SYMBOL) {
            if (point.getY() <= 0 || point.getY() > canvas.getHeight()
                    || point.getX() <= 0 || point.getX() > canvas.getWidth()
                    || canvas.getValue(point.getX(), point.getY()) == DrawingToolConstants.LINE_SYMBOL
                    || canvas.getValue(point.getX(), point.getY()) == color) {
                return;
            }

            canvas.setValue(point.getX(), point.getY(), color);

            fillCanvas(canvas, new Point(point.getX(), point.getY() - 1), color);
            fillCanvas(canvas, new Point(point.getX(), point.getY() + 1), color);
            fillCanvas(canvas, new Point(point.getX() - 1, point.getY()), color);
            fillCanvas(canvas, new Point(point.getX() + 1, point.getY()), color);
        }
    }

    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();

        for (int y = 0; y < height + 2; y++) {
            for (int x = 0; x < width + 2; x++) {
                if (y == 0 || y == height + 1) {
                    buffer.append(DrawingToolConstants.TOP_BOTTOM_BORDER);
                } else if (x == 0 || x == width + 1) {
                    buffer.append(DrawingToolConstants.LEFT_RIGHT_BORDER);
                } else if (getValue(x, y) == 0) {
                    buffer.append(DrawingToolConstants.EMPTY_SYMBOL);
                } else {
                    buffer.append(getValue(x, y));
                }
            }

            buffer.append(System.lineSeparator());
        }

        return buffer.toString();
    }

}
