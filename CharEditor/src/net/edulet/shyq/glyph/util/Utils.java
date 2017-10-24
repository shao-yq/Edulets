package net.edulet.shyq.glyph.util;

public class Utils {
    public static final double epsilon = 1;

	public static double distancePointLine(double lx1, double ly1, double lx2, double ly2, double px, double py) {
        double ll = Math.sqrt((lx1-lx2)*(lx1-lx2) + (ly1-ly2)*(ly1-ly2));
        double area =triangleArea(lx1,ly1, lx2, ly2, px, py);
        double dist = area/ll/2;
        
        return dist;
    }
    
    public static double triangleArea(double x1, double y1,
    		double x2, double y2,
    		double x3, double y3) {
    	double [][]buf = new double[3][3];
    	buf[0][0] = x1;
    	buf[0][1] = y1;
    	buf[0][2] = 1;

    	buf[1][0] = x2;
    	buf[1][1] = y2;
    	buf[1][2] = 1;
    	
    	buf[2][0] = x3;
    	buf[2][1] = y3;
    	buf[2][2] = 1;
    	
    	return Determinant.value(buf);
    }

}
