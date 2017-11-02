package net.edulet.shyq.glyph.editor.ui;

import net.edulet.shyq.glyph.ChineseChar;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Vector;

/**
 * @author Shao Yongqing
 * Date: 2017/10/30.
 */
public class CharEditorPanel extends EditorPanel{
    ChineseChar chineseChar;

    public CharEditorPanel(String title) {
        super(title);
    }
    protected void init(){
        super.init();
        chineseChar= new ChineseChar(0);

    }
    protected Vector getWorkingShaps(){
        return chineseChar.getComponents();
    }

    protected void loadComponent()throws IOException {
        loadCharacter();
    }
    void loadCharacter() throws IOException {
        if(fileDialog==null)
            fileDialog = new FileDialog(frame);

        fileDialog.setMode(FileDialog.LOAD);
        fileDialog.setVisible(true);

        String fileName = fileDialog.getFile();
        if (fileName == null) {
            return;
        }
        String directory = fileDialog.getDirectory();
        File f = new File(directory, fileName);
        if(!f.exists()){
            return;
        }
        DataInputStream dis= null;
        try {
            dis = new DataInputStream(new FileInputStream(f));
            if(dis!=null){
                chineseChar.load(dis);
                shapeList = chineseChar.getComponents();
                invalidate();
            }
            dis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void pickTool(int t) {
        currentTool = t;
        switch(currentTool) {
            case ToolCode.ToolNewLine:      // new character / component
                newComponent();

                break;
            case ToolCode.ToolModifyLine:
                break;
            case ToolCode.ToolClosedContour:   //  Load  existing character / component to Modify
                try {
                    loadComponent();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    protected void newComponent(){
        newCharacter();
    }

    void newCharacter(){
        // New Chinese character
        // 1. Prompt to save the current character
        if(chineseChar==null){
            chineseChar = new ChineseChar(0);
        }
        if(chineseChar.isValid()){
            try {
                saveCharacter(chineseChar);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        // input new  character code
        int code = promptNewChar();
        if(isValidCode(code)) {

            // 2. Reset to new character
            chineseChar.reset();
            chineseChar.setCode(code);


        } else {
            JOptionPane.showMessageDialog(null,"Invalid Input Code ", "Warning", JOptionPane.WARNING_MESSAGE);
            //JOptionPane.showMessageialog(null, "Invalid Input Code ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    protected int promptNew() {
        return promptNewChar();
    }

    private int promptNewChar() {
        String  str = JOptionPane.showInputDialog("请输入的编码值：");
        if(str!=null && str.length()>0){
            try {
                int code = Integer.parseInt(str);
                return code;
            } catch (NumberFormatException nfe) {
                int code = str.charAt(0);
                return code;
                //nfe.printStackTrace();
            }
        }
        return 0;
    }
    private void saveCharacter(ChineseChar chineseChar) throws IOException {

        FileDialog fileDialog;
        fileDialog = new FileDialog(frame);

        fileDialog.setMode(FileDialog.SAVE);
        fileDialog.setVisible(true);

        String fileName = fileDialog.getFile();
        if (fileName == null) {
            return;
        }
        String directory = fileDialog.getDirectory();
        File f = new File(directory, fileName);
        if(!f.exists()){
            f.createNewFile();
        }
        DataOutputStream  dos=new DataOutputStream(new FileOutputStream(f));
        if(dos!=null){
            chineseChar.save(dos);
        }
        dos.close();
    }
}
