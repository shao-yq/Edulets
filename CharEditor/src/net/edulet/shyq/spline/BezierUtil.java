package net.edulet.shyq.spline;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import net.edulet.shyq.glyph.Point;

public class BezierUtil {
    public static void getBezierPoints(Point[] ctrlPoints, int n, int width, Point [] curvePoints){
        double step = 1./ width;
        double t = step;
        double[] Pxi = new double[n], Pyi = new double[n];
        
        
    }
	
    public static void getBezierPoints(Vector ctrlPoints, int width, Vector curvePoints) {
        int n = ctrlPoints.size();
        double step = 1. / width, t = step;
        double[] Pxi = new double[n], Pyi = new double[n];
        curvePoints.add(new Point((Point)ctrlPoints.get(0)));
        for (int k = 1; k < width; k++) {
        	// Init Pxi, Pyi
        	for(int j=0; j<n; j++) {
        		Pxi[j] = ((Point)ctrlPoints.get(j)).getX();
        		Pyi[j] = ((Point)ctrlPoints.get(j)).getY();
        	}
        	
        	for(int j=n-1; j>0; j--) {
        		// points calculation
        		for(int i=0; i<j; i++) {
        			Pxi[i] = (1-t) * Pxi[i] + t * Pxi[i+1];
        			Pyi[i] = (1-t) * Pyi[i] + t * Pyi[i+1];
        		}
        	}
        	curvePoints.add(new Point(Pxi[0], Pyi[0]));
        	
        	t += step;
        }
    }
    
    public static void getBezierPoints(double[] Px, double[] Py,
			int n, int width, double pointx[], double pointy[]) {
		double step = 1. / width, t = step;
		double[] Pxi = new double[n], Pyi = new double[n];
		//int X, Y, Xold = (int) Px[0], Yold = h1 - (int) Py[0];
		
		pointx[0] = Px[0];
		pointy[0] = Py[0];
		for (int k = 1; k < width; k++) {
			System.arraycopy(Px, 0, Pxi, 0, n);
			System.arraycopy(Py, 0, Pyi, 0, n);

			for (int j = n - 1; j > 0; j--)
				// points calculation
				for (int i = 0; i < j; i++) {
					Pxi[i] = (1 - t) * Pxi[i] + t * Pxi[i + 1];
					Pyi[i] = (1 - t) * Pyi[i] + t * Pyi[i + 1];
				}
			pointx[k] = Pxi[0];
			pointy[k] = Pyi[0];

//			X = (int) Pxi[0];
//			Y = h1 - (int) Pyi[0];
//			graphics.drawLine(Xold, Yold, X, Y);
//			Xold = X;
//			Yold = Y;
			t += step;
		}		
	}
	public static void drawSpline(Graphics graphics, double[] Px, double[] Py,
			int n, int h1, int w2, boolean drawControl) {
		double step = 1. / w2, t = step;
		double[] Pxi = new double[n], Pyi = new double[n];
		int X, Y, Xold = (int) Px[0], Yold = h1 - (int) Py[0];
		graphics.clearRect(0, 0, w2, h1 + 1);
		if(drawControl) {
			graphics.setColor(Color.blue);
			for (int i = 0; i < n; i++) {
				X = (int) Px[i];
				Y = h1 - (int) Py[i];
				graphics.drawRect(X - 1, Y - 1, 3, 3);
			}
			if (n > 2) {
				int Xo = Xold, Yo = Yold;
				for (int i = 1; i < n; i++) {
					X = (int) Px[i];
					Y = h1 - (int) Py[i];
					graphics.drawLine(Xo, Yo, X, Y);
					Xo = X;
					Yo = Y;
				}
			}
		}
		graphics.setColor(Color.red);
		for (int k = 1; k < w2; k++) {
			System.arraycopy(Px, 0, Pxi, 0, n);
			System.arraycopy(Py, 0, Pyi, 0, n);

			for (int j = n - 1; j > 0; j--)
				// points calculation
				for (int i = 0; i < j; i++) {
					Pxi[i] = (1 - t) * Pxi[i] + t * Pxi[i + 1];
					Pyi[i] = (1 - t) * Pyi[i] + t * Pyi[i + 1];
				}

			X = (int) Pxi[0];
			Y = h1 - (int) Pyi[0];
			graphics.drawLine(Xold, Yold, X, Y);
			Xold = X;
			Yold = Y;
			t += step;
		}
	}
}
