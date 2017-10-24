/*
 * SearchInfo.java
 *
 * Created on 2000��4��8��, ����9:11
 */
package net.edulet.common;

/**
 * @author York Shao
 */

public class SearchInfo {

    public final static int DOWN = 1;
    public final static int UP = 2;

    public String what;
    public boolean caseSensitive;
    public boolean wholeWord;
    public boolean regularExpression;
    public int direction;

    /**
     * Construct with default value.
     */
    public SearchInfo() {
        what = "";
        caseSensitive = false;
        wholeWord = false;
        regularExpression = false;
        direction = DOWN;
    }

    /**
     * Construct with specific value.
     */
    public SearchInfo(String what, boolean caseSensitive, boolean wholeWord,
                      boolean regularExpression, int direction) {
        this.what = what;
        this.caseSensitive = caseSensitive;
        this.wholeWord = wholeWord;
        this.regularExpression = regularExpression;
        this.direction = direction;
    }

    /**
     * Reset the status
     */
    public void reset() {
        what = "";
        caseSensitive = false;
        wholeWord = false;
        regularExpression = false;
        direction = DOWN;
    }

}
    
