package net.edulet.shyq.glyph;

import java.awt.Color;
import java.awt.Stroke;

public interface GraphicsContext {

	void drawLine(double x1, double y1, double x2, double y2);

	void drawLines(double[] px, double[] py);

	public void setScale(double sx, double sy);
	
	public void setTranslate(double tx, double ty) ;
	
	public void translate(double tx, double ty) ;

	void setColor(Color green);

	void draw(Rectangle rect);

	void scale(double scaleX, double scaleY);

	Stroke getStroke();

	void setStroke(Stroke oStroke);
}
