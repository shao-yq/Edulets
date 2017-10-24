package demo;

import java.awt.*;   
import java.awt.event.*;   
import java.awt.font.*;   
import java.awt.geom.*;   
import javax.swing.*;   
import javax.swing.text.*;   
    
public class GlyphTest   
{   
    Font font;   
    JPanel panel;   
    GridBagConstraints gbc;   
    JTextPane textPane;   
    
    public GlyphTest()   
    {   
        // north panel   
        String[] fontNames = {   
            "symbol", "webdings", "wingdings", "wingdings 2", "wingdings 3"  
        };   
        final JComboBox combo = new JComboBox(fontNames);   
        combo.setSelectedIndex(2);   
        combo.addActionListener(new ActionListener()   
        {   
            public void actionPerformed(ActionEvent e)   
            {   
                String fontName = (String)combo.getSelectedItem();   
                font = new Font(fontName, Font.PLAIN, 18);   
                populateComponents();   
            }   
        });   
        JPanel north = new JPanel();   
        north.add(new JLabel("font name"));   
        north.add(combo);   
    
        // center panel   
        JTabbedPane tabbedPane = new JTabbedPane();   
    
        // tab 1   
        panel = new JPanel(new GridBagLayout());   
        gbc = new GridBagConstraints();   
        gbc.weightx = 1.0;   
        gbc.weighty = 1.0;   
        gbc.insets = new Insets(1,1,1,1);   
        tabbedPane.addTab("panel", new JScrollPane(panel));   
    
        // tab 2   
        textPane = new JTextPane();   
        tabbedPane.addTab("text pane", new JScrollPane(textPane));   
    
        font = new Font("wingdings", Font.PLAIN, 18);   
        populateComponents();   
    
        JFrame f = new JFrame();   
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        f.add(north, "North");   
        f.add(tabbedPane);   
        f.setSize(400,400);   
        f.setLocation(200,200);   
        f.setVisible(true);   
    }   
    
    private void populateComponents()   
    {   
        int numGlyphs = + font.getNumGlyphs();   
        //System.out.println("numGlyphs = " + numGlyphs);   
        FontRenderContext frc = new FontRenderContext(null, true, false);   
        Rectangle2D r = font.getMaxCharBounds(frc);   
        //System.out.println("r = " + r.toString());   
        Dimension d = new Dimension();   
        d.width = (int)Math.ceil(r.getWidth());   
        d.height = (int)Math.ceil(r.getHeight());   
        //System.out.println("d = " + d.toString());   
        populatePanel(numGlyphs, d);   
        populateTextPane(numGlyphs, d);   
    }   
    
    private void populatePanel(int numGlyphs, Dimension d)   
    {   
        panel.removeAll();   
        GlyphLabel gl;   
        for(int j = 0; j < numGlyphs; j++)   
        {   
            int[] k = { j };   
            gl = new GlyphLabel(k, font);   
            gl.setPreferredSize(d);   
            if((j + 1) % 16 == 0)   
                gbc.gridwidth = gbc.REMAINDER;   
            else  
                gbc.gridwidth = 1;   
            panel.add(gl, gbc);   
        }   
        panel.revalidate();   
    }   
    
    private void populateTextPane(int numGlyphs, Dimension d)   
    {   
        Document doc = textPane.getDocument();   
        if(doc.getLength() > 0)   
            try  
            {   
                doc.remove(0, doc.getLength());   
            }   
            catch(BadLocationException ble)   
            {   
                System.err.println("unable to remove: " + ble.getMessage());   
            }   
    
        GlyphLabel gl;   
        for(int j = 0; j < numGlyphs; j++)   
        {   
            int[] k = { j };   
            gl = new GlyphLabel(k, font);   
            gl.setPreferredSize(d);   
            gl.setMinimumSize(d);   
            textPane.insertComponent(gl);   
        }   
    }   
    
    public static void main(String[] args)   
    {   
        new GlyphTest();   
    }   
}   
    
class GlyphLabel extends JLabel   
{   
    int[] i;   
    Font font;   
    
    public GlyphLabel(int[] i, Font font)   
    {   
        this.i = i;   
        this.font = font;   
        setBorder(BorderFactory.createEtchedBorder());   
    }   
    
    protected void paintComponent(Graphics g)   
    {   
        super.paintComponent(g);   
        Graphics2D g2 = (Graphics2D)g;   
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,   
                            RenderingHints.VALUE_ANTIALIAS_ON);   
        int w = getWidth();   
        int h = getHeight();   
        g2.setFont(font);   
        FontRenderContext frc = g2.getFontRenderContext();   
        GlyphVector gv = font.createGlyphVector(frc, i);   
        Rectangle2D r2 = gv.getLogicalBounds();   
        float x = (float)(w - r2.getWidth())/2;   
        float y = (float)((h - r2.getHeight())/2 - r2.getY());   
        g2.drawGlyphVector(gv, x, y);   
    }   
}  
