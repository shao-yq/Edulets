package net.edulet.shyq.glyph;

import net.edulet.shyq.glyph.util.Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * @author Shao Yongqing
 * Date: 2017/11/2.
 */
public class PointSet {
    Vector<Point> points = new Vector<Point>();

    public boolean add(Point point) {
        return points.add(point);
    }
    public boolean remove(Point point) {
        return points.remove(point);
    }

    public int size() {
        return points.size();
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
        if(points == null || points.size()==0)
            return new Rectangle();

        int pointCount = points.size();

        double minX= Double.MAX_VALUE, minY = Double.MAX_VALUE;
        double maxX=Double.MIN_VALUE, maxY= Double.MIN_VALUE;

        for (int i=0; i<pointCount; i++) {
            Point point = (Point)points.get(i);
            if(point.x>maxX)
                maxX = point.x;
            if(point.x<minX)
                minX = point.x;

            if(point.y>maxY)
                maxY = point.y;
            if(point.y<minY)
                minY = point.y;
        }
        int ww = (int)(maxX-minX);
        int hh= (int)(maxY-minY);
        return new Rectangle(minX, minY, ww,hh);
    }
    public boolean isOnContour(double xx, double yy) {
        double dd = Double.MAX_VALUE;
        for(int i=0; i<points.size()-1 ; i++) {
            Point p1 = (Point) points.get(i);
            Point p2 = (Point) points.get(i+1);
            double dist = Utils.distancePointLine(p1.x, p1.y, p2.x, p2.y, xx, yy);
            if(dist < dd)
                dd = dist;
        }
        if(dd <= Utils.epsilon)

            return true;
        return false;
    }

    public void draw(GraphicsContext gctx)   {
        if(points == null || points.size()==0)
            return;
        int pointCount = points.size();
        double px[], py[];
        px = new double[pointCount];
        py = new double[pointCount];

        for (int i=0; i<pointCount; i++) {
            px[i] = ((Point)points.get(i)).getX();
            py[i] = ((Point)points.get(i)).getY();
        }

        gctx.drawPoints(px, py);
    }

    public void save(DataOutputStream dos) throws IOException {

        // points
        int count = points.size();
        dos.writeInt(count);
        for(int i=0; i<count; i++){
            Point pint = points.get(i);
            dos.writeDouble(pint.x);
            dos.writeDouble(pint.y);
        }
    }

    public void load(DataInputStream dis)throws IOException {

        // points
        int count = dis.readInt();

        for(int i=0; i<count; i++){
            Point point =new Point(dis.readDouble(),dis.readDouble());
            points.add(point);
        }
    }
}
