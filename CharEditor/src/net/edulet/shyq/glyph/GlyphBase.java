package net.edulet.shyq.glyph;

public interface GlyphBase {
	String getName();
	
	String getDescription();

	void draw(GraphicsContext gctx);
	
    boolean contains(double x, double y);
    
    boolean contains(double x, double y, double w, double h);
    
    boolean contains(Point p);
    
    boolean intersects(double x, double y, double w, double h);
    
    boolean intersects(Rectangle rect);
    
    Rectangle getBounds();
    
    
}
