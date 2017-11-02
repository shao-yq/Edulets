package net.edulet.shyq.glyph;

public class SurroundLayout extends CharLayout {
    public static final String INNER = "Inner";
    public static final String OUTER = "Outer";

    CharComponent inner;
    CharComponent outer;

    public CharComponent getInner() {
        return inner;
    }

    public CharComponent getOuter() {
        return outer;
    }

    public void setInner(CharComponent inner) {
        adjustComponentCount(this.inner,  inner);
        this.inner = inner;
    }

    public void setOuter(CharComponent outer) {
        adjustComponentCount(this.outer,  outer);
        this.outer = outer;
    }

    public void setLayoutComponent(String name, CharComponent comp) {
        /* Special case:  treat null the same as "Center". */
        if (name == null) {
            name = INNER;
        }

        /* Assign the component to one of the known regions of the layout.
         */
        if (INNER.equals(name)) {
            adjustComponentCount(inner,  comp);

            inner = comp;
        } else if (OUTER.equals(name)) {
            adjustComponentCount(outer,  comp);

            outer = comp;
        }  else {
            throw new IllegalArgumentException("cannot add to layout: unknown constraint: " + name);
        }
    }

}
