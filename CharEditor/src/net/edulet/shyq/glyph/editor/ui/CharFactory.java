package net.edulet.shyq.glyph.editor.ui;

import java.io.*;
import java.util.Vector;

import net.edulet.shyq.glyph.*;

public class CharFactory {
	
	public final static int STROKE_NONE = 0;
	public final static int STROKE_HENG = 1;
	public final static int STROKE_SHU = 2;
	public final static int STROKE_DIAN = 3;
	public final static int STROKE_PIE = 4;
	public final static int STROKE_NA = 5;
	public final static int STROKE_HengZhe = 6;
	public final static int STROKE_HengPie = 7;
	public final static int STROKE_HengGou = 8;
	public final static int STROKE_HengZheZhe = 9;
	public final static int STROKE_HengZheGou = 10;
	
	static int codeMap [][] = { {0, STROKE_NONE},
							{ToolCode.ToolStrokeHeng, STROKE_HENG},
							{ToolCode.ToolStrokeShu, STROKE_SHU},
							{ToolCode.ToolStrokeDian, STROKE_DIAN},
							{ToolCode.ToolStrokePie, STROKE_PIE},
							{ToolCode.ToolStrokeNa, STROKE_NA},
							{ToolCode.ToolStrokeHengZhe, STROKE_HengZhe},
							{ToolCode.ToolStrokeHengPie, STROKE_HengPie},
							{ToolCode.ToolStrokeHengGou, STROKE_HengGou},
							{ToolCode.ToolStrokeHengZheZhe, STROKE_HengZheZhe},
							{ToolCode.ToolStrokeHengZheGou, STROKE_HengZheGou},
						};
	static StrokeBase baseStrokes[];
	private static int lookupStrokeCode(int toolCode) {
		for(int i=0; i<codeMap.length; i++) {
			if(codeMap[i][0]==toolCode)
				return codeMap[i][1];
		}
		return 0;
	}

	public static CharComponent getStrokeByToolCode(int toolCode) {
		int code = lookupStrokeCode(toolCode);
		return (CharComponent) new SimpleStrokeComponent(getStroke(code));
		
	}
	
	public static StrokeBase getStroke(int code) {
		if(baseStrokes == null) {
			initStrokes();
		}
		if(code != STROKE_NONE)
			return baseStrokes[code];
		else 
			return null;
	}
	
	private static void initStrokes() {
		baseStrokes = new StrokeBase[codeMap.length];
		for(int i=0; i<codeMap.length; i++) {
			baseStrokes[i] = createStroke(i);
		}
		
	}

	public static void  saveStrokes(String fileName) throws IOException {
		// Check file name
		if(fileName==null || fileName.trim().length()==0){
			return ;
		}

		// Setup file instance
		File f = new File(fileName);
		if(!f.exists()){
			f.createNewFile();
		}
		// Setup dos
		DataOutputStream  dos=new DataOutputStream(new FileOutputStream(f));
		saveStrokes( dos);

		dos.close();
	}

	static void  saveStrokes(DataOutputStream dos) throws IOException {
		dos.writeInt(codeMap.length);

		for(int i=0; i<codeMap.length; i++) {
			StrokeBase stroke = baseStrokes[i];
			String className = stroke.getClass().getCanonicalName();
			dos.writeUTF(className);

			stroke.save(dos);
		}
	}

	public static void  loadStrokes(String fileName) throws IOException {
		// Check file name
		if(fileName==null || fileName.trim().length()==0){
			return ;
		}

		// Setup file instance
		File f = new File(fileName);
		if(!f.exists()){
			return ;
		}
		// Setup dos
		DataInputStream  dis=new DataInputStream(new FileInputStream(f));
		loadStrokes( dis);

		dis.close();
	}

	static void  loadStrokes(DataInputStream dis) throws IOException {
		int count = dis.readInt();
		baseStrokes = new StrokeBase[count];
		for(int i=0; i<codeMap.length; i++) {
			String className =dis.readUTF();
			Class theClass = null;
			try {
				theClass = Class.forName(className);
				StrokeBase stroke = (StrokeBase)theClass.newInstance();

				stroke.load(dis);
				baseStrokes[i] = stroke;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
	}


	private static StrokeBase createStroke(int code) {
	    Vector stroke = new Vector();
	    StrokeBase componet = null;
		switch(code){
		    case STROKE_HENG:
//    		    stroke.add(new Point(10,60));
//    		    stroke.add(new Point(28,64));
//    		    stroke.add(new Point(104,52));
//    		    stroke.add(new Point(119,58));
    		    
    		    stroke.add(new Point(-54,-2));
    		    stroke.add(new Point(-36,2));
    		    stroke.add(new Point(40,-10));
    		    stroke.add(new Point(55,-4));

    		    break;
		    case STROKE_SHU:
//		        stroke.add(new Point(52,13));
//		        stroke.add(new Point(58,23));
//		        stroke.add(new Point(58,100));
//		        stroke.add(new Point(59,108));
		        stroke.add(new Point(-12,-51));
		        stroke.add(new Point(-6,-41));
		        stroke.add(new Point(-6,36));
		        stroke.add(new Point(-5,42));
		        break;
		    case STROKE_DIAN:
//		        stroke.add(new Point(49,67));
//		        stroke.add(new Point(75,84));
//		        stroke.add(new Point(77,93));
		        stroke.add(new Point(-15,-10));
		        stroke.add(new Point(11,7));
		        stroke.add(new Point(12,16));
		    	break;
		    case STROKE_PIE:
//		        stroke.add(new Point(59,16));
//		        stroke.add(new Point(67,25));
//		        stroke.add(new Point(63,72));
//		        stroke.add(new Point(57,84));
//		        stroke.add(new Point(38,102));
//		        stroke.add(new Point(27,109));

		        stroke.add(new Point(-5,-48));
		        stroke.add(new Point(3,-39));
		        stroke.add(new Point(-1,8));
		        stroke.add(new Point(-7,20));
		        stroke.add(new Point(-26,38));
		        stroke.add(new Point(-37,45));
		        break;
		    case STROKE_NA:
//		        stroke.add(new Point(23,15));
//		        stroke.add(new Point(32,18));
//		        stroke.add(new Point(40,52));
//		        stroke.add(new Point(63,84));
//		        stroke.add(new Point(88,97));
//		        stroke.add(new Point(106,97));

		        stroke.add(new Point(-41,-49));
		        stroke.add(new Point(-32,-46));
		        stroke.add(new Point(-24,-12));
		        stroke.add(new Point(-1,20));
		        stroke.add(new Point(24,33));
		        stroke.add(new Point(42,33));
		        break;
		    case STROKE_HengZhe:
//    		    stroke.add(new Point(10,18));
//    		    stroke.add(new Point(22,21));
//    		    stroke.add(new Point(72,11));
//    		    stroke.add(new Point(84,17));
//    		    stroke.add(new Point(80,25));
//    		    stroke.add(new Point(76,92));
    		    
    		    stroke.add(new Point(-40,-32));
    		    stroke.add(new Point(-28,-29));
    		    stroke.add(new Point(22,-39));
    		    stroke.add(new Point(34,-33));
    		    stroke.add(new Point(30,-25));
    		    stroke.add(new Point(26,42));
		    	break;
		    case STROKE_HengPie:
//    		    stroke.add(new Point(23,16));
//    		    stroke.add(new Point(73,12));
//    		    stroke.add(new Point(78,16));
//    		    stroke.add(new Point(71,38));
//    		    stroke.add(new Point(62,59));
//    		    stroke.add(new Point(45,77));
//    		    stroke.add(new Point(23,88));
//    		    stroke.add(new Point(9, 90));
    		    
    		    stroke.add(new Point(-27,-34));
    		    stroke.add(new Point( 23,-38));
    		    stroke.add(new Point( 28,-34));
     		    stroke.add(new Point( 21,-12));
    		    stroke.add(new Point( 12,  9));
    		    stroke.add(new Point(-5,27));
    		    stroke.add(new Point(-27,38));
    		    stroke.add(new Point(-41,40));
		    	break;
		    case STROKE_HengGou:
//    		    stroke.add(new Point(10,47));
//    		    stroke.add(new Point(21,50));
//    		    stroke.add(new Point(76,43));
//    		    stroke.add(new Point(82,47));
//    		    stroke.add(new Point(67,62));
    		    
    		    stroke.add(new Point(-40,-3));
    		    stroke.add(new Point(-29, 0));
    		    stroke.add(new Point(26,-7));
    		    stroke.add(new Point(32,-3));
    		    stroke.add(new Point(17,12));

    		    break;
		    case STROKE_HengZheGou:
//    		    stroke.add(new Point(10,15));
//    		    stroke.add(new Point(21,18));
//    		    stroke.add(new Point(72,9));
//    		    stroke.add(new Point(84,15));
//    		    stroke.add(new Point(80,20));
//    		    stroke.add(new Point(76,80));
//    		    stroke.add(new Point(67,89));
//    		    stroke.add(new Point(53,79));
    		    
    		    stroke.add(new Point(-40,-35));
    		    stroke.add(new Point(-29,-32));
    		    stroke.add(new Point(22,-41));
    		    stroke.add(new Point(34,-35));
    		    stroke.add(new Point(30,-30));
    		    stroke.add(new Point(26,30));
    		    stroke.add(new Point(17,39));
    		    stroke.add(new Point( 3,29));
		    	break;
		    default:
		        System.out.println("Not supported Code:"+code);
		        break;
		}
		if(stroke != null)
			componet = new StrokeLines(code, stroke);
		return componet;
	}

    public static CharLinearLayout getLayout(int code) {
		CharLinearLayout layout = null;
		switch (code){
			default:
				layout = new CharLinearLayout();
		}
		return layout;
    }

	public static void main(String[] args){
		String strokeFile = "MyStrokes.dat";
		//initStrokes();
		// Save the strokes to file
		//testSave(strokeFile);
		// Load the strokes from file

		testLoad(strokeFile);
	}

	public static void testSave(String fileName){
		try {
			saveStrokes(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testLoad(String fileName){
		try {
			loadStrokes(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
