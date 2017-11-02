package net.edulet.shyq.glyph;

public class HorizonLayout extends CharLinearLayout {

    public CharComponent getLeft(){
        return left;
    }

    public CharComponent getRight(){
        return right;
    }
    public void setLeft(CharComponent left) {
        adjustComponentCount(this.left,  left);

        this.left = left;
    }

    public void setRight(CharComponent right) {
        adjustComponentCount(this.right,  right);

        this.right = right;
    }

}
