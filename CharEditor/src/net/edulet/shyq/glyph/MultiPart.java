package net.edulet.shyq.glyph;

import java.util.Vector;

public class MultiPart extends CharComponent {
    Vector<CharComponent> parts = new Vector<CharComponent>();
    
    public int size() {
    	return parts.size();
    }
    
}

