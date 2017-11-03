package net.edulet.shyq.glyph.editor.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Shao Yongqing
 * Date: 2017/11/3.
 */
public class CharEditor {
    public static void main(String[] args) {
        JFrame frame = new JFrame("");
        final CharComponentEditor editor = new CharComponentEditor(frame);
        String title = editor.getAppTitle();
        frame.setTitle(title);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
            //public void windowDeiconified(WindowEvent e) { editor.start(); }
            //public void windowIconified  (WindowEvent e) { editor.stop(); }
        });

        frame.setBackground(Color.lightGray);
        frame.getContentPane().setLayout(new BorderLayout());

        frame.add("Center", editor);
        frame.pack();
        frame.setSize(new Dimension(700, 700));
        frame.setVisible(true);

        //editor.start();
//        if (surface.animating != null) {
//            surface.animating.start();
//        }

    }
}
