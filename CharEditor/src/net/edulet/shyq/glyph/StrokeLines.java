package net.edulet.shyq.glyph;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

public class StrokeLines extends StrokeBase {
	public StrokeLines(){
		this(0, new Vector<Point>());
	}

	public StrokeLines(int code, Vector<Point> points) {
		super(code, STROKE_LINES, points);
	}

	public void save(DataOutputStream dos) throws IOException {
		super.save(dos);
	}

	public void load(DataInputStream dis)throws IOException {

		super.load(dis);
	}
}
