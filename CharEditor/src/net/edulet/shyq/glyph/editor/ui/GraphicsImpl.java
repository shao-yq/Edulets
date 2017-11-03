package net.edulet.shyq.glyph.editor.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import net.edulet.shyq.glyph.GraphicsContext;
import net.edulet.shyq.glyph.Rectangle;

public class GraphicsImpl implements GraphicsContext {
    /**
     * Screen coordinate system
     * |         (width, 0)
     * ------------------------->
     * (0,0)   |            |   x
     * |            |
     * |            |
     * (0,height)-------------(width, height)
     * |
     * v y
     * Real world coordinate system
     * <p>
     * ^ y
     * |
     * |
     * |
     * ----------------->
     * |(0,0)     x
     * |
     */
    Graphics graphics;
    private int screenWidth;
    private int screenHeight;
    private double tx, ty;
    private double sx, sy;

    public GraphicsImpl(Graphics g) {
        graphics = g;
        tx = 0;
        ty = 0;
        sx = 1;
        sy = 1;
    }

    public void setScale(double sx, double sy) {
        this.sx = sx;
        this.sy = sy;
    }

    public void setTranslate(double tx, double ty) {
        this.tx = tx;
        this.ty = ty;
    }

    public void translate(double tx, double ty) {
        this.tx += tx;
        this.ty += ty;
        graphics.translate((int) tx, (int) ty);
    }

    int toScreenX(double x, double y) {
        return (int) x;
    }

    int toScreenY(double x, double y) {
        //return (int)(getScreenHeight()-y);
        return (int) y;
    }

    private int getScreenHeight() {
        // TODO Auto-generated method stub
        return screenHeight;
    }

    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        int X, Y, oldX, oldY;
        oldX = toScreenX(x1, y1);
        oldY = toScreenY(x1, y1);
        X = toScreenX(x2, y2);
        Y = toScreenY(x2, y2);
        graphics.drawLine(oldX, oldY, X, Y);
    }

    @Override
    public void drawLines(double[] pointx, double[] pointy) {

        int X, Y, oldX, oldY;

        oldX = toScreenX(pointx[0], pointy[0]);
        oldY = toScreenY(pointx[0], pointy[0]);

        for (int i = 1; i < pointx.length; i++) {
            X = toScreenX(pointx[i], pointy[i]);
            Y = toScreenY(pointx[i], pointy[i]);
            graphics.drawLine(oldX, oldY, X, Y);
            oldX = X;
            oldY = Y;
        }
    }

    @Override
    public void drawPoints(double[] pointx, double[] pointy) {
        int X, Y;
        int width=1, height=1;
        for (int i = 1; i < pointx.length; i++) {
            X = toScreenX(pointx[i], pointy[i]);
            Y = toScreenY(pointx[i], pointy[i]);
            graphics.fillOval(X, Y, width, height);
        }

    }

    @Override
    public void setColor(Color cl) {
        graphics.setColor(cl);
    }

    @Override
    public void draw(Rectangle rect) {
        graphics.drawRect((int) rect.x, (int) rect.y, (int) rect.width, (int) rect.height);

    }

    @Override
    public void scale(double scaleX, double scaleY) {
        ((Graphics2D) graphics).scale(scaleX, scaleY);

    }

    @Override
    public Stroke getStroke() {
        return ((Graphics2D) graphics).getStroke();
    }

    @Override
    public void setStroke(Stroke oStroke) {
        ((Graphics2D) graphics).setStroke(oStroke);

    }

}
