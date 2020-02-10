package com.hugeinc.drawingtool.model;

public interface DrawableShape {

    void draw(Canvas canvas);

    boolean isValid(Canvas canvas);

}
