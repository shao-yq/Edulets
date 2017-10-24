package net.edulet.shyq.glyph;

import java.util.Vector;

import net.edulet.shyq.spline.BezierUtil;

public class StrokeBesier extends StrokeBase {
	Vector ctrlPoints;

	public StrokeBesier(Vector points) {
		super(STROKE_BEZIER, points);
		ctrlPoints = points;
		generateCurve();
	}

	private void generateCurve() {
		if(ctrlPoints == null || ctrlPoints.size()==0)
			return;
		Rectangle rect = getBounds();
		int ww = (int) rect.width;
		int hh= (int)rect.height;
		
		points = new Vector(ww);
		BezierUtil.getBezierPoints(ctrlPoints, ww, points);

	}

}
