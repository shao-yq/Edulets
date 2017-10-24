package net.edulet.shyq.spline;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

public class MyPanel extends JPanel  implements MouseMotionListener, Runnable {
    Image buffImage;
    Graphics buffGraphics;
	int n = 6, n1, w, h, h1, w2;
	double[] Px, Py, pointx, pointy;


	public MyPanel() {
		init();
	}


	private void init() {
		w = 400; //Integer.parseInt(getParameter("width"));
		h = 500; //Integer.parseInt(getParameter("height"));  
		h1 = h-1; 
		w2 = w-1; // w/2;
		
		n1 = n+1;
		Px = new double[n];  Py = new double[n];
	
	    Px[0] = .1*w2; Px[1] = .1*w2; Px[2] = .9*w2; Px[3] = .9*w2;
	    Py[0] = .1*h1; Py[1] = .9*h1; Py[2] = .9*h1; Py[3] = .1*h1;
	    
		pointx = new double[w2];
		pointy = new double[w2];
		
		rect = new Rectangle(10,10,w, h);
		
		  buffImage = createImage(w+1, h+1);
		  if(buffImage != null) {
			  buffGraphics = buffImage.getGraphics();
			  setBackground(Color.white);
			  buffGraphics.clearRect(0,0, w, h);
		  }
		  addMouseMotionListener(this);
	}
	
	public void setSize(int width, int height) {
		super.setSize(width, height);
	}
	
	public void setBounds(int x,
            int y,
            int width,
            int height) {
		super.setBounds(x, y, width, height);
		if(buffImage == null) {
			buffImage = createImage(w+rect.x*2, h+rect.y*2);
			  if(buffImage != null) {
				  buffGraphics = buffImage.getGraphics();
				  setBackground(Color.white);
				  buffGraphics.translate(rect.x, rect.y);
				  buffGraphics.clearRect(0,0, w, h);
			  }
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
        final MyPanel panel = new MyPanel();
        Frame f = new Frame("Java2D Demo " );
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing    (WindowEvent e) { System.exit(0); }
            public void windowDeiconified(WindowEvent e) { panel.start(); }
            public void windowIconified  (WindowEvent e) { panel.stop(); }
        });
        f.add("Center", panel);
        f.pack();
        f.setSize(new Dimension(500,600));
        f.setVisible(true);
        
        panel.start();
//        if (surface.animating != null) {
//            surface.animating.start();
//        }

	}
	protected void paintComponent(Graphics g) {
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		  int x = e.getX();  
		  if (x < 0) x = 0;  
		  if (x > w2-3) x = w2-3;
		  
		  //int y = h1 - e.getY();  
		  int y = e.getY();
		  if (y < 0) y = 0;  
		  if (y > h1) y = h1;
		  
		  int iMin = 0;
		  double Rmin = 1e10, r2,xi,yi;
		  for (int i = 0; i < n; i++){
		   xi = (x - Px[i]); yi = (y - Py[i]);
		   r2 = xi*xi + yi*yi;
		   if ( r2 < Rmin ){ iMin = i; Rmin = r2;}}
		  Px[iMin] = x; Py[iMin] = y;
		  //BezierUtil.drawSpline(buffGraphics, Px, Py, n, h1, w2, drawControl);
		  
		  BezierUtil.getBezierPoints(Px, Py, n, w2, pointx, pointy);
		  
		  buffGraphics.clearRect(0, 0, w2, h1 + 1);
		  buffGraphics.setColor(Color.blue);
		  drawLines(buffGraphics, null, Px, Py, n, h1);
		  
		  buffGraphics.setColor(Color.red);
		  drawLines(buffGraphics, Color.red, pointx, pointy, w2, h1);
		  resetAnimation();
		  repaint();
	}
	
	
	private void resetAnimation() {
		currentPoint = -1;
		
	}

	int currentPoint=-1;
	int lastX, lastY;
	int currentX, currentY;
	Rectangle rect;
	private void drawNextFrame() {
		//
		nextPoint();
		if(currentPoint == 0) {
			redrawCell();
			buffGraphics.setColor(Color.red);
			drawLines(buffGraphics, Color.red, pointx, pointy, w2, h1);
		}
		buffGraphics.setColor(Color.black);
		buffGraphics.drawLine(lastX, lastY, currentX, currentY);
		
	}
	

	private void redrawCell() {
		// Clear the rectangle drawing area
		buffGraphics.clearRect(0, 0, w2, h1 + 1);
		// Draw Outer rectangle border
		buffGraphics.setColor(Color.green);
		buffGraphics.drawRect(0, 0, rect.width-1, rect.height-1);
		// Draw diagonal
		buffGraphics.drawLine(0,0,rect.width-1,rect.height-1);
		buffGraphics.drawLine(0,rect.height-1,rect.width-1,0);
		// Draw half segments 
		buffGraphics.drawLine(0,rect.height/2,rect.width-1,rect.height/2);
		buffGraphics.drawLine(rect.width/2,0, rect.width/2, rect.height);
		
		// Draw Inner rectangle
		//buffGraphics.setColor(Color.gray);
		buffGraphics.drawRect(rect.width/4, rect.height/4, rect.width/2-1, rect.height/2-1);
		// Draw control points/lines
		buffGraphics.setColor(Color.blue);
		drawLines(buffGraphics, null, Px, Py, n, h1);
	}


	private void nextPoint() {
		synchronized (this) {
			currentPoint ++;
			lastX = currentX;
			lastY = currentY;
			if(currentPoint == w2) {
				currentPoint = 0;
				lastX = (int) pointx[0];
				//lastY = (int) (h1-pointy[0]);
				lastY = (int)pointy[0];
	
			}
		}
		currentX = (int) pointx[currentPoint];
		//currentY = (int) (h1-pointy[currentPoint]);
		currentY = (int) pointy[currentPoint];
		
	}


	void drawLines(Graphics graphics, Color color, double[] pointx, double[] pointy, int n, int h1) {
	if(color != null) {
		graphics.setColor(color);
	}
	int X, Y, oldX, oldY;
	  oldX = (int) pointx[0];
	  //oldY = h1-(int)pointy[0];
	  oldY = (int)pointy[0];
	  //String str = "("+oldX+","+oldY+")";
	  //graphics.drawString(str, oldX+2, oldY-2);
	  for(int i=1; i<n; i++) {
		  X = (int)pointx[i];
		  //Y = h1 - (int)pointy[i];
		  Y = (int)pointy[i];
		  graphics.drawLine(oldX, oldY, X, Y);
		  oldX = X;
		  oldY = Y;
		  //str = "("+oldX+","+oldY+")";
		  //graphics.drawString(str, oldX+2, oldY-2);
	  }
	
}

@Override
public void mouseMoved(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
public void paint(Graphics g) {
	
	  g.drawImage(buffImage, 0, 0, this);
	//  showStatus( " " + x +"  " + y);
	}

public void update(Graphics g){ paint(g); }


public Thread thread;
protected long sleepAmount = 10;

public void start() {
    if (thread == null ) {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.setName("MyPanel" + " Demo");
        thread.start();
    }
}


public synchronized void stop() {
    if (thread != null) {
        thread.interrupt();
    }
    thread = null;
    notifyAll();
}


public void run() {

    Thread me = Thread.currentThread();

    while (thread == me && !isShowing() || getSize().width == 0) {
        try {
            thread.sleep(200);
        } catch (InterruptedException e) { }
    }

    while (thread == me) {
    	drawNextFrame();
        repaint();
        try {
            thread.sleep(sleepAmount);
        } catch (InterruptedException e) { }
    }
    thread = null;
}


}
