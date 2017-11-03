package net.edulet.shyq.glyph.editor.ui;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.util.Vector;

import javax.swing.*;

import net.edulet.shyq.glyph.CharComponent;
import net.edulet.shyq.glyph.GraphicsContext;
import net.edulet.shyq.glyph.Rectangle;
import net.edulet.shyq.glyph.StrokeBase;

public abstract class EditorPanel extends JPanel implements MouseMotionListener, MouseListener {

    public final static int StatusIdle = 0;
    public final static int StatusEdit = 1;
    public final static int StatusEditting = 2;

    public final static int StatusSelected = 3;
    public final static int StatusSelecting = 4;
    public final static int StatusMove = 5;
    public static final int StatusResizing = 6;

    String [] statusText={
            "Idle",
            "Edit",
            "Editing",
            "Selected",
            "Selecting",
            "Move",
            "Resizing"
    };

    public String getStatusMessage(int status) {
        if(status>=0 && status<statusText.length)
            return statusText[status];
        return "";
    }

    public void setStatusBar(StatusBar statusBar) {
        this.statusBar = statusBar;
    }

    StatusBar statusBar;
    private JToolBar toolBar;

    Image buffImage;
    Graphics buffGraphics;
    int width;
    int height;
    Rectangle rect;

    CharComponent currentComponent;
    Vector selectedShapes;
    Vector shapeList;

    int status = 0;

    Dimension windowView;
    Rectangle bufferRect;
//    float scaleX, scaleY;
//    int offsetX, offsetY;
    int oldX, oldY, currentX, currentY;

    public EditorPanel(String title) {
        init();
        shapeList = getWorkingShaps(); // chineseChar.getComponents();
    }
    protected   Vector getWorkingShaps(){
        return new Vector();
    }

    JFrame frame;
    public void setFrame(JFrame frame) {
        this.frame = frame;

    }

    protected void init() {
        width = 400;
        height = 500;

        rect = new Rectangle(10, 10, width, height);
        buffImage = createImage(width + 1, height + 1);
        if (buffImage != null) {
            buffGraphics = buffImage.getGraphics();
            setBackground(Color.white);
            buffGraphics.clearRect(0, 0, width, height);
        }
        addMouseMotionListener(this);
        addMouseListener(this);

        shapeList = new Vector();
        //selectedShapes = new Vector();

    }

//    protected abstract void newComponent();
//    protected abstract void loadComponent()throws IOException;
//
    int currentTool = 0;

    public abstract void pickTool(int t);

    public abstract void pickLayout(int t);

    protected boolean isValidCode(int code) {
        return code != 0;
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
    }

    public void setBounds(int x,
                          int y,
                          int width,
                          int height) {
        super.setBounds(x, y, width, height);
        //if(buffImage == null) {
        buffImage = createImage((int) (width + rect.x * 2), (int) (height + rect.y * 2));
        if (buffImage != null) {
            buffGraphics = buffImage.getGraphics();
            setBackground(Color.white);
            buffGraphics.translate(getTranslateX(), getTranslateY());
            buffGraphics.clearRect(0, 0, width, height);
        }
        redrawBuffer();
        //}
    }

    public int getTranslateX() {
        return (int) rect.x;
    }

    public int getTranslateY() {
        return (int) rect.y;
    }

    protected void paintComponent(Graphics g) {

    }

    public void paint(Graphics g) {
        g.drawImage(buffImage, 0, 0, this);
    }

    public void update(Graphics g) {
        paint(g);
    }


    private void redrawBuffer() {
        GraphicsContext gctx = new GraphicsImpl(buffGraphics);
        gctx.setTranslate(rect.x, rect.y);

        drawBackground(buffGraphics);

        gctx.setColor(Color.red);

        for (int i = 0; i < shapeList.size(); i++) {
            CharComponent component = (CharComponent) shapeList.get(i);
            component.draw(gctx);
        }
        if (currentComponent != null) {
            highLight(gctx, currentComponent, true);
        }
        if (selectedShapes != null) {
            highLight(gctx, selectedShapes);
        }
        if (selectRect != null) {
            gctx.draw(selectRect);
        }
    }


    private void highLight(GraphicsContext gctx, Vector selectedShapes) {
        for (int i = 0; i < selectedShapes.size(); i++) {
            CharComponent component = (CharComponent) selectedShapes.get(i);
            highLight(gctx, component, false);
        }

    }

    private void highLight(GraphicsContext gctx, CharComponent component, boolean boundFlag) {
        gctx.setColor(Color.green);
        component.draw(gctx);
        float dashPattern[] = {10, 5};
        BasicStroke oStroke = (BasicStroke) gctx.getStroke();
        BasicStroke nStroke = new BasicStroke(1.f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.f, dashPattern, 0.f);
        gctx.setStroke(nStroke);
        if (boundFlag)
            component.drawBounds(gctx);
        //Rectangle rect = component.getBounds();
        //gctx.draw(rect);
        gctx.setStroke(oStroke);
    }

    private void drawBackground(Graphics buffGraphics) {
        // Clear the rectangle drawing area
        buffGraphics.clearRect(0, 0, width, height + 1);
        // Draw Outer rectangle border
        buffGraphics.setColor(Color.cyan);
        buffGraphics.drawRect(0, 0, (int) rect.getWidth() - 1, (int) rect.getHeight() - 1);
        // Draw diagonal
        buffGraphics.drawLine(0, 0, (int) rect.getWidth() - 1, (int) rect.getHeight() - 1);
        buffGraphics.drawLine(0, (int) rect.getHeight() - 1, (int) rect.getWidth() - 1, 0);
        // Draw half segments
        buffGraphics.drawLine(0, (int) rect.getHeight() / 2, (int) rect.getWidth() - 1, (int) rect.getHeight() / 2);
        buffGraphics.drawLine((int) rect.getWidth() / 2, 0, (int) rect.getWidth() / 2, (int) rect.getHeight());

        // Draw Inner rectangle
        //buffGraphics.setColor(Color.gray);
        buffGraphics.drawRect((int) rect.getWidth() / 4, (int) rect.getHeight() / 4, (int) rect.getWidth() / 2 - 1, (int) rect.getHeight() / 2 - 1);
        // Draw control points/lines
        buffGraphics.setColor(Color.blue);

    }

    private CharComponent pickComponent(int xx, int yy) {
        int x = screenToWorldX(xx, yy);
        int y = screenToWorldY(xx, yy);

        for (int i = 0; i < shapeList.size(); i++) {
            CharComponent component = (CharComponent) shapeList.get(i);
            if (component.contains(x, y)) {
                if (component.isOnContour(x, y)) {
                    return component;
                }
            }
        }
        return null;
    }

    public int screenToWorldX(int x, int y) {
        return (int) (x - rect.x);
    }

    public int screenToWorldY(int x, int y) {
        return (int) (y - rect.y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int modifiers = e.getModifiers();
        int clickCount = e.getClickCount();
        int x, y;

        x = e.getX();
        y = e.getY();

        if ((modifiers & InputEvent.BUTTON1_MASK) != 0) {
            System.out.println("mouseClicked:BUTTON1_MASK (x,y)=(" + x + "," + y + ")");
        }
        if ((modifiers & InputEvent.BUTTON2_MASK) != 0) {
            System.out.println("mouseClicked:BUTTON2_MASK (x,y)=(" + x + "," + y + ")");

        }
        if ((modifiers & InputEvent.BUTTON3_MASK) != 0) {
            System.out.println("mouseClicked:BUTTON3_MASK (x,y)=(" + x + "," + y + ")");
        }

    }

    private Vector selectComponentsInRect(Rectangle rect) {
        selectedShapes = null;
        for (int i = 0; i < shapeList.size(); i++) {
            CharComponent component = (CharComponent) shapeList.get(i);
            if (rect.contains(component.getBounds())) {
                if (selectedShapes == null)
                    selectedShapes = new Vector();
                selectedShapes.add(component);
            }
        }
        return selectedShapes;
    }

    void addStroke(CharComponent stroke) {
        shapeList.add(stroke);
    }

    void removeCharComponent(CharComponent stroke) {
        shapeList.remove(stroke);
    }

    void removeCharComponent(int n) {
        shapeList.remove(n);
    }

    Rectangle selectRect;
    int firstX, firstY, secondX, secondY;

    @Override
    public void mousePressed(MouseEvent e) {
        int x, y;
        double xd, yd;
        CharComponent component = null;
        CharComponent stroke = null;
        x = e.getX();
        y = e.getY();
        int clickCount = e.getClickCount();
        int modifiers = e.getModifiers();
        if ((modifiers & InputEvent.BUTTON1_MASK) != 0) {

            if (ToolCode.isStrokeCode(currentTool)) {
                stroke = CharFactory.getStrokeByToolCode(currentTool);
                stroke.move(x, y);
                addStroke(stroke);
                redrawBuffer();

                repaint();
            } else {
                switch (currentTool) {
                    case ToolCode.ToolPointer:
                        oldX = e.getX();
                        oldY = e.getY();
                        if (resizeBorder != Rectangle.BorderNone) {
                            setStatus(StatusResizing);

                        } else {
                            component = pickComponent(x, y);

                            if (component != null) {
                                currentComponent = component;
                                setStatus(StatusSelected);
                            } else {
                                currentComponent = null;
                                selectedShapes = null;
                                setStatus(StatusSelecting);
                                firstX = e.getX();
                                firstY = e.getY();
                                if (selectRect == null)
                                    selectRect = new Rectangle(e.getX(), e.getY(), 1, 1);
                                else {
                                    selectRect.x = e.getX();
                                    selectRect.y = e.getY();
                                    selectRect.width = 1;
                                    selectRect.height = 1;
                                }
                            }
                            redrawBuffer();

                            repaint();
                        }
                        System.out.println("mousePressed:BUTTON1 (" + oldX + "," + oldY + ")");
                        break;
                    case ToolCode.ToolModifyLine:
                        component = pickComponent(x, y);
                        setStatus(StatusEdit);

                        if (component != null) {
                            currentComponent = component;
                            setStatus(StatusEditting);
                        } else {
                            currentComponent = null;
                        }

                        redrawBuffer();
                        repaint();
                        System.out.println("mousePressed:BUTTON1 (" + oldX + "," + oldY + ")");
                        break;
                    case ToolCode.ToolDelete:
                        component = pickComponent(x, y);
                        if (component != null) {
                            removeCharComponent(component);
                            redrawBuffer();

                            repaint();
                        }
                        break;
                    case ToolCode.ToolText:


                        break;
                    default:

                        System.out.println("mousePressed:BUTTON1_MASK (x,y)=(" + x + "," + y + ")");
                        break;
                }
            }
        }
        if ((modifiers & InputEvent.BUTTON2_MASK) != 0) {
            System.out.println("mousePressed:BUTTON2_MASK (x,y)=(" + x + "," + y + ")");
        }
        if ((modifiers & InputEvent.BUTTON3_MASK) != 0) {
            System.out.println("mousePressed:BUTTON3_MASK (x,y)=(" + x + "," + y + ")");
        }
    }


    protected abstract int promptNew();


    private void setStatus(int status) {
        this.status = status;

        if(statusBar!=null)
            statusBar.setStatusText(getStatusMessage(status));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int modifiers = e.getModifiers();
        int clickCount = e.getClickCount();
        int x, y;
        double xd, yd;
        int ret;

        x = e.getX();
        y = e.getY();

        //x = screenToWorldX(e.getX(), e.getY());
        //y = screenToWorldY(e.getY(), e.getY());

        if ((modifiers & InputEvent.BUTTON1_MASK) != 0) {
            System.out.println("mouseReleased:BUTTON1_MASK (x,y)=(" + x + "," + y + ")");
        }
        if ((modifiers & InputEvent.BUTTON2_MASK) != 0) {
            System.out.println("mouseReleased:BUTTON2_MASK (x,y)=(" + x + "," + y + ")");

        }
        if ((modifiers & InputEvent.BUTTON3_MASK) != 0) {
            System.out.println("mouseReleased:BUTTON3_MASK (x,y)=(" + x + "," + y + ")");
        }

        if (currentTool == ToolCode.ToolPointer) {
            if (getStatus() == StatusSelecting) {
                selectComponentsInRect(selectRect);
                selectRect = null;
                setStatus(StatusIdle);

                redrawBuffer();
                repaint();
            }

//	        CharComponent component = pickComponent(x, y);
//	        if(component != null)
//	            currentComponent = component;
//	        else 
//	        	currentComponent = null;
        }

//        redrawBuffer();

//        repaint();
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
        int modifiers = e.getModifiers();
        int clickCount = e.getClickCount();
        int x, y;
        x = e.getX();
        y = e.getY();

        if ((modifiers & InputEvent.BUTTON1_MASK) != 0) {
            System.out.println("mouseDragged:BUTTON1_MASK (x,y)=(" + x + "," + y + ")");
        }
        if ((modifiers & InputEvent.BUTTON2_MASK) != 0) {
            System.out.println("mouseDragged:BUTTON2_MASK (x,y)=(" + x + "," + y + ")");

        }
        if ((modifiers & InputEvent.BUTTON3_MASK) != 0) {
            System.out.println("mouseDragged:BUTTON3_MASK (x,y)=(" + x + "," + y + ")");
        }
        if (currentTool == ToolCode.ToolPointer) {
            if (currentComponent != null) {
                double dx = e.getX() - oldX;
                double dy = e.getY() - oldY;
                switch (resizeBorder) {
                    case Rectangle.BorderEast:
                        currentComponent.scaleWithDX(dx);
                        currentComponent.move(dx / 2, 0);
                        break;
                    case Rectangle.BorderWest:
                        currentComponent.scaleWithDX(-dx);
                        currentComponent.move(dx / 2, 0);
                        break;
                    case Rectangle.BorderNorth:
                        currentComponent.scaleWithDY(-dy);
                        //currentComponent.move(0, -dy/2);
                        break;
                    case Rectangle.BorderSouth:
                        currentComponent.scaleWithDY(dy);
                        currentComponent.move(0, dy);
                        break;

                    case Rectangle.BorderCornerNW:
                        currentComponent.scaleWithDelta(-dx, -dy);
                        break;
                    case Rectangle.BorderCornerSE:
                        currentComponent.scaleWithDelta(dx, dy);
                        break;
                    case Rectangle.BorderCornerNE:
                        currentComponent.scaleWithDelta(dx, -dy);
                        break;
                    case Rectangle.BorderCornerSW:
                        currentComponent.scaleWithDelta(-dx, dy);
                        break;
                    default:
                        //currentComponent.move(dx, dy);
                        moveSelectedComponent(dx, dy);
                        break;
                }

                oldX = e.getX();
                oldY = e.getY();
                redrawBuffer();

                repaint();
            } else {
                if (getStatus() == StatusSelecting) {
                    secondX = e.getX();
                    ;
                    secondY = e.getY();

                    if (selectRect == null)
                        selectRect = new Rectangle(e.getX(), e.getY(), 1, 1);
                    if (firstX > secondX) {
                        selectRect.x = secondX;
                        selectRect.width = firstX - secondX;
                    } else {
                        selectRect.x = firstX;
                        selectRect.width = secondX - firstX;
                    }
                    if (firstY > secondY) {
                        selectRect.y = secondY;
                        selectRect.height = firstY - secondY;
                    } else {
                        selectRect.y = firstY;
                        selectRect.height = secondY - firstY;
                    }

                    redrawBuffer();

                    repaint();
                }
            }
        }

    }

    private void moveSelectedComponent(double dx, double dy) {
        if (selectedShapes != null) {
            for (int i = 0; i < selectedShapes.size(); i++) {
                CharComponent component = (CharComponent) selectedShapes.get(i);
                if (component != null)
                    component.move(dx, dy);
            }
        } else {
            currentComponent.move(dx, dy);

        }

    }

    private int getStatus() {
        return status;
    }

    Cursor oldCursor = null;
    int resizeBorder = 0;

    @Override
    public void mouseMoved(MouseEvent e) {
        int xx = e.getX();
        int yy = e.getY();
        //System.out.println("mouseMoved: ("+xx+","+yy+")");
        if (null != currentComponent) {
            int x = screenToWorldX(xx, yy);
            int y = screenToWorldY(xx, yy);
            Rectangle rect = currentComponent.getBounds();

            int border = rect.checkOnBorder(x, y);
            //if(border != Rectangle.BorderNone){
            resizeBorder = border;
            if (oldCursor == null)
                oldCursor = getCursor();
            //}
            switch (border) {
                case Rectangle.BorderEast:
                    setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                    break;
                case Rectangle.BorderWest:
                    setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                    break;
                case Rectangle.BorderNorth:
                    setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                    break;
                case Rectangle.BorderSouth:
                    setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                    break;

                case Rectangle.BorderCornerNW:
                    setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                    break;
                case Rectangle.BorderCornerSE:
                    setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                    break;
                case Rectangle.BorderCornerNE:
                    setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
                    break;
                case Rectangle.BorderCornerSW:
                    setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                    break;
                default:
                    if (oldCursor != null)
                        setCursor(oldCursor);
                    break;
            }
        } else {
            if (oldCursor != null) {
                setCursor(oldCursor);
                oldCursor = null;
                resizeBorder = 0;
            }
        }

    }

    public void setToolBar(JToolBar toolBar) {
        this.toolBar = toolBar;
    }
    FileDialog fileDialog;

    public void doSave() {
        if(fileDialog==null) {
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
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
