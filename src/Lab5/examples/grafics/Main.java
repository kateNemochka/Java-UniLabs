package Lab5.examples.grafics;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args){
        MouseFrame frame = new MouseFrame();
        frame.setVisible(true);
        frame.addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Exit program");
                System.exit(0); // вихід з програми
            }
            @Override
            public void windowOpened(WindowEvent e){
                JOptionPane pane = new JOptionPane();
                int showConfirmDialog = JOptionPane.showConfirmDialog(null,
                                "Do you want to test this program?");
                System.out.println(showConfirmDialog);
                if (showConfirmDialog==2||showConfirmDialog==1) {
                    System.exit(0);
                }
            }
        });

        frame.getContentPane();
    }
}
