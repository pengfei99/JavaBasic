package org.pengfei.Lesson02_Effective_Java_Programming.S00_Applying_Java_With_Beans.source;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * The MyColors Bean displays a colored object within a frame. */
public class MyColors extends Canvas implements Serializable {
    // It defines the color of the displayed object
    transient private Color color; //not persistent
    // It defines the shape of the displayed object
    private boolean rectangular; // persistent

    /* The constructor defines an anonymous inner class that extends MouseAdapter and overrides its mousePressed()
     * method. When use press mouse it change the object shape to oval and a random color*/
    public MyColors() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                change();
            }
        });
        rectangular = false;
        setSize(200, 100);
        change();
    }

    /*
    * The change() method is invoked in response to mouse presses. It selects a random color and then repaints the
    * component. It calls randomColor() to choose a color and then calls repaint() to make the change visible.
    * */
    public void change() {
        color = randomColor();
        repaint();
    }

    private Color randomColor() {
        // The java.lang.Math.random() method returns a pseudorandom double type number greater than or equal to 0.0
        // and less than 1.0
        int r = (int) (255 * Math.random());
        int b = (int) (255 * Math.random());
        int g = (int) (255 * Math.random());

        return new Color(r, g, b);
    }

    /*
    * The getRectangular() and setRectangular() methods provide access to the one property of this Bean.
    * */
    public boolean isRectangular() {
        return rectangular;
    }

    public void setRectangular(boolean rectangular) {
        this.rectangular = rectangular;
        repaint();
    }

    /*
    * The paint() method uses the rectangular and color variables to determine how to present the Bean.
    * */
    public void pain(Graphics graphics) {
        Dimension dimension = getSize();
        int h = dimension.height;
        int w = dimension.width;
        graphics.setColor(color);
        if (rectangular) {
            graphics.fillRect(0, 0, h - 1, w - 1);
        } else {
            graphics.fillOval(0, 0, h - 1, w - 1);
        }
    }
}
