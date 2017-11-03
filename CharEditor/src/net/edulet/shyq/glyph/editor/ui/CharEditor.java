package net.edulet.shyq.glyph.editor.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.text.TextAction;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import net.edulet.common.CommandActionManager;
import net.edulet.common.ShyqUI;

public class CharEditor extends JPanel implements MouseMotionListener, MouseListener {

    private static final String TITLE = "Title";
    private JTextComponent editor;
    private Hashtable commands;
    private JMenuBar menubar;
    private JToolBar toolbar;
    private JToolBar toolboxStroke;
    private JToolBar toolboxLayout;
    private StatusBar status;
    private JFrame elementTreeFrame;

    protected FileDialog fileDialog;

    CommandActionManager actionManager = new CommandActionManager();
    JButton[] toolButtons = new JButton[40];
    JButton[] layoutButtons = new JButton[12];

    /**
     * Listener for the edits on the current document.
     */
    protected UndoableEditListener undoHandler = new UndoHandler();

    class UndoHandler implements UndoableEditListener {

        /**
         * Messaged when the Document has created an edit, the edit is
         * added to <code>undo</code>, an instance of UndoManager.
         */
        public void undoableEditHappened(UndoableEditEvent e) {
            undo.addEdit(e.getEdit());
            undoAction.update();
            redoAction.update();
        }
    }


    /**
     * UndoManager that we add edits to.
     */
    protected UndoManager undo = new UndoManager();

    /**
     * Suffix applied to the key used in resource file
     * lookups for an image.
     */
    public static final String imageSuffix = "Image";

    /**
     * Suffix applied to the key used in resource file
     * lookups for a label.
     */
    public static final String labelSuffix = "Label";

    /**
     * Suffix applied to the key used in resource file
     * lookups for an action.
     */
    public static final String actionSuffix = "Action";

    /**
     * Suffix applied to the key used in resource file
     * lookups for tooltip text.
     */
    public static final String tipSuffix = "Tooltip";

    public static final String openAction = "open";
    public static final String newAction = "new";
    public static final String saveAction = "save";
    public static final String printAction = "print";
    public static final String exitAction = "exit";
    public static final String showElementTreeAction = "showElementTree";

    public static final String zoomoutAction = "zoomout";
    public static final String zoominAction = "zoomin";
    public static final String toolboxAction = "toolbox";
    public static final String statusbarAction = "statusbar";

    public static final String pickToolAction = "picktool";
    public static final String alterProjectAction = "alterProject";
    public static final String dataListAction = "dataList";

    EditorPanel editorPanel;

    public CharEditor(JFrame frame) {
        this.frame = frame;
        init(frame);
    }
    String getAppTitle(){
        return ShyqUI.getResourceString(TITLE);
    }

    EditorPanel createEditorPanel(int editMode) {
        EditorPanel panel = null;
        switch(editMode){
            case EDIT_STROKE:
                break;
            case EDIT_COMPONENT:
                panel = new CharEditorPanel("EditorPanel");
                break;
            case EDIT_CHARACTER:
                break;
        }
        return panel;
    }

    public final static int EDIT_NONE=0;
    public final static int EDIT_STROKE=1;
    public final static int EDIT_COMPONENT=2;
    public final static int EDIT_CHARACTER=3;

    private void init(JFrame frame) {
        int editorMode = EDIT_COMPONENT;
        editorPanel = createEditorPanel(editorMode);

        addMouseMotionListener(this);
        addMouseListener(this);

        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new BorderLayout());

        // create the embedded JTextComponent
        editor = createEditor();
        editor.setFont(new Font("monospaced", Font.PLAIN, 12));
        // Add this as a listener for undoable edits.
        editor.getDocument().addUndoableEditListener(undoHandler);

        // install the command table
        commands = actionManager.getCommands();
        Action[] actions = getActions();
        for (int i = 0; i < actions.length; i++) {
            Action a = actions[i];
            //commands.put(a.getText(Action.NAME), a);
            commands.put(a.getValue(Action.NAME), a);
        }
        ShyqUI.loadResources("resources.ShyqStrokeRes");
        menubar = ShyqUI.createMenubar(commands, "menubar");
        add(menubar, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JToolBar toolbar = ShyqUI.createToolbar(commands, "toolbar");

        panel.add(toolbar, BorderLayout.NORTH);
        toolboxStroke = (JToolBar) ShyqUI.createToolbox(commands, "toolboxStroke", 0, 5, toolButtons);
        panel.add(toolboxStroke, BorderLayout.WEST);
        for (int i = 0; i < toolButtons.length; i++) {
            JButton b = toolButtons[i];
            if (b != null) {
                Action a = getAction(pickToolAction);
                if (a != null) {
                    b.setActionCommand(pickToolAction);
                    b.addActionListener(a);
                    b.setEnabled(true);
                } else {
                    b.setEnabled(false);
                }
            }
        }

        toolboxLayout = (JToolBar) ShyqUI.createToolbox(commands, "toolboxLayout", 0, 5, layoutButtons);
        panel.add(toolboxLayout, BorderLayout.EAST);
        for (int i = 0; i < layoutButtons.length; i++) {
            JButton b = layoutButtons[i];
            if (b != null) {
                Action a = getAction(pickToolAction);
                if (a != null) {
                    b.setActionCommand(pickToolAction);
                    b.addActionListener(a);
                    b.setEnabled(true);
                } else {
                    b.setEnabled(false);
                }
            }
        }
        add(panel, BorderLayout.CENTER);
        panel.add(editorPanel, BorderLayout.CENTER);
        StatusBar statusBar = createStatusbar();
        add(createStatusbar(), BorderLayout.SOUTH);

        editorPanel.setStatusBar(statusBar);
        editorPanel.setToolBar(toolbar);
    }

    /**
     * Create a status bar
     */
    protected StatusBar createStatusbar() {
        // need to do something reasonable here
        status = new StatusBar();
        //status.setVisible(true);
        return status;
    }

    protected Action getAction(String cmd) {
        return (Action) commands.get(cmd);
    }


    /**
     * Fetch the list of actions supported by this
     * editor.  It is implemented to return the list
     * of actions supported by the embedded JTextComponent
     * augmented with the actions defined locally.
     */
    public Action[] getActions() {
        return TextAction.augmentList(editor.getActions(), defaultActions);
    }
    // --- action implementations -----------------------------------

    private UndoAction undoAction = new UndoAction();
    private RedoAction redoAction = new RedoAction();
    /**
     * Actions defined by the ShyqMicaps class
     */
    private Action[] defaultActions = {
            new NewAction(),
            new OpenAction(),
            new PrintAction(),
            new ExitAction(),
            new ToolboxAction(),
            new StatusbarAction(),
            //new ZoomoutAction(),
            //new ZoominAction(),
            new PickToolAction(),
            //new ShowElementTreeAction(),
            //new AlterProjectAction(),
            //new DataListAction(),
            undoAction,
            redoAction
    };

    class UndoAction extends AbstractAction {
        public UndoAction() {
            super("Undo");
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                undo.undo();
            } catch (CannotUndoException ex) {
                System.out.println("Unable to undo: " + ex);
                ex.printStackTrace();
            }
            update();
            redoAction.update();
        }

        protected void update() {
            if (undo.canUndo()) {
                setEnabled(true);
                putValue(Action.NAME, undo.getUndoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Undo");
            }
        }
    }

    class RedoAction extends AbstractAction {
        public RedoAction() {
            super("Redo");
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                undo.redo();
            } catch (CannotRedoException ex) {
                System.out.println("Unable to redo: " + ex);
                ex.printStackTrace();
            }
            update();
            undoAction.update();
        }

        protected void update() {
            if (undo.canRedo()) {
                setEnabled(true);
                putValue(Action.NAME, undo.getRedoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }

    /**
     * Really lame implementation of an exit command
     */
    class ExitAction extends AbstractAction {

        ExitAction() {
            super(exitAction);
        }

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    JFrame frame = null;

    public  JFrame getFrame() {
        return frame;
    }

    class OpenAction extends NewAction {

        OpenAction() {
            super(openAction);
        }

        public void actionPerformed(ActionEvent e) {

            Frame frame = getFrame();
            if (fileDialog == null) {
                fileDialog = new FileDialog(frame);
            }
            fileDialog.setMode(FileDialog.LOAD);
            fileDialog.setVisible(true);

            String fileName = fileDialog.getFile();
            if (fileName == null) {
                return;
            }
            String directory = fileDialog.getDirectory();
            File f = new File(directory, fileName);
            if (f.exists()) {
                fileName = directory + fileName;
                System.out.println("FileName[" + fileName + "]");
                //shyqGiis.read(fileName);

                revalidate();
                /***
                 Document oldDoc = getEditor().getDocument();
                 if(oldDoc != null)
                 oldDoc.removeUndoableEditListener(undoHandler);
                 getEditor().setDocument(new PlainDocument());
                 frame.setTitle(file);
                 Thread loader = new FileLoader(f, editor.getDocument());
                 loader.start();
                 ***/
            }
        }
    }

    class SaveAction extends AbstractAction {

        SaveAction() {
            super(saveAction);
        }

        SaveAction(String nm) {
            super(nm);
        }

        public void actionPerformed(ActionEvent e) {
            editorPanel.doSave();

        }
    }

    class NewAction extends AbstractAction {

        NewAction() {
            super(newAction);
        }

        NewAction(String nm) {
            super(nm);
        }

        public void actionPerformed(ActionEvent e) {
            Document oldDoc = getEditor().getDocument();
            if (oldDoc != null)
                oldDoc.removeUndoableEditListener(undoHandler);
            getEditor().setDocument(new PlainDocument());
            getEditor().getDocument().addUndoableEditListener(undoHandler);
            revalidate();
        }
    }

    class PrintAction extends AbstractAction {

        PrintAction() {
            super(printAction);
        }

        PrintAction(String nm) {
            super(nm);
        }

        public void actionPerformed(ActionEvent e) {
            PrinterJob printJob = PrinterJob.getPrinterJob();
            if (printJob.printDialog()) {
                PageFormat page = printJob.defaultPage();
                Paper paper = page.getPaper();
                System.out.print("paper:imageableHeight:" + paper.getImageableHeight());
                System.out.println("   imageableWidth" + paper.getImageableWidth());
                System.out.print("paper:Height" + paper.getHeight());
                System.out.println("   :Width" + paper.getWidth());
                paper.setImageableArea(60, 60, paper.getWidth() - 120, paper.getHeight() - 120);
                //page.setPaper(paper);

                page.setOrientation(PageFormat.LANDSCAPE);
                System.out.print("Page:imageableHeight:" + page.getImageableHeight());
                System.out.println("  Page:imageableWidth" + page.getImageableWidth());
                System.out.print("Page:Height" + page.getHeight());
                System.out.println("Page:Width" + page.getWidth());
                //printJob.setPrintable(shyqGiis,page);
                try {
                    printJob.print();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    boolean statusbarFlag = true;

    class StatusbarAction extends AbstractAction {
        StatusbarAction() {
            super(statusbarAction);
        }

        public void actionPerformed(ActionEvent e) {
            statusbarFlag = !statusbarFlag;
            status.setVisible(statusbarFlag);
            revalidate();
        }
    }

    boolean toolboxFlag = true;

    class ToolboxAction extends AbstractAction {
        ToolboxAction() {
            super(toolboxAction);
        }

        public void actionPerformed(ActionEvent e) {
            Frame frame = getFrame();
            toolboxFlag = !toolboxFlag;
            toolboxStroke.setVisible(toolboxFlag);
            revalidate();
        }
    }

    int currentTool = 0;
    int currentLayout = 0;
    int toolCount = 0;

    class PickToolAction extends AbstractAction {
        PickToolAction() {
            super(pickToolAction);
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println("PickToolAction ");
            Object source = e.getSource();
            for (int i = 0; i < toolButtons.length; i++) {
                if (source == toolButtons[i]) {
                    currentTool = i + 1;
                    System.out.println("Tool " + currentTool + " was picked");
                    editorPanel.pickTool(currentTool);
                    return;
                }
            }

            for (int i = 0; i < layoutButtons.length; i++) {
                if (source == layoutButtons[i]) {
                    currentLayout = i + 1;
                    System.out.println("Tool " + currentLayout + " was picked");
                    editorPanel.pickLayout(currentLayout);
                    return;
                }
            }

        }
    }



    /**
     * Create an editor to represent the given document.
     */
    protected JTextComponent createEditor() {
        return new JTextArea();
    }

    /**
     * Fetch the editor contained in this panel
     */
    protected JTextComponent getEditor() {
        return editor;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("");
        final CharEditor editor = new CharEditor(frame);
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


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
