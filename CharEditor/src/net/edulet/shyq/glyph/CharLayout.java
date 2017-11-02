package net.edulet.shyq.glyph;

/**
 * @author Shao Yongqing
 * Date: 2017/10/30.
 */
public abstract class CharLayout {
    int code =0;
    public int getCode() {
        return code;
    }

    int compontCount=0;

    protected void setComponentCount(int compontCount){
        this.compontCount = compontCount;
    }

    public int getCompontCount(){
        return compontCount;
    }

    protected void adjustComponentCount(CharComponent orig, CharComponent comp){
        if(orig==null && comp!=null)
            compontCount++;
        else if(orig!=null && comp==null)
            compontCount--;
    }

    public abstract void setLayoutComponent(String name, CharComponent comp);
}
