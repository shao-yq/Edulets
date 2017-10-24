/*
 * BaseDialog.java
 *
 * Created on 2000��3��15��, ����8:44
 */

package net.edulet.common;

import java.awt.event.*;
import java.awt.Window;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.KeyStroke;


/**
 * BaseDialog is super class of all dialog-like component.
 * BaseDialog owns Ok, Cancel, Help, Apply buttons. These buttons could be set visible and invisible
 * in different apllication circumstance.
 * Because not all the dialog like component use buttons as Ok, Cancel, Apply, Help. These buttons
 * could be set difference appearance as needed.
 *
 * @author Shao Yong Qing
 */

public class BaseDialog extends javax.swing.JDialog
        implements ActionListener {
    ///** The HelpBroker for online help. */
    //protected javax.help.HelpBroker onlineBroker = null;

    /**
     * Creates new form BaseDialog
     */
    public BaseDialog(java.awt.Frame parent, boolean modal) {
        this(parent, "Dialog", modal);
    }

    /**
     * Constructor with modal setting
     */
    public BaseDialog(java.awt.Frame owner, String title, boolean modal) {
        this(owner, title, modal, null, null, null, null);
    }

    /**
     * Constructor with listeners to detect Ok, Cancel, Help, Apply buttons pressed event
     *
     * @param owner          the parent component for the dialog
     * @param title          the title for the dialog
     * @param modal          a boolean. When true, the remainder of the program
     *                       is inactive until the dialog is closed.
     * @param okListener     the ActionListener invoked when "OK" is pressed
     * @param cancelListener the ActionListener invoked when "Cancel" is pressed
     * @param helpListener   the ActionListener invoked when "Help" is pressed
     * @param applyListener  the ActionListener invoked when "Apply" is pressed
     */
    public BaseDialog(java.awt.Frame owner, String title, boolean modal,
                      ActionListener okListener, ActionListener cancelListener,
                      ActionListener helpListener, ActionListener applyListener) {
        super(owner, title, modal);
        initComponents();
        setTitle(title);
        getRootPane().setDefaultButton(okButton);
        if (okListener != null) {
            okButton.addActionListener(okListener);
        }
        if (cancelListener != null) {
            cancelButton.addActionListener(cancelListener);
        }
        if (helpListener != null) {
            helpButton.addActionListener(helpListener);
        }
        if (applyListener != null) {
            applyButton.addActionListener(applyListener);
        }
        addWindowListener(new BaseDialog.Closer());
        addComponentListener(new BaseDialog.DisposeOnClose());
        setButtonAlign(BUTTON_ALIGN_BOTTOM);


        //
        // The following few lines are used to register esc to close the dialog
        ActionListener cancelKeyAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //((AbstractButton)(e.getSource())).fireActionPerformed(e);
                cancelButtonProcess();
            }
        };
        KeyStroke cancelKeyStroke = KeyStroke.getKeyStroke((char) KeyEvent.VK_ESCAPE);
        cancelButton.registerKeyboardAction(cancelKeyAction, cancelKeyStroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        // end esc handling

        cancelButton.setActionCommand("cancel");
        if (cancelListener != null) {
            cancelButton.addActionListener(cancelListener);
        }
        //
        //pack ();
    }

    public void addOkListener(ActionListener listener) {
        okButton.addActionListener(listener);
    }

    public void addApplyListener(ActionListener listener) {
        applyButton.addActionListener(listener);
    }

    private void initComponents() {
        //placePanel = new javax.swing.JPanel ();
        //placePanel.setLayout(null);
        buttonPanel = new javax.swing.JPanel();
        okButton = (javax.swing.JButton) ShyqUI.createButton("BaseDialog.okButton");
        cancelButton = (javax.swing.JButton) ShyqUI.createButton("BaseDialog.cancelButton");
        helpButton = (javax.swing.JButton) ShyqUI.createButton("BaseDialog.helpButton");
        applyButton = (javax.swing.JButton) ShyqUI.createButton("BaseDialog.applyButton");

        //addWindowListener (new java.awt.event.WindowAdapter () {
        //    public void windowClosing (java.awt.event.WindowEvent evt) {
        //        closeDialog (evt);
        //    }
        //}
        //);
        FlowLayout layout = new FlowLayout();
        layout.setHgap(20);
        buttonPanel.setLayout(layout);
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(applyButton);

        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        applyButton.addActionListener(this);
        helpButton.addActionListener(this);
    }

    /**
     * Perform button pressed events.
     * We branch the processes to okButtonProcess(), cancelButtonProcess(), applyButtonProcess(),
     * applyButtonProcess(). So any subclass could override these four methods to accomodate their
     * special requirements.
     */
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == okButton) {
            okButtonProcess();
        } else if (source == cancelButton) {
            cancelButtonProcess();
        } else if (source == applyButton) {
            applyButtonProcess();
        } else if (source == helpButton) {
            helpButtonProcess();
        }
    }

    /**
     * Invokes this methode when Ok Button is pressed.
     * It only hide the dialog at this moment.
     */
    protected void okButtonProcess() {
        buttonPressed = BUTTON_OK;
    }

    /**
     * Invokes this methode when Apply Button is pressed.
     * Nothing happens in this class.
     */
    protected void applyButtonProcess() {
        buttonPressed = BUTTON_APPLY;
    }

    /**
     * Invokes this methode when Cancel Button is pressed.
     * It does nothing monre than hide the dialog.
     */
    public void cancelButtonProcess() {
        buttonPressed = BUTTON_CANCEL;
        setVisible(false);
    }

    /**
     * Invokes this methode when Help Button is pressed.
     * Nothing happens in this class.
     */
    public void helpButtonProcess() {
        buttonPressed = BUTTON_HELP;
    }

    public int showModal() {
        setVisible(true);
        return buttonPressed;
    }


    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {
        setVisible(false);
        dispose();
    }

    //protected javax.swing.JPanel  getPlacePanel(){
    //    return placePanel;
    //}

    public void setButtonAlign(int align) {
        buttonAlign = align;
        switch (buttonAlign) {
            case BUTTON_ALIGN_RIGHT:
                getContentPane().remove(buttonPanel);
                getContentPane().add(buttonPanel, java.awt.BorderLayout.EAST);
                //buttonPanel.setPreferredSize(new Dimension(120,500));
                buttonPanel.setPreferredSize(new Dimension(120, 150));
                okButton.setPreferredSize(new Dimension(108, 28));
                cancelButton.setPreferredSize(new Dimension(108, 28));
                helpButton.setPreferredSize(new Dimension(108, 28));
                applyButton.setPreferredSize(new Dimension(108, 28));
                break;
            case BUTTON_ALIGN_BOTTOM:
                getContentPane().remove(buttonPanel);
                getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);
                break;
            case BUTTON_ALIGN_LEFT:
                getContentPane().remove(buttonPanel);
                getContentPane().add(buttonPanel, java.awt.BorderLayout.WEST);
                break;
            case BUTTON_ALIGN_TOP:
                getContentPane().remove(buttonPanel);
                getContentPane().add(buttonPanel, java.awt.BorderLayout.NORTH);
                break;
        }
    }

    public int getButtonAlign() {
        return buttonAlign;
    }

    String helpID = null;

    /**
     * Set the help ID of this dialog.
     */
    public void setHelpID(String id) {
        helpID = id;
        // add for help
        //onlineBroker =
        //    com.everlasting.sgil.util.Utilities.getHelpOnlineBroker();
        //if (onlineBroker != null && id != null) {
        //    onlineBroker.enableHelpKey(this.getRootPane(), id, null);
        //    onlineBroker.enableHelpOnButton(helpButton, id, null);
        //}
    }

    /**
     * Get the help ID of this dialog.
     *
     * @return The help ID, or null if no help ID.
     */
    public String getHelpID() {
        return helpID;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new BaseDialog(new javax.swing.JFrame(), true).setVisible(true);
    }
    ///** A Panel to place all the other components other than Ok, Cancel, ... Buttons */
    //protected javax.swing.JPanel placePanel;    
    /**
     * A Panel to settle Buttons like Ok, Cancel, Help, Apply.
     */
    protected javax.swing.JPanel buttonPanel;
    /**
     * Ok button. Always refers to confirm action
     */
    protected javax.swing.JButton okButton;
    /**
     * Cancel button. Always refers to cancel current operation
     */
    protected javax.swing.JButton cancelButton;
    /**
     * Help button. Popups some help message, usually another dialog popups up
     */
    protected javax.swing.JButton helpButton;
    /**
     * When press Apply button, current state become effective immediately
     */
    protected javax.swing.JButton applyButton;

    protected int buttonAlign;
    protected int buttonPressed;
    /**
     * The Ok, Cancel, ... Buttons locate align right border of the dialog
     */
    public final static int BUTTON_ALIGN_RIGHT = 1;
    /**
     * The Ok, Cancel, ... Buttons locate align bottom border of the dialog
     */
    public final static int BUTTON_ALIGN_BOTTOM = 2;
    /**
     * The Ok, Cancel, ... Buttons locate align Left border of the dialog, rarely used
     */
    public final static int BUTTON_ALIGN_LEFT = 3;
    /**
     * The Ok, Cancel, ... Buttons locate align top border of the dialog, seldom used
     */
    public final static int BUTTON_ALIGN_TOP = 4;
    /**
     * Indicate last pressed button is Ok
     */
    public final static int BUTTON_OK = 1;
    /**
     * Indicate last pressed button is Apply
     */
    public final static int BUTTON_APPLY = 2;
    /**
     * Indicate last pressed button is Cancel
     */
    public final static int BUTTON_CANCEL = 3;
    /**
     * Indicate last pressed button is Help
     */
    public final static int BUTTON_HELP = 4;
    /**
     * Indicate last pressed button is Close
     */
    public final static int BUTTON_CLOSE = 5;

    static class Closer extends WindowAdapter implements Serializable {
        public void windowClosing(WindowEvent e) {
            Window w = e.getWindow();
            w.setVisible(false);
        }
    }

    static class DisposeOnClose extends ComponentAdapter implements Serializable {
        public void componentHidden(ComponentEvent e) {
            Window w = (Window) e.getComponent();
            w.dispose();
        }
    }

}