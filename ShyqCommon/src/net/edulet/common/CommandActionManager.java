package net.edulet.common;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.Action;

public class CommandActionManager {
    private Hashtable commands;

    public CommandActionManager() {
        commands = new Hashtable();
    }

    /**
     * Disables all actions .
     */
    private void setAllCommandsEnabled(boolean b) {
        Enumeration cmdStrs = commands.keys();
        while (cmdStrs.hasMoreElements()) {
            String cmd = (String) (cmdStrs.nextElement());
            //System.out.println("cmd="+cmd);
            Action a = (Action) commands.get(cmd);
            if (a != null) {
                if (b)
                    a.setEnabled(true);
                else
                    a.setEnabled(false);
            }
        }
    }

    public void setCommandActionEnabled(String cmd, boolean b) {
        Action a = (Action) commands.get(cmd);
        if (a != null)
            a.setEnabled(b);
    }

    public Hashtable getCommands() {
        return commands;
    }
}
