package org.pengfei.pattern.bridge;

public class Circle extends Shape {
    private int x, y ,radius;
    protected Circle(int radius, int x, int y,DrawAPI drawAPI) {
        super(drawAPI);
        this.radius=radius;
        this.x=x;
        this.y=y;
    }

    public void draw() {
        drawAPI.drawCircle(radius,x,y);
    }
}
