package Lab5.examples;

import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class ConwayGameOfLife extends JPanel{
    private int[][] grid;
    private static final Random rnd = new Random();
    private static final int SIZE = 4;
    private int generationCounter;
    private Timer timer = new Timer(100, (ActionEvent e) -> {
        this.updateGrid();
        this.repaint();
    });

    public ConwayGameOfLife(int width, int height) {
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.grid = new int[height / SIZE ][width / SIZE ];
        setupGrid();
    }

    public void startTimer() {
        timer.start();
    }
    public void stopTimer() {
        timer.stop();
    }

    private void setupGrid() {
        for (int[] row : grid) {
            for (int j = 0; j < row.length; j++) {
                if (rnd.nextDouble() < 0.1) {
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
            if (sum < 2)
                grid[i][j] = 0;
            if (sum > 3)
                grid[i][j] = 0;
        }
        // елемент з'являється, якщо в нього 3 сусіди
        else {
            if (sum == 3)
                grid[i][j] = 1;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // розміри вікна
        return new Dimension(grid[0].length * SIZE,
                grid.length * SIZE);
    }
    @Override
    protected void paintComponent(Graphics gg) {
        Graphics2D g = (Graphics2D) gg;
        super.paintComponent(g);
        g.setBackground(Color.WHITE);
        g.drawString("Generation: " + generationCounter++, 0, 10);
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


    public static void main(String[] args) {
        final ConwayGameOfLife c = new ConwayGameOfLife(200 * SIZE, 100 * SIZE );
        JFrame frame = new JFrame("Conway Game of Life");
        frame.getContentPane().add(c);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        ((ConwayGameOfLife) c).startTimer();
    }
}
