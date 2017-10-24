package net.edulet.shyq.glyph.editor.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author Shao Yongqing
 * Date: 2017/10/24.
 */
public class StatusBar  extends JComponent {
    JLabel info;

    public void setStatusText(String text) {
        info.setText(text);
        info.revalidate();
    }

    public StatusBar() {
        super();
        info = new JLabel("Status: Idle");
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(info);
    }

    public void paint(Graphics g) {
        super.paint(g);
    }
}
