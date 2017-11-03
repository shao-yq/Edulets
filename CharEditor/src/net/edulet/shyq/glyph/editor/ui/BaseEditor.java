package net.edulet.shyq.glyph.editor.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * @author Shao Yongqing
 * Date: 2017/11/3.
 */
public abstract class BaseEditor extends JPanel{
    protected EditPanel editPanel;
    protected JToolBar toolBar;
    protected JToolBar toolBox;
    //protected JToolBar toolBox2;

    public EditPanel getEditPanel() {
        return editPanel;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public JToolBar getToolBox() {
        return toolBox;
    }

    JFrame frame = null;

    public  JFrame getFrame() {
        return frame;
    }

    public BaseEditor(JFrame frame) {
        this.frame = frame;
        initCmoponents(frame);
    }

    protected void initCmoponents(JFrame frame) {
        initResources();

        setLayout(new BorderLayout());

        editPanel = createEditorPanel();  // new CharComponentEditPanel("EditPanel");
        add(editPanel, BorderLayout.CENTER);

        toolBar = createToolBar();
        if(toolBar!=null) {
            add(toolBar, BorderLayout.NORTH);
        }

        toolBox = createToolBox();
        if(toolBox!=null) {
            add(toolBox, BorderLayout.WEST);
        }

//        toolBox2 = createToolBox2();
//        if(toolBox2!=null) {
//            add(toolBox2, BorderLayout.EAST);
//        }
    }

    protected ResourceBundle resources = null;

    protected abstract ResourceBundle initResources();

    protected abstract EditPanel createEditorPanel();

    //protected abstract JToolBar createToolBox2();

    protected abstract JToolBar createToolBox();

    protected abstract JToolBar createToolBar() ;


}
