package net.edulet.shyq.glyph;

public class Rectangle {
    public static final int BorderNone = 0;
    public static final int BorderEast = 1;
    public static final int BorderSouth = 2;
    public static final int BorderWest = 4;
    public static final int BorderNorth = 8;
    public static final int BorderCornerSE = 3;
    public static final int BorderCornerSW = 6;
    public static final int BorderCornerNE = 9;
    public static final int BorderCornerNW = 12;

	static double eps = 1.5;
	static int BorderMaskEast  = 0x01;
	static int BorderMaskSouth = 0x02;
	static int BorderMaskWest  = 0x04;
	static int BorderMaskNorth = 0x08;

	public double x;
    public double y;
    public double width;
    public double height;
    
    public Rectangle(double w, double h) {
    	x = 0;
    	y = 0;
    	width = w;
    	height = h;
    }
    
    public Rectangle(double x, double y, double w, double h) {
    	this.x = x;
    	this.y = y;
    	this.width = w;
    	this.height = h;
    }
    
    public Rectangle merge(Rectangle rect) {
    	if(rect instanceof NullRectangle) {
    		return this;
    	}
    	double x1 = Math.min(x, rect.x);
    	double y1 = Math.min(y, rect.y);
    	double x2 = Math.max(x+width, rect.x+rect.width);
    	double y2 = Math.max(y+height, rect.y+rect.height);
    	return new Rectangle(x1, y1, x2-x1, y2-y1);
    }
    
    public boolean contains(double px, double py) {
    	if(px < x || py < y)
    		return false;
    	if(px>x+width || py>y+height)
    		return false;
    	
    	return true;
    }
    
    public boolean contains(Point p) {
    	if(p.x < x || p.y < y)
    		return false;
    	if(p.x>x+width || p.y>y+height)
    		return false;
    	
    	return true;
    }

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void move(double dx, double dy) {
		x += dx;
		y += dy;
	}
	
	
    public int checkOnBorder(double xx, double yy) {
    	int border = 0;
    	if(Math.abs(xx - x) < eps) 
    		border |= BorderMaskWest;
    	if(Math.abs(yy-y) < eps)
    		border |= BorderMaskNorth;
    	if(Math.abs(yy-y-height)<eps)
    		border |= BorderMaskSouth;
    	if(Math.abs(xx - x - width)<eps)
    		border |= BorderMaskEast;
    	
    	return border;
    }

	public boolean contains(Rectangle rect) {
		if((rect.x>=x && rect.x+rect.width <=x+width)
		 &&(rect.y>=y && rect.x+rect.height <=y+height))
			return true;
		return false;
	}
}
