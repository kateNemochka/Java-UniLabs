package Lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Graphics2D;


public class CGoL {
    private JPanel mainPanel, conwayPanel;
    private JTextField probTextField;
    private JButton startButton, stopButton;
    private JLabel warningLabel;
    private JButton pauseButton;
    private JLabel generationField;

    private double probability;
    private static final Random rnd = new Random();
    private static boolean active = false;



    public CGoL() {
        warningLabel.setForeground(Color.RED);
        warningLabel.setFont(new Font(warningLabel.getFont().getName(), Font.BOLD, 10));
        conwayPanel.setSize(390,300);
        ((ConwayPanel) conwayPanel).setGrid(390, 300);
        startButton.addActionListener(e -> {
            if (!probTextField.getText().isEmpty()) {
                probability = Double.parseDouble(probTextField.getText());
                if (!active) {
                    ((ConwayPanel) conwayPanel).setGrid(390, 300);
                    ((ConwayPanel) conwayPanel).resetGenerations();
                    ((ConwayPanel) conwayPanel).setupGrid(probability);
                }
                ((ConwayPanel) conwayPanel).startTimer();
                active = true;
            }
            else {
                warningLabel.setText("Не всі поля заповнені!");
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (active) {
                    ((ConwayPanel) conwayPanel).stopTimer();
                    active = false;
                }
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (active) {
                    ((ConwayPanel) conwayPanel).stopTimer();
                }
            }
        });

        probTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                filter(probTextField, e);
            }
        });

        // unblocking blocked element when nothing is being entered in it
        probTextField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent event) {
                probTextField.setEditable(true);
                if (!probTextField.getText().isEmpty()) {
                    active = false;
                }
            }
        });
    }


    class ConwayPanel extends JPanel {
        private int[][] grid;
        private static final int SIZE = 4;
        private int generationCounter = 1;
        private boolean alive = false;

        private Timer timer = new Timer(500, (ActionEvent e) -> {
            alive = false;
            this.updateGrid();
            if (!alive) {
                active = false;
                generationField.setText("The last active generation: ".concat(String.valueOf(generationCounter))
                        .concat(". Press the START button to try again."));
            }
            else {
                generationField.setText("Generation: ".concat(String.valueOf(generationCounter++)));
            }
            this.repaint();
        });


        public ConwayPanel() {
            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }

        public void setGrid(int width, int height) {
            this.grid = new int[height/SIZE][width/SIZE];
            setupGrid(0.0);
        }

        public void resetGenerations() {
            generationCounter = 0;
        }

        public void startTimer() {
            timer.start();
        }
        public void stopTimer() {
            timer.stop();
        }

        private void setupGrid(double prob) {
            for (int[] row : grid) {
                for (int j = 0; j < row.length; j++) {
                    if (rnd.nextDouble() < prob) {
                        row[j] = rnd.nextInt(2);
                    }
                }
            }
        }

        public void updateGrid() {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    applyRule(i, j);
                }
            }
        }

        private void applyRule(int i, int j) {
            // елементи, що розташовані зверху та знизу
            int west = 0, east = 0, north = 0, south = 0;
            // елементи, що розташовані по кутках
            int northwest = 0, northeast = 0,
                    southwest = 0, southeast = 0;

            // ОТРИМАННЯ ЗНАЧЕНЬ ПРАВИХ ЕЛЕМЕНТІВ (ЦЕНТРАЛЬНИЙ ТА КУТОВІ)
            // елемент не знаходиться на крайніх елементах правої межі
            if (j < grid[i].length - 1) {
                east = grid[i][j + 1]; // правий елемент
                if(i > 0)
                    northeast = grid[i - 1][j + 1];
                if (i < grid.length - 1)
                    southeast = grid[i + 1][j + 1];
            }
            // ОТРИМАННЯ ЗНАЧЕНЬ ЛІВИХ ЕЛЕМЕНТІВ (ЦЕНТРАЛЬНИЙ ТА КУТОВІ)
            // елемент не знаходиться на крайніх елементах лівої межі
            if (j > 0) {
                west = grid[i][j - 1];
                if (i > 0)
                    northwest = grid[i - 1][j - 1];
                if (i< grid.length-1)
                    southwest = grid[i + 1][j - 1];
            }
            // ОТРИМАННЯ ЗНАЧЕНЬ ВЕРХНІХ ЕЛЕМЕНТІВ (ЦЕНТРАЛЬНИЙ ТА КУТОВІ)
            // елемент не знаходиться на крайніх елементах верхньої межі
            if (i > 0)
                north = grid[i - 1][j];
            // ОТРИМАННЯ ЗНАЧЕНЬ НИЖНІХ ЕЛЕМЕНТІВ (ЦЕНТРАЛЬНИЙ ТА КУТОВІ)
            // елемент не знаходиться на крайніх елементах нижньої межі
            if (i < grid.length - 1)
                south = grid[i + 1][j];

            // кількість сусідів
            int sum = west + east + north + south
                    + northwest + northeast + southwest + southeast;

            // ЗАСТОСУВАННЯ ПРАВИЛА
            // елемент зникає, якщо у нього 4+ або 1- сусідів
            if (grid[i][j] == 1) {
                if (sum < 2 || sum > 3) {
                    grid[i][j] = 0;
                    alive = true;
                }
            }
            // елемент з'являється, якщо в нього 3 сусіди
            else {
                if (sum == 3) {
                    grid[i][j] = 1;
                    alive = true;
                }
            }
        }


        @Override
        protected void paintComponent(Graphics gg) {
            Graphics2D g = (Graphics2D) gg;
            super.paintComponent(g);
            g.setBackground(Color.WHITE);
            // заповнення елемента кольором, значення якого 1
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 1) {
                        g.setColor(Color.BLUE);
                    }
                    else {
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(j * SIZE, i * SIZE, SIZE, SIZE );
                }
            }
        }
    }

    private void createUIComponents() {
        conwayPanel = new ConwayPanel();
    }

    private void filter(JTextField tf, KeyEvent ke) {
        String value = tf.getText();
        int l = value.length();
        if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')
                || ke.getKeyChar() == '.' || ke.getKeyChar() == '\b'
                || ke.getKeyChar() == '\n') {
            tf.setEditable(true);
            warningLabel.setText("");
        } else {
            tf.setEditable(false);
            warningLabel.setText("Вводьте в поле лише цифри або крапку");
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Conway Game of Life");
        f.setContentPane(new CGoL().mainPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.pack();

    }

}
