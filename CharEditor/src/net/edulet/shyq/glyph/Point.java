package net.edulet.shyq.glyph;

public class Point {
    double x;
    double y;

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point(double xx, double yy) {
    	x = xx;
    	y = yy;
    }
    
    public Point(Point p) {
    	x = p.getX();
    	y = p.getY();
    }
    public double getX() {
    	return x;
    }
    public double getY() {
    	return y;
    }
    
    public void move(double dx, double dy) {
    	x += dx;
    	y += dy;
    }
    
}
