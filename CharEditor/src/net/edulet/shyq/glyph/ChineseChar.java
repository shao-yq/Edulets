package net.edulet.shyq.glyph;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ChineseChar extends CharComponent {
	public int getCode() {
		return code;
	}

	int code;
	public ChineseChar(int code) {
		this.code = code;
	}

	public boolean isValid() {
		return (code != 0);
	}
	public void reset(){
		super.reset();
		code = 0;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void save(DataOutputStream dos) throws IOException {
		dos.writeInt(code);
		super.save(dos);
	}

	public void load(DataInputStream dis) throws IOException {
		code = dis.readInt();
		super.load(dis);
	}
}
