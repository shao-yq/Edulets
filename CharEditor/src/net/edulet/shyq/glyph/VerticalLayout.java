package net.edulet.shyq.glyph;

public class VerticalLayout extends CharLinearLayout {
    public CharComponent getUpper() {
        return upper;
    }

    public CharComponent getLower() {
        return lower;
    }

    public void setUpper(CharComponent upper) {
        adjustComponentCount(this.upper,  upper);
        this.upper = upper;
    }

    public void setLower(CharComponent lower) {
        adjustComponentCount(this.lower,  lower);

        this.lower = lower;
    }

}
