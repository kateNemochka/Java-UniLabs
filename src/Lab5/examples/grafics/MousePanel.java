package Lab5.examples.grafics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;

class MousePanel extends JPanel{
    private int SIDELENGTH = 30;
    private ArrayList<Rectangle2D> squares;
    private Rectangle2D current;

    public MousePanel(){
        squares = new ArrayList<Rectangle2D>();
        current = null;
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Rectangle2D r: squares){
            g2.draw(r);
            g2.setPaint(Color.ORANGE);
            g2.fill(r);
        }
    }

    public Rectangle2D find(Point2D p){
        for (Rectangle2D r: squares){
            if(r.contains(p))return r;
        }
        return null;
    }
    public void add(Point2D p){
        double x = p.getX();
        double y = p.getY();
        current = new Rectangle2D.Double(
                x-SIDELENGTH/2,
                y-SIDELENGTH/2,
                SIDELENGTH,
                SIDELENGTH);
        squares.add(current);
        repaint();
    }
    public void remove(Rectangle2D s){
        if(s==null)
            return ;
        if (s==current)
            current = null;
        squares.remove(s);
        repaint();
    }


    private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event){
            current = find(event.getPoint());
            if(current == null)
                add(event.getPoint());
        }

        @Override
        public void mouseClicked(MouseEvent event) {
            current = find(event.getPoint());
            if(current!=null&&event.getClickCount()>=2)
                remove (current);
        }
    }

    private class MouseMotionHandler implements MouseMotionListener{
        public void mouseDragged(MouseEvent event) {
            if (current!=null){
                int x = event.getX();
                int y = event.getY();
                current.setFrame(x-SIDELENGTH/2, y-SIDELENGTH/2,
                                    SIDELENGTH, SIDELENGTH);
                repaint();
            }
        }

        public void mouseMoved(MouseEvent event) {
            if (find(event.getPoint())==null)
                setCursor(Cursor.getDefaultCursor());
            else
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    } }
