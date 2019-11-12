package Lab4;

import Lab1.Taxi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Random;

public class MainWindow extends ConstantsClass {
    private JPanel panel;
    private JLabel statLabel;
    private JLabel ridesCountLabel;
    private JLabel profitLabel;
    private JLabel quantityStandart, quantityComfort, quantityGreen, quantityAll;
    private JLabel stFareLabel, comfFareLabel, ecoFareLabel, nightFareLabel;
    private JLabel updateLabel;
    private JButton addRideButton;
    private JButton addTaxiButton, removeTaxiButton, generateTaxiButton;
    private JRadioButton autoRideOn, autoRideOff;
    private JButton modifyTariffsButton;
    private JTabbedPane tabbedPane;
    private JPanel updatePanel;
    private JTable taxiTable;
    private JScrollPane carListPanel;



    public MainWindow() {
        taxiTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // \_-=*TIMER*=-_/
        Timer timer = new Timer(2000, ae -> {
            int rides = new Random().nextInt(5);
            numOfRides += rides;
            taxiPark.addRandomRides(rides);
            updateCounters();
            updateCarList();
        });


        // SETTING INITIAL VALUES
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
        comfFareLabel.setText(String.valueOf(taxiPark.getTariff().getComfort()));
        ecoFareLabel.setText(String.valueOf(taxiPark.getTariff().getGreen()));
        nightFareLabel.setText(String.valueOf(taxiPark.getTariff().getNight()));

        // filling labels with color
        statLabel.setOpaque(true);


        // RADIO BUTTONS
        // turning on and off automated generation of rides
        ButtonGroup autoRideRadioGroup = new ButtonGroup();
        autoRideRadioGroup.add(autoRideOff);
        autoRideRadioGroup.add(autoRideOn);
        autoRideOn.addActionListener(e -> {
            updateLabel.setText("Auto Mode Is On");
            timer.start();
            addRideButton.setEnabled(false);
        });
        autoRideOff.addActionListener(e -> {
            updateLabel.setText("Auto Mode Is Off");
            timer.stop();
            addRideButton.setEnabled(true);
        });


        // ACTIONS WITH BUTTONS
        // generating random car
        generateTaxiButton.addActionListener(e -> {
            taxiPark.addRandomCar();
            updateCounters();
            Taxi taxi = taxiPark.getTaxi(Collections.max(taxiPark.getTaxiIDs()));
            updateLabel.setText("<html>"+"ADDED NEW TAXI!:)<br>"+carInfoToHTML(taxi)+ "</html>");
            updateCarList();
        });
        // adding a new car with a dialog window
        addTaxiButton.addActionListener(e -> {
            NewCarWindow dialog = new NewCarWindow();
            dialog.pack();
            dialog.setVisible(true);
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent windowEvent) {
                    try{
                        Taxi taxi = taxiPark.getTaxi(Collections.max(taxiPark.getTaxiIDs()));
                        updateLabel.setText("<html>"+"ADDED NEW TAXI!:)<br>"+carInfoToHTML(taxi)+ "</html>");
                        updateCounters();
                        updateCarList();
                    } catch (NoSuchElementException exception) {
                        updateLabel.setText("Closed 'Add Taxi' Window");
                    }
                }
            });

        });
        // removing random car (no any success with lists:(()
        removeTaxiButton.addActionListener(e -> {
            /*RemoveCarWindow dialog = new RemoveCarWindow();
            dialog.pack();
            dialog.setVisible(true);
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent windowEvent) {
                    try{
                        Taxi taxi = taxiPark.getTaxi(Collections.max(taxiPark.getTaxiIDs()));
                        updateLabel.setText("<html>"+"ADDED NEW TAXI!:)<br>"+carInfoToHTML(taxi)+ "</html>");
                        updateCounters();
                        updateCarList();
                    } catch (NoSuchElementException exception) {
                        updateLabel.setText("Closed 'Add Taxi' Window");
                    }
                }
            });*/

            ArrayList<Integer> ids = taxiPark.getTaxiIDs();
            try{
                int id = ids.get(new Random().nextInt(ids.size()));
                Taxi taxi = taxiPark.getTaxi(id);
                updateLabel.setText(
                        "<html>"+"Removing a taxi!:)" + carInfoToHTML(taxi) + "</html>"
                );
                profitOfGone += taxi.getProfit();
                taxiPark.removeTaxi(id);
                updateCounters();
            } catch (IllegalArgumentException ex) {
                updateLabel.setText("No more cars left to remove!");
            }
            updateCarList();
        });

        // editing fares for different types of cars
        modifyTariffsButton.addActionListener(e -> {
            TariffWindow dialog = new TariffWindow();
            dialog.pack();
            dialog.setVisible(true);
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent windowEvent) {
                    updateCounters();
                }
            });
        });
        // adding a new ride
        addRideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRideWindow dialog = new AddRideWindow();
                dialog.pack();
                dialog.setVisible(true);
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent windowEvent) {
                        updateCounters();
                        updateCarList();
                    }
                });
            }
        });


        // TAB PANEL
        carListPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                String[][] data = taxiPark.getCarsTabledInformation();
                /*id,type,money,dist, regnumber,brand,color,prodyear,regyear*/
                String[] columnNames = {"ID", "Type", "Profit", "Distance", "Reg.Number",
                        "Brand", "Color", "Product.Year", "Reg.Year"};

                DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                    public boolean isCellEditable(int row, int col) {
                        return false;
                    }
                };
                taxiTable.setModel(model);
            }

        });
    }

    // updating statistics values after changes
    public void updateCounters() {
        quantityAll.setText(String.valueOf(taxiPark.getNumberOfCars()));
        quantityStandart.setText(String.valueOf(taxiPark.getNumberOfCars(0)));
        quantityComfort.setText(String.valueOf(taxiPark.getNumberOfCars(1)));
        quantityGreen.setText(String.valueOf(taxiPark.getNumberOfCars(2)));

        ridesCountLabel.setText(String.valueOf(numOfRides));
        profitLabel.setText(String.valueOf(taxiPark.getTotalProfit() + profitOfGone));

        stFareLabel.setText(String.valueOf(tariff.getBasicFare()));
        comfFareLabel.setText(String.valueOf(tariff.getComfort()));
        ecoFareLabel.setText(String.valueOf(tariff.getGreen()));
        nightFareLabel.setText(String.valueOf(tariff.getNight()));
    }

    // updating table with taxi park cars information
    public void updateCarList() {

        String[][] data = taxiPark.getCarsTabledInformation();
        /*id,type,money,dist, regnumber,brand,color,prodyear,regyear*/
        String[] columnNames = { "ID", "Type", "Profit", "Distance", "Reg.Number",
                "Brand", "Color", "Product.Year", "Reg.Year"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        taxiTable.setModel(model);
    }

    // creating html with car information for a log tab
    public String carInfoToHTML(Taxi taxi) {
        return "<br>======== CAR ID: " + String.valueOf(taxi.getID()) + " =========="
                + "<br>"+taxi.getTaxiType() + " Taxi"
                + "<br>Driver: "+taxi.getDriver()
                + "<br>Brand: " +taxi.getBrand()
                + "<br>Color: "+taxi.getColor()
                + "<br>Production Year: " +taxi.getProductionYear()
                + "<br>Registration Year: " + taxi.getRegistrationYear()
                + "<br>Registration Number: " + taxi.getRegNumber();
    }


    public static void main(String[] args) {
        JFrame f = new JFrame("Таксопарк");
        f.setContentPane(new MainWindow().panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.pack();

    }

}
