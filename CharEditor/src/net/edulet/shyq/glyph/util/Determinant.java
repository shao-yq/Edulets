package net.edulet.shyq.glyph.util;

public class Determinant {
	int rank;
	double [][] data;
    public Determinant(int rank) {
    	this.rank = rank;
    	data = new double[rank][rank];
    	for(int i=0; i<rank;) {
    		for(int j=0; j<rank; j++)
    			data[i][j] = 0;
    	}
    }
    
    public Determinant(double[][] buf) {
    	rank = buf[0].length;
    	data = new double [rank][rank];
    	for(int i=0 ; i<rank; i++) {
    		for (int j=0; j<rank; j++) {
    			data[i][j] = buf[i][j];
    		}
    	}
    	data = buf;
    }
    
    public static double value(double buf[][]) {
    	Determinant det = new Determinant(buf);
    	return det.value();
    }

    public double value() {
    	if(rank == 0)
    		return 0;
    	if(rank==1)
    		return data[0][0];
    	if(rank ==2)
    		return data[0][0]*data[1][1] - data[0][1]*data[1][0];
    	
    	double val = 0;
    	double sign = 1;
    	int j = rank-1;
    	for(int i=0; i<rank; i++) {
    		Determinant subDet = minorDet(i, j);
    		int nn = i-j;
    		if(nn%2 == 0) {
    			sign = 1;
    		} else 
    			sign = -1;
    		
    		val += sign*subDet.value()*data[i][j];
    	}
    	return val;
    }

	private Determinant minorDet(int row, int col) {
		double buf [][] = new double[rank-1][rank-1];
		int rr = 0;
		int cc = 0;
		for(int i=0; i<rank; i++) {
			if(i == row)
				continue;
			for(int j=0; j<rank; j++) {
				if(j == col)
					continue;
				buf[rr][cc] = data[i][j];
				cc++;
			}
			
			rr++;
			cc = 0;
		}
		
		return new Determinant(buf);
	}
}
