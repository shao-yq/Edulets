package net.edulet.shyq.glyph;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public  class CharComponent implements GlyphBase {
	String name = "";
	String description = "";

	public void setComponents(Vector components) {
		this.components = components;
	}

	public Vector<CharComponent> getComponents() {
		return components;
	}

	double scaleX = 1;
	double scaleY = 1;
	Point anchor = new Point(0, 0);
	int layout;
	Vector<CharComponent> components = new Vector();;

	public void reset() {
		components.clear();
		scaleX = 1;
		scaleY = 1;
	}
	public void setAnchor(Point p) {
		anchor = p;
	}
	
	public Point getAnchor() {
		return anchor;
	}
	
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
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public void add(CharComponent component){

		components.add(component);
	}
	
	public void remove(CharComponent component){

		for(int i=0; i<components.size(); i++) {
			if(getChild(i) == component) {
				components.remove(i);
				break;
			}
		}
	}
	
	public CharComponent getChild(int index){

		if(components.size()<index)
			return null;
		
		return (CharComponent) components.get(index);
	}
	
	Enumeration createEnumeration() {

		return components.elements();
	}

	@Override
	public void draw(GraphicsContext gctx) {
		if(hasChild()) {
			drawChildren(gctx);
		}
	}

	protected void drawChildren(GraphicsContext gctx) {
		Enumeration enumeration = createEnumeration();
		
		while(enumeration.hasMoreElements()) {
			CharComponent component = (CharComponent) enumeration.nextElement();
			if(component != null) {
				component.draw(gctx);
			}
		}
	}

	
	protected boolean hasChild() {
		if(components == null || components.size()==0)
			return false;
		return true;
	}
	
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
    public boolean contains(double x, double y, double w, double h){
    	return false;
    }
    

    
    public boolean intersects(double x, double y, double w, double h){
    	return false;
    }
    
    public boolean intersects(Rectangle rect){
    	return false;
    }
    
    public Rectangle getBounds() {
    	if(hasChild()) {
    		Rectangle rect = null;
    		for(int i=0; i<components.size(); i++) {
    			Rectangle r = ((CharComponent)components.get(i)).getBounds();
    			if(rect != null) {
    				rect = rect.merge(r);
    			}
    		}
    		
    		rect.width *= scaleX;
    		rect.height *= scaleY;
    		rect.x *= scaleX;
    		rect.y *= scaleY;
    		
    		rect.move(anchor.x, anchor.y);
			return rect;
    	} else 
    		return new NullRectangle();
    }

	public boolean isOnContour(double x, double y) {
		
		return false;
	}

	public void move(double dx, double dy) {
		anchor.move(dx,  dy);
	}

	protected double toComponentX(double x, double y) {
		x -= anchor.x;
		return x/scaleX;
	}	
	protected double toComponentY(double x, double y) {
		y -= anchor.y;
		return y / scaleY;
	}

	public void scaleWithDelta(double dx, double dy) {
		Rectangle rect = getBounds();
		double sx = (rect.width+dx)/rect.width;
		double sy = (rect.height+dy)/rect.height;
		
		scaleX *= sx;
		scaleY *= sy;
	}
	
	public void scaleWithDX(double d) {
		scaleWithDelta(d, 0);
	}

	public void scaleWithDY(double d) {
		scaleWithDelta(0, d);
	}

	public void drawBounds(GraphicsContext gctx) {
		Rectangle rect = getBounds();
		//rect.x *= scaleX;
		//rect.y *= scaleY;
		//rect.width *= scaleX;
		//rect.height *= scaleY;

		gctx.draw(rect);
	}

	public void save(DataOutputStream dos) throws IOException {

		dos.writeUTF(name);
		dos.writeUTF(description);

		dos.writeDouble(scaleX);
		dos.writeDouble(scaleY);
		double ax = anchor.getX();
		double ay = anchor.getY();
		dos.writeDouble(ax);
		dos.writeDouble(ay);

		dos.writeInt(layout);
		// components
		dos.writeInt(components.size());
		for(int i=0; i<components.size(); i++){
			CharComponent component = components.get(i);
            String className = component.getClass().getCanonicalName();
            dos.writeUTF(className);
			component.save(dos);
		}
	}

	public void load(DataInputStream dis) throws IOException {

        name = dis.readUTF();
		description= dis.readUTF();

		scaleX = dis.readDouble();
		scaleY = dis.readDouble();

		anchor.setX(dis.readDouble());
		anchor.setY(dis.readDouble());

		layout = dis.readInt();
		// components
		int count = dis.readInt();
		for(int i=0; i<count; i++){
		    CharComponent component = null ;// new CharComponent(null);
		    // Class name
            String className =dis.readUTF();
            Class theClass = null;
            try {
                theClass = Class.forName(className);
                component = (CharComponent)theClass.newInstance();

                component.load(dis);
                add(component);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

		}
	}

}
