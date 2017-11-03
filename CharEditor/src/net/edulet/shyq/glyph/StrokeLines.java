package net.edulet.shyq.glyph;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StrokeLines extends StrokeBase {
	public StrokeLines(){
		this(0, new PolyLine());
	}

	public StrokeLines(int code, PointSet pointSet) {
		super(code, STROKE_LINES, pointSet);
	}

	public void save(DataOutputStream dos) throws IOException {
		super.save(dos);
	}

	public void load(DataInputStream dis)throws IOException {

		super.load(dis);
	}
}
