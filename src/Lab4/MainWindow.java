package Lab4;

import Lab1.Taxi;
import Lab3.MapTaxiPark;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private JRadioButton autoRideOn;
    private JLabel statLabel;
    private JLabel ridesCountLabel;
    private JLabel profitLabel;
    private JLabel stFareLabel;
    private JLabel comfFareLabel;
    private JLabel ecoFareLabel;
    private JLabel nigthFareLabel;
    private JRadioButton autoRideOff;
    private JButton змінитиТарифиButton;
    private MapTaxiPark taxiPark;


    public MainWindow() {
        taxiPark = new MapTaxiPark(10);
        // general statistics
        profitLabel.setText("0.0");
        ridesCountLabel.setText("0");
        // setting cars quantity
        quantityStandart.setText("0");
        quantityComfort.setText("0");
        quantityGreen.setText("0");
        quantityAll.setText("0");
        // setting fare values
        stFareLabel.setText(String.valueOf(taxiPark.getTariff().getBasicFare()));
        comfFareLabel.setText(String.valueOf(taxiPark.getTariff().getBasicFare() + taxiPark.getTariff().getComfort()));
        ecoFareLabel.setText(String.valueOf(taxiPark.getTariff().getBasicFare() + taxiPark.getTariff().getGreen()));
        nigthFareLabel.setText(String.valueOf(taxiPark.getTariff().getNight()));
        // filling label with color
        statLabel.setOpaque(true);

        // adding actions to radio buttons
        ButtonGroup autoRideRadioGroup = new ButtonGroup();
        autoRideRadioGroup.add(autoRideOff);
        autoRideRadioGroup.add(autoRideOn);
        autoRideOn.addActionListener(e -> {
            updateLabel.setText("Auto Mode Is On");
        });
        autoRideOff.addActionListener(e -> {
            updateLabel.setText("Auto Mode Is Off");
        });
        // adding actions to buttons
        addTaxiButton.addActionListener(e -> {
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
        });
        removeTaxiButton.addActionListener(e -> {
            ArrayList<Integer> ids = taxiPark.getTaxiIDs();
            int id = ids.get(new Random().nextInt(ids.size()));
            try{
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
            } catch (Exception e1) {
                System.out.println("No more cars left!");
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
        JFrame f = new JFrame("Таксопарк");
        f.setContentPane(new MainWindow().panel1);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

}
