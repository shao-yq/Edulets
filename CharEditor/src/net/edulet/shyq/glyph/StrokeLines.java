package net.edulet.shyq.glyph;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

public class StrokeLines extends StrokeBase {
	int code;
	public StrokeLines(){
		this(0, new Vector<Point>());
	}

	public StrokeLines(int code, Vector<Point> points) {
		super(STROKE_LINES, points);
		this.code = code;
	}

	public void save(DataOutputStream dos) throws IOException {
		dos.writeInt(code);
		super.save(dos);
	}

	public void load(DataInputStream dis)throws IOException {
		code = dis.readInt();

		super.load(dis);
	}
}
