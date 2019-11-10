package Lab4;

import Lab1.Tariffs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TariffsWindow extends ConstantsClass {
    private JPanel panel;
    private JTextField basicTextField, comfTextField, ecoTextField, nightTextField;
    private JLabel baseFareLabel, comfortFareLabel, ecoFareLabel, nightFareLabel;
    private JButton OKButton, cancelButton;
    private JLabel warningLabel;

    public TariffsWindow() {
        updateFareValues();
        OKButton.addActionListener(e -> {
            try {
                double num = Double.parseDouble(basicTextField.getText());
                tariff.setBasicFare(num);
            } catch (NumberFormatException ex) {
                System.out.println("No changes to Basic Fare");
            }
            try {
                double num = Double.parseDouble(comfTextField.getText());
                tariff.setComfort(num);
            } catch (NumberFormatException ex) {
                System.out.println("No changes to Comfort Fare");
            }
            try {
                double num = Double.parseDouble(ecoTextField.getText());
                tariff.setGreen(num);
            } catch (NumberFormatException ex) {
                System.out.println("No changes to Green Fare");
            }
            try {
                double num = Double.parseDouble(nightTextField.getText());
                tariff.setNight(num);
            } catch (NumberFormatException ex) {
                System.out.println("No changes to Night Fare");
            }
            updateFareValues();
            taxiPark.setTariff(tariff);
            System.exit(0);
        });

        cancelButton.addActionListener(e -> {
            System.exit(0);
        });

        basicTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                filter(basicTextField, e);
            }
        });
        comfTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                filter(comfTextField, e);
            }
        });
        ecoTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                filter(ecoTextField, e);
            }
        });
        nightTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                filter(nightTextField, e);
            }
        });
    }


    private void updateFareValues() {
        baseFareLabel.setText(String.valueOf(tariff.getBasicFare()));
        comfortFareLabel.setText(String.valueOf(tariff.getComfort()));
        ecoFareLabel.setText(String.valueOf(tariff.getGreen()));
        nightFareLabel.setText(String.valueOf(tariff.getNight()));
    }

    private void filter(JTextField tf, KeyEvent ke) {
        String value = tf.getText();
        int l = value.length();
        if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')
                || ke.getKeyChar() == '.' || ke.getKeyChar() == '\b') {
            tf.setEditable(true);
            warningLabel.setText("");
        } else {
            tf.setEditable(false);
            warningLabel.setText("* Enter only numeric digits(0-9) or dot symbol");
            warningLabel.setForeground(Color.RED);
        }
    }


    public static void main(String[] args) {
        Tariffs tariff = new Tariffs(15);
        JFrame f = new JFrame("Тарифи");
        f.setContentPane(new TariffsWindow().panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.pack();
    }
}
