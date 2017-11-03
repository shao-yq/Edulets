package net.edulet.shyq.glyph;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

public class StrokeBase {
	public final static int STROKE_NONE = 0;
	public final static int STROKE_LINES = 1;
	public final static int STROKE_BEZIER = 2;
	public final static int STROKE_ARC = 3;
	public final static int STROKE_CIRCLE = 4;
	public final static int STROKE_ELLIPSE = 5;

	public int getCode() {
		return code;
	}

	int code;

	/**
	 * Draw line segment, arc, circle/ellipse, or draw Bezier curve.
	 */
	int type;
	/**
	 * The points buffer to store the stroke coordinates or control point
	 */
	//Vector<Point> points;
	Vector<PointSet> pointSets;

//	public StrokeBase(){
//		this(0, STROKE_NONE, new PointSet());
//	}

	public StrokeBase(int code, int type, PointSet pointSet) {
		this.code = code;
		this.type = type;
		pointSets =  new Vector<PointSet>();
		pointSets.add(pointSet);
		//this.points = points;
	}
//
//	Point anchor = new Point(0, 0);
//	public void setAnchor(Point p) {
//		anchor = p;
//	}
//
//	public Point getAnchor() {
//		return anchor;
//	}
//	public void move(double dx, double dy) {
//		anchor.move(dx,  dy);
//	}
//	double scaleX = 1;
//	double scaleY = 1;
//	public void setScales(double sx, double sy) {
//		scaleX = sx;
//		scaleY = sy;
//	}
//
//	public void setScaleX(double sx) {
//		scaleX = sx;
//	}
//
//	public void setScaleY(double sy){
//		scaleY = sy;
//	}
//
//	public double getScaleX() {
//		return scaleX;
//	}
//
//	public double getScaleY() {
//		return scaleY;
//	}

	public boolean contains(double x, double y){
		Rectangle rect = getBounds();
		//rect.move(anchor.x, anchor.y);
		if(rect.contains(x,y))
			return true;
		return false;
	}
	public boolean contains(Point p){
		Rectangle rect = getBounds();
		if(rect.contains(p))
			return false;
		return false;
	}

	public Rectangle getBounds() {
		Rectangle rect = new Rectangle();
		for(PointSet pointSet : pointSets){
			Rectangle recti = pointSet.getBounds();
			rect.merge(recti);
		}

		return rect;
	}

	public boolean isOnContour(double xx, double yy) {
		for(PointSet pointSet : pointSets){
			if(pointSet.isOnContour(xx,yy))
				return true;
		}

		return false;
	}

	public void draw(GraphicsContext gctx)   {
		for(PointSet pointSet : pointSets){
			pointSet.draw(gctx);
		}
	}

	public void save(DataOutputStream dos) throws IOException {
		dos.writeInt(code);

		String className = getClass().getCanonicalName();
		//dos.writeUTF(className);

		dos.writeInt(type);

		// points
		int count = pointSets.size();
		dos.writeInt(count);
		for(PointSet pointSet : pointSets){
			pointSet.save(dos);
		}
	}

	public void load(DataInputStream dis)throws IOException {
		code = dis.readInt();

		type = dis.readInt();

		// points
		int count = dis.readInt();

		for(int i=0; i<count; i++){
			PointSet pointSet = new PointSet();
			pointSet.load(dis);
		}
	}
}
