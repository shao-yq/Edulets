package net.edulet.shyq.spline;
// Interactive 2D Bezier splines,  Evgeny Demidov 16 June 2001
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class BezierApplet extends java.applet.Applet
    implements MouseMotionListener{
    Image buffImage;
    Graphics buffGraphics;
	int n = 6, n1, w, h, h1, w2;
	double[] Px, Py;
	boolean drawControl = true;
/*
public void drawFun(Graphics buffGraphics){
  double step = 1./w2, t = step;
  double[] B = new double[n1], Bo = new double[n1], Bold = new double[n1];
  B[1] = Bo[1] = h1;
  Color[] iColor = {Color.gray, Color.red, new Color(0f,.7f,0f),
   Color.blue, Color.magenta, new Color(0f,.8f,.8f), new Color(.9f,.9f,0f) };
  for (int k = 1; k < w2; k++){
   System.arraycopy(B,1,Bold,1,n);
   System.arraycopy(Bo,1,B,1,n);

   for (int j = 1; j < n; j++)        //  basis functions calculation
    for (int i = j+1; i > 0; i--)
     B[i] = (1-t)*B[i] + t*B[i-1];

   for (int m = 1; m <= n; m++){
    buffGraphics.setColor(iColor[m % 7]);
    buffGraphics.drawLine(w2+k-1, h1-(int)Bold[m], w2+k, h1-(int)B[m] );}
   t += step;
  }
}
*/


public void init() {
  w = Integer.parseInt(getParameter("width"));
  h = Integer.parseInt(getParameter("height"));  h1 = h-1; w2 = w/2;
  String s = getParameter("N"); if (s != null) n = Integer.parseInt(s);
  n1 = n+1;
  Px = new double[n];  Py = new double[n];
  s=getParameter("pts");
  if (s != null){
   StringTokenizer st = new StringTokenizer(s);
   for (int i = 0; i < n; i++){
    Px[i] = w2*Double.valueOf(st.nextToken()).doubleValue();
    Py[i] = h1*Double.valueOf(st.nextToken()).doubleValue();}}
  else{
   Px[0] = .1*w2; Px[1] = .1*w2; Px[2] = .9*w2; Px[3] = .9*w2;
   Py[0] = .1*h1; Py[1] = .9*h1; Py[2] = .9*h1; Py[3] = .1*h1;}
  buffImage = createImage(w, h);
  buffGraphics = buffImage.getGraphics();
  setBackground(Color.white);
  buffGraphics.clearRect(0,0, w, h);
  addMouseMotionListener(this);
//  drawFun(buffGraphics);
  BezierUtil.drawSpline(buffGraphics, Px, Py, n, h1, w2, drawControl);
}

public void destroy(){ removeMouseMotionListener(this); }
public void mouseMoved(MouseEvent e){}  //1.1 event handling

public void mouseDragged(MouseEvent e) {
  int x = e.getX();  if (x < 0) x = 0;  if (x > w2-3) x = w2-3;
  int y = h1 - e.getY();  if (y < 0) y = 0;  if (y > h1) y = h1;
  int iMin = 0;
  double Rmin = 1e10, r2,xi,yi;
  for (int i = 0; i < n; i++){
   xi = (x - Px[i]); yi = (y - Py[i]);
   r2 = xi*xi + yi*yi;
   if ( r2 < Rmin ){ iMin = i; Rmin = r2;}}
  Px[iMin] = x; Py[iMin] = y;
  //BezierUtil.drawSpline(buffGraphics, Px, Py, n, h1, w2, drawControl);
  double pointx[] = new double[w2];
  double pointy[] = new double[w2];
  BezierUtil.getBezierPoints(Px, Py, n, w2, pointx, pointy);
  
  buffGraphics.clearRect(0, 0, w2, h1 + 1);
  buffGraphics.setColor(Color.blue);
  drawLines(buffGraphics, null, Px, Py, n, h1);
  
  buffGraphics.setColor(Color.red);
  drawLines(buffGraphics, Color.red, pointx, pointy, w2, h1);
  
  repaint();
}
void drawLines(Graphics graphics, Color color, double[] pointx, double[] pointy,
			int n, int h1) {
	if(color != null) {
		graphics.setColor(color);
	}
	int X, Y, oldX, oldY;
	  oldX = (int) pointx[0];
	  oldY = h1-(int)pointy[0];
	  for(int i=1; i<n; i++) {
		  X = (int)pointx[i];
		  Y = h1 - (int)pointy[i];
		  graphics.drawLine(oldX, oldY, X, Y);
		  oldX = X;
		  oldY = Y;
	  }
	
}

public void paint(Graphics g) {
	
  g.drawImage(buffImage, 0, 0, this);
//  showStatus( " " + x +"  " + y);
}

public void update(Graphics g){ paint(g); }

}