package net.edulet.shyq.glyph;

import java.awt.BasicStroke;
import java.awt.Stroke;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SimpleStrokeComponent extends CharComponent {
    StrokeBase stroke;
    public SimpleStrokeComponent(){
        this(null);
    }

    public SimpleStrokeComponent(StrokeBase strokeBase) {
        stroke = strokeBase;
    }

    public Rectangle getBounds() {
        Rectangle rect = stroke.getBounds();
        // Sacle
        rect.width *= scaleX;
        rect.height *= scaleY;
        rect.x *= scaleX;
        rect.y *= scaleY;

        // translate
        if (null != anchor) {
            rect.x += anchor.getX();
            rect.y += anchor.getY();
        }


        return rect;
    }

    public boolean isOnContour(double x, double y) {
        double xx = toComponentX(x, y);
        double yy = toComponentY(x, y);

        return stroke.isOnContour(xx, yy);
    }

    public void draw(GraphicsContext gctx) {
        if (null == stroke)
            return;

        if (anchor != null) {
            gctx.translate(anchor.getX(), anchor.getY());
        }
        BasicStroke oStroke = (BasicStroke) gctx.getStroke();
        double ss = 1.0;
        if (scaleX < scaleY)
            ss = scaleY;
        else
            ss = scaleX;

        gctx.scale(scaleX, scaleY);
        Stroke nStroke = new BasicStroke((float) (oStroke.getLineWidth() / ss));
        gctx.setStroke(nStroke);
        stroke.draw(gctx);

        gctx.scale(1. / scaleX, 1. / scaleY);
        gctx.setStroke(oStroke);

        if (anchor != null)
            gctx.translate(-anchor.getX(), -anchor.getY());
    }

    public void save(DataOutputStream dos) throws IOException {
        super.save(dos);
        if(stroke!=null) {
            String className = stroke.getClass().getCanonicalName();
            dos.writeUTF(className);
            stroke.save(dos);
        }
    }

    public void load(DataInputStream dis) throws IOException {
        super.load(dis);
        // Class name
        String className =dis.readUTF();
        Class theClass = null;
        try {
            theClass = Class.forName(className);
            stroke = (StrokeBase)theClass.newInstance();

            stroke.load(dis);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
