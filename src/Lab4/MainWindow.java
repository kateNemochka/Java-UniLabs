package Lab4;

import Lab1.Taxi;
import Lab3.MapTaxiPark;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainWindow {
    private JPanel panel1;
    private JButton addRideButton;
    private JButton addTaxiButton;
    private JLabel quantityStandart;
    private JLabel quantityComfort;
    private JLabel quantityGreen;
    private JLabel updateLabel;
    private JButton removeTaxiButton;
    private JLabel quantityAll;
    private MapTaxiPark taxiPark;

    public MainWindow() {
        taxiPark = new MapTaxiPark(10);
        quantityStandart.setText("0");
        quantityComfort.setText("0");
        quantityGreen.setText("0");
        quantityAll.setText("0");

        addTaxiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taxiPark.addRandomCar();
                updateCounters();
                Taxi taxi = taxiPark.getTaxi(Collections.max(taxiPark.getTaxiIDs()));
                updateLabel.setText(
                        "<html>"+"Added new taxi!:)"
                                + "<br>"+"======== CAR ID: " + String.valueOf(taxi.getID()) + " =========="
                                + "<br>"+taxi.getTaxiType()
                                + "<br>"+"Driver: "+taxi.getDriver()
                                + "<br>"+"Brand: " +taxi.getBrand()
                                + "<br>"+"Color: "+taxi.getColor()
                                + "<br>"+"Production Year: " +taxi.getProductionYear()
                                + "<br>"+"Registration Year: " + taxi.getRegistrationYear()
                                + "<br>"+"Registration Number: " + taxi.getRegNumber()
                                + "</html>"
                );
            }
        });
        removeTaxiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> ids = taxiPark.getTaxiIDs();
                int id = ids.get(new Random().nextInt(ids.size()));
                Taxi taxi = taxiPark.getTaxi(id);
                updateLabel.setText(
                        "<html>"+"Removing a taxi!:)"
                        + "<br>"+"======== CAR ID: " + String.valueOf(taxi.getID()) + " =========="
                        + "<br>"+taxi.getTaxiType()
                        + "<br>"+"Driver: "+taxi.getDriver()
                        + "<br>"+"Brand: " +taxi.getBrand()
                        + "<br>"+"Color: "+taxi.getColor()
                        + "<br>"+"Production Year: " +taxi.getProductionYear()
                        + "<br>"+"Registration Year: " + taxi.getRegistrationYear()
                        + "<br>"+"Registration Number: " + taxi.getRegNumber()
                        + "</html>"
                );
                taxiPark.removeTaxi(id);
                updateCounters();
            }
        });
    }

    public void updateCounters() {
        quantityAll.setText(String.valueOf(taxiPark.getNumberOfCars()));
        quantityStandart.setText(String.valueOf(taxiPark.getNumberOfCars(0)));
        quantityComfort.setText(String.valueOf(taxiPark.getNumberOfCars(1)));
        quantityGreen.setText(String.valueOf(taxiPark.getNumberOfCars(2)));
    }


    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setContentPane(new MainWindow().panel1);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

}
