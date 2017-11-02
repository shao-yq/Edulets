package net.edulet.shyq.glyph;

/**
 * @author Shao Yongqing
 * Date: 2017/10/24.
 */
public class CharLinearLayout extends CharLayout{
    /**
     * The north layout constraint (top of container).
     */
    public static final String NORTH  = "North";

    /**
     * The south layout constraint (bottom of container).
     */
    public static final String SOUTH  = "South";

    /**
     * The east layout constraint (right side of container).
     */
    public static final String EAST   = "East";

    /**
     * The west layout constraint (left side of container).
     */
    public static final String WEST   = "West";

    /**
     * The center layout constraint (middle of container).
     */
    public static final String CENTER = "Center";

    public static final String LEFT = "Left";
    public static final String UPPER = "Upper";

    public static final String RIGHT = "Right";
    public static final String LOWER = "Lower";

    CharComponent left;
    CharComponent right;

    CharComponent upper;
    CharComponent lower;
    CharComponent center;

    public void setLayoutComponent(String name, CharComponent comp) {
        /* Special case:  treat null the same as "Center". */
        if (name == null) {
            name = CENTER;
        }

        /* Assign the component to one of the known regions of the layout.
         */
        if (CENTER.equals(name)) {
            adjustComponentCount(center,  comp);

            center = comp;
        } else if (UPPER.equals(name)) {
            adjustComponentCount(upper,  comp);

            upper = comp;
        } else if (LOWER.equals(name)) {
            adjustComponentCount(lower,  comp);

            lower = comp;
        } else if (RIGHT.equals(name)) {
            adjustComponentCount(right,  comp);

            right = comp;
        } else if (LEFT.equals(name)) {
            adjustComponentCount(left,  comp);

            left = comp;
        } else {
            throw new IllegalArgumentException("cannot add to layout: unknown constraint: " + name);
        }
    }


    public CharComponent getCenter() {
        return center;
    }

    public void setCenter(CharComponent center) {
        this.center = center;
    }

}
