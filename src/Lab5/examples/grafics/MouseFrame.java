package Lab5.examples.grafics;
import javax.swing.JFrame;
        /**/
class MouseFrame extends JFrame{
    public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 300;

    public MouseFrame(){
        setTitle("MouseTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setLocation(100,100);
        MousePanel panel = new MousePanel();
        this.add(panel);
    }
}
