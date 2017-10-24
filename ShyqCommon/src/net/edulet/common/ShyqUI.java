/*
 * @(#)ShyqUI.java	
 */
package net.edulet.common;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.net.URL;
import java.net.MalformedURLException;

import java.util.Hashtable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.KeyStroke;


/**
 * ShyqUI that manage GUI resources.
 *
 * @author Shao Yong Qing
 */
public class ShyqUI {

    private static ResourceBundle resources = null;
    private static ResourceBundle shyqResources = null;

    public static void setResourceBundle(ResourceBundle resource) {
        resources = resource;
    }

    public static void setResourceBundle(String resourceName) {
        try {
            resources = ResourceBundle.getBundle(resourceName, Locale.getDefault());
        } catch (MissingResourceException mre) {
            System.err.println(resourceName + " not found");
            //System.exit(1);
        }
    }

    public static String getAppRoot() {
        if (appRoot == null)
            appRoot = System.getProperty(APP_ROOT);
        return appRoot;
    }

    /**
     * Suffix applied to the key used in resource file
     * lookups for an image.
     */
    public static final String imageSuffix = ".image";

    /**
     * Suffix applied to the key used in resource file
     * lookups for a label.
     */
    public static final String labelSuffix = ".label";

    /**
     * Suffix applied to the key used in resource file
     * lookups for a label.
     */
    public static final String textSuffix = ".text";

    /**
     * Suffix applied to the key used in resource file
     * lookups for a Mnemonic.
     */
    public static final String mnemonicSuffix = ".mnemonic";

    /**
     * Suffix applied to the key used in resource file
     * lookups for a Shortcut key (Accelerator).
     */
    public static final String acceleratorSuffix = ".accelerator";

    /**
     * Suffix applied to the key used in resource file
     * lookups for a MenuItem type.
     */
    public static final String typeSuffix = ".type";

    /**
     * Suffix applied to the key used in resource file
     * lookups for a CheckBox MenuItem initial value.
     */
    public static final String valueSuffix = ".value";

    /**
     * Suffix applied to the key used in resource file
     * lookups for an action.
     */
    public static final String actionSuffix = "Action";

    /**
     * Suffix applied to the key used in resource file
     * lookups for tooltip text.
     */
    public static final String tipSuffix = ".tooltip";

    public static String INFO = "Information";
    public static String WARNING = "Warning";
    public static String ERROR = "Error";


    protected static String appRoot = null;

    public static String getImageDir() {
        if (imageDir == null)
            imageDir = getAppRoot() + IMAGE_DIR + File.separator;
        return imageDir;
    }

    public final static String APP_ROOT = "APP_ROOT";

    public final static String LIB_DIR = "lib";

    //public final static String IMAGE_DIR = "images";
    public final static String IMAGE_DIR = ".";
    protected static String imageDir = null;


    private ShyqUI(boolean b) {
    }

    public ShyqUI() {
        if (shyqResources == null)
            loadShyqResources(defaultResourceName, Locale.getDefault());
        menuItems = new Hashtable();
    }

    static String defaultResourceName = "net.edulet.common.ShyqUIRes";

    static {
        loadResources(defaultResourceName);
    }

    public static void loadResources() {
        loadResources(defaultResourceName);
    }

    public static void loadResources(String resourceName) {
        loadResources(resourceName, Locale.getDefault());
    }


    public static void loadResources(String resourceName, Locale locale) {
        try {
            resources = ResourceBundle.getBundle(resourceName, locale);
            //installResources();
        } catch (MissingResourceException mre) {
            JOptionPane.showMessageDialog(new JFrame(), resourceName + " not found.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void loadShyqResources() {
        loadShyqResources(defaultResourceName);
    }

    private static void loadShyqResources(String resourceName) {
        loadShyqResources(resourceName, Locale.getDefault());
    }

    private static void loadShyqResources(String resourceName, Locale locale) {
        try {
            shyqResources = ResourceBundle.getBundle(resourceName, locale);
            //installResources();
        } catch (MissingResourceException mre) {
            JOptionPane.showMessageDialog(new JFrame(), resourceName + " not found.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected static void installResources() {
        INFO = getResourceString("ShyqUI.INFO");
        WARNING = getResourceString("ShyqUI.WARNING");
        ERROR = getResourceString("ShyqUI.ERROR");
    }

    public static Component createToolbox(Hashtable commands, String toolbarName,
                                          int row, int col, JButton[] buttons) {
        return createToolbox(resources, commands, toolbarName, row, col, buttons);
    }

    public static Component createToolbox(ResourceBundle resources, Hashtable commands,
                                          String toolbarName, int row, int col, JButton[] buttons) {
        JToolBar toolbar = new JToolBar();
        toolbar.setLayout(new GridLayout(row, col));
        //toolbar.setBorder(BorderFactory.createEtchedBorder());
        String[] toolKeys = tokenize(getResourceString(resources, toolbarName));
        for (int i = 0; i < toolKeys.length; i++) {
            if (toolKeys[i].equals("-")) {
                toolbar.add(Box.createHorizontalStrut(5));
            } else {
                buttons[i] = createToolbarButton(resources, commands, toolKeys[i]);
                toolbar.add(buttons[i]);
            }
        }
        toolbar.add(Box.createHorizontalGlue());
        return toolbar;
    }

    /**
     * Create the toolbar.  By default this reads the
     * resource file for the definition of the toolbar.
     */
    public static JToolBar createToolbar(Hashtable commands, String toolbarName) {
        return createToolbar(resources, commands, toolbarName);
    }

    public static JToolBar createToolbar(ResourceBundle resources, Hashtable commands, String toolbarName) {
        JToolBar toolbar = new JToolBar();
        //toolbar.setBorder(BorderFactory.createEtchedBorder());
        String[] toolKeys = tokenize(getResourceString(resources, toolbarName));
        for (int i = 0; i < toolKeys.length; i++) {
            if (toolKeys[i].equals("-")) {
                toolbar.add(Box.createHorizontalStrut(5));
            } else {
                toolbar.add(createToolbarButton(resources, commands, toolKeys[i]));
            }
        }
        toolbar.add(Box.createHorizontalGlue());
        return toolbar;
    }

    //protected String getResourceString(String nm) {
    public static String getResourceString(String nm) {
        String str = null;
        if (resources != null)
            str = getResourceString(resources, nm);
        if (str == null) {
            if (shyqResources == null)
                loadShyqResources();
            str = getResourceString(shyqResources, nm);
        }
        return str;
    }

    public static String getResourceString(ResourceBundle resources, String nm) {
        String str = null;
        if (resources != null) {
            try {
                str = resources.getString(nm);
            } catch (MissingResourceException mre) {
            }
        }
        return str;
    }

    /**
     * If the value of <code>key</code> is a String return it, otherwise
     * return null.
     */
    public static String getString(String key) {
        return getResourceString((String) key);
    }

    /**
     * If the value of <code>key</code> is a Integer return its
     * integer value, otherwise return 0.
     */
    public static char getChar(ResourceBundle resources, String key) {
        String value = getResourceString(resources, key);
        if (value == null)
            return 0;
        if (value.length() == 0)
            return 0;
        return value.charAt(0);
    }

    public static char getChar(String key) {
        char ch = 0;
        if (resources != null)
            ch = getChar(resources, key);
        if (ch == 0)
            ch = getChar(shyqResources, key);
        return ch;
    }

    public static URL getResource(String key) {
        URL url = null;
        if (resources != null)
            url = getResource(resources, key);
        if (url == null)
            url = getResource(shyqResources, key);
        return url;
    }

    public static URL getResource(ResourceBundle resources, String key) {
        String name = getResourceString(resources, key);
        if (name != null) {
            String imageName = System.getProperty(APP_ROOT) + IMAGE_DIR + File.separator + name;
            System.out.println("ImageName=" + imageName);
            //return ClassLoader.getSystemResource(name);
            //URL url = this.getClass().getResource(resources, name);
            //ShyqUI gui = new ShyqUI(true);
            //URL url = gui.getClass().getResource(imageName);
            URL url = null;
            try {
                url = new URL(imageName);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


            return url;
        }
        return null;
    }

    /**
     * Hook through which every toolbar item is created.
     */
    public static Component createTool(Hashtable commands, String key) {
        Component component = null;
        if (resources != null)
            component = createTool(resources, commands, key);
        if (component == null)
            component = createTool(shyqResources, commands, key);
        return component;
    }

    public static Component createTool(ResourceBundle resources, Hashtable commands, String key) {
        return createToolbarButton(resources, commands, key);
    }


    /**
     * Create the menubar for the app.  By default this pulls the
     * definition of the menu from the associated resource file.
     */
    public static JMenuBar createMenubar(Hashtable commands, String menuName) {
        JMenuBar menuBar = null;
        if (resources != null)
            menuBar = createMenubar(resources, commands, menuName);
        if (menuBar == null)
            menuBar = createMenubar(shyqResources, commands, menuName);
        return menuBar;
    }

    public static JMenuBar createMenubar(ResourceBundle resources,
                                         java.util.Hashtable commands, String menuName) {
        JMenuItem mi;
        JMenuBar mb = new JMenuBar();
        mb.setBorder(BorderFactory.createEtchedBorder());
        String[] menuKeys = tokenize(getResourceString(resources, menuName));
        for (int i = 0; i < menuKeys.length; i++) {
            JMenu m = createMenu(resources, commands, menuKeys[i]);
            if (m != null) {
                mb.add(m);
            }
        }
        return mb;
    }

    /**
     * Create a menu for the app.  By default this pulls the
     * definition of the menu from the associated resource file.
     */
    public static JMenu createMenu(Hashtable commands, String key) {
        JMenu menu = null;
        if (resources != null)
            menu = createMenu(resources, commands, key);
        if (menu == null)
            menu = createMenu(shyqResources, commands, key);
        return menu;
    }

    public static JMenu createMenu(ResourceBundle resources, Hashtable commands, String key) {
        String[] itemKeys = tokenize(getResourceString(resources, key));
        boolean checkBoxFlag = false;
        JMenu menu = new JMenu(getResourceString(resources, key + textSuffix));
        String mnemo = getResourceString(resources, key + mnemonicSuffix);
        if (key.equals("view"))
            checkBoxFlag = true;
        if (mnemo != null)
            menu.setMnemonic(mnemo.charAt(0));
        for (int i = 0; i < itemKeys.length; i++) {
            //System.out.println("item="+itemKeys[i]);
            if (itemKeys[i].equals("-")) {
                menu.addSeparator();
            } else {
                JMenuItem mi;
                mi = createMenuItem(resources, commands, itemKeys[i]);
                menu.add(mi);
            }
        }
        return menu;
    }

    /**
     * Create Button.
     * Set text, mnenomic, tooltip text fetched from resources.
     */
    public static javax.swing.AbstractButton createButton(String name) {
        javax.swing.AbstractButton button = null;
        String text = getResourceString(name + typeSuffix);
        if (text != null) {
            if (text.equals("CheckBox")) {
                button = new javax.swing.JCheckBox();
            } else if (text.equals("RadioButton"))
                button = new javax.swing.JRadioButton();
            else //if(text.equals("Button"))
                button = new javax.swing.JButton();
        } else
            button = new javax.swing.JButton();

        button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button.setAlignmentY(JComponent.CENTER_ALIGNMENT);

        text = getResourceString(name + textSuffix);
        if (text != null)
            button.setText(text);
        String mnemonic = getResourceString(name + mnemonicSuffix);
        if (mnemonic != null)
            button.setMnemonic(mnemonic.charAt(0));
        String toolTipText = getResourceString(name + tipSuffix);
        if (toolTipText != null)
            button.setToolTipText(toolTipText);
        return button;
    }

    /**
     * Set component text , together with mnemonic if exists.
     */
    public static void setComponentText(javax.swing.JComponent component, String name) {
        String text = getResourceString(name + textSuffix);
        if (text != null)
            if (component instanceof javax.swing.AbstractButton) {
                ((javax.swing.AbstractButton) component).setText(text);
            } else if (component instanceof javax.swing.JLabel) {
                ((javax.swing.JLabel) component).setText(text);
            }
        int mnemonic = getChar(name + mnemonicSuffix);
        if (mnemonic != 0)
            if (component instanceof javax.swing.AbstractButton) {
                ((javax.swing.AbstractButton) component).setMnemonic(mnemonic);
            } else if (component instanceof javax.swing.JLabel) {
                ((javax.swing.JLabel) component).setDisplayedMnemonic(mnemonic);
            }
    }

    /**
     * Create Button.
     * Set text, mnenomic, tooltip text fetched from resources.
     */
    public static javax.swing.JLabel createLabel(String name) {
        javax.swing.JLabel label = new javax.swing.JLabel();

        String text = getResourceString(name + textSuffix);
        if (text != null)
            label.setText(text);
        String mnemonic = getResourceString(name + mnemonicSuffix);
        if (mnemonic != null)
            label.setDisplayedMnemonic(mnemonic.charAt(0));
        //String toolTipText = getResourceString(name+tipSuffix);
        //if(toolTipText != null)
        //    label.setToolTipText(toolTipText);
        label.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        label.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        return label;
    }


    /**
     * This is the hook through which all menu items are
     * created.  It registers the result with the menuitem
     * hashtable so that it can be fetched with getMenuItem().
     *
     * @see #createMenuItem
     */
    public static JMenuItem createMenuItem(java.util.Hashtable commands, String cmd) {
        JMenuItem menuItem = null;
        if (resources != null)
            menuItem = createMenuItem(resources, commands, cmd);
        if (menuItem == null)
            menuItem = createMenuItem(shyqResources, commands, cmd);
        return menuItem;
    }

    public static JMenuItem createMenuItem(java.util.ResourceBundle resources, java.util.Hashtable commands, String cmd) {
        String type = getResourceString(resources, cmd + typeSuffix);

        JMenuItem mi = null;
        if (type != null) {
            if (type.equals("CheckBox")) {
                mi = new JCheckBoxMenuItem(getResourceString(resources, cmd + textSuffix));
                String initValue = getResourceString(resources, cmd + valueSuffix);
                if (initValue != null) {
                    if (initValue.equals("Selected"))
                        ((JCheckBoxMenuItem) mi).setState(true);
                }
            } else if (type.equals("RadioButton"))
                mi = new JRadioButtonMenuItem(getResourceString(resources, cmd + textSuffix));
            else if (type.equals("Menu"))
                mi = new JMenu(getResourceString(resources, cmd + textSuffix));
        } else
            mi = new JMenuItem(getResourceString(resources, cmd + textSuffix));
        String mnemo = getResourceString(resources, cmd + mnemonicSuffix);
        if (mnemo != null)
            mi.setMnemonic(mnemo.charAt(0));

        String accelerator = getResourceString(resources, cmd + acceleratorSuffix);
        if (accelerator != null)
            mi.setAccelerator(KeyStroke.getKeyStroke(accelerator));

        //    URL url = getResource(resources,cmd + imageSuffix);
        //if (url != null) {
        //    mi.setHorizontalTextPosition(JButton.RIGHT);
        //    mi.setIcon(new ImageIcon(url));
        //}
        String name = getResourceString(resources, cmd + imageSuffix);
        String imageName = System.getProperty(APP_ROOT) + IMAGE_DIR + File.separator + name;
        mi.setIcon(new ImageIcon(imageName));
        String astr = getResourceString(resources, cmd + actionSuffix);
        if (astr == null) {
            astr = cmd;
        }

        mi.setActionCommand(astr);
        Action a = (Action) commands.get(astr);
        //Action a = getAction(astr);
        if (a != null) {
            mi.addActionListener(a);
            a.addPropertyChangeListener(createActionChangeListener(mi));
            mi.setEnabled(a.isEnabled());
            //mi.setSelected(a.isEnabled());
        } else {
            mi.setEnabled(false);
        }
        String tip = getResourceString(resources, cmd + tipSuffix);
        if (tip != null) {
            mi.setToolTipText(tip);
        }

        //mi.setEnabled(false);
        // menuItems.put(cmd, mi);
        return mi;
    }

    /**
     * Create a button to go inside of the toolbar.  By default this
     * will load an image resource.  The image filename is relative to
     * the classpath (including the '.' directory if its a part of the
     * classpath), and may either be in a JAR file or a separate file.
     *
     * @param key The key in the resource file to serve as the basis
     *            of lookups.
     */
    public static JButton createToolbarButton(java.util.Hashtable commands, String key) {
        JButton button = null;
        if (resources != null)
            button = createToolbarButton(resources, commands, key);
        if (button == null)
            button = createToolbarButton(shyqResources, commands, key);
        return button;
    }

    public static JButton createToolbarButton(java.util.ResourceBundle resources, java.util.Hashtable commands, String key) {
        //URL url = getResource(resources, key + imageSuffix);
        String name = getResourceString(resources, key + imageSuffix);
        //String imageName = System.getProperty(APP_ROOT)+IMAGE_DIR+File.separator+name;
        String imageName = name;
        JButton b = null;
        //if(url!= null)
        b = new JButton(new ImageIcon(imageName)) {
            public float getAlignmentY() {
                return 0.5f;
            }
        };
        //else
        //    b = new JButton(key);
        b.setRequestFocusEnabled(false);
        b.setMargin(new Insets(1, 1, 1, 1));

        String astr = getResourceString(resources, key + actionSuffix);
        if (astr == null) {
            astr = key;
        }
        Action a = (Action) commands.get(astr);
        //Action a = getAction(astr);
        if (a != null) {
            b.setActionCommand(astr);
            a.addPropertyChangeListener(createActionChangeListener(b));
            b.addActionListener(a);
        } else {
            b.setEnabled(false);
        }

        String tip = getResourceString(resources, key + tipSuffix);
        if (tip != null) {
            b.setToolTipText(tip);
        }
        //b.setEnabled(false);
        return b;
    }

    /**
     * Take the given string and chop it up into a series
     * of strings on whitespace boundries.  This is useful
     * for trying to get an array of strings out of the
     * resource file.
     */
    public static String[] tokenize(String input) {
        Vector v = new Vector();
        StringTokenizer t = new StringTokenizer(input);
        String cmd[];

        while (t.hasMoreTokens())
            v.addElement(t.nextToken());
        cmd = new String[v.size()];
        for (int i = 0; i < cmd.length; i++)
            cmd[i] = (String) v.elementAt(i);

        return cmd;
    }

    /**
     * Fetch the menu item that was created for the given
     * command.
     *
     * @param cmd  Name of the action.
     * @returns item created for the given command or null
     * if one wasn't created.
     */
    //protected JMenuItem getMenuItem(Hashtable, menuItems,String cmd) {
    //return (JMenuItem) menuItems.get(cmd);
    //}

    private Hashtable menuItems;


    // Yarked from JMenu, ideally this would be public.
    protected static PropertyChangeListener createActionChangeListener(JMenuItem b) {
        return new ActionChangedListener(b);
    }

    protected static PropertyChangeListener createActionChangeListener(JButton b) {
        return new ActionChangedListener(b);
    }


    //
    // Option types. Assigned with value from JOptionPane.
    //
    /**
     * Type meaning look and feel should not supply any options -- only
     * use the options from the JOptionPane.
     */
    public static final int DEFAULT_OPTION = JOptionPane.DEFAULT_OPTION;
    /**
     * Type used for showConfirmDialog.
     */
    public static final int YES_NO_OPTION = JOptionPane.YES_NO_OPTION;
    /**
     * Type used for showConfirmDialog.
     */
    public static final int YES_NO_CANCEL_OPTION = JOptionPane.YES_NO_CANCEL_OPTION;
    /**
     * Type used for showConfirmDialog.
     */
    public static final int OK_CANCEL_OPTION = JOptionPane.OK_CANCEL_OPTION;

    //
    // Return values. Assigned with value from JOptionPane.
    //
    /**
     * Return value from class method if YES is chosen.
     */
    public static final int YES_OPTION = JOptionPane.YES_OPTION;
    /**
     * Return value from class method if NO is chosen.
     */
    public static final int NO_OPTION = JOptionPane.NO_OPTION;
    /**
     * Return value from class method if CANCEL is chosen.
     */
    public static final int CANCEL_OPTION = JOptionPane.CANCEL_OPTION;
    /**
     * Return value form class method if OK is chosen.
     */
    public static final int OK_OPTION = JOptionPane.OK_OPTION;
    /**
     * Return value from class method if user closes window without selecting
     * anything, more than likely this should be treated as either a
     * CANCEL_OPTION or NO_OPTION.
     */
    public static final int CLOSED_OPTION = JOptionPane.CLOSED_OPTION;

    //
    // Message types. Used by the UI to determine what icon to display,
    // and possibly what behavior to give based on the type.
    // Assigned with value from JOptionPane.
    //
    /**
     * Used for error messages.
     */
    public static final int ERROR_MESSAGE = JOptionPane.ERROR_MESSAGE;
    /**
     * Used for information messages.
     */
    public static final int INFORMATION_MESSAGE = JOptionPane.INFORMATION_MESSAGE;
    /**
     * Used for warning messages.
     */
    public static final int WARNING_MESSAGE = JOptionPane.WARNING_MESSAGE;
    /**
     * Used for questions.
     */
    public static final int QUESTION_MESSAGE = JOptionPane.QUESTION_MESSAGE;
    /**
     * No icon is used.
     */
    public static final int PLAIN_MESSAGE = JOptionPane.PLAIN_MESSAGE;

    public static void showMessageDialog(Component parentComponent, Object message) {
        showMessageDialog(parentComponent, message, "Message", INFORMATION_MESSAGE);
    }

    public static void showMessageDialog(Component parentComponent,
                                         Object message,
                                         String title,
                                         int messageType) {
        showMessageDialog(parentComponent, message, title, messageType, null);
    }


    /**
     * Brings up a dialog displaying a message, specifying all parameters.
     *
     * @param parentComponent Determines the Frame in which the dialog is displayed.
     *                        If null, or if the parentComponent has no Frame, a
     *                        default Frame is used.
     * @param message         The Object to display
     * @param title           the title string for the dialog
     * @param messageType     the type of message to be displayed:
     *                        ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE,
     *                        QUESTION_MESSAGE, or PLAIN_MESSAGE.
     * @param icon            an icon to display in the dialog that helps the user
     *                        identify the kind of message that is being displayed.
     */
    public static void showMessageDialog(Component parentComponent, Object message,
                                         String title, int messageType,
                                         Icon icon) {
        JOptionPane.showOptionDialog(parentComponent, message, title, DEFAULT_OPTION,
                messageType, icon, null, null);
    }


    public static Object showInputDialog(Component parentComponent,
                                         Object message,
                                         String title,
                                         int messageType,
                                         Icon icon,
                                         Object[] selectionValues,
                                         Object initialSelectionValue) {
//          return JOptionPane.showInputDialog(parentComponent,
//                                     message,  title, messageType, icon,
//                                     selectionValues,initialSelectionValue);                              
        JOptionPane pane = new JOptionPane(message, messageType,
                OK_CANCEL_OPTION, icon,
                null, null);
        pane.setPreferredSize(new Dimension(260, 150));
        pane.setWantsInput(true);
        pane.setSelectionValues(selectionValues);
        pane.setInitialSelectionValue(initialSelectionValue);

        javax.swing.JDialog dialog = pane.createDialog(parentComponent, title);
        pane.selectInitialValue();
        dialog.setVisible(true);

        Object value = pane.getInputValue();

        if (value == JOptionPane.UNINITIALIZED_VALUE)
            return null;
        return value;
    }


    public static String showInputDialog(Component parentComponent,
                                         Object message,
                                         String title,
                                         int messageType) {
        return JOptionPane.showInputDialog(parentComponent,
                message, title, messageType);
    }

    /**
     * Call JOptionPane the same method directly.
     */
    public static int showConfirmDialog(Component parentComponent, Object message) {
        return JOptionPane.showConfirmDialog(new javax.swing.JFrame(), message);
    }

    public static int showConfirmDialog(Object message) {
        return showConfirmDialog(new javax.swing.JFrame(), message);
    }

    /**
     * Call JOptionPane the same method directly.
     */
    public static int showConfirmDialog(Component parentComponent, Object message, String title) {
        return JOptionPane.showConfirmDialog(parentComponent,
                message,
                title,
                YES_NO_OPTION);

    }

    public static int showConfirmDialog(Object message, String title) {
        return showConfirmDialog(new javax.swing.JFrame(),
                message,
                title,
                YES_NO_OPTION);

    }

    /**
     * Call JOptionPane the same method directly.
     */
    public static int showConfirmDialog(Object message, String title,
                                        int optionType) {
        return showConfirmDialog(new javax.swing.JFrame(),
                message,
                title,
                optionType);
    }

    public static int showConfirmDialog(Component parentComponent, Object message, String title,
                                        int optionType) {
        return JOptionPane.showConfirmDialog(parentComponent,
                message,
                title,
                optionType);

    }

    /**
     * Call JOptionPane the same method directly.
     */
    public static int showConfirmDialog(Component parentComponent, Object message, String title,
                                        int optionType, int messageType) {
        return JOptionPane.showConfirmDialog(parentComponent,
                message, title,
                optionType, messageType);

    }

    public static int showConfirmDialog(Object message, String title,
                                        int optionType, int messageType) {
        return showConfirmDialog(new javax.swing.JFrame(),
                message, title,
                optionType, messageType);
    }

    /**
     * Call JOptionPane the same method directly.
     */
    public static int showConfirmDialog(Component parentComponent, Object message, String title,
                                        int optionType, int messageType, Icon icon) {
        return showConfirmDialog(parentComponent,
                message, title,
                optionType, messageType, icon);

    }

    public static int showConfirmDialog(Object message, String title,
                                        int optionType, int messageType, Icon icon) {
        return showConfirmDialog(new javax.swing.JFrame(),
                message, title,
                optionType, messageType, icon);

    }

}

// Yarked from JMenu, ideally this would be public.
class ActionChangedListener implements PropertyChangeListener {
    JMenuItem menuItem = null;
    JButton button = null;

    ActionChangedListener(JMenuItem mi) {
        super();
        this.menuItem = mi;
    }

    ActionChangedListener(JButton b) {
        super();
        this.button = b;
    }

    public void propertyChange(PropertyChangeEvent e) {
        String propertyName = e.getPropertyName();
        if (menuItem != null) {
            if (e.getPropertyName().equals(Action.NAME)) {
                String text = (String) e.getNewValue();
                menuItem.setText(text);
            } else if (propertyName.equals("enabled")) {
                Boolean enabledState = (Boolean) e.getNewValue();
                menuItem.setEnabled(enabledState.booleanValue());
            }
        }
        if (button != null) {
            if (propertyName.equals("enabled")) {
                Boolean enabledState = (Boolean) e.getNewValue();
                button.setEnabled(enabledState.booleanValue());
            }
        }
    }
}
    
