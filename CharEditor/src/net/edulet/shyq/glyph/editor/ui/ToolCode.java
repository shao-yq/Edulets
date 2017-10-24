package net.edulet.shyq.glyph.editor.ui;

/**
 * Class ToolCode
 * @author  Shao Yong Qing
 * @version 
 */
public class ToolCode
{
    // const value: Tool Code
    public final static short ToolNone = 0;
    public final static short ToolPointer = 1;
    public final static short  ToolNewLine = 2;
    public final static short  ToolModifyLine = 3;
    public final static short  ToolClosedContour=4;
    public final static short  ToolDelete  =5;
    public final static short  ToolText  =6;
    
    public final static short  ToolRain = 7; 
    public final static short  ToolMiddleRain = 8;
    public final static short  ToolBigRain = 9;
    public final static short  ToolHeavyRain = 10;
    public final static short  ToolSmallSnow = 11;
    public final static short  ToolMiddleSnow = 12;
    public final static short  ToolBigSnow =13;
    public final static short  ToolHeavySnow = 14;
    public final static short  ToolRainSnow =15;
    public final static short  ToolCenterGD  =16;
    public final static short  ToolCenterNL  =17;

    public final static short  ToolStrokeBase = 7; 
    public final static short  ToolStrokeHeng               = ToolStrokeBase+ 0;
    public final static short  ToolStrokeShu                = ToolStrokeBase+ 1;
    public final static short  ToolStrokeDian               = ToolStrokeBase+ 2;
    public final static short  ToolStrokeTi                 = ToolStrokeBase+ 3;
    public final static short  ToolStrokePie                = ToolStrokeBase+ 4;
    public final static short  ToolStrokeNa                 = ToolStrokeBase+ 5;

    public final static short  ToolStrokeHengZhe            = ToolStrokeBase+ 6;
    public final static short  ToolStrokeHengPie            = ToolStrokeBase+ 7;
    public final static short  ToolStrokeHengGou            = ToolStrokeBase+ 8;
    public final static short  ToolStrokeHengZheZhe         = ToolStrokeBase+ 9;
    public final static short  ToolStrokeHengZheGou         = ToolStrokeBase+10;
    public final static short  ToolStrokeHengZheZheZheGou   = ToolStrokeBase+11;
    public final static short  ToolStrokeHengZheWan         = ToolStrokeBase+12;
    public final static short  ToolStrokeHengZheWanGou      = ToolStrokeBase+13;
    public final static short  ToolStrokeHengZheTi          = ToolStrokeBase+14;
    public final static short  ToolStrokeHengZheZheGou      = ToolStrokeBase+15;
    public final static short  ToolStrokeHengZheZhePie      = ToolStrokeBase+16;

    public final static short  ToolStrokeShuGou             = ToolStrokeBase+17;
    public final static short  ToolStrokeShuZhe             = ToolStrokeBase+18;
    public final static short  ToolStrokeShuTi              = ToolStrokeBase+19;
    public final static short  ToolStrokeShuWan             = ToolStrokeBase+20;
    public final static short  ToolStrokeShuZheGou          = ToolStrokeBase+21;
    public final static short  ToolStrokeShuZhePie          = ToolStrokeBase+22;
    public final static short  ToolStrokeShuZheZhe          = ToolStrokeBase+23;
    public final static short  ToolStrokeShuZheZheGou       = ToolStrokeBase+24;
    
    public final static short  ToolStrokePieDian            = ToolStrokeBase+25;
    public final static short  ToolStrokePieZhe             = ToolStrokeBase+26;
    public final static short  ToolStrokeXieGou             = ToolStrokeBase+27;
    public final static short  ToolStrokeWoGou              = ToolStrokeBase+28;
    
    public final static short  ToolStrokeLast              = ToolStrokeWoGou;

    public static boolean isStrokeCode(int toolCode) {
    	if(toolCode>=ToolStrokeBase && toolCode<=ToolStrokeLast)
    		return true;
    	return false;
    }

}
