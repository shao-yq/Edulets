package net.edulet.shyq.glyph;

/**
 * @author Shao Yongqing
 * Date: 2017/11/2.
 */
public class TransformImpl implements Transformable {
    @Override
    public void translate(double tx, double ty) {
        anchor.translate(tx,  ty);
    }

    @Override
    public void scale(double sx, double sy) {
        anchor.scale(sx,sy);
    }


	Point anchor = new Point(0, 0);
	public void setAnchor(Point p) {
		anchor = p;
	}

	public Point getAnchor() {
		return anchor;
	}

	public void move(double dx, double dy) {
		anchor.move(dx,  dy);
	}

	double scaleX = 1;
	double scaleY = 1;
	public void setScales(double sx, double sy) {
		scaleX = sx;
		scaleY = sy;
	}

	public void setScaleX(double sx) {
		scaleX = sx;
	}

	public void setScaleY(double sy){
		scaleY = sy;
	}

	public double getScaleX() {
		return scaleX;
	}

	public double getScaleY() {
		return scaleY;
	}

}
