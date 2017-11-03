package net.edulet.shyq.glyph;

/**
 * @author Shao Yongqing
 * Date: 2017/11/3.
 */
public class PolyLine extends PointSet {
    @Override
    public void draw(GraphicsContext gctx)   {
        if(points == null || points.size()==0)
            return;
        int pointCount = points.size();
        double px[], py[];
        px = new double[pointCount];
        py = new double[pointCount];

        for (int i=0; i<pointCount; i++) {
            px[i] = ((Point)points.get(i)).getX();
            py[i] = ((Point)points.get(i)).getY();
        }

        gctx.drawLines(px, py);
    }
}
